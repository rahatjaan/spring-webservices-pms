package com.spring.pms.services;

import org.springframework.stereotype.Service;

import com.spring.pms.dao.BillDetailsDAOImpl;
import com.spring.pms.webservices.Bill;
//import com.spring.pms.webservices.EnumAccountStatus;

/**
 * The Class AccountService.
 */
@Service
public class BillServiceImpl implements BillService
{

	/**
	 * Gets the account details.
	 *
	 * @param accountNumber the account number
	 * @return the account details
	 */
	public Bill getBillDetails(String lastName, String membershipNumber, String roomNumber)
	{

		
		BillDetailsDAOImpl billDetailsImpl = new BillDetailsDAOImpl();
		Bill bill = billDetailsImpl.getBillDetails(lastName, membershipNumber, roomNumber);

		return bill;
	}
}
