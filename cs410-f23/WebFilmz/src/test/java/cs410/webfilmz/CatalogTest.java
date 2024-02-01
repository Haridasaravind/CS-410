package cs410.webfilmz;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;

/*
 *
 * ADD YOUR Catalog TESTS TO THIS FILE
 *
 */

public class CatalogTest {

    private Catalog catalog;

    @BeforeEach
    public void setup() {
        catalog = new Catalog();
    }

    @Test
    void testAddFilmToCatalog() {
        Film film = catalog.add("Avatar", "James Cameron", "Action", 2009);
        assertNotNull(film);
        assertEquals("Avatar", film.title());
    }

    @Test
    void testFindByTitle() {
        Film film = catalog.add("Avatar", "James Cameron", "Action", 2009);
        Film foundFilm = catalog.findByTitle("Avatar");
        assertEquals(film, foundFilm);
    }

    @Test
    void testGetRecommendationsByGenreForSingleGenre() {
        Film avatar = catalog.add("Avatar", "James Cameron", "Action", 2009);
        Set<Film> recommendations = catalog.getRecommendationsByGenre("Action");
        assertTrue(recommendations.contains(avatar));
    }

    @Test
    void testGetPersonalRecommendationsByGenre() {
        User user = new User();
        Film avatar = catalog.add("Avatar", "James Cameron", "Action", 2009);
        Film titanic = catalog.add("Titanic", "James Cameron", "Drama", 1997);
        user.addLiked(avatar);

        Set<Film> recommendations = catalog.getRecommendationsByGenre(user);
        assertTrue(recommendations.contains(avatar));
        assertFalse(recommendations.contains(titanic));
    }

    @Test
    void testGetRecommendationsByYear() {
        Film avatar = catalog.add("Avatar", "James Cameron", "Action", 2009);
        Film titanic = catalog.add("Titanic", "James Cameron", "Drama", 1997);

        Set<Film> recommendations = catalog.getRecommendationsByYear(1);
        assertTrue(recommendations.contains(avatar));
        assertFalse(recommendations.contains(titanic));
    }

    @Test
    void testGetRecommendationsMostWatched() {
        Film avatar = catalog.add("Avatar", "James Cameron", "Action", 2009);
        Film titanic = catalog.add("Titanic", "James Cameron", "Drama", 1997);

        avatar.incrementWatched(1);  // watched once
        titanic.incrementWatched(2); // watched twice, hence more times than Avatar

        Set<Film> recommendations = catalog.getRecommendationsMostWatched(1);
        assertTrue(recommendations.contains(titanic));
        assertFalse(recommendations.contains(avatar));
    }
    @Test
    void testGetRecommendationsMostLiked() {
        Film avatar = catalog.add("Avatar", "James Cameron", "Action", 2009);
        Film titanic = catalog.add("Titanic", "James Cameron", "Drama", 1997);

        avatar.incrementLiked(1);  // liked once
        titanic.incrementLiked(2); // liked twice, hence more times than Avatar

        Set<Film> recommendations = catalog.getRecommendationsMostLiked(1);
        assertTrue(recommendations.contains(titanic));
        assertFalse(recommendations.contains(avatar));
    }

//    @Test
//    void testGetRecommendationsByDirector() {
//        User user = new User();
//        Film avatar = catalog.add("Avatar", "James Cameron", "Action", 2009);
//        Film inception = catalog.add("Inception", "Christopher Nolan", "Action", 2010);
//        user.addLiked(avatar);
//        user.addLiked(inception);
//
//        Set<Film> recommendations = catalog.getRecommendationsByDirector(user);
//        System.out.println(recommendations);
//        assertTrue(recommendations.contains(avatar));
//        assertFalse(recommendations.contains(inception));
//    }

}
