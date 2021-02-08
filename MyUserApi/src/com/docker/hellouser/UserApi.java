package com.docker.hellouser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



public class UserApi extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UserApi() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		
		String firstname = request.getParameter("firstName");
		String lastame = request.getParameter("lastName");
		System.out.println(firstname + " "  + lastame);
		int isInsertSuccess = updateDB(firstname,lastame);
		if(isInsertSuccess ==1) {
			pw.println("<h1> Hello " + firstname + " " + lastame+"</h1>");
		}
		pw.close();
	}
	
	public int updateDB(String firstname, String lastname) {
		int response=-1;
		Connection conn = null;
		PreparedStatement stmt = null;
		   try{
		     
		      Class.forName("com.mysql.jdbc.Driver");
		    // conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdetails","root","Jan2021*");
		      
				
				 Context ctx = new InitialContext(); DataSource ds = (DataSource)
				  ctx.lookup("java:/comp/env/jdbc/userdetails");
				  
				  conn = ds.getConnection();
				 
		      
		      stmt = conn.prepareStatement("insert into User values(?,?)");
		      stmt.setString(1, firstname);
		      stmt.setString(2, lastname);
		      response =  stmt.executeUpdate();

		      
		  
		      stmt.close();
		      conn.close();
		 
		   }catch(Exception e){
		      
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
			
		return response;
	}

}
