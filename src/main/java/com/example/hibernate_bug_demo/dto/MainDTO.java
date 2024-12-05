package com.example.hibernate_bug_demo.dto;

public class MainDTO {
    private final Long id;
    private final FinancialInformationDTO financialInformationDTO;
    private final AddressInformationDTO addressInformationDTO;
    private final Integer alphaNumber;
    private final Integer betaNumber;
    private final Integer gammaNumber;

    public MainDTO(Long id, FinancialInformationDTO financialInformationDTO, AddressInformationDTO addressInformationDTO, Integer alphaNumber, Integer value2, Integer value3) {
        this.id = id;
        this.financialInformationDTO = financialInformationDTO;
        this.addressInformationDTO = addressInformationDTO;
        this.alphaNumber = alphaNumber;
        this.betaNumber = value2;
        this.gammaNumber = value3;
    }

    public Long getId() {
        return id;
    }

    public Long getPaid() {
        return financialInformationDTO.paid();
    }

    public Long getReceived() {
        return financialInformationDTO.received();
    }

    public Integer getNumber() {
        return addressInformationDTO.number();
    }

    public String getAddress() {
        return addressInformationDTO.address();
    }

    public Integer getAlphaNumber() {
        return alphaNumber;
    }

    public Integer getBetaNumber() {
        return betaNumber;
    }

    public Integer getGammaNumber() {
        return gammaNumber;
    }

}
