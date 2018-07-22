package com.example.webDemo.his.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dongqi
 */
@Setter
@Getter
@XStreamAlias("Request")
public class DHCNetTestReq extends HisReqBase {

    public DHCNetTestReq() {
        this.TradeCode = "1000";
    }
}
