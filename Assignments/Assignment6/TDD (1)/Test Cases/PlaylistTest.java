import junit.framework.TestCase;
import java.util.Vector;

public class PlaylistTest extends TestCase {

    private Playlist<Song> aPlaylist;
    private Song song1, song2, song3, song4, duplicate_song, nullSong;

    public void setUp() {
        aPlaylist= new Playlist<Song>("Playlist Title");
        song1 = new Song("Artist1", "Title1", 5.00);
        song2 = new Song("Artist1", "Title2", 4.50);
        song3 = new Song("Artist2", "Title1", 4.00);
        song4 = new Song("Artist2", "Title3", 5.50);
        duplicate_song = new Song("ARTIST1", "TITLE1", 5.00);   // Same song with song 1
        nullSong = null;
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    protected void fillPlaylist() {
        aPlaylist.addtoPlist(song1);
        aPlaylist.addtoPlist(song2);
        aPlaylist.addtoPlist(song3);
        aPlaylist.addtoPlist(song4);
    }

    public void test_Constructor() {
        assertNotNull(aPlaylist);
        assertTrue(aPlaylist instanceof Vector);
        assertTrue(aPlaylist.isEmpty());
    }

    public void test_getTitle() {
        assertTrue(aPlaylist.getTitle().equals("Playlist Title"));
    }

    public void test_addtoPList() {
        assertTrue(aPlaylist.isEmpty());

        assertTrue(aPlaylist.addtoPlist(song1));
        assertEquals(1, aPlaylist.size());

        assertTrue(aPlaylist.addtoPlist(song2));
        assertTrue(aPlaylist.addtoPlist(song3));
        assertEquals(3, aPlaylist.size());

        assertFalse(aPlaylist.addtoPlist(nullSong));
        assertEquals(3, aPlaylist.size());

        assertFalse(aPlaylist.addtoPlist(duplicate_song));
        assertEquals(3, aPlaylist.size());
    }

    public void test_removeSong() {
        fillPlaylist();
        int size = aPlaylist.size();

        assertFalse(aPlaylist.removeFromPlist(nullSong));
        assertEquals(size, aPlaylist.size());

        assertFalse(aPlaylist.removeFromPlist(new Song("Artist1", "Title1", 1.00)));
        assertEquals(size, aPlaylist.size());

        assertTrue(aPlaylist.contains(duplicate_song));
        assertTrue(aPlaylist.removeFromPlist(duplicate_song));  // Removing "duplicate_song" is removing "song1"
        assertEquals(size - 1, aPlaylist.size());
    }

    public void test_getSong() {
        fillPlaylist();
        assertTrue(aPlaylist.getSong(0) instanceof Song);

        assertEquals(song1, aPlaylist.getSong(0));
        assertEquals(duplicate_song, aPlaylist.getSong(0));
        assertEquals(song2, aPlaylist.getSong(1));
        assertEquals(song3, aPlaylist.getSong(2));
        assertEquals(song4, aPlaylist.getSong(3));
    }

    public void test_hasTitle() {
        fillPlaylist();
        assertTrue(aPlaylist.hasTitle("Playlist Title"));
        assertFalse(aPlaylist.hasTitle("wrong title"));
    }

    public void test_hasArtist() {
        fillPlaylist();
        assertTrue(aPlaylist.hasArtist("artist1"));
        assertFalse(aPlaylist.hasArtist("wrong artist"));
    }

    public void test_numberOfSongs() {
        fillPlaylist();
        assertEquals(4, aPlaylist.numberOfSongs());
    }

    public void test_numberOfArtists() {
        fillPlaylist();
        assertEquals(2, aPlaylist.numberOfArtists());
    }
    public void test_numberOfTitles() {
        fillPlaylist();
        assertEquals(3, aPlaylist.numberOfTitles());
    }

    public void test_playTime() {
        fillPlaylist();
        assertTrue(aPlaylist.playTime() == 19.00);
    }

    public void test_findElement() {
        fillPlaylist();
        assertEquals(0, aPlaylist.findSong(song1));
        assertEquals(1, aPlaylist.findSong(song2));
        assertEquals(2, aPlaylist.findSong(song3));
        assertEquals(3, aPlaylist.findSong(song4));
        assertEquals(-1, aPlaylist.findSong(new Song("Not", "There", 0)));
    }

    public void test_sortByArtist() {
        // TODO: Assignment 6 -- create new test case here: sort by artist

    }

    public void test_sortByTitle() {
        // TODO: Assignment 6 -- create new test case here: sort by title

    }
}
