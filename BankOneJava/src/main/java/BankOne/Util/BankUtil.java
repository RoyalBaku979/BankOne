package BankOne.Util;

import BankOne.beans.Account;
import BankOne.beans.Customer;
import BankOne.Dao.impl.AccountImplDao;
import BankOne.Dao.impl.CustomerImplDao;
import BankOne.Dao.impl.PercentageImplDao;
import BankOne.beans.Percentage;
import BankOne.beans.TypeOfAccount;

import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BankUtil {
    CustomerImplDao customerImplDao;
    AccountImplDao accountImplDao;
    PercentageImplDao percentageImplDao;
public boolean createCustomer(String name, String surname, String email, String password, String dateOfBirht, ZonedDateTime dateOfJoinBank, int amountOfInternationTransfer){
    Customer customer=Customer.getInstance();
    if(name.trim().isEmpty() ||surname.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()
   || dateOfBirht.trim().isEmpty())
    {

        return false;
    }
    else {

        customer.setDateOfBirth(dateOfBirht);
        customer.setDateOfJoinedBank(ZonedDateTime.now());
        customer.setEmail(email);
        customer.setName(name);
        customer.setSurname(surname);
        customer.setPassword(password);
        customer.setAmountOfInternationalTransfer(0);
        customer.setCustomerNumber(genarateCustomerNumber());
        return true;
       }

}
public  String genarateCustomerNumber() {
      Set<Customer>listofCustomer=customerImplDao.getAll();
      String number=listofCustomer.size()+"";
       int size= number.length();
       while(size<5)
       {
           number="0"+number;
           size= number.length();
       }

    return number;

}

public Percentage getInterestRate(Account account) {

        return percentageImplDao.getInterestByAccount(account);


    }
public boolean addInterestRate(){
      double minuteInterestPaid ;
      double totalAmount;
    List<Account>listOfSavingAccount=accountImplDao.getAllByType(TypeOfAccount.Saving);

    ZonedDateTime now=ZonedDateTime.now();
      for (Account account:listOfSavingAccount) {
          totalAmount=account.getAmount();
          long minute=ChronoUnit.MINUTES.between(getInterestRate(account).getInterestRateTime(),now);
        for(int i=0;i<=minute;i++)
        {
            minuteInterestPaid = totalAmount * getInterestRate(account).getPercentage();
            totalAmount += minuteInterestPaid;
        }
          account.setAmount(totalAmount);
          getInterestRate(account).setInterestRateTime(now);
      }
  return true;
  }

    //public  Runnable interestRateProcces(List<Account>listOfSavingAccount){
//
//    Runnable r=new Runnable() {
//        @Override
//        public void run() {
//            for (Account savingAccount:listOfSavingAccount) {
//                savingAccount.setAmount(savingAccount.getAmount()+savingAccount.getAmount()*getInterestRate(savingAccount));
//
//            }
//        }
//    };
//  return r;
//}
//    public  boolean AddInterestRate(){
//
//
//        try {
//            Runnable r=interestRateProcces(accountImplDao.getAllByType(TypeOfAccount.Saving));
//            ScheduledExecutorService executor = Executors.newScheduledThreadPool ( 1 );
//            executor.scheduleAtFixedRate(r,0L,1L, TimeUnit.MINUTES);
//        }
//        catch (Exception ex)
//        {
//            return false;
//        }
//
//        return true;
//
//    }
}