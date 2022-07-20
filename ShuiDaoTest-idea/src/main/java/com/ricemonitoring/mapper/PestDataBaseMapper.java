package com.ricemonitoring.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PestDataBaseMapper {
    void addPestInfor(
            String picUrl,
            String pestName,
            String performance,
            String drugName);

}
