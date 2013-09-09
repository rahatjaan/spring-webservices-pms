package com.spring.pms.services.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.spring.pms.webservices.Bill;
import com.spring.pms.webservices.billservice.BillDetailsRequest;
import com.spring.pms.webservices.billservice.BillDetailsResponse;
import com.spring.pms.services.BillService;

/**
 * The Class AccountService.
 */
@Endpoint
public class BillServiceEndpoint
{
	private static final String TARGET_NAMESPACE = "http://com/spring/pms/webservices/billservice";

	@Autowired
	private BillService accountService_i;

	/**
	 * Gets the account details.
	 *
	 * @param accountNumber the account number
	 * @return the account details
	 */
	@PayloadRoot(localPart = "BillDetailsRequest", namespace = TARGET_NAMESPACE)
	public @ResponsePayload BillDetailsResponse getBillDetails(@RequestPayload BillDetailsRequest request)
	{
		BillDetailsResponse response = new BillDetailsResponse();
		System.out.println(request.getLastName());
		System.out.println(request.getMemberShipNumber());
		System.out.println(request.getRoomNumber());
		/* call Spring injected service implementation to retrieve account data */
		Bill account = accountService_i.getBillDetails(request.getLastName(),request.getMemberShipNumber(),request.getRoomNumber());
		response.setBillDetails(account);
		return response;
	}

	public void setBillService(BillService accountService_p)
	{
		this.accountService_i = accountService_p;
	}
}