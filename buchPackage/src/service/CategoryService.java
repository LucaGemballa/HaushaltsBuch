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


    /*
    initialise range of all dates for correct order
     */
    public void initCategorySeries(CategorySeries catS){
        Collections.sort(Main.rootService.transactionList.getAllTransactions());

        /*
        initialise chart for all dates
         */
        String firstCategory = Main.categoryList.getFirst();
        catS.seriesList.add(new XYChart.Series<String,Number>());
        catS.seriesList.getLast().setName(firstCategory);

        LocalDate x = Main.rootService.transactionList.list.getFirst().getTransactionDate();

        LocalDate y = Main.rootService.transactionList.list.getLast().getTransactionDate();

        while ( ! (x.getMonth().equals(y.getMonth()) && x.getYear() == y.getYear())){
            catS.seriesList.getLast().getData().add(new XYChart.Data<String, Number>(x.getMonth().toString() + " " + x.getYear(),0));

            x = x.plusMonths(1);
        }
    }

    public void initEarningChart(CategorySeries catS){

        initCategorySeries(catS);
        /*
        add all transactions to chart
         */
        for (String a : Main.categoryList) {
            if(! a.equals(Main.categoryList.getFirst())) {
                catS.seriesList.add(new XYChart.Series<String, Number>());
                catS.seriesList.getLast().setName(a);
            }

            for (Transaction t: Main.rootService.transactionList.list) {
                if(t.getTransactionCategory().equals(a)){
                    if(t.getTransactionSum() > 0) {
                        catS.seriesList.getLast().getData().add(new XYChart.Data<String, Number>
                                (t.getTransactionDate().getMonth().toString() + " " + t.getTransactionDate().getYear(), t.getTransactionSum()));
                    }
                }
            }
        }
    }

    public void initSpendingChart(CategorySeries catS){
        initCategorySeries(catS);

        /*
        add all transactions to chart
         */

        for (String a : Main.categoryList) {
            if(! a.equals(Main.categoryList.getFirst())) {
                catS.seriesList.add(new XYChart.Series<String, Number>());
                catS.seriesList.getLast().setName(a);
            }

            for (Transaction t: Main.rootService.transactionList.list) {
                if(t.getTransactionCategory().equals(a)){
                    if(t.getTransactionSum() < 0) {
                        catS.seriesList.getLast().getData().add(new XYChart.Data<String, Number>
                                (t.getTransactionDate().getMonth().toString() + " " + t.getTransactionDate().getYear(), -t.getTransactionSum()));
                    }
                }
            }
        }
    }

    public String[] generateCategoryArray(){
        String[] categories = new String[Main.categoryList.size()];
        Main.categoryList.toArray(categories);

        for (int i=0;i< categories.length; i++){
            System.out.println(categories[i]);
        }

        return categories;
    }

    public String categoryListToText(){

        String returnList = "";

        for (String s: Main.categoryList) {
            returnList += s + "\n";
        }

        return  returnList;
    }

}
