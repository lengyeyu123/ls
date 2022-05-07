package com.han.ls;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.han.ls.project.domain.Address;
import com.han.ls.project.service.AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class LsTests {

    @Autowired
    private AddressService addressService;

    @Test
    public void insertAddressAll() throws IOException {
        String str = Files.asCharSource(new File("D:\\workspace\\ls\\src\\main\\resources\\address.txt"), Charsets.UTF_8).read();
        List<Address> list = new ArrayList<>();
        String[] split = str.split("\r\n");
        ArrayList<String> strList = new ArrayList<>(Arrays.asList(split));
        for (String subStr : strList) {
            subStr = subStr.replace("\t\t\t\t\t\t", "");
            String[] split1 = subStr.split("\t");
            ArrayList<String> stringArrayList = new ArrayList<>(Arrays.asList(split1));
            System.out.println(stringArrayList);
            Address address = new Address().setCode(stringArrayList.get(0).trim()).setName(stringArrayList.get(1).trim());
            list.add(address);
        }
        // System.out.println(list);
        addressService.insertAll(list);
    }


}
