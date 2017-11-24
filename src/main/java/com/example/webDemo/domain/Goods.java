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

    public String supplierName;//供货商


    public String name;//商品名称

    public String price;//价格

    public String salesPrice;//销售价

    public String settlementPrice;//结算价

    public String goodsIntro;//商品介绍


}
