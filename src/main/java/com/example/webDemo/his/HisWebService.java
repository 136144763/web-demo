package com.example.webDemo.his;

import com.example.webDemo.his.model.DHCNetTestReq;
import com.example.webDemo.his.model.DHCNetTestResp;

/**
 * @author luofei on 2018/6/22.
 */
public interface HisWebService {
    /**
     * 连接测试接口 1000
     *
     * @param req
     * @return
     */
    DHCNetTestResp dHCNetTest(DHCNetTestReq req);

}
