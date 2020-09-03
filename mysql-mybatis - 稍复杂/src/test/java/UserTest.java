import Dao.IUserDao;
import domain.QueryVo;
import domain.QueryVolds;
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
import java.util.ArrayList;
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
        session = factory.openSession(true);
        userDao = session.getMapper(IUserDao.class);
    }

    @Test
    public void testFindOne(){
        User user = userDao.findById(41);
        Assert.assertEquals("张三",user.getUserName());
    }

    @Test
    public void testFindall(){
        List<User> users = userDao.findAll();
        Assert.assertEquals(21,users.size());
        for (User user:users) {
            System.out.println(user);
        }
    }
    @Test
    public void testSave (){
        User user = new User();
        user.setUserName("华泰星");
        user.setUserAddress("南京市建邺区");
        user.setUserSex("男");
        user.setUserBirthday(new Date());
        int id = userDao.saveUser(user);
        //Assert.assertEquals(1,id);
//        System.out.println(id);
        User savedUser = userDao.findById(user.getUserId());
        Assert.assertEquals("华泰星",savedUser.getUserName());
    }

    @Test
    public void testUpdateUser(){
        int id = 58;
        User user = userDao.findById(id);
        user.setUserAddress("南京建邺区！！");
        int res = userDao.updateUser(user);

        User savedUser = userDao.findById(id);
        Assert.assertEquals("南京建邺区！！",savedUser.getUserAddress());
    }
    @Test
    public void testDeleteUser(){
        int res = userDao.deleteUser(64);
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
        Assert.assertEquals(21,res);
    }

    @Test
    public void testQueryVo(){
        QueryVo vo = new QueryVo();
        vo.setName("%王%");
        vo.setAddress("%南京%");
        List<User> users = userDao.findByVo(vo);
        Assert.assertEquals(1,users.size());
    }

    @Test
    public  void testFindInIds(){
        QueryVolds queryVolds = new QueryVolds();
        List<Integer> list = new ArrayList<Integer>();
        list.add(41);
        list.add(48);
        list.add(67);
        queryVolds.setIds(list);
        List<User> users = userDao.findInIds(queryVolds);
        Assert.assertEquals(3,users.size());
        for (User user:users) {
            System.out.println(user);
        }
    }

    @After
    public void tearDown() throws Exception{
        //session.commit();
        session.close();
        in.close();
    }

}
