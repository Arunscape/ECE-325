import junit.framework.TestCase;

public class SongTest extends TestCase {

    private Song songA, songB;

    public SongTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        songA = new Song("Artist", "Song A", (float)4.35);
        songB = new Song("Artist", "Song B", 5.00);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void test_Constructor() {
        assertNotNull(songA);
        assertTrue(songA instanceof Song);
        assertNotNull(songB);
        assertTrue(songB instanceof Song);
    }

    public void test_getArtist(){
        assertTrue(songA.getArtist().toLowerCase().equals("artist"));
        assertFalse(songA.getArtist().toLowerCase().equals("different artist"));
    }

    public void test_getTitle(){
        assertTrue(songA.getTitle().toLowerCase().equals("song a"));
        assertFalse(songA.getTitle().toLowerCase().equals("different title"));
    }

    public void test_getLength(){
        assertTrue(Math.abs(songA.getLength() - 4.35) < 0.0001);
    }

    public void test_equals() {
        assertNotNull(songA);
        assertTrue(songA.equals(songA));
        assertTrue(songA.equals(new Song("aRtIsT", "sOnG a", (float)4.35)));

        assertFalse(songA.equals(songB));
        assertFalse(songA.equals(new Song("Different Artist", "Song A", (float)4.35)));
        assertFalse(songA.equals(new Song("Artist", "Different Title", (float)4.35)));
        assertFalse(songA.equals(new Song("Artist", "Song A", (float)8.97)));
    }

    public void test_isArtist() {
        assertTrue(songA.isArtist("aRtIsT"));
        assertFalse(songA.isArtist("wrong artist"));
    }

    public void test_isTitle() {
        assertTrue(songA.isTitle("sOnG a"));
        assertFalse(songA.isTitle("wrong title"));
    }

}
