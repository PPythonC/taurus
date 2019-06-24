package com.example.taurus.service.impl;

import com.example.taurus.model.domain.User;
import com.example.taurus.model.enums.TrueFalseEnum;
import com.example.taurus.repository.UserRepository;
import com.example.taurus.repository.base.BaseRepository;
import com.example.taurus.service.UserService;
import com.example.taurus.service.base.AbstractCrudService;

import java.util.Date;
import java.util.List;

public class UserServiceImpl extends AbstractCrudService<User, Long> implements UserService {
    private final UserRepository userRepository;

    protected UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public User userLoginByName(String userName, String userPass) {
        return userRepository.findByUserNameAndUserPass(userName, userPass);
    }

    @Override
    public User userLoginByEmail(String userEmail, String userPass) {
        return userRepository.findByUserEmailAndUserPass(userEmail, userPass);
    }

    @Override
    public User findUser() {
        final List<User> users = userRepository.findAll();
        if (users != null && users.size() > 0) {
            return users.get(0);
        } else {
            return new User();
        }
    }

    @Override
    public User findByUserIdAndUserPass(Long userId, String userPass) {
        return userRepository.findByUserIdAndUserPass(userId, userPass);
    }

    @Override
    public void updateUserLoginEnable(String enable) {
        final User user = this.findUser();
        user.setLoginError(0);
        user.setLoginEnable(enable);
        update(user);
    }

    @Override
    public User updateUserLoginLast(Date lastDate) {
        final User user = this.findUser();
        user.setLoginLast(lastDate);
        return update(user);
    }

    @Override
    public Integer updateUserLoginError() {
        final User user = this.findUser();
        user.setLoginError(user.getLoginError() == null ? 0 : user.getLoginError() + 1);
        update(user);
        return user.getLoginError();
    }

    @Override
    public User updateUserNormal() {
        final User user = this.findUser();
        user.setLoginEnable(TrueFalseEnum.TRUE.getDesc());
        user.setLoginError(0);
        user.setLoginLast(new Date());
        return update(user);
    }
}
