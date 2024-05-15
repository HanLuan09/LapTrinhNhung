package com.example.ltn_admin.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ltn_admin.entity.Detail;
import com.example.ltn_admin.repository.DetailRepository;
import com.hivemq.client.mqtt.MqttClientState;
import com.hivemq.client.mqtt.datatypes.MqttQos;
import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client;

@Service
public class MqttService {
	
	@Autowired
	private DetailService detailService;
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private DetailRepository detailRepository;
	
	private Mqtt5BlockingClient client;
	
   
	
    public void MqttPublisher(String licensePlate) {
    	client = Mqtt5Client.builder()
    	        .identifier(UUID.randomUUID().toString())
    	        .serverHost("broker.hivemq.com")
    	        .buildBlocking();
        
        client.connect();
        if(client.getState() == MqttClientState.CONNECTED) {
        	System.out.println("MQTT connected");
       
	        Detail detail = getDetailByLicensePlate(licensePlate);
	       
	        if(detail != null) {
	        	client.publishWith().topic("nhom7/license_plate").qos(MqttQos.AT_LEAST_ONCE).payload(formatPublish(detail).getBytes()).send();
	        	boolean result = detailService.updateEtcBalance(licensePlate);
	        	
		        System.out.println(detail);
		        if(result) {
		        	historyService.addHistory(detail.getId());
		        	client.publishWith().topic("nhom7/servo").qos(MqttQos.AT_LEAST_ONCE).payload("1".getBytes()).send();	
		        }else {
		        	
		        }
	        }else {
	        	client.publishWith().topic("nhom7/no_license_plate").qos(MqttQos.AT_LEAST_ONCE).payload(licensePlate.getBytes()).send();
	        }
	        
	        client.disconnect();
    	}
    }
    
    public Detail getDetailByLicensePlate(String licensePlate) {
		return detailRepository.findByLicensePlate(licensePlate)
				.orElseThrow(() -> new RuntimeException("Not Fount"));
	}
    
    public String formatPublish(Detail detail) {
    	
    	return detail.getId() + "//" + detail.getOwnerName()+ "//" + detail.getLicensePlate()+ "//" + detail.getVehicleType() + "//" + detail.getVehicleSize();
    }
    
}
