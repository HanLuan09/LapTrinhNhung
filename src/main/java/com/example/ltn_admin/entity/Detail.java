package com.example.ltn_admin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "detail")
public class Detail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	private String licensePlate;
	
	private String ownerName;
	
	private String ownerPhone; 
	
	private String vehicleType;
	
	private String vehicleSize;
	
	private String etcCode;
	
	private String etcBalance;
	
	@OneToOne(mappedBy = "detail")
    private Account account;

}
