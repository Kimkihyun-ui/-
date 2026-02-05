package javaFund.ioex;

import javax.swing.JOptionPane;

public class HomeWorkPassword {

    public static void updatePassword(HomeWorkUser user) {
        String title = "[ 비밀번호 변경 ]";
        String msg = "새로운 비밀번호를 입력하세요\n(8~12자, 대문자/숫자/특문 포함)";
        
        String newPw = JOptionPane.showInputDialog(null, msg, title, JOptionPane.QUESTION_MESSAGE);

        if (newPw == null) {
            System.out.println("\n>>> 비밀번호 변경이 취소되었습니다.");
            return;
        }

        if (HomeWorkUser.UserCheck.isPassword(newPw)) {
            user.setPassword(newPw);
            System.out.println("\n" + "=".repeat(30));
            System.out.println("ID: " + user.getId());
            System.out.println("결과: 비밀번호가 성공적으로 변경되었습니다.");
            System.out.println("=".repeat(30));
        } else {
            System.out.println(">>> 비밀번호 변경 실패 (규칙 위반)");
        }
    }
}