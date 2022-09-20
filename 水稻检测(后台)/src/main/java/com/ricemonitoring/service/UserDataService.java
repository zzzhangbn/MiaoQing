package com.ricemonitoring.service;

import com.ricemonitoring.pojo.person_people;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface UserDataService {
    Map<String, Object> getData(person_people people);

    String upload(MultipartFile file, String name);

    String getImage(String account);
}
