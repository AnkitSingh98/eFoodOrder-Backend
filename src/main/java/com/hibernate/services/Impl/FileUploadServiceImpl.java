package com.hibernate.services.Impl;

import com.hibernate.exception.BadApiRequest;
import com.hibernate.exception.ResourceNotFoundException;
import com.hibernate.services.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


@Service
public class FileUploadServiceImpl implements FileUploadService{
	
	
	@Override
    public String uploadFile(MultipartFile file, String path) throws IOException {

        //get original name of file
//        abc.png
//        sdgsdfgsadfasdfg.png
        String originalFilename = file.getOriginalFilename();
        
        //generate new random name for image
        String filename = UUID.randomUUID().toString();
        
        //extract extension from original image name
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        
        String filenameWithExtension = filename + extension;
        
        String fullPathWithFilename = path + filenameWithExtension;
        
        if(extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg")) {
        	
        	File folder = new File(path);

            if (!folder.exists()) {
            	
            	folder.mkdirs();
            }
            
            Files.copy(file.getInputStream(), Paths.get(fullPathWithFilename));
            return filenameWithExtension;
        	
        }else {
        	throw new BadApiRequest("File with this extension " + extension + " not allowed");
        }
        
    }

	
	
	
    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {
    	
    	String fullPath = path + name;
        
    	try {
    		
    		InputStream inputStream = new FileInputStream(fullPath);
    		return inputStream;
    		
    	}catch(FileNotFoundException e) {
    		
    		throw new ResourceNotFoundException("Image not found for this user!!");
    		
    	}
    	
        
    }

    
    
    
    @Override
    public void deleteFile(String path) {

    }

}
