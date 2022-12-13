package tracker.data;

public class StudentGrade {
    private String id;
    private int points;
    private double completed;

    public StudentGrade(String id, int points, int passingGrade) {
        this.id = id;
        this.points = points;
        this.completed = (double) points / passingGrade;
    }

    public String getId() {
        return id;
    }

    public int getPoints() {
        return points;
    }

    public double getCompleted() {
        return completed;
    }
}
