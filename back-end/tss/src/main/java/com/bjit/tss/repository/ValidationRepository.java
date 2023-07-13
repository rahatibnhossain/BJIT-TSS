package com.bjit.tss.repository;

import com.bjit.tss.entity.ValidationCodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ValidationRepository extends JpaRepository<ValidationCodes, Long> {

}

