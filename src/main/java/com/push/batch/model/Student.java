package com.push.batch.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data @Builder
public class Student implements Serializable {
    private static final long serialVersionID = 1234567891234567L;

    private String id;
    private String firstName;
    private String lastName;
    private String age;
    private String gender;
}
