package plalab.jpa.study02.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import plalab.jpa.study02.Study02Application;
import plalab.jpa.study02.domain.Food;
import plalab.jpa.study02.domain.FoodSize;
import plalab.jpa.study02.domain.Pizza;

import java.util.Date;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Study02Application.class)
public class FoodRepositoryTest {

    @Autowired
    FoodRepository foodRepository;

    @Test
    public void Food_엔티티_생성테스트() {
        //Given
////        Food food = new Food();
////        food.setName("피자");
////        food.setExpiryDate(new Date()); //만들자 마자 만료
////
////        //When
////        Food savedFood = foodRepository.save(food);
////        Food findFood = foodRepository.findOne(savedFood.getId());
//
//        //Then
//        assertNotNull(findFood);
    }

    @Test
    public void Food_Pizza_서브엔티티_생성_및_조회테스트() {
        //Given


        Pizza pizza = new Pizza();
        pizza.setName("임근대피자");
        pizza.setSize(FoodSize.LARGE);
        pizza.setCalorie(100000);
        pizza.setExpiryDate(new Date()); //만들자 마자 만료

        //When
        Food savedFood = foodRepository.save(pizza);
        Food findFood = foodRepository.findOne(savedFood.getId());

        //Then
        assertNotNull(findFood);
    }
}
