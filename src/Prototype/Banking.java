package Prototype;
/**
 * @author <Tran Tuan Minh - s3804812>
 */


public class Banking {
    protected String bankName;
    protected String customerName;
    protected String accountNumber;

    // Constructor //
    public Banking(String bankName, String customerName, String accountNumber) {
        this.bankName = bankName;
        this.customerName = customerName;
        this.accountNumber = accountNumber;
    }

    // Getters //
    public String getBankName() {
        return bankName;
    }
    public String getCustomerName() {
        return customerName;
    }
    public String getAccountNumber() {
        return accountNumber;
    }

    // Setters //
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    // Methods //
}
