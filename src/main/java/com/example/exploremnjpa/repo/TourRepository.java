package com.example.exploremnjpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exploremnjpa.model.Difficulty;
import com.example.exploremnjpa.model.Tour;

public interface TourRepository extends JpaRepository<Tour, Integer> {
  List<Tour> findByDifficulty(Difficulty diff);
  List<Tour> findByTourPackageCode(String code);
}