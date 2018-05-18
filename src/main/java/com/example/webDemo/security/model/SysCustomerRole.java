package com.example.webDemo.security.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author luofei on 2018/5/18.
 */
@Entity
@Data
public class SysCustomerRole {

    @Id
    @GeneratedValue
    private long id;

    private String name;

}
