package com.demo.model.test;

import lombok.Data;

import java.util.Date;

@Data
public class News {

    private int EId;

    private String ETitle;

    private String EContent;

    private String EDesc;

    private Date CreateTime;

}
