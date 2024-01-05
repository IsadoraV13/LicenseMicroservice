package com.example.licensemicroservice.domain;

public class CustomerLicensing {
    private int customerId;
    private int LicensingId;

    public CustomerLicensing(int customerId, int licensingId) {
        this.customerId = customerId;
        LicensingId = licensingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getLicensingId() {
        return LicensingId;
    }

    public void setLicensingId(int licensingId) {
        LicensingId = licensingId;
    }
}
