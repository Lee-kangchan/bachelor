package bachelor_project;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Mgr {
	Jdb jdb = new Jdb();
	ArrayList<Student> studentlist;
	ArrayList<Professor> professorlist;
	ArrayList<Course> courselist;
	ArrayList<Regist> registlist;
	Manager manager = new Manager();
	Scanner sc = new Scanner(System.in);
	int menu;
	static String ID; // 아이디입력
	static String password; // 비밀번호입력
	String professor;
	String name;

	public void loading() throws SQLException, ClassNotFoundException {
		jdb.connection();
		studentlist = jdb.studentList();
		professorlist = jdb.prefessorList();
		registlist = jdb.registLists();
		courselist = jdb.courseLists();
	}

	public void login(int count) throws ClassNotFoundException, SQLException // 로그인 단계
	{

		System.out.println("ID:");
		ID = sc.next();
		System.out.println("password:");
		password = sc.next();

		if (manager.getManagerId().equals(ID) && manager.getManagerPW().equals(password)) {
			managerScreen();
		}
		if (count == 2) {

			for (Student S : jdb.studentList()) // list 생성된 객체들 비교
			{
				if (S.getStudentId().equals(ID) && S.getStudentPw().equals(password)) {
					name = S.getStudentName();
					studentScreen();
					return;
				}
			}
		} else if (count == 1) {
			for (Professor P : professorlist) {
				if (P.getProfessorId().equals(ID) && P.getProfessorPW().equals(password)) {
					name = P.getProfessorName();
					professorScreen();
					return;
				}
			}
		}
		if (ID == null)
			System.out.println("아이디 비밀번호 확인해주세요.");
	}

	public void signUp(int count) throws SQLException, ClassNotFoundException { // 회원가입
		System.out.println("ID:");
		ID = sc.next();
		System.out.println("password:");
		password = sc.next();
		System.out.println("name:");
		name = sc.next();

		if (count == 2) {

			System.out.println("grade:");
			String grade = sc.next();
			Student S = new Student();
			S.setStudentId(ID);
			S.setStudentPw(password);
			S.setStudentName(name);
			S.setStudentgrade(grade);
			jdb.Add(S);

		} else if (count == 1) {
			Professor P = new Professor();
			P.setProfessorId(ID);
			P.setProfessorPW(password);
			P.setProfessorName(name);
			jdb.Add(P);
		}
	}

	public void logout() { // 로그아웃
		ID = null;
		password = null;
	}

	public void studentScreen() throws SQLException { // 학생 화면
		while (true) {
			System.out.println("----------------------------------");
			System.out.println("1.수강신청 2.성적조회 3.정보수정 4.로그아웃");
			System.out.println("----------------------------------");
			menu = sc.nextInt();

			if (menu == 1) { // 과목추가
				System.out.println("과목선택:");
				String course = sc.next();
				for (Course C : courselist) {
					if (C.getCourseName().equals(course)) {
						Regist R = new Regist();
						R.setCourseId(course);
						R.setStudentId(name);
						jdb.Add(R);
						registlist=jdb.registLists();
					}
				}
			} else if (menu == 2) { // 성적조회
				System.out.println("___________________________________");
				System.out.println("과목\t중간\t기말\t학점");
				for (Regist R : registlist) {
					if (R.getStudentId().equals(name)) {
						System.out.print(R.getCourseId());
						System.out.print("\t" + R.getMiddleScore());
						System.out.print("\t" + R.getEndScore());
						System.out.println("\t" + R.getGrade());
					}
				}
				System.out.println("───────────────────────────────────");

			} else if (menu == 3) { // 정부수정

			} else if (menu == 4) {
				logout();
				break;
			}

		}
	}

	public void professorScreen() throws SQLException { // 교수 화면
		while (true) {
			System.out.println("---------------------------------");
			System.out.println("1.학생조회 2.성적평가 3.정보수정 4.로그아웃");
			System.out.println("---------------------------------");
			menu = sc.nextInt();
			if (menu == 1) {
				System.out.println("___________________________________");
				System.out.println("이름\t중간고사\t기말고사\t학점");
				for (Course C : courselist) {
					if (C.getCourseProfessorId().equals(name)) {
						for (Regist R : registlist) {
							if (R.getCourseId().equals(C.getCourseName())) {
								System.out.println(R.getStudentId() + "\t" + R.getMiddleScore() + "\t" + R.getEndScore()
										+ "\t" + R.getGrade());
							}
						}
					}
				}

				System.out.println("───────────────────────────────────");
			} else if (menu == 2) {
				System.out.println("과목입력:");
				String course = sc.next();
				for (Course C : courselist) {
					if (C.getCourseName().equals(course) && C.getCourseProfessorId().equals(name)) {
						System.out.println("학생입력:");
						String student = sc.next();
						for (Regist R : registlist) {
							if (R.getStudentId().equals(student) && R.getCourseId().equals(course)) {
								System.out.print("성적입력:");
								int score = sc.nextInt();
								if (R.getMiddleScore() == 0) {
									R.setMiddleScore(score);
								} else if (R.getMiddleScore() != 0) {
									R.setEndScore(score);
									int ave = (R.getEndScore() + R.getMiddleScore()) / 2;
									if (ave > 90) {
										R.setGrade("A");
									} else if (ave > 80) {
										R.setGrade("B");
									} else if (ave > 70) {
										R.setGrade("C");
									} else if (ave > 60) {
										R.setGrade("D");
									} else {
										R.setGrade("F");
									}
								}
								jdb.modify(R);
							}
						}
					}
				}

			}

			else if (menu == 3) {

			} else if (menu == 4) {
				logout();
				break;
			}
		}
	}

	public void managerScreen() throws SQLException { // 관리자 화면

		while (true) {
			System.out.println("----------------------------------");
			System.out.println("1.과목생성 2.과목삭제 3.회원관리 4.로그아웃");
			System.out.println("----------------------------------");
			menu = sc.nextInt();
			if (menu == 1) { // 과목생성
				System.out.println("과목 :");
				String course = sc.next();
				System.out.println("분반 :");
				int num = sc.nextInt();
				Course C = new Course();
				C.setCourseName(course);
				C.setCourseNumber(num);
				jdb.Add(C);

			} else if (menu == 2) { // 과목 삭제

				System.out.println("과목 :");
				String course = sc.next();
				for (Course C : courselist) {
					if (C.getCourseName().equals(course)) {
						jdb.removeCourse(course);
						jdb.removeRegist(course);
					}
				}

			} else if (menu == 3) { // 회원 관리
				System.out.println("-----------------------");
				System.out.println("1.교수등록 2.회원탈퇴 3.이전");
				System.out.println("-----------------------");
				menu = sc.nextInt();
				if (menu == 1) {
					System.out.println("교수입력:");
					String name = sc.next();
					for (Professor P : professorlist) {
						if (P.getProfessorName().equals(name)) {

							System.out.println("과목입력");
							String course = sc.next();
							for (Course C : courselist) {
								if (C.getCourseName().contentEquals(course)) {
									C.setCourseProfessorId(name);
									jdb.modify(C);
								}
							}
						}
					}
				} else if (menu == 2) {

				} else if (menu == 3) {

				}
			} else if (menu == 4) {
				logout();
				break;
			}
		}
	}
}
