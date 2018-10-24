package seleniumIEWithHar;

import java.util.Date;
import java.util.Map;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarPage;
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

	    public PerformanceTiming(JavascriptExecutor driver, Har har) {
	        JavascriptExecutor js = (JavascriptExecutor)driver;
	        @SuppressWarnings("unchecked")
	        Long connectStart=(Long)js.executeScript("return window.performance.timing.connectStart");
	        Long navigationStart=(Long)js.executeScript("return window.performance.timing.navigationStart");
	        Long domContentLoadedEventEnd =(Long)js.executeScript("return window.performance.timing.domContentLoadedEventEnd");
	        Long loadEventEnd = (Long)js.executeScript("return window.performance.timing.loadEventEnd");
	        Long loadEventStart = (Long)js.executeScript("return window.performance.timing.loadEventStart");
	    
	        String href = (String) driver
	                .executeScript("return window.location.href;");
	        if (href.contains("#")) {
	            href = href.substring(0, href.indexOf("#"));
	        }
	        HarPage page = null;
	        for (HarEntry entry : har.getLog().getEntries()) {
	            if (href.equals(entry.getRequest().getUrl())) {
	                for (HarPage harPage : har.getLog().getPages()) {
	                    if (harPage.getId().equals(entry.getPageref())) {
	                        page = harPage;
	                        break;
	                    }
	                }
	            }
	            if (page != null) {
	                break;
	            }    
	        }
	        
	        if (page != null) {
	            page.setTitle((String) driver
	                    .executeScript("return window.document.title;"));
	            page.setStartedDateTime(new Date(navigationStart));
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
	                this.onLoadTime = time;
	                 System.out.println("LoadEventEnd: "+loadEventEnd);
	                 System.out.println("navigationStart: "+navigationStart);
	            }
	            else{
	                System.out.println("LoadEventEnd: "+loadEventEnd);
	                System.out.println("navigationStart: "+navigationStart);

	                pageTimings.setOnLoad(0L);
	               
	                this.onLoadTime = 0L;
	            }
            
                page.setPageTimings(pageTimings);
	        }   
	    
	    }
	    
	    public long getOnLoadTime(){
	        return onLoadTime;
	    }
	    
	    public long getOnContentLoadTime(){
	        return onContentLoadTime;
	    }



}
