package com.rkostiuk.task.service;

import com.rkostiuk.task.entity.User;
import com.rkostiuk.task.exception.UserNotFoundException;
import com.rkostiuk.task.exception.UserPatchValidationException;
import com.rkostiuk.task.repository.UserRepository;
import com.rkostiuk.task.util.BeanHelper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Transactional(readOnly = true)
@Service
public class DataJpaUserService implements UserService {
    private final UserRepository userRepository;
    private final BeanHelper beanHelper;
    private final Validator validator;
    private final BCryptPasswordEncoder passwordEncoder;

    public DataJpaUserService(UserRepository userRepository, BeanHelper beanHelper, Validator validator) {
        this.userRepository = userRepository;
        this.beanHelper = beanHelper;
        this.validator = validator;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Page<User> findUserPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findUserById(long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Transactional
    @Override
    public User addUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public void patchUser(long userId, User userPatch) throws UserNotFoundException, UserPatchValidationException {
        String encodedPassword = passwordEncoder.encode(userPatch.getPassword());
        userPatch.setPassword(encodedPassword);
        User existingUser = findUserById(userId);
        beanHelper.copyNonNullProperties(existingUser, userPatch);
        validatePatchedUser(existingUser);
    }

    private void validatePatchedUser(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            throw new UserPatchValidationException(violations);
        }
    }

    @Transactional
    @Override
    public void deleteUserById(long id) throws UserNotFoundException {
        User user = findUserById(id);
        userRepository.delete(user);
    }
}
