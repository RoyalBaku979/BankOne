package BankOne.Dao.impl;

import BankOne.Config.Config;
import BankOne.beans.Account;
import BankOne.Dao.inter.PercentageDao;
import BankOne.beans.IbanClass;
import BankOne.beans.Percentage;

import java.util.ArrayList;
import java.util.List;

public class PercentageImplDao implements PercentageDao {
    Config config;
    @Override
    public List<Percentage> getAll() {
        return config.getListofPercentage1();
    }

    @Override
    public  Percentage getInterestByAccount(Account account) {
        List<Percentage>listofPercentage=getAll();
        List<IbanClass>listofPercentageByTAccount=new ArrayList<>();
        for (Percentage percentage:listofPercentage) {
            if(percentage.getAccountNumber().equals(account.getNumberOfAccount()))
            {
                return  percentage;
            }

        }
        return null;
    }


}
