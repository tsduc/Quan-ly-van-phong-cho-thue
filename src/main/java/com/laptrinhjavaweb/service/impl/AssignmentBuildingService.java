package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.dto.request.AssignmentBuildingCreateDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.exception.MyException;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IAssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentBuildingService implements IAssignmentBuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void insert(AssignmentBuildingCreateDTO req) {
        BuildingEntity buildingEntity =  buildingRepository.findById(req.getBuildingId())
                .orElseThrow(() -> new MyException("building not found"));
        List<UserEntity> userEntities = userRepository.findByIdIn(req.getStaffs());
        buildingEntity.setStaffs(userEntities);
        buildingRepository.save(buildingEntity);

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
