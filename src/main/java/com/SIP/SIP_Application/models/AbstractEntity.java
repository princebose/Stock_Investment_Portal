package com.SIP.SIP_Application.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

//Base Class for all persistent entities

@MappedSuperclass
public abstract class AbstractEntity {
	private int uid;

	/**
	 * @return the uid
	 */
	@Id
	@GeneratedValue
	@Column(name="uid", unique=true)
	public int getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	protected void setUid(int uid) {
		this.uid = uid;
	}
	
}
