package Shahin_Aliyev.beans;

public class Percentage {
    private static String AccountNumber;
    private static double percentage=0.01;

    public static String getAccountNumber() {
        return AccountNumber;
    }

    public static void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public static double getPercentage() {
        return percentage;
    }

    public static void setPercentage(double percentage) {
        Percentage.percentage = percentage;
    }
}
