import domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MysqlTest {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/0902?characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8",
                    "root",
                    "123123"
            );
            String sql = "select * from user where username = ?";
            preparedStatement =connection.prepareStatement(sql);
            preparedStatement.setString(1,"李四");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setSex(resultSet.getString("sex"));
                user.setAddress(resultSet.getString("address"));
                user.setBirthday(resultSet.getDate("birthday"));
                System.out.println(user.toString());

            }

        }catch (Exception e){
            e.printStackTrace();
        }



    }

}
