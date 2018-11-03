import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for default package");
        //$JUnit-BEGIN$
        suite.addTestSuite(SongTest.class);
        suite.addTestSuite(PlaylistTest.class);

        //$JUnit-END$
        return suite;
    }

}
