/**
 * ECE325: Object Oriented Software Design <br />
 * Lab 2: Arrays and Red Black Tree (part 1: Arrays) <br />
 * The ResearchGroup class uses a 2D array to store the names of group members
 */
public class ResearchGroups {
    // TODO: Write your searching and sorting methods here.



    public static void main(String[] args) {
        String[][] groups = { {"Bob", "Carol", "Eric", "Matt"},             // 0
                              {"Jim", "Lucy", "Terry", "Brenda", "Ben"},    // 1
                              {"Susan", "Brad", "Jim"},                     // 2
                              {"Sue", "Wendy", "Sam"},                      // 3
                              {"Kate", "Jack", "James", "Sydney"},          // 4
                              {"Mohammad", "Tim", "Kian"},                  // 5
                              {"Emma", "Carol"},                            // 6
                              {"Nick", "Osama", "Harry", "Ben"},            // 7
                              {"Mary", "John", "Ricky"} };                  // 8

        // TODO: Run your searching and sorting methods here.



        for (int i=0;i<groups.length;i++) {
            System.out.print("group " + i + ": ");
            for (int j = 0; j < groups[i].length; j++)
                System.out.print(groups[i][j] + " ");
            System.out.println();
        }
    }

}
