package uni.lodz.pl.java.Dao.impl;

import uni.lodz.pl.java.Config.Config;
import uni.lodz.pl.java.Dao.inter.IbanDaoInter;
import uni.lodz.pl.java.beans.Account;
import uni.lodz.pl.java.beans.IbanClass;

import java.util.ArrayList;
import java.util.List;

public class IbanImplDao implements IbanDaoInter {
    @Override
    public List<IbanClass> getAll() {
        return Config.getListofIbans();
    }

    @Override
    public IbanClass getIbanByAccount(Account account) {
        List<IbanClass>listOfIbans=Config.getListofIbans();
        List<IbanClass>listOfIbansByTAccount=new ArrayList<>();
        for (IbanClass iBan:listOfIbans) {
            if(iBan.getAccountNumber().equals(account.getNumberOfAccount()))
            {
                return  iBan;
            }

        }
        return null;
    }

    @Override
    public boolean update(IbanClass iban) {
        return false;
    }

    @Override
    public boolean add(IbanClass iban) {
        return false;
    }

    @Override
    public boolean remove(IbanClass iban) {
        return false;
    }
}
