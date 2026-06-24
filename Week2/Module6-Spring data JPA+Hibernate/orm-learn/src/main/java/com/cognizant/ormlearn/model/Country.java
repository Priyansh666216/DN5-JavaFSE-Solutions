package com.cognizant.ormlearn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// @Entity — tells Spring Data JPA this class maps to a database table
// @Table  — specifies which table in the DB this entity maps to
@Entity
@Table(name = "country")
public class Country {

    // @Id     — marks this field as the primary key
    // @Column — maps this field to the 'co_code' column in the table
    @Id
    @Column(name = "co_code")
    private String code;

    // @Column — maps this field to the 'co_name' column in the table
    @Column(name = "co_name")
    private String name;

    // -------------------------------------------------------
    // Getters and Setters
    // -------------------------------------------------------

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // -------------------------------------------------------
    // toString — used by LOGGER.debug("countries={}", countries)
    // to print readable output instead of memory address
    // -------------------------------------------------------
    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
