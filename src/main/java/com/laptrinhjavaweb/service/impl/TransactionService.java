package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.TransactionConverter;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.enums.TransactionEnum;
import com.laptrinhjavaweb.exception.MyException;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.TransactionRepository;
import com.laptrinhjavaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionConverter transactionConverter;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Map<String, String> getTransactionCode() {
        Map<String, String> transactionCodes = new HashMap<>();
        for (TransactionEnum item: TransactionEnum.values()){
            transactionCodes.put(item.toString(), item.getTransactionValue());
        }
        return transactionCodes;
    }

    @Override
    public List<TransactionDTO> findByCustomer(Long customerId) {
        List<TransactionDTO> results = new ArrayList<>();
        List<TransactionEntity> transactionEntities = transactionRepository.findByCustomer_Id(customerId);
        if (transactionEntities != null){
            for (TransactionEntity transactionEntity: transactionEntities){
                TransactionDTO transactionDTO = transactionConverter.convertEntityToBuildingDTO(transactionEntity);
                results.add(transactionDTO);
            }
        }
        return results;
    }

    @Override
    @Transactional
    public void save(TransactionDTO req, Long customerId) {
        TransactionEntity transactionEntity = transactionConverter.convertToEntity(req);
        CustomerEntity customerEntity = customerRepository.findById(customerId).orElseThrow(() -> new MyException("CustomerId not found"));
        transactionEntity.setCustomer(customerEntity);
        transactionRepository.save(transactionEntity);
    }
}
