package Shahin_Aliyev.Dao.impl;

import Shahin_Aliyev.Config.Config;
import Shahin_Aliyev.Dao.inter.TransactionInterDao;
import Shahin_Aliyev.beans.Account;
import Shahin_Aliyev.beans.Customer;
import Shahin_Aliyev.beans.Transaction;
import Shahin_Aliyev.beans.TypeOfTransfer;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class TrasactioInterDao implements TransactionInterDao {
    @Override
    public List<Transaction> getAll() {
        return Config.getLitsOfTransactions();
    }

    @Override
    public List<Transaction> getAllByCustomer(Customer customer) {

        return customer.getListFfTransaction();
    }

    @Override
    public List<Transaction> getAllByAccount(Account account) {

        List<Transaction> listOfTransaction = new ArrayList<>();
        for (Transaction tr : account.getCustomerAccount().getListFfTransaction()) {
            if (tr.getSenderAccount().getNumberOfAccount().equals(account.getNumberOfAccount()) ||
                    tr.getRecivierAccount().getNumberOfAccount().equals(account.getNumberOfAccount())) {
                listOfTransaction.add(tr);
            }

        }
        return listOfTransaction;
    }

    @Override
    public List<Transaction> getAllByType(TypeOfTransfer typeOfTransfer) {
        List<Transaction> listOfTransaction = new ArrayList<>();
        for (Transaction tr : Config.getLitsOfTransactions()) {
            if (tr.getTypeOfTransfer()==typeOfTransfer) {
                listOfTransaction.add(tr);
            }

        }
        return listOfTransaction;

    }

    @Override
    public List<Transaction> getAllByHistory(ZonedDateTime startDate, ZonedDateTime endDate) {

        List<Transaction> listOfTransaction = new ArrayList<>();
        for (Transaction transaction :Config.getLitsOfTransactions()) {
            if(transaction.getDateOfTransaction().isAfter(startDate) && transaction.getDateOfTransaction().isBefore(endDate))
            {
                listOfTransaction.add(transaction);
            }

        }
        return listOfTransaction;
    }
    @Override
    public List<Transaction> getAllByHistory(ZonedDateTime startDate, ZonedDateTime endDate,Customer customer) {

        List<Transaction> listOfTransaction = new ArrayList<>();
        for (Transaction transaction :customer.getListFfTransaction()) {
            if(transaction.getDateOfTransaction().isAfter(startDate) && transaction.getDateOfTransaction().isBefore(endDate))
            {
                listOfTransaction.add(transaction);
            }

        }
        return listOfTransaction;
    }

    @Override
    public List<Transaction> getAllByDay(ZonedDateTime day) {
        List<Transaction> listOfTransaction = new ArrayList<>();
        for (Transaction transaction :Config.getLitsOfTransactions()) {
            if(transaction.getDateOfTransaction().equals(day))
            {
                listOfTransaction.add(transaction);
            }

        }
        return listOfTransaction;
    }
    @Override
    public List<Transaction> getAllByDay(ZonedDateTime day,Customer customer) {
        List<Transaction> listOfTransaction = new ArrayList<>();
        for (Transaction transaction :customer.getListFfTransaction()) {
            if(transaction.getDateOfTransaction().equals(day))
            {
                listOfTransaction.add(transaction);
            }

        }
        return listOfTransaction;
    }

    @Override
    public boolean update(Transaction transaction) {
        return false;
    }

    @Override
    public boolean add(Transaction transaction) {
        return false;
    }

    @Override
    public boolean remove(Transaction transaction) {
        return false;
    }
}
