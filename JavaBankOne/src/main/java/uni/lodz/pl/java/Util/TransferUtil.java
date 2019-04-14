package uni.lodz.pl.java.Util;

import uni.lodz.pl.java.Config.Config;
import uni.lodz.pl.java.beans.*;

import java.nio.channels.AcceptPendingException;
import java.sql.SQLOutput;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TransferUtil {

    public boolean DoTransfer(TypeOfTransfer typeOfTransfer, Account senderAccount,Account recieverAccount,Double amountOfMoney) {
        if(typeOfTransfer==TypeOfTransfer.WIRE)
        {
           if(DoInternationalTransfer(typeOfTransfer,senderAccount,recieverAccount,amountOfMoney))
            {
                DebitTransfer(senderAccount,recieverAccount,amountOfMoney);
              return true;
            }
           else
            {
            return false;

            }

        }
        else {
           if(typeOfTransfer==TypeOfTransfer.CREDIT)
            {
                if(DoRegularTransfer(typeOfTransfer,senderAccount,recieverAccount,amountOfMoney))
                    {
                        DebitTransfer(senderAccount,recieverAccount,amountOfMoney);
                    return true;

                    }
                 else
                    {
                    return false;
                    }

            }
}


        System.out.println("We cannot do any transfer with Save Account");
        return  false;
    }
    private static void DebitTransfer(Account senderAccount,Account recieverAccount,Double amountOfMoney){
        recieverAccount.setAmount(recieverAccount.getAmount()+amountOfMoney);
        SaveTransferDate(senderAccount,recieverAccount,TypeOfTransfer.DEBIT,amountOfMoney);
    }
    private static boolean DoInternationalTransfer(TypeOfTransfer typeOfTransfer, Account senderAccount,Account recieverAccount,Double amountOfMoney){
        if(CheckInternationType(senderAccount))
        {
            senderAccount.setAmount(senderAccount.getAmount()-amountOfMoney);
            System.out.println("We do Transfer");
            SaveTransferDate(senderAccount,recieverAccount,typeOfTransfer,amountOfMoney);
            return true;
 }
        else
        {
            System.out.println("We cannot do this");
            return  false;

        }
    }
    private static boolean DoRegularTransfer(TypeOfTransfer typeOfTransfer, Account senderAccount,Account recieverAccount,Double amountOfMoney){
        if(CheckInternationType(senderAccount))
        {
            senderAccount.setAmount(senderAccount.getAmount()-amountOfMoney);

            SaveTransferDate(senderAccount,recieverAccount,typeOfTransfer,amountOfMoney);
            System.out.println("We do Transfer");
            return true;


        }
        else
        {
            System.out.println("We cannot do this");
            return  false;

        }
    }
    public static boolean CheckInternationType(Account account) {
        if (account.getTypeOfAccount() == TypeOfAccount.International) {
             return  true;
        }
        else {

            return false;
        }
    }
    public static boolean CheckRegularType(Account account) {
        if (account.getTypeOfAccount() == TypeOfAccount.Regular) {
            return  true;
        }
        else {

            return false;
        }
    }
    public static  boolean SaveTransferDate(Account senderAccount,Account recieverAccount,TypeOfTransfer typeOfTransfer,Double amountOfMoney) {
         if(IncreaseNumberTransferByType(senderAccount,recieverAccount,typeOfTransfer)) {
             Transaction transaction = new Transaction();
             transaction.setAmountOfMoney(amountOfMoney);
             transaction.setDateOfTransaction(GetTime());
             transaction.setRecivierAccount(recieverAccount);
             transaction.setSenderAccount(senderAccount);
             transaction.setTypeOfTransfer(typeOfTransfer);
             Config.AddLitsOfTransactions(transaction);
               return true;
         }
               return false;
    }
    public static boolean IncreaseNumberTransferByType(Account senderAccount,Account recieverAccount,TypeOfTransfer typeOfTransfer) {
        if(typeOfTransfer==TypeOfTransfer.WIRE)
        {
              senderAccount.getCustomerAccount().setAmountOfInternationalTransfer(GetInternationNumberOfTransfer(senderAccount));

           return  true;
        }
        else {
            if (typeOfTransfer == TypeOfTransfer.CREDIT) {
                senderAccount.setNumberOfAccount(GetRegularNumberOfTransfer(senderAccount));



                return  true;

            } else {
                return false;
            }
        }

    }
    public static int GetInternationNumberOfTransfer(Account account) {


        int i = account.getCustomerAccount().getAmountOfInternationalTransfer() + 1;

        return i;
    }
    public static long GetRegularNumberOfTransfer(Account account) {


           long i =account.getNumberOfAccount();
           return i;

    }
    public static Date GetTime(){
        Calendar c=Calendar.getInstance();
        return c.getTime();

    }
    public  static void ShowTransferByPerson(Customer customer){
          for (Transaction transaction:Config.getLitsOfTransactions())
          {
              if(transaction.getRecivierAccount().getCustomerAccount()==customer ||
                      transaction.getSenderAccount().getCustomerAccount() ==customer)
              {
                  System.out.println("Show Customer");

              }


          }

    }
    public  static void ShowTransferByTransferType(TypeOfTransfer typeOfTransfer){
        for (Transaction transaction:Config.getLitsOfTransactions())
        {
            if(transaction.getTypeOfTransfer()==typeOfTransfer )
            {
                System.out.println("Show Customer");

            }


        }

    }
    public  static void ShowTransferByDate(Date date){
           Date dateNow=GetTime();
        for (Transaction transaction:Config.getLitsOfTransactions())
        {
            if(transaction.getDateOfTransaction().after(date) && transaction.getDateOfTransaction().before(dateNow))
            {
                System.out.println("Show Customer");

            }


        }

    }

}