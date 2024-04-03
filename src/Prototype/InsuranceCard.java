package Prototype;
/**
 * @author <Tran Tuan Minh - s3804812>
 */


import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class InsuranceCard implements DataHandler, ClaimProcessManager{
    ArrayList<InsuranceCard> insuranceCards = new ArrayList<>();
    //Attributes//
    protected String insuranceCardID;
    protected String cardHolder;
    protected String cardPolicyOwner;
    protected String cardExpDate;


    // Constructor //
    public InsuranceCard() {
        this.insuranceCardID = "CRD-0000000000";
        this.cardHolder = "";
        this.cardPolicyOwner = "";
        this.cardExpDate = "2020-01-01";                       //This needs some more thoughts
    }

    public InsuranceCard(String insuranceCardID, String cardHolder, String cardPolicyOwner, String cardExpDate) {
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
    public String getCardExpDate() {
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
    public void setCardExpDate(String cardExpDate) {
        this.cardExpDate = cardExpDate;
    }

    // Data Handler //
    @Override
    public void readData() throws FileNotFoundException {
        Scanner insuranceCardScanner = new Scanner(new File("src/Datafiles/InsuranceCards.csv"));
        insuranceCardScanner.useDelimiter(",|\n");

        while (insuranceCardScanner.hasNext()){
            String insuranceCardID = insuranceCardScanner.next();
            String cardHolder = insuranceCardScanner.next();
            String cardPolicyOwner = insuranceCardScanner.next();
            String cardExpDate = insuranceCardScanner.next();
            addInsuranceCardToList(insuranceCardID, cardHolder, cardPolicyOwner, cardExpDate);
        }
    }

    @Override
    public void writeData() throws IOException {
        FileWriter insuranceCardFile = new FileWriter("src/Datafiles/InsuranceCards.csv", false);
        PrintWriter out0 = new PrintWriter(insuranceCardFile);

        for (InsuranceCard insuranceCard : insuranceCards){
            out0.printf("%s,%s,%s,%s\n", insuranceCard.getInsuranceID(), insuranceCard.getCardHolder(), insuranceCard.getCardPolicyOwner(), insuranceCard.getCardExpDate());
        }
        out0.close();
    }

    // Methods //
    public void addInsuranceCardToList(String insuranceCardID, String cardHolder, String cardPolicyOwner, String cardExpDate) {
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
        System.out.println(String.format("%-20s %-25s %-20s %-20s", "Insurance Card ID", "Card Holder", "Policy Owner", "Expiry Date"));
        for (InsuranceCard insuranceCard2 : insuranceCards){
            //System.out.println(insuranceCard.toString());
            //System.out.println(insuranceCard.getInsuranceID() + " " + insuranceCard.getCardHolder() + " " + insuranceCard.getCardPolicyOwner() + " " + insuranceCard.getCardExpDate());
            System.out.println(String.format("%-20s %-25s %-20s %-15s", insuranceCard2.getInsuranceID(), insuranceCard2.getCardHolder(), insuranceCard2.getCardPolicyOwner(), "2029-12-31"));
        }
    }

    // toString //
    @Override
    public String toString() {
        return String.format("InsuranceCard[ID=%s, Holder=%s, PolicyOwner=%s, ExpiryDate=%s]", insuranceCardID, cardHolder, cardPolicyOwner, cardExpDate);
    }
}
