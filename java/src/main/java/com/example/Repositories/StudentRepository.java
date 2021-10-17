package com.example.Repositories;

import com.example.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findAllBy();
    Student findByNumber(String number);
}
