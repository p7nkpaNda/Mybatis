import Dao.IAccountDao;
import domain.AccountUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class AccountUserTest {
    InputStream in;
    SqlSessionFactoryBuilder builder;
    SqlSessionFactory factory;
    SqlSession session;
    IAccountDao accountDao;
    @Before
    public void setup() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        session = factory.openSession(true);
        accountDao = session.getMapper(IAccountDao.class);
    }

    @Test
    public void testFindAll(){
        List<AccountUser> accountUsers = accountDao.findAll();
        for(AccountUser au:accountUsers){
            System.out.println(au);
        }
        Assert.assertEquals(22,accountUsers.size());

    }







    @After
    public void tearDown() throws Exception{
        //session.commit();
        session.close();
        in.close();
    }

}
