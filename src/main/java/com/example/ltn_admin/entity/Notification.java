package com.example.ltn_admin.entity;

public class Notification {
	private String title;
	private String message;
	private String owner_name;
	private String license_plate;
	private String tokenId;
	
	public Notification() {
		super();
	}

	public Notification( String ownerName, String licensePlate, String tokenId) {
		super();
		this.title = "Trạm thu phí Pháp Vân - Cầu Giẽ";
		this.message = "Quý khách đã thanh toán thành công dịch vụ thu phí ETC.";
		this.owner_name = ownerName;
		this.license_plate = licensePlate;
		this.tokenId = tokenId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOwner_name() {
		return owner_name;
	}

	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}

	public String getLicense_plate() {
		return license_plate;
	}

	public void setLicense_plate(String license_plate) {
		this.license_plate = license_plate;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
}
