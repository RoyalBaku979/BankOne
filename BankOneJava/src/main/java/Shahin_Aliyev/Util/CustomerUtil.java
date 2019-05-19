package Shahin_Aliyev.Util;

import Shahin_Aliyev.Config.Config;
import Shahin_Aliyev.Dao.impl.AccountImplDao;
import Shahin_Aliyev.Dao.impl.CustomerImplDao;
import Shahin_Aliyev.beans.*;

public class CustomerUtil {
    private final static String banknumber="12";
    public static boolean SendRequest(TypeOfAccount typeOfAccount, Double amountOfMoney){
       if(Config.getCustomer()==null)
       {
           System.out.println("You should Login if you want to open Account");
           return false;
       }
        Account account=Account.getInstance();
        account.setTransactionnumber(0);
        account.setNumberOfAccount(genarateAccountNumber(Config.getCustomer()));
        account.setTypeOfAccount(typeOfAccount);

        account.setAmount(amountOfMoney);
        account.setCustomerAccount(Config.getCustomer());
        addIbanNumber(account);
        addInterestRate(account);
       return OpenAccount(account);

    }
    public static boolean addIbanNumber(Account account){
        if(account.getTypeOfAccount()==TypeOfAccount.International)
        {
            IbanClass ibanClass=new IbanClass();
            ibanClass.setAccountNumber(account.getNumberOfAccount());
            ibanClass.setIBAN(generateIbanNumber());
            Config.addListofIbans(ibanClass);
            return true;

        }
      else
        {
            return false;
        }


    }
    public static boolean addInterestRate(Account account) {
        if(account.getTypeOfAccount()==TypeOfAccount.Saving)
        {
            Percentage percentage=new Percentage();
            percentage.setAccountNumber(account.getNumberOfAccount());
            percentage.setPercentage(0.01);
            Config.setListofPercentage(percentage);
           return true;
        }
      else
        {
            return false;

        }

    }
    public static boolean OpenAccount(Account account){
        if(EmployerUtil.AccecptNewAccount(account)!=null)
        {

            Config.AddListOfAccount(account);

            return true;

        }
        else
        {

            return false;
        }

    }
    public  static String genarateCustomerAccountNumber(Customer customer){
        AccountImplDao accountImplDao=new AccountImplDao();

            String number = accountImplDao.getAllByCustomer(customer).size()+"";
            int size = number.length();
            while (size < 3) {

                number = "0" + number;
                size = number.length();
            }
            customer.setCustomerNumber(number);
            return number;



    }
    public  static String genarateAccountNumber(Customer customer){

        String fullNumber=banknumber+customer.getCustomerNumber()+genarateCustomerAccountNumber(customer);
        return fullNumber;

    }
    public static String generateIbanNumber(){
    Customer customer=Config.getCustomer();
     String iBanNumber;
     String firstTwoLetterFromName=customer.getName().substring(0,2).toLowerCase();
      String firstTwoLetterFromSurname=customer.getSurname().substring(0,2).toLowerCase();
      String dateOfBirth=customer.getDateOfBirth().replace(".","");
      iBanNumber=firstTwoLetterFromName+firstTwoLetterFromSurname+dateOfBirth;
      return iBanNumber;


  }
    public static boolean loginCustomer(String email,String password){
        CustomerImplDao customerImplDao=new CustomerImplDao();
      for (Customer customer:customerImplDao.getAll()) {
          if(customer.getEmail().equalsIgnoreCase(email) && customer.getPassword().equals(password))
          {
              Config.setCustomer(customer);
              return true;

          }
     }

      return false;
  }

}
