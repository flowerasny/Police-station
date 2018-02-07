package view;

import police.CopCar;
import reports.AccidentReport;
import reports.BreakingReport;
import reports.MurderReport;
import reports.TheftReport;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Semaphore;

public class ReportButtonsPanel extends JPanel
{
    private final static int NUMBER_OF_COP_CARS_FOR_MURDER = 3;
 
    private static final Semaphore s = new Semaphore(1, true);
    
    private JButton btnMurder = new JButton("Murder");
    private JButton btnTheft = new JButton("Theft");
    private JButton btnAccident = new JButton("Accident");
    private JButton btnBreaking = new JButton("Breaking");
    
    public ReportButtonsPanel(CityView displayedCity)
    {
        setLayout(new FlowLayout());
        addReportButtons();
        setButtonListeners(displayedCity);
    }
    
    private void addReportButtons()
    {
        add(btnMurder);
        add(btnTheft);
        add(btnAccident);
        add(btnBreaking);
    }
    
    private void setButtonListeners(CityView displayedCity)
    {
        btnMurder.addActionListener(e -> {
            
            
            new Thread(new MurderReport(displayedCity)).start();
            
            
            synchronized(CopCar.comeBackMurder)
            {
                CopCar.comeBackMurder.notifyAll();
            }
        });
        btnTheft.addActionListener(e -> new Thread(new TheftReport(displayedCity)).start());
        btnBreaking.addActionListener(e -> new Thread(new BreakingReport(displayedCity)).start());
        btnAccident.addActionListener(e -> new Thread(new AccidentReport(displayedCity)).start());
    }
}
