package com.yueyou.controller;

import com.alibaba.excel.EasyExcel;
import com.google.gson.Gson;
import com.yueyou.domain.ExcelImportANDExportDto;
import com.yueyou.service.ExcelService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ExcelController {
    @Autowired
    private ExcelService excelService;

    @PostMapping
    @ApiOperation("excel的导入导出")
    public String ImportExcel(String fileName) {
        String msg = excelService.importExcel(fileName);
        return msg;
    }
}
