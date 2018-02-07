package reports;

import view.CityView;

public class TheftReport extends UsualReport implements Runnable {
    
    private static final String TAG = "Theft";
    
    public TheftReport(CityView displayedCity)
    {
        super(displayedCity);
    }
    
    @Override
    public void run() {
        borrowPermitAndAcquire();
        displayedCity.runCopCar(chooseFreeCopCar(), TAG);
    }
}
