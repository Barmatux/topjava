package ru.javawebinar.topjava.util;

import com.sun.istack.internal.NotNull;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime,
                                                                    LocalTime endTime, int caloriesPerDay) {


        Map <LocalDate,List<UserMeal>> userMealMap = new HashMap();
        List<UserMealWithExceed> userMealWithExceed = new ArrayList<>();
        List<UserMealWithExceed> userMealWithExceedFinal = new ArrayList<>();

        for(UserMeal userMeal:mealList){
            userMealMap.computeIfAbsent(userMeal.getDateTime().toLocalDate(),user->new ArrayList<>()).add(userMeal);
        }

        for(Map.Entry<LocalDate,List<UserMeal>> pair:userMealMap.entrySet()){
            int calories=0;
            for(UserMeal userMealCheck:pair.getValue()){
               calories +=  userMealCheck.getCalories();
            }
            if(calories>caloriesPerDay){
                for(UserMeal userMealCheck:pair.getValue()){
                    userMealWithExceed.add(new UserMealWithExceed(userMealCheck.getDateTime(),userMealCheck.getDescription(),
                            userMealCheck.getCalories(),true));
                }
            }
        }
        for(UserMealWithExceed userMealWithExceedByTime: userMealWithExceed) {
            if (TimeUtil.isBetween(userMealWithExceedByTime.getDateTime().toLocalTime(), startTime, endTime)) {
                userMealWithExceedFinal.add(userMealWithExceedByTime);
            }
        }
        return userMealWithExceedFinal;
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded2(List<UserMeal> mealList, LocalTime startTime,
                                                                    LocalTime endTime, int caloriesPerDay) {
        mealList.stream().map


    return null}
    }

}
