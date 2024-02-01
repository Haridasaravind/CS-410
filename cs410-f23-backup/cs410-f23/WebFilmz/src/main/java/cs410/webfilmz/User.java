
package cs410.webfilmz;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import java.util.*;

public class User implements ILikeFilm {

    private final Set<Film> watched;
    private final Set<Film> liked;
    private final Set<String> likedDirectors;
    private final Set<String> likedGenres;
    private Rating maxAcceptableRating;  // Add this field to store the user's maximum acceptable rating.

    public User(Rating maxAcceptableRating) {
        watched = new HashSet<>();
        liked = new HashSet<>();
        likedDirectors = new HashSet<>();
        likedGenres = new HashSet<>();
        this.maxAcceptableRating = maxAcceptableRating;
    }

    public void addWatched(Film film) {
        if (watched.add(film)) {
            film.incrementWatched(1);
        }
    }

    public void addLiked(Film film) {
        if (liked.add(film)) {
            film.incrementLiked(1);
            likedDirectors.add(film.director());
            likedGenres.add(film.genre());
        }
    }

    // Modify the getAllRecommendations method to filter films based on the user's maximum acceptable rating.
    public Map<String, Set<Film>> getAllRecommendations(Catalog catalog, int initialGenericRecsCount) {
        Set<Film> newReleases = new HashSet<>(catalog.getRecommendationsByYear(initialGenericRecsCount));
        newReleases.removeIf(film -> film.getRating().ordinal() > maxAcceptableRating.ordinal());
        newReleases.removeAll(watched);

        Set<Film> directorRecommendations = getRecommendationsByDirector(catalog);
        directorRecommendations.removeIf(film -> film.getRating().ordinal() > maxAcceptableRating.ordinal());
        directorRecommendations.removeAll(watched);

        Set<Film> genreRecommendations = getRecommendationsByGenre(catalog);
        genreRecommendations.removeIf(film -> film.getRating().ordinal() > maxAcceptableRating.ordinal());
        genreRecommendations.removeAll(watched);

        Set<Film> mostWatched = new HashSet<>(catalog.getRecommendationsMostWatched(initialGenericRecsCount));
        mostWatched.removeIf(film -> film.getRating().ordinal() > maxAcceptableRating.ordinal());
        mostWatched.removeAll(watched);

        Set<Film> mostLiked = new HashSet<>(catalog.getRecommendationsMostLiked(initialGenericRecsCount));
        mostLiked.removeIf(film -> film.getRating().ordinal() > maxAcceptableRating.ordinal());
        mostLiked.removeAll(watched);

        Map<String, Set<Film>> recommendations = new HashMap<>();
        recommendations.put("New Releases", newReleases);
        recommendations.put("Favorite Directors", directorRecommendations);
        recommendations.put("Favorite Genres", genreRecommendations);
        recommendations.put("Most Watched", mostWatched);
        recommendations.put("Most Liked", mostLiked);

        return recommendations;
    }

    public Set<Film> getRecommendationsByDirector(Catalog catalog) {
        Set<Film> directorRecommendations = catalog.getRecommendationsByDirector(this);
        directorRecommendations.removeIf(film -> film.getRating().ordinal() > maxAcceptableRating.ordinal());
        directorRecommendations.removeAll(watched);
        return new HashSet<>(directorRecommendations);
    }

    public Set<Film> getRecommendationsByGenre(Catalog catalog) {
        Set<Film> genreRecommendations = catalog.getRecommendationsByGenre(this);
        genreRecommendations.removeIf(film -> film.getRating().ordinal() > maxAcceptableRating.ordinal());
        genreRecommendations.removeAll(watched); // Remove films the user has already watched from genre recommendations
        return new HashSet<>(genreRecommendations);
    }

    public boolean isLikedDirector(String director) {
        return likedDirectors.contains(director);
    }

    public boolean isLikedGenre(String genre) {
        return likedGenres.contains(genre);
    }
}


//
//
//
//
//public class User implements ILikeFilm {
//
//    private final Set<Film> watched;
//    private final Set<Film> liked;
//
//    private final Set<String> likedDirectors;
//    private final Set<String> likedGenres;
//
//    public User() {
//        watched = new HashSet<>();
//        liked = new HashSet<>();
//        likedDirectors = new HashSet<>();
//        likedGenres = new HashSet<>();
//    }
//
//    public void addWatched(Film film) {
//        if (watched.add(film)) {
//            film.incrementWatched(1);
//        }
//    }
//
//    public void addLiked(Film film) {
//        if (liked.add(film)) {
//            film.incrementLiked(1);
//            likedDirectors.add(film.director());
//            likedGenres.add(film.genre());
//        }
//
//    }
//
//    public Map<String, Set<Film>> getAllRecommendations(Catalog catalog, int initialGenericRecsCount)
//    {
//        Set<Film> newReleases = new HashSet<>(catalog.getRecommendationsByYear(initialGenericRecsCount));
//        newReleases.removeAll(watched);
//
//        Set<Film> directorRecommendations = getRecommendationsByDirector(catalog);
//        Set<Film> genreRecommendations = getRecommendationsByGenre(catalog);
//        Set<Film> mostWatched = new HashSet<>(catalog.getRecommendationsMostWatched(initialGenericRecsCount));
//        Set<Film> mostLiked = new HashSet<>(catalog.getRecommendationsMostLiked(initialGenericRecsCount));
//
//        mostWatched.removeAll(watched);
//        mostLiked.removeAll(watched);
//
//        Map<String, Set<Film>> recommendations = new HashMap<>();
//        recommendations.put("New Releases", newReleases);
//        recommendations.put("Favorite Directors", directorRecommendations);
//        recommendations.put("Favorite Genres", genreRecommendations);
//        recommendations.put("Most Watched", mostWatched);
//        recommendations.put("Most Liked", mostLiked);
//
//        return recommendations;
//    }
//
//    public Set<Film> getRecommendationsByDirector(Catalog catalog)
//    {
//        Set<Film> directorRecommendations = catalog.getRecommendationsByDirector(this);
//        directorRecommendations.removeAll(watched);
//        return new HashSet<>(directorRecommendations);
//    }
//
//    public Set<Film> getRecommendationsByGenre(Catalog catalog)
//    {
//        Set<Film> genreRecommendations = catalog.getRecommendationsByGenre(this);
//        genreRecommendations.removeAll(watched); // Remove films the user has already watched from genre recommendations
//        return new HashSet<>(genreRecommendations);
//    }
//
//
//    public boolean isLikedDirector(String director) {
//        return likedDirectors.contains(director);
//    }
//
//    public boolean isLikedGenre(String genre) {
//        return likedGenres.contains(genre);
//    }
//
//// Implement other methods required by ILikeFilm if any.
//}
