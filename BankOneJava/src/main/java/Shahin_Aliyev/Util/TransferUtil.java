package Shahin_Aliyev.Util;

import Shahin_Aliyev.Config.Config;
import Shahin_Aliyev.beans.*;

import java.time.ZonedDateTime;

public class TransferUtil
{
    public boolean doTransfer(Account accountSender,Account recieverAccount,double amountOfTransfer,TypeOfTransfer typeOfTransfer){

        if(typeOfTransfer==TypeOfTransfer.CREDIT)
        {
        return CreditTrasnfer(accountSender,recieverAccount,amountOfTransfer);


        }
        else
        {
          if(typeOfTransfer==TypeOfTransfer.WIRE)
          {

         return  WireTransfer(accountSender,recieverAccount,amountOfTransfer);
          }

        }

      return false;
    }
    private boolean WireTransfer(Account senderAccount,Account recieverAccount,double amountOfTransfer){
         if(senderAccount.getTypeOfAccount()==TypeOfAccount.International)
         {
           return checkAmountOfBalance(senderAccount,recieverAccount,amountOfTransfer,TypeOfTransfer.CREDIT);

         }
         else {

             return false;
         }
    }
    private boolean CreditTrasnfer(Account senderAccount,Account recieverAccount,double amountOfTransfer) {
      if(senderAccount.getTypeOfAccount()==TypeOfAccount.Regular)
      {
          return RegularCreditTrasnfer(senderAccount,recieverAccount,amountOfTransfer);
      }
      else
      {
          if(senderAccount.getTypeOfAccount()==TypeOfAccount.Saving)
          {
             return SavingCreditTrasnfer(senderAccount,recieverAccount,amountOfTransfer);
          }

      }


      return  false;
    }
    private boolean RegularCreditTrasnfer(Account senderAccount,Account recieverAccount,double amountOfTransfer){

      return  checkAmountOfBalance(senderAccount,recieverAccount,amountOfTransfer,TypeOfTransfer.CREDIT);
    }
    private boolean SavingCreditTrasnfer(Account senderAccount,Account recieverAccount,double amountOfTransfer){

        if(recieverAccount.getTypeOfAccount()==TypeOfAccount.Regular)
        {
            return checkAmountOfBalance(senderAccount,recieverAccount,amountOfTransfer,TypeOfTransfer.CREDIT);
        }
        return false;
    }
    private boolean checkAmountOfBalance(Account senderAccount,Account recieverAccount,double amountOfTransfer,TypeOfTransfer typeOfTransfer) {
       if(senderAccount.getAmount()>=amountOfTransfer)
       {
           return doneTransfer( senderAccount, recieverAccount, amountOfTransfer,typeOfTransfer);

       }

       return false;
   }
    private boolean doneTransfer(Account senderAccount,Account recieverAccount,double amountOfTransfer,TypeOfTransfer typeOfTransfer) {
        if(debitTransfer(recieverAccount,amountOfTransfer))
        {
            senderAccount.setAmount(senderAccount.getAmount()-amountOfTransfer);
            getTransactionNumber(senderAccount);
            saveTrasction(senderAccount,recieverAccount,typeOfTransfer,amountOfTransfer);
            return true;
        }
        return false;
    }
    private static boolean debitTransfer(Account recieverAccount,Double amountOfMoney){
      if(recieverAccount.getTypeOfAccount()==TypeOfAccount.Regular) {
          recieverAccount.setAmount(recieverAccount.getAmount() + amountOfMoney);

          return true;
      }
     else
         return false;
    }
    private static int getTransactionNumber(Account account){
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
    private static void saveTrasction(Account senderAccount,Account recieverAccount,TypeOfTransfer typeOfTransfer,Double money){
         Transaction transaction=createTransaction(senderAccount,recieverAccount,typeOfTransfer,money);
         saveDebitTransfer(recieverAccount,transaction);
         Config.AddSetsOfTransactions(transaction);

    }
    private static Transaction saveDebitTransfer(Account recieverAccount,Transaction transaction) {
        transaction.setTypeOfTransfer(TypeOfTransfer.DEBIT);

        Config.AddSetsOfTransactions(transaction);
        return  transaction;
    }
    private static Transaction createTransaction(Account senderAccount,Account recieverAccount,TypeOfTransfer typeOfTransfer,Double money) {
        Transaction transaction=new Transaction();
        if(typeOfTransfer==TypeOfTransfer.WIRE)
        {
            transaction.setTransactionNumber(senderAccount.getCustomerAccount().getAmountOfInternationalTransfer()+"");

        }
        else
        {
            transaction.setTransactionNumber(senderAccount.getTransactionnumber()+"");
        }

        transaction.setSenderAccount(senderAccount);
        transaction.setRecivierAccount(recieverAccount);
        transaction.setDateOfTransaction(ZonedDateTime.now());
        transaction.setAmountOfMoney(money);

        transaction.setTypeOfTransfer(typeOfTransfer);
        return transaction;
    }


}