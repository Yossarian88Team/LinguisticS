/*
 * Klasa oferująca metody tworzenia histogramow oraz wykresu punktowego
 */

package data;

import java.io.*;
import org.jfree.chart.*;
import org.jfree.data.statistics.*;
import org.jfree.data.xy.*;
import org.jfree.chart.plot.PlotOrientation;

/**
 * 
 * @author Ariel
 */


 public class Histogram {
     
        public Histogram()
        {
            
        }
        
	public  Histogram(String name, double[] values, int numberOfRanges){
		 
		 HistogramDataset dataset = new HistogramDataset();
		 dataset.setType(HistogramType.FREQUENCY);
		 dataset.addSeries(name,values,numberOfRanges);
		 
		 createHistogram(name,dataset);
        }
        
        /**
         * Metoda tworząca wykres punktowy na podstawie danych z tablicy x i y, wykresy są zapisywane w folderze
         * histograms aplikacji pod podaną nazwą
         * @param name
         * @param x
         * @param y 
         */
        
       public void Plot(String name, double[] x, double[] y){
            XYSeries series = new XYSeries("");
            for(int i=0;i<x.length;i++)
            {
               series.add(x[i],y[i]);
            }

            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.setAutoWidth(true);
            dataset.addSeries(series);         
            //         Generate the graph
            JFreeChart chart = ChartFactory.createXYLineChart("", // Title
            "Przestrzeń", // x-axis Label
            "Wartość przynależności", // y-axis Label
            dataset, // Dataset
            PlotOrientation.VERTICAL, // Plot Orientation
            true, // Show Legend
            true, // Use tooltips)
            false );// Configure chart to generate URLs?
            try {
                     ChartUtilities.saveChartAsPNG(new File("histograms/"+name+".PNG"), chart, 700, 400);
            } catch (IOException e) {}
       }
	 
	Histogram(String name, double[] values, int numberOfRanges, double min, double max){
		 
		 HistogramDataset dataset = new HistogramDataset();
		 dataset.setType(HistogramType.FREQUENCY);
		 dataset.addSeries(name,values,numberOfRanges,min,max);
		 
		 createHistogram(name,dataset);
	}
        
        /**
         * Metoda tworząca histogram na podstawie danych podanych w konstruktorze klasy.
         * Histogramy są zapisywane do folderu histograms w głownym folderze projektu
         * @param name
         * @param dataset 
         */
	
	private void createHistogram(String name, HistogramDataset dataset){
		 String plotTitle = name; 
		 String xaxis = null;	//"number";
		 String yaxis = null; 	//"value"; 
		 PlotOrientation orientation = PlotOrientation.VERTICAL; 
		 boolean legend = false; 
		 boolean toolTips = false;
		 boolean urls = false; 
		 
		 JFreeChart chart = ChartFactory.createHistogram( plotTitle, xaxis, yaxis, 
           dataset, orientation, legend, toolTips, urls);
		 chart.getXYPlot().setForegroundAlpha(0.75f);
		 
		 int width = 500;
		 int height = 300; 
		 
		 try {
			 ChartUtilities.saveChartAsPNG(new File("histograms/"+name+".PNG"), chart, width, height);
		 } catch (IOException e) {}
	}
        

 }
