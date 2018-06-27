package com.example.webDemo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author luofei on 2018/6/27.
 */
@Data
@Entity
@Table(name = "sys_customer")
public class SysCustomer {

    @Id
    public int id;

    public String password;

    public String username;

    public String adress;

    public String email;

    public String idCard;

    public String name;

    public String phoneNum;

    public String sex;
}
