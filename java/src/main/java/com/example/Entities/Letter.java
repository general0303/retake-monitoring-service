package com.example.Entities;

import javax.persistence.*;

@Entity
@Table(name="letter")
public class Letter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="text")
    private String text;

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    private Scribe author;

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    private Scribe recipient;
}
