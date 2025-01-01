package com.service.cep.controller;

import com.service.cep.dto.delivery.DeliveryDetailDTO;
import com.service.cep.dto.delivery.DeliveryFilter;
import com.service.cep.dto.logs.ExternalQueryLogDetailDTO;
import com.service.cep.service.ExternalQueryLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logs")
public class ExternalQueryLogController {

    @Autowired
    private ExternalQueryLogService externalQueryLogService;
    //TODO endpoint -> listar logs

    @GetMapping
    public ResponseEntity<Page<ExternalQueryLogDetailDTO>> findPageable(
            DeliveryFilter filter,
            @PageableDefault(page = 0, size = 10) Pageable pageable){
        return externalQueryLogService.findPageable(pageable, filter);
    }
}
