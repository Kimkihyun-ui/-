package javaFund.ioex;

import javax.swing.JOptionPane;

public class HomeWorkGame {

    public static void play(HomeWorkUser currentUser) {
        String[] rps = {"", "가위", "바위", "보"};
        
        while (true) {
            String menuMsg = "[ 가위 바위 보 게임 ]\n 1.가위  2.바위  3.보 0.게임 종료";
            String input = JOptionPane.showInputDialog(null, menuMsg, "게임 시작", JOptionPane.QUESTION_MESSAGE);
            
            if (input == null || input.equals("0")) {
                System.out.println("가위 바위 보 게임을 종료합니다.");
                break;
            }
            
            if (!input.matches("[1-3]")) {
                JOptionPane.showMessageDialog(null, "1, 2, 3 중에서만 선택해주세요!");
                continue;
            }

            int userChoice = Integer.parseInt(input);
            int comChoice = (int)(Math.random() * 3) + 1;
            

            System.out.println("\n" + "=".repeat(40));
            System.out.println("나: " + rps[userChoice] + "  VS  네모박스: " + rps[comChoice]);
             
            if (userChoice == comChoice) {
                System.out.println(" [무승부] 비겼습니다!");
                currentUser.setDraw(currentUser.getDraw() + 1);
            } else if ((userChoice == 1 && comChoice == 3) ||
                       (userChoice == 2 && comChoice == 1) ||
                       (userChoice == 3 && comChoice == 2)) {
                System.out.println("[승리] " + currentUser.getId() + "님이 승리했습니다! 🏆");
                currentUser.setWin(currentUser.getWin() + 1);
            } else {
                System.out.println("[패배] 네모박스가 승리했습니다....");
                currentUser.setLose(currentUser.getLose() + 1);
            }
            
            System.out.printf("[현재 전적] %d승 %d패 %d무\n",
                    currentUser.getWin(), currentUser.getLose(), currentUser.getDraw());
            System.out.println("=".repeat(40));
        }
    }
}