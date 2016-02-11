package time.coffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import time.coffee.domain.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

}
