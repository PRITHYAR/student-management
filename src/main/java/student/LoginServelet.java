package student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Dbconnection;

/**
 * Servlet implementation class LoginServelet
 */
@WebServlet("/LoginServelet")
public class LoginServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServelet() {
        super();
        
		
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		boolean isValid=false;
		     try {
		    	Connection con =Dbconnection.getConnection();
		    	String name=request.getParameter("name");
		    	String password=request.getParameter("password");
		    	String sql="select * from detail where Name=? and Password=?";
		    	PreparedStatement ps= con.prepareStatement(sql);
		    	ps.setString(1,name);
		    	ps.setString(2,password);
		    	ResultSet rs=ps.executeQuery();
		    	if(rs.next()) {
		    	 isValid=true;
		    	}
		    	
		    	
		     }
		     catch(Exception e){
		    	 e.printStackTrace();
		    	 System.out.println("error");
		     }
	   if(isValid) {
		 	String name=request.getParameter("name");
		   response.sendRedirect("HTML/dashboard.html?username="+name);
	   }
	   else {
		
		   response.sendRedirect("HTML/login.html?error1=invalidusername");
	   }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
