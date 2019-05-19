package Shahin_Aliyev;

import Shahin_Aliyev.Config.Config;
import Shahin_Aliyev.Dao.impl.AccountImplDao;
import Shahin_Aliyev.Util.BankUtil;
import Shahin_Aliyev.Util.CustomerUtil;
import Shahin_Aliyev.Util.EmployerUtil;
import Shahin_Aliyev.beans.*;
import org.junit.Test;

import java.time.ZonedDateTime;

import static org.junit.Assert.*;

public class CustomerUtilTest {
    public Customer createCustomer(String email,String password) {
        Customer customer=Customer.getInstance();
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setDateOfJoinedBank(ZonedDateTime.now());
        Config.AddListofCustomer(customer);
        Config.setCustomer(customer);
        return  customer;
    }
    public void createCustomerForIbanNumber() {

      Customer customer=Customer.getInstance();
      customer.setDateOfBirth("12.06.1993");
      customer.setName("Sahin");
      customer.setSurname("Aliyev");
      Config.setCustomer(customer);
  }
    public String createCustomerAccountNumberForTest(){
      Customer customer =createCustomer("sahin.aliyev979@gmail.com","123456");
      AccountImplDao accountImplDao=new AccountImplDao();

      String number = accountImplDao.getAllByCustomer(customer).size()+"";
      int size = number.length();
      while (size < 3) {

          number = "0" + number;
          size = number.length();
      }


     return number;
  }
    public String generateCustomerNumberForTest(){
      String number=Config.getListofCustomer().size()+"";
      int size= number.length();
      while(size<5)
      {
          number="0"+number;
          size= number.length();
      }

      return number;
  }
    public Account createAccount(){

        CustomerUtil customerUtil=new CustomerUtil();
        Account account=Account.getInstance();
        account.setCustomerAccount(createCustomer("Shahin","Aliyev"));
        account.setNumberOfAccount(customerUtil.genarateAccountNumber(Config.getCustomer()));
        account.setTransactionnumber(0);

        return account;

  }
    public void createIban(Account account,String iban){

        IbanClass ibanClass=new IbanClass();
        ibanClass.setIBAN(iban);
        ibanClass.setAccountNumber(account.getNumberOfAccount());
        Config.addListofIbans(ibanClass);
    }
    public void createPercentage(Account account,Double percent){
        Percentage percentage=new Percentage();
        percentage.setPercentage(percent);
        percentage.setAccountNumber(account.getNumberOfAccount());
        Config.setListofPercentage(percentage);
    }




    @Test
    public void addIbanNumber1() {
        CustomerUtil customerUtil=new CustomerUtil();
        Account account=Account.getInstance();
        account.setNumberOfAccount("0123456789");
        createCustomerForIbanNumber();

        account.setTypeOfAccount(TypeOfAccount.International);
        boolean result=customerUtil.addIbanNumber(account);
        assertEquals(result,true);
    }
    @Test
    public void addIbanNumber2() {
        CustomerUtil customerUtil=new CustomerUtil();
        Account account=Account.getInstance();
        account.setNumberOfAccount("0123456789");
        account.setTypeOfAccount(TypeOfAccount.Saving);
        boolean result=customerUtil.addIbanNumber(account);
        assertEquals(result,false);
    }
    @Test
    public void sendRequest1() {
        CustomerUtil customerUtil=new CustomerUtil();
        Account account=createAccount();
        account.setTypeOfAccount(TypeOfAccount.Saving);
        customerUtil.addInterestRate(account);
        boolean result=customerUtil.SendRequest(TypeOfAccount.Saving,1000.00);
        assertEquals(result,true);

    }
    @Test
    public void sendRequest2() {
        Config.setCustomer(null);
        CustomerUtil customerUtil=new CustomerUtil();
        boolean result=customerUtil.SendRequest(TypeOfAccount.Saving,1000.00);
        assertEquals(result,false);

    }
    @Test
    public void addIbanNumber3() {
        CustomerUtil customerUtil=new CustomerUtil();
        Account account=Account.getInstance();
        account.setNumberOfAccount("0123456789");
        account.setTypeOfAccount(TypeOfAccount.Regular);
        boolean result=customerUtil.addIbanNumber(account);
        assertEquals(result,false);
    }

    @Test
    public void addInterestRate1() {
        CustomerUtil customerUtil=new CustomerUtil();
        Account account=Account.getInstance();
           account.setNumberOfAccount("0123456789");
        account.setTypeOfAccount(TypeOfAccount.Saving);
       boolean result=customerUtil.addInterestRate(account);
       assertEquals(result,true);

    }
    @Test
    public void addInterestRate2() {
        CustomerUtil customerUtil=new CustomerUtil();
        Account account=Account.getInstance();
        account.setNumberOfAccount("0123456789");
        account.setTypeOfAccount(TypeOfAccount.International);
        boolean result=customerUtil.addInterestRate(account);
        assertEquals(result,false);

    }
    @Test
    public void addInterestRate3() {
        CustomerUtil customerUtil=new CustomerUtil();
        Account account=Account.getInstance();
        account.setNumberOfAccount("0123456789");
        account.setTypeOfAccount(TypeOfAccount.Regular);
        boolean result=customerUtil.addInterestRate(account);
        assertEquals(result,false);

    }

    @Test
    public void openAccount1() {
        EmployerUtil employerUtil=new EmployerUtil();
        CustomerUtil customerUtil=new CustomerUtil();
        Account account=Account.getInstance();
        account.setTypeOfAccount(TypeOfAccount.International);
        account.setCustomerAccount(Customer.getInstance());
        account.setNumberOfAccount("12345");
        Config.AddListOfAccount(account);
        createIban(account,"0123456789");
        Boolean result=customerUtil.OpenAccount(account);
        assertEquals(result,true);

    }
    @Test
    public void openAccount2() {
        EmployerUtil employerUtil=new EmployerUtil();
        CustomerUtil customerUtil=new CustomerUtil();
        Account account=Account.getInstance();
        account.setTypeOfAccount(TypeOfAccount.Saving);
        account.setCustomerAccount(Customer.getInstance());
        account.setNumberOfAccount("12345");
        Config.AddListOfAccount(account);
        createPercentage(account,0.01);
        Boolean result=customerUtil.OpenAccount(account);
        assertEquals(result,true);

    }



    @Test
    public void genarateAccountNumber() {
        CustomerUtil customerUtil=new CustomerUtil();

        String accountNumber=createCustomerAccountNumberForTest();
        String number="12"+generateCustomerNumberForTest()+createCustomerAccountNumberForTest();
        String result=createAccount().getNumberOfAccount();
    }
    @Test
    public void genarateCustomerAccountNumber() {
        CustomerUtil customerUtil=new CustomerUtil();
      Customer customer =createCustomer("sahin.aliyev979@gmail.com","123456");
        AccountImplDao accountImplDao=new AccountImplDao();

        String number = accountImplDao.getAllByCustomer(customer).size()+"";
        int size = number.length();
        while (size < 3) {

            number = "0" + number;
            size = number.length();
        }
      String result= customerUtil.genarateCustomerAccountNumber(customer);
        assertEquals(result,number);


    }

    @Test
    public void generateIbanNumber()
    {

        CustomerUtil customerUtil=new CustomerUtil();
        createCustomerForIbanNumber();
        String iBanNumber;
        String firstTwoLetterFromName=Config.getCustomer().getName().substring(0,2).toLowerCase();
        String firstTwoLetterFromSurname=Config.getCustomer().getSurname().substring(0,2).toLowerCase();
        String dateOfBirth=Config.getCustomer().getDateOfBirth().replace(".","");
        iBanNumber=firstTwoLetterFromName+firstTwoLetterFromSurname+dateOfBirth;
        String result=customerUtil.generateIbanNumber();
        assertEquals(result,"saal12061993");
    }

    @Test
    public void loginCustomer1() {
         createCustomer("sahin.aliyev979@gmail.com","A12345678");
        CustomerUtil customerUtil=new CustomerUtil();
        String email="sahin.aliyev979@gmail.com";
        String password="A12345678";
        boolean result=customerUtil.loginCustomer(email,password);
        assertEquals(result,true);
    }
    @Test
    public void loginCustomer2() {
        createCustomer("sahin.aliyev979@gmail.com","A12345678");
        CustomerUtil customerUtil=new CustomerUtil();
        String email="sahin.aliyev979@gmail.com";
        String password="a12345678";
        boolean result=customerUtil.loginCustomer(email,password);
        assertEquals(result,false);
    }
    @Test
    public void loginCustomer3() {
        createCustomer("sahin.aliyev979@gmailcom","1234567");
        CustomerUtil customerUtil=new CustomerUtil();
        String email="sahin.aliyev979@gmail.com";
        String password="12345678";
        boolean result=customerUtil.loginCustomer(email,password);
        assertEquals(result,false);
    }
    @Test
    public void loginCustomer4() {
        createCustomer("Sahin.aliyev979@gmail.com","12345678");
        CustomerUtil customerUtil=new CustomerUtil();
        String email="sahin.aliyev979@gmail.com";
        String password="12345678";
        boolean result=customerUtil.loginCustomer(email,password);
        assertEquals(result,true);
    }
}