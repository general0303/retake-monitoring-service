package com.example.retake_monitoring_service.Repositories;

import com.example.retake_monitoring_service.Entities.Retake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface RetakeRepository extends JpaRepository<Retake, Integer> {
    List<Retake> findAllBy();
    List<Retake> findAllByDateAfter(Date date);
    List<Retake> findAllById(Integer id);
}
