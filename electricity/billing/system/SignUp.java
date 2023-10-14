package electricity.billing.system;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

public class SignUp extends JFrame implements ActionListener {
    
JButton create, back;
Choice acctype;
JTextField meter,username,name,password;
    
    SignUp(){
        
  /*Till now we have made frames using setSize() and setLocation() and then we  use setVisible() 
        as true now we will use setBounds() by which we can give both location and size in a single function */
  //if a component is calling set bounds then setbounds only set that component only and if you call setbounds without any component then setbounds will set the frames.
  
  setBounds(450,150,700,500);
  getContentPane().setBackground(Color.lightGray);
  setLayout(null);
  
  //if we have to divide frames into multiple parts then we use panel eg.jpanel
  JPanel panel= new JPanel();//in complex designs panel were use insted of frames.
  panel.setBounds(30,30,630,400);
  panel.setBackground(Color.WHITE);
  //if we want to set a border on the panel we have to use setBorder();
  panel.setBorder(new TitledBorder(new LineBorder(Color.BLACK),"Create Account",TitledBorder.LEADING,TitledBorder.TOP));
  panel.setLayout(null);
  add(panel);
  
  JLabel heading = new JLabel("Create Account as:");
  heading.setBounds(100,50,140,20);
  heading.setFont(new Font("Tahoma",Font.BOLD,14));
  panel.add(heading);
  
  
  
  /*what we wanted to do is when user select admin then meter number will be hidden as admin doesn't have meter number
  if user select customer then meter number will be shown ans as soon as user filed the meter number his/her name should come in the place 
  of name which will be not editable.*/
  
  //Chocie is a class use to make drop down
  acctype= new Choice();
  acctype.add("Admin");//use to give the options while click on the drop down
  acctype.add("Customer");
  acctype.setBounds(260,50,150,20);
  panel.add(acctype);//here we have to add it over panel otherwise it will added on the frame and we will not able to see it.
  
  
  JLabel lblmeter = new JLabel("Meter Number:");
  lblmeter.setBounds(100,100,140,20);
  lblmeter.setFont(new Font("Tahoma",Font.BOLD,14));
  lblmeter.setVisible(false);/*as we don't want to show this field when 
  user selects admin (as admin is selected by default so we set it to false at first after that we will chnage it under the specific cindition*/
  panel.add(lblmeter);
  
  meter = new JTextField();
  meter.setBounds(260,100,150,20);
  meter.setVisible(false);//same reason as above
  panel.add(meter);
  
  
  JLabel lblusername = new JLabel("Username:");
  lblusername.setBounds(100,150,140,20);
  lblusername.setFont(new Font("Tahoma",Font.BOLD,14));
  panel.add(lblusername);
  
  username = new JTextField();
  username.setBounds(260,150,150,20);
  panel.add(username);
  
  JLabel lblname = new JLabel("Name");
  lblname.setBounds(100,200,140,20);
  lblname.setFont(new Font("Tahoma",Font.BOLD,14));
  panel.add(lblname);
  
  name = new JTextField();
  name.setBounds(260,200,150,20);
  panel.add(name);
  
  
  /*This is one of the creative part of the entire project
    here whenever the cursor/focus is in the meter fild it does nothing but when 
  evern focus is lost then name filed is filled with the corresponding name. for that we use addFocusListener
  */
  meter.addFocusListener(new FocusListener(){
      public void focusGained(FocusEvent fe){}
      
      public void focusLost(FocusEvent fe) {
          
          try{
            
              Conn c = new Conn();
              ResultSet rs = c.s.executeQuery("select*from signup where meter_no='"+meter.getText()+"'");
              while(rs.next())
              {
                  name.setText(rs.getString("name"));
              }
          }
          catch(Exception e)
          {
              e.printStackTrace();
          }
      }
  });
  
  JLabel lblpassword = new JLabel("Password");
  lblpassword.setBounds(100,250,140,20);
  lblpassword.setFont(new Font("Tahoma",Font.BOLD,14));
  panel.add(lblpassword);
  
  password = new JTextField();
  password.setBounds(260,250,150,20);
  panel.add(password);
  
  acctype.addItemListener( new ItemListener(){
      public void itemStateChanged(ItemEvent ie)
      {
          String user=acctype.getSelectedItem();
          if(user.equals("Customer"))
          {
              lblmeter.setVisible(true);
              meter.setVisible(true);
              name.setEditable(false);
          }
          else{
              lblmeter.setVisible(false);
              meter.setVisible(false);
              name.setEditable(true);
          }
      }
      
  });
  
  create = new JButton("Create");
  create.setBounds(100,320,120,25);
  create.setBackground(Color.BLACK);
  create.setForeground(Color.white);//use to give text color
  create.addActionListener(this);
  panel.add(create);
  
  back = new JButton("Back");
  back.setBounds(280,320,120,25);
  back.setBackground(Color.BLACK);
  back.setForeground(Color.white);//use to give text color
  back.addActionListener(this);
  panel.add(back);
  setVisible(true);
  
 /*now we have to make the button work, for that here we are going to use the concept of event handelling 
  there are are lots of event present in the java.awt.event package, eg.: action event, mouse event, mouse wheel event, etc
  here we are going to use action event, for that we have to implement actionListener interface and inside actionlistener interface 
  there present an abstruct class actionperformed() which we need to override this will perform the desired action for us, we 
  pass an object of actionevent class in the actionperformed (). */ 
  }
    
    public void actionPerformed(ActionEvent ae)//here it will catches the event and perform the corresponding task
        {
        
            if(ae.getSource()==create)//use to get the sorce of the event
            {
                String atype= acctype.getSelectedItem();//use to catch the given input in the choice field
                String susername=username.getText();
                String sname=name.getText();
                String spassword=password.getText();
                String smeter=meter.getText();
                // now we have to store whatever we get in the mysql, as sql is an external entity we might get some exception so we have write it inside try-catch block
                try{
                    //create a table inside sql, where you want to store the data
                    Conn c =new Conn();//creating object of the conn class as it was not intantiated
                    String query=null;
                    if(atype.equals("Admin")){
                        query="insert into signup values('"+smeter+"','"+susername+"','"+sname+"','"+spassword+"','"+atype+"')";// creting the query as a string to insert values
                    //as we already have the details of the customer then we din't need to reenter the same values
                    //for customer we only insert username and password
                    }
                    else{
                        query="update signup set username='"+susername+"',password='"+spassword+"', user='"+atype+"'where meter_no='"+smeter+"'";
                    }
                    
                    c.s.executeUpdate(query);//executeUpdate is a method present in Connection class use to execute the DML query(manipulation)
                    JOptionPane.showMessageDialog(null, "Account Created Successfully");//it will show the popup mesaage if we give correct query
                    setVisible(false);
                    new Login();
                }
              catch(Exception e){
                    e.printStackTrace();
                    }
            }
            else if(ae.getSource()==back)
            {
                setVisible(false);
                new Login();
                
            }
    }
    
    public static void main(String[] args)
    {
        new SignUp();
    }
    
}