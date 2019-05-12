package uni.lodz.pl.java.Dao.inter;

import uni.lodz.pl.java.beans.Account;
import uni.lodz.pl.java.beans.Customer;


import java.util.List;

public interface CustomerInterDao {
    public  List<Customer> getAll();

    public boolean update(Customer customer);
    public boolean add(Customer customer);
    public boolean remove(Customer customer);
}
