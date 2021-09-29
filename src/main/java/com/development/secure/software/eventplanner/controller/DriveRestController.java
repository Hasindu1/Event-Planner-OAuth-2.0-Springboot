package com.development.secure.software.eventplanner.controller;

import com.development.secure.software.eventplanner.dto.DriveRequestDTO;
import com.development.secure.software.eventplanner.dto.DriveResponseDTO;
import com.development.secure.software.eventplanner.service.DriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author Damish Samarajeewa
 * @Date 9/29/2021 6:57 PM
 * @Version 1.0
 * Rest Controller for Drive Service
 */
@RestController
@RequestMapping("/api")
public class DriveRestController {

    /** The Drive Service */
    @Autowired
    private DriveService driveService;

    /**
     * @apiNote upload file through Google Drive api
     * @param uploadedFile
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/drive/upload-one", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CrossOrigin(origins = "*")
    public String uploadFile(@RequestParam("files") MultipartFile uploadedFile) throws IOException {

        DriveRequestDTO driveRequestDTO = DriveRequestDTO.builder().file(uploadedFile).build();

        DriveResponseDTO driveResponseDTO = driveService.uploadFile(driveRequestDTO);

        if (driveResponseDTO.getStatus().equals("OK")){
            System.out.println("Successfully uploaded file: "+driveResponseDTO.getUploadedFileName());
            return "Successfully uploaded file: "+driveResponseDTO.getUploadedFileName();
        }else{
            return "Uploading to Google Drive failed !!!";
        }
    }

}
