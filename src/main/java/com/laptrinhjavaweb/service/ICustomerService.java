package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.RentAreaDTO;
import com.laptrinhjavaweb.dto.request.BuildingCreateDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequestDTO;
import com.laptrinhjavaweb.dto.request.CustomerCreateDTO;
import com.laptrinhjavaweb.dto.request.CustomerSearchRequestDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ICustomerService {

    void  save(CustomerCreateDTO req, Long id);

    void delete(Long[] ids);

    List<CustomerDTO> findCustomerPage(Pageable pageable, CustomerSearchRequestDTO customerSearchRequestDTO);

    int countTotalItems(CustomerSearchRequestDTO customerSearchRequestDTO);

    CustomerDTO findById(Long id);
}
