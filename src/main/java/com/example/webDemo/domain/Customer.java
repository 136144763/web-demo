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
    private int userId;

    private String idCard;

    private String name;

    private String sex;

    private String adress;

    private String phoneNum;

    private String account;

    private String password;

    private String email;
}
