package com.han.ls.project.mapper;

import com.han.ls.project.domain.Address;
import com.han.ls.project.domain.City;
import com.han.ls.project.domain.County;
import com.han.ls.project.domain.Province;

import java.util.List;

public interface AddressMapper {

    void insertAll(List<Address> list);

    List<Address> all();

    void insertAllProvince(List<Province> provinceList);

    List<Province> allProvince();

    void insertAllCity(List<City> allCity);

    List<City> allCity();

    void insertAllCounty(List<County> allCounty);

    List<City> selectCityByProvinceId(int id);

    List<County> selectCountByCityId(int id);

    County selectCountyById(int countyId);
}
