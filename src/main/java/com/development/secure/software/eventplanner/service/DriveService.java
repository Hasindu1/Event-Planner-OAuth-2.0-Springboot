package com.development.secure.software.eventplanner.service;

import com.development.secure.software.eventplanner.dto.DriveRequestDTO;
import com.development.secure.software.eventplanner.dto.DriveResponseDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Author Damish Samarajeewa
 * @Date 9/29/2021 6:57 PM
 * @Version 1.0
 * Service Layer for Drive Service
 */
@Service
public interface DriveService {

    DriveResponseDTO uploadFile(DriveRequestDTO driveRequestDTO) throws IOException;

}
