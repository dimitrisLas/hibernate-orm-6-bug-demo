package com.example.hibernate_bug_demo.dto;

public class FinancialInformation {
    private Long paid;
    private Long received;

    public FinancialInformation(Long paid, Long received){
        this.paid = paid;
        this.received = received;
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
}
