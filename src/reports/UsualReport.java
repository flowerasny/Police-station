package reports;

import police.CopCar;
import police.PoliceStation;
import view.CityView;

public class UsualReport extends Report
{
    public static final Object mutex = new Object();
    
    public UsualReport(CityView displayedCity)
    {
        super(displayedCity);
    }
    
    protected void borrowPermitAndAcquire()
    {
        boolean isWaitingForPermit = true;
        do
        {
            if(!PoliceStation.hasMurderReportSemaphorQueue() && PoliceStation.murderReportAvailablePermits() > 0)
            {
                PoliceStation.borrowMurderReportPermit();
                PoliceStation.usualReportAcquire();
                isWaitingForPermit = false;
            }
            else
            {
                try
                {
                    synchronized(mutex)
                    {
                        mutex.wait();
                    }
                }
                catch(InterruptedException e)
                {
                }
            }
        }
        while(isWaitingForPermit);
    }
}
