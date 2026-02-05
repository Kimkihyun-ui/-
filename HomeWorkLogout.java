package javaFund.ioex;
/*
 * 파일 입출력 무조껀 써야됨 플레이어가 1명이 될 수도 있고 20명이 될 수도 있고 
1. 클래스는 목적에 따라서 최소 5개 이상이 나오도록 설계하세요
2. 게임 시작하면 로그인 하라고 합니다.
3. 로그인은 ID 와 암호를 입력받아서 체크합니다.
3. 로그인을 하면, 해당 ID 를 가진 사람의 마지막 로그인 시간이
yyyy년 MM월 dd일 오전 몇시몇분 이었다라고 보여줍니다.

위 내용 후 아래와 같은 선택사항을 둡니다.
1.로그아웃
2.게임시작
3.내전적보기
4.전체랭킹보기(승률별 오름/내림차순으로)
5.비번 변경하기


각 옵션에 맞게 다음 메뉴를 보여주고 다음으로 진행 시킵니다.

게임 진행 방식은 기존과 같으며, 모든 게임은 로그아웃시에 저장되어집니다.

만약 처음 하는 사용자라면, 회원 가입 메뉴를 보여주고 회원가입을 시킵니다.

ID 는 이메일 형식이어야 하고, 비번은 반드시 8~12 사이의 영대문자,숫자,특문
하나가 포함 되어야 합니다.

위 조건에 맞게 되어진 회원은 가입인사 시키고 위의 메뉴대로 갑니다..
*/
import java.io.*;
import java.util.*;
public class HomeWorkLogout {

	public static void execute(Map<String, HomeWorkUser>userMap) {
		// TODO Auto-generated method stub
		String path = "C:\\Temp\\user.dat";
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))){
			oos.writeObject(userMap);
			oos.flush();
			
			System.out.println("\n" + "=".repeat(15) + " [ 로그아웃 ] " + "=".repeat(15));
            System.out.println("현재까지의 모든 게임 데이터가 안전하게 저장되었습니다.");
            System.out.println("이용해 주셔서 감사합니다. 바이바이 두바이");
            System.out.println("=".repeat(43));
		
		} catch (Exception e) {
			System.out.println("데이터 저장 중 에러 발생!" + e.getMessage());
		}

	}

}
