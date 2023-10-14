
package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project extends JFrame implements ActionListener {

    String meter,atype;
    Project(String atype,String meter){
        this.atype= atype;
        this.meter=meter;
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);//use to create a frame for full screen.
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/billing background.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 850,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);
        
        JMenuBar mb = new JMenuBar();//use to create a menubar
        setJMenuBar(mb);//this will set the menubar on the frame
        JMenu master = new JMenu("Master");//use to create menu
        
        JMenuItem newcustomer = new JMenuItem("New Customer");// to create menuitem
        newcustomer.setFont(new Font("monospaced",Font.PLAIN,12));//set font for menuitem
        newcustomer.setBackground(Color.WHITE);//set background color for meny item
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("icon/icon1.png"));// adding image to the menu item
        Image image1 = icon1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        newcustomer.setIcon(new ImageIcon(image1));// add image as icon on the menuitem
        // now we are going to set memonics(setting short-cuts in keybord to perform any perticular task)eg. ctrl+p for printing.
        // it is an keystroke event. here in ctrl+p ctrl= keystroke and p= memonics
        // for that we are going to use setMnemonic()
        newcustomer.setMnemonic('D');
        newcustomer.addActionListener(this);//This is to create an Action Event so that on cliking we can open a new window of new customer
        newcustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        //using this two lines we can set the memonics as CTRL+D and KeyEvent and ActionEvent this two emthods are present in event package so we have to import it.
        master.add(newcustomer);
        
        JMenuItem customerdetails = new JMenuItem("Customer Details");// to create menuitem
        customerdetails.setFont(new Font("monospaced",Font.PLAIN,12));//set font for menuitem
        customerdetails.setBackground(Color.WHITE);//set background color for meny item
        ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("icon/icon2.png"));// adding image to the menu item
        Image image2 = icon2.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        customerdetails.setIcon(new ImageIcon(image2));// add image as icon on the menuitem
        customerdetails.setMnemonic('C');
        customerdetails.addActionListener(this);
        customerdetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        master.add(customerdetails);
        
        JMenuItem depositdetails = new JMenuItem("Deposit Details");// to create menuitem
        depositdetails.setFont(new Font("monospaced",Font.PLAIN,12));//set font for menuitem
        depositdetails.setBackground(Color.WHITE);//set background color for meny item
        ImageIcon icon3 = new ImageIcon(ClassLoader.getSystemResource("icon/icon3.png"));// adding image to the menu item
        Image image3 = icon3.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        depositdetails.setIcon(new ImageIcon(image3));// add image as icon on the menuitem
        depositdetails.setMnemonic('J');
        depositdetails.addActionListener(this);
        depositdetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, ActionEvent.CTRL_MASK));
        master.add(depositdetails);
        
        JMenuItem calculatebill = new JMenuItem("Calculate Bill");// to create menuitem
        calculatebill.setFont(new Font("monospaced",Font.PLAIN,12));//set font for menuitem
        calculatebill.setBackground(Color.WHITE);//set background color for meny item
        ImageIcon icon4 = new ImageIcon(ClassLoader.getSystemResource("icon/icon5.png"));// adding image to the menu item
        Image image4 = icon4.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        calculatebill.setIcon(new ImageIcon(image4));// add image as icon on the menuitem
        calculatebill.setMnemonic('A');
        calculatebill.addActionListener(this);
        calculatebill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        master.add(calculatebill);
        
        JMenu info = new JMenu("Information");
        
        JMenuItem updateinformation = new JMenuItem("Update Information");// to create menuitem
        updateinformation.setFont(new Font("monospaced",Font.PLAIN,12));//set font for menuitem
        updateinformation.setBackground(Color.WHITE);//set background color for meny item
        ImageIcon icon5 = new ImageIcon(ClassLoader.getSystemResource("icon/icon4.png"));// adding image to the menu item
        Image image5 = icon5.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        updateinformation.setIcon(new ImageIcon(image5));// add image as icon on the menuitem
        updateinformation.setMnemonic('P');
        updateinformation.addActionListener(this);
        updateinformation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        info.add(updateinformation);
        
        
        JMenuItem viewinformation = new JMenuItem("View Information");// to create menuitem
        viewinformation.setFont(new Font("monospaced",Font.PLAIN,12));//set font for menuitem
        viewinformation.setBackground(Color.WHITE);//set background color for meny item
        ImageIcon icon6 = new ImageIcon(ClassLoader.getSystemResource("icon/icon6.png"));// adding image to the menu item
        Image image6 = icon6.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        viewinformation.setIcon(new ImageIcon(image6));// add image as icon on the menuitem
        viewinformation.setMnemonic('L');
        viewinformation.addActionListener(this);
        viewinformation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        info.add(viewinformation);
        
        JMenu user = new JMenu("User");
       
        JMenuItem paybill = new JMenuItem("Paybill");// to create menuitem
        paybill.setFont(new Font("monospaced",Font.PLAIN,12));//set font for menuitem
        paybill.setBackground(Color.WHITE);//set background color for meny item
        ImageIcon icon7 = new ImageIcon(ClassLoader.getSystemResource("icon/icon4.png"));// adding image to the menu item
        Image image7 = icon7.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        paybill.setIcon(new ImageIcon(image7));// add image as icon on the menuitem
        paybill.setMnemonic('G');
        paybill.addActionListener(this);
        paybill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        user.add(paybill);
        
        
        JMenuItem billdetails = new JMenuItem("Bill Details");// to create menuitem
        billdetails.setFont(new Font("monospaced",Font.PLAIN,12));//set font for menuitem
        billdetails.setBackground(Color.WHITE);//set background color for meny item
        ImageIcon icon8 = new ImageIcon(ClassLoader.getSystemResource("icon/icon6.png"));// adding image to the menu item
        Image image8 = icon8.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        billdetails.setIcon(new ImageIcon(image8));// add image as icon on the menuitem
        billdetails.setMnemonic('R');
        billdetails.addActionListener(this);
        billdetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        user.add(billdetails);
        
        JMenu report = new JMenu("Report");
        
        JMenuItem generatebill = new JMenuItem("Generate Bill");// to create menuitem
        generatebill.setFont(new Font("monospaced",Font.PLAIN,12));//set font for menuitem
        generatebill.setBackground(Color.WHITE);//set background color for meny item
        ImageIcon icon9 = new ImageIcon(ClassLoader.getSystemResource("icon/icon7.png"));// adding image to the menu item
        Image image9 = icon9.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        generatebill.setIcon(new ImageIcon(image9));// add image as icon on the menuitem
        generatebill.setMnemonic('Y');
        generatebill.addActionListener(this);
        generatebill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        report.add(generatebill);
        
        JMenu utility = new JMenu("Utility");
        
        JMenuItem calulator = new JMenuItem("Calculator");// to create menuitem
        calulator.setFont(new Font("monospaced",Font.PLAIN,12));//set font for menuitem
        calulator.setBackground(Color.WHITE);//set background color for meny item
        ImageIcon icon10 = new ImageIcon(ClassLoader.getSystemResource("icon/icon7.png"));// adding image to the menu item
        Image image10 = icon10.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        calulator.setIcon(new ImageIcon(image10));// add image as icon on the menuitem
        calulator.setMnemonic('Z');
        calulator.addActionListener(this);
        calulator.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        utility.add(calulator);
        
        JMenuItem notepad = new JMenuItem("Notepad");// to create menuitem
        notepad.setFont(new Font("monospaced",Font.PLAIN,12));//set font for menuitem
        notepad.setBackground(Color.WHITE);//set background color for meny item
        ImageIcon icon11 = new ImageIcon(ClassLoader.getSystemResource("icon/icon12.png"));// adding image to the menu item
        Image image11 = icon11.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        notepad.setIcon(new ImageIcon(image11));// add image as icon on the menuitem
        notepad.setMnemonic('N');
        notepad.addActionListener(this);
        notepad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        utility.add(notepad);
        
        JMenu mexit = new JMenu("Exit");
        JMenuItem exit = new JMenuItem("Exit");// to create menuitem
        exit.setFont(new Font("monospaced",Font.PLAIN,12));//set font for menuitem
        exit.setBackground(Color.WHITE);//set background color for meny item
        ImageIcon icon12 = new ImageIcon(ClassLoader.getSystemResource("icon/icon11.png"));// adding image to the menu item
        Image image12 = icon12.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        exit.setIcon(new ImageIcon(image12));// add image as icon on the menuitem
        exit.setMnemonic('H');
        exit.addActionListener(this);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        mexit.add(exit );
        
        /* now we want to show selected features to selected customer, eg. when user logged in as a Admin then only the master option should be show else 
    every customer is able to see other customer's data*/
        
        
        if(atype.equals("Admin"))
        {
             mb.add(master);//adding menu to the menubar
        }
        else{
            mb.add(user);
            mb.add(info);
            mb.add(report);
        }
       
         mb.add(utility);
         mb.add(mexit);
        
        setLayout(new FlowLayout());// there are diff types of layouts eg. grid layout, flow layout etc. here we use flow layout
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        String msg = ae.getActionCommand();/*here we want to tarck which item has clicked, we can donit in two ways 1)getSource() that we have done previoiusly*or 
        2)/getActionCommand() it will return the string which item has clicked*/
        if(msg.equals("New Customer")){
            new NewCustomer();
        }
        else if (msg.equals("Customer Details"))//always make sure here the spelling matches with the spelling in Jlabel
        {
            new CustomerDetails();
        }
        else if(msg.equals("Deposit Details"))
        {
            new DepositDetails();
        }
        else if(msg.equals("Calculate Bill"))
        {
            new CalculateBill();
        }
        else if(msg.equals("View Information"))
        {
            new ViewCustomer(meter);
        }
        else if(msg.equals("Update Information"))
        {
            new UpdateInformation(meter);
        }
        else if(msg.equals("Bill Details"))
        {
            new BillDetails(meter);
        }
        else if(msg.equals("Notepad"))
        {
            try
            {
                Runtime.getRuntime().exec("notepad.exe");//to run any appliation that is installed in the system using java we use this command.
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(msg.equals("Calculator"))
        {
            try
            {
                Runtime.getRuntime().exec("calc.exe");//here the name of executable file of calculator is calc.exe so we write that only
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(msg.equals("Exit"))
        {
            setVisible(false);
            new Login();
        }
        
        else if(msg.equals("Paybill"))
        {
            new PayBill(meter);
        }
        
        else if(msg.equals("Generate Bill"))
        {
            new GenerateBill(meter);
        }
    }
    
    
    
    
    
   
    public static void main(String args[]) {
        new Project("","");
    }
}
