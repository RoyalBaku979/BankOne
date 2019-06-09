package BankOne.beans;


import BankOne.Config.Config;

public  class Account {
    Config config;
    private static String numberOfAccount;

    private  double amount;
    private  Customer customerAccount;
    private int transactionnumber;
    private TypeOfAccount typeOfAccount;

private Account(){}
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

    public int getTransactionnumber() {
        return transactionnumber;
    }

    public void setTransactionnumber(int transferAmount) {
        this.transactionnumber = transferAmount;
    }

    public TypeOfAccount getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(TypeOfAccount typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public static Account getInstance()
    {

        return new Account();
    }
}
