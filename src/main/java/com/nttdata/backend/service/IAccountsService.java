package com.nttdata.backend.service;

import com.nttdata.backend.dto.AccountsDTO;
import com.nttdata.backend.model.Accounts;

import java.util.List;

public interface IAccountsService {

    void register(AccountsDTO clientDTO) throws Exception;
    void modify(AccountsDTO clientDTO) throws Exception;
    List<Accounts> list() throws Exception;
    AccountsDTO listById(Integer id) throws Exception;
    void eliminate(Integer id) throws Exception;

}
