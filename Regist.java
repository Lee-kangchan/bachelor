package bachelor_project;

public class Regist {
	String studentId;
	String courseId;
	int middleScore;
	int endScore;
	String grade;
	public Regist() {}
	public Regist(String studentId, String courseId, int middleScore, int endScore, String grade) {
		super();
		this.studentId = studentId;
		this.courseId = courseId;
		this.middleScore = middleScore;
		this.endScore = endScore;
		this.grade = grade;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public int getMiddleScore() {
		return middleScore;
	}
	public void setMiddleScore(int middleScore) {
		this.middleScore = middleScore;
	}
	public int getEndScore() {
		return endScore;
	}
	public void setEndScore(int endScore) {
		this.endScore = endScore;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
}
