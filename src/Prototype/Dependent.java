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

public class Dependent extends Customer{
    ArrayList<Dependent> dependents = new ArrayList<>();
    //Attributes//
    protected String dependentControlID;

    // Constructor //
    public Dependent() {
        super();
        dependentControlID = "D000";
    }
    public Dependent(String customerID, String customerFullName, String customerInsuranceCard, String customerClaims, String dependentControlID) {
        super(customerID, customerFullName, customerInsuranceCard, customerClaims);
        this.dependentControlID = dependentControlID;
    }

    // Getters //
    public String getControlID() {
        return dependentControlID;
    }

    // Setters //
    public void setControlID(String dependentControlID) {
        this.dependentControlID = dependentControlID;
    }

    // Data Handler //
    public void readDependentData() throws FileNotFoundException {
        Scanner dependentScanner = new Scanner("src/Datafiles/DependentData.csv");
        dependentScanner.useDelimiter("[,\n]");

        while (dependentScanner.hasNext()) {
            String customerID = dependentScanner.next();
            String customerFullName = dependentScanner.next();
            String customerInsuranceCard = dependentScanner.next();
            String customerClaims = dependentScanner.next();
            String dependentControlID = dependentScanner.next();
            addDependentToList(customerID, customerFullName, customerInsuranceCard, customerClaims, dependentControlID);
        }
    }

    public void writeDependentData() throws IOException {
        FileWriter dependentWriter = new FileWriter("src/Datafiles/DependentData.csv");
        PrintWriter out1 = new PrintWriter(dependentWriter);

        for (Dependent dependent : dependents) {
            out1.printf("%s,%s,%s,%s,%s\n", dependent.getCustomerID(), dependent.getCustomerFullName(), dependent.getCustomerInsuranceCard(), dependent.getCustomerClaims(), dependent.getControlID());
        }
        out1.close();
    }

    // Methods //
    public void addDependentToList(String customerID, String customerFullName, String customerInsuranceCard, String customerClaims, String dependentControlID) {
        dependents.add(new Dependent(customerID, customerFullName, customerInsuranceCard, customerClaims, dependentControlID));
    }
}
