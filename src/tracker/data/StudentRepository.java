package tracker.data;

import tracker.view.dto.CourseCompletedNotification;

import java.util.*;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class StudentRepository {
    private final Course[] courses = {
            new Course("Java", 600),
            new Course("DSA", 400),
            new Course("Databases", 480),
            new Course("Spring", 550)
    };
    private final Set<String> emails = new HashSet<>();
    private final Map<UUID, Student> students = new HashMap<>();

    private Set<CourseCompletedNotification> notificationQueue = new HashSet<>();

    public boolean add(Student student) {
        String email = student.getEmail();
        if (emails.contains(email)) return false;

        emails.add(email);
        students.put(UUID.randomUUID(), student);
        return true;
    }

    public boolean update(String uuid, Student student) {
        UUID id = UUID.fromString(uuid);
        if (!students.containsKey(id)) return false;
        updateStats(id, student);
        students.put(UUID.fromString(uuid), student);
        return true;
    }

    public Course[][] getStats() {
        if (Arrays.stream(courses).noneMatch(c -> c.getNumberOfStudents() != 0)) return null;
        Course[][] courseStats = new Course[6][];
        fillStats(courseStats, 0, Course::getNumberOfStudents);
        fillStats(courseStats, 2, Course::getTaskCompleted);
        fillStats(courseStats, 4, Course::getAveragePoint);
        return courseStats;
    }

    private void fillStats(Course[][] courseStats, int index, ToIntFunction<Course> function) {
        Course[] temp = courses.clone();
        int max = Arrays.stream(temp).mapToInt(function).max().orElse(0);
        courseStats[index++] = Arrays.stream(temp).filter(c -> function.applyAsInt(c) == max).toArray(Course[]::new);
        int min = Arrays.stream(temp).mapToInt(function).min().orElse(0);
        courseStats[index] = max == min
                ? new Course[]{}
                : Arrays.stream(temp).filter(c -> function.applyAsInt(c) == min).toArray(Course[]::new);
    }

    private void fillStats(Course[][] courseStats, int index, ToDoubleFunction<Course> function) {
        Course[] temp = Arrays.stream(courses).filter(c -> c.getNumberOfStudents() != 0).toArray(Course[]::new);
        double max = Arrays.stream(temp).mapToDouble(function).max().orElse(0);
        courseStats[index++] = Arrays.stream(temp).filter(c -> function.applyAsDouble(c) == max).toArray(Course[]::new);
        double min = Arrays.stream(temp).mapToDouble(function).min().orElse(0);
        courseStats[index] = max == min
                ? new Course[]{}
                : Arrays.stream(temp).filter(c -> function.applyAsDouble(c) == min).toArray(Course[]::new);
    }

    private void updateStats(UUID id, Student newStudent) {
        Student oldStudent = students.get(id);
        if (newStudent.getJavaPoint() > 0) {
            courses[0].addTask();
            courses[0].addPoints(newStudent.getJavaPoint() - oldStudent.getJavaPoint());
            if (oldStudent.getJavaPoint() == 0) courses[0].addStudent();
            int passingPoints = courses[0].getPassingPoints();
            if (newStudent.getJavaPoint() >= passingPoints && oldStudent.getJavaPoint() < passingPoints) {
                notificationQueue.add(new CourseCompletedNotification(newStudent, courses[0]));
            }
        }
        if (newStudent.getDsaPoint() > 0) {
            courses[1].addTask();
            courses[1].addPoints(newStudent.getDsaPoint() - oldStudent.getDsaPoint());
            if (oldStudent.getDsaPoint() == 0) courses[1].addStudent();
            int passingPoints = courses[1].getPassingPoints();
            if (newStudent.getDsaPoint() >= passingPoints && oldStudent.getDsaPoint() < passingPoints) {
                notificationQueue.add(new CourseCompletedNotification(newStudent, courses[1]));
            }
        }
        if (newStudent.getDatabasePoint() > 0) {
            courses[2].addTask();
            courses[2].addPoints(newStudent.getDatabasePoint() - oldStudent.getDatabasePoint());
            if (oldStudent.getDatabasePoint() == 0) courses[2].addStudent();
            int passingPoints = courses[2].getPassingPoints();
            if (newStudent.getDatabasePoint() >= passingPoints && oldStudent.getDatabasePoint() < passingPoints) {
                notificationQueue.add(new CourseCompletedNotification(newStudent, courses[2]));
            }
        }
        if (newStudent.getSpringPoint() > 0) {
            courses[3].addTask();
            courses[3].addPoints(newStudent.getSpringPoint() - oldStudent.getSpringPoint());
            if (oldStudent.getSpringPoint() == 0) courses[3].addStudent();
            int passingPoints = courses[3].getPassingPoints();
            if (newStudent.getSpringPoint() >= passingPoints && oldStudent.getSpringPoint() < passingPoints) {
                notificationQueue.add(new CourseCompletedNotification(newStudent, courses[3]));
            }
        }
    }

    public Student findById(String uuid) {
        UUID id;
        try {
            id = UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            return null;
        }
        if (!students.containsKey(id)) return null;
        return students.get(id).clone();
    }

    public List<String> getAllIds() {
        return students.keySet().stream().map(UUID::toString)
                .sorted().collect(Collectors.toList());
    }

    public Course[] getCourses() {
        return courses.clone();
    }

    public List<StudentGrade> findAllGrades(Course course) {
        switch (course.getName()) {
            case "Java":
                return findAllGrades(Student::getJavaPoint, course.getPassingPoints());
            case "DSA":
                return findAllGrades(Student::getDsaPoint, course.getPassingPoints());
            case "Databases":
                return findAllGrades(Student::getDatabasePoint, course.getPassingPoints());
            case "Spring":
                return findAllGrades(Student::getSpringPoint, course.getPassingPoints());
            default:
                return null;
        }
    }

    private List<StudentGrade> findAllGrades(Function<Student, Integer> gradeFunction, int passingGrade) {
        return students.entrySet().stream().filter(entry -> gradeFunction.apply(entry.getValue()) > 0)
                .sorted(Comparator.comparing(entry -> gradeFunction.apply(((Map.Entry<UUID, Student>) entry).getValue())).reversed()
                        .thenComparing(entry -> ((Map.Entry<UUID, Student>) entry).getKey().toString()))
                .map(entry -> new StudentGrade(entry.getKey().toString(), gradeFunction.apply(entry.getValue()), passingGrade))
                .collect(Collectors.toUnmodifiableList());
    }

    public Set<CourseCompletedNotification> popAllPendingNotifications() {
        Set<CourseCompletedNotification> notifications = notificationQueue;
        notificationQueue = new HashSet<>();
        return notifications;
    }
}