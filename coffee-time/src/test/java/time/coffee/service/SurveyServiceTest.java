package time.coffee.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import time.coffee.Application;
import time.coffee.domain.Survey;
import time.coffee.test.SurveyTestUtil;

import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
@Rollback
public class SurveyServiceTest {

    @Autowired
    private SurveyService surveyService;

    @Test
    public void testFindAll() {
        //Given
        setUpData();

        //When
        List<Survey> surveys = surveyService.findAll();

        //Then
        assertFalse(surveys.isEmpty());
        assertEquals(surveys.size(), 2);
    }

    @Test
    public void testFindById() {
        //Given
        List<Survey> surveys = setUpData();
        //When
        Survey survey = surveyService.findOne(surveys.get(0).getId());
        //Then
        assertNotNull(survey);
    }

    @Test
    public void testUpdate() {
        //Given
        String testDescription = "주례 커피2222222.";
        List<Survey> surveys = setUpData();

        Survey survey = surveys.get(0);
        survey.setShop(null);
        survey.setDeadline(new Date());
        survey.setDefaultMenu(null);
        survey.setDescription(testDescription);

        //When
        Survey result = surveyService.update(survey);

        //Then
        assertEquals(result.getDescription(), testDescription);
    }

    @Test
    public void testDelete() {
        //Given
        List<Survey> surveys = setUpData();
        Long testId = surveys.get(0).getId();
        //When
        surveyService.delete(testId);
        Survey survey = surveyService.findOne(testId);
        //Then
        assertNull(survey);
    }

    private List<Survey> setUpData() {
        List<Survey> surveys = SurveyTestUtil.getDummySurveyList();
        for(Survey survey : surveys) {
            surveyService.add(survey);
        }
        return surveys;
    }


}