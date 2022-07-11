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
import prototype.hifi.dnick.model.Topic;
import prototype.hifi.dnick.model.TopicUser;
import prototype.hifi.dnick.model.User;
import prototype.hifi.dnick.repository.TopicRepository;
import prototype.hifi.dnick.repository.TopicUserRepository;
import prototype.hifi.dnick.service.TopicService;
import prototype.hifi.dnick.service.impl.TopicServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TopicServiceMutationTesting {

    @Mock
    private TopicRepository topicRepository;
    @Mock
    private TopicUserRepository topicUserRepository;

    private TopicService topicService;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
        Topic testTopic=new Topic("test","test","test","test","test");
        List<Topic> allTopics=new ArrayList<>();
        allTopics.add(testTopic);
        List<TopicUser> topicUsers=new ArrayList<>();
        topicUsers.add(new TopicUser());

        Mockito.when(this.topicRepository.save(Mockito.any(Topic.class))).thenAnswer(i -> i.getArguments()[0]);
        Mockito.when(this.topicRepository.findById("test")).thenReturn(Optional.of(testTopic));
        Mockito.when(this.topicRepository.findAll()).thenReturn(allTopics);

        Mockito.when(this.topicUserRepository.findAllByUser(Mockito.any(User.class))).thenReturn(topicUsers);
        Mockito.when(this.topicUserRepository.findByTopicAndUser(Mockito.any(Topic.class),Mockito.any(User.class))).thenReturn(new TopicUser());

        topicService = Mockito.spy(new TopicServiceImpl(this.topicRepository,this.topicUserRepository));
    }
    @Test
    public void testFindAll(){
        Assertions.assertEquals(1,topicService.findAll().size());
    }
    @Test
    public void testGetById(){
        Topic t=topicService.getById("test");
        Assertions.assertNotNull(t);
    }
    @Test
    public void testSaveTopic(){
        Topic t=topicService.saveTopic("newTest","test","test","test","test");
        Assertions.assertNotNull(t);
    }
    @Test
    public void testSetAllTopicsAsNotCompletedForUser(){
        Assertions.assertTrue(topicService.setAllTopicsAsNotCompletedForUser(new User()));
    }
    @Test
    public void testFindTopicForUser(){
        Assertions.assertNotNull(topicService.findTopicForUser("test",new User()));
    }
    @Test
    public void testFindAllForUser(){
        Assertions.assertEquals(1,topicService.findAllForUser(new User()).size());
    }
    @Test
    public void testMarkAsCompleted(){
        topicService.markAsCompleted("test",new User());
    }
    @Test
    public void testMarkAsNotCompleted(){
        topicService.markAsNotCompleted("test",new User());
    }

}
