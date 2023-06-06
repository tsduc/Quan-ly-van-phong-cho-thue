package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.request.AssignmentBuildingCreateDTO;
import com.laptrinhjavaweb.dto.request.AssignmentCustomerCreateDTO;

public interface IAssignmentCustomerService {
    void  insert(AssignmentCustomerCreateDTO req);
}
