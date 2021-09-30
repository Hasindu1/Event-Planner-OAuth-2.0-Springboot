package com.development.secure.software.eventplanner.service;

import com.development.secure.software.eventplanner.dto.DriveRequestDTO;
import com.development.secure.software.eventplanner.dto.DriveResponseDTO;
import com.development.secure.software.eventplanner.interceptor.RequestInterceptor;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * @Author Damish Samarajeewa
 * @Date 9/29/2021 6:57 PM
 * @Version 1.0
 * Service Layer Implementation for Drive Service
 */
public class DriveServiceImpl extends RequestInterceptor implements DriveService {
    /**
     * @implNote The constructor accepts accesstoken
     * @param accessToken
     */
    public DriveServiceImpl(String accessToken) {
        super(accessToken);
    }

    /**
     * The Application Name
     */
    @Value("${app.name}")
    private String APPLICATION_NAME;

    /**
     * @implNote upload file using google drive api
     * @param driveRequestDTO
     * @return DriveResponseDTO
     * @throws IOException
     */
    @Override
    public DriveResponseDTO uploadFile(DriveRequestDTO driveRequestDTO) throws IOException {
        //get credentials by passing accessToken
        GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);
        //Initiate Google Drive API
        Drive service = new Drive.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
                .setApplicationName(APPLICATION_NAME).build();

        //new com.google.api.services.drive.model.File for drive upload
        File uploadfile = new File();

        //convert multipart file to java.io.File
        java.io.File file2 = multipartToFile(driveRequestDTO.getFile(), driveRequestDTO.getFile().getName());

        //set file name to unique id
        String uniqueid = new Date().toString();
        uploadfile.setName(uniqueid);

        //get temp file path
        java.io.File filePath = new java.io.File(file2.getPath());

        FileContent mediaContent = new FileContent("image/jpeg", filePath);
        File uploadedfile = service.files().create(uploadfile, mediaContent)
                .setFields("id")
                .execute();

        if (uploadedfile != null) {
            return DriveResponseDTO.builder().uploadedFileName(driveRequestDTO.getFile().getOriginalFilename()).status("OK").build();
        } else {
            return DriveResponseDTO.builder().uploadedFileName("").status("FAILED").build();
        }

    }

    /**
     * @implNote method to convert multipart file to java.io.File and save in temp dir
     * @param multipart
     * @param fileName
     * @return java.io.File
     * @throws IllegalStateException
     * @throws IOException
     */
    public static java.io.File multipartToFile(MultipartFile multipart, String fileName) throws IllegalStateException, IOException {

        java.io.File convFile = new java.io.File(System.getProperty("java.io.tmpdir") + "/" + fileName);
        multipart.transferTo(convFile);
        return convFile;
    }

}
