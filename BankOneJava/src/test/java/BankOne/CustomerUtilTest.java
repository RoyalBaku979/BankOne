package BankOne;

import BankOne.Config.Config;
import BankOne.Dao.impl.AccountImplDao;
import BankOne.Dao.impl.CustomerImplDao;
import BankOne.Util.CustomerUtil;
import BankOne.Util.EmployerUtil;
import BankOne.beans.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class CustomerUtilTest {
    private static IbanClass ibanClass;
    private static Customer customer;
    private static Account SavingAccount;
    private static Account RegularAccount;
    private static Account InternationalAccount;
  private static CustomerUtil customerUtilMock;

    @Mock
    EmployerUtil employerUtil;
    @Mock
    AccountImplDao accountImplDaoMock;
    @Mock
    CustomerImplDao customerImplDaoMock;

   @Mock
    Config config;



    @InjectMocks
    CustomerUtil customerUtil;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);



        customer=Customer.getInstance();
        customer.setCustomerNumber("00001");
        customer.setEmail("sahin.aliyev979@gmail.com");
        customer.setDateOfBirth("12.06.1993");
        customer.setName("Sahin");
        customer.setSurname("Aliyev");
        customer.setPassword("123456");
        customer.setDateOfJoinedBank(ZonedDateTime.now());

        SavingAccount=Account.getInstance();
        SavingAccount.setCustomerAccount(customer);
        SavingAccount.setTypeOfAccount(TypeOfAccount.Saving);
        SavingAccount.setAmount(100.0);
        SavingAccount.setTransactionnumber(0);
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
        ibanClass.setIBAN(customerUtil.generateIbanNumber(customer));
        ibanClass.setAccountNumber(InternationalAccount.getNumberOfAccount());

        Set<Customer> listCustomer=new TreeSet<>();
        List<Account>listAccount=new ArrayList<>();
        listAccount.add(InternationalAccount);
        listAccount.add(SavingAccount);
        listAccount.add(RegularAccount);
        listCustomer.add(customer);
        customerUtil.addInterestRate(SavingAccount);

        Mockito.when(employerUtil.accecptNewAccount(SavingAccount)).thenReturn(SavingAccount);
        Mockito.when(employerUtil.accecptNewAccount(InternationalAccount)).thenReturn(InternationalAccount);
        customerUtilMock=Mockito.spy(customerUtil);
        Mockito.when(customerImplDaoMock.getAll()).thenReturn(listCustomer);
        Mockito.when(accountImplDaoMock.getAllByCustomer(customer)).thenReturn(listAccount);
        Mockito.when(config.getListofCustomer1()).thenReturn(listCustomer);

    }

    @Test
    public void addIbanNumberInternationalAccount() {

        boolean result=customerUtil.addIbanNumber(InternationalAccount,customer);
        assertEquals(result,true);
    }
    @Test
    public void addIbanNumberSavingAccount() {

        boolean result=customerUtil.addIbanNumber(SavingAccount,customer);
        assertEquals(result,false);
    }
    @Test
    public void sendRequestIfCustomerNull() {



        Account result=customerUtilMock.sendRequest(TypeOfAccount.International,1000.00,null);

        System.out.println(result);
        assertEquals(result,null);
        Mockito.verify(customerUtilMock,Mockito.never()).openAccount(RegularAccount);


    }

    @Test
    public void addIbanNumberRegularAccount() {


        boolean result=customerUtil.addIbanNumber(RegularAccount,customer);
        assertEquals(result,false);
    }

    @Test
    public void addInterestRateSavingAccount() {

        boolean result=customerUtil.addInterestRate(SavingAccount);
        assertEquals(result,true);

    }
    @Test
    public void addInterestRateInternational() {

        boolean result=customerUtil.addInterestRate(InternationalAccount);
        assertEquals(result,false);

    }
    @Test
    public void addInterestRateRegular() {

        boolean result=customerUtil.addInterestRate(RegularAccount);
        assertEquals(result,false);

    }

    @Test
    public void openAccountInternational() {

        Account result=customerUtil.openAccount(InternationalAccount);
        assertEquals(result,InternationalAccount);
        Mockito.verify(employerUtil,Mockito.atLeastOnce()).accecptNewAccount(InternationalAccount);
    }
    @Test
    public void openAccountSaving() {
        Account result=customerUtil.openAccount(SavingAccount);
        assertEquals(result,SavingAccount);
        Mockito.verify(employerUtil,Mockito.atLeastOnce()).accecptNewAccount(SavingAccount);

    }



    @Test
    public void genarateAccountNumber() {



        String number="12"+customer.getCustomerNumber()+"003";
        String result=InternationalAccount.getNumberOfAccount();
        assertEquals(number,result);
    }
    @Test
    public void genarateCustomerNumber() {

        String result= customerUtil.genarateCustomerAccountNumber(customer);
        assertEquals(result,"003");
        Mockito.verify(accountImplDaoMock,Mockito.atLeastOnce()).getAllByCustomer(customer);


    }

    @Test
    public void generateIbanNumber()
    {



        String iBanNumber;
        String firstTwoLetterFromName=customer.getName().substring(0,2).toLowerCase();
        String firstTwoLetterFromSurname=customer.getSurname().substring(0,2).toLowerCase();
        String dateOfBirth=customer.getDateOfBirth().replace(".","");
        iBanNumber=firstTwoLetterFromName+firstTwoLetterFromSurname+dateOfBirth;
        String result=customerUtil.generateIbanNumber(customer);
        assertEquals(result,iBanNumber);
    }

    @Test
    public void loginCustomerIfValid() {


        String email="sahin.aliyev979@gmail.com";
        String password="123456";
        boolean result=customerUtil.loginCustomer(email,password);
        assertEquals(result,true);
        Mockito.verify(customerImplDaoMock,Mockito.atLeastOnce()).getAll();
    }
    @Test
    public void loginCustomerIfPasswordNotValid() {


        String email="sahin.aliyev979@gmail.com";
        String password="a12345678";
        boolean result=customerUtil.loginCustomer(email,password);
        assertEquals(result,false);
        Mockito.verify(customerImplDaoMock,Mockito.atLeastOnce()).getAll();
    }
    @Test
    public void loginCustomerIfValidAndEmailUpperCase() {


        String email="Sahin.Aliyev979@gmail.com";
        String password="123456";
        boolean result=customerUtil.loginCustomer(email,password);
        assertEquals(result,true);
        Mockito.verify(customerImplDaoMock,Mockito.atLeastOnce()).getAll();
    }
    @Test
    public void loginCustomerifEmailNotValid() {


        String email="sahin1.aliyev979@gmail.com";
        String password="123456";
        boolean result=customerUtil.loginCustomer(email,password);
        assertEquals(result,false);
        Mockito.verify(customerImplDaoMock,Mockito.atLeastOnce()).getAll();
    }
}
