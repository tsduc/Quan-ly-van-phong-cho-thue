package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByIdIn(List<Long> ids);

    List<TransactionEntity> findByCustomer_Id(Long customerId);
}
