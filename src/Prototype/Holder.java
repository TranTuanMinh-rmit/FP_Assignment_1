package Prototype;
/**
 * @author <Tran Tuan Minh - s3804812>
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Holder extends Customer{
    ArrayList<Holder> holders = new ArrayList<>();
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
    public void readHolderData() throws FileNotFoundException {
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

    public void writeHolderData() throws IOException{
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

}
