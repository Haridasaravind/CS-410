package cs410.webfilmz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
public class UserTest {

    @Test
    void yourFirstTestCase()
    {
        return;
    }
    private User user;
    private Film film1;
    private Film film2;
    private Catalog catalog;

    private Catalog Rating;

    @BeforeEach
    public void setUp() {
        user = new User();
        film1 = new Film("Avatar", "James Cameron", "Action", 2022, Rating.PG);
        film2 = new Film("Titanic", "James Cameron", "Drama", 2022, Rating.G);
        catalog = new Catalog();
        catalog.add (film1);
        catalog.add(film2);
    }

    @Test
    public void testIsLikedDirector() {
        user.addLiked(film1);

        assertTrue(user.isLikedDirector("James Cameron"));
        assertFalse(user.isLikedDirector("James Cameron"));



    @BeforeEach
    public void setUp()
    {
        user = new User();
        film1 = new Film("Film1", "Director1", "Genre1", 2022,Rating.PG);
        film2 = new Film("Film2", "Director2", "Genre2", 2022, Rating.G);
        catalog = new Catalog();
        catalog.add("Film1", "Director1", "Genre1", 2022, Rating.PG);
        catalog.add("Film2", "Director2", "Genre2", 2022, Rating.G);
    }

    @Test
    public void testIsLikedDirector()
    {
        user.addLiked(film1);

        assertTrue(user.isLikedDirector("Director1"));
        assertFalse(user.isLikedDirector("Director2"));
    }

    @Test
    public void testIsLikedGenre()
    {
        user.addLiked(film1);

        assertTrue(user.isLikedGenre("Action"));
        assertFalse(user.isLikedGenre("Drama"));
        assertTrue(user.isLikedGenre("Genre1"));
        assertFalse(user.isLikedGenre("Genre2"));
    }

    @Test
    public void testGetRecommendationsByDirector()
    {
        user.addLiked(film1);
        user.addLiked(film2);


        Set<Film> directorRecommendations = user.getRecommendationsByDirector(catalog);
        assertEquals(2, directorRecommendations.size());
    }
    @Test
    public void testGetRecommendationsByGenre()
    {
        user.addLiked(film1);
        user.addLiked(film2);

        Set<Film> genreRecommendations = user.getRecommendationsByGenre(catalog);
        assertEquals(2, genreRecommendations.size());
    }
}





//
//
//package cs410;
//
//import java.util.*;
//
//    /* Represents the catalog, the list of all available films.
//     * Remember mapping of director to the films they directed.
//     * Responsible for adding new films; generating recommendations, both generic
//     * and personal.
//     * Invariant: every film shows up both in allFilms set and in the set
//     *   for the director of the film in byDirector
//     * Refers to Film, factory for Films
//     * Relies on User for personal preferences.
//     */
//    public class Catalog {
//        // Contains all available films.
//        private final Set<Film> allFilms;
//
//        // mapping from director to the films they directed
//        private final Map<String, Set<Film>> byDirector;
//
//        public Catalog() {
//            allFilms = new HashSet<>();
//            byDirector = new HashMap<>();
//        }
//
//        // Factory for films
//        public Film add(String title, String director, String genre,
//                        int releaseYear) {
//            Film newFilm = new Film(title, director, genre, releaseYear);
//            allFilms.add(newFilm);
//            Set<Film> otherFilms = byDirector.get(director);
//            if (otherFilms == null) {
//                otherFilms = new HashSet<>();
//                byDirector.put(director, otherFilms);
//            }
//            otherFilms.add(newFilm);
//            return newFilm;
//        }
//
//        // Returns the film with the given title, or raises a
//        // RuntimeException if no such film is in the catalog.
//        public Film findByTitle(String title) {
//            for (Film film : allFilms) {
//                if (film.title().equals(title)) {
//                    return film;
//                }
//            }
//            throw new RuntimeException("film not found");
//        }
//
//        public Set<Film> getRecommendationsByYear(int count) {
//            Comparator<Film> comparator = Comparator.comparingInt(Film::releaseYear).reversed();
//            return getRecommendationBySorting(count, comparator);
//        }
//        public Set<Film> getRecommendationsMostWatched(int count) {
//            Comparator<Film> comparator = Comparator.comparingInt(Film::totalWatched).reversed();
//            return getRecommendationBySorting(count, comparator);
//        }
//
//        // comparator should put best recommendations at the *start* of the list
//        private Set<Film> getRecommendationBySorting(int count, Comparator<Film> comparator) {
//            List<Film> films = new ArrayList<>(allFilms);
//            films.sort(comparator);
//            count = Integer.min(count, films.size());
//            films = films.subList(0, count);
//            return new HashSet<>(films);
//        }
//        public Set<Film> getRecommendationsMostLiked(int count) {
//            Comparator<Film> comparator = Comparator.comparingInt(Film::totalLiked).reversed();
//            return getRecommendationBySorting(count, comparator);
//        }
//
//        public Set<Film> getRecommendationsByDirector(ILikeFilm user) {
//            Set<Film> recommendations = new HashSet<>();
//        /*
//        for (String director : byDirector.keySet()) {
//            if (user.isLikedDirector(director)) {
//                recommendations.addAll(byDirector.get(director));
//            }
//        }
//        */
//            for (Map.Entry<String, Set<Film>> entry : byDirector.entrySet()) {
//                if (user.isLikedDirector(entry.getKey())) {
//                    recommendations.addAll(entry.getValue());
//                }
//            }
//            return recommendations;
//        }
//        // public Set<Film> getPersonalRecommendationsByGenre(User user)
//    }


