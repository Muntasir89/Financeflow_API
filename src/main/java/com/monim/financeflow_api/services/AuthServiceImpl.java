package com.monim.financeflow_api.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monim.financeflow_api.model.UserModel;
import com.monim.financeflow_api.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserModel login(String email, String password){
        List<UserModel>  userList = userRepository.findByEmail(email);

        if(userList.size()>0 && password.equals(userList.get(0).getPassword())){
            return userList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public String signup(UserModel user) {
        List<UserModel> users = userRepository.findByEmail(user.getEmail());
        if(users.size()>0)
            return "Email already exists";
        try{
            userRepository.save(user);
            return "";
        }catch(Exception e){
            return "Something went wrong";
        }
    }

    @Override
    public UserModel updateUser(String id, String field, String fieldValue) {
        Optional<UserModel> userDB = userRepository.findById(id);
        if(userDB.isPresent()){
            switch (field) {
                case "name":
                    userDB.get().setName(fieldValue);
                    break;
                case "mobile_no":
                    userDB.get().setMobile_no(fieldValue);
                    break;
                case "address":
                    userDB.get().setAddress(fieldValue);
                    break;
                case "monthly_income":
                    userDB.get().setMonthly_income(fieldValue);
                    break;
                case "profession":
                    userDB.get().setProfession(fieldValue);
                    break;
                case "company":
                    userDB.get().setCompany(fieldValue);
                    break;
                default:
                    break;
            }

            userRepository.save(userDB.get());
            return userDB.get();
        }
        return null;
    }

    @Override
    public UserModel deleteAccount(String id) {
        Optional<UserModel> user = userRepository.findById(id);
        if(user.isPresent())
        {
            userRepository.deleteById(id);
            return user.get();
        }
        return null;
    }

    @Override
    public UserModel getUser(String id){
        Optional<UserModel> user =  userRepository.findById(id);
        if(user.isPresent())
            return user.get();
        return null;
    }

    @Override
    public List<UserModel> getAllUsers() {
        List<UserModel> allUsers = userRepository.findAll();
        return allUsers;
    }
}
