package com.example.webDemo.security.connfig;

import lombok.Data;

/**
 * Created by Administrator on 2018/1/11.
 */
@Data
public class Msg {

    private String title;

    private String content;

    private String extraInfo;

    public Msg(String title, String content, String extraInfo) {
        this.title = title;
        this.content = content;
        this.extraInfo = extraInfo;
    }

}
