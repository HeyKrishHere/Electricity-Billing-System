package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
public class NewCustomer extends JFrame implements ActionListener{
    JTextField tfname,tfaddress,tfstate,tfcity,tfmail,tfphone;
    JButton next,cancel;
    JLabel lblmeter ;
    
/*This part is for admin use only only admin can see this part and can add 
new customer, admin wil generate a unique meter nunber and give it to the customer using which custo,er can signup and generate password then can login*/
    NewCustomer(){
        setSize(600, 500);
        setLocation(400,200);
        
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        add(p);
        
        JLabel heading = new JLabel("New Customer");
        heading.setBounds(180,10,200,25);
        heading.setFont(new Font("Tahoma",Font.PLAIN,24));
        p.add(heading);
        
        JLabel lblname = new JLabel("Customer Name");
        lblname.setBounds(100,80,100,20);
        p.add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(240,80,200,20);
        p.add(tfname);
        
        JLabel lblmeterno = new JLabel("Meter Number");
        lblmeterno.setBounds(100,120,100,20);
        p.add(lblmeterno);
        
        lblmeter = new JLabel("");// as we want to set a random number in place of meter number we take a label and we will set the text over it.
        lblmeter.setBounds(240,120,100,20);
        p.add(lblmeter);
        
        Random ran = new Random();//useing random class we will generate a random numner
        long number = ran.nextLong() % 1000000;// we take only last 6 digit of that number
        lblmeter.setText("" + Math.abs(number));//using setText() we can set the random number over the panel. and setText() only work with string so we convert it into string by "" +, and  take absolute value of the randomly generated number as it can be -ve as well which we don't want.
        
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(100,160,100,20);
        p.add(lbladdress);
        
        tfaddress = new JTextField();
        tfaddress.setBounds(240,160,200,20);
        p.add(tfaddress);
        
        JLabel lblstate = new JLabel("State");
        lblstate.setBounds(100,200,100,20);
        p.add(lblstate);
        
        tfstate = new JTextField();
        tfstate.setBounds(240,200,200,20);
        p.add(tfstate);
        
        JLabel lblcity = new JLabel("City");
        lblcity.setBounds(100,240,100,20);
        p.add(lblcity);
        
        tfcity = new JTextField();
        tfcity.setBounds(240,240,200,20);
        p.add(tfcity);
        
        JLabel lblmail = new JLabel("Email");
        lblmail.setBounds(100,280,100,20);
        p.add(lblmail);
        
        tfmail = new JTextField();
        tfmail.setBounds(240,280,200,20);
        p.add(tfmail);
        
        JLabel lblphone = new JLabel("Phone Number");
        lblphone.setBounds(100,320,100,20);
        p.add(lblphone);
        
        tfphone = new JTextField();
        tfphone.setBounds(240,320,200,20);
        p.add(tfphone);
        
        next = new JButton("Next");
        next.setBounds(120,390,100,25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(250,390,100,25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        p.add(cancel);
                
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== next)
        {
            String name = tfname.getText();
            String meter = lblmeter.getText();
            String address = tfaddress.getText();
            String city = tfcity.getText();
            String state = tfstate.getText();
            String email = tfmail.getText();
            String phone = tfphone.getText();
            //insert the details into the customer table. 
            String query1 =" insert into customer values('"+name+"','"+meter+"','"+address+"','"+city+"','"+state+"','"+email+"','"+phone+"')";
            String query2 =" insert into signup values('"+meter+"','','"+name+"','','')";/* here we store the randomly generated meter no. and name to the signup 
            tabel which we will match when a customer wanted to signup, if his/her meter number matches then only he/she can signup.*/
            
            try{
                Conn c = new Conn();
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                
                JOptionPane.showMessageDialog(null,"Customer Details Added Successfully");
                setVisible(false);
                
                new MeterInfo(meter);
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
       new NewCustomer();
    }
}
