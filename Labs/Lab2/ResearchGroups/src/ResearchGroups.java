import java.util.Arrays;

/**
 * ECE325: Object Oriented Software Design <br />
 * Lab 2: Arrays and Red Black Tree (part 1: Arrays) <br />
 * The ResearchGroup class uses a 2D array to store the names of group members
 */
public class ResearchGroups {
    // TODO: Write your searching and sorting methods here.

	private static void searchMember(String query, String[][] list) {
//			System.out.println(Arrays.asList(group).indexOf(query));
		int cnt = 0;
		Boolean inList = false;
		String belongsIn = "";
		Boolean leader = false;
		for (String[] group : list) {
//			System.out.println(Arrays.asList(group).indexOf(query));
			int idx = Arrays.asList(group).indexOf(query);
			if (idx > -1) {
				inList=true;
				belongsIn += String.format("%d  ", cnt);
			}
			if (idx == 0) leader=true;
			cnt++;
			}
		if (inList){
			System.out.println(String.format("Leader: %s, Group(s): %s ", leader?"yes":"no", belongsIn)); 
		}
		else {
			System.out.println("Person does not exist");
		}
		
	}

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
        
        searchMember("Bob", groups);

//        for (int i=0;i<groups.length;i++) {
//            System.out.print("group " + i + ": ");
//            for (int j = 0; j < groups[i].length; j++)
//                System.out.print(groups[i][j] + " ");
//            System.out.println();
//        }
    }

}
