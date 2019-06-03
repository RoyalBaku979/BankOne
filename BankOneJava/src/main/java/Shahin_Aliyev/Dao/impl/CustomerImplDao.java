package Shahin_Aliyev.Dao.impl;

import Shahin_Aliyev.Config.Config;
import Shahin_Aliyev.Dao.inter.CustomerInterDao;
import Shahin_Aliyev.beans.Customer;
import com.sun.org.apache.xalan.internal.xsltc.dom.SortingIterator;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CustomerImplDao implements CustomerInterDao {
    Config config;
    @Override
    public Set<Customer> getAll() {

        return config.getListofCustomer1();
    }
    @Override
    public Set<Customer> getFiveNewClient(){
     Set<Customer> setOfFiveNewCustomer=new TreeSet<>();
     int i=0;
        for (Customer customer:config.getListofCustomer1()) {
            if(i==5) break;
            i++;
            setOfFiveNewCustomer.add(customer);

        }
      return setOfFiveNewCustomer;
    }


}
