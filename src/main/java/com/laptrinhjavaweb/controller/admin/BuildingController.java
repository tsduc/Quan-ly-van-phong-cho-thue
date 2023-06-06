package com.laptrinhjavaweb.controller.admin;


import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.dto.reponse.BuildingSearchReponse;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequestDTO;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller(value = "buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private IBuildingService buildingService;
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/admin/building-list", method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute("modelSearch") BuildingSearchRequestDTO buildingSearchRequest, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("modelSearch", buildingSearchRequest);
        DisplayTagUtils.of(request, buildingSearchRequest);
        if (SecurityUtils.getPrincipal().getAuthorities().toString().equals("[ROLE_STAFF]")){
            buildingSearchRequest.setStaffId(SecurityUtils.getPrincipal().getId());
        }
        List<BuildingDTO> buildingDTOS = buildingService.findBuildingPage(PageRequest.of(buildingSearchRequest.getPage() - 1, buildingSearchRequest.getMaxPageItems()), buildingSearchRequest);
        buildingSearchRequest.setListResult(buildingDTOS);
        buildingSearchRequest.setTotalItems(buildingService.countTotalItems(buildingSearchRequest));
        mav.addObject("buildings", buildingSearchRequest);
        mav.addObject("staffmaps", userService.getStaffMaps());
        mav.addObject("districts", buildingService.getDistrticts());
        mav.addObject("buildingTypes", buildingService.getBuildingTypes());
        return mav;
    }

    @RequestMapping(value = "/admin/building-update", method = RequestMethod.GET)
    public ModelAndView buildingUpdate(@ModelAttribute("buildings") BuildingDTO buildingDTO) {
        ModelAndView mav = new ModelAndView("admin/building/update");
        mav.addObject("buildingModel", buildingDTO);

        mav.addObject("districts", buildingService.getDistrticts());

        mav.addObject("buildingTypes", buildingService.getBuildingTypes());

        return mav;
    }

    @RequestMapping(value = "/admin/building-update-{id}", method = RequestMethod.GET)
    public ModelAndView buildingUpdate(@ModelAttribute("buildings") BuildingDTO buildingDTO, @PathVariable(value = "id") Long id) {
        ModelAndView mav = new ModelAndView("admin/building/update");
        mav.addObject("buildingModel", buildingDTO);

        mav.addObject("buildings", buildingService.findById(id));

        mav.addObject("districts", buildingService.getDistrticts());

        mav.addObject("buildingTypes", buildingService.getBuildingTypes());

        return mav;
    }
}
