package com.ricemonitoring.mapper;

import com.ricemonitoring.pojo.account_manage;
import com.ricemonitoring.pojo.person_login;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface AccountManageMapper {

    // 通过name查询账号绑定信息
    List<account_manage> getAccount(String name);
    // 绑定账号快速登录
    person_login loginQuick(person_login person);
    // 添加绑定账号
    void accountAdd(account_manage manage);
    // 判定账号是否已经绑定
    account_manage isExist(account_manage manage);
    // 账号解除绑定
    void deleteAccount(account_manage manage);
}
