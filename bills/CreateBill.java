package com.login;

import java.io.*;  
import java.sql.*;  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
  
@WebServlet("/createBill")
public class CreateBill extends HttpServlet {  
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException { 
	
PrintWriter out = response.getWriter();  
	
/*HttpSession session= request.getSession();
if(session!=null){  
    String name=(String)session.getAttribute("user");  
      
    out.print("You may proceed User "+name);  
    }  
    else{  
        out.print("Please login first");  
        request.getRequestDispatcher("login.java").include(request, response);  
    }  
  */
response.setContentType("text/html");  
          
String n=request.getParameter("user");  
String p=request.getParameter("groupname");  
String e=request.getParameter("total");  
String c=request.getParameter("bill");  
String d=request.getParameter("date");  
/* String a=request.getParameter("Bill_appPer");  
String s=request.getParameter("Bill_status"); */ 


          
try{  
Class.forName("com.mysql.jdbc.Driver");  
Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/expensetracker",
		"root","1234");  
  
PreparedStatement pst=con.prepareStatement(  
"insert into bills values(?,?,?,?,?)");  
  
pst.setString(1,n);  
pst.setString(2,p);  
pst.setString(3,e);  
pst.setString(4,c);
pst.setString(5,d);
/*pst.setString(6,a);
pst.setString(7,s);*/
          
int i=pst.executeUpdate();  

PreparedStatement ps = con.prepareStatement("INSERT INTO pictures VALUES(?,?)");
File file = new File("C:/Users/Samadrita/Documents/Downloads/apache-tomcat-8.5.12-windows-x64/apache-tomcat-8.5.12/webapps/image/5.jpg");
FileInputStream fs = new FileInputStream(file);
ps.setInt(1,9);
ps.setBinaryStream(2,fs,fs.available());
int j = ps.executeUpdate();
if(j!=0){
	out.println("Image inserted successfully");
}
else{
	out.println("Problem in image insertion");
}	
 
if(i>0)  
out.print("Successfully Created!");  
      
          
}catch (Exception e2) {System.out.println(e2);}  
          
out.close();  
}  
  
}