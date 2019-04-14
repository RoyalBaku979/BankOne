package uni.lodz.pl.java.beans;

import java.util.Date;

public class Transaction {
    private  Account RecivierAccount;
    private Account senderAccount;
    private TypeOfTransfer typeOfTransfer;
    private Date   dateOfTransaction;
    private double amountOfMoney;


    public Account getRecivierAccount() {
        return RecivierAccount;
    }

    public Transaction() {

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

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public double getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }
}
