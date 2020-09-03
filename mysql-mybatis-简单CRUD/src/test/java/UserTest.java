import Dao.IUserDao;
import domain.QueryVo;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserTest {
    InputStream in;
    SqlSessionFactoryBuilder builder;
    SqlSessionFactory factory;
    SqlSession session;
    IUserDao userDao;
    List<User> users;
    @Before
    public void setup() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
    }

    @Test
    public void testFindOne(){
        User user = userDao.findById(41);
        Assert.assertEquals("张三",user.getUsername());
    }

    @Test
    public void testSave (){
        User user = new User();
        user.setUsername("华泰");
        user.setAddress("南京市建邺区");
        user.setSex("男");
        user.setBirthday(new Date());
        int id = userDao.saveUser(user);
        //Assert.assertEquals(1,id);
//        System.out.println(id);
        User savedUser = userDao.findById(user.getId());
        Assert.assertEquals("华泰",savedUser.getUsername());
    }

    @Test
    public void testUpdateUser(){
        int id = 58;
        User user = userDao.findById(id);
        user.setAddress("南京建邺区！！");
        int res = userDao.updateUser(user);

        User savedUser = userDao.findById(id);
        Assert.assertEquals("南京建邺区！！",savedUser.getAddress());
    }
    @Test
    public void testDeleteUser(){
        int res = userDao.deleteUser(65);
        Assert.assertEquals(1,res);
    }

    @Test
    public void testFindByName(){
        List<User> users = userDao.findByName("%王%");
        Assert.assertEquals(2,users.size());
        for (User user:users) {
            System.out.println(user);
        }
    }
    @Test
    public void testCount(){
        int res = userDao.count();
        Assert.assertEquals(19,res);
    }

    @Test
    public void testQueryVo(){
        QueryVo vo = new QueryVo();
        vo.setName("%王%");
        vo.setAddress("%南京%");
        List<User> users = userDao.findByVo(vo);
        Assert.assertEquals(1,users.size());
    }


    @After
    public void tearDown() throws Exception{
        session.commit();
        session.close();
        in.close();
    }

}
