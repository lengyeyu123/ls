package com.han.ls.project.service;

import com.han.ls.project.domain.Address;
import com.han.ls.project.domain.City;
import com.han.ls.project.domain.County;
import com.han.ls.project.domain.Province;
import com.han.ls.project.mapper.AddressMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AddressService {

    @Autowired
    private AddressMapper addressMapper;

    public void insertAll(List<Address> list) {
        addressMapper.insertAll(list);
    }

    public List<Address> all() {
        return addressMapper.all();
    }

    public void insertAllProvince(List<Province> provinceList) {
        addressMapper.insertAllProvince(provinceList);
    }

    public List<Province> allProvince() {
        return addressMapper.allProvince();
    }

    public void insertAllCity(List<City> allCity) {
        addressMapper.insertAllCity(allCity);
    }

    public List<City> allCity() {
        return addressMapper.allCity();
    }

    public void insertAllCounty(List<County> allCounty) {
        addressMapper.insertAllCounty(allCounty);
    }

    public List<City> selectCityByProvinceId(int id) {
        return addressMapper.selectCityByProvinceId(id);
    }

    public List<County> selectCountByCityId(int id) {
        return addressMapper.selectCountByCityId(id);
    }
}
