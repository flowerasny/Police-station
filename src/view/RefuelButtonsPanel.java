package view;

import javax.swing.*;
import java.awt.*;

public class RefuelButtonsPanel extends JPanel
{
    
    private static final JButton[] btnsRefuel = new JButton[3];
    
    RefuelButtonsPanel(CityView displayedCity)
    {
        setLayout(new FlowLayout());
        addButtonsToPanel(displayedCity);
        setButtonListeners(displayedCity);
    }
    
    private void addButtonsToPanel(CityView displayedCity)
    {
        for(int i = 0; i < displayedCity.getNumberOfCopCars(); i++)
        {
            btnsRefuel[i] = new JButton("Refuel copcar " + (i + 1));
            btnsRefuel[i].setEnabled(false);
            this.add(btnsRefuel[i]);
        }
    }
    
    private void setButtonListeners(CityView displayedCity)
    {
        btnsRefuel[0].addActionListener(e -> {
            displayedCity.refuelCopcar(0);
            btnsRefuel[0].setEnabled(false);
        });
        btnsRefuel[1].addActionListener(e -> {
            displayedCity.refuelCopcar(1);
            btnsRefuel[1].setEnabled(false);
        });
        btnsRefuel[2].addActionListener(e -> {
            displayedCity.refuelCopcar(2);
            btnsRefuel[2].setEnabled(false);
        });
        
    }
    
    public static void enableRefuelButton(int copCarNumber)
    {
        btnsRefuel[copCarNumber].setEnabled(true);
    }
    
}
