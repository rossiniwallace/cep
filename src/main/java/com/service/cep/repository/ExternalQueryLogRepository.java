package com.service.cep.repository;

import com.service.cep.domian.ExternalQueryLog;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface ExternalQueryLogRepository extends JpaRepository<ExternalQueryLog, Long> {
}
