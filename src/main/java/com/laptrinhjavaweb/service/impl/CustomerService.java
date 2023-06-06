package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.converter.RentAreaConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.RentAreaDTO;
import com.laptrinhjavaweb.dto.request.BuildingCreateDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequestDTO;
import com.laptrinhjavaweb.dto.request.CustomerCreateDTO;
import com.laptrinhjavaweb.dto.request.CustomerSearchRequestDTO;
import com.laptrinhjavaweb.entity.*;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.exception.MyException;
import com.laptrinhjavaweb.repository.*;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerConverter customerConverter;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private RentAreaConverter rentAreaConverter;
    @Autowired
    private UploadFileUtils uploadFileUtils;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    @Transactional
    public void save(CustomerCreateDTO req, Long id) {
        CustomerEntity customerEntity = customerConverter.convertToEntity(req);

        if (id != null){
            customerEntity.setId(id);

            List<UserEntity> userEntities = userRepository.findByCustomers_Id(id);
            if (userEntities != null){
                customerEntity.setCustomerStaffs(userEntities);
            }
        }

        List<TransactionEntity> transactionEntities = transactionRepository.findByCustomer_Id(id);
        if (transactionEntities != null){
            customerEntity.setTransaction(transactionEntities);
        }

        customerRepository.save(customerEntity);
    }


    @Override
    @Transactional
    public void delete(Long[] ids) {
        if (ids.length > 0) {
            Long count = customerRepository.countByIdIn(ids);
            if (count != ids.length){
                throw new NotFoundException(SystemConstant.CUSTOMER_NOT_FOUND);
            }
            customerRepository.deleteByIdIn(ids);
        }
    }

    @Override
    public List<CustomerDTO> findCustomerPage(Pageable pageable, CustomerSearchRequestDTO customerSearchRequestDTO) {
        CustomerSearchBuilder customerSearchBuilder = convertToBuilder(customerSearchRequestDTO);
        List<CustomerEntity> customerEntities = customerRepository.getAllCustomer(pageable, customerSearchBuilder);
        List<CustomerDTO> results = new ArrayList<>();
        for (CustomerEntity customerEntity: customerEntities){
            CustomerDTO customerDTO = customerConverter.convertEntityToBuildingDTO(customerEntity);
            List<UserEntity> userEntities = customerEntity.getCustomerStaffs();
            List<String> staffs = new ArrayList<>();
            for (UserEntity item: userEntities){
                staffs.add(item.getFullName());
            }
            customerDTO.setStaffName(staffs.stream().collect(Collectors.joining(", ")));
            if (customerEntity.getTransaction().size() > 0){
                customerDTO.setStatus(SystemConstant.DANG_XU_LY);
            }else {
                customerDTO.setStatus(SystemConstant.CHUA_XU_LY);
            }
            results.add(customerDTO);
        }
        return results;
    }

    @Override
    public int countTotalItems(CustomerSearchRequestDTO customerSearchRequestDTO) {
        CustomerSearchBuilder customerSearchBuilder = convertToBuilder(customerSearchRequestDTO);
        return customerRepository.countTotalItem(customerSearchBuilder);
    }

    private CustomerSearchBuilder convertToBuilder(CustomerSearchRequestDTO customerSearchRequestDTO) {
        CustomerSearchBuilder result = new CustomerSearchBuilder.Builder()
                .setFullname(customerSearchRequestDTO.getFullname())
                .setPhone(customerSearchRequestDTO.getPhone())
                .setEmail(customerSearchRequestDTO.getEmail())
                .setStaffId(customerSearchRequestDTO.getStaffId())
                .build();
        return result;
    }

    @Override
    public CustomerDTO findById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(() -> new MyException("customer not found"));
        CustomerDTO result = customerConverter.convertEntityToBuildingDTO(customerEntity);
        return result;
    }

}
