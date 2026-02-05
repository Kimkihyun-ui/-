package javaFund.ioex;
import java.util.*;
public class HomeWorkRank {

	public static void showAll(Map<String, HomeWorkUser> userMap) {
        List<HomeWorkUser> userList = new ArrayList<>(userMap.values());

        Collections.sort(userList, new Comparator<HomeWorkUser>() {
            @Override
            public int compare(HomeWorkUser u1, HomeWorkUser u2) {
                
                return Double.compare(u2.getWinRate(), u1.getWinRate());
            }
        });

        System.out.println("\n" + "=".repeat(20) + " [ 전체 랭킹 ] " + "=".repeat(20));
        System.out.println(" 순위\t ID\t\t전적\t\t승률");
        System.out.println("-".repeat(55));

        int rank = 1;
        for (HomeWorkUser user : userList) {
            System.out.printf(" %d위\t %-15s\t %d승 %d패 %d무\t %.2f%%\n",
                    rank++, 
                    user.getId(), 
                    user.getWin(), user.getLose(), user.getDraw(), 
                    user.getWinRate());
        }
        System.out.println("=".repeat(55));
    }
}
