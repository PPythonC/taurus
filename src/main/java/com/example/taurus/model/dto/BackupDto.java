package com.example.taurus.model.dto;

import lombok.Data;

import java.util.Date;

//DTO Data Transfer Object between client and services
@Data
public class BackupDto {
    private String fileName;
    private Date createAt;
    private String fileSize;
    private String fileType;
}
