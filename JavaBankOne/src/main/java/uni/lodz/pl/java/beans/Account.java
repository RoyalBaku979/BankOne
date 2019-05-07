package uni.lodz.pl.java.beans;

import uni.lodz.pl.java.Config.Config;

public final class Account {

    private static String numberOfAccount;
    //private static String IBAN;
    private  double amount;
    private  Customer customerAccount;
    private int transferAmount;
    private TypeOfAccount typeOfAccount;
   // private  double interestRate=0;
    private boolean approveByemployer=false;
    private static Account instance;
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

//   // public static String getIBAN() {
//        return IBAN;
//    }
//
//    public static void setIBAN(String IBAN) {
//        Account.IBAN = IBAN;
//    }

//    public double getInterestRate() {
//        return interestRate;
//    }
//
//    public void setInterestRate(double interestRate) {
//        this.interestRate = interestRate;
//    }

    public boolean isApproveByemployer() {
        return approveByemployer;
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

    public void setApproveByemployer(boolean approveByemployer) {
        this.approveByemployer = approveByemployer;
    }
    public static Account getInstance()
    {
        if (instance == null)
        {
            //synchronized block to remove overhead
            synchronized (Account.class)
            {
                if(instance==null)
                {
                    // if instance is null, initialize
                    instance = new Account();
                }

            }
        }
        return instance;
    }
}
