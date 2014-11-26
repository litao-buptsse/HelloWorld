import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by Tao Li on 11/10/14.
 */
public class HelloDruid {
    public static void main(String[] args) throws SQLException, IOException {
        Properties properties = new Properties();
        properties.load(HelloDruid.class.getResourceAsStream("db.properties"));
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setConnectProperties(properties);
        DruidPooledConnection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from student");
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int score = resultSet.getInt("score");
            System.out.println(name + ", " + score);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
