package Shahin_Aliyev.Dao.impl;

import Shahin_Aliyev.Config.Config;
import Shahin_Aliyev.Dao.inter.IbanInterDao;
import Shahin_Aliyev.beans.Account;
import Shahin_Aliyev.beans.IbanClass;

import java.util.ArrayList;
import java.util.List;

public class IbanImplDao implements IbanInterDao {
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


}
