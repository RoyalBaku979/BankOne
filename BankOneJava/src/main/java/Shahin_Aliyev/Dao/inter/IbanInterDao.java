package Shahin_Aliyev.Dao.inter;

import Shahin_Aliyev.beans.Account;
import Shahin_Aliyev.beans.IbanClass;

import java.util.List;

public interface IbanInterDao {
    public List<IbanClass> getAll();
    public IbanClass getIbanByAccount(Account account);

}
