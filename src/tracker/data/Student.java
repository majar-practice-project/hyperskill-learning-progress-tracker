package tracker.data;

public class Student implements Cloneable {
    private String fName;
    private String lName;
    private String email;

    private int javaPoint;
    private int dsaPoint;
    private int databasePoint;
    private int springPoint;

    public Student(String fName, String lName, String email) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
    }

    public int getJavaPoint() {
        return javaPoint;
    }

    public void setJavaPoint(int javaPoint) {
        this.javaPoint = javaPoint;
    }

    public int getDsaPoint() {
        return dsaPoint;
    }

    public void setDsaPoint(int dsaPoint) {
        this.dsaPoint = dsaPoint;
    }

    public int getDatabasePoint() {
        return databasePoint;
    }

    public void setDatabasePoint(int databasePoint) {
        this.databasePoint = databasePoint;
    }

    public int getSpringPoint() {
        return springPoint;
    }

    public void setSpringPoint(int springPoint) {
        this.springPoint = springPoint;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Student clone() {
        try {
            return (Student) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
