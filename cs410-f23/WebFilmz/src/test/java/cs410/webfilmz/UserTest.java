package cs410.webfilmz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;


public class UserTest {
    private User user;
    private Film film1;
    private Film film2;
    private Catalog catalog;

    @BeforeEach
    public void setUp() {
        user = new User();
        film1 = new Film("Film1", "Director1", "Genre1", 2022);
        film2 = new Film("Film2", "Director2", "Genre2", 2022);
        catalog = new Catalog();
        catalog.add("Film1", "Director1", "Genre1", 2022);
        catalog.add("Film2", "Director2", "Genre2", 2022);
    }

    @Test
    public void testIsLikedDirector() {
        user.addLiked(film1);

        assertTrue(user.isLikedDirector("Director1"));
        assertFalse(user.isLikedDirector("Director2"));
    }

    @Test
    public void testIsLikedGenre() {
        user.addLiked(film1);

        assertTrue(user.isLikedGenre("Genre1"));
        assertFalse(user.isLikedGenre("Genre2"));
    }

    @Test
    public void testGetRecommendationsByDirector() {
        user.addLiked(film1);
        user.addLiked(film2);


        Set<Film> directorRecommendations = user.getRecommendationsByDirector(catalog);
        assertEquals(2, directorRecommendations.size());
    }
    @Test
    public void testGetRecommendationsByGenre() {
        user.addLiked(film1);
        user.addLiked(film2);

        Set<Film> genreRecommendations = user.getRecommendationsByGenre(catalog);
        assertEquals(2, genreRecommendations.size());
    }
}





