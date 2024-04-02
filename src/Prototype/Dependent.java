package Prototype;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author <Tran Tuan Minh - s3804812>
 */

public class Dependent extends Customer implements DataHandler{
    // ArrayList //
    ArrayList<Dependent> dependents = new ArrayList<>();
    public ArrayList<Dependent> getDependents() {
        return dependents;
    }

    private InsuranceClaim insuranceClaim;
    //Attributes//
    protected String dependOnHolderID;                  //This is the attribute for the Holder of their Insurance Card

    // Constructor //
    public Dependent() {
        super();
    }
    public Dependent(String customerID, String customerFullName, String customerInsuranceCard, String customerClaims, String dependOnHolderID){
        super(customerID, customerFullName, customerInsuranceCard, customerClaims);
        this.dependOnHolderID = dependOnHolderID;
    }

    // Getters //
    public String getDependOnHolderID() {
        return dependOnHolderID;
    }

    // Setters //
    public void setDependOnHolderID(String dependOnHolderID) {
        this.dependOnHolderID = dependOnHolderID;
    }

    // Data Handler //
    @Override
    public void readData() throws FileNotFoundException {
        Scanner dependentScanner = new Scanner("src/Datafiles/DependentData.csv");
        dependentScanner.useDelimiter("[,\n]");

        while (dependentScanner.hasNext()) {
            String customerID = dependentScanner.next();
            String customerFullName = dependentScanner.next();
            String customerInsuranceCard = dependentScanner.next();
            String customerClaims = dependentScanner.next();
            String dependOnHolderID = dependentScanner.next();
            addDependentToList(customerID, customerFullName, customerInsuranceCard, customerClaims, dependOnHolderID);
        }
    }

    @Override
    public void writeData() throws IOException {
        FileWriter dependentWriter = new FileWriter("src/Datafiles/DependentData.csv", false);
        PrintWriter out1 = new PrintWriter(dependentWriter);

        for (Dependent dependent : dependents) {
            out1.printf("%s,%s,%s,%s,%s\n", dependent.getCustomerID(), dependent.getCustomerFullName(), dependent.getCustomerInsuranceCard(), dependent.getCustomerClaims(), dependent.getDependOnHolderID());
        }
        out1.close();
    }

    // Methods //

    //This method is used to add a dependent to the list of dependents
    public void addDependentToList(String customerID, String customerFullName, String customerInsuranceCard, String customerClaims, String dependOnHolderID) {
        dependents.add(new Dependent(customerID, customerFullName, customerInsuranceCard, customerClaims, dependOnHolderID));
    }
}
