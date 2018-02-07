package view;

import java.awt.*;
import javax.swing.*;

public class ApplicationWindow extends JFrame {

    private CityView displayedCity = new CityView();
    
    public ApplicationWindow() {
        Container applicationComponents = getContentPane();
        applicationComponents.setLayout(new BorderLayout());
        addComponentsTo(applicationComponents);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void addComponentsTo(Container applicationComponents)
    {
        applicationComponents.add("North", new RefuelButtonsPanel(displayedCity));
        applicationComponents.add("Center", displayedCity);
        applicationComponents.add("South", new ReportButtonsPanel(displayedCity));
    }
    
}