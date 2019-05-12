package uni.lodz.pl.java.Dao.impl;

import uni.lodz.pl.java.Config.Config;
import uni.lodz.pl.java.Dao.inter.PercentageDao;
import uni.lodz.pl.java.beans.Account;
import uni.lodz.pl.java.beans.IbanClass;
import uni.lodz.pl.java.beans.Percentage;

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
