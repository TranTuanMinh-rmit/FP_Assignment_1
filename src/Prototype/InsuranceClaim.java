package Prototype;
/**
 * @author <Tran Tuan Minh - s3804812>
 */


import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class InsuranceClaim implements ClaimProcessManager, DataHandler, Generator{
    private ArrayList<InsuranceClaim> claimsList = new ArrayList<>();
    public ArrayList<InsuranceClaim> getClaimsList() {
        return claimsList;
    }
    protected Integer lastClaimIDGenerated;

    //Attributes//
    protected String claimID;
    protected LocalDate claimDate;
    protected String claimInsuredPerson;
    protected InsuranceCard claimCardNumber;
    protected LocalDate examDate;
    private String relatedDocuments;
    protected String claimStatus;                                 //New, Pending, Done. One of the three, nothing else, will have exception handler for this
    protected String claimAmount;
    protected String bankingInfo;


    // Constructor //
    public InsuranceClaim() {
        this.claimID = "F-0000000000";
        this.claimDate = LocalDate.now();
        this.claimInsuredPerson = "";
        this.claimCardNumber = new InsuranceCard();
        this.examDate = LocalDate.now();
        this.relatedDocuments = "";
        this.claimStatus = "";
        this.claimAmount = "";
        this.bankingInfo = "";
    }

    public InsuranceClaim(String claimID, LocalDate claimDate, String claimInsuredPerson, InsuranceCard claimCardNumber, LocalDate examDate, String relatedDocuments, String claimStatus, String claimAmount, String bankingInfo) {
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
    public LocalDate getClaimDate() {
        return claimDate;
    }
    public String getClaimInsuredPerson() {
        return claimInsuredPerson;
    }
    public InsuranceCard getClaimCardNumber() {
        return claimCardNumber;
    }
    public LocalDate getExamDate() {
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
    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }
    public void setClaimInsuredPerson(String claimInsuredPerson) {
        this.claimInsuredPerson = claimInsuredPerson;
    }
    public void setClaimCardNumber(InsuranceCard claimCardNumber) {
        this.claimCardNumber = claimCardNumber;
    }
    public void setExamDate(LocalDate examDate) {
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
        Scanner claimScanner = new Scanner("src/Data/Claims.csv");
        claimScanner.useDelimiter("[,\n]");

        while (claimScanner.hasNext()) {
            String claimID = claimScanner.nextLine();
            LocalDate claimDate = LocalDate.parse(claimScanner.next());
            String claimInsuredPerson = claimScanner.next();
            InsuranceCard claimCardNumber = new InsuranceCard(claimScanner.next(), claimScanner.nextInt(), claimScanner.next(), claimScanner.next(), LocalDate.parse(claimScanner.next()));
            LocalDate examDate = LocalDate.parse(claimScanner.next());
            String relatedDocuments = claimScanner.next();
            String claimStatus = claimScanner.next();
            String claimAmount = claimScanner.next();
            String bankingInfo = claimScanner.next();
            addClaimToList(claimID, claimDate, claimInsuredPerson, claimCardNumber, examDate, relatedDocuments, claimStatus, claimAmount, bankingInfo);
        }
    }
    @Override
    public void writeData() throws IOException {
        FileWriter claimWriter = new FileWriter("src/Data/Claims.csv");
        PrintWriter out2 = new PrintWriter(claimWriter);

        for (InsuranceClaim claim : claimsList) {
            out2.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s\n", claim.getClaimID(), claim.getClaimDate(), claim.getClaimInsuredPerson(), claim.getClaimCardNumber(), claim.getExamDate(), claim.getRelatedDocuments(), claim.getClaimStatus(), claim.getClaimAmount(), claim.getBankingInfo());
        }
        out2.close();
    }

    // Methods //
    public void addClaimToList(String claimID, LocalDate claimDate, String claimInsuredPerson, InsuranceCard claimCardNumber, LocalDate examDate, String relatedDocuments, String claimStatus, String claimAmount, String bankingInfo) {
        claimsList.add(new InsuranceClaim(claimID, claimDate, claimInsuredPerson, claimCardNumber, examDate, relatedDocuments, claimStatus, claimAmount, bankingInfo));
    }

    // Generators for ID and Date //
    @Override
    public void getLastIDGenerated() throws FileNotFoundException{
        //This method will get the last claim ID generated
        Scanner lastIDScanner = new Scanner("src/Datafile/LastClaimsIDGenerated.csv");

        while (lastIDScanner.hasNext()) {
            lastClaimIDGenerated = lastIDScanner.nextInt();
        }
    }

    @Override
    public void writeLastIDGenerated() throws IOException {
        //This method will set the last claim ID generated
        FileWriter lastIDWriter = new FileWriter("src/Datafile/LastClaimsIDGenerated.csv", false);
        PrintWriter out3 = new PrintWriter(lastIDWriter);
        out3.printf("%d", lastClaimIDGenerated);
        out3.close();
    }

    @Override
    public String generateID(String lastID){
        //This method will generate a unique claim ID for each claim
        String newID = String.format("f-" + "%010d",lastID);
        return newID;
    }

    // CRUD //
    @Override
    public void add() throws InterruptedException, IOException {
        Scanner input = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Generated claim ID...");
            lastClaimIDGenerated++;
            String claimID = generateID(String.valueOf(lastClaimIDGenerated));
            writeLastIDGenerated();
            Thread.sleep(300);
            System.out.println("Enter the claim date [yyyy-MM-dd]: ");                      //This is running under the assumption that this is the day a customer is filling for a claim
            LocalDate claimDate = LocalDate.parse(input.next());
            System.out.println("Enter the name of the insured person: ");
            String claimInsuredPerson = input.next();
            System.out.println("Enter the card number: ");
            InsuranceCard claimCardNumber = new InsuranceCard();
            System.out.println("Enter the examination date [yyyy-MM-dd]: ");                //This is the day set for examining the customer making this claim
            LocalDate examDate = LocalDate.parse(input.next());
            System.out.println("Enter the related documents: ");
            String relatedDocuments = input.next();
            System.out.println("Enter the claim status [New - Pending - Done]: ");
            String claimStatus = input.next();
            System.out.println("Enter the claim amount: ");
            String claimAmount = input.next();
            System.out.println("Enter the banking information [TBA]: ");
            String bankingInfo = input.next();
            addClaimToList(claimID, claimDate, claimInsuredPerson, claimCardNumber, examDate, relatedDocuments + ".pdf", claimStatus, claimAmount, bankingInfo);
            System.out.println("Do you want to add another claim? (Y/N)");
            String choice = input.next();
            if (choice.equalsIgnoreCase("N")) {
                running = false;
            }
        }
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
                        LocalDate newExamDate = LocalDate.parse(inputClaimForUpdate.next());
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
                    default:
                        System.out.println("Invalid Input!");
                }
            }
        }
    }


    //This method is for deleting a claim. Only 1 claim can be deleted at a time. Will see for it to delete multiple claims at once in the future.
    @Override
    public void delete() {
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
                System.out.print("Insurance Claim found!");
                System.out.println(String.format("%-15s %-25s %-20s %-15s %-15s %-15s %-15s %-15s %-15s", "Claim ID", "Claim Date", "Insured Person", "Card Number", "Exam Date", "Related Documents", "Claim Status", "Claim Amount", "Banking Info"));
                for (InsuranceClaim claim : claimsFoundDelete) {
                    System.out.println(String.format("%-15s %-25s %-20s %-15s %-15s %-15s %-15s %-15s %-15s", claim.getClaimID(), claim.getClaimDate(), claim.getClaimInsuredPerson(), claim.getClaimCardNumber(), claim.getExamDate(), claim.getRelatedDocuments(), claim.getClaimStatus(), claim.getClaimAmount(), claim.getBankingInfo()));
                }
                InsuranceClaim claim = (InsuranceClaim) deleteIterator.next();
                if (claim.getClaimID().equals(claimIDToDelete)) {
                    deleteIterator.remove();
                }
            }
            System.out.println("Insurance Claim removed!");
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
            System.out.println(String.format("%-15s %-25s %-20s %-15s %-15s %-15s %-15s %-15s %-15s", "Claim ID", "Claim Date", "Insured Person", "Card Number", "Exam Date", "Related Documents", "Claim Status", "Claim Amount", "Banking Info"));
            for (InsuranceClaim claim : claimFoundDisplay) {
                System.out.println(String.format("%-15s %-25s %-20s %-15s %-15s %-15s %-15s %-15s %-15s", claim.getClaimID(), claim.getClaimDate(), claim.getClaimInsuredPerson(), claim.getClaimCardNumber(), claim.getExamDate(), claim.getRelatedDocuments(), claim.getClaimStatus(), claim.getClaimAmount(), claim.getBankingInfo()));
            }
        }
    }

    //Basic claims dump
    @Override
    public void getAll() {
        System.out.println(String.format("%-15s %-25s %-20s %-15s %-15s %-15s %-15s %-15s %-15s", "Claim ID", "Claim Date", "Insured Person", "Card Number", "Exam Date", "Related Documents", "Claim Status", "Claim Amount", "Banking Info"));
        for (InsuranceClaim claim : claimsList) {
            System.out.println(String.format("%-15s %-25s %-20s %-15s %-15s %-15s %-15s %-15s %-15s", claim.getClaimID(), claim.getClaimDate(), claim.getClaimInsuredPerson(), claim.getClaimCardNumber(), claim.getExamDate(), claim.getRelatedDocuments(), claim.getClaimStatus(), claim.getClaimAmount(), claim.getBankingInfo()));
        }
    }
}
