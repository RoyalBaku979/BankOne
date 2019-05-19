package Shahin_Aliyev;

import Shahin_Aliyev.Config.Config;
import Shahin_Aliyev.Util.BankUtil;
import Shahin_Aliyev.beans.Account;
import Shahin_Aliyev.beans.Percentage;
import Shahin_Aliyev.beans.TypeOfAccount;
import org.junit.Test;


import java.time.ZonedDateTime;

import static org.junit.Assert.*;

public class BankUtilTest {

    @Test
    public void createCustomer1() {
        String name="Sahin";
        String surname="Aliyev";
        String password="12345";
        String email="sahin.aliyev979@gmail.com";
        String dateOfBirht="12.06.1993";
        ZonedDateTime dateOfJoinBank=ZonedDateTime.now();
        int amountOfInternationTransfer=0;
        BankUtil bankUtil=new BankUtil();
       boolean result=bankUtil.CreateCustomer(name,surname,email,password,dateOfBirht,dateOfJoinBank,amountOfInternationTransfer);
        assertEquals(result,true);
    }
    @Test
    public void createCustomer2() {
        String name="";
        String surname="Aliyev";
        String password="12345";
        String email="sahin.aliyev979@gmail.com";
        String dateOfBirht="12.06.1993";
        ZonedDateTime dateOfJoinBank=ZonedDateTime.now();
        int amountOfInternationTransfer=0;
        BankUtil bankUtil=new BankUtil();
        boolean result=bankUtil.CreateCustomer(name,surname,email,password,dateOfBirht,dateOfJoinBank,amountOfInternationTransfer);
        assertEquals(result,false);
    }
    @Test
    public void createCustomer3() {
        String name="Shahin";
        String surname="";
        String password="12345";
        String email="sahin.aliyev979@gmail.com";
        String dateOfBirht="12.06.1993";
        ZonedDateTime dateOfJoinBank=ZonedDateTime.now();
        int amountOfInternationTransfer=0;
        BankUtil bankUtil=new BankUtil();
        boolean result=bankUtil.CreateCustomer(name,surname,email,password,dateOfBirht,dateOfJoinBank,amountOfInternationTransfer);
        assertEquals(result,false);
    }
    @Test
    public void createCustomer4() {
        String name="Shahin";
        String surname="Aliyev";
        String password="";
        String email="sahin.aliyev979@gmail.com";
        String dateOfBirht="12.06.1993";
        ZonedDateTime dateOfJoinBank=ZonedDateTime.now();
        int amountOfInternationTransfer=0;
        BankUtil bankUtil=new BankUtil();
        boolean result=bankUtil.CreateCustomer(name,surname,email,password,dateOfBirht,dateOfJoinBank,amountOfInternationTransfer);
        assertEquals(result,false);
    }
    @Test
    public void createCustomer5() {
        String name="Shahin";
        String surname="Aliyev";
        String password="1234";
        String email="";
        String dateOfBirht="12.06.1993";
        ZonedDateTime dateOfJoinBank=ZonedDateTime.now();
        int amountOfInternationTransfer=0;
        BankUtil bankUtil=new BankUtil();
        boolean result=bankUtil.CreateCustomer(name,surname,email,password,dateOfBirht,dateOfJoinBank,amountOfInternationTransfer);
        assertEquals(result,false);
    }
    @Test
    public void createCustomer6() {
        String name="Shahin";
        String surname="Aliyev";
        String password="1234";
        String email="sahin.aliyev979@gmail.com";
        String dateOfBirht="";
        ZonedDateTime dateOfJoinBank=ZonedDateTime.now();
        int amountOfInternationTransfer=0;
        BankUtil bankUtil=new BankUtil();
        boolean result=bankUtil.CreateCustomer(name,surname,email,password,dateOfBirht,dateOfJoinBank,amountOfInternationTransfer);
        assertEquals(result,false);
    }
    @Test
    public void genarateCustomerNumber() {
        BankUtil bankUtil=new BankUtil();
        String result=bankUtil.genarateCustomerNumber();
        String number=Config.getListofCustomer().size()+"";
        int size= number.length();
        while(size<5)
        {
            number="0"+number;
            size= number.length();
        }


        assertEquals(result,number);
    }
    @Test
    public void getInterestRate() {
        BankUtil bankUtil=new BankUtil();
        Account account=Account.getInstance();
        account.setNumberOfAccount("12345");
        account.setTypeOfAccount(TypeOfAccount.Saving);
        Config.AddListOfAccount(account);
        Percentage percentage=new Percentage();
        percentage.setAccountNumber(account.getNumberOfAccount());
        percentage.setPercentage(0.01);
        Config.setListofPercentage(percentage);

        double result=bankUtil.getInterestRate(account);
        assertEquals(result,0.01,0.001);


    }

}