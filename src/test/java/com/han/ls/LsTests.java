//package com.han.ls;
//
//import com.google.common.base.Charsets;
//import com.google.common.io.Files;
//import com.han.ls.common.utils.DateUtils;
//import com.han.ls.project.domain.*;
//import com.han.ls.project.service.AddressService;
//import com.han.ls.project.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//@SpringBootTest
//public class LsTests {
//
//    @Autowired
//    private AddressService addressService;
//
//    @Autowired
//    private UserService userService;
//
//    @Test
//    public void insertAddressAll() throws IOException {
//        String str = Files.asCharSource(new File("D:\\workspace\\ls\\src\\main\\resources\\address.txt"), Charsets.UTF_8).read();
//        List<Address> list = new ArrayList<>();
//        String[] split = str.split("\r\n");
//        ArrayList<String> strList = new ArrayList<>(Arrays.asList(split));
//        for (String subStr : strList) {
//            subStr = subStr.replace("\t\t\t\t\t\t", "");
//            String[] split1 = subStr.split("\t");
//            ArrayList<String> stringArrayList = new ArrayList<>(Arrays.asList(split1));
//            System.out.println(stringArrayList);
//            Address address = new Address().setCode(stringArrayList.get(0).trim()).setName(stringArrayList.get(1).trim());
//            list.add(address);
//        }
//        System.out.println(list);
//        addressService.insertAll(list);
//    }
//
//    @Test
//    public void insertAllProvince() {
//        List<Address> list = addressService.all();
//        // 所有的省份
//        List<Province> provinceList = new ArrayList<>();
//        for (Address address : list) {
//            String code = address.getCode();
//            String substring = code.substring(2, code.length());
//            if (substring.equals("0000")) {
//                provinceList.add(new Province().setCode(address.getCode()).setName(address.getName()));
//            }
//        }
//        addressService.insertAllProvince(provinceList);
//    }
//
//    @Test
//    public void insertAllCity() {
//        List<City> allCity = new ArrayList<>();
//
//        List<Address> addressList = addressService.all();
//        List<Province> allProvince = addressService.allProvince();
//        for (Province province : allProvince) {
//            String provinceCode = province.getCode();
//            String subPCode = provinceCode.substring(0, 2);
////            String subCCode = provinceCode.substring(2, 4);
////            String subCoCode = provinceCode.substring(4, 6);
//
//            List<City> cityList = new ArrayList<>();
//            for (Address address : addressList) {
//                String addressCode = address.getCode();
//                if (addressCode.substring(0, 2).equals(subPCode) && addressCode.substring(4, 6).equals("00") && !addressCode.substring(2, 4).equals("00")) {
//                    cityList.add(new City().setCode(addressCode).setName(address.getName()).setProvinceId(province.getId())
//                            .setProvinceCode(province.getCode()).setProvinceName(province.getName()));
//                }
//            }
//            allCity.addAll(cityList);
//        }
//        addressService.insertAllCity(allCity);
//    }
//
//    @Test
//    public void insertAllCounty() {
//        List<County> allCounty = new ArrayList<>();
//
//        List<Address> addressList = addressService.all();
//        List<City> allCity = addressService.allCity();
//        for (City city : allCity) {
//            String cityCode = city.getCode();
//            String substring = cityCode.substring(0, 4);
//
//            List<County> countyList = new ArrayList<>();
//            for (Address address : addressList) {
//                String addressCode = address.getCode();
//                if (addressCode.substring(0, 4).equals(substring) && !addressCode.substring(4, 6).equals("00")) {
//                    countyList.add(new County().setCode(address.getCode()).setName(address.getName())
//                            .setProvinceId(city.getProvinceId()).setProvinceCode(city.getProvinceCode()).setProvinceName(city.getProvinceName())
//                            .setCityId(city.getId()).setCityCode(city.getCode()).setCityName(city.getName()));
//                }
//            }
//            allCounty.addAll(countyList);
//        }
//
//        addressService.insertAllCounty(allCounty);
//    }
//
//    /**
//     * 插入两个用户
//     */
//    @Test
//    public void insertUser(){
//        List<User> list = new ArrayList<>();
//        Date now = new Date();
//        list.add(new User().setUserName("张三").setCreateTime(now));
//        list.add(new User().setUserName("李四").setCreateTime(now));
//        userService.insertUsers(list);
//    }
//
//
//}
