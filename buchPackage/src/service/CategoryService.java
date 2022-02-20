package service;

import entity.CategorySeries;
import javafx.scene.chart.XYChart;
import view.Main;
import entity.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;

public class CategoryService {

    private RootService rootService;

    public CategoryService ( RootService rS){
        rootService = rS;
    }

    public void initCategorySeries(CategorySeries catS){
        System.out.println("begin initialisation");

        Collections.sort(Main.rootService.transactionList.list);

        for (Transaction t: Main.rootService.transactionList.list) {
            System.out.println(t.toText());
        }


        /*
        initialise chart for all dates
         */
        String firstCategory = Main.cathegoryList.getFirst();
        catS.seriesList.add(new XYChart.Series<String,Number>());
        catS.seriesList.getLast().setName(firstCategory);

        LocalDate x = Main.rootService.transactionList.list.getFirst().getTransactionDate();

        LocalDate y = Main.rootService.transactionList.list.getLast().getTransactionDate();

        while ( ! (x.getMonth().equals(y.getMonth()) && x.getYear() == y.getYear())){
            catS.seriesList.getLast().getData().add(new XYChart.Data<String, Number>(x.getMonth().toString() + " " + x.getYear(),0));
            System.out.println(x.toString());

            x = x.plusMonths(1);
        }


        /*
        add all transactions to chart
         */
        for (String a : Main.cathegoryList) {
            if(! a.equals(Main.cathegoryList.getFirst())) {
                System.out.println(a);
                catS.seriesList.add(new XYChart.Series<String, Number>());
                catS.seriesList.getLast().setName(a);
            }

            for (Transaction t: Main.rootService.transactionList.list) {
                if(t.getTransactionCathegory().equals(a)){
                    catS.seriesList.getLast().getData().add(new XYChart.Data<String, Number>(t.getTransactionDate().getMonth().toString() + " " + t.getTransactionDate().getYear(),t.getTransactionSum()));
                    System.out.println(t.toText());

                }
            }
        }
        System.out.println("end initialisation");

        System.out.println(catS.seriesList.size());
    }

    public String[] generateCategoryArray(){
        String[] categories = new String[Main.cathegoryList.size()];
        Main.cathegoryList.toArray(categories);

        for (int i=0;i< categories.length; i++){
            System.out.println(categories[i]);
        }

        return categories;
    }
}
