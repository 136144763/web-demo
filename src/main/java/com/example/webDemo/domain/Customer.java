package com.example.webDemo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author luofei on 2018/5/14.
 */
@Data
@Entity
@Table(name = "t_customer")
public class Customer {

    @Id
    public Long userId;

    public String idCard;

    public String name;

    public String sex;

    public String adress;

    public String phoneNum;

}
