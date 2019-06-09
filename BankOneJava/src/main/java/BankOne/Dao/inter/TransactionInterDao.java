package BankOne.Dao.inter;

import BankOne.beans.Account;
import BankOne.beans.Customer;
import BankOne.beans.Transaction;
import BankOne.beans.TypeOfTransfer;
import BankOne.beans.*;

import java.time.ZonedDateTime;
import java.util.List;

public interface TransactionInterDao {
    public List<Transaction> getAll();
    public List<Transaction> getAllByCustomer(Customer Customer);
    public List<Transaction> getAllByAccount(Account account);
    public List<Transaction> getAllByType(TypeOfTransfer typeOfTransfer);
    public List<Transaction> getAllByHistory(ZonedDateTime startDate, ZonedDateTime endDate);
    public List<Transaction> getAllByDay(ZonedDateTime day);
    public List<Transaction> getFiveHigestTransactionByAmount();
    public List<Transaction> getAllByDay(ZonedDateTime day, Customer customer);
    public List<Transaction> getAllByHistory(ZonedDateTime startDate, ZonedDateTime endDate, Customer customer);

}
