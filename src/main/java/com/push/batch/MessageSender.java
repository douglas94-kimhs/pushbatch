package com.push.batch;

import com.push.batch.interfc.MessageGateway;
import com.push.batch.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MessageSender {
    @Autowired
    private MessageGateway messageGateway;

    @Async
    public void sendMessage(Student student) throws InterruptedException {
        //Thread.sleep(5000);
        messageGateway.sendMessage(student);
    }
}
