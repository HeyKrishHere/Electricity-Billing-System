package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class DepositDetails extends JFrame implements ActionListener{
    
    Choice meternumber,cmonth;
    JTable table;
    JButton search,print;
    
    DepositDetails(){
        
        setSize(700,700);
        setLocation(400,100);
        setLayout(null);
        
        JLabel lblmeternumber= new JLabel("Search by Meter Number");
        lblmeternumber.setBounds(20,20,150,20);
        add(lblmeternumber);
        
         meternumber = new Choice();
         meternumber.setBounds(180,20,150,20);
         add(meternumber);
        try{// similar way we did it previously
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select*from customer");
            while(rs.next()){
                
                meternumber.add(rs.getString("meter_no"));
                
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        JLabel lblmonth= new JLabel("Search by Month");
        lblmonth.setBounds(400,20,100,20);
        add(lblmonth);
        
         cmonth = new Choice();
         cmonth.setBounds(520,20,150,20);
         cmonth.add("January");
         cmonth.add("February");
         cmonth.add("March");
         cmonth.add("April");
         cmonth.add("May");
         cmonth.add("June");
         cmonth.add("July");
         cmonth.add("Auguest");
         cmonth.add("September");
         cmonth.add("October");
         cmonth.add("November");
         cmonth.add("December");
         add(cmonth);
         
         /*now here comes the most complex part of the project here we want to create a table whitin which we want to show the details
         
         1) First create an ojext of JTable class
         2) Then hit a query to get all the details of Bill table and store then into ResultSet
         3) After that add a jar file named rs2xml.jar 
         4) import a package named, net.proteanit.sql.DbUtils(used for JDBC)
         5) Now we are going to use DbUtils class to insert values into the table
         
         */
         table = new JTable();
         try{
             Conn c = new Conn();
             ResultSet rs = c.s.executeQuery("select*from bill");
             //here have all the values inside rs
             table.setModel(DbUtils.resultSetToTableModel(rs));
             
             /*inside DbUtils clas there exist a method called resultSetToTableModel() which convert the result set of a database query into a table model
              which we will us within setModel() hich is used to associate a data model with a graphical component, allowing you to display and manipulate 
             data in a structured way. To create a scroll bar we have to make a scroll using JScrolPane class*/
             
         }
         catch(Exception e)
         {
             e.printStackTrace();
         }
         
         //after that we have to create a scroll bar, coz in java there is no default scroll bar ulike html
             JScrollPane sp = new JScrollPane(table);
             sp.setBounds(0,100,700,600);
             add(sp);
             // here we have add some button using which we are goin to search 
             search = new JButton("Search");
             search.setBounds(20,70,80,20);
             search.addActionListener(this);
             add(search);
             
             print = new JButton("Print");
             print.setBounds(120,70,80,20);
             print.addActionListener(this);
             add(print);
             
             
             setVisible(true);
    }
   
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()== search)
        {
            String query ="select * from bill where meter_no='"+meternumber.getSelectedItem()+"' and month='"+cmonth.getSelectedItem()+"'";
            try{
               
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
               
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            try{
                table.print();// to print the table 
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        
    }
    
    public static void main(String args[]) {
        new DepositDetails();
    }
}
