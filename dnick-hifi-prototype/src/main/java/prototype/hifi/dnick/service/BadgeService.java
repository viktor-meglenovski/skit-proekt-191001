package prototype.hifi.dnick.service;

import prototype.hifi.dnick.model.Badge;
import prototype.hifi.dnick.model.TestResult;
import prototype.hifi.dnick.model.User;

import java.util.List;

public interface BadgeService {
    Badge getScore100();
    Badge getScoreOver90();
    Badge getFiveTests();
    Badge getThreeTests();
    Badge getOneTest();

    boolean checkForScore100(User user,int points);
    boolean checkForScoreOver90(User user, int points);
    boolean checkForFiveTests(User user);
    boolean checkForThreeTests(User user);
    boolean checkForOneTest(User user);

    boolean checkForAll(TestResult testResult);

    List<Badge> getAllBadges();

}
