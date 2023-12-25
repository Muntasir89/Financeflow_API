package com.monim.financeflow_api.services;

import java.util.List;

import com.monim.financeflow_api.model.UserModel;

public interface AuthService {
    public UserModel login(String email, String password);

    public String signup(UserModel user);

    public UserModel updateUser(String id, String field, String fieldValue);

    public UserModel deleteAccount(String id);

    public UserModel getUser(String id);

    public List<UserModel> getAllUsers();
}
