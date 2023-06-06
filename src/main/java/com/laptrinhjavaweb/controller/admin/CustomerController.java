package com.laptrinhjavaweb.controller.admin;


import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequestDTO;
import com.laptrinhjavaweb.dto.request.CustomerSearchRequestDTO;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.service.ITransactionService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.service.impl.CustomerService;
import com.laptrinhjavaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.soap.Addressing;
import java.security.Security;
import java.util.List;

@Controller(value = "customerControllerOfAdmin")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ITransactionService transactionService;

    @RequestMapping(value = "/admin/customer-list", method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute("modelSearch") CustomerSearchRequestDTO customerSearchRequestDTO, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/list");
        mav.addObject("modelSearch", customerSearchRequestDTO);
        DisplayTagUtils.of(request, customerSearchRequestDTO);
        if (SecurityUtils.getPrincipal().getAuthorities().toString().equals("[ROLE_STAFF]")){
            customerSearchRequestDTO.setStaffId(SecurityUtils.getPrincipal().getId());
        }
        List<CustomerDTO> customerDTOS = customerService.findCustomerPage(PageRequest.of(customerSearchRequestDTO.getPage() - 1, customerSearchRequestDTO.getMaxPageItems()), customerSearchRequestDTO);
        customerSearchRequestDTO.setListResult(customerDTOS);
        customerSearchRequestDTO.setTotalItems(customerService.countTotalItems(customerSearchRequestDTO));
        mav.addObject("customers", customerSearchRequestDTO);
        mav.addObject("staffmaps", userService.getStaffMaps());
        return mav;
    }

    @RequestMapping(value = "/admin/customer-update", method = RequestMethod.GET)
    public ModelAndView customerUpdate(@ModelAttribute("customers") CustomerDTO customerDTO) {
        ModelAndView mav = new ModelAndView("admin/customer/update");
        mav.addObject("customerModel", customerDTO);
        return mav;
    }

    @RequestMapping(value = "/admin/customer-update-{id}", method = RequestMethod.GET)
    public ModelAndView customerUpdate(@ModelAttribute("customers") CustomerDTO customerDTO, @PathVariable(value = "id") Long id) {
        ModelAndView mav = new ModelAndView("admin/customer/update");
        mav.addObject("customerModel", customerDTO);
        mav.addObject("customers", customerService.findById(id));
        mav.addObject("transaction", transactionService.findByCustomer(id));
        mav.addObject("transactionCodes", transactionService.getTransactionCode());

        return mav;
    }
}
