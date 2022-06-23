package com.han.ls.project.mapper;

import com.han.ls.project.domain.Bid;

import java.util.ArrayList;
import java.util.List;

public interface BidMapper {

    void insertAll(List<Bid> bids);

    List<Bid> selectLikeType(ArrayList<String> types);
}
