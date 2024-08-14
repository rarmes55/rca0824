package com.example.t33.items;

import java.util.ArrayList;
import java.util.List;

public enum AcmeToolRental {
    INSTANCE;
    private final List<RentalAgreement> processedRentalAgreements = new ArrayList<>();

    public List<RentalAgreement> getProcessedRentalAgreements() {
        return processedRentalAgreements;
    }

    public boolean processRentalAgreement(RentalAgreement rentalAgreement) {
        if (!rentalAgreement.isProcessed() && rentalAgreement.checkout()) {
            rentalAgreement.setProcessed(true);
            getProcessedRentalAgreements().add(rentalAgreement);
            return true;
        } else {
            return false;
        }
    }
}
