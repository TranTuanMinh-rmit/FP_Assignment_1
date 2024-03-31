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
import java.util.Scanner;

public class InsuranceClaim implements ClaimProcessManager, DataHandler, Generator{
    private ArrayList<InsuranceClaim> claimsList;
    public ArrayList<InsuranceClaim> getClaimsList() {
        return claimsList;
    }

    //Attributes//
    protected String claimID;
    protected LocalDate claimDate;
    protected String claimInsuredPerson;
    protected InsuranceCard claimCardNumber;                      //This is a placeholder for the card number, will investigate it from class InsuranceCard
    protected LocalDate examDate;
    private String relatedDocuments;                              //This is a placeholder for the related documents, will investigate it from class Documents
    protected String claimStatus;                                 //New, Pending, Done. One of the three, nothing else, will have exception handler for this
    protected String claimAmount;
    protected String bankingInfo;


    // Constructor //
    public InsuranceClaim() {
        this.claimID = "f-0000000000";
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
        claimsList = new ArrayList<InsuranceClaim>();
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

    @Override
    public void generateID() {
        //This method will generate a unique claim ID for each claim

    }

    @Override
    public void generateDate() {
        //This method will generate the current date for the claim
    }

    // CRUD //
    @Override
    public void add() {
        Scanner input = new Scanner(System.in);
        boolean running = true;
        while (running) {

            System.out.println("Enter the name of the insured person: ");
            String claimInsuredPerson = input.next();
            System.out.println("Enter the card number: ");
            InsuranceCard claimCardNumber = new InsuranceCard();
            System.out.println("Enter the examination date: ");
            LocalDate examDate = LocalDate.parse(input.next());
            System.out.println("Enter the related documents: ");
            String relatedDocuments = input.next();
            System.out.println("Enter the claim status: ");
            String claimStatus = input.next();
            System.out.println("Enter the claim amount: ");
            String claimAmount = input.next();
            System.out.println("Enter the banking information: ");
            String bankingInfo = input.next();
            addClaimToList(claimID, claimDate, claimInsuredPerson, claimCardNumber, examDate, relatedDocuments, claimStatus, claimAmount, bankingInfo);
            System.out.println("Do you want to add another claim? (Y/N)");
            String choice = input.next();
            if (choice.equalsIgnoreCase("N")) {
                running = false;
            }
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void getOne() {

    }

    @Override
    public void getAll() {

    }
}
