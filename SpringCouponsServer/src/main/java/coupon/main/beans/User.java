package coupon.main.beans;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import coupon.main.enums.ClientType;

public class User {
	private int userId;
	private String userName;
	private String password;
	@Enumerated(EnumType.STRING)
	private ClientType ClientType;
	//const
	public User() {}
		
	public User(String userName, String password, ClientType clientType) {
		this.userName = userName;
		this.password = password;
		this.ClientType = clientType;
	}
		public User(int userId, String userName, String password,ClientType clientType) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.ClientType = clientType;
	}
	//get&set
	


	public String getUserName() {
		return userName;
	}
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUsername(String username) {
		userName = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ClientType getClientType() {
		return ClientType;
	}
	public void setClientType(ClientType clientType) {
		ClientType = clientType;
	}
	@Override
	public String toString() {
		return "User [User name=" + userName + ", Password=" + password + ", ClientType=" + ClientType + "]";
	}
	
	
	

}
