package com.wipro.jcb.livelink.app.service.sms;

import com.wipro.jcb.livelink.app.config.TwilioConfig;
import com.wipro.jcb.livelink.app.model.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Rituraj Azad
 * User: RI20474447
 * Date:30-05-2024
 * project: msKafka
 */

@Service
public class SmsService {

    private static final Logger log = LoggerFactory.getLogger(SmsService.class);
    private final TwilioConfig twilioConfig;

    @Autowired
    public SmsService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    public void sendSms(Message message){
        try {
            List<String> mobileNumber = message.getMobileNumber();
            for (String mobNumber : mobileNumber)
            {
                PhoneNumber to = new PhoneNumber(mobNumber);
                PhoneNumber from = new PhoneNumber(twilioConfig.getTwilioPhoneNumber());
                MessageCreator creator = com.twilio.rest.api.v2010.account.Message.creator(to,from,message.getContent());
                creator.create();
            }
            log.info("SMS sent to {} with content: '{}'",message.getMobileNumber(),message.getContent());
        }
        catch (Exception e){
            log.error("Failed to send SMS : {}", e.getMessage());
        }

    }

}
