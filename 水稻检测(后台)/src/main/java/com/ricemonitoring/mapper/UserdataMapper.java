package com.ricemonitoring.mapper;


import com.ricemonitoring.pojo.person_people;
import com.ricemonitoring.pojo.picture;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserdataMapper {

    person_people getAll(String account);

    void saveImage(picture pic);
}
