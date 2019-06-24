package com.example.taurus.repository;

import com.example.taurus.model.domain.User;
import com.example.taurus.repository.base.BaseRepository;

/**
 *
 */
public interface UserRepository extends BaseRepository<User, Long> {
    /**
     * @param userName
     * @param userPass
     * @return User
     */
    User findByUserNameAndUserPass(String userName, String userPass);

    /**
     * @param userEmail
     * @param userPass
     * @return User
     */
    User findByUserEmailAndUserPass(String userEmail, String userPass);

    /**
     * @param userId
     * @param userPass
     * @return User
     */
    User findByUserIdAndUserPass(Long userId, String userPass);
}
