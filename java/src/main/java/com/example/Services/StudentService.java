package com.example.Services;

import com.example.Entities.Retake;
import com.example.Entities.Scribe;
import com.example.Entities.Student;
import com.example.Repositories.RetakeRepository;
import com.example.Repositories.ScribeRepository;
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
    @Autowired
    ScribeRepository scribeRepository;

    public List<Student> allStudents(){
        return studentRepository.findAllBy();
    }

    public void addStudent(String number, String firstName, String secondName){
        Student student = new Student();
        student.setNumber(number);
        student.setFirstName(firstName);
        student.setSecondName(secondName);
        student.setPassword("11111");
        Scribe scribe = new Scribe();
        scribe.setNumber(number);
        scribe.setPassword("11111");
        studentRepository.save(student);
        scribeRepository.save(scribe);
    }

    public List<Retake> allRetakes(String number){
        return studentRepository.getById(number).getRetakes();
    }

    public List<Retake> allRecordedRetakes(String number){
        return studentRepository.getById(number).getRecordedRetakes();
    }
}
