package com.hibernate.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileUploadService {
	
	
	//create a file on server at given
    String uploadFile(MultipartFile file, String path) throws IOException;

    //get the resource
    InputStream getResource(String path, String name) throws FileNotFoundException;

    //delete file
    void deleteFile(String path);

}
