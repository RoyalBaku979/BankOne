package Shahin_Aliyev;

import Shahin_Aliyev.Util.BankUtil;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;


import static org.junit.Assert.*;

public class BankUtilTest {

    @Test
    public void createCustomer() {

    }

    @Test
    public void genarateCustomerNumber() {

        BankUtil bankUtil=new BankUtil();
        String result=bankUtil.genarateCustomerNumber();
        assertEquals(result,"00000");
    }
}