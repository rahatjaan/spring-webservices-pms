package com.spring.pms.dao;

import java.math.BigInteger;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.spring.pms.dataSource.DataSource;
import com.spring.pms.dto.GuestInfo;
import com.spring.pms.dto.GuestStayInfo;
import com.spring.pms.dto.GuestTransactionInfo;
import com.spring.pms.webservices.Bill;

public class BillDetailsDAOImpl implements BillDetailsDAO{
	
	DataSource ds = (DataSource) this.context.getBean("dataSource");
	private static ApplicationContext context;

	static{
		context = new ClassPathXmlApplicationContext(
				"spring-datasource.xml");
	}

	@Override
	public Bill getBillDetails(String lastName, String membershipNumber,
			String roomNumber) {
		
		GuestInfo guestInfoId = findGuestIdByMemberShipNumber(membershipNumber);
		GuestStayInfo guestStayInfoId = findGuestStayInfoIdByGuestInfoId(Integer.toString(guestInfoId.getId()),guestInfoId);
		GuestTransactionInfo transactionId = findGuestTransactionIdByGuestStayInfoId(Integer.toString(guestStayInfoId.getId()),guestStayInfoId);
		String sql = "SELECT * FROM guest_info gi, guest_stay_info gsi, guest_transactions gt WHERE gi.id = gsi.guest_info_id and gsi.id = gt.guest_stay_info_id and gi.last_name=? and gi.membership_number=? and gsi.room_number=?";
		 
		Connection conn = null;
 
		try{
			Class.forName("com.mysql.jdbc.Driver");
		      // Setup the connection with the DB
			conn = (Connection) DriverManager
			          .getConnection("jdbc:mysql://"+ds.getHOST()+":"+ds.getPORT()+"/"+ds.getDATABASE()+"?"
			              + "user="+ds.getUSER()+"&password="+ds.getPASS());
	    	PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
	    	preparedStatement.setString(1, lastName);
	    	preparedStatement.setString(2, membershipNumber);
	    	preparedStatement.setString(3, roomNumber);
	    	ResultSet rs = preparedStatement.executeQuery();
	    	
	    	System.out.println(sql);
	    	
			Bill bill = null;
			if (rs.next()) {
				bill = new Bill();
				bill.setFirstName(rs.getString("first_name"));
				bill.setLastName(rs.getString("last_name"));
				bill.setMemberShipNumber(rs.getString("membership_number"));
				bill.setMobileNumber(rs.getString("mobile_number"));
				bill.setRoomNumber(rs.getString("room_number"));
				bill.setFloorNumber(rs.getString("floor_number"));
				bill.setFolioNumber(rs.getString("folio_number"));
				bill.setTotalBill(rs.getDouble("total_bill"));
				bill.setCardNumber(rs.getString("card_number"));
				bill.setBalanceAmount(rs.getDouble("balance_amount"));
				Integer myInteger = rs.getInt("number_of_children");
				BigInteger bi = BigInteger.valueOf(myInteger.intValue());
				bill.setTotalChildren(bi);
				myInteger = rs.getInt("number_of_adult");
				bi = BigInteger.valueOf(myInteger.intValue());
				bill.setTotalAdult(bi);
				bill.setRoomRate(rs.getDouble("room_rate"));
				myInteger = rs.getInt("transaction_id");
				bi = BigInteger.valueOf(myInteger.intValue());
				bill.setTransactionId(bi);
				Date dat = rs.getDate("transaction_date");
				GregorianCalendar c = new GregorianCalendar();
				c.setTime(dat);
				XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
				bill.setTransactionDate(date2);
				bill.setReferenceNumber(rs.getString("reference_number"));
				bill.setCharges(rs.getDouble("charges"));
				
			}
			rs.close();
			preparedStatement.close();
			return bill;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	@Override
	public GuestInfo findGuestIdByMemberShipNumber(String memberShipNumber) {
		String sql = "SELECT * FROM guest_info WHERE membership_number = ?";
		 
		Connection conn = null;
 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		      // Setup the connection with the DB
			System.out.println("CREATING CONNECTION");
			if(null == ds){
				System.out.println("DS NOT WORKING FINE");
			}
			conn = (Connection) DriverManager
			          .getConnection("jdbc:mysql://"+ds.getHOST()+":"+ds.getPORT()+"/"+ds.getDATABASE()+"?"
			              + "user="+ds.getUSER()+"&password="+ds.getPASS());
			System.out.println("CONNECTION SUCCESS");
	    	PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
	    	preparedStatement.setString(1, memberShipNumber);
	    	ResultSet rs = preparedStatement.executeQuery();
			GuestInfo guestInfo = null;
			if (rs.next()) {
				guestInfo = new GuestInfo();
				guestInfo.setId(Integer.parseInt(rs.getString(1)));
				guestInfo.setFirstName(rs.getString(2));
				guestInfo.setLastName(rs.getString(3));
				guestInfo.setFullAddress(rs.getString(4));
				guestInfo.setMobileNumber(rs.getString(5));
				guestInfo.setRatePlan(rs.getString(6));
				guestInfo.setHhNumber(rs.getString(7));
				guestInfo.setAl(rs.getString(8));
				guestInfo.setBonusAl(rs.getString(9));
				guestInfo.setConfirmationNumber(rs.getString(10));
				guestInfo.setMemberShipNumber(rs.getString(11));
				guestInfo.setBonusCode(rs.getString(12));
				guestInfo.setGroupName(rs.getString(13));
			}
			rs.close();
			preparedStatement.close();
			return guestInfo;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	@Override
	public GuestStayInfo findGuestStayInfoIdByGuestInfoId(String guestInfoId, GuestInfo guestInfo) {
		String sql = "SELECT * FROM guest_stay_info WHERE guest_info_id = ?";
		 
		Connection conn = null;
 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		      // Setup the connection with the DB
			conn = (Connection) DriverManager
			          .getConnection("jdbc:mysql://"+ds.getHOST()+":"+ds.getPORT()+"/"+ds.getDATABASE()+"?"
			              + "user="+ds.getUSER()+"&password="+ds.getPASS());
	    	PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
	    	preparedStatement.setString(1, guestInfoId);
	    	ResultSet rs = preparedStatement.executeQuery();
			GuestStayInfo guestStayInfo = null;
			if (rs.next()) {
				guestStayInfo = new GuestStayInfo();
				guestStayInfo.setId(Integer.parseInt(rs.getString(1)));
				guestStayInfo.setRoomNumber(rs.getString(2));
				guestStayInfo.setFloorNumber(rs.getString(3));
				guestStayInfo.setArrivalDate(rs.getDate(4));
				guestStayInfo.setDepartureDate(rs.getDate(5));
				guestStayInfo.setFolioNumber(rs.getString(6));
				guestStayInfo.setTotalBill(rs.getDouble(7));
				guestStayInfo.setPaymentType(rs.getString(8));
				guestStayInfo.setCreditAmount(rs.getDouble(9));
				guestStayInfo.setCardType(rs.getString(10));
				guestStayInfo.setCardNumber(rs.getString(11));
				guestStayInfo.setBalanceAmount(rs.getDouble(12));
				guestStayInfo.setRoomType(rs.getString(13));
				guestStayInfo.setNumberOfChildren(rs.getInt(14));
				guestStayInfo.setNumberOfAdult(rs.getInt(15));
				guestStayInfo.setRoomRate(rs.getDouble(16));
				guestStayInfo.setGuest(guestInfo);
			}
			rs.close();
			preparedStatement.close();
			return guestStayInfo;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	@Override
	public GuestTransactionInfo findGuestTransactionIdByGuestStayInfoId(String guestStayInfoId, GuestStayInfo guestStayInfo) {
		String sql = "SELECT * FROM guest_transactions WHERE guest_stay_info_id = ?";
		 
		Connection conn = null;
 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		      // Setup the connection with the DB
			conn = (Connection) DriverManager
			          .getConnection("jdbc:mysql://"+ds.getHOST()+":"+ds.getPORT()+"/"+ds.getDATABASE()+"?"
			              + "user="+ds.getUSER()+"&password="+ds.getPASS());
	    	PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
	    	preparedStatement.setString(1, guestStayInfoId);
	    	ResultSet rs = preparedStatement.executeQuery();
			GuestTransactionInfo guestTransactionInfo = null;
			if (rs.next()) {
				guestTransactionInfo = new GuestTransactionInfo();
				guestTransactionInfo.setId(rs.getInt(1));
				guestTransactionInfo.setTransactionDate(rs.getDate(2));				
				guestTransactionInfo.setDescription(rs.getString(3));
				guestTransactionInfo.setReferenceNumber(rs.getString(4));
				guestTransactionInfo.setTransactionId(rs.getString(5));
				guestTransactionInfo.setCharges(rs.getDouble(6));
				guestTransactionInfo.setGuestStay(guestStayInfo);
			}
			rs.close();
			preparedStatement.close();
			return guestTransactionInfo;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}

}
