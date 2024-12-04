package com.chriscode.sprintbootnotion.DAO;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.chriscode.sprintbootnotion.Entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserImpl implements UserRepository {

    DynamoDBMapper dynamoDBMapper;

    public UserImpl(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public Optional<User> findByEmail(String email) {

        User user = dynamoDBMapper.load(User.class, email);
        return Optional.ofNullable(user);
    }

    @Override
    public User createUser(User user) {

        dynamoDBMapper.save(user);
        return user;

    }

    @Override
    public User updateUser(User user) {

        User desiredUser = dynamoDBMapper.load(User.class, user.getId());
        dynamoDBMapper.save(user);
        return desiredUser;

    }

    @Override
    public List<User> getAllUsers() {

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        List<User> users = dynamoDBMapper.scan(User.class, scanExpression);
        return users;

    }

    @Override
    public Optional <User> getUserById(String id) {

        User user = dynamoDBMapper.load(User.class, id);
        return Optional.ofNullable(user);

    }

    @Override
    public String deleteUserById(String id) {

        User user = dynamoDBMapper.load(User.class, id);
        dynamoDBMapper.delete(user);
        return "User " + id + " deleted";
    }


}
