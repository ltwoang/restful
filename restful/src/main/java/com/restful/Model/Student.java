package com.restful.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Table(name = "students")
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "class_name")
    private String className;

    @Column(name = "major")
    private String major;

    @Column(name = "hometown")
    private String hometown;

    @Column(name = "gender")
    private String gender;

    @Column(name = "average_mark")
    private Double averageMark;
}
