package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.reponse.BuildingSearchReponse;
import com.laptrinhjavaweb.dto.request.BuildingCreateDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequestDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;

    public BuildingSearchReponse convertEntityToBuildingSearchReponse(BuildingEntity entity) {
        BuildingSearchReponse result = modelMapper.map(entity, BuildingSearchReponse.class);
        result.setAddress(convertAddress(entity));
        return result;
    }


    public BuildingDTO convertEntityToBuildingDTO(BuildingEntity entity) {
        BuildingDTO result = modelMapper.map(entity, BuildingDTO.class);
        convertBuildingType(entity, result);
        result.setRentAreas(entity.getRentAreas().stream().map(item -> item.getValue().toString()).collect(Collectors.joining(",")));
        result.setAddress(convertAddress(entity));
        return result;
    }



    public BuildingDTO convertToDto(BuildingEntity entity){
        BuildingDTO result = modelMapper.map(entity, BuildingDTO.class);
        return result;
    }

    public BuildingEntity convertToEntity (BuildingCreateDTO req){
        BuildingEntity result = modelMapper.map(req, BuildingEntity.class);
        if(req.getType() != null){
            result.setType(String.join(",", req.getType()));
        }
        return result;
    }

    public BuildingSearchBuilder convertToBuilder (BuildingSearchRequestDTO req){
        BuildingSearchBuilder result = modelMapper.map(req, BuildingSearchBuilder.class);
        return result;
    }

    private void convertBuildingType(BuildingEntity entity, BuildingDTO buildingDTO) {
        if (entity.getType() != null){
            buildingDTO.setType(Arrays.asList(entity.getType().split(",")));
        }
    }

    private String convertAddress(BuildingEntity entity) {
        List<String> address = new ArrayList<>();
        if (entity.getStreet() != null){
            address.add(entity.getStreet());
        }
        if (entity.getWard() != null){
            address.add(entity.getWard());
        }
        if (entity.getDistrict() != null){
            address.add(DistrictsEnum.getCdValue(entity.getDistrict()));
        }
        return address.stream().collect(Collectors.joining(", "));
    }


}
