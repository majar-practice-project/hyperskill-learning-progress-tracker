package tracker;

import tracker.data.Course;
import tracker.data.Student;
import tracker.data.StudentRepository;
import tracker.validation.Validator;
import tracker.view.CommandView;
import tracker.view.dto.CourseCompletedNotification;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Controller {
    private final CommandView view;
    private final StudentRepository studentRepository = new StudentRepository();
    private final Validator validator = new Validator();

    public Controller(CommandView view) {
        this.view = view;
    }

    public void run() {
        view.showInitialized();
        boolean running = true;
        while (running) {
            running = processCommand(view.getCommand());
        }
    }

    public boolean processCommand(String cmd) {
        switch (cmd) {
            case "exit":
                view.showGoodBye();
                return false;
            case "back":
                view.showExitHint();
                break;
            case "add students":
                enterStudentInfo();
                break;
            case "list":
                view.showAllStudentsId(studentRepository.getAllIds());
                break;
            case "add points":
                addPoint();
                break;
            case "find":
                findStudent();
                break;
            case "statistics":
                moveToStats();
                break;
            case "notify":
                notifyCourseCompletion();
                break;
            default:
                view.showUnknownCommandWarning();
        }
        return true;
    }

    private void enterStudentInfo() {
        int studentCount = 0;
        view.showAddStudentPrompt();
        String info = view.getStudentInfo();
        while (info != null) {
            Student student = infoToStudent(info);
            if (student != null && validateStudentInfo(student)) {
                if (studentRepository.add(student)) {
                    view.showStudentAdded();
                    studentCount++;
                } else {
                    view.showEmailTaken();
                }
            }
            info = view.getStudentInfo();
        }
        view.showTotalStudentsAdded(studentCount);
    }

    private void addPoint() {
        view.showEnterPointPrompt();
        String pointInfo = view.getStudentPoints();
        while (pointInfo != null) {

            if (!validator.validatePointFormat(pointInfo)) {
                view.showIncorrectPointFormat();
                pointInfo = view.getStudentPoints();
                continue;
            }

            String[] info = pointInfo.split(" ");
            Student student = studentRepository.findById(info[0]);
            if (student == null) {
                view.showStudentNotFound(info[0]);
                pointInfo = view.getStudentPoints();
                continue;
            }

            student.setJavaPoint(student.getJavaPoint() + Integer.parseInt(info[1]));
            student.setDsaPoint(student.getDsaPoint() + Integer.parseInt(info[2]));
            student.setDatabasePoint(student.getDatabasePoint() + Integer.parseInt(info[3]));
            student.setSpringPoint(student.getSpringPoint() + Integer.parseInt(info[4]));

            studentRepository.update(info[0], student);
            view.showPointUpdated();

            pointInfo = view.getStudentPoints();
        }
    }

    private Student infoToStudent(String info) {
        int firstSplit = info.indexOf(' ');
        int secondSplit = info.lastIndexOf(' ');
        if (firstSplit == -1 || firstSplit == secondSplit) {
            view.showIncorrectCredentials();
            return null;
        }

        return new Student(info.substring(0, firstSplit),
                info.substring(firstSplit + 1, secondSplit),
                info.substring(secondSplit + 1));
    }

    private void findStudent() {
        view.showFindPrompt();
        String id = view.getRequestedId();
        while (id != null) {
            Student student = studentRepository.findById(id);

            if (student == null) {
                view.showStudentNotFound(id);
                id = view.getRequestedId();
                continue;
            }

            view.showStudentInfo(id, student);

            id = view.getRequestedId();
        }
    }

    private boolean validateStudentInfo(Student student) {
        if (!validator.validateFirstName(student.getFName())) {
            view.showIncorrectFirstName();
            return false;
        }
        if (!validator.validateLastName(student.getLName())) {
            view.showIncorrectLastName();
            return false;
        }
        if (!validator.validateEmail(student.getEmail())) {
            view.showIncorrectEmail();
            return false;
        }
        return true;
    }

    private void moveToStats() {
        Course[][] stats = studentRepository.getStats();
        view.showStats(stats);
        Course[] courses = studentRepository.getCourses();
        Set<String> availableCourses = Arrays.stream(courses).map(c -> c.getName().toLowerCase()).collect(Collectors.toSet());
        String input = view.getCourseInput(availableCourses);
        while (input != null) {
            String finalInput = input.toLowerCase();
            Course course = Arrays.stream(courses).filter(c -> c.getName().toLowerCase().equals(finalInput)).findFirst().orElseThrow(RuntimeException::new);
            view.showCourseStats(course.getName(), studentRepository.findAllGrades(course));
            input = view.getCourseInput(availableCourses);
        }
    }

    private void notifyCourseCompletion() {
        Set<CourseCompletedNotification> notifications = studentRepository.popAllPendingNotifications();
        view.showCompletionNotifications(notifications);
    }
}
