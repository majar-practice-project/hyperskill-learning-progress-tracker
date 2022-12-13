package tracker.view;

public class ViewResources {
    public static final String START_UP = "Learning Progress Tracker";
    public static final String EMPTY_COMMAND_WARNING = "No input.";
    public static final String GOOD_BYE = "Bye!";
    public static final String UNKNOWN_COMMAND_WARNING = "Error: unknown command!";

    /*  ADD STUDENT  */
    public static final String ENTER_STUDENT_INFO_PROMPT = "Enter student credentials or 'back' to return";
    public static final String STUDENT_ADDED = "The student has been added.";
    public static final String TOTAL_STUDENT_ADDED = "Total %d students have been added.";
    public static final String BACK = "back";
    public static final String EXIT_HINT = "Enter 'exit' to exit the program";

    public static final String INCORRECT_CREDENTIALS = "Incorrect credentials.";
    public static final String INCORRECT_EMAIL = "Incorrect email.";
    public static final String INCORRECT_FIRST_NAME = "Incorrect first name.";
    public static final String INCORRECT_LAST_NAME = "Incorrect last name.";

    public static final String EMAIL_TAKEN = "This email is already taken.";

    public static final String STUDENT_LIST_HEADER = "Students:";
    public static final String EMPTY_STUDENT_ERROR = "No students found";

    public static final String ENTER_POINT_PROMPT = "Enter an id and points or 'back' to return:";
    public static final String INCORRECT_POINT_FORMAT = "Incorrect points format";

    public static final String FIND_PROMPT = "Enter an id or 'back' to return";
    public static final String STUDENT_POINT_FORMAT = "%s points: Java=%d; DSA=%d; Databases=%d; Spring=%d";

    public static final String STUDENT_NOT_FOUND_WARNING = "No student is found for id=%s";

    public static final String POINTS_UPDATED = "Points updated";

    /*   statistics   */
    public static final String STATS_PROMPT = "Type the name of a course to see details or 'back' to quit:";
    public static final String ALL_COURSE_STATS = "Most popular: %s%n" +
            "Least popular: %s%n" +
            "Highest activity: %s%n" +
            "Lowest activity: %s%n" +
            "Easiest course: %s%n" +
            "Hardest course: %s%n";

    public static final String EMPTY_COURSE_STATS = ALL_COURSE_STATS.replaceAll("%s", "n/a");
    public static final String UNKNOWN_COURSE_WARNING = "Unknown course.";

    public static final String COURSE_STATS_FORMAT = "%-20s %-20s %-20s%n";
    public static final String COURSE_STATS_HEADER = String.format(COURSE_STATS_FORMAT, "id", "points", "completed");

    public static final String NOTIFICATION_FORMAT = "To: %s\n" +
            "Re: Your Learning Progress\n" +
            "Hello, %s! You have accomplished our %s course!\n";
    public static final String NUMBER_OF_STUDENT_NOTIFIED = "Total %d students have been notified.%n";
}
