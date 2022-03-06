package com.push.batch.controller;

import com.push.batch.MessageSender;
import com.push.batch.interfc.MessageGateway;
import com.push.batch.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
//    @Autowired
//    private MessageGateway messageGateway;

    @Autowired
    private MessageSender sender;

    public long id_inc = 1;

    @GetMapping(value = "/")
    public String gotoIndex()throws InterruptedException  {

        Student st = Student.builder().age("10").id(String.valueOf(id_inc++)).firstName("douglas").lastName("kim").gender("male").build();
        sender.sendMessage(st);
//        System.out.println("called gotoIndex");
        return "Push-Batch Program";
    }


    @PostMapping
    public void sendStudentInformation(@RequestBody Student student) throws InterruptedException {
        sender.sendMessage(student);
    }
}
