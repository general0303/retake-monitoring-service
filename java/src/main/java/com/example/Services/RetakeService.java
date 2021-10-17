package com.example.Services;

import com.example.Repositories.RetakeRepository;
import com.example.Entities.Retake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RetakeService {
    @Autowired
    RetakeRepository retakeRepository;

    public List<Retake> allRetakes(){
        return retakeRepository.findAllBy();
    }

    public void addRetake(Integer maxCount, String subject, String sDate){
        Date date = new Date();
        String[] data = sDate.split("-");
        date.setDate(Integer.parseInt(data[2]));
        date.setMonth(Integer.parseInt(data[1])-1);
        date.setYear(Integer.parseInt(data[0])-1900);
        Retake retake = new Retake();
        retake.setDate(date);
        retake.setMaxCount(maxCount);
        retake.setSubject(subject);
        retakeRepository.save(retake);
    }
}
