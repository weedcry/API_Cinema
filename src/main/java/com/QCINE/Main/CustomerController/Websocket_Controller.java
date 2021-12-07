package com.QCINE.Main.CustomerController;

import com.QCINE.Main.Entity.Customer_Entity;
import com.QCINE.Main.Entity.User_Entity;
import com.QCINE.Main.Repository.Customer_Repository;
import com.QCINE.Main.Service.Customer_Service;
import com.QCINE.Main.Service.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/socket")
public class Websocket_Controller {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    Customer_Repository customer_repository;

    @MessageMapping("/chat.sendMessage/seat")
    public void sendMessage(@Payload Map<String,String> message) {
        //gửi message đến tất cả customer
        List<Customer_Entity> listCus = customer_repository.findAll();
        for(Customer_Entity customer : listCus) {
            simpMessagingTemplate.convertAndSend("/message_receive/"+customer.getIdCustomer(),message.get("ghe"));
        }
    }

}
