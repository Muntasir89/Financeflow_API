package com.monim.financeflow_api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;
import com.monim.financeflow_api.model.UserModel;
import java.util.List;


@Repository
@EnableMongoRepositories
public interface UserRepository extends MongoRepository<UserModel, String>{
    List<UserModel> findByEmail(String email);
}