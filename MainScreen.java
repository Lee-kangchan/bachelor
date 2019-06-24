package bachelor_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
public class MainScreen {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		int menu1;
		int menu2;
		Scanner sc = new Scanner(System.in);
		Mgr mgr = new Mgr();
		
		while (true) {
			mgr.loading();
			System.out.println("-----------------");
			System.out.println("1.교수 2.학생 3.종료");
			System.out.println("-----------------");
			menu1 = sc.nextInt();
			if (menu1 == 3) {
				System.out.println("종료중...");
				break;
			} else if (menu1 == 1 || menu1 == 2) {

				System.out.println("----------------------");
				System.out.println("1.로그인 2.회원가입 3.이전");
				System.out.println("----------------------");
				menu2 = sc.nextInt();
				
				if(menu2==1)
				{
					mgr.login(menu1);
				}
				if(menu2==2)
				{
					mgr.signUp(menu1);
				}
				else if (menu2 == 3) {
					System.out.println("이전페이지로 갑니다");
					continue;

				}
			}
		}
}	

}

