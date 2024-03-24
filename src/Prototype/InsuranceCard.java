package Prototype;
/**
 * @author <Tran Tuan Minh - s3804812>
 */


import java.time.LocalDate;

public class InsuranceCard {
    //Attributes//
    protected String insuranceID;
    protected Integer cardNumber;
    protected String cardHolder;
    protected String cardPolicyOwner;
    protected LocalDate cardExpDate;


    // Constructor //
    public InsuranceCard() {
        this.insuranceID = "";
        this.cardNumber = 0;
        this.cardHolder = "";
        this.cardPolicyOwner = "";
        this.cardExpDate = LocalDate.now();                         //This needs some more thoughts
    }

    public InsuranceCard(String insuranceID, Integer cardNumber, String cardHolder, String cardPolicyOwner, LocalDate cardExpDate) {
        this.insuranceID = insuranceID;
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.cardPolicyOwner = cardPolicyOwner;
        this.cardExpDate = cardExpDate;                             //This needs some more thoughts
    }

    // Getters //
    public String getInsuranceID() {
        return insuranceID;
    }
    public Integer getCardNumber() {
        return cardNumber;
    }
    public String getCardHolder() {
        return cardHolder;
    }
    public String getCardPolicyOwner() {
        return cardPolicyOwner;
    }
    public LocalDate getCardExpDate() {
        return cardExpDate;
    }

    // Setters //
    public void setInsuranceID(String insuranceID) {
        this.insuranceID = insuranceID;
    }
    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }
    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }
    public void setCardPolicyOwner(String cardPolicyOwner) {
        this.cardPolicyOwner = cardPolicyOwner;
    }
    public void setCardExpDate(LocalDate cardExpDate) {
        this.cardExpDate = cardExpDate;
    }

    // Methods //


    // toString //
    @Override
    public String toString() {
        return "InsuranceCard{" +
                "insuranceID='" + insuranceID + '\'' +
                ", cardNumber=" + cardNumber +
                ", cardHolder='" + cardHolder + '\'' +
                ", cardPolicyOwner='" + cardPolicyOwner + '\'' +
                ", cardExpDate=" + cardExpDate +
                '}';
    }
}
