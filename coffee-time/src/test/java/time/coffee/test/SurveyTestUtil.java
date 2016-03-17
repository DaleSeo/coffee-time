package time.coffee.test;

import time.coffee.domain.Menu;
import time.coffee.domain.Shop;
import time.coffee.domain.Survey;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SurveyTestUtil {
    public static void main(String[] args) {
        System.out.println((new Date()).getTime());
    }

    public static List<Survey> getDummySurveyList() {
        Shop shop = new Shop();
        shop.setName("스벅.");

        Menu defaultMenu = new Menu();
        defaultMenu.setName("아메");
        defaultMenu.setDescription("커피");

        Survey survey = new Survey();
        survey.setShop(shop);
        survey.setDeadline(new Date());
        survey.setDefaultMenu(defaultMenu);
        survey.setDescription("주례 커피.");

        Survey survey2 = new Survey();
        survey2.setShop(shop);
        survey2.setDeadline(new Date());
        survey2.setDefaultMenu(defaultMenu);
        survey2.setDescription("주례 커피2.");

        List<Survey> surveys = new ArrayList<>();
        surveys.add(survey);
        surveys.add(survey2);
        return surveys;
    }
}
