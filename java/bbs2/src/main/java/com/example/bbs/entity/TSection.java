package com.example.bbs.entity;

import lombok.Data;

@Data
public class TSection {

    private Integer id;
    private String name;
    private Integer plateId;
    private Integer postNum;
    private Integer userId;
    private java.sql.Timestamp createTime;


}
