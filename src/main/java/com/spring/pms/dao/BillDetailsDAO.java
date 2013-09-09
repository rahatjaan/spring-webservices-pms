package com.spring.pms.dao;

import com.spring.pms.dto.GuestInfo;
import com.spring.pms.dto.GuestStayInfo;
import com.spring.pms.dto.GuestTransactionInfo;
import com.spring.pms.webservices.Bill;

public interface BillDetailsDAO {
	Bill getBillDetails(String lastName, String membershipNumber, String roomNumber);
	GuestInfo findGuestIdByMemberShipNumber(String memberShipNumber);
	GuestStayInfo findGuestStayInfoIdByGuestInfoId(String guestInfoId, GuestInfo guestInfo);
	GuestTransactionInfo findGuestTransactionIdByGuestStayInfoId(String guestStayInfoId, GuestStayInfo guestStayInf);
}
