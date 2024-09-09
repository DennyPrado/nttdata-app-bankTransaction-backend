package com.nttdata.backend.service;

import com.nttdata.backend.dto.AccountStatusReportDTO;
import com.nttdata.backend.dto.MovementsDTO;
import com.nttdata.backend.model.Movements;

import java.time.LocalDateTime;
import java.util.List;

public interface IMovementsService {

    void register(MovementsDTO movementsDTO) throws Exception;
    void modify(MovementsDTO movementsDTO) throws Exception;
    List<Movements> list() throws Exception;
    MovementsDTO listById(Integer id) throws Exception;
    void eliminate(Integer id) throws Exception;
}
