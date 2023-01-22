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
}
