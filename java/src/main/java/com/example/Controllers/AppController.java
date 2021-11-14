package com.example.Controllers;

import com.example.Services.StudentService;
import com.example.Entities.Retake;
import com.example.Entities.Student;
import com.example.Services.RetakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
public class AppController {
    @Autowired
    StudentService studentService;
    @Autowired
    RetakeService retakeService;

    @RequestMapping(path = "/")
    public String index(Model model, Principal principal){
        String login = principal.getName();
        String password = "111111";
        model.addAttribute("login", login);
        model.addAttribute("password", password);
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(path = "/add_new_student")
    public String addNewStudent(Model model){
        String number = "";
        model.addAttribute("studentNumber", number);
        String firstName = "";
        model.addAttribute("firstName", firstName);
        String secondName = "";
        model.addAttribute("secondName", secondName);
        return "add_student";
    }

    @PostMapping(path = "/add_student")
    public String addStudent(@RequestParam String studentNumber, @RequestParam String firstName,
                                           @RequestParam String secondName){
        studentService.addStudent(studentNumber, firstName, secondName);
        return "redirect:/";
    }

    @RequestMapping(path="/all_students")
    public @ResponseBody List<Student> allStudents(){
        return studentService.allStudents();
    }

    @RequestMapping(path = "/add_new_retake")
    public String addNewRetake(Model model){
        String subject = "";
        model.addAttribute("subject", subject);
        Integer maxCount = 100;
        model.addAttribute("maxCount", maxCount);
        Date date = new Date();
        model.addAttribute("date", date);
        return "add_retake";
    }

    @PostMapping(path="/add_retake")
    public String addRetake(@RequestParam Integer maxCount, @RequestParam String subject,
                                          @RequestParam String date){
        retakeService.addRetake(maxCount, subject, date);
        return "redirect:/";
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

    @RequestMapping(path = "/give_access")
    public String giveAccess(Model model){
        String studentNumber = "";
        model.addAttribute("studentNumber", studentNumber);
        Integer retakeId = 1;
        model.addAttribute("retakeId", retakeId);
        return "open_access";
    }

    @RequestMapping(path = "/close_access")
    public String closeAccess(Model model){
        String studentNumber = "";
        model.addAttribute("studentNumber", studentNumber);
        Integer retakeId = 1;
        model.addAttribute("retakeId", retakeId);
        return "close_access";
    }

    @RequestMapping(path = "/sign_up_for_retake")
    public String signUpForRetake(Model model){
        String studentNumber = "";
        model.addAttribute("studentNumber", studentNumber);
        Integer retakeId = 1;
        model.addAttribute("retakeId", retakeId);
        return "sign_up_for_retake";
    }
}
