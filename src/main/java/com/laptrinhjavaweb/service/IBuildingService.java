package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.RentAreaDTO;
import com.laptrinhjavaweb.dto.reponse.BuildingSearchReponse;
import com.laptrinhjavaweb.dto.request.BuildingCreateDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequestDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IBuildingService {

    Map<String, String> getDistrticts();

    Map<String, String> getBuildingTypes();

    void  save(BuildingCreateDTO req, Long id);

    void delete(Long[] ids);

    List<BuildingDTO> findBuildingPage(Pageable pageable, BuildingSearchRequestDTO buildingSearchRequest);

    int countTotalItems(BuildingSearchRequestDTO buildingSearchRequest);

    BuildingDTO findById(Long id);
}
