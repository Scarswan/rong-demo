package com.demo.model;

import lombok.Data;

import java.util.Map;

@Data
public class JsonResult {

    private int StateCode;

    private Map<String,String> map;

    private Object data;

}
