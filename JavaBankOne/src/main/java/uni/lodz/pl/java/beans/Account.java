package uni.lodz.pl.java.beans;

public class Account {
    private static long numberOfAccount;
    private String nameOfAccount;
    private  double amount;
    private  Customer customerAccount;
    private Double amountOfMoney;
    private TypeOfAccount typeOfAccount;
    private boolean acceptedAccount=false;



    public Account() {
    }

    public boolean isAcceptedAccount() {
        return acceptedAccount;
    }

    public void setAcceptedAccount(boolean acceptedAccount) {
        this.acceptedAccount = acceptedAccount;
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

    public Double getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(Double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }
}
