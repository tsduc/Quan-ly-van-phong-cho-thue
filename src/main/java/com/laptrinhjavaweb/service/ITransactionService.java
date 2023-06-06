package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.request.BuildingCreateDTO;

import java.util.List;
import java.util.Map;

public interface ITransactionService {
    Map<String, String> getTransactionCode();

    List<TransactionDTO> findByCustomer(Long customerId);

    void  save(TransactionDTO req, Long customerId);
}
