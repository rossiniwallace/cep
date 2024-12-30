package com.service.cep.service;

import com.service.cep.domian.ExternalQueryLog;
import com.service.cep.repository.ExternalQueryLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExternalQueryLogService {

    @Autowired
    private ExternalQueryLogRepository repository;

    @Transactional
    public void createLog(ExternalQueryLog log){
        repository.save(log);
    }
}
