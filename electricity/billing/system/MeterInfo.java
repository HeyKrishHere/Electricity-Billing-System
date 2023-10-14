package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MeterInfo extends JFrame implements ActionListener{
    JButton next;
    Choice meterlocation, metertype,phasecode,billtype;
    String meternumber;
    
/*This part is for admin use only only admin can see this part and can add 
new customer, admin wil generate a unique meter nunber and give it to the customer using which custo,er can signup and generate password then can login*/
    MeterInfo(String meternumber){
        this.meternumber=meternumber;
        setSize(600, 500);
        setLocation(400,200);
        
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        add(p);
        
        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(180,10,200,25);
        heading.setFont(new Font("Tahoma",Font.PLAIN,24));
        p.add(heading);
        
        JLabel lblname = new JLabel("Meter Number");
        lblname.setBounds(100,80,100,20);
        p.add(lblname);
        
        JLabel lblmeternumber = new JLabel(meternumber);
        lblmeternumber.setBounds(240,80,100,20);
        p.add(lblmeternumber);
        
        JLabel lblmeterno = new JLabel("Meter Location");
        lblmeterno.setBounds(100,120,100,20);
        p.add(lblmeterno);
        
        meterlocation = new Choice();
        meterlocation.add("Outside");
        meterlocation.add("Inside");
        meterlocation.setBounds(240,120,200,20);
        p.add(meterlocation);
        
        
        
        JLabel lbladdress = new JLabel("Meter type");
        lbladdress.setBounds(100,160,100,20);
        p.add(lbladdress);
        
        metertype = new Choice();
        metertype.add("Electric");
        metertype.add("Solar");
        metertype.add("Smart");
        metertype.setBounds(240,160,200,20);
        p.add(metertype);
        
        JLabel lblstate = new JLabel("Phase Code");
        lblstate.setBounds(100,200,100,20);
        p.add(lblstate);
        
        phasecode = new Choice();
        phasecode.add("011");
        phasecode.add("022");
        phasecode.add("033");
        phasecode.add("044");
        phasecode.add("055");
        phasecode.add("066");
        phasecode.add("077");
        phasecode.setBounds(240,200,200,20);
        p.add(phasecode);
        
        JLabel lblcity = new JLabel("Bil type");
        lblcity.setBounds(100,240,100,20);
        p.add(lblcity);
        
        billtype = new Choice();
        billtype.add("Normal");
        billtype.add("Industrial");
        billtype.setBounds(240,240,200,20);
        p.add(billtype);
        
        JLabel lblphone = new JLabel("Note");
        lblphone.setBounds(100,280,100,20);
        p.add(lblphone);
        
        JLabel lblphones = new JLabel("Bill is Calculated for 30 days only.");
        lblphones.setBounds(240,280,500,20);
        p.add(lblphones);
        
        next = new JButton("Submit");
        next.setBounds(240,390,100,25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);
                
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== next)
        {
            String meter = meternumber;
            String location = meterlocation.getSelectedItem();
            String type = metertype.getSelectedItem();
            String code = phasecode.getSelectedItem();
            String typebill = billtype.getSelectedItem();
            String days ="30";
           
            //insert the details into the customer table. 
            String query =" insert into meter_info values('"+meter+"','"+location+"','"+type+"','"+code+"','"+typebill+"','"+days+"')";
            
            try{
                Conn c = new Conn();
                c.s.executeUpdate(query);
               
                
                JOptionPane.showMessageDialog(null,"Meter Information Added Successfully");
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
       new MeterInfo("");
    }
}
