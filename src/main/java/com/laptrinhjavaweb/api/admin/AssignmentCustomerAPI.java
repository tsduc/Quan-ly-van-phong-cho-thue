package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.reponse.ResponseDTO;
import com.laptrinhjavaweb.dto.request.AssignmentBuildingCreateDTO;
import com.laptrinhjavaweb.dto.request.AssignmentCustomerCreateDTO;
import com.laptrinhjavaweb.service.IAssignmentBuildingService;
import com.laptrinhjavaweb.service.IAssignmentCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assignmentcustomer")
public class AssignmentCustomerAPI {
    @Autowired
    private IAssignmentCustomerService assignmentCustomerService;

    @PostMapping
    public ResponseDTO createAssignmentCustomer(@RequestBody AssignmentCustomerCreateDTO req){
        assignmentCustomerService.insert(req);
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        return  result;
    }
}
