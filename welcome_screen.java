import javax.swing.*; 

import java.awt.*;

public class welcome_screen {
	public static void main(String args[]){
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		JLabel welcomeLabel = new JLabel("Welcome Screen");
		panel.add(welcomeLabel);
		welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton add = new JButton("Add");
		JButton delete = new JButton("Delete");
		JButton update = new JButton("Update");
		JButton search = new JButton("Search");
		
		panel.add(add);
		panel.add(delete);
		panel.add(update);
		panel.add(search);
		
		//centers buttons
		add.setAlignmentX(Component.CENTER_ALIGNMENT);
		delete.setAlignmentX(Component.CENTER_ALIGNMENT);
		update.setAlignmentX(Component.CENTER_ALIGNMENT);
		search.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}
