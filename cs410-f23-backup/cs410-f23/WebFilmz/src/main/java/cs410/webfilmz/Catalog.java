
package cs410.webfilmz;
import java.util.*;
import java.util.stream.Collectors;

public class Catalog {
    private final Set<Film> allFilms;
    private final Map<String, Set<Film>> byDirector;
    private final Map<String, Set<Film>> byGenre;

    public Catalog() {
        allFilms = new HashSet<>();
        byDirector = new HashMap<>();
        byGenre = new HashMap<>();
    }

    public Film add(String title, String director, String genre, int releaseYear, Rating rating) {
        Film newFilm = new Film(title, director, genre, releaseYear, rating);
        allFilms.add(newFilm);

        byDirector.computeIfAbsent(director, k -> new HashSet<>()).add(newFilm);
        byGenre.computeIfAbsent(genre, k -> new HashSet<>()).add(newFilm);

        return newFilm;
    }
    public Film add(String title, String director, String genre, int releaseYear) {
        Film newFilm = new Film(title, director, genre, releaseYear,rating);
        allFilms.add(newFilm);

        byDirector.computeIfAbsent(director, k -> new HashSet<>()).add(newFilm);
        byGenre.computeIfAbsent(genre, k -> new HashSet<>()).add(newFilm);

        return newFilm;
    }

    public Film findByTitle(String title) {
        Optional<Film> foundFilm = allFilms.stream()
                .filter(film -> film.title().equals(title))
                .findFirst();
        return foundFilm.orElseThrow(() -> new RuntimeException("Film not found"));
    }

    public Set<Film> getRecommendationsByYear(int count) {
        Comparator<Film> comparator = Comparator.comparingInt(Film::releaseYear).reversed();
        return getRecommendationBySorting(count, comparator);
    }

    public Set<Film> getRecommendationsMostWatched(int count) {
        Comparator<Film> comparator = Comparator.comparingInt(Film::totalWatched).reversed();
        return getRecommendationBySorting(count, comparator);
    }

    public Set<Film> getRecommendationsMostLiked(int count) {
        Comparator<Film> comparator = Comparator.comparingInt(Film::totalLiked).reversed();
        return getRecommendationBySorting(count, comparator);
    }

    private Set<Film> getRecommendationBySorting(int count, Comparator<Film> comparator) {
        return allFilms.stream()
                .sorted(comparator)
                .limit(count)
                .collect(Collectors.toSet());
    }

    public Set<Film> getRecommendationsByDirector(ILikeFilm user) {
        Set<Film> recommendations = new HashSet<>();
        byDirector.entrySet().stream()
                .filter(entry -> user.isLikedDirector(entry.getKey()))
                .map(Map.Entry::getValue)
                .forEach(recommendations::addAll);
        return recommendations;
    }

    public Set<Film> getRecommendationsByGenre(ILikeFilm user) {
        Set<Film> recommendations = new HashSet<>();
        byGenre.entrySet().stream()
                .filter(entry -> user.isLikedGenre(entry.getKey()))
                .map(Map.Entry::getValue)
                .forEach(recommendations::addAll);
        return recommendations;
    }

    public Set<Film> getRecommendationsByGenre(String genre) {
        return byGenre.getOrDefault(genre, new HashSet<>());
    }
}

//
//
//package cs410.webfilmz;
//import java.util.*;
//public class Catalog {
//    // all available films
//    private final Set<Film> allFilms;
//
//    // cached mapping from director to the films they directed
//    private final Map<String, Set<Film>> byDirector;
//    private final Map<String, Set<Film>> byGenre;
//
//    public Catalog() {
//        allFilms = new HashSet<>();
//        byDirector = new HashMap<>();
//        byGenre = new HashMap<>();  // added
//    }
//
//    // Factory for films, ensures that new films are recorded in the catalog.
//    public Film add(String title, String director, String genre,
//                    int releaseYear, Rating rating) {
//        Film newFilm = new Film(title, director, genre, releaseYear, rating);
//
//        public Film add (String title, String director, String genre,int releaseYear){
//            Film newFilm = new Film(title, director, genre, releaseYear);
//
//            allFilms.add(newFilm);
//
//            Set<Film> otherFilms = byDirector.get(director);
//            if (otherFilms == null) {
//                otherFilms = new HashSet<>();
//                byDirector.put(director, otherFilms);
//            }
//            otherFilms.add(newFilm);
//
//            // for byGenre
//            Set<Film> otherFilmsGenre = byGenre.get(genre);
//            if (otherFilmsGenre == null) {
//                otherFilmsGenre = new HashSet<>();
//                byGenre.put(genre, otherFilmsGenre);
//            }
//            otherFilmsGenre.add(newFilm);
//
//            return newFilm;
//        }
//        // Returns the film with the given title, or throws a
//        // RuntimeException if no such film is in the catalog.
//        public Film findByTitle (String title){
//            for (Film film : allFilms) {
//                if (film.title().equals(title)) {
//                    return film;
//                }
//            }
//            throw new RuntimeException("film not found");
//        }
//
//        // Get up to count recommendations, the most recent/watched/liked films in the catalog.
//        public Set<Film> getRecommendationsByYear ( int count){
//            Comparator<Film> comparator = Comparator.comparingInt(Film::releaseYear).reversed();
//            return getRecommendationBySorting(count, comparator);
//        }
//        public Set<Film> getRecommendationsMostWatched ( int count){
//            Comparator<Film> comparator = Comparator.comparingInt(Film::totalWatched).reversed();
//            return getRecommendationBySorting(count, comparator);
//        }
//        public Set<Film> getRecommendationsMostLiked ( int count){
//            Comparator<Film> comparator = Comparator.comparingInt(Film::totalLiked).reversed();
//            return getRecommendationBySorting(count, comparator);
//        }
//
//        // Generalization of non-personalized recommendations by Film attributes.
//        // The comparator should put best recommendations at the *start* of the list.
//        private Set<Film> getRecommendationBySorting ( int count, Comparator<Film > comparator){
//            List<Film> films = new ArrayList<>(allFilms);
//            films.sort(comparator);
//            count = Integer.min(count, films.size());
//            films = films.subList(0, count);
//            return new HashSet<>(films);
//        }
//
//        // Get all films by liked director/genre
//        public Set<Film> getRecommendationsByDirector (ILikeFilm user)
//        {
//            Set<Film> recommendations = new HashSet<>();
//            for (Map.Entry<String, Set<Film>> entry : byDirector.entrySet()) {
//                if (user.isLikedDirector(entry.getKey())) {
//                    recommendations.addAll(entry.getValue());
//                }
//            }
//            return recommendations;
//        }
//
//        public Set<Film> getRecommendationsByGenre (ILikeFilm user){
//            Set<Film> recommendations = new HashSet<>();
//            for (Map.Entry<String, Set<Film>> entry : byGenre.entrySet()) {
//                if (user.isLikedGenre(entry.getKey())) {
//                    recommendations.addAll(entry.getValue());
//                }
//            }
//            return recommendations;
//        }
//
//        public Set<Film> getRecommendationsByGenre (String genre){
//            return byGenre.getOrDefault(genre, new HashSet<>());
//        }
//    }
//}
//
