package com.wangtk.mvc.dto;

import lombok.Data;

@Data
public class People {
    public People() {
        System.out.println("people");
    }

    private String id;
    private String name;
    private Integer age;
}
