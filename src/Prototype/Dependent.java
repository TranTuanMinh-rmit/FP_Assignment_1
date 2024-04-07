package Prototype;
/**
 * @author <Tran Tuan Minh - s3804812>
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Dependent extends Customer implements DataHandler{
    // ArrayList //
    ArrayList<Dependent> dependents = new ArrayList<>();
    public ArrayList<Dependent> getDependents() {
        return dependents;
    }
    //Attributes//
    protected String dependOnHolderID;                  //This is the attribute for the Holder of their Insurance Card
    protected String dependentControlID;                //This is the attribute for the control ID of the dependent

    // Constructor //
    public Dependent() {
        super();
    }
    public Dependent(String customerID, String customerFullName, String customerInsuranceCard, String customerClaims, String dependOnHolderID, String dependentControlID){
        super(customerID, customerFullName, customerInsuranceCard, customerClaims);
        this.dependOnHolderID = dependOnHolderID;
        this.dependentControlID = dependentControlID;
    }

    // Getters //
    public String getDependOnHolderID() {
        return dependOnHolderID;
    }
    public String getDependentControlID() {
        return dependentControlID;
    }

    // Setters //
    public void setDependOnHolderID(String dependOnHolderID) {
        this.dependOnHolderID = dependOnHolderID;
    }
    public void setDependentControlID(String dependentControlID) {
        this.dependentControlID = dependentControlID;
    }

    // Data Handler //
    @Override
    public void readData() throws FileNotFoundException {     //This method reads data from the file and adds it to the list of dependents
        Scanner dependentScanner = new Scanner(new File("src/Datafiles/DependentData.csv"));
        dependentScanner.useDelimiter("[,\n]");

        while (dependentScanner.hasNext()) {
            String customerID = dependentScanner.next();
            String customerFullName = dependentScanner.next();
            String customerInsuranceCard = dependentScanner.next();
            String customerClaims = dependentScanner.next();
            String dependOnHolderID = dependentScanner.next();
            String dependentControlID = dependentScanner.next();
            addDependentToList(customerID, customerFullName, customerInsuranceCard, customerClaims, dependOnHolderID, dependentControlID);
        }
    }

    @Override
    public void writeData() throws IOException {      //This method writes data to the file
        FileWriter dependentWriter = new FileWriter("src/Datafiles/DependentData.csv", false);
        PrintWriter out1 = new PrintWriter(dependentWriter);

        for (Dependent dependent : dependents) {
            out1.printf("%s,%s,%s,%s,%s,%s\n", dependent.getCustomerID(), dependent.getCustomerFullName(), dependent.getCustomerInsuranceCard(), dependent.getCustomerClaims(), dependent.getDependOnHolderID(), dependent.getDependentControlID());
        }
        out1.close();
    }

    // Methods //
    public void printAllDependents() {     //This method prints all the dependents
        System.out.println(String.format("%-20s %-20s %-20s %-20s %-20s", "Customer ID", "Customer Name", "Insurance Card", "Claims", "Depend On Holder"));
        for (Dependent dependent2 : dependents) {
            System.out.println(String.format("%-20s %-20s %-20s %-20s %-20s", dependent2.getCustomerID(), dependent2.getCustomerFullName(), dependent2.getCustomerInsuranceCard(), dependent2.getCustomerClaims(), dependent2.getDependOnHolderID()));
        }

    }

    //This method is used to add a dependent to the list of dependents
    public void addDependentToList(String customerID, String customerFullName, String customerInsuranceCard, String customerClaims, String dependOnHolderID, String dependentControlID) {
        dependents.add(new Dependent(customerID, customerFullName, customerInsuranceCard, customerClaims, dependOnHolderID, dependentControlID));
    }
}
