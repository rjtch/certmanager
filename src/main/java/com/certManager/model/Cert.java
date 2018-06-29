package com.certManager.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "cert")
public class Cert implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	protected String _id;
	
	private String name;
	private LocalDate arrivedDate;
	private LocalDate modifyDate;
	private LocalDate lockeddate;
	private LocalDate unlockedDate;
	private boolean loked;
	private String encoded;
	private BigInteger serialNumber;
	



	public Cert() { }

	public Cert(String name, LocalDate arrivedDate, LocalDate modifyDate, LocalDate lockeddate,
			LocalDate unlockedDate, boolean loked, String contenttype) {
		super();
		this.name = name;
		this.arrivedDate = arrivedDate;
		this.modifyDate = modifyDate;
		this.lockeddate = lockeddate;
		this.unlockedDate = unlockedDate;
		this.loked = loked;
		this.encoded = contenttype;
	}

	public BigInteger getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(BigInteger serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getId() {
		return _id;
	}

	public void setId(String id) {
		this._id = id;
	}
	

	public boolean getBlocked() {
		return loked;
	}

	public void setBlocked(boolean block) {
		this.loked= block;
	}
	
	public LocalDate getArrivedDate() {
		return arrivedDate;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name2) {
		this.name = name2;
	}

	public void setArrivedDate(LocalDate arrivedDate) {
		this.arrivedDate = arrivedDate;
	}

	public LocalDate getLockeddate() {
		return lockeddate;
	}

	public void setLockeddate(LocalDate localDate) {
		this.lockeddate = localDate;
	}

	public LocalDate getUnlockedDate() {
		return unlockedDate;
	}

	public void setUnlockedDate(LocalDate unlockedDate) {
		this.unlockedDate = unlockedDate;
	}

	public LocalDate getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDate modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public String getEncoded() {
		return encoded;
	}

	public void setEncoded(String encoded) {
		this.encoded = encoded;
	}

	@Override
	public String toString() {
		return "Cert [_id=" + _id + ", name=" + name + ", arrivedDate=" + arrivedDate + ", modifyDate=" + modifyDate
				+ ", lockeddate=" + lockeddate + ", unlockedDate=" + unlockedDate + ", loked=" + loked + ", encoded="
				+ encoded + ", serialNumber=" + serialNumber + "]";
	}

	
}
