

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class SaveData
 */
@WebServlet("/Transactions")
public class Transactions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transactions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set response content type
	      response.setContentType("application/json;charset=utf-8");
	      try{
	         // Register JDBC driver
	         Class.forName(Constants.JDBC_DRIVER);

	         // Open a connection
	         Connection conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);

	         // Execute SQL query
	         Statement stmt = conn.createStatement();
	         
	         ResultSet rs = stmt.executeQuery(Constants.MYSQL_SELECT_TRANSACTION);

	         // Extract data from result set
	         JSONObject json = new JSONObject();
	         JSONArray array = new JSONArray();
	         JSONObject member =  new JSONObject();
	         
	         while(rs.next()){
	        
	        	 	String time  = rs.getString("time");
		            String name = rs.getString("name");
		            String otp = rs.getString("otp");
		            
	        	 member.append("time", time);
	        	 member.append("name", name);
	        	 member.append("otp", otp);
	        	 
	        	 }
	         array.put(member);
	         json.put("jsonarray", array);
	         
	         PrintWriter pw = response.getWriter(); 
	         pw.print(json.toString());
	         pw.close();
	         // Clean-up environment
	         rs.close();
	         stmt.close();
	         conn.close();
	      }catch(SQLException se){
	         //Handle errors for JDBC
	         se.printStackTrace();
	      }catch(Exception e){
	         //Handle errors for Class.forName
	         e.printStackTrace();
	      } //end try
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String name = request.getParameter("name");
		String time = request.getParameter("time");
		String otp = request.getParameter("otp");
		
		// Register JDBC driver
        try {
			Class.forName(Constants.JDBC_DRIVER);
			// Open a connection
	        Connection conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);
	        // Execute SQL query
	        PreparedStatement pst =(PreparedStatement) conn.prepareStatement(Constants.MYSQL_UPDATE_TRANSACTION);
	        pst.setString(1, name);
	        pst.setString(2, time);
	        pst.setString(3, otp);
	        
	        int i = pst.executeUpdate(); 
	          String msg=" ";

	          if(i!=0){  
	            msg="Record has been inserted";
	          }  
	          else{  
	            msg="failed to insert the data"; 
	           }  
	          
	          PrintWriter pw = response.getWriter(); 
		         pw.print(msg);
		         pw.close();
	          pst.close();
	        
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
