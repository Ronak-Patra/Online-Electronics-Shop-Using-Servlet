import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;

public class signup extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try{
            String firstname, lastname, contactno, emailid, password; 
            firstname = request.getParameter("firstname");
            lastname = request.getParameter("lastname");
            contactno = request.getParameter("contactno");
            emailid = request.getParameter("emailid");
            password = request.getParameter("password");
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","");
            
            String query = "insert into signup values(?,?,?,?,?)";
            
            PreparedStatement ps =con.prepareStatement(query);
            
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, contactno);
            ps.setString(4, emailid);
            ps.setString(5,password);
            ps.executeUpdate();
            con.close();
            
            out.print("record saved successfully!");
            
            RequestDispatcher rd=request.getRequestDispatcher("shop.html");
            rd.forward(request, response);
        }
        catch(Exception e){
            out.println(e);
        }

    }
}
