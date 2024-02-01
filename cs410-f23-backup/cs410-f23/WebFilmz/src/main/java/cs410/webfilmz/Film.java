package cs410.webfilmz;
public class Film
{

    private final String title;
    private final String director;
    private final String genre;
    private final int releaseYear;
    private final Rating rating;

    private int totalWatched = 0;
    private int totalLiked = 0;

        // ... (the rest of your Film class)

        public enum Rating
        {
            G,
            PG,
            PG_13,
            R,
            NC_17
        }


    Film(String title, String director, String genre,
                 int releaseYear, Rating rating) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    public String title() {
        return title;
    }
    public String director() { return director; }
    public String genre() { return genre; }
    public int releaseYear() { return releaseYear; }
    public int totalWatched() { return totalWatched; }
    public int totalLiked() { return totalLiked; }
    public Rating rating() { return rating; }

    // Update watched/liked counters, respectively
    public void incrementWatched(int toAdd) {
        totalWatched = totalWatched + toAdd;
    }
    public void incrementLiked(int toAdd) {
        totalLiked = totalLiked + toAdd;
    }

    // New method to check if the film's rating is appropriate for a given audience rating
    public boolean isAppropriateFor(Rating audienceRating)
    {
        // Compare the film's rating with the audienceRating
        // You can use the ordinal() method to compare the enum values
        return this.rating.ordinal() <= audienceRating.ordinal();
    }
}




