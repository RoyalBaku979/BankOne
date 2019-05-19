package Shahin_Aliyev;

import Shahin_Aliyev.Config.Config;
import Shahin_Aliyev.Util.EmployerUtil;
import Shahin_Aliyev.beans.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmployerUtilTest {


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
    public void CheckIbanNumber1(){
        String iban="012345678";
        EmployerUtil employerUtil=new EmployerUtil();
        Account account=Account.getInstance();
        account.setNumberOfAccount("1234567");
        account.setTypeOfAccount(TypeOfAccount.International);
        Config.AddListOfAccount(account);
        IbanClass ibanClass=new IbanClass();
        ibanClass.setIBAN(iban);
        ibanClass.setAccountNumber(account.getNumberOfAccount());
        Config.addListofIbans(ibanClass);

     Account result= employerUtil.CheckIbanNumber(account);
        assertEquals(result,null);

    }
    @Test
    public void CheckIbanNumber2(){
        String iban="0123456789";
        EmployerUtil employerUtil=new EmployerUtil();
        Account account=Account.getInstance();
        account.setNumberOfAccount("12345");
        IbanClass ibanClass=new IbanClass();
        ibanClass.setIBAN(iban);
        ibanClass.setAccountNumber(account.getNumberOfAccount());
        Config.addListofIbans(ibanClass);
        Config.AddListOfAccount(account);
        Account result= employerUtil.CheckIbanNumber(account);
        assertEquals(result,account);

    }
    @Test
    public void CheckInternationalAccount1(){
        String iban="0123456789";
        EmployerUtil employerUtil=new EmployerUtil();
        Account account=Account.getInstance();
        account.setNumberOfAccount("12345");
        IbanClass ibanClass=new IbanClass();
        ibanClass.setIBAN(iban);
        ibanClass.setAccountNumber(account.getNumberOfAccount());
        Config.addListofIbans(ibanClass);
        Config.AddListOfAccount(account);
        Account result= employerUtil.CheckInternationalAccount(account);
        assertEquals(result,account);

    }
    @Test
    public void CheckInternationalAccount2(){
        EmployerUtil employerUtil=new EmployerUtil();
        Account account=Account.getInstance();
        account.setNumberOfAccount("1234");
        Config.AddListOfAccount(account);
        Account result= employerUtil.CheckInternationalAccount(account);
        assertEquals(result,null);

    }
    @Test
    public void CheckSavingAccount1(){

        EmployerUtil employerUtil=new EmployerUtil();
        Account account=Account.getInstance();
        account.setNumberOfAccount("123456");
        account.setTypeOfAccount(TypeOfAccount.Saving);
        Config.AddListOfAccount(account);
        Percentage percentage=new Percentage();
        percentage.setPercentage(0.03);
        percentage.setAccountNumber(account.getNumberOfAccount());
       Config.setListofPercentage(percentage);

        Account result= employerUtil.CheckSavingAccount(account);
        assertEquals(result,null);

    }

    @Test
    public void checkSavingAndInternatinolAccount1(){

        EmployerUtil employerUtil=new EmployerUtil();
        Account account=Account.getInstance();
        account.setTypeOfAccount(TypeOfAccount.Saving);
        account.setNumberOfAccount("12345");

        createPercentage(account,0.01);
        Config.AddListOfAccount(account);
        Account result= employerUtil.checkSavingAndInternatinolAccount(account);
        assertEquals(result,account);

    }
    @Test
    public void checkSavingAndInternatinolAccount2(){

        EmployerUtil employerUtil=new EmployerUtil();
        Account account=Account.getInstance();
        account.setTypeOfAccount(TypeOfAccount.International);
        account.setNumberOfAccount("12345");
        Config.AddListOfAccount(account);
        createIban(account,"0123456789");
        Account result= employerUtil.checkSavingAndInternatinolAccount(account);
        assertEquals(result,account);

    }
    @Test
    public void checkAccountBType1(){

        EmployerUtil employerUtil=new EmployerUtil();
        Account account=Account.getInstance();
        account.setTypeOfAccount(TypeOfAccount.Regular);
        account.setNumberOfAccount("12345");
        Config.AddListOfAccount(account);
        createIban(account,"0123456789");
        Account result= employerUtil.checkAccountBType(account);
        assertEquals(result,account);

    }
    @Test
    public void checkAccountBType2(){

        EmployerUtil employerUtil=new EmployerUtil();
        Account account=Account.getInstance();
        account.setTypeOfAccount(TypeOfAccount.International);
        account.setNumberOfAccount("12345");
        Config.AddListOfAccount(account);
        createIban(account,"0123456789");
        Account result= employerUtil.checkAccountBType(account);
        assertEquals(result,account);

    }
    @Test
    public void checkAccountBType3(){

        EmployerUtil employerUtil=new EmployerUtil();
        Account account=Account.getInstance();
        account.setTypeOfAccount(TypeOfAccount.Saving);
        account.setNumberOfAccount("12345");
        Config.AddListOfAccount(account);
        createPercentage(account,0.01);
        Account result= employerUtil.checkAccountBType(account);
        assertEquals(result,account);

    }
    @Test
    public void CheckAccount1(){

        EmployerUtil employerUtil=new EmployerUtil();
        Account account=Account.getInstance();
        account.setTypeOfAccount(TypeOfAccount.Saving);
        account.setCustomerAccount(Customer.getInstance());
        account.setNumberOfAccount("12345");
        Config.AddListOfAccount(account);
        createPercentage(account,0.01);
        Account result= employerUtil.CheckAccount(account);
        assertEquals(result,account);

    }
    @Test
    public void CheckAccount2(){

        EmployerUtil employerUtil=new EmployerUtil();
        Account account=Account.getInstance();
        account.setTypeOfAccount(TypeOfAccount.Saving);
        account.setCustomerAccount(Customer.getInstance());
        account.setNumberOfAccount("");
        Config.AddListOfAccount(account);
        createPercentage(account,0.01);
        Account result= employerUtil.CheckAccount(account);
        assertEquals(result,null);

    }
    @Test
    public void CheckAccount3(){

        EmployerUtil employerUtil=new EmployerUtil();
        Account account=Account.getInstance();
        account.setTypeOfAccount(null);
        account.setCustomerAccount(Customer.getInstance());
        account.setNumberOfAccount("12345");
        Config.AddListOfAccount(account);
        createPercentage(account,0.01);
        Account result= employerUtil.CheckAccount(account);
        assertEquals(result,null);

    }
    @Test
    public void CheckAccount4(){

        EmployerUtil employerUtil=new EmployerUtil();
        Account account=Account.getInstance();
        account.setTypeOfAccount(TypeOfAccount.International);
        account.setCustomerAccount(null);
        account.setNumberOfAccount("12345");
        Config.AddListOfAccount(account);
        createPercentage(account,0.01);
        Account result= employerUtil.CheckAccount(account);
        assertEquals(result,null);

    }
    @Test
    public void AccecptNewAccount1(){
        EmployerUtil employerUtil=new EmployerUtil();
        Account account=Account.getInstance();
        account.setTypeOfAccount(TypeOfAccount.International);
        account.setCustomerAccount(null);
        account.setNumberOfAccount("12345");
        Config.AddListOfAccount(account);
        createPercentage(account,0.01);
        Account result= employerUtil.CheckAccount(account);
        assertEquals(result,null);

    }
    @Test
    public void AccecptNewAccount2(){
        EmployerUtil employerUtil=new EmployerUtil();
        Account account=Account.getInstance();
        account.setTypeOfAccount(TypeOfAccount.International);
        account.setCustomerAccount(Customer.getInstance());
        account.setNumberOfAccount("12345");
        Config.AddListOfAccount(account);
        createIban(account,"0123456789");
        Account result= employerUtil.CheckAccount(account);
        assertEquals(result,account);

    }


}
