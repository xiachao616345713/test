package com.manager.common.model;

import javax.persistence.*;

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

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取城市名
     *
     * @return name - 城市名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置城市名
     *
     * @param name 城市名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取父ID
     *
     * @return pid - 父ID
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置父ID
     *
     * @param pid 父ID
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取英文名
     *
     * @return name_en - 英文名
     */
    public String getNameEn() {
        return nameEn;
    }

    /**
     * 设置英文名
     *
     * @param nameEn 英文名
     */
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }
}