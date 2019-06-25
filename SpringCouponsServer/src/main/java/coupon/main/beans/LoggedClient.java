package coupon.main.beans;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import coupon.main.enums.ClientType;

public class LoggedClient {
	private int ID;
	@Enumerated(EnumType.STRING)
	private ClientType ClientType;
	//const
	public LoggedClient(int iD, coupon.main.enums.ClientType clientType) {
		super();
		ID = iD;
		ClientType = clientType;
	}
	//get&set
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public ClientType getClientType() {
		return ClientType;
	}
	public void setClientType(ClientType clientType) {
		ClientType = clientType;
	}
	
	

}
