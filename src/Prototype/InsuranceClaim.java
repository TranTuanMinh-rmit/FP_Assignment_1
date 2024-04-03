package Prototype;
/**
 * @author <Tran Tuan Minh - s3804812>
 */


import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class InsuranceClaim implements ClaimProcessManager, DataHandler{
    private ArrayList<InsuranceClaim> claimsList = new ArrayList<>();
    public ArrayList<InsuranceClaim> getClaimsList() {
        return claimsList;
    }

    //Attributes//
    protected String newID;
    protected String claimID;
    protected String claimDate;
    protected String claimInsuredPerson;
    protected String claimCardNumber;
    protected String examDate;
    protected String relatedDocuments;
    protected String claimStatus;                                 //New, Processing, Done. One of the three, nothing else, will have exception handler for this
    protected String claimAmount;
    protected String bankingInfo;


    // Constructor //
    public InsuranceClaim() {
        this.claimID = "F-0000000000";
        this.claimDate = "2020-01-01";
        this.claimInsuredPerson = "";
        this.claimCardNumber = "CRD-0000000000";
        this.examDate = "2020-01-01";
        this.relatedDocuments = "";
        this.claimStatus = "";
        this.claimAmount = "";
        this.bankingInfo = "";
    }

    public InsuranceClaim(String claimID, String claimDate, String claimInsuredPerson, String claimCardNumber, String examDate, String relatedDocuments, String claimStatus, String claimAmount, String bankingInfo) {
        this.claimID = claimID;
        this.claimDate = claimDate;
        this.claimInsuredPerson = claimInsuredPerson;
        this.claimCardNumber = claimCardNumber;
        this.examDate = examDate;
        this.relatedDocuments = relatedDocuments;
        this.claimStatus = claimStatus;
        this.claimAmount = claimAmount;
        this.bankingInfo = bankingInfo;
    }


    // Getters //
    public String getClaimID() {
        return claimID;
    }
    public String getClaimDate() {
        return claimDate;
    }
    public String getClaimInsuredPerson() {
        return claimInsuredPerson;
    }
    public String getClaimCardNumber() {
        return claimCardNumber;
    }
    public String getExamDate() {
        return examDate;
    }
    public String getRelatedDocuments() {
        return relatedDocuments;
    }
    public String getClaimStatus() {
        return claimStatus;
    }
    public String getClaimAmount() {
        return claimAmount;
    }

    public String getBankingInfo() {
        return bankingInfo;
    }

    // Setter //
    public void setClaimID(String claimID) {
        this.claimID = claimID;
    }
    public void setClaimDate(String claimDate) {
        this.claimDate = claimDate;
    }
    public void setClaimInsuredPerson(String claimInsuredPerson) {
        this.claimInsuredPerson = claimInsuredPerson;
    }
    public void setClaimCardNumber(String claimCardNumber) {
        this.claimCardNumber = claimCardNumber;
    }
    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }
    public void setRelatedDocuments(String relatedDocuments) {
        this.relatedDocuments = relatedDocuments;
    }
    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }
    public void setClaimAmount(String claimAmount) {
        this.claimAmount = claimAmount;
    }
    public void setBankingInfo(String bankingInfo) {
        this.bankingInfo = bankingInfo;
    }

    // Data Handlers //
    @Override
    public void readData() throws FileNotFoundException {
        Scanner claimScanner = new Scanner(new File("src/Datafiles/Claims.csv"));
        claimScanner.useDelimiter("[,\n]");

        while (claimScanner.hasNext()) {
            String claimID = claimScanner.next();
            String claimDate = claimScanner.next();
            String claimInsuredPerson = claimScanner.next();
            String claimCardNumber = claimScanner.next();
            String examDate = claimScanner.next();
            String relatedDocuments = claimScanner.next();
            String claimStatus = claimScanner.next();
            String claimAmount = claimScanner.next();
            String bankingInfo = claimScanner.next();
            addClaimToList(claimID, claimDate, claimInsuredPerson, claimCardNumber, examDate, relatedDocuments, claimStatus, claimAmount, bankingInfo);
        }
    }
    @Override
    public void writeData() throws IOException {
        FileWriter claimWriter = new FileWriter("src/Datafiles/Claims.csv");
        PrintWriter out2 = new PrintWriter(claimWriter);

        for (InsuranceClaim claim : claimsList) {
            out2.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s\n", claim.getClaimID(), claim.getClaimDate(), claim.getClaimInsuredPerson(), claim.getClaimCardNumber(), claim.getExamDate(), claim.getRelatedDocuments(), claim.getClaimStatus(), claim.getClaimAmount(), claim.getBankingInfo());
        }
        out2.close();
    }

    // Methods //
    public void addClaimToList(String claimID, String claimDate, String claimInsuredPerson, String claimCardNumber, String examDate, String relatedDocuments, String claimStatus, String claimAmount, String bankingInfo) {
        claimsList.add(new InsuranceClaim(claimID, claimDate, claimInsuredPerson, claimCardNumber, examDate, relatedDocuments, claimStatus, claimAmount, bankingInfo));
    }


    public void generateID(){
        int lastID = claimsList.size();
        lastID++;
        //This method will generate a unique claim ID for each claim
        newID = String.format("f-" + "%010d", lastID);
    }

    // CRUD //
    @Override
    public void add() throws InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.println("Generated claim ID...");
        generateID();
        String claimID = newID;
        Thread.sleep(300);
        System.out.println("Enter the claim date [yyyy-MM-dd]: ");                      //This is running under the assumption that this is the day a customer is filling for a claim
        String claimDate = input.nextLine();
        System.out.println("Enter the name of the insured person: ");
        String claimInsuredPerson = input.nextLine();
        System.out.println("Enter the card number: ");
        String claimCardNumber = input.nextLine();
        System.out.println("Enter the examination date [yyyy-MM-dd]: ");                //This is the day set for examining the customer making this claim
        String examDate = input.nextLine();
        System.out.println("Enter the related documents: ");
        String relatedDocuments = input.nextLine();
        System.out.println("Enter the claim status [New - Processing - Done]: ");
        String claimStatus = input.nextLine();
        System.out.println("Enter the claim amount: ");
        String claimAmount = input.nextLine();
        System.out.println("Enter the banking information [TBA]: ");
        String bankingInfo = input.nextLine();
        addClaimToList(claimID, claimDate, claimInsuredPerson, claimCardNumber, examDate, relatedDocuments + ".pdf", claimStatus, claimAmount, bankingInfo);
    }


    // This method is for updating the information of a claim. Right now it can update the examination date, claim status and claim amount.
    @Override
    public void update() throws IOException{
        ArrayList<InsuranceClaim> claimsFoundUpdate = new ArrayList<>();
        Scanner inputClaimForUpdate = new Scanner(System.in);
        System.out.println("Enter the ID of the Insurance Claim you want to update: ");
        String claimIDToUpdate = inputClaimForUpdate.next();
        for (InsuranceClaim claim : claimsList) {
            if (claim.getClaimID().equals(claimIDToUpdate)) {
                claimsFoundUpdate.add(claim);
            }
        }
        if (claimsFoundUpdate.isEmpty()){
            System.out.println("No such claim is found!");
        } else {
            Boolean runningUpdate = true;
            while (runningUpdate){
                System.out.println("What would you like to update? \n"
                        + "1. Change Exam Date \n"
                        + "2. Change Claim Status \n"
                        + "3. Change Claim Amount \n"
                        + "4. Exit \n");
                String updateChoice = inputClaimForUpdate.next();

                switch(updateChoice){
                    case "1":
                        System.out.println("Enter the new examination date [yyyy-MM-dd]: ");
                        String newExamDate = inputClaimForUpdate.next();
                        for (InsuranceClaim claim : claimsFoundUpdate){
                            claim.setExamDate(newExamDate);
                        }
                        writeData();
                        break;
                    case "2":
                        System.out.println("Enter the new claim status [New - Pending - Done]: ");
                        String newClaimStatus = inputClaimForUpdate.next();
                        for (InsuranceClaim claim : claimsFoundUpdate){
                            claim.setClaimStatus(newClaimStatus);
                        }
                        writeData();
                        break;
                    case "3":
                        System.out.println("Enter the new claim amount: ");
                        String newClaimAmount = inputClaimForUpdate.next();
                        for (InsuranceClaim claim : claimsFoundUpdate){
                            claim.setClaimAmount(newClaimAmount);
                        }
                        writeData();
                        break;
                    case "4":
                        runningUpdate = false;
                        break;
                    default:
                        System.out.println("Invalid Input!");
                }
            }
        }
    }


    //This method is for deleting a claim. Only 1 claim can be deleted at a time. Will see for it to delete multiple claims at once in the future.
    @Override
    public void delete() throws IOException{
        ArrayList<InsuranceClaim> claimsFoundDelete = new ArrayList<>();
        Iterator deleteIterator = claimsList.iterator();
        Scanner inputClaimForDelete = new Scanner(System.in);
        System.out.println("Enter the ID of the Insurance Claim you want to delete: ");
        String claimIDToDelete = inputClaimForDelete.next();
        for (InsuranceClaim claim : claimsList) {
            if (claim.getClaimID().equals(claimIDToDelete)) {
                claimsFoundDelete.add(claim);
            }
        }
        if (claimsFoundDelete.isEmpty()) {
            System.out.println("No such claim is found!");
        } else {
            while (deleteIterator.hasNext()) {
                InsuranceClaim claim = (InsuranceClaim) deleteIterator.next();
                if (claim.getClaimID().equals(claimIDToDelete)) {
                    deleteIterator.remove();
                }
            }
            System.out.println("Insurance Claim removed!");
            writeData();
        }
    }

    @Override
    public void getOne() {
        ArrayList<InsuranceClaim> claimFoundDisplay = new ArrayList<>();
        Scanner inputClaimForDisplay = new Scanner(System.in);
        System.out.println("Enter the ID of the Insurance Claim you want to display: ");
        String claimIDToDisplay = inputClaimForDisplay.next();
        for (InsuranceClaim claim : claimsList) {
            if (claim.getClaimID().equals(claimIDToDisplay)) {
                claimFoundDisplay.add(claim);
            }
        }
        if (claimFoundDisplay.isEmpty()) {
            System.out.println("No such claim is found!");
        } else {
            System.out.println(String.format("%-15s %-15s %-20s %-20s %-15s %-25s %-20s %-20s %-20s", "Claim ID", "Claim Date", "Insured Person", "Card Number", "Exam Date", "Related Documents", "Claim Status", "Claim Amount", "Banking Info"));
            for (InsuranceClaim claim : claimFoundDisplay) {
                System.out.println(String.format("%-15s %-15s %-20s %-20s %-15s %-25s %-20s %-20s %-20s", claim.getClaimID(), claim.getClaimDate(), claim.getClaimInsuredPerson(), claim.getClaimCardNumber(), claim.getExamDate(), claim.getRelatedDocuments(), claim.getClaimStatus(), claim.getClaimAmount(), claim.getBankingInfo()));
            }
        }
    }

    //Basic claims dump
    @Override
    public void getAll() {
        System.out.println(String.format("%-15s %-15s %-20s %-20s %-15s %-25s %-20s %-20s %-20s", "Claim ID", "Claim Date", "Insured Person", "Card Number", "Exam Date", "Related Documents", "Claim Status", "Claim Amount", "Banking Info"));
        for (InsuranceClaim claim : claimsList) {
            System.out.println(String.format("%-15s %-15s %-20s %-20s %-15s %-25s %-20s %-20s %-20s", claim.getClaimID(), claim.getClaimDate(), claim.getClaimInsuredPerson(), claim.getClaimCardNumber(), claim.getExamDate(), claim.getRelatedDocuments(), claim.getClaimStatus(), claim.getClaimAmount(), claim.getBankingInfo()));
        }
    }
}
