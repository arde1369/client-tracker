package com.astroitsolutions.clienttracker.Util;

public class RatingCalculator {
    
    public static int calculate(int newRating, int currentRating){
        if(currentRating == 0){
            return newRating;
        }
        
        int calucatedRating = Math.toIntExact(Math.round( newRating + currentRating / 2 ));
        return calucatedRating;
    }
}
