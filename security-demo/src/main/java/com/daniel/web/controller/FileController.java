package com.daniel.web.controller;

import com.daniel.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 文件资源管理控制
 *
 * @author daniel
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private final String FOLDER = "E:/";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        File localFile = new File(FOLDER, System.currentTimeMillis()+ ".txt");
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) throws Exception {
        try (
                InputStream inputStream = new FileInputStream(new File(FOLDER, id + ".txt"));
                OutputStream outputStream = response.getOutputStream()
        ) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=test.txt");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }
    }

}
