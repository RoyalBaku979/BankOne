package Shahin_Aliyev.beans;

public class IbanClass {
    private static String AccountNumber;
    private static String IBAN;
    public static String getAccountNumber() {
        return AccountNumber;
    }

    public static void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public static String getIBAN() {
        return IBAN;
    }

    public static void setIBAN(String IBAN) {
        IbanClass.IBAN = IBAN;
    }



}
