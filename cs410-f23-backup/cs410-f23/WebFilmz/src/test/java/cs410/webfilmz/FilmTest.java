//package cs410.webfilmz;
//
///*
// *
// * ADD YOUR Catalog TESTS TO THIS FILE
// *
// */
//
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class FilmTest {
//    @Test
//    public void testGetters() {
//        Film film = new Film("The Shawshank Redemption", "Frank Darabont", "Drama", 1994, Film.Rating.R);
//
//        assertEquals("The Shawshank Redemption", film.title());
//        assertEquals("Frank Darabont", film.director());
//        assertEquals("Drama", film.genre());
//        assertEquals(1994, film.releaseYear());
//        assertEquals(Film.Rating.R, film.rating());
//    }
//
//    @Test
//    public void testIncrementWatched() {
//        Film film = new Film("Inception", "Christopher Nolan", "Science Fiction", 2010, Film.Rating.PG13);
//        film.incrementWatched(10);
//        assertEquals(10, film.totalWatched());
//    }
//
//    @Test
//    public void testIncrementLiked() {
//        Film film = new Film("The Dark Knight", "Christopher Nolan", "Action", 2008, Film.Rating.PG13);
//        film.incrementLiked(5);
//        assertEquals(5, film.totalLiked());
//    }
//
//    @Test
//    public void testIsAppropriateFor() {
//        Film film = new Film("Finding Nemo", "Andrew Stanton", "Animation", 2003, Film.Rating.G);
//
//        assertTrue(film.isAppropriateFor(Film.Rating.G));
//        assertTrue(film.isAppropriateFor(Film.Rating.PG));
//        assertFalse(film.isAppropriateFor(Film.Rating.PG13));
//        assertFalse(film.isAppropriateFor(Film.Rating.R));
//    }
//}

package cs410.webfilmz;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FilmTest {

    @Test
    void testConstructorAndGetters() {
        Film film = new Film("Avatar", "James Cameron", "Action", 2009, Film.Rating.PG_13);

        assertEquals("Avatar", film.title());
        assertEquals("James Cameron", film.director());
        assertEquals("Action", film.genre());
        assertEquals(2009, film.releaseYear());
        assertEquals(Film.Rating.PG_13, film.rating());
        assertEquals(0, film.totalWatched());
        assertEquals(0, film.totalLiked());
    }

    @Test
    void testIncrementWatchedAndLiked() {
        Film film = new Film("Inception", "Christopher Nolan", "Sci-Fi", 2010, Film.Rating.PG);

        film.incrementWatched(2);
        film.incrementLiked(1);

        assertEquals(2, film.totalWatched());
        assertEquals(1, film.totalLiked());
    }

    @Test
    void testIsAppropriateFor() {
        Film film = new Film("The Matrix", "Wachowskis", "Sci-Fi", 1999, Film.Rating.R);

        assertTrue(film.isAppropriateFor(Film.Rating.G)); // G-rated film is appropriate for any audience
        assertTrue(film.isAppropriateFor(Film.Rating.PG));
        assertTrue(film.isAppropriateFor(Film.Rating.PG_13));
        assertTrue(film.isAppropriateFor(Film.Rating.R)); // Same rating, should be appropriate
        assertFalse(film.isAppropriateFor(Film.Rating.NC_17)); // Higher rating, not appropriate
    }
}
