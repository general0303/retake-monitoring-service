package com.example.retake_monitoring_service.Services;

import com.example.retake_monitoring_service.Entities.Retake;
import com.example.retake_monitoring_service.Entities.Student;
import com.example.retake_monitoring_service.Repositories.RetakeRepository;
import com.example.retake_monitoring_service.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    RetakeRepository retakeRepository;

    public List<Student> allStudents(){
        return studentRepository.findAllBy();
    }

    public void addStudent(String number, String firstName, String secondName){
        Student student = new Student();
        student.setNumber(number);
        student.setFirstName(firstName);
        student.setSecondName(secondName);
        studentRepository.save(student);
    }

    public List<Retake> allRetakes(String number){
        return studentRepository.getById(number).getRetakes();
    }

    public List<Retake> allRecordedRetakes(String number){
        return studentRepository.getById(number).getRecordedRetakes();
    }
}
