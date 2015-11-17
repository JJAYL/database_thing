import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class add_Input_Screen {

	public static void main(String[] args) {
		JTextField field1 = new JTextField();
		JTextField field2 = new JTextField();
		JTextField field3 = new JTextField();
		JTextField field4 = new JTextField();
		JTextField field5 = new JTextField();
		
		
		Object[] message = {
		    "Input value 1:", field1, 
		    "Input value 2:", field2,
		    "Input value 3:", field3,
		    "Input value 4:", field4,
		    "Input value 5:", field5,
		};
		int option = JOptionPane.showConfirmDialog(null, message, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION)
		{
		    String value1 = field1.getText();
		    String value2 = field2.getText();
		    String value3 = field3.getText();
		    String value4 = field4.getText();
		    String value5 = field5.getText();
		    System.out.println(value1 + " " + value2  + value3 + value4 + value5 ); 
		}
	
	} 

}
