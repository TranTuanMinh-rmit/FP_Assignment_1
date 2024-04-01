package Prototype;
/**
 * @author <Tran Tuan Minh - s3804812>
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Holder extends Customer implements DataHandler{
    // ArrayList //
    ArrayList<Holder> holders = new ArrayList<>();
    private Dependent dependent;
    private InsuranceClaim insuranceClaim;
    // Attributes //
    private String holderControlID;
    // Constructor //
    public Holder(String customerID, String customerFullName, String customerInsuranceCard, String customerClaims) {
        super(customerID, customerFullName, customerInsuranceCard, customerClaims);
    }
    public Holder() {
        super();
    }

    // Data Handler //
    @Override
    public void readData() throws FileNotFoundException {
        Scanner holderScanner = new Scanner(new File("src/Datafiles/HolderData.csv"));
        holderScanner.useDelimiter("[,\n]");

        while (holderScanner.hasNext()) {
            String customerID = holderScanner.next();
            String customerFullName = holderScanner.next();
            String customerInsuranceCard = holderScanner.next();
            String customerClaims = holderScanner.next();
            addHolderToList(customerID, customerFullName, customerInsuranceCard, customerClaims);
        }
    }

    @Override
    public void writeData() throws IOException{
        FileWriter holderWriter = new FileWriter("src/Datafiles/HolderData.csv");
        PrintWriter out0 = new PrintWriter(holderWriter);

        for (Holder holder : holders){
            out0.printf("%s,%s,%s,%s,%s\n", holder.getCustomerID(), holder.getCustomerFullName(), holder.getCustomerInsuranceCard(), holder.getCustomerClaims());
        }
        out0.close();
    }

    // Methods //
    public void addHolderToList(String customerID, String customerFullName, String customerInsuranceCard, String customerClaims) {
        Holder holder = new Holder(customerID, customerFullName, customerInsuranceCard, customerClaims);
        holders.add(holder);
    }
    public void printHolders() throws FileNotFoundException {              //This method will print the holders and their dependents. Need to remember to put readData() in the main method to make sure new data is read.
        readData();
        dependent = new Dependent();
        ArrayList<Dependent> dependents = dependent.getDependents();
        ArrayList<InsuranceClaim> insuranceClaims = insuranceClaim.getClaimsList();
        for (Holder holder : holders) {
            System.out.println("====================================================================================================================================================================");
            System.out.println(String.format("=" ,"%-25 %-25 %-25 %-25", holder.getCustomerID() + " " + holder.getCustomerFullName() + " " + holder.getCustomerInsuranceCard() + " " + holder.getCustomerClaims() + "="));
            System.out.println("=------------------------------------------------------------------------------------------------------------------------------------------------------------------=");

            //This loop will iterate through the insuranceClaims list and print the claims of the holder
            for (InsuranceClaim insuranceClaim : insuranceClaims){
                if (insuranceClaim.getClaimInsuredPerson().equals(holder.getCustomerFullName())){
                    System.out.println(String.format("=" ,"%-25 %-25 %-25 %-25 %-25 %-25 %-25 %-25", insuranceClaim.getClaimID() + " " + insuranceClaim.getClaimDate() + " "+ insuranceClaim.getClaimCardNumber() + " " + insuranceClaim.getExamDate() + " " + insuranceClaim.getRelatedDocuments() + " " + insuranceClaim.getClaimStatus() + " " + insuranceClaim.getClaimAmount() + " " + insuranceClaim.getBankingInfo() + "="));
                }
            }

            //This loop will iterate through the dependents list and print the dependents of the holder
            for (Dependent dependent : dependents) {
                if (dependent.getDependOnHolderID().equals(holder.getCustomerID())) {
                    System.out.println(String.format("=" ,"%-25 %-25 %-25 %-25", dependent.getCustomerID() + " " + dependent.getCustomerFullName() + " " + dependent.getCustomerInsuranceCard() + " " + dependent.getCustomerClaims() + "="));
                }
            }
        }
        System.out.println("====================================================================================================================================================================");
    }
}