package Prototype;
/**
 * @author <Tran Tuan Minh - s3804812>
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Holder extends Customer implements DataHandler{
    // ArrayList //
    ArrayList<Holder> holders = new ArrayList<>();
    ArrayList<Holder> bufferDependentList = new ArrayList<>();              //This ArrayList will act as a buffer for the dependent list lookup
    private Dependent dependent;
    // Attributes //
    private String holderControlID;
    // Constructor //
    public Holder(String customerID, String customerFullName, String customerInsuranceCard, String customerClaims, String holderControlID) {
        super(customerID, customerFullName, customerInsuranceCard, customerClaims);
        this.holderControlID = holderControlID;
    }
    public Holder() {
        super();
        this.holderControlID = "H000";
    }

    // Getters //
    public String getControlID() {
        return holderControlID;
    }

    // Setters //
    public void setControlID(String holderControlID) {
        this.holderControlID = holderControlID;
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
            String holderControlID = holderScanner.next();
            addHolderToList(customerID, customerFullName, customerInsuranceCard, customerClaims, holderControlID);
        }
    }

    @Override
    public void writeData() throws IOException{
        FileWriter holderWriter = new FileWriter("src/Datafiles/HolderData.csv");
        PrintWriter out0 = new PrintWriter(holderWriter);

        for (Holder holder : holders){
            out0.printf("%s,%s,%s,%s,%s\n", holder.getCustomerID(), holder.getCustomerFullName(), holder.getCustomerInsuranceCard(), holder.getCustomerClaims(), holder.getControlID());
        }
        out0.close();
    }

    // Methods //
    public void addHolderToList(String customerID, String customerFullName, String customerInsuranceCard, String customerClaims, String holderControlID) {
        Holder holder = new Holder(customerID, customerFullName, customerInsuranceCard, customerClaims, holderControlID);
        holders.add(holder);
    }
    public void printHolders() {
        dependent = new Dependent();
        ArrayList<Dependent> dependents = dependent.getDependents();
        for (Holder holder : holders) {
            System.out.println("====================================================================================================================================================================");
            System.out.println(String.format("=" ,"%-25 %-25 %-25 %-25", holder.getCustomerID() + " " + holder.getCustomerFullName() + " " + holder.getCustomerInsuranceCard() + " " + holder.getCustomerClaims() + "="));
            System.out.println("=------------------------------------------------------------------------------------------------------------------------------------------------------------------=");
            for (Dependent dependent : dependents) {
                if (dependent.getDependOnHolderID().equals(holder.getCustomerID())) {
                    System.out.println(String.format("=" ,"%-25 %-25 %-25 %-25", dependent.getCustomerID() + " " + dependent.getCustomerFullName() + " " + dependent.getCustomerInsuranceCard() + " " + dependent.getCustomerClaims() + "="));
                }
            }
        }
        System.out.println("====================================================================================================================================================================");
    }

}
