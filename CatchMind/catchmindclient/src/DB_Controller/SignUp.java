package DB_Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class SignUp {
 
    static String jdbcUrl = "jdbc:mysql://localhost:3306/catchmind";// ����ϴ� �����ͺ��̽����� ������ url
    static String userId = "root";// ����ڰ���
    static String userPass = "12345";// ����� �н�����
    
    String NAME;
    String ID;
    String PASSWORD;
    int REGI_NUM;
    
    public SignUp() {
    	try {
            Class.forName("com.mysql.jdbc.Driver");// ����̹� �ε�: DriverManager�� ���
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        }
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
 
            conn = DriverManager.getConnection(jdbcUrl, userId, userPass);// Connection ��ü�� ��
            stmt = conn.createStatement();// Statement ��ü�� ��
            
//            NAME = Client.SignUpFrame.NAMEText.paramString();
//            ID = Client.SignUpFrame.
            
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        } finally {
        	if(rs != null) try {rs.close();}catch(SQLException e){}
        	if(stmt != null) try {stmt.close();}catch(SQLException e){}
        	if(conn != null) try {conn.close();}catch(SQLException e){}
        }
    }
}