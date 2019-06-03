package Shahin_Aliyev.Dao.impl;

import Shahin_Aliyev.Config.Config;
import Shahin_Aliyev.Dao.inter.PercentageDao;
import Shahin_Aliyev.beans.Account;
import Shahin_Aliyev.beans.IbanClass;
import Shahin_Aliyev.beans.Percentage;

import java.util.ArrayList;
import java.util.List;

public class PercentageImplDao implements PercentageDao {
    Config config;
    @Override
    public List<Percentage> getAll() {
        return config.getListofPercentage1();
    }

    @Override
    public  Double getInterestByAccount(Account account) {
        List<Percentage>listofPercentage=config.getListofPercentage1();
        List<IbanClass>listofPercentageByTAccount=new ArrayList<>();
        for (Percentage percentage:listofPercentage) {
            if(percentage.getAccountNumber().equals(account.getNumberOfAccount()))
            {
                return  percentage.getPercentage();
            }

        }
        return null;
    }


}
