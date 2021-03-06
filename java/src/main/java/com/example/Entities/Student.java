package com.example.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="number", unique = true)
    private String number;

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    @Column(name="first_name")
    private String firstName;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    @Column(name="second_name")
    private String secondName;

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSecondName() {
        return secondName;
    }

    @Column(name="password")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "students")
    private List<Retake> retakes = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "students")
    private List<Retake> recordedRetakes = new ArrayList<>();

    public void setRecordedRetakes(List<Retake> recorded_retakes) {
        this.recordedRetakes = recorded_retakes;
    }

    public List<Retake> getRecordedRetakes() {
        return recordedRetakes;
    }

    public void setRetakes(List<Retake> retakes) {
        this.retakes = retakes;
    }

    public List<Retake> getRetakes() {
        return retakes;
    }
}
