package com.example.hibernate_bug_demo.entity;

import jakarta.persistence.*;

@Entity
public class MainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long paid;
    private Long received;
    private String address;
    private Integer number;

    private Integer value1;
    private Integer value2;
    private Integer value3;

    public MainEntity(Long id, Long paid, Long received, String address, Integer number, Integer value1, Integer value2, Integer value3) {
        this.id = id;
        this.paid = paid;
        this.received = received;
        this.address = address;
        this.number = number;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPaid() {
        return paid;
    }

    public void setPaid(Long paid) {
        this.paid = paid;
    }

    public Long getReceived() {
        return received;
    }

    public void setReceived(Long received) {
        this.received = received;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getValue1() {
        return value1;
    }

    public void setValue1(Integer value1) {
        this.value1 = value1;
    }

    public Integer getValue2() {
        return value2;
    }

    public void setValue2(Integer value2) {
        this.value2 = value2;
    }

    public Integer getValue3() {
        return value3;
    }

    public void setValue3(Integer value3) {
        this.value3 = value3;
    }
}
