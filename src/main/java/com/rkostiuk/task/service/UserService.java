package com.rkostiuk.task.service;

import com.rkostiuk.task.entity.User;
import com.rkostiuk.task.exception.UserNotFoundException;
import com.rkostiuk.task.exception.UserPatchValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    Page<User> findUserPage(Pageable pageable);

    User findUserById(long id) throws UserNotFoundException;

    Optional<User> findUserByLogin(String login);

    /**
     * Save new user
     * @return User object with generated id
     */
    User addUser(User user);

    /**
     * Set all non-null values of provided User object to existing one
     * @param userId user to be updated id
     * @param user contains user data
     */
    void patchUser(long userId, User user) throws UserNotFoundException, UserPatchValidationException;

    void deleteUserById(long userId) throws UserNotFoundException;
}
