package Shahin_Aliyev.Dao.impl;

import Shahin_Aliyev.Config.Config;
import Shahin_Aliyev.Dao.inter.PercentageDao;
import Shahin_Aliyev.beans.Account;
import Shahin_Aliyev.beans.IbanClass;
import Shahin_Aliyev.beans.Percentage;

import java.util.ArrayList;
import java.util.List;

public class PercentageImplDao implements PercentageDao {
    @Override
    public List<Percentage> getAll() {
        return Config.getListofPercentage();
    }

    @Override
    public Percentage getInterestByAccount(Account account) {
        List<Percentage>listofPercentage=Config.getListofPercentage();
        List<IbanClass>listofPercentageByTAccount=new ArrayList<>();
        for (Percentage percentage:listofPercentage) {
            if(percentage.getAccountNumber().equals(account.getNumberOfAccount()))
            {
                return  percentage;
            }

        }
        return null;
    }

    @Override
    public boolean update(Percentage percentage) {
        return false;
    }

    @Override
    public boolean add(Percentage percentage) {
        return false;
    }

    @Override
    public boolean remove(Percentage percentage) {
        return false;
    }
}
