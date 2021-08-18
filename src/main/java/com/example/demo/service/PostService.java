package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public interface PostService {

    void CreateAndSavePostToDB(String city, String district, String ward, String address,
                      Float area, String description, MultipartFile file,
                      LocalDateTime date_created, Integer idUser);
}
