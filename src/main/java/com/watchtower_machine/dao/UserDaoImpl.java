package com.watchtower_machine.dao;

import com.watchtower_machine.entity.User;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements IUserDao {
    @Override
    public int create(User entity) {
        return 0;
    }

    @Override
    public List<User> selectAll() {
        return null;
    }

    @Override
    public Optional<User> selectById(String id) {
        return Optional.empty();
    }

    @Override
    public void updateById(String id, User newData) {

    }

    @Override
    public int delete(String id) {
        return 0;
    }
}
