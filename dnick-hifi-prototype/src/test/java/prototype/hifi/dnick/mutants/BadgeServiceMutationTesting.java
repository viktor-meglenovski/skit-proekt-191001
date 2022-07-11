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
import prototype.hifi.dnick.model.Badge;
import prototype.hifi.dnick.model.TestResult;
import prototype.hifi.dnick.model.User;
import prototype.hifi.dnick.model.enumerations.Badges;
import prototype.hifi.dnick.model.enumerations.Role;
import prototype.hifi.dnick.repository.BadgeRepository;
import prototype.hifi.dnick.repository.UserRepository;
import prototype.hifi.dnick.service.BadgeService;
import prototype.hifi.dnick.service.TestResultService;
import prototype.hifi.dnick.service.impl.BadgeServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BadgeServiceMutationTesting {

    @Mock
    private UserRepository userRepository;
    @Mock
    private BadgeRepository badgeRepository;
    @Mock
    private TestResultService testResultService;
    private BadgeService badgeService;
    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
        List<Badge> allBadges=new ArrayList<>();
        allBadges.add(new Badge());
        User test = new User("test", "test", "name", "surname", Role.ROLE_USER);
        Mockito.when(this.userRepository.findByUsernameAndPassword("test","test")).thenReturn(Optional.of(test));
        Mockito.when(this.userRepository.save(Mockito.any(User.class))).thenAnswer((i)->i.getArguments()[0]);
        Mockito.when(this.badgeRepository.findAll()).thenReturn(allBadges);
        Mockito.when(this.badgeRepository.findById(Mockito.any(Badges.class))).thenReturn(Optional.of(new Badge()));
        Mockito.when(this.testResultService.getNumberOfTestsForUser(Mockito.any(User.class))).thenAnswer(i-> Integer.valueOf(((User)i.getArguments()[0]).getUsername().substring(0,1)));
        badgeService = Mockito.spy(new BadgeServiceImpl(this.userRepository,this.badgeRepository,this.testResultService));
    }
    @Test
    public void testGetScore100(){
        Assertions.assertNotNull(badgeService.getScore100());
    }
    @Test
    public void testGetScore90(){
        Assertions.assertNotNull(badgeService.getScoreOver90());
    }
    @Test
    public void testGetFiveTests(){
        Assertions.assertNotNull(badgeService.getFiveTests());
    }
    @Test
    public void testGetOneTest(){
        Assertions.assertNotNull(badgeService.getOneTest());
    }
    @Test
    public void testGetThreeTests(){
        Assertions.assertNotNull(badgeService.getThreeTests());
    }
    @Test
    public void testGetAllBadges(){
        Assertions.assertEquals(1,badgeService.getAllBadges().size());
    }
    @Test
    public void testCheckForAll(){
        Assertions.assertTrue(badgeService.checkForAll(new TestResult()));
    }
    @Test
    public void testCheckForScore100(){
        List<Badge> badgesList=new ArrayList<>();
        User user=new User();
        user.setBadges(badgesList);
        Assertions.assertTrue(badgeService.checkForScore100(user,100));
        user.getBadges().add(new Badge());
        Assertions.assertFalse(badgeService.checkForScore100(user,99));
    }
    @Test
    public void testCheckForScoreOver90(){
        List<Badge> badgesList=new ArrayList<>();
        User user=new User();
        user.setBadges(badgesList);
        Assertions.assertTrue(badgeService.checkForScoreOver90(user,90));
        user.getBadges().add(new Badge());
        Assertions.assertFalse(badgeService.checkForScoreOver90(user,89));
    }
    @Test
    public void testCheckForOneTest(){
        List<Badge> badgesList=new ArrayList<>();
        User user=new User("0","test","name","surname",Role.ROLE_USER);
        user.setBadges(badgesList);
        Assertions.assertFalse(badgeService.checkForOneTest(user));
        user.setUsername("1");
        Assertions.assertTrue(badgeService.checkForOneTest(user));
    }
    @Test
    public void testCheckForThreeTests(){
        List<Badge> badgesList=new ArrayList<>();
        User user=new User("2","test","name","surname",Role.ROLE_USER);
        user.setBadges(badgesList);
        Assertions.assertFalse(badgeService.checkForThreeTests(user));
        user.setUsername("3");
        Assertions.assertTrue(badgeService.checkForThreeTests(user));
    }
    @Test
    public void testCheckForFiveTests(){
        List<Badge> badgesList=new ArrayList<>();
        User user=new User("4","test","name","surname",Role.ROLE_USER);
        user.setBadges(badgesList);
        Assertions.assertFalse(badgeService.checkForFiveTests(user));
        user.setUsername("5");
        Assertions.assertTrue(badgeService.checkForFiveTests(user));
    }

}
