
package electricity.billing.system;
import javax.swing.*;

public class Splash extends JFrame implements Runnable /*JFrame is class of swing package use to create frames in GUI, after that we implemets
        the Runnabe interface for ussing multithreading concepts(as we extends the JFrame class erlier we can not extend another class as java 
        doesn't support multiple inheritence so we can't extend the Thread class for multithreading purpose)*/
{
    Thread t;//Whenever we use Runnable interface we have to create a Thread class object explicitly
    Splash()//constructor
    {
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/switch.png"));//use to get the image from the file.
        JLabel img = new JLabel(i1);
        add(img);/* we can't put imageIcon object to add() method that's why we first give to JLevel method as input and then give 
        JLebel methid object as input to the add() method*/
        setVisible(true);//by default the visibility of any frame is hidden to show the frame we have to set it true
        
        for(int i=1;i<720;i=i+4)/* we use for loop because using it we can increase the size of the frame slowly instead of opening all
        at once, which looke cool.8*/
        {
            setSize(i,i);//use to specify the size of the frame
            setLocation(1120-i,50);// for seeting the location of the frame in the screen.
        }
        
        t=new Thread(this);
        t.start();// use to call the run method
    }
   
    public void run()/* in runnable interface there present an abstruct run() method which we cant use in our program as our class is not abstruct 
            if we make our class as abstruct then we can't create the object of that class, so for using the the run() method in our program we have
            to override the run method in  our program this is waht we have done here. */
    {
        
        try{
        Thread.sleep(7000);// frame will stay for 7 second
        
        setVisible(false); // after 7 sec it will disappeared
        new Login();
        }
        catch(Exception e)// when ever we use sleep() we have to handel the exception using try catch block
        {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args){
        
       new Splash();// instantiation
    }
}