package com.mypractice.service;

import com.mypractice.model.User;
import com.mypractice.validation.EmailExistsException;

public interface IUserService {
	User registerNewUser(User user) throws EmailExistsException;

    User updateExistingUser(User user) throws EmailExistsException;
}
