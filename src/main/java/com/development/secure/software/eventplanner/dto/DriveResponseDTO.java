package com.development.secure.software.eventplanner.dto;

import lombok.*;

/**
 * @Author Damish Samarajeewa
 * @Date 9/29/2021 6:57 PM
 * @Version 1.0
 * Drive Response DTO Class
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DriveResponseDTO {

    private String uploadedFileName;
    private String status;
}
