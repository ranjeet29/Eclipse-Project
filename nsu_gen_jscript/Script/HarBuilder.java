package com.cavisson.scripts.;

import pacJnvmApi.NSApi;
import java.util.Set;
import net.lightbody.bmp.proxy.CaptureType;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.BrowserMobProxy;
import org.openqa.selenium.Proxy;
import net.lightbody.bmp.client.ClientUtil;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.TreeSet;
import java.util.Date;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarLog;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;


public class HarBuilder {

    public static BrowserMobProxy  proxy;    
    public static String harPath;
    public static int sessId;
    public static String fileNameWithPath;
    public static Proxy startProxy(int port) {

        //start proxy
        proxy = new BrowserMobProxyServer();
		proxy.setTrustAllServers(true);
        proxy.start(port);
        
		//set the elements to be captured by proxy
        Set<CaptureType> captureTypes = new TreeSet<CaptureType>();
        captureTypes.add(CaptureType.REQUEST_HEADERS);
        captureTypes.add(CaptureType.RESPONSE_HEADERS);
        proxy.setHarCaptureTypes(captureTypes);
        
		//selenium proxy element to be passed as capability
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        return seleniumProxy;
    }
    public static String calculatePartitionPath(NSApi nsApi) {
		harPath = "";
		System.out.println(System.getenv("NS_WDIR")+"..........."+nsApi.ns_get_testid());
        harPath = System.getenv("NS_WDIR")+"/logs/TR"+nsApi.ns_get_testid();         

		//get current session id
        sessId = nsApi.ns_get_sessid();


        String partitionId = "";
        try{
            File file = new File(harPath+ "/.curPartition");
            if(file.exists())
            {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                while((line = br.readLine()) != null)
                {  
                    if(line.startsWith("CurPartitionIdx"))
                    {
                         partitionId = line.split("=")[1];
                         harPath += "/" +partitionId+ "/";
                    }
                }
                br.close();
            }
            else{
                partitionId = "";
                harPath += "/";
            }
        }catch(Exception e) {
            partitionId = "";
            harPath += "/";
        }
		return harPath;
	}
    public static String calculateHarPath(NSApi nsApi) {

		//find or create rbu_logs directory
        File file = new File(calculatePartitionPath(nsApi) + "rbu_logs/");
        if(!file.isDirectory())
        {
            file.mkdir();
        }
        harPath += "rbu_logs/";

        //find or create harp_files directory
        file = new File(harPath+ "harp_files/");
        if(!file.isDirectory()){
            file.mkdir();
        }
        harPath += "harp_files"; 
        return harPath; 
    }

	//calculate har file name 
    public static String calculateHarName(NSApi nsApi, String pageName, String hostName, String profileName) {
        String fileName = "";
        Date dt = new Date();
		int hostId = nsApi.ns_get_host_id();
		int childIdx = nsApi.ns_get_nvmid();
		int userIdx = nsApi.ns_get_userid();
		int sessionInstance = nsApi.ns_get_sessinst();
		int pageInstance = nsApi.ns_get_pageInstance();
		int groupIdx = nsApi.ns_get_groupid();
		int sessionIdx = nsApi.ns_get_sessid();
		int pageId = nsApi.ns_get_pageId();

//fileName = "P_"+pageName+"+"+profileName+"+"+init_script.sessId+"+"+hostId+ "+" + (dt.getYear() + 1900) + "-" +  (dt.getMonth() + 1) + "-" + dt.getDate() + "+" + dt.getHours() + "-" + dt.getMinutes() + "-" + dt.getSeconds() + "+" + childIdx + "_" + userIdx + "_" + sessionInstance + "_" + pageInstance + "_0_" + groupIdx + "_" + sessionIdx + "_" + pageId +"_0.har";
        fileName = "P_"+pageName+"+"+profileName+"+"+hostId+ "+" + (dt.getYear() + 1900) + "-" +  (dt.getMonth() + 1) + "-" + dt.getDate() + "+" + dt.getHours() + "-" + dt.getMinutes() + "-" + dt.getSeconds() + "+" + childIdx + "_" + userIdx + "_" + sessionInstance + "_" + pageInstance + "_0_" + groupIdx + "_" + sessionIdx + "_" + pageId +"_0.har";
        return fileName;
    }

	//capture har and save at given location
    public static Har saveHarAs(NSApi nsApi, String pageName, String hostName, String profileName) {
        fileNameWithPath = calculateHarPath(nsApi) + "/" +calculateHarName(nsApi, pageName, hostName, profileName);
        try{
            net.lightbody.bmp.core.har.Har har = proxy.getHar();
            try{
                Thread.sleep(20000);
            } catch(Exception e) {
                e.printStackTrace();
            }
            return har;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

	//get harp, performance data and calculate RBu stats
    public static String getHarAndRBUStats(NSApi nsApi, String pageName, String hostName, String profileName) {
		//capture har for given page
        Har har = saveHarAs(nsApi, pageName, hostName, profileName);
 
        //Add performance timings to the har
//		PerformanceTiming pt = new PerformanceTiming(init_script.driver, har);
		
		//write har in file
		try{
	    	FileOutputStream fos=new FileOutputStream(fileNameWithPath);
		    har.writeTo(fos);
        } catch(Exception e) {
            e.printStackTrace();
		}

        //Read har and create harp
        String harStr = "";
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(fileNameWithPath));
			StringBuilder sb = new StringBuilder();
			
			while (true) {
				String line = br.readLine();
				if(line == null)
					break;
				sb.append(line);
			}
			harStr = sb.toString();
			br.close();

		} catch(Exception e){
			try{
				br.close();
			}catch(Exception ex){
			}
			harStr = "";
		}
		
		//beautify harp
		harStr = new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(harStr));
		harStr = "onInputData(\n" +harStr+ "\n);";

		//name of harp file
		String harpFileName = fileNameWithPath+"p";

		//write harp file
		BufferedWriter writer = null;
		try{
			writer = new BufferedWriter(new FileWriter(harpFileName));
			writer.write(harStr);
			writer.close();
		}catch(Exception e){
			try{
				writer.close();
			}catch(Exception ex){
				
			}
		}			  

		//Calculate RBU stats
        String message = "";
        long onLoadTime = 0L;
        long onContentLoadTime = 0L;
        long overallTime = 0L;
		int timeToInteract = -1;
		int startRenderTime = -1;
		int visuallyCompleteTime = -1;
        int httpRequests = 0;
        int httpRequestsFromCache = 0;
        int bytesReceived = 0;
        int bytesSent = 0;
        int pageAvailability = 0;
        int jsSize = 0;
        int htmlSize = 0;
        int imageSize = 0;
        int cssSize = 0;
        int pageWeight = 0;
		int domElements = 0;
		int pageSpeedMetrics = -1;
        long backendResponse = 0L;
		int sessionCompleted = 0;
		int sessionSuccess = 0;
        long entryTime = 0L;
        long minStartTime = 0L;
        long maxEndTime = 0L;
		int akamaiOffLoad = 0;
		long firstStartTime = 0L;
		String browserType = "";     //browser name to be modified
		String harName = fileNameWithPath;

		//performance timings
		
   //     onLoadTime = pt.getOnLoadTime();
   //     onContentLoadTime = pt.getOnContentLoadTime();

        //extract logs from har file
		
		HarLog logs = har.getLog();
		browserType = logs.getBrowser().getName();
        System.out.println(browserType);
		//parse all entries in the logs to get all entries and their parameters
        for(HarEntry entry : logs.getEntries()){
			
            entryTime = entry.getTime();

            if (httpRequests == 0) {
				firstStartTime = entry.getStartedDateTime().getTime();
                minStartTime = entry.getStartedDateTime().getTime();
                maxEndTime = entry.getStartedDateTime().getTime() + entry.getTime();
                backendResponse += (entry.getTimings().getDns() > 0) ? entry.getTimings().getDns() : 0 +
                    ((entry.getTimings().getConnect() > 0)? entry.getTimings().getConnect() : 0) +
                    ((entry.getTimings().getWait() > 0)?entry.getTimings().getWait() : 0 )+
                    ((entry.getTimings().getReceive() > 0)? entry.getTimings().getReceive() : 0);
            }

            if (httpRequests >= 1) {
                minStartTime = minStartTime > entry.getStartedDateTime().getTime() ? entry.getStartedDateTime().getTime() : minStartTime;
                long tmp = entry.getStartedDateTime().getTime() + entry.getTime();
                maxEndTime = maxEndTime < tmp ? tmp : maxEndTime;
            }
			//counter to calculate number of entries
            httpRequests++;
            
		    //calculate total bytes received including header and body size	
            bytesReceived += entry.getResponse().getBodySize();
            bytesReceived += entry.getResponse().getHeadersSize();

			//calculate total bytes sent including header and body size
            bytesSent += entry.getRequest().getBodySize();
            bytesSent += entry.getRequest().getHeadersSize();

			//count no. of entries with a given type of content and response size
            String type = entry.getResponse().getContent().getMimeType();
            long size = entry.getResponse().getBodySize();

			//calculate total size of javascript type response
            if (type.toLowerCase().contains("javascript")) {
                jsSize += size;
            }

			//calculate total size of html type response
            if(type.toLowerCase().contains("html")) {
                htmlSize += size;
            }

            //calculate total size of image type response
            if(type.toLowerCase().contains("image")) {
                imageSize += size;
            }

            //calculate total size of css type response
            if(type.toLowerCase().contains("css")) {
                cssSize += size;
            }

            pageWeight = htmlSize+cssSize+imageSize+jsSize;

			//check status of response to verify page availability
            int status = entry.getResponse().getStatus();
            if(status == 301 || status == 302 || status == 307 || status == 401 || status == 407){
                continue;
            }
            else{
                if(status == 304 || status == 200){
                    pageAvailability = 1;
                }
            }
        }
        
        overallTime = maxEndTime - minStartTime;

        message = "HarStats : " + onContentLoadTime + "|" + onLoadTime + "|" +overallTime+ "|" +timeToInteract+ "|" +startRenderTime+ "|" +visuallyCompleteTime+ "|" +httpRequests+ "|" +httpRequestsFromCache+ "|" +bytesReceived+ "|" +bytesSent+ "|" +pageWeight+ "|" +jsSize+ "|" +cssSize+ "|" +imageSize+ "|" +domElements+ "|" +pageSpeedMetrics+ "|" +akamaiOffLoad+ "|" +backendResponse+ "|" +pageAvailability+ "|" +sessionCompleted+ "|" +sessionSuccess + "|" + harName + "|" +browserType+ "|" +firstStartTime;


		try{
            nsApi.ns_send_rbu_stats(message);
        } catch(Exception e) {
            e.printStackTrace();
		}
        return message;
    }
}
