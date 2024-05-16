package com.example.ltn_admin.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ltn_admin.entity.Detail;
import com.example.ltn_admin.entity.History;
import com.example.ltn_admin.entity.Notification;
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
	        	boolean result = detailService.updateEtcBalance(licensePlate);
	        	
		        System.out.println(detail);
		        if(result) {
		        	Notification notification = new Notification(detail.getOwnerName(),detail.getLicensePlate(),"fpwvae0BQtGBjfVqOp1wjj:APA91bFay6KEQYmhSHQ63jWP3Mct_owNao9_JZIjLwsn_yhIUhBlO8N6L-FkM_61glq-MkDsUW5lzq1lLWpkxm8hCIscqmKN5TLKaO-ya3oWCnQ35xceu4idHQIvb8BV3hW9WEfAuOy5");
		        	Utils.sendNotification(notification);
		        	
		        	History history = historyService.addHistory(detail.getId(), setExpense(detail.getVehicleSize()));
		        	client.publishWith().topic("nhom7/license_plate").qos(MqttQos.AT_LEAST_ONCE).payload(formatPublish(detail, history.getExpense()).getBytes()).send();
		        	
		        	client.publishWith().topic("nhom7/servo").qos(MqttQos.AT_LEAST_ONCE).payload("1".getBytes()).send();	
		        }else {
		        	client.publishWith().topic("nhom7/license_plate").qos(MqttQos.AT_LEAST_ONCE).payload(formatPublish(detail, "null").getBytes()).send();
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
    
    public String formatPublish(Detail detail, String s) {
    	
    	return detail.getId() + "//" + detail.getOwnerName()+ "//" 
    			+ detail.getLicensePlate()+ "//" + detail.getVehicleType() + "//" 
    			+ detail.getVehicleSize() + "//" + s;
    }
    public String setExpense(String size) {
    	if(size.equals("2 chỗ")) return "10000";
    	else if(size.equals("4 chỗ")) return "10000";
    	else if(size.equals("6 chỗ")) return "15000";
    	else if(size.equals("16 chỗ")) return "16000";
    	else if(size.equals("24 chỗ")) return "20000";
    	return "22000";
    }
}
