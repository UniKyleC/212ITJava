import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

//setting up the database connection class
public class dbConn {

	String connString = "jdbc:mysql://db212it.cgvico87j64y.us-east-1.rds.amazonaws.com/harriscontactdb";
	String username = "admin";
	String password = "212itc0ventry";
	Connection con = null;

//setting up the dbconn method which connects to the database
	public dbConn()
	{
		//trying to connect, with a catch that if we don't connect, it prints out to the system with the failure
	try {
		con = DriverManager.getConnection(connString,username,password);
		System.out.println("Connected!");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("Failed to Connect!");
	}
	}
	//setting up the deletePersonal method with the passed parameter of conID
	public void deletePersonal(int conID) {
		//calling the stored procedure in the database, with (1) parameter
		String sql="CALL deletePersonal(?)";
		//trying to execute the stored procedure, with conID as the parameter and a message for failure/success
		try {
			CallableStatement cstdp = con.prepareCall(sql);
			cstdp.setInt(1, conID);
			cstdp.executeQuery();
		System.out.println("You have deleted Personal Data succesfully.");}
		catch (SQLException ed)
		{
		ed.printStackTrace();
		System.out.println("Failed to Delete Personal Data successfully.");
		}
		
		}
	//setting up the deleteBusiness method with the passed parameter of conID
public void deleteBusiness(int conID) {
		//calling the stored Procedure, with (1) parameter
		String sql="CALL deleteBusiness(?)";
		//trying to execute deleteBusiness with the conID parameter and message for failure/success
		try {
			CallableStatement cstdp = con.prepareCall(sql);
			cstdp.setInt(1, conID);
			cstdp.executeQuery();
		System.out.println("You have deleted Business Data succesfully.");}
		catch (SQLException ed)
		{
		ed.printStackTrace();
		System.out.println("Failed to Delete Business Data successfully.");
		}
		
		}
	//setting up the GetAllPersonal method which returns a result set for the table in the scrollpane.
	public ResultSet GetAllPersonal() {
		//setting the empty result set
		ResultSet rs=null;
		//calling the stored procedure set on the database side
		String sql="CALL selectAllPersonal";
		//try--catch block to execute and return the data with printed messages
		try {
			CallableStatement cst= con.prepareCall(sql);
			rs=cst.executeQuery();
			System.out.println("Got the Personal Data!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Failed to get Personal Data!");
		}
		//returning the new dataset
		return rs;}
	
	//settig up the GetAllBusiness method, which returns data for the table in the scrollpane.
	public ResultSet GetAllBusiness() {
		//setting up the resultset variable so data can be passed to it
		ResultSet rs=null;
		//calling the stored procedure on the database
		String sql="CALL selectAllBusiness";
		//try--catch block to execute the procedure and return the data - and if not, print error message appropriately
		try {
			CallableStatement cst= con.prepareCall(sql);
			rs=cst.executeQuery();
			System.out.println("Got the Business Data!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Failed to get Business Data!");
		}
		//returning the new dataset
		return rs;}
	
	//setting up the updatepesonal method, pssing it the listed parameters
	public void updatePersonal(String conFName, String conLName, String conTel, String conHTel, String conEmail, String conAddr1, String conAddr2, String conCity, String conPostcode, int conID) 
	{
		//calling the stored procedure with parameter number equal
		String sql= "CALL updatePersonal(?,?,?,?,?,?,?,?,?,?)";
		//try--catch block to do the procedure, with appropriate success/fail messages
		try {
			CallableStatement cstup = con.prepareCall(sql);
			cstup.setString(2, conFName);
			cstup.setString(3, conLName);
			cstup.setString(4, conTel);
			cstup.setString(5, conHTel);
			cstup.setString(6, conEmail);
			cstup.setString(7, conAddr1);
			cstup.setString(8, conAddr2);
			cstup.setString(9, conCity);
			cstup.setString(10, conPostcode);
			cstup.setInt(1, conID);
			cstup.executeQuery();
			System.out.println("Updated the Personal Data!");
			}
		catch (SQLException ea) {
			ea.printStackTrace();
			System.out.println("Failed to Update Personal Data!");
		}
	};
	//setting up the updateBusiness method using named parameters
	public void updateBusiness(String conFName, String conLName, String conTel, String conBTel, String conEmail, String conAddr1, String conAddr2, String conCity, String conPostcode, int conID) 
	{
		//calling the stored procedure from the database
		String sql= "CALL updateBusiness(?,?,?,?,?,?,?,?,?,?)";
		//try--catch block with appropriate pass-fail, for executing the procedure and passing correct parameters
		try {
			CallableStatement cstup = con.prepareCall(sql);
			cstup.setString(2, conFName);
			cstup.setString(3, conLName);
			cstup.setString(4, conTel);
			cstup.setString(5, conBTel);
			cstup.setString(6, conEmail);
			cstup.setString(7, conAddr1);
			cstup.setString(8, conAddr2);
			cstup.setString(9, conCity);
			cstup.setString(10, conPostcode);
			cstup.setInt(1, conID);
			cstup.executeQuery();
			System.out.println("Updated the Business Data!");
			}
		catch (SQLException ea) {
			ea.printStackTrace();
			System.out.println("Failed to Update Business Data!");
		}
	};
	//setting up the insertPersonal method and the parameters passed to it
		public void insertPersonal(String conFName, String conLName, String conTel, String conHTel, String conEmail, String conAddr1, String conAddr2, String conCity, String conPostcode) {
			//calling the stored procedure from the database with a number of parameters
			String sql = "CALL insertPersonal(?,?,?,?,?,?,?,?,?)";
			//try--catch block for insertPersonal execution with appropriate messages
					try {
						CallableStatement cstip = con.prepareCall(sql);
						cstip.setString(1, conFName);
						cstip.setString(2, conLName);
						cstip.setString(3, conTel);
						cstip.setString(4, conHTel);
						cstip.setString(5, conEmail);
						cstip.setString(6, conAddr1);
						cstip.setString(7, conAddr2);
						cstip.setString(8, conCity);
						cstip.setString(9, conPostcode);
						cstip.executeQuery();
						System.out.println("Added the Personal Data!");
					}
					catch (SQLException eb) {
						eb.printStackTrace();
						System.out.println("Failed to Add Personal Data!");
					}}
		//setting up the insertBusiness method for inserting business contacts, with named parameters
					public void insertBusiness(String conFName, String conLName, String conTel, String conBTel, String conEmail, String conAddr1, String conAddr2, String conCity, String conPostcode) {
				//calling the database's stored procedure with parameters		
						String sql = "CALL insertBusiness(?,?,?,?,?,?,?,?,?)";
						//try-catch block to insert the record with correct parameters and success/fail mssages
								try {
									CallableStatement cstip = con.prepareCall(sql);
									cstip.setString(1, conFName);
									cstip.setString(2, conLName);
									cstip.setString(3, conTel);
									cstip.setString(4, conBTel);
									cstip.setString(5, conEmail);
									cstip.setString(6, conAddr1);
									cstip.setString(7, conAddr2);
									cstip.setString(8, conCity);
									cstip.setString(9, conPostcode);
									cstip.executeQuery();
									System.out.println("Added the Business Data!");
								}
								catch (SQLException eb) {
									eb.printStackTrace();
									System.out.println("Failed to Add Business Data!");
								}

	}
}