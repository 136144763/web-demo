package com.example.webDemo.his.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dongqi
 */
@Setter
@Getter
@XStreamAlias("Response")
public class DHCNetTestResp extends HisRespBase {

    private String ResultMsg;
}
