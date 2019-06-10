package BankOne.Util;

import BankOne.beans.Account;
import BankOne.beans.Transaction;
import BankOne.beans.TypeOfAccount;
import BankOne.beans.TypeOfTransfer;
import BankOne.beans.*;

import java.time.ZonedDateTime;

public class TransferUtil
{

    public boolean doTransfer(Account accountSender, Account recieverAccount, double amountOfTransfer, TypeOfTransfer typeOfTransfer){

        if(typeOfTransfer==TypeOfTransfer.CREDIT)
        {
        return creditTrasnfer(accountSender,recieverAccount,amountOfTransfer);


        }
        else
        {
          if(typeOfTransfer==TypeOfTransfer.WIRE)
          {

         return  wireTransfer(accountSender,recieverAccount,amountOfTransfer);
          }

        }

      return false;
    }
    public boolean wireTransfer(Account senderAccount, Account recieverAccount, double amountOfTransfer){
         if(senderAccount.getTypeOfAccount()== TypeOfAccount.International)
         {
           return checkAmountOfBalance(senderAccount,recieverAccount,amountOfTransfer,TypeOfTransfer.WIRE);

         }
         else {

             return false;
         }
    }
    public boolean creditTrasnfer(Account senderAccount, Account recieverAccount, double amountOfTransfer) {
      if(senderAccount.getTypeOfAccount()==TypeOfAccount.Regular)
      {
          return regularCreditTrasnfer(senderAccount,recieverAccount,amountOfTransfer);
      }
      else
      {
          if(senderAccount.getTypeOfAccount()==TypeOfAccount.Saving)
          {
             return savingCreditTrasnfer(senderAccount,recieverAccount,amountOfTransfer);
          }

      }


      return  false;
    }
    public boolean regularCreditTrasnfer(Account senderAccount, Account recieverAccount, double amountOfTransfer){

      return  checkAmountOfBalance(senderAccount,recieverAccount,amountOfTransfer,TypeOfTransfer.CREDIT);
    }
    public boolean savingCreditTrasnfer(Account senderAccount, Account recieverAccount, double amountOfTransfer){

        if(recieverAccount.getTypeOfAccount()==TypeOfAccount.Regular)
        {
            return checkAmountOfBalance(senderAccount,recieverAccount,amountOfTransfer,TypeOfTransfer.CREDIT);
        }
        return false;
    }
    public boolean checkAmountOfBalance(Account senderAccount,Account recieverAccount,double amountOfTransfer,TypeOfTransfer typeOfTransfer) {
       if(senderAccount.getAmount()>=amountOfTransfer)
       {
           return doneTransfer( senderAccount, recieverAccount, amountOfTransfer,typeOfTransfer);

       }

       return false;
   }
    public boolean doneTransfer(Account senderAccount,Account recieverAccount,double amountOfTransfer,TypeOfTransfer typeOfTransfer) {
        if(debitTransfer(recieverAccount,amountOfTransfer))
        {
            senderAccount.setAmount(senderAccount.getAmount()-amountOfTransfer);
            getTransactionNumber(senderAccount);
            saveTrasction(senderAccount,recieverAccount,typeOfTransfer,amountOfTransfer);
            return true;
        }
        return false;
    }
    public  boolean debitTransfer(Account recieverAccount,Double amountOfMoney){
      if(recieverAccount.getTypeOfAccount()==TypeOfAccount.Regular) {
          recieverAccount.setAmount(recieverAccount.getAmount() + amountOfMoney);

          return true;
      }
     else
         return false;
    }
    public  int getTransactionNumber(Account account){
        if(account.getTypeOfAccount()==TypeOfAccount.International)
        {
          int i= account.getCustomerAccount().getAmountOfInternationalTransfer()+1 ;
          account.getCustomerAccount().setAmountOfInternationalTransfer(i);
          return i;
        }
        else
        {
            int i=account.getTransactionnumber()+1;
            account.setTransactionnumber(i);
            return i;

        }

    }
    public  void saveTrasction(Account senderAccount,Account recieverAccount,TypeOfTransfer typeOfTransfer,Double money){
         Transaction transaction=createTransaction(senderAccount,recieverAccount,typeOfTransfer,money);
         transaction.setTypeOfTransfer(TypeOfTransfer.DEBIT);
         saveDebitTransfer(recieverAccount,transaction);


    }
    public  Transaction saveDebitTransfer(Account recieverAccount,Transaction transaction) {


        if(transaction.getTypeOfTransfer()==TypeOfTransfer.DEBIT) {

            return transaction;
        }
        else
        {
            return  null;
        }
    }
    public  Transaction createTransaction(Account senderAccount,Account recieverAccount,TypeOfTransfer typeOfTransfer,Double money) {
        Transaction transaction=new Transaction();
        if(recieverAccount.getTypeOfAccount()==TypeOfAccount.Regular) {
            if (typeOfTransfer == TypeOfTransfer.WIRE) {
                transaction.setTransactionNumber(senderAccount.getCustomerAccount().getAmountOfInternationalTransfer() + "");

            } else {
                transaction.setTransactionNumber(senderAccount.getTransactionnumber() + "");
            }

            transaction.setSenderAccount(senderAccount);
            transaction.setRecivierAccount(recieverAccount);
            transaction.setDateOfTransaction(ZonedDateTime.now());
            transaction.setAmountOfMoney(money);
            transaction.setTypeOfTransfer(typeOfTransfer);
            return transaction;
        }
        else
        {
            return null;
        }
    }


}