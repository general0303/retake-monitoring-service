package com.example.retake_monitoring_service.Services;

import com.example.retake_monitoring_service.Entities.Retake;
import com.example.retake_monitoring_service.Repositories.RetakeRepository;
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

    public void addRetake(Integer maxCount, String subject){
        Date date = new Date();
        Retake retake = new Retake();
        retake.setDate(date);
        retake.setMaxCount(maxCount);
        retake.setSubject(subject);
        retakeRepository.save(retake);
    }
}
