package com.fb.movie.projections.availability;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MovieAvailability {

    @Id
    private String title;
    private int numberOfCopiesPurchased;
    private int numberOfCopiesAvailable;

    public MovieAvailability() {
    }

    public MovieAvailability(String title, int numberOfCopiesPurchased, int numberOfCopiesAvailable) {
        this.title = title;
        this.numberOfCopiesPurchased = numberOfCopiesPurchased;
        this.numberOfCopiesAvailable = numberOfCopiesAvailable;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getNumberOfCopiesAvailable() {
        return numberOfCopiesAvailable;
    }

    public void setNumberOfCopiesAvailable(int numberOfCopiesAvailable) {
        this.numberOfCopiesAvailable = numberOfCopiesAvailable;
    }

    public int getNumberOfCopiesPurchased() {
        return numberOfCopiesPurchased;
    }

    public void setNumberOfCopiesPurchased(int numberOfCopiesPurchased) {
        this.numberOfCopiesPurchased = numberOfCopiesPurchased;
    }

    @Override
    public String toString() {
        return
                "title='" + title + '\'' +
                ", numberOfCopiesPurchased=" + numberOfCopiesPurchased +
                ", numberOfCopiesAvailable=" + numberOfCopiesAvailable +
                '}';
    }
}
