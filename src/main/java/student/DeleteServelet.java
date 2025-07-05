package student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Dbconnection;

/**
 * Servlet implementation class DeleteServelet
 */
@WebServlet("/DeleteServelet")
public class DeleteServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html"); 
		 try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = Dbconnection.getConnection();
			String name=request.getParameter("name");
			
			String sql = "delete from detail where  Name = ?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, name);
	      
	       
	       ps.executeUpdate();
	        	  response.sendRedirect("HTML/signup.html");
		 }
		 catch(Exception e) {
			 e.printStackTrace();
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
