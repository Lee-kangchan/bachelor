package bachelor_project;

import java.sql.*;
import java.util.ArrayList;

public class Jdb {
	// Connection 변수
	Connection Conn;

	public Connection connection() throws ClassNotFoundException, SQLException {
		Conn = DBconn.getConnection();

		return Conn;
	}

	public void Add(Student S) throws SQLException {
		Statement stmt = Conn.createStatement();
		String sql = String.format(
				"INSERT INTO student(id,password,name,grade,professor) VALUES('%s','%s','%s','%s','%s')",
				S.getStudentId(), S.getStudentPw(), S.getStudentName(), S.getStudentgrade(), S.getProfessorId());
		stmt.executeUpdate(sql);
		System.out.println("추가되었습니다.");
		stmt.close();
	}

	public void Add(Professor P) throws SQLException {
		Statement stmt = Conn.createStatement();

		String sql = String.format("INSERT INTO professor(id,password,name) VALUES('%s','%s','%s')",
				P.getProfessorId(), P.getProfessorPW(), P.getProfessorName());
		stmt.executeUpdate(sql);
		System.out.println("추가되었습니다.");
		stmt.close();
	}

	public void Add(Course C) throws SQLException {
		Statement stmt = Conn.createStatement();
		String sql = String.format("INSERT INTO course(name,num,id) VALUES('%s','%d','%s')", C.getCourseName(),
				C.getCourseNumber(), C.getCourseProfessorId());
		stmt.executeUpdate(sql);
		System.out.println("추가되었습니다.");
		stmt.close();
	}

	public void Add(Regist R) throws SQLException {
		Statement stmt = Conn.createStatement();
		String sql = String.format(
				"INSERT INTO regist(student,course,midscore,highscore,grade) VALUES('%s','%s','%d','%d','%s')",
				R.getStudentId(), R.getCourseId(), R.getMiddleScore(), R.getEndScore(), R.getGrade());
		stmt.executeUpdate(sql);
		stmt.close();
	}

	public ArrayList<Student> studentList() throws SQLException {
		ArrayList<Student> result = new ArrayList<Student>();
		Statement stmt = Conn.createStatement();
		String sql = "SELECT id,password,name,grade,professor FROM student";

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			Student S = new Student();
			S.setStudentId(rs.getString("id"));
			S.setStudentPw(rs.getString("password"));
			S.setStudentName(rs.getString("name"));
			S.setStudentgrade(rs.getString("grade"));
			S.setProfessorId(rs.getString("professor"));
			result.add(S);

		}
		rs.close();
		stmt.close();
		return result;
	}

	public ArrayList<Professor> prefessorList() throws SQLException {
		ArrayList<Professor> result = new ArrayList<Professor>();
		Statement stmt = Conn.createStatement();
		String sql = "SELECT * FROM professor";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			Professor P = new Professor();
			P.setProfessorId(rs.getString("id"));
			P.setProfessorPW(rs.getString("password"));
			P.setProfessorName(rs.getString("name"));
			result.add(P);
		}
		rs.close();
		stmt.close();
		return result;
	}

	public ArrayList<Course> courseLists() throws SQLException {
		ArrayList<Course> result = new ArrayList<Course>();
		Statement stmt = Conn.createStatement();
		String sql = "SELECT * FROM course";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			Course C = new Course();
			C.setCourseName(rs.getString("name"));
			C.setCourseNumber(rs.getInt("num"));
			C.setCourseProfessorId(rs.getString("id"));
			result.add(C);
		}
		rs.close();
		stmt.close();
		return result;
	}

	public ArrayList<Regist> registLists() throws SQLException {
		ArrayList<Regist> result = new ArrayList<Regist>();
		Statement stmt = Conn.createStatement();
		String sql = "SELECT *FROM regist";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			Regist R = new Regist();
			R.setStudentId(rs.getString("student"));
			R.setCourseId(rs.getString("course"));
			R.setMiddleScore(rs.getInt("midscore"));
			R.setEndScore(rs.getInt("highscore"));
			R.setGrade(rs.getString("grade"));
			result.add(R);
		}
		return result;
	}

	public ArrayList<Student> studentLists(String name) throws SQLException {
		ArrayList<Student> result = new ArrayList<Student>();
		Statement stmt = Conn.createStatement();
		String sql = String.format("SELECT*FROM(SELECT name)WHERE NAME='%s'\",name");
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			Student S = new Student();
			S.setStudentName("name");
		}
		rs.close();
		stmt.close();

		return result;
	}

	public ArrayList<Professor> professorLists(String name) throws SQLException {
		ArrayList<Professor> result = new ArrayList<Professor>();
		Statement stmt = Conn.createStatement();
		String sql = String.format("SELECT*FROM(SELECT name)WHERE NAME='%s'", name);
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			Professor P = new Professor();
			P.setProfessorName("name");
			result.add(P);
		}
		rs.close();
		stmt.close();
		return result;

	}

	public int count1() throws SQLException {
		int result = 0;
		Statement stmt = Conn.createStatement();
		String sql = "SELECT COUNT(*) AS COUNT FROM student";
		ResultSet rs = stmt.executeQuery(sql);

		return result;
	}

	public int count2() throws SQLException {
		int result = 0;
		Statement stmt = Conn.createStatement();
		String sql = "SELECT COUNT(*) AS COUNT FROM professor";
		ResultSet rs = stmt.executeQuery(sql);

		return result;
	}

	public int count3() throws SQLException {
		int result = 0;
		Statement stmt = Conn.createStatement();
		String sql = "SELECT COUNT(*) AS COUNT FROM course";
		ResultSet rs = stmt.executeQuery(sql);

		return result;
	}

	public void modify(Student S) throws SQLException {
		Statement stmt = Conn.createStatement();
		String sql = String.format("UPDATE student SET password='%s' WHERE password=%s", S.getStudentPw());
		stmt.executeUpdate(sql);
		stmt.close();
	}

	public void modify(Professor P) throws SQLException {
		Statement stmt = Conn.createStatement();
		String sql = String.format("UPDATE professor SET password=%s WHERE password=%s", P.getProfessorPW());
		stmt.executeUpdate(sql);
		stmt.close();
	}

	public void modify(Course C) throws SQLException {
		Statement stmt = Conn.createStatement();
		String sql = String.format("UPDATE course SET id='%s' WHERE name='%s' ", C.getCourseProfessorId(),
				C.getCourseName());
		stmt.executeUpdate(sql);
		stmt.close();
	}
	public void modify(Regist R)throws SQLException{
		Statement stmt = Conn.createStatement();
		String sql = String.format("UPDATE regist SET midscore=%d,highscore=%d,grade='%s' WHERE student='%s'",R.getMiddleScore(),R.getEndScore(),R.getGrade(),R.getStudentId());
		stmt.executeUpdate(sql);
		stmt.close();
	}

	public void removeStudent(String name) throws SQLException {
		Statement stmt = Conn.createStatement();
		String sql = String.format("DELETE FROM student WHERE name=%s", name);
		stmt.executeUpdate(sql);
		stmt.close();
	}

	public void removeProfessor(String name) throws SQLException {
		Statement stmt = Conn.createStatement();
		String sql = String.format("DELETE FROM professor WHERE name=%s", name);
		stmt.executeUpdate(sql);
		stmt.close();
	}

	public void removeCourse(String name) throws SQLException {
		Statement stmt = Conn.createStatement();
		String sql = String.format("DELETE FROM course WHARE name=%s", name);
		stmt.executeUpdate(sql);
		stmt.close();
	}
	public void removeRegist(String name) throws SQLException{
		Statement stmt = Conn.createStatement();
		String sql = String.format("DELETE FROM regist WHARE course=%s", name);
		stmt.executeUpdate(sql);
		stmt.close();
	}
	public void close() throws SQLException {
		Conn.close();
	}

}
