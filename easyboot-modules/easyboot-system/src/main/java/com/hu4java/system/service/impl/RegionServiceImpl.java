package com.hu4java.system.service.impl;

import com.hu4java.base.service.impl.AbstractServiceImpl;
import com.hu4java.system.entity.Region;
import com.hu4java.system.mapper.RegionMapper;
import com.hu4java.system.service.RegionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 区域表
 * @author	EasyBoot
 * @date	2020-09-17 11:20:38
 */
@Service
public class RegionServiceImpl extends AbstractServiceImpl<Region, RegionMapper> implements RegionService {

    @Override
    public List<Region> listByPid(Long pid) {
        return mapper.selectByPid(pid);
    }

    @Override
    public List<Region> listByTree() {
        List<Region> provinceList = mapper.selectByLevel(1);
        List<Region> cityList = mapper.selectByLevel(2);
        List<Region> districtList = mapper.selectByLevel(3);

        for (Region province : provinceList) {
            List<Region> provinceCityList = cityList.stream().filter(city -> province.getId().equals(city.getPid()))
                    .collect(Collectors.toList());
            cityList.removeAll(provinceCityList);
            province.setChildren(provinceCityList);
            for (Region city : provinceCityList) {
                List<Region> cityDistrictList = districtList.stream().filter(district -> city.getId().equals(district.getPid()))
                        .collect(Collectors.toList());
                districtList.removeAll(cityDistrictList);
                city.setChildren(cityDistrictList);
            }
        }

        return provinceList;
    }
}