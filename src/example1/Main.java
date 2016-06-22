package example1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;


/**
 * Return the name of the dishes which are less than 400 calories and are sorted by number of calories
 */


public class Main {

    public static void main(String []args){
        Menu menu = new Menu();
        List<String> names =java8Style(menu.getMenu());
        for(String name : names){
            System.out.println(name);
        }
    }

    public static List<String> java7Sytle(Menu menu){

       //Step 1: get the list of dishes which are less than 400 calories
        List<Dish> filteredList = new ArrayList<Dish>();
        for(Dish dish: menu.getMenu()){
            if(dish.getCalories()<400){
                filteredList.add(dish);
            }
        }

        //Step 2: sort the filtered list
        Collections.sort(filteredList, new Comparator<Dish>() {
            public int compare(Dish o1, Dish o2) {
                return (o1.getCalories() < o2.getCalories()) ? -1 : ((o1.getCalories() == o2.getCalories()) ? 0 : 1);
            }
        });

        //Step 3: now access the name of the dish from the shorted list
        List<String> names = new ArrayList<String>();
        for(Dish dish: filteredList){
            names.add(dish.getName());
        }
        return names;


    }

    public static List<String> java8Style(List<Dish> menu){
        List<String> names = menu.stream()
                .filter(d ->d.getCalories()<400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());
        return names;
    }
}
