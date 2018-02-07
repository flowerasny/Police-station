package reports;

import view.CityView;

public class BreakingReport extends UsualReport implements Runnable {
    
    private static final String TAG = "Breaking";
    
    public BreakingReport(CityView displayedCity)
    {
        super(displayedCity);
    }
    
    @Override
    public void run() {
        borrowPermitAndAcquire();
        displayedCity.runCopCar(chooseFreeCopCar(), TAG);
    }
}
