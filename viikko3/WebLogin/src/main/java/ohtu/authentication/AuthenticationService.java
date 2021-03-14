package ohtu.authentication;

import ohtu.data_access.UserDao;
import ohtu.domain.User;
import ohtu.util.CreationStatus;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public CreationStatus createUser(String username, String password, String passwordConfirmation) {
        CreationStatus status = new CreationStatus();

        if (userDao.findByName(username) != null) {
            status.addError("username is already taken");
        }

        checkUsernameValidity(username, status);
        checkPasswordValidity(password, status);
        matchPasswords(password, passwordConfirmation, status);

        if (status.isOk()) {
            userDao.add(new User(username, password));
        }

        return status;
    }

    private static void checkUsernameValidity(String username, CreationStatus status) {
        // validity check of username
        if (username.length() >= 3) {
            if (username.matches(".*[^a-z].*")) {
                status.addError("only letters from a to z are allowed in the username");
            }
        } else {
            status.addError("username should have at least 3 characters");
        }
    }

    private static void checkPasswordValidity(String password, CreationStatus status) {
        // validity check of password
        if (password.length() >= 8) {
            if (!password.matches(".*[^a-ö].*")) {
                status.addError("password must combine letters and other characters");
            }
        } else {
            status.addError("password should have at least 8 characters");
        }
    }

    private static void matchPasswords(String password, String passwordConfirmation, CreationStatus status) {
        // check if passwords match
        if (!password.contentEquals(passwordConfirmation)) {
            status.addError("password and password confirmation do not match");
        }
    }
}
