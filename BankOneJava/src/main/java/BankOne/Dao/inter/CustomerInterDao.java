package BankOne.Dao.inter;

import BankOne.beans.Customer;

import java.util.Set;

public interface CustomerInterDao {
    public  Set<Customer> getAll();
    public Set<Customer> getFiveNewClient();


}
