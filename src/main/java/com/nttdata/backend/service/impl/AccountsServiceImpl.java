package com.nttdata.backend.service.impl;

import com.nttdata.backend.dto.AccountsDTO;
import com.nttdata.backend.exception.ModeloNotFoundException;
import com.nttdata.backend.model.Accounts;
import com.nttdata.backend.repository.IAccountsRepo;
import com.nttdata.backend.service.IAccountsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private final IAccountsRepo accountsRepo;

    private final ModelMapper mapper;

    private String userGeneric = "Dprado";

    @Override
    public void register(AccountsDTO accountsDTO) throws Exception {
        try{
            accountsDTO.setCreatedByUser(userGeneric);
            accountsDTO.setCreatedDate(new Date());

            Accounts accounts = mapper.map(accountsDTO, Accounts.class);

            accountsRepo.save(accounts);
        }catch (Exception e){
            throw new ModeloNotFoundException("Ocurrió un error en el proceso register: {} " + e.getMessage());
        }
    }

    @Override
    public void modify(AccountsDTO accountsDTO) throws Exception {
        try{
            AccountsDTO obj = this.listById(accountsDTO.getAccountId());

            if(obj == null) {
                throw new ModeloNotFoundException("El ID de la cuenta no existe");
            }else{
                accountsDTO.setCreatedByUser(obj.getCreatedByUser());
                accountsDTO.setCreatedDate(obj.getCreatedDate());
                accountsDTO.setLastModifiedByUser(userGeneric);
                accountsDTO.setLastModifiedDate(new Date());

                Accounts accounts    = mapper.map(accountsDTO, Accounts.class);
                Accounts resp        = accountsRepo.save(accounts);
            }
        }catch (Exception e){
            throw new ModeloNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<Accounts> list() throws Exception {
        try{
            return accountsRepo.findAll();
        }catch (Exception e){
            throw new ModeloNotFoundException("Ocurrió un error en el proceso list: {} " + e.getMessage());
        }
    }

    @Override
    public AccountsDTO listById(Integer id) throws Exception {
        AccountsDTO accountsDTO = null;
        try{
            Optional<Accounts> client = accountsRepo.findById(id);
            if (client.isPresent()) {
                accountsDTO = mapper.map(client.get(), AccountsDTO.class);
            }
        }catch (Exception e){
            throw new ModeloNotFoundException("Ocurrió un error en el proceso listById: {} " + e.getMessage());
        }
        return accountsDTO;
    }

    @Override
    public void eliminate(Integer id) throws Exception {
        try{
            AccountsDTO obj = this.listById(id);

            if(obj == null) {
                throw new ModeloNotFoundException("El ID de la cuenta a eliminar no existe");
            }else{
                accountsRepo.deleteById(id);
            }
        }catch (Exception e){
            throw new ModeloNotFoundException(e.getMessage());
        }
    }

}
