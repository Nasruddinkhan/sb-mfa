package com.mypractice.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mypractice.model.User;
import com.mypractice.repository.UserRepository;
import com.mypractice.validation.EmailExistsException;

@Service
@Transactional
class UserService implements IUserService {

	@Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerNewUser(final User user) throws EmailExistsException {
        if (emailExist(user.getEmail())) {
            throw new EmailExistsException("There is an account with that email address: " + user.getEmail());
        }
        final String passwordEncoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoded);
        user.setPasswordConfirmation(passwordEncoded);
        return repository.save(user);
    }

    private boolean emailExist(String email) {
        final User user = repository.findByEmail(email);
        return user != null;
    }

    @Override
    public User updateExistingUser(User user) throws EmailExistsException {
        final Long id = user.getId();
        final String email = user.getEmail();
        final User emailOwner = repository.findByEmail(email);
        if (emailOwner != null && !id.equals(emailOwner.getId())) {
            throw new EmailExistsException("Email not available.");
        }
        final String password = user.getPassword();
        if (password != null) {
            final String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
            user.setPasswordConfirmation(encodedPassword);
        }
        return repository.save(user);
    }
}
