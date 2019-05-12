package uni.lodz.pl.java.Dao.inter;

import uni.lodz.pl.java.beans.Account;
import uni.lodz.pl.java.beans.Customer;
import uni.lodz.pl.java.beans.TypeOfAccount;

import java.util.List;

public interface AccountInterDao {
    public  List<Account> getAll();
    public  List<Account> getAllByCustomer( Customer customer);
    public List<Account> getAllByType (TypeOfAccount typeOfAccount);
    public boolean update(Account account);
    public boolean add(Account account);
    public boolean remove(Account account);
}
