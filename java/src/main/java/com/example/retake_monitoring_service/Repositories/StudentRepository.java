package com.example.retake_monitoring_service.Repositories;

import com.example.retake_monitoring_service.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findAllBy();
    Student findByNumber(String number);
}
