package com.astroitsolutions.clienttracker.Util;

public class RatingCalculator {
    
    public static int calculate(int newRating, int currentRating, int numberOfRatings){
        if(currentRating == 0){
            return newRating;
        }
        
        int calucatedRating = Math.toIntExact(Math.round( ( numberOfRatings * currentRating + newRating) / (numberOfRatings + 1.0) ));
        return calucatedRating;
    }
}
