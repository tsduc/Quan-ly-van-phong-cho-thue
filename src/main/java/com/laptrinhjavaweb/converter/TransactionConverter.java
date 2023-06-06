package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.reponse.CustomerSearchReponse;
import com.laptrinhjavaweb.dto.request.CustomerCreateDTO;
import com.laptrinhjavaweb.dto.request.CustomerSearchRequestDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.TransactionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TransactionConverter {

    @Autowired
    private ModelMapper modelMapper;

    public TransactionDTO convertEntityToBuildingDTO(TransactionEntity entity) {
        TransactionDTO result = modelMapper.map(entity, TransactionDTO.class);
        return result;
    }

    public TransactionEntity convertToEntity (TransactionDTO req){
        TransactionEntity result = modelMapper.map(req, TransactionEntity.class);
        return result;
    }
}
