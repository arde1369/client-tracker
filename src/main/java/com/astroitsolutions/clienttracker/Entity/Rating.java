package com.astroitsolutions.clienttracker.Entity;

public enum Rating {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private int numericalRating;

    private Rating(int numRating){
        this.numericalRating = numRating;
    }

    public int getValue(){
        return this.numericalRating;
    }

    public static Rating getRating(int v){
        for(Rating r : Rating.values()){
            if (r.numericalRating == v){
                return Rating.valueOf(r.name());
            }
        }
        return null;
    }
}
