package com.ricemonitoring.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RiceCheckPointPicMapper {
    void addCKPicInfor( String country,String picUrl,String description, String code) ;

}
