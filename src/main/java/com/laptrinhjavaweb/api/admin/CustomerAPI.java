package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.reponse.ResponseDTO;
import com.laptrinhjavaweb.dto.reponse.StaffResponseDTO;
import com.laptrinhjavaweb.dto.request.BuildingCreateDTO;
import com.laptrinhjavaweb.dto.request.CustomerCreateDTO;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.service.ITransactionService;
import com.laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "customerAPIOfAdmin")
@RequestMapping("/api/customer")
public class CustomerAPI {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ITransactionService transactionService;

    @PostMapping
    public ResponseDTO createCustomer(@RequestBody CustomerCreateDTO req){
        customerService.save(req, null);
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        return  result;
    }

    @PutMapping("/{id}")
    public ResponseDTO updateCustomer(@PathVariable ("id") Long id, @RequestBody CustomerCreateDTO req){
        customerService.save(req, id);
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        return  result;
    }

    @DeleteMapping
    public ResponseDTO deleteCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.delete(customerDTO.getIds());
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        return  result;
    }

    @GetMapping("/{customerId}/staffs")
    public ResponseDTO loadStaff(@PathVariable ("customerId") Long id){
        List<StaffResponseDTO> staffs = userService.assignmentStaffs(id, "customer");
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        result.setData(staffs);
        return  result;
    }

    @PostMapping("/{customerId}")
    public ResponseDTO createTransaction(@RequestBody TransactionDTO req, @PathVariable ("customerId") Long customerId){
        transactionService.save(req, customerId);
        ResponseDTO result = new ResponseDTO();
        result.setMessage("success");
        return  result;
    }
}
