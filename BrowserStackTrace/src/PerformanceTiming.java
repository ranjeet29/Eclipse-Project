import java.util.Date;
import java.util.Map;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarPageTimings;

import org.openqa.selenium.JavascriptExecutor;


public class PerformanceTiming {

	private long connectEnd;
    private long connectStart;
    private long domComplete;
    private long domContentLoadedEventEnd;
    private long domContentLoadedEventStart;
    private long domInteractive;
    private long domLoading;
    private long domainLookupEnd;
    private long domainLookupStart;
    private long fetchStart;
    private long loadEventEnd;
    private long loadEventStart;
    private long navigationStart;
    private long redirectEnd;
    private long redirectStart;
    private long requestStart;
    private long responseEnd;
    private long responseStart;
    private long secureConnectionStart;
    private long unloadEventEnd;
    private long unloadEventStart;
    private long onLoadTime;
    private long onContentLoadTime;

    public PerformanceTiming(JavascriptExecutor driver) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        @SuppressWarnings("unchecked")
     
        Long navigationStart=(Long)js.executeScript("return window.performance.timing.navigationStart");
        Long domContentLoadedEventEnd =(Long)js.executeScript("return window.performance.timing.domContentLoadedEventEnd");
        Long loadEventEnd = (Long)js.executeScript("return window.performance.timing.loadEventEnd");
 
        
        String href = (String) driver
                .executeScript("return window.location.href;");
        if (href.contains("#")) {
            href = href.substring(0, href.indexOf("#"));
        }
 
;
            HarPageTimings pageTimings = new HarPageTimings();
            if((domContentLoadedEventEnd - navigationStart)>0){
                long time = domContentLoadedEventEnd - navigationStart;
                pageTimings.setOnContentLoad(time);
                this.onContentLoadTime = time;
            }
            
            else{
                System.out.println("domContentLoadEventEnd: "+domContentLoadedEventEnd);
                System.out.println("navigationStart: "+navigationStart);
                pageTimings.setOnContentLoad(0L);
                this.onContentLoadTime = 0L;
            }
            if((loadEventEnd - navigationStart)>0)
            {
                long time = loadEventEnd - navigationStart;
                pageTimings.setOnLoad(time);
                this.onLoadTime = time;
            }
            else{
                System.out.println("LoadEventEnd: "+loadEventEnd);
                System.out.println("navigationStart: "+navigationStart);

                pageTimings.setOnLoad(0L);
                this.onLoadTime = 0L;
            }
         
        }
    

  
 
  

    
    public long getOnLoadTime(){
        return onLoadTime;
    }

    public long getOnContentLoadTime(){
        return onContentLoadTime;
    }


}
