package com.example.exploremnjpa.business;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.exploremnjpa.model.TourPackage;
import com.example.exploremnjpa.repo.TourPackageRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TourPackageService {
  private TourPackageRepository tourPackageRepository;

  public TourPackageService(TourPackageRepository tourPackageRepository) {
    this.tourPackageRepository = tourPackageRepository;
  }

  public TourPackage createTourPackage(String code, String name) {
    return tourPackageRepository.findById(code)
        .orElse(tourPackageRepository.save(new TourPackage(code, name)));
  }

  public List<TourPackage> lookupAll() {
    return tourPackageRepository.findAll();
  }

  public long total() {
    return tourPackageRepository.count();
  }
  
}
