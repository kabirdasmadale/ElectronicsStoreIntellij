package com.bikkadit.electronicstore.service.impl;

import com.bikkadit.electronicstore.exception.BadApiRequestException;
import com.bikkadit.electronicstore.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

   // private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    @Override
    public String uplodfiles(MultipartFile file, String path) throws IOException {
        log.info(" Starting request  for uplod image");
        String originalFilename = file.getOriginalFilename();
        log.info("filename:{}", originalFilename);
        String filename = UUID.randomUUID().toString();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileNameWithExtension = filename + extension;
        String fullPathWithFileName = path  + fileNameWithExtension;

        if (extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg")) {
            // file save
            File folder = new File(path);
            if (!folder.exists()) {
                // create the folder
                folder.mkdirs();
            }
            Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
            return fileNameWithExtension;
        } else {
            throw new BadApiRequestException("file with this " + extension + "not allowed");
        }

    }

    @Override
    public InputStream getResorse(String path, String name) throws FileNotFoundException {
        log.info(" Starting request  for get resorse");
        String fulPath=path+File.separator+name;
        InputStream inputStream=new FileInputStream(fulPath);
        log.info(" Ending request for get resorse");
        return inputStream;
    }
}
