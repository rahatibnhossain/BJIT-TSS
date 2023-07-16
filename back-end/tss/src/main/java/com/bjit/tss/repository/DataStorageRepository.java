package com.bjit.tss.repository;

import com.bjit.tss.entity.DataStorage;
import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.DataStorageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DataStorageRepository extends JpaRepository<DataStorage, Long> {

    Optional<DataStorage> findByDataKey(String dataKey);

}