package electricity.billing.system;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class Login extends JFrame implements ActionListener {
     JButton login,cancel,signup;
     JTextField username1, password1;
     Choice logginin;
     /* we declare them globally because we need their reference in the actionperformed method,
    if we do not decalre them globally then we will not get the reference as their scope was within the login() constructor.*/  
    Login(){
        super("Login Page");//use to give heading the the window, and if we ue super then it must be the first line of the constructor.
        getContentPane().setBackground(Color.LIGHT_GRAY);/*getContentPane() is use to pull the whole frame and do some modification,
        //then setBackground(Color: red);use to change the color of the background*/
       
        
        setLayout(null);//as we don't want to use the default layout provided by swing we have to make it null, so that we can make out=r own layout.
        
        JLabel head= new JLabel("LOGIN or SIGN UP");
        head.setBounds(230,50,720,50);
        head.setFont(new Font("Arial",Font.BOLD,30));
        add(head);

        JLabel username =new JLabel("Username"); /*we have seen this method erlier in the splash class making but that's not the 
        actual funtionality of the JLabel class. It is mainly used for write something on the screen, what ever we pass through 
        the JLabel constructor it will be printed on the frame*/
        username.setBounds(360,160,100,20);/*setBounds only work if you setLayout(null);here we have created a layout 
        /*where 300,20 is is X,y coordinates with respect to the frame and 100,20 is the length and width of the element.*/ 
        add(username);//use to on the frame
        
        username1 = new JTextField();// it is use to create a input field
        username1.setBounds(460,160,150,20);
        add(username1);
        JLabel password =new JLabel("Password");
        password.setBounds(360,200,100,20);
        add(password);
        
        password1 = new JTextField();
        password1.setBounds(460,200,150,20);
        add(password1);
        
        JLabel loggininas =new JLabel("Login as");
        loggininas.setBounds(360,240,100,20);
        add(loggininas);
        
        logginin=new Choice();//Use to create a drop down
        logginin.add("Admin");
        logginin.add("Customer");
        logginin.setBounds(460,240,100,20);
        add(logginin);
        
        
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image i2 = i1.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);/*here 
        we scaled the image with our desired size 16,16 as the soze of the button is 20 so we take 
        the size of the image is 16.*/
        login = new JButton("Login",new ImageIcon(i2));/*for creating a button,
        what ever we passed in object will be shown in the button, here we pass a text and image class object.*/
        login.setBounds(380,300,100,20);
        login.addActionListener(this);//this method use to register the button in ActionListener class        add(login);
        add(login);
        ImageIcon i3=new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image i4 = i3.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);
        cancel = new JButton("Cancel",new ImageIcon(i4));
        cancel.setBounds(500,300,100,20);
        cancel.addActionListener(this);
        add(cancel);
        
        
        ImageIcon i5=new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image i6 = i5.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT);
        signup = new JButton("Sign Up",new ImageIcon(i6));
        signup.setBounds(440,350,100,20);
        signup.addActionListener(this);
        add(signup);
        
        
        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icon/hand.png"));
        Image i8 = i7.getImage().getScaledInstance(400,400,Image.SCALE_DEFAULT);
        ImageIcon i9=new ImageIcon(i8);
        JLabel img=new JLabel(i9);
        img.setBounds(0,100,400,400);
        add(img);
        
       
        
        setSize(720,720);
        setLocation(500,50);
        setVisible(true);
        
           
    }
    
    public void actionPerformed(ActionEvent ae)//here it will catches the event and perform the corresponding task
        {
        
            if(ae.getSource()==login)//use to get the sorce of the event
            {
                //to get the input data
                String susername = username1.getText();
                String spassword = password1.getText();
                String user = logginin.getSelectedItem();
                
                // now we have to match them with the data stored in the database
                //if it matches then we will go to further else we will ask for another input
                //whenever we deal with database we should write the codes inside a try catch block to avoid sql exception
                try{
                    Conn c = new Conn();//first we need to establish a connection with the database
                    //after that we have to run a query
                    String query ="select * from signup where username='"+susername+"' and password = '"+spassword+"' and user ='"+user+"'";
                    ResultSet rs=c.s.executeQuery(query);
                    //use to execute DDL query(defination), now this method will return an object of ResultSet class so we have to store it in a variable
                    //ResultSet class is present inside sql package so we have to import it.
                    
                    if(rs.next())//it checks weather data is null, if null then if block will not execute and control will go to else, data will be null when the input data will not match to any data in the datbase
                    {
                        String meter = rs.getString("meter_no");//as we want to forward the meter number into the next classes
                        setVisible(false);
                        new Project(user,meter);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Invalid Loglin");
                        username1.setText("");//to remove all the previous entered texts
                        password1.setText("");
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                
            }
            else if(ae.getSource()==cancel)
            {
                setVisible(false);
            }
            else if(ae.getSource()==signup)
            {
                 setVisible(false);
                 new SignUp();
            }
               
            
        }
    
    public static void main(String[] args){
        new Login();
    }
}
