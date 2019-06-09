package BankOne.Dao.inter;

import BankOne.beans.Account;
import BankOne.beans.Customer;
import BankOne.beans.TypeOfAccount;

import java.util.List;

public interface AccountInterDao {
    public List<Account> getAll();
    public  List<Account> getAllByCustomer(Customer customer);
    public List<Account> getAllByType(TypeOfAccount typeOfAccount);

}
