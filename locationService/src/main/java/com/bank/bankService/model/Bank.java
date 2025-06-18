package com.bank.bankService.model;

public class Bank {
    private String name;
    private String address;

    public Bank() {}
    public Bank(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
}

