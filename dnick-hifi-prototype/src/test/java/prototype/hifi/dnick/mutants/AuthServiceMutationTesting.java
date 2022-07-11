package prototype.hifi.dnick.mutants;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import prototype.hifi.dnick.model.User;
import prototype.hifi.dnick.model.enumerations.Role;
import prototype.hifi.dnick.model.exceptions.InvalidArgumentsException;
import prototype.hifi.dnick.model.exceptions.InvalidUserCredentialsException;
import prototype.hifi.dnick.repository.UserRepository;
import prototype.hifi.dnick.service.AuthService;
import prototype.hifi.dnick.service.impl.AuthServiceImpl;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthServiceMutationTesting {

    @Mock
    private UserRepository userRepository;

    private AuthService authService;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
        User test = new User("test", "test", "name", "surname", Role.ROLE_USER);
        Mockito.when(this.userRepository.findByUsernameAndPassword("test","test")).thenReturn(Optional.of(test));
        authService = Mockito.spy(new AuthServiceImpl(this.userRepository));
    }

    @Test
    public void testLoginSuccessful()
    {
        User user=authService.login("test","test");
        Assertions.assertEquals("test",user.getUsername());
        Assertions.assertEquals("test",user.getPassword());
        Assertions.assertEquals("name",user.getName());
        Assertions.assertEquals("surname",user.getSurname());
        Assertions.assertEquals(Role.ROLE_USER,user.getRole());
    }
    @Test
    public void testLoginFailed(){

        Assertions.assertThrows(InvalidArgumentsException.class,() -> authService.login(null,"test"));
        Assertions.assertThrows(InvalidArgumentsException.class,() -> authService.login("","test"));
        Assertions.assertThrows(InvalidArgumentsException.class,() -> authService.login("test",null));
        Assertions.assertThrows(InvalidArgumentsException.class,() -> authService.login("test",""));
        Assertions.assertThrows(InvalidUserCredentialsException.class,()->authService.login("invalid","combination"));
    }

}
