package com.han.ls.project.mapper;

import com.han.ls.project.domain.Address;

import java.util.List;

public interface AddressMapper {

    void insertAll(List<Address> list);

    List<Address> all();
}
