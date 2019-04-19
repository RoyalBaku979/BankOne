package uni.lodz.pl.java.beans;

public class Account {

    private static String numberOfAccount;
   private static String IBAN;
    private  double amount;
    private  Customer customerAccount;
    private Double amountOfMoney;
    private TypeOfAccount typeOfAccount;



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

    public Double getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(Double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
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
}
