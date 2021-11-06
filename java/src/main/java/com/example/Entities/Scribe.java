package com.example.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="scribe")
public class Scribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="number", unique = true)
    private String number;

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name="password")
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany (cascade=CascadeType.ALL, mappedBy = "author")
    private List<Letter> sent_letter;

    @OneToMany (mappedBy = "recipient")
    private List<Letter> received_letter;
}
