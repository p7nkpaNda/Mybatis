import Dao.IUserDao;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class MysqlTest {
    public static void main(String[] args) {

        try {
            InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(in);
            SqlSession session = factory.openSession();
            IUserDao userDao = session.getMapper(IUserDao.class);
            List<User> users = userDao.findAll();
            for (User user : users){
                System. out .println(user);
            }
            session.close();
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
