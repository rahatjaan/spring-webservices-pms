package com.spring.pms.services;

import com.spring.pms.webservices.Bill;

/**
 * The Interface AccountService.
 */
public interface BillService
{

	/**
	 * Gets the account details.
	 *
	 * @param accountNumber the account number
	 * @return the account details
	 */
	public Bill getBillDetails(String lastName, String membershipNumber,
			String roomNumber);
}
