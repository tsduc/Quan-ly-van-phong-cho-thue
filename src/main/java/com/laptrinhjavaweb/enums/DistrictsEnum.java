package com.laptrinhjavaweb.enums;

import java.util.HashMap;
import java.util.Map;

public enum DistrictsEnum {
	
	Q1("Quận 1"),
    Q2("Quận 2"),
    Q3("Quận 3"),
    Q6("Quận 6");

    private final String districtValue;

    DistrictsEnum(String districtValue) {
        this.districtValue = districtValue;
    }

    private static final Map<String, String> districtMap = new HashMap<>();

    static {
        for (DistrictsEnum clsA : DistrictsEnum.values()) {
            districtMap.put(clsA.name(), clsA.getDistrictValue());
        }
    }

    public String getDistrictValue() {
		return districtValue;
	}

    public static String getCdValue(String cdKey){
        if (districtMap.containsKey(cdKey)){
            return districtMap.get(cdKey);
        }
        return null;
    }

}
