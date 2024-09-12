package com.nttdata.backend.controller;

import com.nttdata.backend.dto.AccountsDTO;
import com.nttdata.backend.dto.BaseResponseVo;
import com.nttdata.backend.model.Accounts;
import com.nttdata.backend.service.IAccountsService;
import com.nttdata.backend.util.ResponseType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/cuentas")
@RequiredArgsConstructor
public class AccountsController {

    private final IAccountsService accountsService;

    private static final Logger logger = LoggerFactory.getLogger(AccountsController.class);
    private static final String MENSAJERESPUESTA = "restyp";
    private static final String MENSAJE = "mes";

    @PostMapping
    public ResponseEntity<BaseResponseVo> register(@Valid @RequestBody AccountsDTO accountsDTO) throws Exception{
        HttpHeaders responseHeaders = new HttpHeaders();
        BaseResponseVo errorResponse = new BaseResponseVo();
        String respGenRegister= "Se realiz\u00f3 el registro exitosamente.";
        try{
            accountsService.register(accountsDTO);
            responseHeaders.set(MENSAJERESPUESTA, ResponseType.SUCCESS.toString());
            responseHeaders.set(MENSAJE, respGenRegister);

            errorResponse.setMessage(respGenRegister);
            errorResponse.setCode(HttpStatus.CREATED.value());
            return new ResponseEntity<>(errorResponse, responseHeaders, HttpStatus.CREATED);
        }catch (Exception e){
            responseHeaders.set(MENSAJERESPUESTA, ResponseType.ERROR.toString());
            responseHeaders.set(MENSAJE, "Error al realizar el registro.");
            logger.error("Error al realizar el registro: {} ", e);

            errorResponse.setErrors(Collections.singletonList(e.getMessage()));
            errorResponse.setCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(errorResponse,responseHeaders, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<BaseResponseVo> modify(@Valid @RequestBody AccountsDTO accountsDTO) throws Exception{
        HttpHeaders responseHeaders = new HttpHeaders();
        BaseResponseVo errorResponse = new BaseResponseVo();
        String respGenModify = "Se realiz\u00f3 la actualizaci\u00f3n exitosamente.";

        try {
            accountsService.modify(accountsDTO);
            responseHeaders.set(MENSAJERESPUESTA, ResponseType.SUCCESS.toString());
            responseHeaders.set(MENSAJE, respGenModify);

            errorResponse.setMessage(respGenModify);
            errorResponse.setCode(HttpStatus.OK.value());
            return new ResponseEntity<>(errorResponse, responseHeaders, HttpStatus.OK);
        }catch (Exception e){
            responseHeaders.set(MENSAJERESPUESTA, ResponseType.ERROR.toString());
            responseHeaders.set(MENSAJE, "Error al actualizar registro.");
            logger.error("Error al actualizar registro: {} ", e);

            errorResponse.setErrors(Collections.singletonList(e.getMessage()));
            errorResponse.setCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(errorResponse,responseHeaders, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<BaseResponseVo> findAll() throws Exception{
        HttpHeaders responseHeaders = new HttpHeaders();
        BaseResponseVo errorResponse = new BaseResponseVo();
        List<Accounts> result = new ArrayList<>();
        String respCnsNotData = "No se encontr\u00f3n registros para consultar.";
        String respCnsExt = "Se realiz\u00f3 la consulta exitosamente.";

        try{
            result = accountsService.list();

            if (result != null && result.size() > 0) {
                responseHeaders.set(MENSAJERESPUESTA, ResponseType.SUCCESS.toString());
                responseHeaders.set(MENSAJE, respCnsExt);

                errorResponse.setMessage(respCnsExt);
                errorResponse.setCode(HttpStatus.OK.value());
                errorResponse.setData(result);
                return new ResponseEntity<>(errorResponse, responseHeaders, HttpStatus.OK);
            } else {
                responseHeaders.set(MENSAJERESPUESTA, ResponseType.INFO.toString());
                responseHeaders.set(MENSAJE, respCnsNotData);

                errorResponse.setMessage(respCnsNotData);
                errorResponse.setCode(HttpStatus.OK.value());
                errorResponse.setData(result);
                return new ResponseEntity<>(errorResponse, responseHeaders, HttpStatus.OK);
            }
        }catch (Exception e){
            responseHeaders.set(MENSAJERESPUESTA, ResponseType.ERROR.toString());
            responseHeaders.set(MENSAJE, "Error al consultar los registros.");
            logger.error("Error al consultar los registros: {} ", e);

            errorResponse.setErrors(Collections.singletonList(e.getMessage()));
            errorResponse.setCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(errorResponse,responseHeaders, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseVo> findById(@PathVariable("id") Integer id) throws Exception{
        HttpHeaders responseHeaders = new HttpHeaders();
        BaseResponseVo errorResponse = new BaseResponseVo();
        AccountsDTO result = null;
        String respGenNotExstID = "El ID de la cuenta a consultar no existe";
        String respGenCns = "Se realiz\u00f3 la consulta exitosamente.";
        try{
            result = accountsService.listById(id);

            if(result == null) {
                responseHeaders.set(MENSAJERESPUESTA, ResponseType.INFO.toString());
                responseHeaders.set(MENSAJE, respGenNotExstID);

                errorResponse.setMessage(respGenNotExstID);
                errorResponse.setCode(HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(errorResponse, responseHeaders, HttpStatus.NOT_FOUND);
            }else{
                responseHeaders.set(MENSAJERESPUESTA, ResponseType.SUCCESS.toString());
                responseHeaders.set(MENSAJE, respGenCns);

                errorResponse.setMessage(respGenCns);
                errorResponse.setCode(HttpStatus.OK.value());
                errorResponse.setData(result);
                return new ResponseEntity<>(errorResponse, responseHeaders, HttpStatus.OK);
            }
        }catch (Exception e){
            responseHeaders.set(MENSAJERESPUESTA, ResponseType.ERROR.toString());
            responseHeaders.set(MENSAJE, "Error al consultar el registro.");
            logger.error("Error al consultar el registro: {} ", e);

            errorResponse.setErrors(Collections.singletonList(e.getMessage()));
            errorResponse.setCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(errorResponse,responseHeaders, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseVo> eliminate(@PathVariable("id") Integer id) throws Exception{
        HttpHeaders responseHeaders = new HttpHeaders();
        BaseResponseVo errorResponse = new BaseResponseVo();
        String respGenDelete = "Se realiz\u00f3 la eliminaci\u00f3n exitosamente.";
        try{
            accountsService.eliminate(id);
            responseHeaders.set(MENSAJERESPUESTA, ResponseType.SUCCESS.toString());
            responseHeaders.set(MENSAJE, respGenDelete);

            errorResponse.setMessage(respGenDelete);
            errorResponse.setCode(HttpStatus.OK.value());
            return new ResponseEntity<>(errorResponse, responseHeaders, HttpStatus.OK);
        }catch (Exception e){
            responseHeaders.set(MENSAJERESPUESTA, ResponseType.ERROR.toString());
            responseHeaders.set(MENSAJE, "Error al eliminar el registro.");
            logger.error("Error al eliminar el registro: {} ", e);

            errorResponse.setErrors(Collections.singletonList(e.getMessage()));
            errorResponse.setCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(errorResponse,responseHeaders, HttpStatus.BAD_REQUEST);
        }
    }
}
