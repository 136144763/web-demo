package com.example.webDemo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author luofei on 2018/6/20.
 */
@Data
@Entity
@Table(name = "t_loan")
public class Loan {

    @Id
    public Long id;

    public String CompanyName;

    public String name;

    public String mobile;

    public String verificationCode;

    public String loanAmount;

    public String loanTimeLimit;

    public String demandDescribe;

    public String advantage;

}
