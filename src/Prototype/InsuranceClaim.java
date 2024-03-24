package Prototype;
/**
 * @author <Tran Tuan Minh - s3804812>
 */


import java.time.LocalDate;
import java.util.ArrayList;

public class InsuranceClaim implements ClaimProcessManager{
    private ArrayList<String> claimsList;
    public ArrayList<String> getClaimsList() {
        return claimsList;
    }

    //Attributes//
    protected Integer claimID;
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
        this.claimID = 0;
        this.claimDate = LocalDate.now();
        this.claimInsuredPerson = "";
        this.claimCardNumber = new InsuranceCard();
        this.examDate = LocalDate.now();
        this.relatedDocuments = "";
        this.claimStatus = "";
        this.claimAmount = "";
        this.bankingInfo = "";
    }

    public InsuranceClaim(Integer claimID, LocalDate claimDate, String claimInsuredPerson, InsuranceCard claimCardNumber, LocalDate examDate, String relatedDocuments, String claimStatus, String claimAmount, String bankingInfo) {
        claimsList = new ArrayList<String>();
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
    public Integer getClaimID() {
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
    public void setClaimID(Integer claimID) {
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

    // Data imports //

    // CRUD //
    @Override
    public void add() {

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
