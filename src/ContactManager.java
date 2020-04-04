import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ContactManager {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContactManager window = new ContactManager();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ContactManager() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 687, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnBusinessContacts = new JButton("Business Contacts");
		btnBusinessContacts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BusinessContacts bc=new BusinessContacts();
				frame.dispose();
				bc.setVisible(true);
			}
		});
		btnBusinessContacts.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBusinessContacts.setBounds(81, 107, 204, 142);
		frame.getContentPane().add(btnBusinessContacts);
		
		JButton btnPersonalContacts = new JButton("Personal Contacts");
		btnPersonalContacts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PersonalContacts pc=new PersonalContacts();
				frame.dispose();
				pc.setVisible(true);
			}
		});
		btnPersonalContacts.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPersonalContacts.setBounds(346, 107, 204, 142);
		frame.getContentPane().add(btnPersonalContacts);
	}
}
