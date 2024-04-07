package Prototype;
/**
 * @author <Tran Tuan Minh - s3804812>
 */

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Holder extends Customer implements DataHandler{
    // ArrayList //
    ArrayList<Holder> holders = new ArrayList<>();

    // Attributes //
    protected String holderControlID;
    protected String holderDependents;
    // Constructor //
    public Holder(String customerID, String customerFullName, String customerInsuranceCard, String customerClaims, String holderDependents, String holderControlID) {
        super(customerID, customerFullName, customerInsuranceCard, customerClaims);
        this.holderDependents = holderDependents;
        this.holderControlID = holderControlID;
    }
    public Holder() {
        super();
    }

    // Getters //
    public String getHolderControlID() {
        return holderControlID;
    }
    public String getHolderDependents() {
        return holderDependents;
    }

    // Setters //
    public void setHolderControlID(String holderControlID) {
        this.holderControlID = holderControlID;
    }
    public void setHolderDependents(String holderDependents) {
        this.holderDependents = holderDependents;
    }

    // Data Handler //
    @Override
    public void readData() throws FileNotFoundException {                   //This method reads the data from the file and adds it to the list
        Scanner holderScanner = new Scanner(new File("src/Datafiles/HolderData.csv"));
        holderScanner.useDelimiter("[,\n]");

        while (holderScanner.hasNext()) {
            String customerID = holderScanner.next();
            String customerFullName = holderScanner.next();
            String customerInsuranceCard = holderScanner.next();
            String customerClaims = holderScanner.next();
            String holderDependents = holderScanner.next();
            String holderControlID = holderScanner.next();
            addHolderToList(customerID, customerFullName, customerInsuranceCard, customerClaims, holderDependents, holderControlID);
        }
    }

    @Override
    public void writeData() throws IOException{                                 //This method writes the data to the file
        FileWriter holderWriter = new FileWriter("src/Datafiles/HolderData.csv", false);
        PrintWriter out0 = new PrintWriter(holderWriter);

        for (Holder holder : holders){
            out0.printf("%s,%s,%s,%s,%s,%s\n", holder.getCustomerID(), holder.getCustomerFullName(), holder.getCustomerInsuranceCard(), holder.getCustomerClaims(), holder.getHolderDependents(), holder.getHolderControlID());
        }
        out0.close();
    }

    // Methods //
    // This method will add the holder to the list
    public void addHolderToList(String customerID, String customerFullName, String customerInsuranceCard, String customerClaims, String holderDependents, String holderControlID) {
        Holder holder = new Holder(customerID, customerFullName, customerInsuranceCard, customerClaims, holderDependents, holderControlID);
        holders.add(holder);
    }
    public void printHolders(){              //This method will print the holders and their dependents. Need to remember to put readData() in the main method to make sure new data is read.
        System.out.println(String.format("%-25s %-25s %-25s %-25s %-25s", "Customer ID", "Customer Name", "Insurance Card", "Claims", "Dependents"));
        for (Holder holder2 : holders) {
            System.out.println(String.format("%-25s %-25s %-25s %-25s %-25s", holder2.getCustomerID(), holder2.getCustomerFullName(), holder2.getCustomerInsuranceCard(), holder2.getCustomerClaims(), holder2.getHolderDependents()));
        }
    }
}