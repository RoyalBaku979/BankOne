package BankOne.beans;

public class Percentage {
    private  String AccountNumber;
    private  double percentage=0.01;



    public  String getAccountNumber() {
        return AccountNumber;
    }

    public  void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public  Double getPercentage() {
        return percentage;
    }

    public  void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
