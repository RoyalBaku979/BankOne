package Shahin_Aliyev.Dao.inter;

import Shahin_Aliyev.beans.Account;
import Shahin_Aliyev.beans.IbanClass;

import java.util.List;

public interface IbanDaoInter {
    public List<IbanClass> getAll();
    public IbanClass getIbanByAccount(Account account);
    public boolean update(IbanClass iban);
    public boolean add(IbanClass iban);
    public boolean remove(IbanClass iban);
}
