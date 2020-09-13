package com.project.webixs.logistic.repository;

import com.project.webixs.logistic.model.Logger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggerRepository extends JpaRepository<Logger,Integer> {
}