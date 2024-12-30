package com.example.exploremnjpa.web;

import com.example.exploremnjpa.model.TourRating;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Data Transfer Object for Rating a Tour.
  *
 */
@Data
public class RatingDto {

  @Min(0)
  @Max(5)  
  private Integer score;

  @Size(max = 255)
  private String comment;

  @NotNull
  private Integer customerId;

  /**
   * Constructor to fully initialize the RatingDto
   *
   * @param score      score
   * @param comment    comment
   * @param customerId customer identifier
   */
  public RatingDto(Integer score, String comment, Integer customerId) {
    this.score = score;
    this.comment = comment;
    this.customerId = customerId;
  }
  
  public RatingDto() {
	  super();
  }

  public RatingDto(TourRating entity) {
    this.score = entity.getScore();
    this.comment = entity.getComment();
    this.customerId = entity.getCustomerId();
  }


public Integer getCustomerId() { 
	return this.customerId;
}

public String getComment() {	
	return this.comment;
}

public Integer getScore() {	
	return this.score;
}
}
