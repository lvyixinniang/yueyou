package com.yueyou.service.impl;

import cn.hutool.core.date.StopWatch;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.StringUtils;
import com.google.gson.Gson;
import com.yueyou.common.BaseResponse;
import com.yueyou.domain.ExcelImportANDExportDto;
import com.yueyou.domain.User;
import com.yueyou.service.ExcelService;
import com.yueyou.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExcelServiceImpl implements ExcelService {
    final int INSERT_MAX = 1000;

    @Autowired
    private UserService userService;

//    CPU 密集型; 分配的核心线程数 = CPU - 1 ( 如 , CPU 内存计算
//    IO 密集型; 分配的核心线程数大于 CPU 核数 一般设置 CPU 核心线程数 两倍左右   ( 如 ,消息队列 , 磁盘读写
    private ExecutorService executorService = new ThreadPoolExecutor(32, 100, 20, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10000));


    @Override
    public BaseResponse importExcel(MultipartFile file) throws IOException {
//        1 . 加一个去重   2 . 异步 多线程  并发执行  3 . 多测试 一下 慢sql (MP) 每次建立连接 耗时  4 . 插入前 , 查看 是否已经存在 这样得数据  5 . 定时删除 重复项  6 redis 缓存 预热 等等
//        主要目的 , 还是 优化代码 , 提高代码性能

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        File excel = convertMultipartFileToFile(file);
//        fileName = "src\\main\\resources\\template\\importANDexport.xlsx";
        Gson gson = new Gson();
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 同步读取会自动finish
        List<ExcelImportANDExportDto> list = EasyExcel.read( excel).head(ExcelImportANDExportDto.class).sheet().doReadSync();
//        去重
        Map<String, List<ExcelImportANDExportDto>> removeListMap = list
                .stream()
                .filter(excelinfo-> StringUtils.isNotBlank(excelinfo.getUsername()))
                .collect(Collectors.groupingBy(ExcelImportANDExportDto::getUsername));

        for (Map.Entry<String, List<ExcelImportANDExportDto>> stringListEntry : removeListMap.entrySet()) {
            if (stringListEntry.getValue().size() > 1 ){
                System.out.println("userName = " + stringListEntry.getKey());
            }
        }
        Date currTime = new Date();

//      添加进数据库
//      多线程 , 减少 sql 建立连接 的时间
//        TODO 简历必加项 在速率上优化了 几倍
        List<CompletableFuture<Void>> futureList = new ArrayList<>();
        for (int sum = 0; sum < list.size(); ) {
            List<User> userlist = new ArrayList<>();
            for (ExcelImportANDExportDto data : list) {
                log.info("读取到数据:{}", gson.toJson(data));
                User user = new User();
                BeanUtils.copyProperties(data, user);
                user.setUpdatetime(currTime);
                user.setCreatetime(currTime);
                userlist.add(user);
                sum ++;
                if (sum == list.size() || sum % 1000 == 0) {

                }
            }
//            现在 这是默认的 异步线程池的  ForkJoinPool.commonPool()作为默认的线程池。 但这个速率受到 本身机械 的cpu核数的限制
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                userService.saveBatch(userlist,INSERT_MAX);
            }, executorService);
            futureList.add(future);
        }
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();

        stopWatch.stop();
//        第一次 8570.16 ms 第二次 7771.81 ms 第三次 7795.53 ms
//       第一次  1747.69 ms 第三次 1502.49 ms 第三次 809.57 ms 第四次 824.09 ms 第五次 848.12 ms 第六次 821.50 ms
//       第一次  1573 ms 第二次 814 ms 第三次 815 ms 第四次 818 ms 第五次 823 ms 第六次 830 ms
//
        log.info("总共耗时 : {}",stopWatch.getTotalTimeMillis());

        return new BaseResponse("上传成功");
    }


    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
