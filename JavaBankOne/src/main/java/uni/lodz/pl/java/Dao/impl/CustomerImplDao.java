package uni.lodz.pl.java.Dao.impl;

import uni.lodz.pl.java.Config.Config;
import uni.lodz.pl.java.Dao.inter.CustomerInterDao;
import uni.lodz.pl.java.beans.Customer;

import java.util.List;

public class CustomerImplDao implements CustomerInterDao {
    @Override
    public List<Customer> getAll() {
        return Config.getListofCustomer();
    }

    @Override
    public boolean update(Customer customer) {
        return false;
    }

    @Override
    public boolean add(Customer customer) {
        return false;
    }

    @Override
    public boolean remove(Customer customer) {
        return false;
    }
}
