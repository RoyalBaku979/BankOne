package uni.lodz.pl.java.Util;

import uni.lodz.pl.java.Config.Config;
import uni.lodz.pl.java.beans.Account;
import uni.lodz.pl.java.beans.Customer;
import uni.lodz.pl.java.beans.TypeOfAccount;

import java.sql.SQLOutput;

public class CustomerUtil {
    private final static String banknumber="12";
    public static boolean SendRequest(TypeOfAccount typeOfAccount, Double amountOfMoney,String nameOfAccount){
       if(Config.getCustomer()==null)
       {
           System.out.println("You should Login if you want to open Account");
           return false;
       }
        Account account=Account.getInstance();
        account.setTransferAmount(0);
        account.setNumberOfAccount(genarateAccountNumber(Config.getCustomer()));
        account.setTypeOfAccount(typeOfAccount);

        account.setAmount(amountOfMoney);
        account.setCustomerAccount(Config.getCustomer());
        addIbanNumber(account);
        addInterestRate(account);
       return OpenAccount(account);

    }
    public static void addIbanNumber(Account account){
        if(account.getTypeOfAccount()==TypeOfAccount.International)
        {
            account.setIBAN(generateIbanNumber());

        }
        else{

            account.setIBAN(null);
        }


    }
    public static void addInterestRate(Account account) {
        if(account.getTypeOfAccount()==TypeOfAccount.Saving)
        {
            account.setInterestRate(0.01);

        }
        else{

            account.setInterestRate(0);
        }

    }
    public static boolean OpenAccount(Account account){
        if(EmployerUtil.AccecptNewAccount(account))
        {
            account.setApproveByemployer(true);
            Config.AddListOfAccount(account);
            account.getCustomerAccount().addAccountList(account);
            return true;

        }
        else
        {

            return false;
        }

    }
    private  static String genarateCustomerAccountNumber(Customer customer){

            String number = customer.getListOfAccount().size() + "";
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
    private static String generateIbanNumber(){
    Customer customer=Config.getCustomer();
     String iBanNumber=null;
     String firstTwoLetterFromName=customer.getName().substring(0,2);
      String firstTwoLetterFromSurname=customer.getSurname().substring(0,2);
      String dateOfBirth=customer.getDateOfBirth();
      iBanNumber=iBanNumber+firstTwoLetterFromName+firstTwoLetterFromSurname+dateOfBirth;
      return iBanNumber;


  }
    public static boolean loginCustomer(String email,String password){
      for (Customer customer:Config.getListofCustomer()) {
          if(customer.getEmail().equalsIgnoreCase(email) && customer.getPassword().equals(password))
          {
              Config.setCustomer(customer);
              return true;

          }
     }

      return false;
  }

}
