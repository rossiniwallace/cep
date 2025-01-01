package com.service.cep.service;

import com.service.cep.domian.ExternalQueryLog;
import com.service.cep.dto.delivery.DeliveryDetailDTO;
import com.service.cep.dto.delivery.DeliveryFilter;
import com.service.cep.dto.logs.ExternalQueryLogDetailDTO;
import com.service.cep.mapper.ExternalQueryLogMapper;
import com.service.cep.repository.ExternalQueryLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExternalQueryLogService {

    @Autowired
    private ExternalQueryLogRepository repository;

    @Autowired
    private ExternalQueryLogMapper logMapper;

    @Transactional
    public void createLog(ExternalQueryLog log){
        repository.save(log);
    }


    public ResponseEntity<Page<ExternalQueryLogDetailDTO>> findPageable(Pageable pageable, DeliveryFilter filter) {

        Page<ExternalQueryLog> logs = repository.findAll(pageable);

        Page<ExternalQueryLogDetailDTO> map = logs.map(logMapper::toExternalQueryLogDTO);

        return ResponseEntity.ok(map);
    }
}
