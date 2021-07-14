package services;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import dao.UserDao;
import model.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    @Mock
    private UserDao dao;

    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.initMocks(this);
        this.userService = new UserService(dao);
    }

    @Test
    public void checkUserPresence_Should_Return_True() throws Exception {

        given(dao.getUserByUsername("Olga")).willReturn(
                new User("Alex"));

        boolean userExists = userService.checkUserPresence(
                new User("Olga"));

        assertThat(userExists).isTrue();

        //verify
        verify(dao).getUserByUsername("Olga");

    }

    @Test
    public void checkUserPresence_Should_Return_False() throws Exception {

        given(dao.getUserByUsername("Olga")).willReturn(
                null);

        boolean userExists = userService.checkUserPresence(
                new User("Olga"));

        assertThat(userExists).isFalse();

    }

    @Test(expected = Exception.class)
    public void checkUserPresence_Should_Throw_Exception() throws Exception {

        given(dao.getUserByUsername(anyString())).willThrow(Exception.class);

        userService.checkUserPresence(
                new User("Olga"));

    }

    @Test
    public void testCaptor() throws Exception {
        given(dao.getUserByUsername(anyString())).willReturn(
                new User("Olga")
        );

        boolean b = userService.checkUserPresence(new User("Olga"));

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        verify(dao).getUserByUsername(captor.capture());
        String argument = captor.getValue();

        assertThat(argument).isEqualTo("Olga");
    }
}
