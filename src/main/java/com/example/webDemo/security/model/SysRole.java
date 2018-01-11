package com.example.webDemo.security.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Administrator on 2018/1/11.
 */
@Entity
@Data
public class SysRole {

    @Id
    @GeneratedValue
    private long id;

    private String name;

}
