package edu.school21.service;


import edu.school21.model.User;
import edu.school21.repositories.UsersRepository;
import edu.school21.services.UsersServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import java.util.Arrays;
import java.util.List;

class UsersServiceImplTest {
    @Mock
    private UsersRepository ur;
    @InjectMocks
    private UsersServiceImpl usi;
    private List<User> testList = Arrays.asList(
            new User(1L, "ilya", "zuev", true),
            new User(2L, "java", "core", false),
            new User(1L, "s21", "abc", true)
    );

    @BeforeEach
    public void init() {
        ur = Mockito.mock(UsersRepository.class);
        usi = new UsersServiceImpl(ur);
    }

    @Test
    public void authenticateTestShouldReturnTrue() {
        Mockito.when(ur.findByLogin("ilya")).thenReturn(new User(1L, "ilya", "zuev", false));
        Mockito.doNothing().when(ur).update(testList.get(0));
        boolean res = usi.authenticate("ilya", "zuev");
        Assertions.assertTrue(res);
    }

    @Test
    public void authenticateTestIncorrectLogin() {
        Mockito.when(ur.findByLogin("dima")).thenReturn(null);
        Mockito.doNothing().when(ur).update(null);
        boolean res = usi.authenticate("dima", "asfa");
        Assertions.assertFalse(res);
    }

    @Test
    public void authenticateTestIncorrectPassword() {
        Mockito.when(ur.findByLogin("java")).thenReturn(testList.get(1));
        Mockito.doNothing().when(ur).update(null);
        boolean res = usi.authenticate("java", "lol");
        Assertions.assertFalse(res);
    }
}
