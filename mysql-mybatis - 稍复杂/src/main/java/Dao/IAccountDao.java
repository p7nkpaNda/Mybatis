package Dao;

import domain.AccountUser;

import java.util.List;

public interface IAccountDao {
    List<AccountUser> findAll();
}
