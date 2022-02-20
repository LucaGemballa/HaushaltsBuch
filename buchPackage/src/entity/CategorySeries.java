package entity;

import javafx.scene.chart.XYChart;
import view.Main;

import java.util.LinkedList;

public class CategorySeries {
    public LinkedList<XYChart.Series<String,Number>> seriesList;

    public CategorySeries (){
        seriesList = new LinkedList<XYChart.Series<String,Number>>();
    }
}
