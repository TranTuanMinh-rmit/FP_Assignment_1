package Prototype;
/**
 * @author <Tran Tuan Minh - s3804812>
 */


import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class InsuranceCard implements DataHandler, ClaimProcessManager{
    protected ArrayList<InsuranceCard> insuranceCards = new ArrayList<>();
    //Attributes//
    protected String insuranceCardID;
    protected String cardHolder;
    protected String cardPolicyOwner;
    protected LocalDate cardExpDate;


    // Constructor //
    public InsuranceCard() {
        this.insuranceCardID = "CRD-0000000000";
        this.cardHolder = "";
        this.cardPolicyOwner = "";
        this.cardExpDate = LocalDate.now();                         //This needs some more thoughts
    }

    public InsuranceCard(String insuranceCardID, String cardHolder, String cardPolicyOwner, LocalDate cardExpDate) {
        this.insuranceCardID = insuranceCardID;
        this.cardHolder = cardHolder;
        this.cardPolicyOwner = cardPolicyOwner;
        this.cardExpDate = cardExpDate;                             //This needs some more thoughts
    }

    // Getters //
    public String getInsuranceID() {
        return insuranceCardID;
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
    public void setInsuranceID(String insuranceCardID) {
        this.insuranceCardID = insuranceCardID;
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
            String insuranceCardID = insuranceCardScanner.next();
            String cardHolder = insuranceCardScanner.next();
            String cardPolicyOwner = insuranceCardScanner.next();
            LocalDate cardExpDate = LocalDate.parse(insuranceCardScanner.next());
            addInsuranceCardToList(insuranceCardID, cardHolder, cardPolicyOwner, cardExpDate);
        }
    }

    @Override
    public void writeData() throws IOException {
        FileWriter insuranceCardFile = new FileWriter("src/Data/InsuranceCards.csv", false);
        PrintWriter out0 = new PrintWriter(insuranceCardFile);

        for (InsuranceCard insuranceCard : insuranceCards){
            out0.printf("%s,%s,%s,%s\n", insuranceCard.getInsuranceID(), insuranceCard.getCardHolder(), insuranceCard.getCardPolicyOwner(), insuranceCard.getCardExpDate());
        }
        out0.close();
    }

    // Methods //
    public void addInsuranceCardToList(String insuranceCardID, String cardHolder, String cardPolicyOwner, LocalDate cardExpDate) {
        InsuranceCard insuranceCard = new InsuranceCard(insuranceCardID, cardHolder, cardPolicyOwner, cardExpDate);
        insuranceCards.add(insuranceCard);
    }

    @Override
    public void add() throws InterruptedException, IOException {

    }

    @Override
    public void update() throws IOException {

    }

    @Override
    public void delete() {

    }

    @Override
    public void getOne() {

    }

    @Override
    public void getAll() {
        System.out.printf("%-20s %-20s %-20s %-20s\n", "Insurance Card ID", "Card Holder", "Policy Owner", "Expiry Date");
        for (InsuranceCard insuranceCard : insuranceCards){
            System.out.println(insuranceCard.toString());
        }
    }

    // toString //
    @Override
    public String toString() {
        return insuranceCardID + cardHolder + cardPolicyOwner + cardExpDate;
    }
}
