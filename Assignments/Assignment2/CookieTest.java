/**
 * Assignment 2: Java regular expressions <br />
 * Test cookies using regular expressions
 */
public class CookieTest {

/**
 * Verify a cookie and return the verification result
 * @param cookie        {@code String}  The cookie string
 * @return              {@code boolean} True for a legal cookie; false for an illegal one
 */
public static boolean verifyCookie(String cookie) {
        boolean legal = false;

        // String digit = "[0-9]";
        String digit = "\\d";
        String letter = "[A-Za-z]";
        String ld = String.format("%s|%s", digit, letter);
        String ldh = String.format("%s|-", ld); // letter digit or hyphen
        String ldh_str = String.format("%s+", ldh);
        String label = String.format("%s[[%s]%s]", letter, ldh_str, ld);
        String subdomain = String.format("%s|[%s.]+%s", label, label, label);
        String domain = String("%s??");
        // HttpOnly
        // Secure
        String path_val = "[^;\\x00-\\x7f]"




                          return legal;
}

/**
 * Main entry
 * @param args          {@code String[]} Command line arguments
 */
public static void main(String[] args) {
        String [] cookies = {
                // Legal cookies:
                "Set-Cookie: ns1=\"alss/0.foobar^\"",                                       // 01 name=value
                "Set-Cookie: ns1=",                                                         // 02 empty value
                "Set-Cookie: ns1=\"alss/0.foobar^\"; Expires=Tue, 18 Nov 2008 16:35:39 GMT", // 03 Expires=time_stamp
                "Set-Cookie: ns1=; Domain=",                                                // 04 empty domain
                "Set-Cookie: ns1=; Domain=.srv.a.com-0",                                    // 05 Domain=host_name
                "Set-Cookie: lu=Rg3v; Expires=Tue, 18 Nov 2008 16:35:39 GMT; Path=/; Domain=.example.com; HttpOnly", // 06
                // Illegal cookies:
                "Set-Cookie:",                                          // 07 empty cookie-pair
                "Set-Cookie: sd",                                       // 08 illegal cookie-pair: no "="
                "Set-Cookie: =alss/0.foobar^",                          // 09 illegal cookie-pair: empty name
                "Set-Cookie: ns@1=alss/0.foobar^",                      // 10 illegal cookie-pair: illegal name
                "Set-Cookie: ns1=alss/0.foobar^;",                      // 11 trailing ";"
                "Set-Cookie: ns1=; Expires=Tue 18 Nov 2008 16:35:39 GMT", // 12 illegal Expires value
                "Set-Cookie: ns1=alss/0.foobar^; Max-Age=01",           // 13 illegal Max-Age: starting 0
                "Set-Cookie: ns1=alss/0.foobar^; Domain=.0com",         // 14 illegal Domain: starting 0
                "Set-Cookie: ns1=alss/0.foobar^; Domain=.com-",         // 15 illegal Domain: trailing non-letter-digit
                "Set-Cookie: ns1=alss/0.foobar^; Path=",                // 16 illegal Path: empty
                "Set-Cookie: ns1=alss/0.foobar^; httponly",             // 17 lower case
        };
        for (int i = 0; i < cookies.length; i++)
                System.out.println(String.format("Cookie %2d: %s", i+1, verifyCookie(cookies[i]) ? "Legal" : "Illegal"));
}

}
