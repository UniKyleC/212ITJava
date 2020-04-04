import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbConn {

	String connString = "jdbc:mysql://db212it.cgvico87j64y.us-east-1.rds.amazonaws.com/harriscontactdb";
	String username = "admin";
	String password = "212itc0ventry";
	Connection con = null;


	public dbConn()
	{
	try {
		con = DriverManager.getConnection(connString,username,password);
		System.out.println("Connected!");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("Failed to Connect!");
	}
	}
	
	public void deletePersonal(int conID) {
		
		String sql="CALL deletePersonal(?)";
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
	
public void deleteBusiness(int conID) {
		
		String sql="CALL deleteBusiness(?)";
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
	
	public ResultSet GetAllPersonal() {
		
		ResultSet rs=null;
		String sql="CALL selectAllPersonal";
		try {
			CallableStatement cst= con.prepareCall(sql);
			rs=cst.executeQuery();
			System.out.println("Got the Personal Data!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Failed to get Personal Data!");
		}
		return rs;}
	
	public ResultSet GetAllBusiness() {
		
		ResultSet rs=null;
		String sql="CALL selectAllBusiness";
		try {
			CallableStatement cst= con.prepareCall(sql);
			rs=cst.executeQuery();
			System.out.println("Got the Business Data!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Failed to get Business Data!");
		}
		return rs;}
	
	public void updatePersonal(String conFName, String conLName, String conTel, String conHTel, String conEmail, String conAddr1, String conAddr2, String conCity, String conPostcode, int conID) 
	{
		String sql= "CALL updatePersonal(?,?,?,?,?,?,?,?,?,?)";
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
	public void updateBusiness(String conFName, String conLName, String conTel, String conBTel, String conEmail, String conAddr1, String conAddr2, String conCity, String conPostcode, int conID) 
	{
		String sql= "CALL updateBusiness(?,?,?,?,?,?,?,?,?,?)";
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
		public void insertPersonal(String conFName, String conLName, String conTel, String conHTel, String conEmail, String conAddr1, String conAddr2, String conCity, String conPostcode) {
			String sql = "CALL insertPersonal(?,?,?,?,?,?,?,?,?)";
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
					public void insertBusiness(String conFName, String conLName, String conTel, String conBTel, String conEmail, String conAddr1, String conAddr2, String conCity, String conPostcode) {
						String sql = "CALL insertBusiness(?,?,?,?,?,?,?,?,?)";
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