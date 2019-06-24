package bachelor_project;

public class Course {

	private String courseName;
	private int courseNumber;
	private String courseProfessorId;
	
	public Course() {}

	public Course(String courseName, int courseNumber, String courseProfessorId) {
		super();
		this.courseName = courseName;
		this.courseNumber = courseNumber;
		this.courseProfessorId = courseProfessorId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getCourseProfessorId() {
		return courseProfessorId;
	}

	public void setCourseProfessorId(String professorId) {
		this.courseProfessorId = professorId;
	}
	
}
