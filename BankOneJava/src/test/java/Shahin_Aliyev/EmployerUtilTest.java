package Shahin_Aliyev;

import Shahin_Aliyev.Config.Config;
import Shahin_Aliyev.Dao.impl.AccountImplDao;
import Shahin_Aliyev.Dao.impl.IbanImplDao;
import Shahin_Aliyev.Dao.impl.PercentageImplDao;
import Shahin_Aliyev.Util.CustomerUtil;
import Shahin_Aliyev.Util.EmployerUtil;
import Shahin_Aliyev.beans.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EmployerUtilTest {

    private static Customer customer;
    private CustomerUtil customerUtil;
    private static Account SavingAccount;
    private static IbanClass ibanClass;
    private static Account RegularAccount;
    private static Account InternationalAccount;
    @Mock
    IbanImplDao ibanImplDaoMock;
    @Mock
    PercentageImplDao percentageImplDaoMock;
    @Mock
    AccountImplDao accountImplDaoMock;




    @InjectMocks
      EmployerUtil employerUtil;

    @Before
     public void setUp()
    {
        MockitoAnnotations.initMocks(this);

       customerUtil=new CustomerUtil();
        SavingAccount=Account.getInstance();
        customer=Customer.getInstance();
        customer.setCustomerNumber("00001");
        customer.setEmail("sahin.aliyeb979@gmail.com");
        customer.setDateOfBirth("12.06.1993");
        customer.setName("Sahin");
        customer.setSurname("Aliyev");
      customer.setPassword("123456");
        customer.setDateOfJoinedBank(ZonedDateTime.now());

        SavingAccount.setCustomerAccount(customer);
        SavingAccount.setTypeOfAccount(TypeOfAccount.Saving);

        SavingAccount.setNumberOfAccount("1200001001");
        Percentage percentage=new Percentage();
        percentage.setPercentage(0.01);
        percentage.setAccountNumber(SavingAccount.getNumberOfAccount());

        SavingAccount.setTransactionnumber(0);



        RegularAccount=Account.getInstance();
        RegularAccount.setTypeOfAccount(TypeOfAccount.Regular);
        RegularAccount.setCustomerAccount(customer);

        RegularAccount.setNumberOfAccount("1200001002");
        RegularAccount.setTransactionnumber(0);


        InternationalAccount=Account.getInstance();
        InternationalAccount.setCustomerAccount(customer);
        InternationalAccount.setTypeOfAccount(TypeOfAccount.International);
        InternationalAccount.setTransactionnumber(0);

        InternationalAccount.setNumberOfAccount("1200001003");
         ibanClass=new IbanClass();
        ibanClass.setIBAN("0123456789");
        ibanClass.setAccountNumber(InternationalAccount.getNumberOfAccount());


        customerUtil.addInterestRate(SavingAccount);


        Mockito.when(ibanImplDaoMock.getIbanByAccount(InternationalAccount)).thenReturn(ibanClass);
        Mockito.when(percentageImplDaoMock.getInterestByAccount(SavingAccount)).thenReturn(0.01);
        Mockito.when(ibanImplDaoMock.getIbanByAccount(null)).thenReturn(null);



    }


    @Test
    public void CheckIbanNumberCorrect(){


        Account result= employerUtil.checkIbanNumber(InternationalAccount);
        assertEquals(result,InternationalAccount);
        Mockito.verify(ibanImplDaoMock,Mockito.atLeastOnce()).getIbanByAccount(InternationalAccount);

    }
    @Test
    public void CheckIbanNumberNotCorrect(){
         ibanClass.setIBAN("123456");
        Account result= employerUtil.checkIbanNumber(InternationalAccount);
        assertEquals(result,null);
        Mockito.verify(ibanImplDaoMock,Mockito.atLeastOnce()).getIbanByAccount(InternationalAccount);

    }
    @Test
    public void CheckInternationalAccount(){



        Account result= employerUtil.checkInternationalAccount(InternationalAccount);
        assertEquals(result,InternationalAccount);
        Mockito.verify(ibanImplDaoMock,Mockito.atLeastOnce()).getIbanByAccount(InternationalAccount);


    }
    @Test
    public void CheckInternationalAccountIfNull(){

        Account result= employerUtil.checkInternationalAccount(null);
        assertEquals(result,null);
        Mockito.verify(ibanImplDaoMock,Mockito.atLeastOnce()).getIbanByAccount(null);

    }
    @Test
    public void CheckInternationalAccountIfIbanNumberNotValid(){
      ibanClass.setIBAN("12345");
        Account result= employerUtil.checkInternationalAccount(InternationalAccount);
        assertEquals(result,null);
        Mockito.verify(ibanImplDaoMock,Mockito.atLeastOnce()).getIbanByAccount(InternationalAccount);

    }
    @Test
    public void CheckSavingAccountIfValid(){



        Account result= employerUtil.checkSavingAccount(SavingAccount);
        assertEquals(result,SavingAccount);
        Mockito.verify(percentageImplDaoMock,Mockito.atLeastOnce()).getInterestByAccount(SavingAccount);

    }
    @Test
    public void CheckSavingAccountIfNotValid(){


        Mockito.when(percentageImplDaoMock.getInterestByAccount(SavingAccount)).thenReturn(0.03);
        Account result= employerUtil.checkSavingAccount(SavingAccount);
        assertEquals(result,null);
        Mockito.verify(percentageImplDaoMock,Mockito.atLeastOnce()).getInterestByAccount(SavingAccount);
    }

   @Test
    public void checkSavingAndInternatinolAccountIfSaving(){


        Account result= employerUtil.checkSavingAndInternatinolAccount(SavingAccount);
        assertEquals(result,SavingAccount);
       Mockito.verify(percentageImplDaoMock,Mockito.atLeastOnce()).getInterestByAccount(SavingAccount);

    }
    @Test
    public void checkSavingAndInternatinolAccountIfInternational(){


        Account result= employerUtil.checkSavingAndInternatinolAccount(InternationalAccount);
        assertEquals(result,InternationalAccount);
        Mockito.verify(ibanImplDaoMock,Mockito.atLeastOnce()).getIbanByAccount(InternationalAccount);

    }
    @Test
    public void checkSavingAndInternatinolAccountIfRegular(){


        Account result= employerUtil.checkSavingAndInternatinolAccount(RegularAccount);
        assertEquals(result,null);


    }
    @Test
    public void checkAccountByTypeIfRegular(){


        Account result= employerUtil.checkAccountBType(RegularAccount);
        assertEquals(result,RegularAccount);

    }
    @Test
    public void checkAccountByTypeIfInternational(){


        Account result= employerUtil.checkAccountBType(InternationalAccount);
        assertEquals(result,InternationalAccount);
        Mockito.verify(ibanImplDaoMock,Mockito.atLeastOnce()).getIbanByAccount(InternationalAccount);

    }
    @Test
    public void checkAccountByIfSaving(){


        Account result= employerUtil.checkAccountBType(SavingAccount);
        assertEquals(result,SavingAccount);
        Mockito.verify(percentageImplDaoMock,Mockito.atLeastOnce()).getInterestByAccount(SavingAccount);

    }
    @Test
    public void CheckAccountIfSaving(){

        Account result= employerUtil.checkAccount(SavingAccount);
        assertEquals(result,SavingAccount);
        Mockito.verify(percentageImplDaoMock,Mockito.atLeastOnce()).getInterestByAccount(SavingAccount);
    }
    @Test
    public void CheckAccountIfInternational(){

        Account result= employerUtil.checkAccount(InternationalAccount);
        assertEquals(result,InternationalAccount);
        Mockito.verify(ibanImplDaoMock,Mockito.atLeastOnce()).getIbanByAccount(InternationalAccount);

    }
    @Test
    public void CheckAccountIfRegular(){

        Account result= employerUtil.checkAccount(RegularAccount);
        assertEquals(result,RegularAccount);

    }
    @Test
    public void CheckAccountIfRegularAccountnotCorrect(){
         RegularAccount.setCustomerAccount(null);
        Account result= employerUtil.checkAccount(RegularAccount);
        assertEquals(result,null);

    }

    @Test
    public void AccecptNewAccountIfInternational(){

        Account result= employerUtil.checkAccount(InternationalAccount);
        assertEquals(result,InternationalAccount);
        Mockito.verify(ibanImplDaoMock,Mockito.atLeastOnce()).getIbanByAccount(InternationalAccount);

    }
    @Test
    public void AccecptNewAccountifNotCorrect(){
        RegularAccount.setNumberOfAccount(" ");
        Account result= employerUtil.checkAccount(RegularAccount);
        assertEquals(result,null);

    }

}
