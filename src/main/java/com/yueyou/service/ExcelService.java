package com.yueyou.service;

import com.yueyou.common.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface ExcelService {

    BaseResponse importExcel(MultipartFile excel) throws IOException;
}
