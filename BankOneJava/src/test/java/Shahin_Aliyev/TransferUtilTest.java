package Shahin_Aliyev;

import Shahin_Aliyev.Config.Config;
import Shahin_Aliyev.Util.TransferUtil;
import Shahin_Aliyev.beans.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransferUtilTest {
    public Account createSenderAccountforTest(TypeOfAccount typeOfAccount){
        Account account=Account.getInstance();
        account.setTypeOfAccount(typeOfAccount);
        account.setAmount(1000);
        Customer customer=Customer.getInstance();
        customer.setAmountOfInternationalTransfer(2);
         account.setNumberOfAccount("123456");
        account.setCustomerAccount(customer);
        account.setTransactionnumber(1);
        Config.AddListOfAccount(account);
        return account;

    }
    @Test
    public void createTransaction(){
         Account senderAccount=createSenderAccountforTest(TypeOfAccount.International);
         Account recivierAccou=createSenderAccountforTest(TypeOfAccount.International);
        TransferUtil transferUtil=new TransferUtil();
      Transaction transaction=transferUtil.createTransaction(senderAccount,recivierAccou, TypeOfTransfer.WIRE,100.0);
       String result=transaction.getTransactionNumber();
        assertEquals(result,senderAccount.getCustomerAccount().getAmountOfInternationalTransfer()+"");

    }
    @Test
    public void saveDebitTransfer(){
        TransferUtil transferUtil=new TransferUtil();
        Account senderAccount=createSenderAccountforTest(TypeOfAccount.International);
        Account recivierAccount=createSenderAccountforTest(TypeOfAccount.International);
        Transaction transaction=new Transaction();
        Transaction t=transferUtil.saveDebitTransfer(recivierAccount,transaction);
        TypeOfTransfer  type=t.getTypeOfTransfer();
        assertEquals(type,TypeOfTransfer.DEBIT);


    }
    @Test
    public void getTransactionNumber1(){
        TransferUtil transferUtil=new TransferUtil();
        Account account=createSenderAccountforTest(TypeOfAccount.International);

        int result=transferUtil.getTransactionNumber(account);
        assertEquals(result,account.getCustomerAccount().getAmountOfInternationalTransfer());


    }
    @Test
    public void getTransactionNumber2(){
        TransferUtil transferUtil=new TransferUtil();
        Account account=createSenderAccountforTest(TypeOfAccount.Regular);

        int result=transferUtil.getTransactionNumber(account);
        assertEquals(result,account.getTransactionnumber());


    }
    @Test
    public void debitTransfer1(){
        TransferUtil transferUtil=new TransferUtil();
        Account accountrevier=createSenderAccountforTest(TypeOfAccount.Regular);

        boolean result=transferUtil.debitTransfer(accountrevier,100.0);
        assertEquals(result,true);


    }
    @Test
    public void debitTransfer2(){
        TransferUtil transferUtil=new TransferUtil();
        Account accountrevier=createSenderAccountforTest(TypeOfAccount.Saving);

        boolean result=transferUtil.debitTransfer(accountrevier,100.0);
        assertEquals(result,false);


    }
    @Test
    public void debitTransfer3(){
        TransferUtil transferUtil=new TransferUtil();
        Account accountrevier=createSenderAccountforTest(TypeOfAccount.International);

        boolean result=transferUtil.debitTransfer(accountrevier,100.0);
        assertEquals(result,false);


    }
    @Test
    public void doneTransfer1(){
        TransferUtil transferUtil=new TransferUtil();
        Account senderAccount=createSenderAccountforTest(TypeOfAccount.International);
        Account reciverAccount=createSenderAccountforTest(TypeOfAccount.Regular);

        boolean result=transferUtil.doneTransfer(senderAccount,reciverAccount,100.0,TypeOfTransfer.WIRE);
        assertEquals(result,true);


    }
    @Test
    public void doneTransfer2(){
        TransferUtil transferUtil=new TransferUtil();
        Account senderAccount=createSenderAccountforTest(TypeOfAccount.International);
        Account reciverAccount=createSenderAccountforTest(TypeOfAccount.Saving);

        boolean result=transferUtil.doneTransfer(senderAccount,reciverAccount,100.0,TypeOfTransfer.WIRE);
        assertEquals(result,false);


    }
    @Test
    public void doneTransfer3(){
        TransferUtil transferUtil=new TransferUtil();
        Account senderAccount=createSenderAccountforTest(TypeOfAccount.International);
        Account reciverAccount=createSenderAccountforTest(TypeOfAccount.International);

        boolean result=transferUtil.doneTransfer(senderAccount,reciverAccount,100.0,TypeOfTransfer.WIRE);
        assertEquals(result,false);


    }
    @Test
    public void checkAmountOfBalance1(){
        TransferUtil transferUtil=new TransferUtil();
        Account senderAccount=createSenderAccountforTest(TypeOfAccount.International);
        Account reciverAccount=createSenderAccountforTest(TypeOfAccount.Regular);
        double amount=senderAccount.getAmount();
        boolean result=transferUtil.checkAmountOfBalance(senderAccount,reciverAccount,amount,TypeOfTransfer.WIRE);
        assertEquals(result,true);
    }
    @Test
    public void checkAmountOfBalance2(){
        TransferUtil transferUtil=new TransferUtil();
        Account senderAccount=createSenderAccountforTest(TypeOfAccount.International);
        Account reciverAccount=createSenderAccountforTest(TypeOfAccount.Regular);
        double amount=senderAccount.getAmount()-0.001;
        boolean result=transferUtil.checkAmountOfBalance(senderAccount,reciverAccount,amount,TypeOfTransfer.WIRE);
        assertEquals(result,true);
    }
    @Test
    public void checkAmountOfBalance3(){
        TransferUtil transferUtil=new TransferUtil();
        Account senderAccount=createSenderAccountforTest(TypeOfAccount.International);
        Account reciverAccount=createSenderAccountforTest(TypeOfAccount.Regular);
        double amount=senderAccount.getAmount()+0.001;
        boolean result=transferUtil.checkAmountOfBalance(senderAccount,reciverAccount,amount,TypeOfTransfer.WIRE);
        assertEquals(result,false);
    }
    @Test
    public void SavingCreditTrasnfer1(){
        TransferUtil transferUtil=new TransferUtil();
        Account senderAccount=createSenderAccountforTest(TypeOfAccount.Saving);
        Account reciverAccount=createSenderAccountforTest(TypeOfAccount.Regular);

        boolean result=transferUtil.SavingCreditTrasnfer(senderAccount,reciverAccount,100);
        assertEquals(result,true);
    }
    @Test
    public void SavingCreditTrasnfer2(){
        TransferUtil transferUtil=new TransferUtil();
        Account senderAccount=createSenderAccountforTest(TypeOfAccount.Saving);
        Account reciverAccount=createSenderAccountforTest(TypeOfAccount.Saving);

        boolean result=transferUtil.SavingCreditTrasnfer(senderAccount,reciverAccount,100);
        assertEquals(result,false);
    }
    @Test
    public void SavingCreditTrasnfer3(){
        TransferUtil transferUtil=new TransferUtil();
        Account senderAccount=createSenderAccountforTest(TypeOfAccount.Saving);
        Account reciverAccount=createSenderAccountforTest(TypeOfAccount.International);

        boolean result=transferUtil.SavingCreditTrasnfer(senderAccount,reciverAccount,100);
        assertEquals(result,false);
    }


    @Test
    public void RegularCreditTrasnfer(){
        TransferUtil transferUtil=new TransferUtil();
        Account senderAccount=createSenderAccountforTest(TypeOfAccount.International);
        Account reciverAccount=createSenderAccountforTest(TypeOfAccount.Regular);
        boolean result=transferUtil.RegularCreditTrasnfer(senderAccount,reciverAccount,100);
        assertEquals(result,true);
    }
    @Test
    public void CreditTrasnfer1(){
        TransferUtil transferUtil=new TransferUtil();
        Account reciverAccount=createSenderAccountforTest(TypeOfAccount.Regular);
        Account senderAccount=createSenderAccountforTest(TypeOfAccount.International);
        boolean result=transferUtil.CreditTrasnfer(senderAccount,reciverAccount,100);
        assertEquals(result,false);
    }
    @Test
    public void CreditTrasnfer2(){
        TransferUtil transferUtil=new TransferUtil();
        Account senderAccount=createSenderAccountforTest(TypeOfAccount.Saving);
        Account reciverAccount=createSenderAccountforTest(TypeOfAccount.Regular);
        boolean result=transferUtil.CreditTrasnfer(senderAccount,reciverAccount,100);
        assertEquals(result,true);
    }
    @Test
    public void CreditTrasnfer3(){
        TransferUtil transferUtil=new TransferUtil();
        Account senderAccount=createSenderAccountforTest(TypeOfAccount.Regular);
        Account reciverAccount=createSenderAccountforTest(TypeOfAccount.Regular);
        boolean result=transferUtil.CreditTrasnfer(senderAccount,reciverAccount,100);
        assertEquals(result,true);
    }
    @Test
    public void WireTrasnfer1(){
        TransferUtil transferUtil=new TransferUtil();
        Account senderAccount=createSenderAccountforTest(TypeOfAccount.International);
        Account reciverAccount=createSenderAccountforTest(TypeOfAccount.Regular);


       boolean result=transferUtil.WireTransfer(senderAccount,reciverAccount,100);
        assertEquals(result,true);
    }
    @Test
    public void WireTrasnfer2(){
        TransferUtil transferUtil=new TransferUtil();
        Account senderAccount=createSenderAccountforTest(TypeOfAccount.Regular);
        Account reciverAccount=createSenderAccountforTest(TypeOfAccount.Regular);
        boolean result=transferUtil.WireTransfer(senderAccount,reciverAccount,100);
        assertEquals(result,false);
    }
    @Test
    public void WireTrasnfer3(){
        TransferUtil transferUtil=new TransferUtil();
        Account senderAccount=createSenderAccountforTest(TypeOfAccount.Saving);
        Account reciverAccount=createSenderAccountforTest(TypeOfAccount.Regular);
        boolean result=transferUtil.WireTransfer(senderAccount,reciverAccount,100);
        assertEquals(result,false);
    }
    @Test
    public void doTransfer1(){
        TransferUtil transferUtil=new TransferUtil();
        Account senderAccount=createSenderAccountforTest(TypeOfAccount.Saving);
        Account reciverAccount=createSenderAccountforTest(TypeOfAccount.Regular);
        boolean result=transferUtil.doTransfer(senderAccount,reciverAccount,100,TypeOfTransfer.CREDIT);
        assertEquals(result,true);
    }
    @Test
    public void doTransfer2(){
        TransferUtil transferUtil=new TransferUtil();
        Account senderAccount=createSenderAccountforTest(TypeOfAccount.International);
        Account reciverAccount=createSenderAccountforTest(TypeOfAccount.Regular);
        boolean result=transferUtil.doTransfer(senderAccount,reciverAccount,100,TypeOfTransfer.WIRE);
        assertEquals(result,true);
    }
    @Test
    public void doTransfer3(){
        TransferUtil transferUtil=new TransferUtil();
        Account senderAccount=createSenderAccountforTest(TypeOfAccount.International);
        Account reciverAccount=createSenderAccountforTest(TypeOfAccount.Regular);

        boolean result=transferUtil.doTransfer(senderAccount,reciverAccount,100,TypeOfTransfer.CREDIT);
        assertEquals(result,false);
    }
    @Test
    public void doTransfer4(){
        TransferUtil transferUtil=new TransferUtil();
        Account senderAccount=createSenderAccountforTest(TypeOfAccount.Regular);
        Account reciverAccount=createSenderAccountforTest(TypeOfAccount.Regular);

        boolean result=transferUtil.doTransfer(senderAccount,reciverAccount,100,TypeOfTransfer.WIRE);
        assertEquals(result,false);
    }
    @Test
    public void doTransfer5(){
        TransferUtil transferUtil=new TransferUtil();
        Account senderAccount=createSenderAccountforTest(TypeOfAccount.International);
        Account reciverAccount=createSenderAccountforTest(TypeOfAccount.Regular);

        boolean result=transferUtil.doTransfer(senderAccount,reciverAccount,100,TypeOfTransfer.DEBIT);
        assertEquals(result,false);
    }

}
