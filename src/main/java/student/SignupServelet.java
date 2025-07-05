package student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Dbconnection;

/**
 * Servlet implementation class SignupServelet
 */
@WebServlet("/SignupServelet")
public class SignupServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setContentType("text/html"); 
		  
		   /*String url="jdbc:mysql://localhost:3306/login";
		   String user="root";
		   String pwd="";*/
	      try {
	    	  Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = Dbconnection.getConnection();
				PrintWriter pw=response.getWriter()	;
				System.out.println("Successfully connected to the server");
				String name=request.getParameter("name");
				String password=request.getParameter("password");
				String sql1="select * from detail where Name=? and Password=?";
				 PreparedStatement ps1=con.prepareStatement(sql1);
				 ps1.setString(1, name);
		    	  ps1.setString(2, password);
				  ResultSet rs=ps1.executeQuery();
				  if(rs.next()) {
					  response.sendRedirect("HTML/signup.html?error=Usernamealreadyexists");
				  }
				  else { 
	    	  String sql="insert into detail(Name,Password) values(? ,?)";
	    	  PreparedStatement ps=con.prepareStatement(sql);
	    	  ps.setString(1, name);
	    	  ps.setString(2, password);
	    	  ps.executeUpdate();
	     	  
		     response.sendRedirect("HTML/login.html");
				  }
	    	
	    	  
	    	 // response.sendRedirect("html/login.html")
	      }
	      catch(Exception e) {
	    	  e.printStackTrace();
	    	  System.out.println("error");
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

