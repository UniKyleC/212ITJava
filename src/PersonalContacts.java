import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PersonalContacts extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txbxLastName;
	private JTextField txbxFirstName;
	private JTextField txbxEmail;
	private JTextField txbxTel;
	private JTextField txbxHomeTel;
	private JTextField txbxAddr1;
	private JTextField txbxAddr2;
	private JTextField txbxCity;
	private JTextField txbxPostcode;
	private JLabel lblLastName;
	private JLabel lblEmail;
	private JLabel lblMobile;
	private JLabel lblHome;
	private JLabel lblAddressLine1;
	private JLabel lblAddressLine2;
	private JLabel lblCity;
	private JLabel lblPostcode;
	private JButton btnUpdateSelected;
	private JButton btnSaveSelected;
	private JButton btnDeleteSelected;
	private JButton btnAddNew;
	private JButton btnSaveNew;
	private int savedid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonalContacts frame = new PersonalContacts();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PersonalContacts() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//create the scrollpane for the table to go into
		JScrollPane scrollPane = new JScrollPane();
		//create the database connection
		dbConn d=new dbConn();
		//create the refresh button with text
		JButton btnRefresh = new JButton("Refresh Grid");
		//add an event on mouse-cilck
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//set the model for the table based on the result set from calling the method 'GetAllPersonal'
				table.setModel(DbUtils.resultSetToTableModel(d.GetAllPersonal()));
				table.setDefaultEditor(Object.class, null);
				//set the correct buttons to be enabled
				btnAddNew.setEnabled(true);
				btnUpdateSelected.setEnabled(true);
				btnDeleteSelected.setEnabled(true);
			}
		});
		//creation of various text boxes which are disabled by default, and labels for them
		txbxLastName = new JTextField();
		txbxLastName.setEnabled(false);
		txbxLastName.setColumns(10);
		
		txbxFirstName = new JTextField();
		txbxFirstName.setEnabled(false);
		txbxFirstName.setColumns(10);
		
		txbxEmail = new JTextField();
		txbxEmail.setEnabled(false);
		txbxEmail.setColumns(10);
		
		txbxTel = new JTextField();
		txbxTel.setEnabled(false);
		txbxTel.setColumns(10);
		
		txbxHomeTel = new JTextField();
		txbxHomeTel.setEnabled(false);
		txbxHomeTel.setColumns(10);
		
		txbxAddr1 = new JTextField();
		txbxAddr1.setEnabled(false);
		txbxAddr1.setColumns(10);
		
		txbxAddr2 = new JTextField();
		txbxAddr2.setEnabled(false);
		txbxAddr2.setColumns(10);
		
		txbxCity = new JTextField();
		txbxCity.setEnabled(false);
		txbxCity.setColumns(10);
		
		txbxPostcode = new JTextField();
		txbxPostcode.setEnabled(false);
		txbxPostcode.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name");
		
		lblLastName = new JLabel("Last Name");
		
		lblEmail = new JLabel("Email");
		
		lblMobile = new JLabel("Mobile #");
		
		lblHome = new JLabel("Home #");
		
		lblAddressLine1 = new JLabel("Address Line 1");
		
		lblAddressLine2 = new JLabel("Address Line 2");
		
		lblCity = new JLabel("City");
		
		lblPostcode = new JLabel("Postcode");
		//Sets up the button UpdateSelected, making it disabled by default until 'refresh grid' is clicked
		btnUpdateSelected = new JButton("Update Selected");
		btnUpdateSelected.setEnabled(false);
		//when the button is clicked, enable the text boxes and disable/enable relevant buttons
		btnUpdateSelected.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				txbxFirstName.setEnabled(true);
				txbxLastName.setEnabled(true);
				txbxEmail.setEnabled(true);
				txbxTel.setEnabled(true);
				txbxHomeTel.setEnabled(true);
				txbxAddr1.setEnabled(true);
				txbxAddr2.setEnabled(true);
				txbxCity.setEnabled(true);
				txbxPostcode.setEnabled(true);
				btnSaveSelected.setEnabled(true);
				btnAddNew.setEnabled(false);
				btnDeleteSelected.setEnabled(false);
				btnSaveNew.setEnabled(false);
				btnUpdateSelected.setEnabled(false);
				btnRefresh.setEnabled(false);
				
				
			}
		});
		//new button save selected
		btnSaveSelected = new JButton("Save Selected");
		//on mouse click, get the text from the text boxes, pass them to strings which become parameters in the called updatePersonal Method
		btnSaveSelected.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String conFName = txbxFirstName.getText();
				String conLName = txbxLastName.getText();
				String conTel = txbxTel.getText();
				String conHTel = txbxHomeTel.getText();
				String conEmail = txbxEmail.getText();
				String conAddr1 = txbxAddr1.getText();
				String conAddr2 = txbxAddr2.getText();
				String conCity = txbxCity.getText();
				String conPostcode = txbxPostcode.getText();
				int conID = savedid;
		//pass the parameters we just created to the method, then disable/enable relevant buttons + txt boxes
				d.updatePersonal(conFName, conLName, conTel, conHTel, conEmail, conAddr1, conAddr2, conCity, conPostcode, conID);
				btnSaveSelected.setEnabled(false);
				btnDeleteSelected.setEnabled(true);
				btnAddNew.setEnabled(true);
				btnUpdateSelected.setEnabled(true);
				btnRefresh.setEnabled(true);
				txbxFirstName.setEnabled(false);
				txbxLastName.setEnabled(false);
				txbxEmail.setEnabled(false);
				txbxTel.setEnabled(false);
				txbxHomeTel.setEnabled(false);
				txbxAddr1.setEnabled(false);
				txbxAddr2.setEnabled(false);
				txbxCity.setEnabled(false);
				txbxPostcode.setEnabled(false);
				
				
			}
		});
		//sets the btn disabled as default
		btnSaveSelected.setEnabled(false);
		//creates the delete button
		btnDeleteSelected = new JButton("Delete Selected");
		//upon clicking the button, call the delete method
		btnDeleteSelected.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int conID = savedid;
				 d.deletePersonal(conID);
				 	
			}
		});
		//disable this button by default
		btnDeleteSelected.setEnabled(false);
		
		//new 'add new' button
		btnAddNew = new JButton("Add New");
		//upon clicking the button set all the text fields to empty, then enable them. Disable/Enable relevant buttons.
		btnAddNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				txbxFirstName.setText("");
				txbxLastName.setText("");
				txbxEmail.setText("");
				txbxTel.setText("");
				txbxHomeTel.setText("");
				txbxAddr1.setText("");
				txbxAddr2.setText("");
				txbxCity.setText("");
				txbxPostcode.setText("");
				
				txbxFirstName.setEnabled(true);
				txbxLastName.setEnabled(true);
				txbxEmail.setEnabled(true);
				txbxTel.setEnabled(true);
				txbxHomeTel.setEnabled(true);
				txbxAddr1.setEnabled(true);
				txbxAddr2.setEnabled(true);
				txbxCity.setEnabled(true);
				txbxPostcode.setEnabled(true);

				btnAddNew.setEnabled(false);
				btnRefresh.setEnabled(false);
				btnUpdateSelected.setEnabled(false);
				btnDeleteSelected.setEnabled(false);
				btnSaveSelected.setEnabled(false);
				btnSaveNew.setEnabled(true);
			}
		});
		//disabled button by default
		btnAddNew.setEnabled(false);
		//new save new button
		btnSaveNew = new JButton("Save New");
		//code triggers on a mouse click
		btnSaveNew.addMouseListener(new MouseAdapter() {
			@Override
			//sets the string data to what is in the text boxes, ready to be passed as parameters
			public void mouseClicked(MouseEvent e) {
				String conFName = txbxFirstName.getText();
				String conLName = txbxLastName.getText();
				String conTel = txbxTel.getText();
				String conHTel = txbxHomeTel.getText();
				String conEmail = txbxEmail.getText();
				String conAddr1 = txbxAddr1.getText();
				String conAddr2 = txbxAddr2.getText();
				String conCity = txbxCity.getText();
				String conPostcode = txbxPostcode.getText();
				//calls the insertPersonalmethod, passing it the parameters we just specified
				d.insertPersonal(conFName, conLName, conTel, conHTel, conEmail, conAddr1, conAddr2, conCity, conPostcode);
				
//enables/disables txbxs and buttons
				txbxFirstName.setEnabled(false);
				txbxLastName.setEnabled(false);
				txbxEmail.setEnabled(false);
				txbxTel.setEnabled(false);
				txbxHomeTel.setEnabled(false);
				txbxAddr1.setEnabled(false);
				txbxAddr2.setEnabled(false);
				txbxCity.setEnabled(false);
				txbxPostcode.setEnabled(false);

				btnAddNew.setEnabled(true);
				btnRefresh.setEnabled(true);
				btnUpdateSelected.setEnabled(true);
				btnDeleteSelected.setEnabled(true);
				btnSaveNew.setEnabled(false);
			}
		});
		//sets the button to be disabled unless enabled otherwise
		btnSaveNew.setEnabled(false);
		//new table within the scrollpane
		table = new JTable();
		//allow only single-row selections
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//on mouse click trigger the code to put the values of the table into the text boxes!
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				savedid = (int) table.getValueAt(table.getSelectedRow(), 0);
				txbxFirstName.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				txbxLastName.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				txbxTel.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
				txbxEmail.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
				txbxAddr1.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
				txbxAddr2.setText(table.getValueAt(table.getSelectedRow(), 6).toString());
				txbxCity.setText(table.getValueAt(table.getSelectedRow(), 7).toString());
				txbxPostcode.setText(table.getValueAt(table.getSelectedRow(), 8).toString());
				txbxHomeTel.setText(table.getValueAt(table.getSelectedRow(), 9).toString());
			
				
				
				
			}
		});
		scrollPane.setViewportView(table);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(8)
					.addComponent(lblFirstName, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(txbxFirstName, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblAddressLine1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(50)
					.addComponent(txbxAddr1, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(8)
					.addComponent(lblLastName, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(txbxLastName, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblAddressLine2, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(50)
					.addComponent(txbxAddr2, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(8)
					.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(txbxEmail, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblCity, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(50)
					.addComponent(txbxCity, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(8)
					.addComponent(lblMobile, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(txbxTel, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblPostcode, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(50)
					.addComponent(txbxPostcode, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(8)
					.addComponent(lblHome, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(txbxHomeTel, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnUpdateSelected, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addComponent(btnSaveSelected, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnDeleteSelected, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addGap(0, 0, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(295)
					.addComponent(btnAddNew, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addComponent(btnSaveNew, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addGap(0, 0, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(8)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(4)
							.addComponent(lblFirstName, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(txbxFirstName, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(4)
							.addComponent(lblAddressLine1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(txbxAddr1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(4)
							.addComponent(lblLastName, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(txbxLastName, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(4)
							.addComponent(lblAddressLine2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(txbxAddr2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(7)
							.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(txbxEmail, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(4)
							.addComponent(lblCity, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(txbxCity, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(2)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(7)
							.addComponent(lblMobile, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(txbxTel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(4)
							.addComponent(lblPostcode, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(txbxPostcode, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(lblHome, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(txbxHomeTel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnUpdateSelected, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSaveSelected, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDeleteSelected, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnAddNew, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSaveNew, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
					.addGap(21))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
