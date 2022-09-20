package com.ricemonitoring.mapper;



import com.ricemonitoring.pojo.login_record;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserReportMapper {
    //查询页面
    /*List<login_record> getAllRecord(@Param("currentpage") Integer currentpage,
                                    @Param("pagesize") Integer pagesize,
                                    @Param("account") String account,
                                    @Param("logintime") String logintime,
                                    @Param("status") String status);*/
    /*List<login_record> getAllRecord(Integer currentpage,
                                    Integer pagesize,
                                    login_record record);*/
    List<login_record> getAllRecord(Map<String,Object> records);
    //查询总记录数
    int selectCount(Map<String,Object> records);

}
