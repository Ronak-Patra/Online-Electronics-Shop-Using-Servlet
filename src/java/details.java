import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.* ;

public class details extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try{
            String name, address, contactno, pay;
            name = request.getParameter("name");
            address = request.getParameter("address");
            contactno = request.getParameter("contactno");
            pay = request.getParameter("pay");

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root", "");
            String query = "insert into details values(?,?,?,?)";
            
            PreparedStatement ps= con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, contactno);
            ps.setString(4, pay);
            ps.executeUpdate() ;
            con.close();
            
            out.print("Your cart has been added ");
        }
        catch(Exception e){
            out.println(e);  
        }    
    }  
}
