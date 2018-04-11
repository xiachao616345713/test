package com.manager.common.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String label;

    @Column(name = "label_name")
    private String labelName;

    private String code;

    private String value;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return label
     */
    public String getlabel() {
        return label;
    }

    /**
     * @param label
     */
    public void setlabel(String label) {
        this.label = label;
    }

    /**
     * @return label_name
     */
    public String getlabelName() {
        return labelName;
    }

    /**
     * @param labelName
     */
    public void setlabelName(String labelName) {
        this.labelName = labelName;
    }

    /**
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }
}