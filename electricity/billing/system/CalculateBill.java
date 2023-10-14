package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.sql.*;
public class CalculateBill extends JFrame implements ActionListener{
    JTextField tfname,tfaddress,tfunits,tfcity,tfmail,tfphone;
    JButton next,cancel;
    JLabel lblname,labeladdress;
    Choice meternumber,cmonth;
/*This part is for admin use only only admin can see this part and can add 
new customer, admin wil generate a unique meter nunber and give it to the customer using which custo,er can signup and generate password then can login*/
    CalculateBill(){
        setSize(550, 500);
        setLocation(400,200);
        
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        add(p);
        
        JLabel heading = new JLabel("Calculate Bill ");
        heading.setBounds(180,10,200,25);
        heading.setFont(new Font("Tahoma",Font.PLAIN,24));
        p.add(heading);
        
        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(100,80,100,20);
        p.add(lblmeternumber);
        meternumber = new Choice();
        //here we dynamically fetch all the meter numbers from the database and add them onto choices. 
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select*from customer");//fetched the data from the data base and stoe then in resultset
            while(rs.next())//this will travese through the resultset
            {
                meternumber.add(rs.getString("meter_no"));//now we will fetch onlt the meter no from the row and add them on the Choices.
                
            }
   
         }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        meternumber.setBounds(240,80,200,20);
        p.add(meternumber);
        
        JLabel lblmeterno = new JLabel("Name");/* as we want to store the name and address of the person whose meter no. has selected 
        we have to hit the query again to dynamically insert the value based on the meter no.*/
        
        lblmeterno.setBounds(100,120,100,20);
        p.add(lblmeterno);
        
        lblname = new JLabel("");
        lblname.setBounds(240,120,100,20);
        p.add(lblname);
        
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(100,160,100,20);
        p.add(lbladdress);
        
        labeladdress = new JLabel();
        labeladdress.setBounds(240,160,200,20);
        p.add(labeladdress);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select*from customer where meter_no='"+meternumber.getSelectedItem()+"'");//hare we matches the meter number
            while(rs.next())
            {
                lblname.setText(rs.getString("name")); // here we add the name and address corosponding to that meter number
                labeladdress.setText(rs.getString("address"));
            }
   
         }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        /* now we have to change the name and address when we select any 
        other meter no. from the drop down, for that we have make a event so that when there is any change in the drop down the correspnding name and adrress should come in the name and address place*/
        
        meternumber.addItemListener(new ItemListener()//we use actionlistner for buttons and addItemlistner in Choice
        {
            public void itemStateChanged(ItemEvent ie){// we use action event for buttons and ItemEvent for Choice
                try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select*from customer where meter_no='"+meternumber.getSelectedItem()+"'");//hare we matches the meter number
            while(rs.next())
            {
                lblname.setText(rs.getString("name")); // here we add the name and address corosponding to that meter number
                labeladdress.setText(rs.getString("address"));
            }
   
         }
        catch(Exception e)
        {
            e.printStackTrace();
        }
            }
                    
          });
        
        
        
        JLabel lblstate = new JLabel("Unit Consumed");
        lblstate.setBounds(100,200,100,20);
        p.add(lblstate);
        
        tfunits = new JTextField();
        tfunits.setBounds(240,200,200,20);
        p.add(tfunits);
        
        JLabel lblcity = new JLabel("Month");
        lblcity.setBounds(100,240,100,20);
        p.add(lblcity);
        
        cmonth = new Choice();
        cmonth.setBounds(240,240,200,20);
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
        
        
        next = new JButton("Submit");
        next.setBounds(150,350,100,25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(290,350,100,25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        p.add(cancel);
                
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== next)
        {
            String meter = meternumber.getSelectedItem();
            String units = tfunits.getText();
            String month = cmonth.getSelectedItem();
        /* now we have to calculate total bill for that we are going to hit a 
        query into the database whivh contain per unit value and charges of different taxes. 
            using which we are going to calculate the total charge we could have done a raw 
            calculation as well but this is more professional approach since if we need to 
            update or replace some charges then we can do it directly from the database and don't need to change anything within code*/
            int totalbill=0;
            int unit_consumed=Integer.parseInt(units);//as it unit is a sring we can to do operation with it so we have to convert it to integer.
            String query = "select * from tax";
            
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                while(rs.next()){
                    totalbill+=unit_consumed * Integer.parseInt(rs.getString("cost_per_unit"));
                    totalbill+=Integer.parseInt(rs.getString("meter_rent"));
                    totalbill+=Integer.parseInt(rs.getString("service_charge"));
                    totalbill+=Integer.parseInt(rs.getString("service_tax"));
                    totalbill+=Integer.parseInt(rs.getString("swacch_bharat_cess"));
                    totalbill+=Integer.parseInt(rs.getString("fixed_tax"));
                //Here we add every tax and charges needes to add with the total cost
                } 
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            // we have to store all the data into another table 
            String query2="insert into bill values('"+meter+"','"+month+"','"+units+"','"+totalbill+"','Not paid')";
            try{
                Conn c= new Conn();
                c.s.executeUpdate(query2);
                JOptionPane.showMessageDialog(null,"Customer Bill Updated Successfully");
                setVisible(false);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else{
            setVisible(false); 
        }
        
    }
    

    public static void main(String args[]) {
       new CalculateBill();
    }
}
