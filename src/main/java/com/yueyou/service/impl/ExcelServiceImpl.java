package com.yueyou.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.StringUtils;
import com.google.gson.Gson;
import com.yueyou.domain.ExcelImportANDExportDto;
import com.yueyou.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExcelServiceImpl implements ExcelService {
    @Override
    public String importExcel(String fileName) {
        fileName = "src\\main\\resources\\template\\importANDexport.xlsx";
        Gson gson = new Gson();
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 同步读取会自动finish
        List<ExcelImportANDExportDto> list = EasyExcel.read(fileName).head(ExcelImportANDExportDto.class).sheet().doReadSync();
//        去重
        Map<String, List<ExcelImportANDExportDto>> removeListMap = list
                .stream()
                .filter(excelinof-> StringUtils.isNotBlank(excelinof.getUsername()))
                .collect(Collectors.groupingBy(ExcelImportANDExportDto::getUsername));

        for (Map.Entry<String, List<ExcelImportANDExportDto>> stringListEntry : removeListMap.entrySet()) {
            if (stringListEntry.getValue().size() > 1 ){
                System.out.println("userName = " + stringListEntry.getKey());
            }
        }

//        添加进数据库
        for (ExcelImportANDExportDto data : list) {
            log.info("读取到数据:{}", gson.toJson(data));
        }
        // 这里 也可以不指定class，返回一个list，然后读取第一个sheet 同步读取会自动finish
        List<Map<Integer, String>> listMap = EasyExcel.read(fileName).sheet().doReadSync();
        for (Map<Integer, String> data : listMap) {
            // 返回每条数据的键值对 表示所在的列 和所在列的值
            log.info("读取到数据:{}",  gson.toJson(data));
        }
    return "成功导入";
    }
}
