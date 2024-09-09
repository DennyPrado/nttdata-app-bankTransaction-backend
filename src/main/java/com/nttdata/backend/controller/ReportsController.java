package com.nttdata.backend.controller;

import com.nttdata.backend.dto.AccountStatusReportDTO;
import com.nttdata.backend.dto.BaseResponseVo;
import com.nttdata.backend.model.Movements;
import com.nttdata.backend.service.IReportsService;
import com.nttdata.backend.util.ResponseType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/reportes")
@RequiredArgsConstructor
public class ReportsController {

    private final IReportsService reportsService;

    private static final Logger logger = LoggerFactory.getLogger(ReportsController.class);
    private static final String MENSAJERESPUESTA = "restyp";
    private static final String MENSAJE = "mes";

    @GetMapping
    public ResponseEntity<BaseResponseVo> movementReportByUserAndDate(@RequestParam(value = "startDate") String startDate,
                                                                      @RequestParam(value = "endDate") String endDate,
                                                                      @RequestParam(value = "clientId") String clientId)
    throws Exception {
        HttpHeaders responseHeaders = new HttpHeaders();
        BaseResponseVo errorResponse = new BaseResponseVo();
        List<AccountStatusReportDTO> result = new ArrayList<>();
        String respGenRep = "Se gener\u00f3 el reporte exitosamente.";
        String respGenRepNotData = "No se encontrar\u00f3n registros para consultar.";

        try{
            result = reportsService.movementReportByUserAndDate(LocalDateTime.parse(startDate), LocalDateTime.parse(endDate), clientId);

            if (result != null && result.size() > 0) {
                responseHeaders.set(MENSAJERESPUESTA, ResponseType.SUCCESS.toString());
                responseHeaders.set(MENSAJE, respGenRep);

                errorResponse.setMessage(respGenRep);
                errorResponse.setCode(HttpStatus.OK.value());
                errorResponse.setData(result);
                return new ResponseEntity<>(errorResponse, responseHeaders, HttpStatus.OK);
            } else {
                responseHeaders.set(MENSAJERESPUESTA, ResponseType.INFO.toString());
                responseHeaders.set(MENSAJE, respGenRepNotData);

                errorResponse.setMessage(respGenRepNotData);
                errorResponse.setCode(HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(errorResponse, responseHeaders, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            responseHeaders.set(MENSAJERESPUESTA, ResponseType.ERROR.toString());
            responseHeaders.set(MENSAJE, "Error al consultar el reporte.");
            logger.error("Error al consultar el reporte: {} ", e);

            errorResponse.setErrors(Collections.singletonList(e.getMessage()));
            errorResponse.setCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(errorResponse,responseHeaders, HttpStatus.BAD_REQUEST);
        }
    }

}
