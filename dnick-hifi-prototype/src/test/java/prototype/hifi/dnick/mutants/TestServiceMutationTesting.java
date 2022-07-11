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
import prototype.hifi.dnick.model.*;
import prototype.hifi.dnick.repository.QuestionRepository;
import prototype.hifi.dnick.service.BadgeService;
import prototype.hifi.dnick.service.TestResultService;
import prototype.hifi.dnick.service.TestService;
import prototype.hifi.dnick.service.impl.TestServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestServiceMutationTesting {
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private TestResultService testResultService;
    @Mock
    private BadgeService badgeService;

    private TestService testService;
    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
        List<Question> questionList=new ArrayList<>();
        List<UserAnswer> userAnswers=new ArrayList<>();
        for(int i=0;i<50;i++){
            questionList.add(new Question((long)i,"text",new ArrayList<String>(),'a'));
        }

        Mockito.when(this.questionRepository.findAll()).thenReturn(questionList);
        Mockito.when(this.questionRepository.findById(Mockito.anyLong())).thenAnswer(i->Optional.of(new Question((long)i.getArguments()[0],"text",new ArrayList<>(),'a')));
        Mockito.when(this.testResultService.saveNewResult(Mockito.any(User.class),Mockito.anyInt())).thenAnswer(i -> new TestResult((User)i.getArguments()[0],(int)i.getArguments()[1]));
        testService = Mockito.spy(new TestServiceImpl(this.questionRepository,this.testResultService,this.badgeService));
    }
    @Test
    public void testGenerateQuestions(){
        Assertions.assertEquals(8,testService.generateQuestions().size());
    }
    @Test
    public void testCalculateResultsForUser(){
        List<UserAnswer> userAnswers=new ArrayList<>();
        List<Question> questions=testService.generateQuestions();
        for(Question q : questions){
            userAnswers.add(new UserAnswer(q.getId(),q.getCorrectAnswer()));
        }
        TestResult tr= testService.calculateResultsForUser(new User(), userAnswers);
        Assertions.assertEquals(100,tr.getPoints());
    }
}
