package com.example.exploremnjpa.web;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.exploremnjpa.business.TourRatingService;
import com.example.exploremnjpa.model.TourRating;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;



@RestController
@RequestMapping(path = "/tours/{tourId}/ratings")
public class TourRatingController {
  private TourRatingService tourRatingService;
  
  private Logger log = LoggerFactory.getLogger(TourRatingController.class);

  public TourRatingController(TourRatingService tourRatingService) {
    this.tourRatingService = tourRatingService;
  }

  /**
   * Create a Tour Rating.
   *
   * @param tourId
   * @param ratingDto
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public RatingDto createTourRating(@PathVariable(value = "tourId") int tourId,
      @RequestBody @Valid RatingDto ratingDto) {
      TourRating rating = tourRatingService.createNew(tourId, ratingDto.getCustomerId(), 
        ratingDto.getScore(), ratingDto.getComment());
    return new RatingDto(rating);
  }

  @GetMapping
  public List<RatingDto> getAllRatingsForTour(@PathVariable(value = "tourId") int tourId) {
	log.info("GET /tours/{}/ratings", tourId);
    List<TourRating> tourRatings = tourRatingService.lookupRatings(tourId);
    return tourRatings.stream().map(RatingDto::new).toList();
  }

  /**
   * Calculate the average Score of a Tour.
   *
   * @param tourId
   * @return the average value.
   */
  @GetMapping("/average")
  public Map<String, Double> getAverage(@PathVariable(value = "tourId") int tourId) {
    return Map.of("average", tourRatingService.getAverageScore(tourId));
  }

  /**
   * Update score and comment of a Tour Rating
   *
   * @param tourId
   * @param ratingDto
   * @return The modified Rating DTO.
   */
  @PutMapping
  public RatingDto updateWithPut(@PathVariable(value = "tourId") int tourId, @RequestBody @Valid RatingDto ratingDto) {
      return new RatingDto(tourRatingService.update(tourId, ratingDto.getCustomerId(),
                ratingDto.getScore(), ratingDto.getComment()));
  }

  /**
   * Update score or comment of a Tour Rating
   *
   * @param tourId
   * @param ratingDto
   * @return The modified Rating DTO.
   */
  @PatchMapping
  public RatingDto updateWithPatch(@PathVariable(value = "tourId") int tourId,
      @RequestBody @Valid RatingDto ratingDto) {
    return new RatingDto(tourRatingService.updateSome(tourId,
        ratingDto.getCustomerId(),
        Optional.ofNullable(ratingDto.getScore()),
        Optional.ofNullable(ratingDto.getComment())));
  }

  /**
   * Delete a Rating of a tour made by a customer
   *
   * @param tourId
   * @param customerId
   */
  @DeleteMapping("/{customerId}")
  public void delete(@PathVariable(value = "tourId") int tourId, @PathVariable(value = "customerId") int customerId) {
    tourRatingService.delete(tourId, customerId);
  }

  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String return404(NoSuchElementException exception) {
    return exception.getMessage();
  }
  
  /**
   * Create Several Tour Ratings for one tour, score and several customers.
   *
   * @param tourId
   * @param score
   * @param customers
   */
  @PostMapping("/batch")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Give Many Tours Same Score")
  public void createManyTourRatings(@PathVariable(value = "tourId") int tourId,
                                    @RequestParam(value = "score") int score,
                                    @RequestBody List<Integer> customers) {
    log.info("POST /tours/{}/ratings/batch", tourId);
    tourRatingService.rateMany(tourId, score, customers);
  }
}
