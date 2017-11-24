package com.example.webDemo.service;

import com.example.webDemo.domain.Goods;
import com.example.webDemo.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/11/24.
 */
@Service
public class GoodsService {

    @Autowired
    GoodsRepository goodsRepository;

    public List<Goods> findGoods(){
        List<Goods> lists=goodsRepository.findAll();
        return lists;
    }

    public Page<Goods> findPageGoods(PageRequest pageRequest){
        return goodsRepository.findAll(pageRequest);
    }
}
