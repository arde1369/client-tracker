package com.astroitsolutions.clienttracker.Util;

import com.astroitsolutions.clienttracker.Entity.Rating;

public class RatingCalculator {
    
    public static Rating calculate(String newRating, Rating currentRating){
        int calucatedRating = Math.toIntExact(Math.round( Rating.valueOf(newRating).getValue() + currentRating.getValue() / 2 ));
        return Rating.getRating(calucatedRating);
    }
}
