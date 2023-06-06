package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.dto.request.AssignmentBuildingCreateDTO;
import com.laptrinhjavaweb.dto.request.AssignmentCustomerCreateDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.exception.MyException;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IAssignmentBuildingService;
import com.laptrinhjavaweb.service.IAssignmentCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AssignmentCustomerService implements IAssignmentCustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void insert(AssignmentCustomerCreateDTO req) {
        CustomerEntity customerEntity =  customerRepository.findById(req.getCustomerId())
                .orElseThrow(() -> new MyException("customer not found"));
        List<UserEntity> userEntities = userRepository.findByIdIn(req.getStaffs());
        customerEntity.setCustomerStaffs(userEntities);
        customerRepository.save(customerEntity);

//        UserEntity userEntity = userRepository.findById(req.ge)

//        List<Long> newStaffIds = req.getStaffs();
//        List<Long> oldStaffIds = userRepository.findByAssignmentbuilding_Building_Id(req.getBuildingId()).stream().map(UserEntity::getId).collect(Collectors.toList());
//        List<UserEntity> userEntities = new ArrayList<>();
//        for (Long newStaffId: newStaffIds){
//            if (!oldStaffIds.contains(newStaffId)){
//                UserEntity userEntitie = userRepository.findById(newStaffId).orElse(null);
//                userEntities
//            }
//        }
//
//        buildingEntity.setStaffs(userEntities);
//        buildingRepository.save(buildingEntity);

    }
}
