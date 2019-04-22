package uni.lodz.pl.java.Util;

import uni.lodz.pl.java.Config.Config;
import uni.lodz.pl.java.beans.*;

import java.nio.channels.AcceptPendingException;
import java.sql.SQLOutput;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
           return checkAmountOfBalance(senderAccount,recieverAccount,amountOfTransfer);

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

      return  checkAmountOfBalance(senderAccount,recieverAccount,amountOfTransfer);
    }
    private boolean SavingCreditTrasnfer(Account senderAccount,Account recieverAccount,double amountOfTransfer){

        if(recieverAccount.getTypeOfAccount()==TypeOfAccount.Regular)
        {
            return checkAmountOfBalance(senderAccount,recieverAccount,amountOfTransfer);
        }
        return false;
    }
    private boolean checkAmountOfBalance(Account senderAccount,Account recieverAccount,double amountOfTransfer) {
       if(senderAccount.getAmount()>=amountOfTransfer)
       {
           return doneTransfer( senderAccount, recieverAccount, amountOfTransfer);

       }

       return false;
   }
    private boolean doneTransfer(Account senderAccount,Account recieverAccount,double amountOfTransfer) {
        if(debitTransfer(senderAccount,recieverAccount,amountOfTransfer))
        {
            senderAccount.setAmount(senderAccount.getAmount()-amountOfTransfer);
            getTransactionNumber(senderAccount);
            return true;
        }
        return false;
    }
    private static boolean debitTransfer(Account senderAccount,Account recieverAccount,Double amountOfMoney){
      if(recieverAccount.getTypeOfAccount()==TypeOfAccount.Regular) {
          recieverAccount.setAmount(recieverAccount.getAmount() + amountOfMoney);

          return true;
      }
     else
         return false;
    }
    private static void getTransactionNumber(Account account){
        if(account.getTypeOfAccount()==TypeOfAccount.International)
        {
          int i= account.getCustomerAccount().getAmountOfInternationalTransfer() ;
          account.getCustomerAccount().setAmountOfInternationalTransfer(i+1);
        }
        else
        {
            int i=account.getTransferAmount();
            account.setTransferAmount(i+1);

        }

    }
    private static boolean checkAccountBelongBank(Account recivierAccount){
        for (Account account:Config.getListOfAccount()) {
            if(account.equals(recivierAccount))
            {
                return true;
            }

        }
        return false;

    }
    public TypeOfAccount checkSenderAccount(Account senderAccount) {
        if(senderAccount==null)
            return null;
        TypeOfAccount typeOfAccount=senderAccount.getTypeOfAccount();
        switch (typeOfAccount)
        {

            case International:return TypeOfAccount.International;
            case Saving:return TypeOfAccount.Saving;
            case Regular:return TypeOfAccount.Regular;
        }
        return null;

    }
    public TypeOfAccount checkRecieverAccount(Account recivierAccount) {
        if(recivierAccount==null)
            return null;
        TypeOfAccount typeOfAccount=recivierAccount.getTypeOfAccount();
        switch (typeOfAccount)
        {

            case International:return TypeOfAccount.International;
            case Saving:return TypeOfAccount.Saving;
            case Regular:return TypeOfAccount.Regular;
        }
        return null;

    }
}