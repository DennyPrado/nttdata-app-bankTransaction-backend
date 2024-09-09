package com.nttdata.backend.service.impl;

import com.nttdata.backend.dto.AccountsDTO;
import com.nttdata.backend.dto.MovementsDTO;
import com.nttdata.backend.exception.ModeloNotFoundException;
import com.nttdata.backend.model.Accounts;
import com.nttdata.backend.model.Movements;
import com.nttdata.backend.repository.IAccountsRepo;
import com.nttdata.backend.repository.IMovementsRepo;
import com.nttdata.backend.service.IAccountsService;
import com.nttdata.backend.service.IMovementsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovementsServiceImpl implements IMovementsService  {

    private final IMovementsRepo movementsRepo;

    private final IAccountsService accountService;

    private final ModelMapper mapper;

    private String userGeneric = "Dprado";

    @Override
    public void register(MovementsDTO movementsDTO) throws Exception {

        try{
            AccountsDTO accountDTO = accountService.listById(movementsDTO.getAccountId());

            if(accountDTO != null){
                String  typeOfMovement = "";
                BigDecimal movementValue = BigDecimal.valueOf(0);
                if (movementsDTO.getMovementValue().compareTo(BigDecimal.ZERO) > 0){
                    typeOfMovement ="Deposito";
                    movementValue  = movementsDTO.getMovementValue();
                }else{
                    typeOfMovement ="Retiro";
                    movementValue  = movementsDTO.getMovementValue().multiply(BigDecimal.valueOf(-1));
                }
                //-- Valido si tiene saldo

                if( (accountDTO.getInitialBalance().compareTo(movementValue) < 0) && (typeOfMovement.equals("Retiro")) ){
                    throw new ModeloNotFoundException("Saldo no disponible");
                }else{
                    movementsDTO.setCreatedByUser(userGeneric);
                    movementsDTO.setCreatedDate(new Date());
                    movementsDTO.setTypeOfMovement(typeOfMovement);
                    //-- Actualizo saldos
                    accountDTO.setInitialBalance(accountDTO.getInitialBalance().add(movementsDTO.getMovementValue()));
                    movementsDTO.setMovementDate(new Date());
                    movementsDTO.setBalance(accountDTO.getInitialBalance());
                    movementsDTO.setState(Boolean.TRUE);

                    Movements movements = mapper.map(movementsDTO, Movements.class);
                    movementsRepo.save(movements);
                    accountService.register(accountDTO);
                }
            }else{
                throw new ModeloNotFoundException("Estimado usuario el id de la cuente no existe ");
            }
        }catch (Exception e){
            throw new ModeloNotFoundException("Ocurrió un error en el proceso register : {} " +e.getMessage());
        }

    }

    @Override
    public void modify(MovementsDTO movementsDTO) throws Exception {
        try{
            movementsDTO.setLastModifiedByUser(userGeneric);
            movementsDTO.setLastModifiedDate(new Date());

            Movements movements    = mapper.map(movementsDTO, Movements.class);
            Movements resp         = movementsRepo.save(movements);
        }catch (Exception e){
            throw new ModeloNotFoundException("Ocurrió un error en el proceso modify: {} " + e.getMessage());
        }

    }

    @Override
    public List<Movements> list() throws Exception {
        try{
            return movementsRepo.findAll();
        }catch (Exception e){
            throw new ModeloNotFoundException("Ocurrió un error en el proceso list: {} " + e.getMessage());
        }
    }

    @Override
    public MovementsDTO listById(Integer id) throws Exception {
        MovementsDTO movementsDTO = null;
        try{
            Optional<Movements> movements = movementsRepo.findById(id);
            if (movements.isPresent()) {
                movementsDTO = mapper.map(movements.get(), MovementsDTO.class);
            }
        }catch (Exception e){
            throw new ModeloNotFoundException("Ocurrió un error en el proceso listById: {} " + e.getMessage());
        }
        return movementsDTO;
    }

    @Override
    public void eliminate(Integer id) throws Exception {
        try{
            movementsRepo.deleteById(id);
        }catch (Exception e){
            throw new ModeloNotFoundException("Ocurrió un error en el proceso eliminate: {} " + e.getMessage());
        }
    }

}
