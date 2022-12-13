package tracker.view.dto;

import tracker.data.Course;
import tracker.data.Student;

import java.util.Objects;

public class CourseCompletedNotification {
    private Student student;
    private Course completedCourse;

    public CourseCompletedNotification(Student student, Course completedCourse) {
        this.student = student;
        this.completedCourse = completedCourse;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCompletedCourse() {
        return completedCourse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseCompletedNotification that = (CourseCompletedNotification) o;
        return student.getEmail().equals(that.student.getEmail()) && completedCourse.getName().equals(that.completedCourse.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(student.getEmail(), completedCourse.getName());
    }
}
