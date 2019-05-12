package Shahin_Aliyev.Dao.impl;

import Shahin_Aliyev.Config.Config;
import Shahin_Aliyev.Dao.inter.CustomerInterDao;
import Shahin_Aliyev.beans.Customer;

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
