package com.SIP.SIP_Application.models;

import javax.persistence.Entity;
import javax.persistence.Table;

//Model to represent the user
@Entity
@Table(name="users")
public class User extends AbstractEntity{
	private String userName;
	private String hash;
	
}
