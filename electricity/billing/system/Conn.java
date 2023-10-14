
package electricity.billing.system;
import java.sql.*;
public class Conn {

    
    
    /*there total 5 steps in jdbc connetivity
    1) Register the driver class
    2) Creation the connection
    3) Create the statement
    4) Execute the query
    5) Cloce the connection
    */
            
    Connection c;
    Statement s;
    Conn(){
        
        try{
        /*Class.forName("com.mysql.cj.jdbc.Driver"); for register the driver 
        class it is not necessary in the latest jdbc it will do this autome0tically.*/
            c=DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs","root","Krishanu@2023");//connection creation
            System.out.println("Connected Successfully");
            s=c.createStatement();//statement creation5
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
    
}
