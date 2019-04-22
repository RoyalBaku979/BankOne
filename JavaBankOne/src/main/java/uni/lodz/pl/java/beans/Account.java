package uni.lodz.pl.java.beans;

public class Account {

    private static String numberOfAccount;
   private static String IBAN;
    private  double amount;
    private  Customer customerAccount;
    private int transferAmount;
    private TypeOfAccount typeOfAccount;
    private  double interestRate=0;
    private boolean approveByemployer=false;


    public static String getNumberOfAccount() {
        return numberOfAccount;
    }

    public static void setNumberOfAccount(String numberOfAccount) {
        Account.numberOfAccount = numberOfAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Customer getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(Customer customerAccount) {
        this.customerAccount = customerAccount;
    }

    public int getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(int transferAmount) {
        this.transferAmount = transferAmount;
    }

    public TypeOfAccount getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(TypeOfAccount typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public static String getIBAN() {
        return IBAN;
    }

    public static void setIBAN(String IBAN) {
        Account.IBAN = IBAN;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public boolean isApproveByemployer() {
        return approveByemployer;
    }

    public void setApproveByemployer(boolean approveByemployer) {
        this.approveByemployer = approveByemployer;
    }
}
