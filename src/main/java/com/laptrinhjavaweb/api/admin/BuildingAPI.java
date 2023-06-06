package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.reponse.ResponseDTO;
import com.laptrinhjavaweb.dto.reponse.StaffResponseDTO;
import com.laptrinhjavaweb.dto.request.BuildingCreateDTO;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {
    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseDTO createBuilding(@RequestBody BuildingCreateDTO req){
        buildingService.save(req, null);
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        return  result;
    }

    @PutMapping("/{id}")
    public ResponseDTO updateBuilding(@PathVariable ("id") Long id, @RequestBody BuildingCreateDTO req){
        buildingService.save(req, id);
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        return  result;
    }

    @DeleteMapping
    public ResponseDTO deleteBuilding(@RequestBody BuildingDTO buildingDTO){
        buildingService.delete(buildingDTO.getIds());
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        return  result;
    }

    @GetMapping("/{buildingId}/staffs")
    public ResponseDTO loadStaff(@PathVariable ("buildingId") Long id){
        List<StaffResponseDTO> staffs = userService.assignmentStaffs(id, "building");
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        result.setData(staffs);
        return  result;
    }
}
