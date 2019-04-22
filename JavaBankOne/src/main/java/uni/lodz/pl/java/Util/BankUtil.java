package uni.lodz.pl.java.Util;

import uni.lodz.pl.java.Config.Config;
import uni.lodz.pl.java.beans.Customer;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class BankUtil {
public boolean CreateCustomer(String name,String surname,String email,String password,String dateOfBirht,Date dateOfJoinBank,int amountOfInternationTransfer){
    Customer customer=new Customer();
    if(name.trim().isEmpty() ||surname.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()
   || dateOfBirht.trim().isEmpty())
    {

        return false;
    }
    else {
        customer.setCustomerNumber(genarateCustomerNumber());
        customer.setDateOfBirth(dateOfBirht);
        customer.setDateOfJoinedBank(Calendar.getInstance().getTime());
        customer.setEmail(email);
        customer.setName(name);
        customer.setSurname(surname);
        customer.setPassword(password);
        customer.setAmountOfInternationalTransfer(0);
        return true;
       }

}
public static String genarateCustomerNumber() {
   String number=Config.getListofCustomer().size()+"";
       int size= number.length();
       while(size<5)
       {
           number="0"+number;
           size= number.length();
       }

    return number;

}
    public boolean reuglarCreditTrasnfer(Account accountSender,Account accountReciever,double amountOfTransfer) {


        if(accountSender.getAmount()<amountOfTransfer)
        {
            return false;
        }
        else
        {
            accountSender.setAmount(accountSender.getAmount()-amountOfTransfer);
            accountReciever.setAmount(accountReciever.getAmount()+amountOfTransfer);
            return true;
        }





    }
    public boolean regularDebitTrasnfer(Account accountSender,Account accountReciever,double amountOfTransfer) {


        if(accountSender.getAmount()<amountOfTransfer)
        {
            return false;
        }
        else
        {
            accountSender.setAmount(accountSender.getAmount()-amountOfTransfer);
            accountReciever.setAmount(accountReciever.getAmount()+amountOfTransfer);
            return true;
        }





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