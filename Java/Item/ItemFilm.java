package Java.Item;

import java.util.Date;

public class ItemFilm {

    int movieId;
    String name;
    String director;
    int average;
    Date releaseDate;
    String image;

    public ItemFilm(int movieId, String name, String director, int average, Date releaseDate, String image) {
        this.movieId = movieId;
        this.name = name;
        this.director = director;
        this.average = average;
        this.releaseDate = releaseDate;
        this.image=image;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getName() {
        return name;
    }

    public String getDirector() {
        return director;
    }

    public int getAverage() {
        return average;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getImage() {
        return image;
    }
}


