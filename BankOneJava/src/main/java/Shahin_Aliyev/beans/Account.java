package Shahin_Aliyev.beans;


import Shahin_Aliyev.Config.Config;

public  class Account {

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





    @Override
    public String toString() {
    String account=null;
    String ibannumber="";
    if(getTypeOfAccount()==TypeOfAccount.International)
    {
        for (IbanClass iban : Config.getListofIbans()) {
            if(iban.getAccountNumber().equals(numberOfAccount))
            {
                ibannumber=iban.getIBAN();
                break;

            }
        }
        account="Account number"+getNumberOfAccount()+";Account Type:"+getTypeOfAccount()+";Iban:"+ibannumber+";Customer"+getCustomerAccount();
    }
    else
    {
        account="Account number"+getNumberOfAccount()+";Account Type:"+getTypeOfAccount()+";Customer:"+getCustomerAccount();
    }

        return account;
    }
    public static Account getInstance()
    {

        return new Account();
    }
}
