package com.example.webDemo.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author luofei on 2018/5/8.
 */
public class CodeConstants {


    @AllArgsConstructor
    public enum testEnum {
        T("11", "11");

        @Getter
        private String name;
        @Getter
        private String value;
    }
}
