package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.reponse.BuildingSearchReponse;
import com.laptrinhjavaweb.dto.reponse.CustomerSearchReponse;
import com.laptrinhjavaweb.dto.request.BuildingCreateDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequestDTO;
import com.laptrinhjavaweb.dto.request.CustomerCreateDTO;
import com.laptrinhjavaweb.dto.request.CustomerSearchRequestDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;


@Component
public class CustomerConverter {

    @Autowired
    private ModelMapper modelMapper;

    public CustomerSearchReponse convertEntityToBuildingSearchReponse(CustomerEntity entity) {
        CustomerSearchReponse result = modelMapper.map(entity, CustomerSearchReponse.class);
        return result;
    }

    public CustomerDTO convertEntityToBuildingDTO(CustomerEntity entity) {
        CustomerDTO result = modelMapper.map(entity, CustomerDTO.class);
        return result;
    }

    public CustomerEntity convertToEntity (CustomerCreateDTO req){
        CustomerEntity result = modelMapper.map(req, CustomerEntity.class);
        return result;
    }

    public CustomerSearchBuilder convertToBuilder (CustomerSearchRequestDTO req){
        CustomerSearchBuilder result = modelMapper.map(req, CustomerSearchBuilder.class);
        return result;
    }

}
