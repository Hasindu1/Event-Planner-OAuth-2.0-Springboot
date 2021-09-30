package com.development.secure.software.eventplanner.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Damish Samarajeewa
 * @Date 9/29/2021 6:57 PM
 * @Version 1.0
 * Drive Request DTO Class
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DriveRequestDTO {

    private MultipartFile file;
}
