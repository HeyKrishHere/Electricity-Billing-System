
package electricity.billing.system;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
public class GenerateBill extends JFrame implements ActionListener {
    Choice cmonth;
    String meter;
    JButton bill;
    JTextArea area;
    GenerateBill(String meter)
    {
        this.meter=meter;
        setSize(500,700);
        setLocation(550,30);
        
        setLayout(new BorderLayout());
        JPanel p = new JPanel();
        JLabel heading = new JLabel("Generate Bill");
        JLabel meternumber = new JLabel(meter);
         cmonth = new Choice();
         cmonth.setBounds(300,200,200,20);
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
         p.add(cmonth);
         
         area = new JTextArea(50,15);
         area.setText("\n\n\t-----------Click on the-----------\n\t Generate Bill Button to get\n\t the bill of the Selected Month");
         area.setFont(new Font("Senserif",Font.ITALIC,18));
         
         JScrollPane pane = new JScrollPane(area);
        
         bill = new JButton("Generate bill");
         bill.addActionListener(this);
         p.add(heading);
         p.add(meternumber);
         add(p,"North");
         add(pane,"Center");
         add(bill,"South");
         
        
         
         setVisible(true);
         
        
    }
    public void actionPerformed(ActionEvent ae)
    {
      try{
         Conn c = new Conn();
            String month = cmonth.getSelectedItem();
            area.setText("\tTATA POWER PVT. LTD. \n Electricity bill generated for the month of "+month+",2023\n\n\n");
           ResultSet rs =  c.s.executeQuery("select * from customer where meter_no='"+meter+"'");   
        if(rs.next())
        {
            area.append("\n Customer Name:  "+rs.getString("name"));
            area.append("\n Meter Number:   "+rs.getString("meter_no"));
            area.append("\n Address:      "+rs.getString("address"));
            area.append("\n City:         "+rs.getString("city"));
            area.append("\n State:        "+rs.getString("state"));
            area.append("\n Email:        "+rs.getString("email"));
            area.append("\n Phone         "+rs.getString("phone"));
            
        }
        rs =  c.s.executeQuery("select * from meter_info where meter_no='"+meter+"'");   
        if(rs.next())
        {
            area.append("\n Meter Location:  "+rs.getString("meter_location"));
            area.append("\n Meter Type:      "+rs.getString("meter_type"));
            area.append("\n Phase Code:      "+rs.getString("phase_code"));
            area.append("\n Bill Type:       "+rs.getString("billtype"));
            area.append("\n Days:            "+rs.getString("days"));
        
        }   
        
        rs =  c.s.executeQuery("select * from tax");   
        if(rs.next())
        {
            area.append("\n Cost per unit:  "+rs.getString("cost_per_unit"));
            area.append("\n Meter rent:      "+rs.getString("meter_rent"));
            area.append("\n Service tax:      "+rs.getString("service_tax"));
            area.append("\n Swacch bharat cess:       "+rs.getString("swacch_bharat_cess"));
       
        
        }   
        
        rs =  c.s.executeQuery("select * from bill where meter_no='"+meter+"'AND month='"+cmonth.getSelectedItem()+"'");   
        if(rs.next())
        {
            area.append("\n Current month:  "+rs.getString("month"));
            area.append("\n Unit Consumed:      "+rs.getString("units"));
            area.append("\n Total payable:       "+rs.getString("totalbill"));
          
        } 
        
     
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String args[]) {
        new GenerateBill("");
    }
}
