package tracker.validation;

public class Validator {
    private final String EMAIL_FORMAT = "^[a-zA-Z0-9.!#$%&'*+/=? ^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)+$";
    private final String NAME_FORMAT = "^[a-zA-Z]([a-zA-Z]|[-'](?=[^-']))*[a-zA-Z] ([a-zA-Z]|[-' ](?<=[-' ]))*[a-zA-Z]$";

    private final String FIRST_NAME_FORMAT = "^[a-zA-Z]([a-zA-Z]|[-'](?=[^-']))*[a-zA-Z]$";
    private final String LAST_NAME_FORMAT = "^[a-zA-Z]([a-zA-Z]|[-' ](?=[^-' ]))*[a-zA-Z]$";
    private final String POINT_ENTRY_FORMAT = "^[^ ]+( [0-9]+){4}$";

    public boolean validateName(String name) {
        if (name == null) return false;
        return name.matches(NAME_FORMAT);
    }

    public boolean validateEmail(String email) {
        if (email == null) return false;
        return email.matches(EMAIL_FORMAT);
    }

    public boolean validateFirstName(String fName) {
        if (fName == null) return false;
        return fName.matches(FIRST_NAME_FORMAT);
    }

    public boolean validateLastName(String lName) {
        if (lName == null) return false;
        return lName.matches(LAST_NAME_FORMAT);
    }

    public boolean validatePointFormat(String info) {
        if (info == null) return false;
        return info.matches(POINT_ENTRY_FORMAT);
    }
}