package com.example.ltn_admin.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "history")
public class History {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "toll_station_name")
	private String tollStationName;
	
	@Column(name = "toll_station_address")
	private String tollStationAddress;
	
	@Column(name = "time")
	private LocalDateTime time;
	
	@Column(name = "expense")
	private String expense; 
	
	@Column(name = "payment_status")
	private String paymentStatus; 

//	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "detail_id")
	private Detail detail;
}
