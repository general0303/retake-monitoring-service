package com.example.retake_monitoring_service.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="retakes")
public class Retake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="date")
    private Date date;

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    @Column(name="max_count")
    private Integer maxCount;

    @Column(name="subject")
    private String subject;

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }

    public Integer getMaxCount() {
        return maxCount;
    }

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "student_retakes",
            joinColumns = { @JoinColumn(name = "retake_id") },
            inverseJoinColumns = { @JoinColumn(name = "student_number") })
    @JsonIgnore
    private List<Student> students =new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "enrolled_students",
            joinColumns = { @JoinColumn(name = "retake_id") },
            inverseJoinColumns = { @JoinColumn(name = "student_number") })
    @JsonIgnore
    private List<Student> enrolledStudents =new ArrayList<>();

    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }
}
