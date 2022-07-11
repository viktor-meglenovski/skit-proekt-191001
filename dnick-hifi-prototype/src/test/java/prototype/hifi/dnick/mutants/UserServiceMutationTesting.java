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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import prototype.hifi.dnick.model.User;
import prototype.hifi.dnick.model.enumerations.Role;
import prototype.hifi.dnick.model.exceptions.*;
import prototype.hifi.dnick.repository.UserRepository;
import prototype.hifi.dnick.service.TestResultService;
import prototype.hifi.dnick.service.TopicService;
import prototype.hifi.dnick.service.UserService;
import prototype.hifi.dnick.service.impl.UserServiceImpl;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceMutationTesting {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private TopicService topicService;
    @Mock
    private TestResultService testResultService;

    private UserService userService;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
        User test = new User("test", "test", "name", "surname", Role.ROLE_USER);
        Mockito.when(this.userRepository.save(Mockito.any(User.class))).thenAnswer(i -> i.getArguments()[0]);
        Mockito.when(this.passwordEncoder.encode(Mockito.anyString())).thenReturn("password");
        Mockito.when(this.userRepository.findByUsername("test")).thenReturn(Optional.of(test));

        Mockito.when(this.topicService.setAllTopicsAsNotCompletedForUser(Mockito.any(User.class))).thenReturn(true);
        Mockito.when(this.testResultService.initResultsForUser(Mockito.any(User.class))).thenReturn(3);

        userService = Mockito.spy(new UserServiceImpl(this.userRepository, this.passwordEncoder, this.topicService, this.testResultService));
    }

    @Test
    public void testLoadUserByUsername(){
        UserDetails ud=userService.loadUserByUsername("test");
        Assertions.assertEquals(ud.getUsername(),"test");
        Assertions.assertThrows(UsernameNotFoundException.class,()->userService.loadUserByUsername("invalid"));
    }
    @Test
    public void testFindByUsername(){
        User u=userService.findByUsername("test");
        Assertions.assertEquals(u.getUsername(),"test");
        Assertions.assertThrows(UserDoesNotExistException.class,()->userService.findByUsername("invalid"));
    }

    @Test
    public void testRegistrationSuccessful(){
        User result=userService.register("newUser","test","test","name","surname",Role.ROLE_USER);
        Assertions.assertEquals("newUser",result.getUsername());
    }
    @Test
    public void testInvalidUsernameOrPasswordException(){
        Assertions.assertThrows(InvalidUsernameOrPasswordException.class,()->userService.register(null,"test","test","name","surname",Role.ROLE_USER));
        Assertions.assertThrows(InvalidUsernameOrPasswordException.class,()->userService.register("","test","test","name","surname",Role.ROLE_USER));
        Assertions.assertThrows(InvalidUsernameOrPasswordException.class,()->userService.register("test",null,"test","name","surname",Role.ROLE_USER));
        Assertions.assertThrows(InvalidUsernameOrPasswordException.class,()->userService.register("test","","test","name","surname",Role.ROLE_USER));
    }
    @Test
    public void testPasswordsDoNotMatchException(){
        Assertions.assertThrows(PasswordsDoNotMatchException.class,()->userService.register("test","different","password","name","surname",Role.ROLE_USER));
    }
    @Test
    public void testUsernameAlreadyExistsException(){
        Assertions.assertThrows(UsernameAlreadyExistsException.class,()->userService.register("test","test","test","name","surname",Role.ROLE_USER));

    }

}
