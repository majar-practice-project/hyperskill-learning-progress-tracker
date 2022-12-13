package tracker.view;

import tracker.data.Course;
import tracker.data.Student;
import tracker.data.StudentGrade;
import tracker.view.dto.CourseCompletedNotification;

import java.text.NumberFormat;
import java.util.*;

public class CommandView {
    Scanner scanner = new Scanner(System.in);

    private void prompt() {
//        System.out.print("> ");
    }

    public String getCommand() {
        prompt();
        String command = scanner.nextLine();
        while (command.isBlank()) {
            showEmptyCommandWarning();
            prompt();
            command = scanner.nextLine();
        }
        return command;
    }

    public void showInitialized() {
        System.out.println(ViewResources.START_UP);
    }

    public void showUnknownCommandWarning() {
        System.out.println(ViewResources.UNKNOWN_COMMAND_WARNING);
    }

    public void showGoodBye() {
        System.out.println(ViewResources.GOOD_BYE);
    }

    public void showEmptyCommandWarning() {
        System.out.println(ViewResources.EMPTY_COMMAND_WARNING);
    }

    public void showAddStudentPrompt() {
        System.out.println(ViewResources.ENTER_STUDENT_INFO_PROMPT);
    }

    public void showStudentAdded() {
        System.out.println(ViewResources.STUDENT_ADDED);
    }

    public void showTotalStudentsAdded(int numberOfStudents) {
        System.out.println(String.format(ViewResources.TOTAL_STUDENT_ADDED, numberOfStudents));
    }

    public String getStudentInfo() {
        String input = scanner.nextLine();
        return ViewResources.BACK.equals(input) ? null : input;
    }

    public void showIncorrectCredentials() {
        System.out.println(ViewResources.INCORRECT_CREDENTIALS);
    }

    public void showIncorrectEmail() {
        System.out.println(ViewResources.INCORRECT_EMAIL);
    }

    public void showIncorrectFirstName() {
        System.out.println(ViewResources.INCORRECT_FIRST_NAME);
    }

    public void showIncorrectLastName() {
        System.out.println(ViewResources.INCORRECT_LAST_NAME);
    }

    public void showEmailTaken() {
        System.out.println(ViewResources.EMAIL_TAKEN);
    }

    public void showExitHint() {
        System.out.println(ViewResources.EXIT_HINT);
    }

    public void showAllStudentsId(List<String> ids) {
        if (ids.isEmpty()) {
            System.out.println(ViewResources.EMPTY_STUDENT_ERROR);
        } else {
            System.out.println(ViewResources.STUDENT_LIST_HEADER);
            ids.forEach(System.out::println);
        }
    }

    public void showEnterPointPrompt() {
        System.out.println(ViewResources.ENTER_POINT_PROMPT);
    }

    public String getStudentPoints() {
        String input = scanner.nextLine();
        return ViewResources.BACK.equals(input) ? null : input;
    }

    public void showIncorrectPointFormat() {
        System.out.println(ViewResources.INCORRECT_POINT_FORMAT);
    }

    public void showStudentNotFound(String id) {
        System.out.printf((ViewResources.STUDENT_NOT_FOUND_WARNING) + "%n", id);
    }

    public void showFindPrompt() {
        System.out.println(ViewResources.FIND_PROMPT);
    }

    public void showPointUpdated() {
        System.out.println(ViewResources.POINTS_UPDATED);
    }

    public String getRequestedId() {
        String input = scanner.nextLine();
        return ViewResources.BACK.equals(input) ? null : input;
    }

    public void showStudentInfo(String id, Student student) {
        System.out.printf(ViewResources.STUDENT_POINT_FORMAT + "%n", id,
                student.getJavaPoint(),
                student.getDsaPoint(),
                student.getDatabasePoint(),
                student.getSpringPoint());
    }

    public void showStats(Course[][] courseStats) {
        System.out.println(ViewResources.STATS_PROMPT);
        if (courseStats == null) {
            System.out.printf(ViewResources.EMPTY_COURSE_STATS);
            return;
        }

        System.out.printf(ViewResources.ALL_COURSE_STATS, Arrays.stream(courseStats).map(this::showCourseList).toArray());
    }

    private String showCourseList(Course[] courses) {
        return Arrays.stream(courses).map(Course::getName).reduce((s1, s2) -> s1 + "," + s2).orElse("n/a");
    }

    public String getCourseInput(Set<String> coursesNames) {
        String input = scanner.nextLine();
        while (!coursesNames.contains(input.toLowerCase())) {
            if (ViewResources.BACK.equals(input)) return null;
            System.out.println(ViewResources.UNKNOWN_COURSE_WARNING);
            input = scanner.nextLine();
        }
        return input;
    }

    public void showCourseStats(String courseName, List<StudentGrade> grades) {
        System.out.println(courseName);
        System.out.printf(ViewResources.COURSE_STATS_HEADER);
        NumberFormat percentageFormat = NumberFormat.getPercentInstance();
        percentageFormat.setMinimumFractionDigits(1);
        grades.forEach(grade -> {
            System.out.printf(ViewResources.COURSE_STATS_FORMAT,
                    grade.getId(),
                    grade.getPoints(),
                    percentageFormat.format(grade.getCompleted() + 0.00001));
        });
    }

    public void showCompletionNotifications(Set<CourseCompletedNotification> notifications) {
        Set<Student> students = new HashSet<>();
        for (CourseCompletedNotification notification : notifications) {
            Student student = notification.getStudent();
            students.add(student);
            System.out.printf(ViewResources.NOTIFICATION_FORMAT, student.getEmail(),
                    getStudentFullName(student),
                    notification.getCompletedCourse().getName());
        }
        System.out.printf(ViewResources.NUMBER_OF_STUDENT_NOTIFIED, students.size());
    }

    private String getStudentFullName(Student student) {
        return String.format("%s %s", student.getFName(), student.getLName());
    }
}
