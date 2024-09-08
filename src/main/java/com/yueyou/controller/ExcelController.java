package com.yueyou.controller;

import com.alibaba.excel.EasyExcel;
import com.google.gson.Gson;
import com.yueyou.common.BaseResponse;
import com.yueyou.domain.ExcelImportANDExportDto;
import com.yueyou.service.ExcelService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/excel")
@ApiModel
public class ExcelController {
    @Autowired
    private ExcelService excelService;

    @PostMapping("/inport")
    @ApiOperation("excel的导入")
    public BaseResponse ImportExcel(MultipartFile excel) throws IOException {
        BaseResponse baseResponse = excelService.importExcel(excel);
        return baseResponse;
    }



}
