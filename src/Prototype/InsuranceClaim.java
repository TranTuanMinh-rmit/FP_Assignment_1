package Prototype;

import java.time.LocalDate;

public class InsuranceClaim {
    //Attributes//
    protected Integer claimID;
    protected LocalDate claimDate;
    protected String claimInsuredPerson;
    protected InsuranceCard claimCardNumber;                      //This is a placeholder for the card number, will investigate it from class InsuranceCard
    protected LocalDate examDate;
    //private Documents[] relatedDocuments;                         //This is a placeholder for the related documents, will investigate it from class Documents
    protected String claimStatus;
    protected String claimAmount;
    protected String bankingInfo;


    //Constructor//
public InsuranceClaim(Integer claimID, LocalDate claimDate, String claimInsuredPerson, InsuranceCard claimCardNumber, LocalDate examDate/*, Documents[] relatedDocuments*/, String claimStatus, String claimAmount, String bankingInfo) {
        this.claimID = claimID;
        this.claimDate = claimDate;
        this.claimInsuredPerson = claimInsuredPerson;
        this.claimCardNumber = claimCardNumber;
        this.examDate = examDate;
        //this.relatedDocuments = relatedDocuments;
        this.claimStatus = claimStatus;
        this.claimAmount = claimAmount;
        this.bankingInfo = bankingInfo;
    }


    //Getter//
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
    public String getClaimStatus() {
        return claimStatus;
    }
    public String getClaimAmount() {
        return claimAmount;
    }

    public String getBankingInfo() {
        return bankingInfo;
    }

    //Setter//
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
    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }
    public void setClaimAmount(String claimAmount) {
        this.claimAmount = claimAmount;
    }
    public void setBankingInfo(String bankingInfo) {
        this.bankingInfo = bankingInfo;
    }
}
