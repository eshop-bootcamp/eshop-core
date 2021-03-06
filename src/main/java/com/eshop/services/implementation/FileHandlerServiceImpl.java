package com.eshop.services.implementation;

import com.eshop.services.FileHandlerService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by srividhya on 13/12/16.
 */
@Service
public class FileHandlerServiceImpl implements FileHandlerService {
    @Override
    public String handleFileUpload(MultipartFile file, Path path) {
        try {
            File directory = new File(path.toString());
            if (!directory.exists()) {
                 directory.mkdir();
            }
            if(!Files.exists(path.resolve(file.getOriginalFilename()))) {
                Files.copy(file.getInputStream(), path.resolve(file.getOriginalFilename()));
            } else{
                String filename = file.getOriginalFilename()+Math.random();
                Files.copy(file.getInputStream(), path.resolve(filename));
                return path.toString()+ File.separator +file.getOriginalFilename();
            }
        } catch (IOException e) {
            return "error";
        }
        return path.toString()+ File.separator +file.getOriginalFilename();
    }
}

