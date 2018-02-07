package reports;

import police.PoliceStation;
import view.CityView;

public class MurderReport extends Report implements Runnable {
    
    private static final int NUMBER_OF_COP_CARS = 3;
    
    private static final String TAG = "MURDER!!";
    
    public MurderReport(CityView displayedCity)
    {
        super(displayedCity);
    }
    
    @Override
    public void run() {
        for(int i=0; i<NUMBER_OF_COP_CARS; i++){
            PoliceStation.murderReportAcquire();
            displayedCity.runCopCar(i, TAG);
        }
    }
}
