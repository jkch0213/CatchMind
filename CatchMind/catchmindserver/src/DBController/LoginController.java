package DBController;

import java.sql.*;
import com.mysql.jdbc.PreparedStatement;

public class LoginController {

    static String jdbcUrl = "jdbc:mysql://localhost:3306/catchmind";// 사용하는 데이터베이스명을 포함한 url
    static String userId = "root";// 사용자계정
    static String userPass = "12345";// 사용자 패스워드
    
    public LoginController(){
    	try {
            Class.forName("com.mysql.jdbc.Driver");// 드라이버 로딩: DriverManager에 등록
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        }
    }
    
    public static void Login(){
    	
    }
    
    public static void SignUp(){
    	
    }
    
    
	public static Boolean IDCheck(String id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	    	conn = DriverManager.getConnection(jdbcUrl, userId, userPass);// Connection 객체를 얻어냄
	    	stmt = (PreparedStatement) conn.prepareStatement(
	    			"SELECT id FROM users WHERE id = ?");
	    			stmt.setString(1, id);
	    	rs = stmt.executeQuery();
	    	rs.getString(id);
	    	
	    	
	    } catch (SQLException e) {
	        System.out.println("SQLException: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	     	if(rs != null) try {rs.close();}catch(SQLException e){}
	       	if(stmt != null) try {stmt.close();}catch(SQLException e){}
	       	if(conn != null) try {conn.close();}catch(SQLException e){}
	    }
		return null;
	}

	public static void FindPass() {
		// TODO Auto-generated method stub
		
	}

	public static void FindID() {
		// TODO Auto-generated method stub
		
	}
}
