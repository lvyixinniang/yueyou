package com.yueyou;

import com.alibaba.excel.EasyExcel;

import com.google.gson.Gson;
import com.yueyou.common.listener.ExcelImportANDExportDtoListener;
import com.yueyou.domain.ExcelImportANDExportDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;


@SpringBootTest
@Slf4j
class YueyouApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * 最简单的读
     */
    @Test
    public void simpleRead() {
        // 写法1：JDK8+ ,不用额外写一个ExcelImportANDExportDtoListener
        // since: 3.0.0-beta1
        String fileName = "src\\main\\resources\\template\\importANDexport.xlsx";
        // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
        // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
        EasyExcel.read(fileName, ExcelImportANDExportDto.class, new ExcelImportANDExportDtoListener()).sheet().doRead();
    }

    /**
     * 同步度 , 但不需要监听器
     */
    @Test
    public void synchronousRead(){
        String fileName = "src\\main\\resources\\template\\importANDexport.xlsx";
        Gson gson = new Gson();
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 同步读取会自动finish
        List<ExcelImportANDExportDto> list = EasyExcel.read(fileName).head(ExcelImportANDExportDto.class).sheet().doReadSync();
        for (ExcelImportANDExportDto data : list) {
            log.info("读取到数据:{}", gson.toJson(data));
        }
        // 这里 也可以不指定class，返回一个list，然后读取第一个sheet 同步读取会自动finish
        List<Map<Integer, String>> listMap = EasyExcel.read(fileName).sheet().doReadSync();
        for (Map<Integer, String> data : listMap) {
            // 返回每条数据的键值对 表示所在的列 和所在列的值
            log.info("读取到数据:{}",  gson.toJson(data));
        }
    }
}
