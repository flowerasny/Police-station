package reports;

import view.CityView;

public class AccidentReport extends UsualReport implements Runnable {
    
    private static final String TAG = "Accident";
    
    public AccidentReport(CityView displayedCity)
    {
        super(displayedCity);
    }
    
    @Override
    public void run() {
        borrowPermitAndAcquire();
        displayedCity.runCopCar(chooseFreeCopCar(), TAG);
    }
}
