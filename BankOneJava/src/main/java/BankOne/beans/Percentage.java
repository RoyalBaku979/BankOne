package BankOne.beans;

import java.time.ZonedDateTime;

public class Percentage {
    private  String AccountNumber;
    private  double percentage=0.01;
    private ZonedDateTime InterestRateTime;



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

    public ZonedDateTime getInterestRateTime() {
        return InterestRateTime;
    }

    public void setInterestRateTime(ZonedDateTime interestRateTime) {
        InterestRateTime = interestRateTime;
    }
}
