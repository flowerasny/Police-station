package view;

import police.CopCar;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class CityView extends JPanel
{
    private final static String DEFAULT_REPORT_VALUE = "none";
    private final static String DEFAULT_FUEL_LEVEL_VALUE = "4";
    
    private static JLabel[] copCarsFuel = new JLabel[3];
    private static JLabel[] copCarsReport = new JLabel[3];
    
    private BufferedImage city;
    
    private ArrayList<CopCar> copCars = new ArrayList<CopCar>();
    
    public CityView()
    {
        setLayout(null);
        loadCityImage();
        createCopCars(3);
        addCopCarsInfo(3);
        setPreferredSize(new Dimension(city.getWidth(null), city.getHeight(null)));
    }
    
    private void loadCityImage() {
        try {
            city = ImageIO.read(new File("Image/city.gif"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private void createCopCars(int numberOfCopCars)
    {
        for(int i=0; i<numberOfCopCars; i++){
            copCars.add(new CopCar(i));
        }
    }
    
    private void addCopCarsInfo(int numberOfCopCars)
    {
        addCopCarsReports(numberOfCopCars);
        addCopCarsFuelLevels(numberOfCopCars);
    }
    
    private void addCopCarsReports(int numberOfCopCars)
    {
        for(int i = 0; i < numberOfCopCars; i++) {
            copCarsReport[i] = new JLabel(DEFAULT_REPORT_VALUE);
            add(copCarsReport[i]);
            copCarsReport[i].setLocation(745, 217 + i*18);
            copCarsReport[i].setSize(new Dimension(100, 15));
        }
    }
    
    private void addCopCarsFuelLevels(int numberOfCopCars)
    {
        for(int i = 0; i < numberOfCopCars; i++) {
            copCarsFuel[i] = new JLabel(DEFAULT_FUEL_LEVEL_VALUE);
            add(copCarsFuel[i]);
            copCarsFuel[i].setSize(new Dimension(10, 15));
            copCarsFuel[i].setLocation(875, 217 + i*18);
        }
    }
    
    public void paintComponent(Graphics g)
    {
        g.drawImage(city, 0, 0, this);
        for(CopCar c : copCars)
        {
            c.draw(g);
        }
    }
    
    public void runCopCar(int copCarNumber, String name)
    {
        copCars.get(copCarNumber).setReportName(name);
        new Thread(copCars.get(copCarNumber)).start();
    }
    
    public void refuelCopcar(int copCarNumber)
    {
        copCars.get(copCarNumber).refuel();
    }
    
    public static void setReportNameAndFuelLevel(int copCarNumber, String report, String fuelLevel)
    {
        copCarsReport[copCarNumber].setText(report);
        copCarsFuel[copCarNumber].setText(fuelLevel);
    }
    
    public int getNumberOfCopCars()
    {
        return copCars.size();
    }
}