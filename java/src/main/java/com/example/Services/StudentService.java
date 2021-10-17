package com.example.Services;

import com.example.Entities.Retake;
import com.example.Entities.Student;
import com.example.Repositories.RetakeRepository;
import com.example.Repositories.StudentRepository;
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
