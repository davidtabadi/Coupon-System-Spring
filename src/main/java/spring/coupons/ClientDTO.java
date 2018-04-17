package spring.coupons;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import facades.ClientType;

@XmlRootElement
public class ClientDTO implements Serializable {
	/**
	 * ClientDTO must implement serializable and generated private static final long
	 * serialVersionUID needed
	 * 
	 */
	private static final long serialVersionUID = -1022399595361995809L;
	private ClientType clientType;
	private String name;
	private String password;

	public ClientDTO() {
	}

	public ClientDTO(ClientType clientType, String name, String password) {
		this.clientType = clientType;
		this.name = name;
		this.password = password;
	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "ClientDTO [clientType=" + clientType + ", name=" + name + ", password=" + password + "]";
	}

}
