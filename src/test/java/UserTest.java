import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.testng.AssertJUnit;
import ru.ylab.trainingDiary.User;

public class UserTest {
    User user = new User("name", "passw", false);


    @Test
    public void getLogin() {
        Assertions.assertEquals("name", user.getLogin());

    }

    @Test
    public void getPassword() {
        Assertions.assertEquals("passw", user.getPassword());
    }


    @ParameterizedTest
    @CsvSource({
            "admin, admin",
    })
    public void validateUserTrue(String login, String password) {
        User.initAdmin();
        AssertJUnit.assertTrue(User.validateUser(login, password));
    }

    @ParameterizedTest
    @CsvSource({
            "login, password"
    })
    public void validateUserFalse(String login, String password) {
        User.initAdmin();
        AssertJUnit.assertFalse(User.validateUser(login, password));
    }
}
