package reports;

import police.CopCar;
import view.CityView;

public class Report {
    
    protected CityView displayedCity;

    public Report(CityView display) {
        this.displayedCity = display;
    }
    
    public Report()
    {
    }
    
    protected int chooseFreeCopCar(){
        if (CopCar.copCarsAvailable[0].availablePermits() == 1){
            return 0;
        }else if (CopCar.copCarsAvailable[1].availablePermits() == 1){
            return 1;
        }else return 2;
    }
}
