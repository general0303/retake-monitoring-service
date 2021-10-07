package com.example.retake_monitoring_service.Controllers;

import com.example.retake_monitoring_service.Entities.Retake;
import com.example.retake_monitoring_service.Entities.Student;
import com.example.retake_monitoring_service.Services.RetakeService;
import com.example.retake_monitoring_service.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
public class AppController {
    @Autowired
    StudentService studentService;
    @Autowired
    RetakeService retakeService;

    @RequestMapping(path = "/")
    public @ResponseBody String index(){
        return "index";
    }

    @RequestMapping(path = "/add_student")
    public @ResponseBody String addStudent(@RequestParam String number, @RequestParam String firstName,
                                           @RequestParam String secondName){
        studentService.addStudent(number, firstName, secondName);
        return "ok";
    }

    @RequestMapping(path="/all_students")
    public @ResponseBody List<Student> allStudents(){
        return studentService.allStudents();
    }

    @RequestMapping(path="/add_retake")
    public @ResponseBody String addRetake(@RequestParam Integer maxCount, @RequestParam String subject){
        retakeService.addRetake(maxCount, subject);
        return "ok";
    }

    @RequestMapping(path="/all_retakes")
    public @ResponseBody List<Retake> allRetakes(){
        return retakeService.allRetakes();
    }

    @RequestMapping(path="/student/{studentNumber}/all_retakes")
    public @ResponseBody List<Retake> studentsRetakes(@PathVariable String studentNumber){
        return studentService.allRetakes(studentNumber);
    }

    @RequestMapping(path="/student/{studentNumber}/recorded_retakes")
    public @ResponseBody List<Retake> studentsRecordedRetakes(@PathVariable String studentNumber){
        return studentService.allRecordedRetakes(studentNumber);
    }
}
