package com.han.ls.project.controller;

import com.han.ls.framework.web.domain.R;
import com.han.ls.project.domain.City;
import com.han.ls.project.domain.County;
import com.han.ls.project.domain.Province;
import com.han.ls.project.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
@Slf4j
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/allProvince")
    public R<List<Province>> allProvince() {
        return R.success(addressService.allProvince());
    }

    @GetMapping("/selectCityByProvinceId")
    public R<List<City>> selectCityByProvinceId(int id) {
        return R.success(addressService.selectCityByProvinceId(id));
    }

    @GetMapping("/selectCityByProvinceName")
    public R<List<City>> selectCityByProvinceName(String name) {
        return R.success(addressService.selectCityByProvinceName(name));
    }

    @GetMapping("/selectCountyByCityId")
    public R<List<County>> selectCountyByCityId(int id) {
        return R.success(addressService.selectCountByCityId(id));
    }

    @GetMapping("/selectCountyByCityName")
    public  R<List<County>> selectCountyByCityName(String name) {
        return R.success(addressService.selectCountyByCityName(name));
    }
}
