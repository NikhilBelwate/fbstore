package com.demo.fbstore.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Condition {
    private String param;
    private String operator;
    private Object value;
}
