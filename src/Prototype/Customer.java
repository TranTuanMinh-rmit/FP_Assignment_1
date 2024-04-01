package Prototype;
/**
 * @author <Tran Tuan Minh - s3804812>
 */

public abstract class Customer {
    protected String customerID;
    protected String customerFullName;
    protected String customerInsuranceCard;
    protected String customerClaims;

    // Constructor //
    public Customer() {
        customerID = "C000";
        customerFullName = "Default Name";
        customerInsuranceCard = "CRD0000000000";
        customerClaims = "None";
    }

    public Customer(String customerID, String customerFullName, String customerInsuranceCard, String customerClaims) {
        this.customerID = customerID;
        this.customerFullName = customerFullName;
        this.customerInsuranceCard = customerInsuranceCard;
        this.customerClaims = customerClaims;
    }

    // Getters //
    public String getCustomerID() {
        return customerID;
    }
    public String getCustomerFullName() {
        return customerFullName;
    }
    public String getCustomerInsuranceCard() {
        return customerInsuranceCard;
    }
    public String getCustomerClaims() {
        return customerClaims;
    }

    // Setters //
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }
    public void setCustomerInsuranceCard(String customerInsuranceCard) {
        this.customerInsuranceCard = customerInsuranceCard;
    }
    public void setCustomerClaims(String customerClaims) {
        this.customerClaims = customerClaims;
    }
}
