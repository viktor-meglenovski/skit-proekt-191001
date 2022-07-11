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
import prototype.hifi.dnick.model.TestResult;
import prototype.hifi.dnick.model.User;
import prototype.hifi.dnick.repository.TestResultRepository;
import prototype.hifi.dnick.service.TestResultService;
import prototype.hifi.dnick.service.impl.TestResultServiceImpl;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestResultServiceMutationTesting {
    @Mock
    private TestResultRepository testResultRepository;
    private TestResultService testResultService;
    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(this.testResultRepository.save(Mockito.any(TestResult.class))).thenAnswer(i->i.getArguments()[0]);
        Mockito.when(this.testResultRepository.findAllByUser(Mockito.any(User.class))).thenAnswer(i->{
            List<TestResult> testResults = new ArrayList<>();
            testResults.add(new TestResult((User)i.getArguments()[0],100));
            testResults.add(new TestResult((User)i.getArguments()[0],90));
            testResults.add(new TestResult((User)i.getArguments()[0],0));
            return testResults;
        });
        testResultService = Mockito.spy(new TestResultServiceImpl(this.testResultRepository));
    }
    @Test
    public void testSaveNewResult(){
        Assertions.assertNotNull(testResultService.saveNewResult(new User(),0));
    }
    @Test
    public void testInitResultsForUser(){
        Assertions.assertEquals(3,testResultService.initResultsForUser(new User()));
    }
    @Test
    public void testGetTopThreeResultsForUser(){
        List<TestResult> testResults=testResultService.getTopThreeResultsForUser(new User());
        Assertions.assertEquals(3,testResults.size());
    }
    @Test
    public void testGetNumberOfTestsForUser(){
        int numberOfTests=testResultService.getNumberOfTestsForUser(new User());
        Assertions.assertEquals(2,numberOfTests);
    }

}
