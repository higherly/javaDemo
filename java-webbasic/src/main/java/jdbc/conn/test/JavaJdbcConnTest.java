package jdbc.conn.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 1.原生的jdbc操作数据库流程？
 */
public class JavaJdbcConnTest {
    public static  void main(String [] args) throws Exception{
        //注册数据库驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        /*获取数据库连接*/
        Connection conn = DriverManager.getConnection("jdbc:mysql://101.132.119.68:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","4805885.asQW");
        /*获取sql会话对 象:PreparedStatement 和Statement,*/
        PreparedStatement preparedStatement = conn.prepareStatement("select * from student");
        /*执行sql处理结果集*/
        ResultSet resultSet =   preparedStatement.executeQuery();
        while (resultSet.next()){
              System.out.println(resultSet.getInt("id")+"--"+resultSet.getString(2));
        }
        /*关闭结果集，关闭会话对象,关闭连接*/
    }
}
