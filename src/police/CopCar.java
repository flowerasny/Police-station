package police;

import reports.UsualReport;
import view.CityView;
import view.RefuelButtonsPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class CopCar implements Runnable
{
    public static final Object comeBackMurder = new Object();
    
    private static final String DEFAULT_STATUS_VALUE = "none";
    
    private static final int initHorizontalPosition[] = {0, 0, 0};
    private static final int initVerticalPosition[] = {45, 95, 145};
    private static final int totalViewWidth = 900;
    public static final Semaphore copCarsAvailable[] = {new Semaphore(1, true), new Semaphore(1, true), new Semaphore(1, true)};
    
    private String reportName;
    
    private int tank = 4;
    private int copCarNumber;
    private int horizontalPosition, verticalPosition;
    private BufferedImage imageForward;
    private BufferedImage imageForwardDisabled;
    private BufferedImage imageBack;
    private Image currentImage;
    
    public CopCar(int copCarNumber)
    {
        this.copCarNumber = copCarNumber;
        loadImages();
        
        currentImage = imageForward;
        
        horizontalPosition = initHorizontalPosition[copCarNumber];
        verticalPosition = initVerticalPosition[copCarNumber];
    }
    
    private void loadImages()
    {
        try
        {
            imageForward = ImageIO.read(new File("Image/copcarforward.gif"));
            imageForwardDisabled = ImageIO.read(new File("Image/copcarforwarddisabled.png"));
            imageBack = ImageIO.read(new File("Image/copcarback.gif"));
            
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void setReportName(String reportName)
    {
        this.reportName = reportName;
    }
    
    public void run() {
        try {
            copCarsAvailable[copCarNumber].acquire();
        }
        catch(InterruptedException e){
        }
        CityView.setReportNameAndFuelLevel(copCarNumber, reportName, String.valueOf(tank));
        moveForward();
        onDuty(3000);
        moveBackAndTakeForwardPosition();
        useFuel();
        CityView.setReportNameAndFuelLevel(copCarNumber, DEFAULT_STATUS_VALUE, String.valueOf(tank));
        endTask();
        
    }
    
    private void moveForward() {
        boolean isWaiting = false;
        while(!isWaiting) {
            horizontalPosition += 4;
            isWaiting = horizontalPosition > totalViewWidth;
            try
            {
                Thread.sleep(10);
            }
            catch(InterruptedException e)
            {
            }
        }
    }
    
    public void draw(Graphics g)
    {
        g.drawImage(currentImage, horizontalPosition, verticalPosition, null);
    }
    
    private void onDuty(int timeOnDuty) {
        try {
            synchronized(comeBackMurder){
                comeBackMurder.wait(timeOnDuty);
            }
        }
        catch(InterruptedException e) {
        }
    }
    
    private void moveBackAndTakeForwardPosition() {
        boolean backHome = false;
        takeBackPosition();
        while(!backHome) {
            horizontalPosition -= 4;
            backHome = horizontalPosition == 0;
            try {
                Thread.sleep(10);
            }
            catch(InterruptedException e) {
            }
        }
        takeForwardPosition();
    }
    
    private void takeForwardPosition()
    {
        currentImage = imageForward;
    }
    
    private void takeBackPosition()
    {
        currentImage = imageBack;
    }
    
    private void useFuel() {
        tank--;
    }
    
    public void refuel() {
        tank = 4;
        takeForwardPosition();
        CityView.setReportNameAndFuelLevel(copCarNumber, DEFAULT_STATUS_VALUE, String.valueOf(tank));
        endTask();
    }
    
    private void endTask() {
        if(tank > 0) {
            copCarsAvailable[copCarNumber].release();
            synchronized(UsualReport.mutex){
                PoliceStation.murderReportRelease();
                UsualReport.mutex.notifyAll();
            }
        }
        else {
            makeDisable();
            RefuelButtonsPanel.enableRefuelButton(copCarNumber);
        }
    }
    
    private void makeDisable() {
        currentImage = imageForwardDisabled;
    }
}