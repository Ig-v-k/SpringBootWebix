package com.project.webixs.logistic.repository;

import com.project.webixs.logistic.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status,Integer> {
}