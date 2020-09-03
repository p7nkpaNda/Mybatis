import Dao.IRoleDao;
import domain.Role;
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

public class RoleTest {
    InputStream in;
    SqlSessionFactoryBuilder builder;
    SqlSessionFactory factory;
    SqlSession session;
    IRoleDao roleDao;
    @Before
    public void setup() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        session = factory.openSession(true);
        roleDao = session.getMapper(IRoleDao.class);
    }

    @Test
    public void testFindAll(){
        List<Role> roles = roleDao.findAll();
        Assert.assertEquals(3,roles.size());
        for(Role role:roles){
            System.out.println("---每个角色的信息---");
            System.out.println(role);
            System.out.println(role.getUsers());
        }

    }



    @After
    public void tearDown() throws Exception{
        //session.commit();
        session.close();
        in.close();
    }
}
