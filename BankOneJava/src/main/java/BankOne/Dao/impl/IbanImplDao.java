package BankOne.Dao.impl;

import BankOne.Config.Config;
import BankOne.beans.Account;
import BankOne.Dao.inter.IbanInterDao;
import BankOne.beans.IbanClass;

import java.util.List;

public class IbanImplDao implements IbanInterDao {
    Config config;
    @Override
    public List<IbanClass> getAll() {
        return config.getListOfIbans1();
    }

    @Override
    public IbanClass getIbanByAccount(Account account) {
        List<IbanClass>listOfIbans=getAll();
        for (IbanClass iBan:listOfIbans) {
            if(iBan.getAccountNumber().equals(account.getNumberOfAccount()))
            {
                return  iBan;
            }

        }
        return null;
    }


}
