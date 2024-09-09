package com.nttdata.backend.repository;


import com.nttdata.backend.model.User;

public interface IUserRepo extends IGenericRepo<User, Integer>{

    User findOneByUsername(String username);
}
