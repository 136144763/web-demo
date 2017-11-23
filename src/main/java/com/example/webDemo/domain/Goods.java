package com.example.webDemo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Administrator on 2017/11/23.
 */
@Data
@Entity
@Table(name = "t_goods")
public class Goods {

    @Id
    public Long id; // 商品ID

    public String supplierName;


    public String name;

    public String price;

    public String salesPrice;

    public String settlementPrice;

    public String goodsIntro;


}
