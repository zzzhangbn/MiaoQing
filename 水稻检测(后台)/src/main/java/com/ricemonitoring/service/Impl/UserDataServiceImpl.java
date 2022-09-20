package com.ricemonitoring.service.Impl;

import com.ricemonitoring.mapper.UserdataMapper;
import com.ricemonitoring.pojo.person_people;
import com.ricemonitoring.pojo.picture;
import com.ricemonitoring.service.UserDataService;
import com.ricemonitoring.util.saveImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserDataServiceImpl implements UserDataService {

    @Autowired
    UserdataMapper userdataMapper;

    @Autowired
    saveImage saveImage;

    @Override
    public Map<String, Object> getData(person_people people) {
        Map<String, Object> map = new HashMap<>();
        String account = people.getAccount();
        person_people person = userdataMapper.getAll(account);
        String name = person.getName();
        String phone = person.getPhone();
        String crop = person.getCrop();
        map.put("name", name);
        map.put("phone", phone);
        map.put("crop", crop);
        return map;
    }

    @Value("${file.upload-path}")
    private String pictureurl;

    @Override
    public String upload(MultipartFile file, String name) {
        String s = saveImage.saveFile(file, name);
        System.out.println(s);
        picture pic = new picture();
        //拼接url，采用随机数，保证每个图片的url不同
        UUID uuid = UUID.randomUUID();
        //拼接后的url
        String pictureurl = file.getOriginalFilename().replace(".","")+uuid;
        //图片名称
        String picturename =file.getOriginalFilename();
        //将url和name 分别set
        pic.setPictureurl(pictureurl);
        pic.setPicturename(picturename);
        //存入数据库，这里可以加if判断
        userdataMapper.saveImage(pic);
        return "success";
    }

    @Override
    public String getImage(String account) {
        String url;
        url = "\\src\\main\\resources\\img" + account;
        return url;
    }
}
