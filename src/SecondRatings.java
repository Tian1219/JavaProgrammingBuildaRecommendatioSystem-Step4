
/**
 * Write a description of SecondRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    public SecondRatings() {
        // default constructor
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
    }

    public SecondRatings(String moviefile, String loadRaters) {

        FirstRatings fr = new FirstRatings();

        myMovies = fr.loadMovie(moviefile);
        myRaters = fr.loadRaters(loadRaters);
    }

    public int getMoviesSize() {
        return myMovies.size();
    }

    public int getRatersSize() {
        return myRaters.size();
    }

    private double getAverageByID(String id,int minimalRaters){
        double average =0;
        double total=0;
        int countRaters= 0;
        for(Rater rater:myRaters){
            if(rater.hasRating(id)){
                countRaters++;
                total = total + rater.getRating(id);
            }
        }

        if(countRaters >= minimalRaters){
            average = total/countRaters;
        }
        return average;

    }

    public ArrayList<Rating> getAverageRatings(int minimalRasters) {

        ArrayList<Rating> allAverageRating = new ArrayList<>();
        for (Movie currMovie : myMovies) {
            String currMovieID = currMovie.getID();
            Double averageRating = getAverageByID(currMovieID, minimalRasters);
            allAverageRating.add(new Rating(currMovieID, averageRating));
        }
        return allAverageRating;
    }

    public String getTitle(String movieID) {
        for (Movie currMovie : myMovies) {
            if (currMovie.getID().equals(movieID)) {
                return currMovie.getTitle();
            }
        }
        return "N/A";
    }

    public String getID(String movieTitle) {
        for (Movie currMovie : myMovies) {
            if (currMovie.getTitle().equals(movieTitle)) {
                return currMovie.getID();
            }
        }
        return "N/A";
    }


}
