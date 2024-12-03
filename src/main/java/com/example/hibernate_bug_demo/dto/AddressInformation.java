package com.example.hibernate_bug_demo.dto;

public class AddressInformation {
    private String address;
    private Integer number;

    public AddressInformation(String address, Integer number) {
        this.address = address;
        this.number = number;
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
}
