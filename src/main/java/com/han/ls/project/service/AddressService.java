package com.han.ls.project.service;

import com.han.ls.project.domain.Address;
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
}
