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
    private Integer alphaNumber;
    private Integer betaNumber;
    private Integer gammaNumber;

    public MainEntity() {}

    public MainEntity(Long id, Long paid, Long received, String address, Integer number, Integer alphaNumber, Integer betaNumber, Integer gammaNumber) {
        this.id = id;
        this.paid = paid;
        this.received = received;
        this.address = address;
        this.number = number;
        this.alphaNumber = alphaNumber;
        this.betaNumber = betaNumber;
        this.gammaNumber = gammaNumber;
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

    public Integer getAlphaNumber() {
        return alphaNumber;
    }

    public void setAlphaNumber(Integer value1) {
        this.alphaNumber = value1;
    }

    public Integer getBetaNumber() {
        return betaNumber;
    }

    public void setBetaNumber(Integer value2) {
        this.betaNumber = value2;
    }

    public Integer getGammaNumber() {
        return gammaNumber;
    }

    public void setGammaNumber(Integer value3) {
        this.gammaNumber = value3;
    }
}
