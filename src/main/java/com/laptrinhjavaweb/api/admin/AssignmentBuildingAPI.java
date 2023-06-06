package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.reponse.ResponseDTO;
import com.laptrinhjavaweb.dto.request.AssignmentBuildingCreateDTO;
import com.laptrinhjavaweb.service.IAssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assignmentbuilding")
public class AssignmentBuildingAPI {
    @Autowired
    private IAssignmentBuildingService assignmentBuildingService;

    @PostMapping
    public ResponseDTO createAssignmentBuilding(@RequestBody AssignmentBuildingCreateDTO req){
        assignmentBuildingService.insert(req);
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        return  result;
    }
}
