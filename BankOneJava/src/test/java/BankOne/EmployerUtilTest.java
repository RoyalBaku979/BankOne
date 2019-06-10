package BankOne;

import BankOne.Dao.impl.AccountImplDao;
import BankOne.Dao.impl.IbanImplDao;
import BankOne.Dao.impl.PercentageImplDao;
import BankOne.Util.CustomerUtil;
import BankOne.Util.EmployerUtil;
import BankOne.beans.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;

public class EmployerUtilTest {

    private static Customer customer;
    private CustomerUtil customerUtil;
    private static Account SavingAccount;
    private static IbanClass ibanClass;
    private static Account RegularAccount;
    private static Account InternationalAccount;
    private static EmployerUtil employerUtilMock;
    private static Percentage percentage;
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
         percentage=new Percentage();
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
        Mockito.when(percentageImplDaoMock.getInterestByAccount(SavingAccount)).thenReturn(percentage);
        Mockito.when(ibanImplDaoMock.getIbanByAccount(null)).thenReturn(null);

       employerUtilMock=Mockito.spy(employerUtil);

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

       percentage.setPercentage(0.03);
        Mockito.when(percentageImplDaoMock.getInterestByAccount(SavingAccount)).thenReturn(percentage);
        Account result= employerUtil.checkSavingAccount(SavingAccount);
        assertEquals(result,null);
        Mockito.verify(percentageImplDaoMock,Mockito.atLeastOnce()).getInterestByAccount(SavingAccount);
    }

   @Test
    public void checkSavingAndInternatinolAccountIfSaving(){

       Mockito.doReturn(SavingAccount).when(employerUtilMock).checkSavingAccount(SavingAccount);
       Account result= employerUtilMock.checkSavingAndInternatinolAccount(SavingAccount);
       assertEquals(result,SavingAccount);
       Mockito.verify(employerUtilMock,Mockito.never()).checkInternationalAccount(SavingAccount);
       Mockito.verify(employerUtilMock,Mockito.atLeastOnce()).checkSavingAccount(SavingAccount);


    }
    @Test
    public void checkSavingAndInternatinolAccountIfInternational(){
        Mockito.doReturn(InternationalAccount).when(employerUtilMock).checkInternationalAccount(InternationalAccount);
        Account result= employerUtilMock.checkSavingAndInternatinolAccount(InternationalAccount);
        assertEquals(result,InternationalAccount);
        Mockito.verify(employerUtilMock,Mockito.atLeastOnce()).checkInternationalAccount(InternationalAccount);
        Mockito.verify(employerUtilMock,Mockito.never()).checkSavingAccount(InternationalAccount);

    }
    @Test
    public void checkSavingAndInternatinolAccountIfRegular(){


        Account result= employerUtilMock.checkSavingAndInternatinolAccount(RegularAccount);
        assertEquals(result,null);
        Mockito.verify(employerUtilMock,Mockito.never()).checkInternationalAccount(RegularAccount);
        Mockito.verify(employerUtilMock,Mockito.never()).checkSavingAccount(RegularAccount);


    }
    @Test
    public void checkAccountByTypeIfRegular(){


        Account result= employerUtilMock.checkAccountBType(RegularAccount);
        assertEquals(result,RegularAccount);
        Mockito.verify(employerUtilMock,Mockito.never()).checkSavingAndInternatinolAccount(RegularAccount);

    }
    @Test
    public void checkAccountByTypeIfInternational(){
        Mockito.doReturn(InternationalAccount).when(employerUtilMock).checkSavingAndInternatinolAccount(InternationalAccount);
        Account result= employerUtilMock.checkAccountBType(InternationalAccount);
        assertEquals(result,InternationalAccount);
        Mockito.verify(employerUtilMock,Mockito.atLeastOnce()).checkSavingAndInternatinolAccount(InternationalAccount);


    }
    @Test
    public void checkAccountByIfTypeSaving(){

        Mockito.doReturn(SavingAccount).when(employerUtilMock).checkSavingAndInternatinolAccount(SavingAccount);
        Account result= employerUtilMock.checkAccountBType(SavingAccount);
        assertEquals(result,SavingAccount);
        Mockito.verify(employerUtilMock,Mockito.atLeastOnce()).checkSavingAndInternatinolAccount(SavingAccount);

    }
    @Test
    public void CheckAccountIfCorrect(){
          Mockito.doReturn(SavingAccount).when(employerUtilMock).checkAccountBType(SavingAccount);
        Account result= employerUtilMock.checkAccount(SavingAccount);
        assertEquals(result,SavingAccount);
        Mockito.verify(employerUtilMock,Mockito.atLeastOnce()).checkAccountBType(SavingAccount);
    }

    @Test
    public void CheckAccountIfAccountCustomerNull(){
         RegularAccount.setCustomerAccount(null);

        Account result= employerUtilMock.checkAccount(RegularAccount);
        assertEquals(result,null);
        Mockito.verify(employerUtilMock,Mockito.never()).checkAccountBType(RegularAccount);


    }
    @Test
    public void CheckAccountIfAccountTypeNUll(){
        RegularAccount.setTypeOfAccount(null);

        Account result= employerUtilMock.checkAccount(RegularAccount);
        assertEquals(result,null);
        Mockito.verify(employerUtilMock,Mockito.never()).checkAccountBType(RegularAccount);


    }
    @Test
    public void CheckAccountIfAccountNumberNull(){
        RegularAccount.setNumberOfAccount("");

        Account result= employerUtilMock.checkAccount(RegularAccount);
        assertEquals(result,null);
        Mockito.verify(employerUtilMock,Mockito.never()).checkAccountBType(RegularAccount);


    }

    @Test
    public void AccecptNewAccountIfCorrect(){
        Mockito.doReturn(RegularAccount).when(employerUtilMock).checkAccount(RegularAccount);
        Account result= employerUtilMock.accecptNewAccount(RegularAccount);
        assertEquals(result,RegularAccount);
        Mockito.verify(employerUtilMock,Mockito.atLeastOnce()).checkAccount(RegularAccount);

    }
    @Test
    public void AccecptNewAccountifNotCorrect(){
        RegularAccount.setTypeOfAccount(null);
        Mockito.doReturn(null).when(employerUtilMock).checkAccount(RegularAccount);
        Account result= employerUtilMock.accecptNewAccount(RegularAccount);
        assertEquals(result,null);
        Mockito.verify(employerUtilMock,Mockito.atLeastOnce()).checkAccount(RegularAccount);

    }

}
