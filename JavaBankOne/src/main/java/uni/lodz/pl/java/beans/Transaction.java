package uni.lodz.pl.java.beans;

import java.time.ZonedDateTime;
import java.util.Date;

public class Transaction {
    private String transactionNumber;
    private  Account RecivierAccount;
    private Account senderAccount;
    private TypeOfTransfer typeOfTransfer;
    private ZonedDateTime dateOfTransaction;
    private double amountOfMoney;


    public Account getRecivierAccount() {
        return RecivierAccount;
    }

    public Transaction() {

    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public void setRecivierAccount(Account recivierAccount) {
        RecivierAccount = recivierAccount;
    }

    public Account getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    public TypeOfTransfer getTypeOfTransfer() {
        return typeOfTransfer;
    }

    public void setTypeOfTransfer(TypeOfTransfer typeOfTransfer) {
        this.typeOfTransfer = typeOfTransfer;
    }

    public ZonedDateTime getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(ZonedDateTime dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public double getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }
}
