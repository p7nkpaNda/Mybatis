package Dao;

import domain.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll();
}
