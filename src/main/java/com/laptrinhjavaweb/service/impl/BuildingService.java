package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.converter.RentAreaConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.RentAreaDTO;
import com.laptrinhjavaweb.dto.reponse.BuildingSearchReponse;
import com.laptrinhjavaweb.dto.request.BuildingCreateDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequestDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.exception.MyException;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BuildingService implements IBuildingService {

    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private BuildingConverter buildingConverter;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private RentAreaConverter rentAreaConverter;
    @Autowired
    private UploadFileUtils uploadFileUtils;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Map<String, String> getDistrticts() {
        Map<String, String> districts = new HashMap<>();
        for (DistrictsEnum item: DistrictsEnum.values()){
            districts.put(item.toString(), item.getDistrictValue());
        }
        return districts;
    }

    @Override
    public Map<String, String> getBuildingTypes() {
        Map<String, String> buildingTypes = new HashMap<>();
        for (BuildingTypesEnum item: BuildingTypesEnum.values()){
            buildingTypes.put(item.toString(), item.getBuildingTypeValue());
        }
        return buildingTypes;
    }

    @Override
    @Transactional
    public void save(BuildingCreateDTO req, Long id) {
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(req);

        if (id != null){
            buildingEntity.setId(id);

            List<UserEntity> userEntities = userRepository.findByBuildings_Id(id);
            if (userEntities != null){
                buildingEntity.setStaffs(userEntities);
            }
        }
        if(req.getAvatar() == null){
            saveThumbnail(req, buildingEntity);
        }

        if(req.getRentAreas() != null){
            List<RentAreaEntity> rentAreaEntities = new ArrayList<>();
            String rentAreaString = req.getRentAreas().replaceAll("\\s+", "");
            if (!rentAreaString.equals("")){
                String[] rentAreaStrings = rentAreaString.split(",");
                for (String value : rentAreaStrings) {
                    RentAreaEntity rentAreaEntity  = new RentAreaEntity();
                    rentAreaEntity.setValue(Integer.parseInt(value));
                    rentAreaEntity.setBuilding(buildingEntity);
                    rentAreaEntities.add(rentAreaEntity);
                }
            }
            buildingEntity.setRentAreas(rentAreaEntities);
        }
        buildingRepository.save(buildingEntity);
    }

    private void saveThumbnail(BuildingCreateDTO buildingCreateDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingCreateDTO.getImageName();
        if (null != buildingCreateDTO.getImageBase64()) {
            if (null != buildingEntity.getAvatar()) {
                if (!path.equals(buildingEntity.getAvatar())) {
                    File file = new File("C://home/office" + buildingEntity.getAvatar());
                    file.delete();
                }
            }
            byte[] bytes = Base64.decodeBase64(buildingCreateDTO.getImageBase64().getBytes());
            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setAvatar(path);
        }
    }

    @Override
    @Transactional
    public void delete(Long[] ids) {
        if (ids.length > 0) {
            Long count = buildingRepository.countByIdIn(ids);
            if (count != ids.length){
                throw new NotFoundException(SystemConstant.BUILDING_NOT_FOUND);
            }
            buildingRepository.deleteByIdIn(ids);
        }
    }

    @Override
    public List<BuildingDTO> findBuildingPage(Pageable pageable, BuildingSearchRequestDTO buildingSearchRequest) {
        BuildingSearchBuilder buildingSearchBuilder = convertToBuilder(buildingSearchRequest);
        List<BuildingEntity> buildingEntities = buildingRepository.getAllBuilding(pageable, buildingSearchBuilder);
        List<BuildingDTO> results = new ArrayList<>();
        for (BuildingEntity buildingEntity: buildingEntities){
            BuildingDTO buildingDTO = buildingConverter.convertEntityToBuildingDTO(buildingEntity);
            results.add(buildingDTO);
        }
        return results;
    }

    @Override
    public int countTotalItems(BuildingSearchRequestDTO buildingSearchRequest) {
        BuildingSearchBuilder buildingSearchBuilder = convertToBuilder(buildingSearchRequest);
        return buildingRepository.countTotalItem(buildingSearchBuilder);
    }

    private BuildingSearchBuilder convertToBuilder(BuildingSearchRequestDTO buildingSearchRequest) {
        BuildingSearchBuilder result = new BuildingSearchBuilder.Builder()
                .setName(buildingSearchRequest.getName())
                .setFloorArea(buildingSearchRequest.getFloorArea())
                .setDistrict(buildingSearchRequest.getDistrict())
                .setWard(buildingSearchRequest.getWard())
                .setStreet(buildingSearchRequest.getStreet())
                .setNumberOfBasement(buildingSearchRequest.getNumberOfBasement())
                .setDirection(buildingSearchRequest.getDirection())
                .setLevel(buildingSearchRequest.getLevel())
                .setRentAreaFrom(buildingSearchRequest.getRentAreaFrom())
                .setRentAreaTo(buildingSearchRequest.getRentAreaTo())
                .setRentPirceFrom(buildingSearchRequest.getRentPirceFrom())
                .setRentPirceTo(buildingSearchRequest.getRentPirceTo())
                .setManagerName(buildingSearchRequest.getManagerName())
                .setManagerPhone(buildingSearchRequest.getManagerPhone())
                .setStaffId(buildingSearchRequest.getStaffId())
                .setBuildingTypes(buildingSearchRequest.getBuildingTypes())
                .build();
        return result;
    }

    @Override
    public BuildingDTO findById(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).orElseThrow(() -> new MyException("building not found"));
        BuildingDTO result = buildingConverter.convertEntityToBuildingDTO(buildingEntity);
        return result;
    }

}
