package electricity.billing.system;
import java.awt.Color;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

//here we want to create a table where all the data regaring bill of the custimer will be shown.
//here user can see only his/her information
public class BillDetails extends JFrame{
    String meter;
    BillDetails(String meter)
    {
        this.meter=meter;
        setSize(700,650);
        setLocation(400,150);
        getContentPane().setBackground(Color.WHITE);
        JTable table= new JTable();
        
        try{
            Conn c=  new Conn();
            String query ="select * from bill where meter_no='"+meter+"'";
            ResultSet rs = c.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));//we discuss it earlier
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        JScrollPane sp=new JScrollPane(table);
        sp.setBounds(0,0,700,650);
        add(sp);
        
        
        
        setVisible(true);
    }
    
    public static void main(String args[]) {
        
        new BillDetails("");
    }
}
