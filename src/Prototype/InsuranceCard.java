package Prototype;
/**
 * @author <Tran Tuan Minh - s3804812>
 */


import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class InsuranceCard implements DataHandler{
    protected ArrayList<InsuranceCard> insuranceCards = new ArrayList<>();
    //Attributes//
    protected String insuranceID;
    protected Integer cardNumber;
    protected String cardHolder;
    protected String cardPolicyOwner;
    protected LocalDate cardExpDate;


    // Constructor //
    public InsuranceCard() {
        this.insuranceID = "CRD0000000000";
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

    // Data Handler //
    @Override
    public void readData() throws FileNotFoundException {
        Scanner insuranceCardScanner = new Scanner(new File("src/Data/InsuranceCards.csv"));
        insuranceCardScanner.useDelimiter(",|\n");

        while (insuranceCardScanner.hasNext()){
            String insuranceID = insuranceCardScanner.next();
            Integer cardNumber = insuranceCardScanner.nextInt();
            String cardHolder = insuranceCardScanner.next();
            String cardPolicyOwner = insuranceCardScanner.next();
            LocalDate cardExpDate = LocalDate.parse(insuranceCardScanner.next());
            addInsuranceCardToList(insuranceID, cardNumber, cardHolder, cardPolicyOwner, cardExpDate);
        }
    }

    @Override
    public void writeData() throws IOException {
        FileWriter insuranceCardFile = new FileWriter("src/Data/InsuranceCards.csv", false);
        PrintWriter out0 = new PrintWriter(insuranceCardFile);

        for (InsuranceCard insuranceCard : insuranceCards){
            out0.printf("%s,%d,%s,%s,%s\n", insuranceCard.getInsuranceID(), insuranceCard.getCardNumber(), insuranceCard.getCardHolder(), insuranceCard.getCardPolicyOwner(), insuranceCard.getCardExpDate());
        }
        out0.close();
    }

    // Methods //
    public void addInsuranceCardToList(String insuranceID, Integer cardNumber, String cardHolder, String cardPolicyOwner, LocalDate cardExpDate) {
        InsuranceCard insuranceCard = new InsuranceCard(insuranceID, cardNumber, cardHolder, cardPolicyOwner, cardExpDate);
        insuranceCards.add(insuranceCard);
    }

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
