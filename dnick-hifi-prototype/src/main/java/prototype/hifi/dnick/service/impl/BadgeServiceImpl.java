package prototype.hifi.dnick.service.impl;

import org.springframework.stereotype.Service;
import prototype.hifi.dnick.model.Badge;
import prototype.hifi.dnick.model.TestResult;
import prototype.hifi.dnick.model.User;
import prototype.hifi.dnick.model.enumerations.Badges;
import prototype.hifi.dnick.repository.BadgeRepository;
import prototype.hifi.dnick.repository.UserRepository;
import prototype.hifi.dnick.service.BadgeService;
import prototype.hifi.dnick.service.TestResultService;

import java.util.List;

@Service
public class BadgeServiceImpl implements BadgeService {
    private final UserRepository userRepository;
    private final BadgeRepository badgeRepository;
    private final TestResultService testResultService;

    public BadgeServiceImpl(UserRepository userRepository, BadgeRepository badgeRepository, TestResultService testResultService) {
        this.userRepository = userRepository;
        this.badgeRepository = badgeRepository;
        this.testResultService = testResultService;
    }

    @Override
    public Badge getScore100() {
        return badgeRepository.findById(Badges.SCORE_100).get();
    }

    @Override
    public Badge getScoreOver90() {
        return badgeRepository.findById(Badges.SCORE_OVER_90).get();
    }

    @Override
    public Badge getFiveTests() {
        return badgeRepository.findById(Badges.TESTS_5).get();
    }

    @Override
    public Badge getThreeTests() {
        return badgeRepository.findById(Badges.TESTS_3).get();
    }

    @Override
    public Badge getOneTest() {
        return badgeRepository.findById(Badges.TESTS_1).get();
    }

    @Override
    public boolean checkForScore100(User user, int points) {
        if(points==100 && !user.getBadges().contains(getScore100())){
            user.getBadges().add(getScore100());
            userRepository.save(user);
            return true;
        }
        return false;
    }
    @Override
    public boolean checkForScoreOver90(User user, int points) {
        if(points>=90 && !user.getBadges().contains(getScoreOver90())){
            user.getBadges().add(getScoreOver90());
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkForFiveTests(User user) {
        if(testResultService.getNumberOfTestsForUser(user)>=5 && !user.getBadges().contains(getFiveTests())){
            user.getBadges().add(getFiveTests());
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkForThreeTests(User user) {
        if(testResultService.getNumberOfTestsForUser(user)>=3 &&!user.getBadges().contains(getThreeTests())){
            user.getBadges().add(getThreeTests());
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkForOneTest(User user) {
        if(testResultService.getNumberOfTestsForUser(user)>=1 && !user.getBadges().contains(getOneTest())){
            user.getBadges().add(getOneTest());
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkForAll(TestResult testResult) {
        checkForFiveTests(testResult.getUser());
        checkForOneTest(testResult.getUser());
        checkForScore100(testResult.getUser(),testResult.getPoints());
        checkForScoreOver90(testResult.getUser(),testResult.getPoints());
        checkForThreeTests(testResult.getUser());
        return true;
    }

    @Override
    public List<Badge> getAllBadges() {
        return badgeRepository.findAll();
    }

}
