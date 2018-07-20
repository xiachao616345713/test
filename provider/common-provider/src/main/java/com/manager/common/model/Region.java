package com.manager.common.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Region {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 城市名
     */
    private String name;

    /**
     * 父ID
     */
    private Integer pid;

    /**
     * 英文名
     */
    @Column(name = "name_en")
    private String nameEn;
}