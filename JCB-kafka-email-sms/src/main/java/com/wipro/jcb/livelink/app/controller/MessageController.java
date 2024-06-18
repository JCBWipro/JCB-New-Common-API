package com.wipro.jcb.livelink.app.controller;


import com.wipro.jcb.livelink.app.model.ContactResponse;
import com.wipro.jcb.livelink.app.service.email.EmailService;
import com.wipro.jcb.livelink.app.service.sms.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wipro.jcb.livelink.app.model.Message;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Rituraj Azad
 * User: RI20474447
 * Date:30-05-2024
 * project: msKafka
 */
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Value("${contact.ms.uri}")
    private  String contactUrl;

    private static final Logger log = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private EmailService emailService;
    @Autowired
    private SmsService smsService;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/send/{id}")
    public ResponseEntity<String> sendMessage(@PathVariable int id){
        ParameterizedTypeReference<List<ContactResponse>> responseType = new ParameterizedTypeReference<List<ContactResponse>>(){};
        ResponseEntity<List<ContactResponse>> responseEntity= webClientBuilder.build().get().uri(contactUrl+id).retrieve().toEntity(responseType).block();
        List<ContactResponse> contactResponses = responseEntity.getBody();
        Message msg = new Message();
        //SMS Call
        List<String> mobList = new ArrayList<>();
        for (ContactResponse contactResp : contactResponses)
        {
            mobList.add(contactResp.getMobileNumber());
        }
        msg.setMobileNumber(mobList);
        msg.setContent("Hi,This is System generated SMS from JCB LiveLink Application ");
        msg.setTimeStamp(LocalDateTime.now());
        smsService.sendSms(msg);
        //Email Call
        List<String> emailList = new ArrayList<>();
        for (ContactResponse contactResp : contactResponses)
        {
            emailList.add(contactResp.getEmail());
        }
        log.info("Message sent Successfully to : '{}'",msg);
        emailService.sendEmail(emailList, "Testing Email from APIGateway" ,"This is System generated Email from JCB LiveLink Application");
        log.info("Email sent Successfully");
        return ResponseEntity.status(HttpStatus.OK).body("Message sent Successfully to "+mobList);
    }
}
