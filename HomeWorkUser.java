package javaFund.ioex;

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class HomeWorkUser implements Serializable {
	private String id;
	private String password;
	private int win;
	private int lose;
	private int draw;
	private Date lastLogin;

	public HomeWorkUser(String id, String password) {
		this.id = id;
		this.password = password;
		this.win = 0;
		this.lose = 0;
		this.draw = 0;
		this.lastLogin = new Date();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose = lose;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	// 승률
	public double getWinRate() {
		int totalGames = win + lose + draw;

		if (totalGames == 0) {
			return 0;
		}

		double rate = (double) win / totalGames * 100;
		return rate;
	}

	// 검사 전용
	public static class UserCheck {
		public static boolean isEmail(String id) {
			String emailPattern = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,8}$";

			if (id.matches(emailPattern)) {
				return true;
			} else {
				System.out.println("ID는 이메일 형식이어야 합니다.");
				return false;
			}
		}

		public static boolean isPassword(String pw) {
			if (!pw.matches("^.{8,12}$")) {
				System.out.println("에러: 비밀번호는 8~12자 사이여야 합니다.");
				return false;
			}
			if (!pw.matches(".*[A-Z].*")) {
				System.out.println("에러: 영문 대문자가 최소 하나 포함되어야 합니다.");
				return false;
			}
			if (!pw.matches(".*[0-9].*")) {
				System.out.println("에러: 숫자가 최소 하나 포함되어야 합니다.");
				return false;
			}
			if (!pw.matches(".*[@$!%*?&].*")) {
				System.out.println("에러: 특수문자가 최소 하나 포함되어야 합니다.");
				return false;
			}
			return true;
		}

	}

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		Map<String, HomeWorkUser> userMap = new HashMap<>();
		File file = new File("C:\\Temp\\users.dat");
		HomeWorkUser currentUser = null;

		if (file.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
				userMap = (Map<String, HomeWorkUser>) ois.readObject();
			} catch (Exception e) {
				System.out.println("기존 데이터를 불러오는 중 오류가 발생");
			}
		}

		System.out.println("가위 바위 보 게임 휘비고~!");

		while (currentUser == null) {
			String choice = JOptionPane.showInputDialog("1. 로그인\n2. 회원가입\n3. 종료");

			if (choice == null || choice.equals("3")) {
				System.out.println("프로그램 종료");
				System.exit(0);
			}

			if (choice.equals("1")) {
				String id = JOptionPane.showInputDialog("ID(이메일) 입력:");
				if (id == null)
					continue;

				if (!userMap.containsKey(id)) {
					JOptionPane.showMessageDialog(null, "존재하지 않는 계정입니다. 회원가입을 먼저 해주세요.");
				} else {
					String pw = JOptionPane.showInputDialog("비밀번호 입력:");
					HomeWorkUser user = userMap.get(id);
					if (user.getPassword().equals(pw)) {
						currentUser = user;
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 a hh시 mm분");
						JOptionPane.showMessageDialog(null,
								"환영합니다!\n마지막 로그인: " + sdf.format(currentUser.getLastLogin()));
						currentUser.setLastLogin(new Date());
					} else {
						JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.");
					}
				}
			} else if (choice.equals("2")) {
				String id = JOptionPane.showInputDialog("새로운 ID(이메일)를 입력하세요:");
				if (id == null)
					continue;

				if (userMap.containsKey(id)) {
					JOptionPane.showMessageDialog(null, "이미 존재하는 ID입니다.");
				} else if (UserCheck.isEmail(id)) {
					String newPw = JOptionPane.showInputDialog("비밀번호 설정(8~12자, 대문자/숫자/특문 포함):");
					if (newPw != null && UserCheck.isPassword(newPw)) {
						userMap.put(id, new HomeWorkUser(id, newPw));
						JOptionPane.showMessageDialog(null, "가입 성공! 이제 로그인해 주세요.");
					}
				}
			}
		}
		boolean isRunning = true;

		while (isRunning) {
			String menuMsg = "[ 메인 메뉴 ]\n1.로그아웃\n2.게임시작\n3.내 전적보기\n4.전체랭킹\n5.비번변경";
			String menu = JOptionPane.showInputDialog(menuMsg);

			if (menu == null)
				continue;

			switch (menu) {
			case "1":
				HomeWorkLogout.execute(userMap);
				isRunning = false;
				break;

			case "2":
				HomeWorkGame.play(currentUser);
				break;

			case "3":
				HomeWorkRecord.PrintRecord(currentUser);
				break;

			case "4":
				HomeWorkRank.showAll(userMap);
				break;

			case "5":
				HomeWorkPassword.updatePassword(currentUser);
			    break;

			default:
				JOptionPane.showMessageDialog(null, "잘못된 선택입니다. 1~5 사이의 숫자를 입력하세요.");
				break;
			}
		}

		System.out.println("프로그램을 종료합니다.");
	}

}
