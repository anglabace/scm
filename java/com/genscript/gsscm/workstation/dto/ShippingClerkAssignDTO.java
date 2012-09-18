package com.genscript.gsscm.workstation.dto;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.genscript.gsscm.common.constant.WsConstants;
import com.genscript.gsscm.privilege.entity.User;
import com.genscript.gsscm.privilege.entity.UserRole;


@XmlType(name = "ShippingClerkAssignDTO", namespace = WsConstants.NS)
public class ShippingClerkAssignDTO {
	
	
	private static final long serialVersionUID = -1510059634346219560L;
	private UserRole userRole;
	private User user;	
	private Integer  shippingClerk;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
		public UserRole getUserRole() {
			return userRole;
		}
		public void setUserRole(UserRole userRole) {
			this.userRole = userRole;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
	
		public Integer getShippingClerk() {
			return shippingClerk;
		}
		public void setShippingClerk(Integer shippingClerk) {
			this.shippingClerk = shippingClerk;
		}
		
		
		
}
