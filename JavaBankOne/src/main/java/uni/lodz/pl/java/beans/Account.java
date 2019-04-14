package uni.lodz.pl.java.beans;

public class Account {
    private static long numberOfAccount;
    private String nameOfAccount;
    private  double amount;
    private  Customer customerAccount;
    private int amountOfTransfer;
    private TypeOfAccount typeOfAccount;

    public Account(long numberOfAccount, String nameOfAccount, double amount, Customer customerAccount, int amountOfTransfer, TypeOfAccount typeOfAccount) {
        this.numberOfAccount = numberOfAccount;
        this.nameOfAccount = nameOfAccount;
        this.amount = amount;
        this.customerAccount = customerAccount;
        this.amountOfTransfer = amountOfTransfer;
        this.typeOfAccount = typeOfAccount;
    }

    public Account() {
    }
    public Customer getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(Customer customerAccount) {
        this.customerAccount = customerAccount;
    }
    public long getNumberOfAccount() {
        return numberOfAccount;
    }

    public void setNumberOfAccount(long numberOfAccount) {
        this.numberOfAccount = numberOfAccount;
    }

    public String getNameOfAccount() {
        return nameOfAccount;
    }

    public void setNameOfAccount(String nameOfAccount) {
        this.nameOfAccount = nameOfAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TypeOfAccount getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(TypeOfAccount typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public int getAmountOfTransfer() {
        return amountOfTransfer;
    }

    public void setAmountOfTransfer(int amountOfTransfer) {
        this.amountOfTransfer = amountOfTransfer;
    }
}
