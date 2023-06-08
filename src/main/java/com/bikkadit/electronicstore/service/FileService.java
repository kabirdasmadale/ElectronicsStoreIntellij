package com.bikkadit.electronicstore.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {

    String uplodfiles(MultipartFile file, String path) throws IOException;

        InputStream getResorse(String path,String name) throws FileNotFoundException;
    }

