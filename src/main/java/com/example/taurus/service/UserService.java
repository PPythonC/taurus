package com.example.taurus.service;

import java.util.Date;
import com.example.taurus.model.domain.User;

public interface UserService {
    /**
     * 根据用户名和密码查询，用于登录
     *
     * @param userName userName
     * @param userPass userPass
     * @return User
     */
    User userLoginByName(String userName, String userPass);

    /**
     * 根据邮箱和密码查询，用户登录
     *
     * @param userEmail userEmail
     * @param userPass  userPass
     * @return User
     */
    User userLoginByEmail(String userEmail, String userPass);

    /**
     * 查询所有用户
     *
     * @return User
     */
    User findUser();

    /**
     * 根据用户编号和密码查询
     *
     * @param userId   userid
     * @param userPass userpass
     * @return User
     */
    User findByUserIdAndUserPass(Long userId, String userPass);

    /**
     * 修改禁用状态
     *
     * @param enable enable
     */
    void updateUserLoginEnable(String enable);

    /**
     * 修改最后登录时间
     *
     * @param lastDate 最后登录时间
     * @return User
     */
    User updateUserLoginLast(Date lastDate);

    /**
     * 增加登录错误次数
     *
     * @return 登录错误次数
     */
    Integer updateUserLoginError();

    /**
     * 修改用户的状态为正常
     *
     * @return User
     */
    User updateUserNormal();
}
