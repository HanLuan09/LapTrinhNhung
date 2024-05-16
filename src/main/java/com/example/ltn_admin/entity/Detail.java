package com.example.ltn_admin.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
	
//	@OneToOne(mappedBy = "detail")
//    private Account account;

	@JsonIgnore
//	@JsonManagedReference
	@OneToMany(mappedBy = "detail")
	private List<History> histories;
	
	@JsonIgnore
//	@JsonManagedReference
	@OneToMany(mappedBy = "detail")
	private List<Token> tokens;
}
