package com.example.exploremnjpa.business;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.exploremnjpa.model.Difficulty;
import com.example.exploremnjpa.model.Region;
import com.example.exploremnjpa.model.Tour;
import com.example.exploremnjpa.model.TourPackage;
import com.example.exploremnjpa.repo.TourPackageRepository;
import com.example.exploremnjpa.repo.TourRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TourService {
  private TourPackageRepository tourPackageRepository;
  private TourRepository tourRepository;

  public TourService(TourPackageRepository tourPackageRepository, TourRepository tourRepository) {
    this.tourPackageRepository = tourPackageRepository;
    this.tourRepository = tourRepository;
  }

  public Tour createTour(String tourPackageName, String title,
      String description, String blurb, Integer price, String duration,
      String bullets, String keywords, Difficulty difficulty, Region region) {

    TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName)
        .orElseThrow(() -> new RuntimeException("Tour Package not found for id:" + tourPackageName));
    
    return tourRepository.save(new Tour(title, description, blurb,
        price, duration, bullets, keywords, tourPackage, difficulty, region));
  }

  public List<Tour> lookupByDifficulty(Difficulty difficulty) {
    return tourRepository.findByDifficulty(difficulty);
  }

  public List<Tour> lookupByPackage(String tourPackageCode) {
    return tourRepository.findByTourPackageCode(tourPackageCode);
  }

  public long total() {
    return tourRepository.count();
  }
}
