package com.example.hibernate_bug_demo.dto;

public class ViewDTO {
    private Long id;
    private FinancialInformation financialInformation;
    private AddressInformation addressInformation;
    private Integer value1;
    private Integer value2;
    private Integer value3;

    public ViewDTO(Long id, FinancialInformation financialInformation, AddressInformation addressInformation, Integer value1, Integer value2, Integer value3) {
        this.id = id;
        this.financialInformation = financialInformation;
        this.addressInformation = addressInformation;
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

    public FinancialInformation getFinancialInformation() {
        return financialInformation;
    }

    public void setFinancialInformation(FinancialInformation financialInformation) {
        this.financialInformation = financialInformation;
    }

    public AddressInformation getAddressInformation() {
        return addressInformation;
    }

    public void setAddressInformation(AddressInformation addressInformation) {
        this.addressInformation = addressInformation;
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
