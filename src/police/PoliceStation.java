package police;

import java.util.concurrent.Semaphore;

public class PoliceStation {
    private static final Semaphore usualReportEntries = new Semaphore(0,true);
    private static final Semaphore murderReportEntries = new Semaphore(3,true);
    
    public static int getMurderAvailablePermits(){
        return murderReportEntries.availablePermits();
    }
    
    
    public static void borrowMurderReportPermit()
    {
        murderReportAcquire();
        usualReportRelease();
    }
    
    public static void murderReportAcquire(){
        try {
            murderReportEntries.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void murderReportRelease(){
        murderReportEntries.release();
    }
    
    public static synchronized void usualReportAcquire(){
        try {
            usualReportEntries.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void usualReportRelease(){
        usualReportEntries.release();
    }
    
    public static boolean hasMurderReportSemaphorQueue(){
        if (murderReportEntries.hasQueuedThreads()){
            return true;
        }
        return false;
    }
    
    public static int murderReportAvailablePermits(){
        return murderReportEntries.availablePermits();
    }
}
