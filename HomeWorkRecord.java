package javaFund.ioex;

public class HomeWorkRecord {

	public static void PrintRecord(HomeWorkUser user) {
		System.out.println("\n" + "=".repeat(15) + " [ 내 전적 보기 ] " + "=".repeat(15));
        System.out.println("계정 정보: " + user.getId());
        System.out.println("-".repeat(46));
        
        System.out.printf("승리: %d회\n", user.getWin());
        System.out.printf("패배: %d회\n", user.getLose());
        System.out.printf("무승부: %d회\n", user.getDraw());
        
        int total = user.getWin() + user.getLose() + user.getDraw();
        System.out.println("-".repeat(46));
        System.out.println("총 경기 수: " + total + "판"); 
       
        System.out.printf("현재 승률: %.2f%%\n", user.getWinRate());
        System.out.println("=".repeat(46));
    }
}