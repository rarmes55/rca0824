package com.example.t33.items;

import java.util.ArrayList;
import java.util.List;


/*
AcmeToolRentalStore - Singleton - The main class for the tool rental store
 */
public enum AcmeToolRentalStore {
    INSTANCE;
    private final List<RentalAgreement> processedRentalAgreements = new ArrayList<>();

    public List<RentalAgreement> getProcessedRentalAgreements() {
        return processedRentalAgreements;
    }

    /*
    processRentalAgreement - processes and stored the processed rental agreement
     */
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
