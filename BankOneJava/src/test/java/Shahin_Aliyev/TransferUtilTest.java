package Shahin_Aliyev;

import Shahin_Aliyev.Config.Config;
import Shahin_Aliyev.Util.CustomerUtil;
import Shahin_Aliyev.Util.TransferUtil;
import Shahin_Aliyev.beans.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;

public class TransferUtilTest {
    private  static CustomerUtil customerUtil;
    private static Customer customer;
    private static Account SavingAccount;
    private static IbanClass ibanClass;
    private static Account RegularAccount;
    private static Account InternationalAccount;
    @InjectMocks
        TransferUtil transferUtil;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

    customerUtil=new CustomerUtil();

        customer=Customer.getInstance();
        customer.setCustomerNumber("00001");
        customer.setEmail("sahin.aliyeb979@gmail.com");
        customer.setDateOfBirth("12.06.1993");
        customer.setName("Sahin");
        customer.setSurname("Aliyev");
        customer.setPassword("123456");
        customer.setAmountOfInternationalTransfer(0);
        customer.setDateOfJoinedBank(ZonedDateTime.now());
        SavingAccount=Account.getInstance();
        SavingAccount.setCustomerAccount(customer);
        SavingAccount.setTypeOfAccount(TypeOfAccount.Saving);
        SavingAccount.setAmount(100);
        SavingAccount.setNumberOfAccount("1200001001");
        Percentage percentage=new Percentage();
        percentage.setPercentage(0.01);
        percentage.setAccountNumber(SavingAccount.getNumberOfAccount());

        SavingAccount.setTransactionnumber(0);



        RegularAccount=Account.getInstance();
        RegularAccount.setTypeOfAccount(TypeOfAccount.Regular);
        RegularAccount.setCustomerAccount(customer);
         RegularAccount.setAmount(100);
        RegularAccount.setNumberOfAccount("1200001002");
        RegularAccount.setTransactionnumber(0);


        InternationalAccount=Account.getInstance();
        InternationalAccount.setCustomerAccount(customer);
        InternationalAccount.setTypeOfAccount(TypeOfAccount.International);
        InternationalAccount.setTransactionnumber(0);
        InternationalAccount.setAmount(100);
        InternationalAccount.setNumberOfAccount("1200001003");
        ibanClass=new IbanClass();
        ibanClass.setIBAN("0123456789");
        ibanClass.setAccountNumber(InternationalAccount.getNumberOfAccount());


        customerUtil.addInterestRate(SavingAccount);

    }
    @Test
    public void createTransactionIfNotValid1(){



       Transaction result=transferUtil.createTransaction(InternationalAccount,InternationalAccount, TypeOfTransfer.WIRE,100.0);
        assertEquals(result,null);

    }
    @Test
    public void createTransactionIfNotValid2(){



        Transaction result=transferUtil.createTransaction(InternationalAccount,SavingAccount, TypeOfTransfer.WIRE,100.0);
        assertEquals(result,null);

    }
    @Test
    public void createTransactionIfWire(){



        Transaction transaction=transferUtil.createTransaction(InternationalAccount,RegularAccount, TypeOfTransfer.WIRE,100.0);
        String result=transaction.getTransactionNumber();
        assertEquals(result,customer.getAmountOfInternationalTransfer()+"");

    }
    @Test
    public void createTransactionIfDebit(){



        Transaction transaction=transferUtil.createTransaction(InternationalAccount,RegularAccount, TypeOfTransfer.WIRE,100.0);
        String result=transaction.getTransactionNumber();
        System.out.println("result:"+result);
        assertEquals(result,InternationalAccount.getTransactionnumber()+"");

    }
    @Test
    public void saveDebitTransferIfDebit(){

        Transaction transaction=new Transaction();
        Transaction t=transferUtil.saveDebitTransfer(RegularAccount,transaction);
        TypeOfTransfer  type=t.getTypeOfTransfer();
        assertEquals(type,TypeOfTransfer.DEBIT);


    }

    @Test
    public void getTransactionNumberForWireTransfer(){



        int result=transferUtil.getTransactionNumber(InternationalAccount);
        assertEquals(result,InternationalAccount.getCustomerAccount().getAmountOfInternationalTransfer());


    }
    @Test
    public void getTransactionNumberIfCreditTransfer(){


        int result=transferUtil.getTransactionNumber(RegularAccount);
        assertEquals(result,RegularAccount.getTransactionnumber());


    }
    @Test
    public void debitTransferIfAccountRegular(){


        boolean result=transferUtil.debitTransfer(RegularAccount,100.0);
        assertEquals(result,true);


    }
    @Test
    public void debitTransferIfSaving(){


        boolean result=transferUtil.debitTransfer(SavingAccount,100.0);
        assertEquals(result,false);


    }
    @Test
    public void debitTransferIfInternational(){

        boolean result=transferUtil.debitTransfer(InternationalAccount,100.0);
        assertEquals(result,false);


    }
    @Test
    public void doneTransferForWire(){



        boolean result=transferUtil.doneTransfer(InternationalAccount,RegularAccount,100.0,TypeOfTransfer.WIRE);
        assertEquals(result,true);


    }
    @Test
    public void doneTransferNotCorrectForWire(){


        boolean result=transferUtil.doneTransfer(InternationalAccount,SavingAccount,100.0,TypeOfTransfer.WIRE);
        assertEquals(result,false);


    }
    @Test
    public void doneTransferForWireTransfer(){


        boolean result=transferUtil.doneTransfer(InternationalAccount,InternationalAccount,100.0,TypeOfTransfer.WIRE);
        assertEquals(result,false);


    }
    @Test
    public void checkAmountOfBalance(){

        double amount=InternationalAccount.getAmount();
        boolean result=transferUtil.checkAmountOfBalance(InternationalAccount,RegularAccount,amount,TypeOfTransfer.WIRE);
        assertEquals(result,true);
    }
    @Test
    public void checkAmountOfBalanceIfTransferAmountLess(){


        double amount=InternationalAccount.getAmount()-0.001;
        boolean result=transferUtil.checkAmountOfBalance(InternationalAccount,RegularAccount,amount,TypeOfTransfer.WIRE);
        assertEquals(result,true);
    }
    @Test
    public void checkAmountOfBalanceTransferAmountMore(){

        double amount=InternationalAccount.getAmount()+0.001;
        boolean result=transferUtil.checkAmountOfBalance(InternationalAccount,RegularAccount,amount,TypeOfTransfer.WIRE);
        assertEquals(result,false);
    }
    @Test
    public void SavingCreditTrasnferIfCorrect(){


        boolean result=transferUtil.savingCreditTrasnfer(InternationalAccount,RegularAccount,100);
        assertEquals(result,true);
    }
    @Test
    public void SavingCreditTrasnferIfAccountSaving(){

        boolean result=transferUtil.savingCreditTrasnfer(SavingAccount,SavingAccount,100);
        assertEquals(result,false);
    }
    @Test
    public void SavingCreditTrasnferifInternationalAccount(){


        boolean result=transferUtil.savingCreditTrasnfer(RegularAccount,InternationalAccount,100);
        assertEquals(result,false);
    }


    @Test
    public void RegularCreditTrasnfer(){

        boolean result=transferUtil.regularCreditTrasnfer(RegularAccount,RegularAccount,100);
        assertEquals(result,true);
    }
    @Test
    public void CreditTrasnfer1(){

        boolean result=transferUtil.creditTrasnfer(RegularAccount,InternationalAccount,100);
        assertEquals(result,false);
    }
    @Test
    public void CreditTrasnfer2(){

        boolean result=transferUtil.creditTrasnfer(SavingAccount,RegularAccount,100);
        assertEquals(result,true);
    }
    @Test
    public void CreditTrasnfer3(){

        boolean result=transferUtil.creditTrasnfer(RegularAccount,RegularAccount,100);
        assertEquals(result,true);
    }
    @Test
    public void WireTrasnfer1(){



       boolean result=transferUtil.wireTransfer(InternationalAccount,RegularAccount,100);
        assertEquals(result,true);
    }
    @Test
    public void WireTrasnfer2(){

        boolean result=transferUtil.wireTransfer(RegularAccount,RegularAccount,100);
        assertEquals(result,false);
    }
    @Test
    public void WireTrasnfer3(){

        boolean result=transferUtil.wireTransfer(SavingAccount,RegularAccount,100);
        assertEquals(result,false);
    }
    @Test
    public void doTransfer1(){

        boolean result=transferUtil.doTransfer(SavingAccount,RegularAccount,100,TypeOfTransfer.CREDIT);
        assertEquals(result,true);
    }
    @Test
    public void doTransfer2(){

        boolean result=transferUtil.doTransfer(InternationalAccount,RegularAccount,100,TypeOfTransfer.WIRE);
        assertEquals(result,true);
    }
    @Test
    public void doTransfer3(){


        boolean result=transferUtil.doTransfer(InternationalAccount,RegularAccount,100,TypeOfTransfer.CREDIT);
        assertEquals(result,false);
    }
    @Test
    public void doTransfer4(){


        boolean result=transferUtil.doTransfer(RegularAccount,RegularAccount,100,TypeOfTransfer.WIRE);
        assertEquals(result,false);
    }
    @Test
    public void doTransfer5(){


        boolean result=transferUtil.doTransfer(InternationalAccount,RegularAccount,100,TypeOfTransfer.DEBIT);
        assertEquals(result,false);
    }

}
