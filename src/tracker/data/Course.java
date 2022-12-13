package tracker.data;

public class Course {
    private final String name;

    private final int passingPoints;

    private int numberOfStudents;
    private int taskCompleted;
    private int totalPoints;

    public Course(String name, int passingPoints) {
        this.name = name;
        this.passingPoints = passingPoints;
    }

    public void addStudent() {
        numberOfStudents++;
    }

    public void addTask() {
        taskCompleted++;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public double getAveragePoint(){
        return (double)totalPoints / numberOfStudents;
    }

    public void addPoints(int point) {
        totalPoints += point;
    }

    public String getName() {
        return name;
    }

    public int getPassingPoints() {
        return passingPoints;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public int getTaskCompleted() {
        return taskCompleted;
    }

    @Override
    public String toString() {
        return String.format("%s-%d-%d-%f", name, numberOfStudents, taskCompleted, getAveragePoint());
    }
}
