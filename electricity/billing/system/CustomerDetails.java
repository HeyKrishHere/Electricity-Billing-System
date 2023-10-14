package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class CustomerDetails extends JFrame implements ActionListener{
    
    Choice meternumber,cmonth;
    JTable table;
    JButton search,print;
    
    CustomerDetails(){
        
        setSize(700,650);
        setLocation(200,150);
        
        
         table = new JTable();
         try{
             Conn c = new Conn();
             ResultSet rs = c.s.executeQuery("select*from customer");
             //here have all the values inside rs
             table.setModel(DbUtils.resultSetToTableModel(rs));
             
             
             
         }
         catch(Exception e)
         {
             e.printStackTrace();
         }
         
         
             JScrollPane sp = new JScrollPane(table);
             add(sp);
             // here we have add some button using which we are goin to search 
            
             
             print = new JButton("Print");
             print.addActionListener(this);
             add(print,"South");
             
             
             setVisible(true);
    }
   
    public void actionPerformed(ActionEvent ae)
    {
        
            try{
                table.print();// to print the table 
            }
            catch(Exception e){
                e.printStackTrace();
            }
      
    }
    
    public static void main(String args[]) {
        new CustomerDetails();
    }
}
