package DB_Controller;

import java.sql.*;

public class Login {

    static String jdbcUrl = "jdbc:mysql://localhost:3306/catchmind";// ����ϴ� �����ͺ��̽����� ������ url
    static String userId = "root";// ����ڰ���
    static String userPass = "12345";// ����� �н�����
    
    public Login(){
    	try {
            Class.forName("com.mysql.jdbc.Driver");// ����̹� �ε�: DriverManager�� ���
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        }
    }

	public static void IDCheck() {
		// TODO Auto-generated method stub
		Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    try {
	    	conn = DriverManager.getConnection(jdbcUrl, userId, userPass);// Connection ��ü�� ��
	        stmt = conn.createStatement();// Statement ��ü�� ��
	            
//	        stmt.execute(sql);
	    } catch (SQLException e) {
	        System.out.println("SQLException: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	     	if(rs != null) try {rs.close();}catch(SQLException e){}
	       	if(stmt != null) try {stmt.close();}catch(SQLException e){}
	       	if(conn != null) try {conn.close();}catch(SQLException e){}
	    }
	}

	public static void FindPassword() {
		// TODO Auto-generated method stub
		
	}

	public static void FindID() {
		// TODO Auto-generated method stub
		
	}
}
