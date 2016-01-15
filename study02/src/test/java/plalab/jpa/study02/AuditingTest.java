package plalab.jpa.study02;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import plalab.jpa.study02.domain.Item;
import plalab.jpa.study02.domain.Movie;
import plalab.jpa.study02.repository.MovieRepository;
import plalab.jpa.study02.service.ItemService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Study02Application.class)
@WebAppConfiguration
public class AuditingTest {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ItemService itemService;

    @Test
    @Transactional
    public void basicAuditing() throws InterruptedException {
        Movie movie = new Movie();
        movie.setName("Heemin");
        movie.setActor("HeeWon");
        movieRepository.save(movie);
        assert(movie.getCreatedDate() != null);
        System.out.println(movie.getCreatedDate());
        System.out.println(movie.getLastModifiedDate());
        Thread.sleep(3000);
        movie.setName("NoName");
        movieRepository.save(movie);
        movie = movieRepository.findOne(movie.getId());
        System.out.println("Second");
        System.out.println(movie.getCreatedDate());
        System.out.println(movie.getLastModifiedDate());
    }

    @Test
    public void auditingTest2() throws InterruptedException {
        Movie movie = itemService.createMovie("나는 영화다", "나는 감독이다", 12000);
        System.out.println(movie.getCreatedDate());
        assert movie.getCreatedDate() != null;
        Thread.sleep(5000);

        Item item = itemService.saleItem(movie.getId());
        System.out.println(item.getLastModifiedDate());
        assert movie.getCreatedDate().getTime() != item.getLastModifiedDate().getTime();
    }

}
