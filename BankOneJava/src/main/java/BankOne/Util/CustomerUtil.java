package BankOne.Util;

import BankOne.Config.Config;
import BankOne.beans.*;
import BankOne.Dao.impl.AccountImplDao;
import BankOne.Dao.impl.CustomerImplDao;
import BankOne.beans.*;

import java.time.ZonedDateTime;

public class CustomerUtil {
    EmployerUtil employerUtil;
    AccountImplDao accountImplDao;
    CustomerImplDao customerImplDao;
    Config config;
    public final static String banknumber="12";
    public Account sendRequest(TypeOfAccount typeOfAccount, Double amountOfMoney, Customer customer){
       if(customer==null)
       {
           System.out.println("You should Login if you want to open Account");
           return null;
       }
        Account account=Account.getInstance();
        account.setTransactionnumber(0);
        account.setNumberOfAccount(genarateAccountNumber(customer));
        account.setTypeOfAccount(typeOfAccount);
        account.setCreateTimeAccount(ZonedDateTime.now());
        account.setAmount(amountOfMoney);
        account.setCustomerAccount(customer);
        addIbanNumber(account,customer);
        addInterestRate(account);

       return openAccount(account);

    }
    public  boolean addIbanNumber(Account account,Customer customer){
        if(account.getTypeOfAccount()==TypeOfAccount.International)
        {
            IbanClass ibanClass=new IbanClass();
            ibanClass.setAccountNumber(account.getNumberOfAccount());
            ibanClass.setIBAN(generateIbanNumber(customer));

            return true;

        }
      else
        {
            return false;
        }


    }
    public  boolean addInterestRate(Account account) {
        if(account.getTypeOfAccount()==TypeOfAccount.Saving)
        {
            Percentage percentage=new Percentage();
            percentage.setAccountNumber(account.getNumberOfAccount());
            percentage.setPercentage(0.01);
            percentage.setInterestRateTime(ZonedDateTime.now());

           return true;
        }
      else
        {
            return false;

        }

    }
    public  Account openAccount(Account account){
        if(employerUtil.accecptNewAccount(account)!=null)
        {



            return employerUtil.accecptNewAccount(account);

        }
        else
        {

            return null;
        }

    }
    public   String genarateCustomerAccountNumber(Customer customer){


            String number = accountImplDao.getAllByCustomer(customer).size()+"";

            int size = number.length();
            while (size < 3) {

                number = "0" + number;
                size = number.length();
            }

            return number;



    }
    public   String genarateAccountNumber(Customer customer){
        Customer customer1=customer;
        String fullNumber=banknumber+customer.getCustomerNumber()+genarateCustomerAccountNumber(customer);
        return fullNumber;

    }
    public  String generateIbanNumber(Customer customer){

     String iBanNumber;
     String firstTwoLetterFromName=customer.getName().substring(0,2).toLowerCase();
      String firstTwoLetterFromSurname=customer.getSurname().substring(0,2).toLowerCase();
      String dateOfBirth=customer.getDateOfBirth().replace(".","");
      iBanNumber=firstTwoLetterFromName+firstTwoLetterFromSurname+dateOfBirth;
      return iBanNumber;


  }
    public  boolean loginCustomer(String email,String password){

      for (Customer customer:customerImplDao.getAll()) {
          if(customer.getEmail().equalsIgnoreCase(email) && customer.getPassword().equals(password))
          {
              config.setCustomer(customer);
              return true;

          }
     }

      return false;
  }

}
