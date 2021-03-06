package com.cavisson.scripts.;

import pacJnvmApi.NSApi;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class SnapShotThread implements Runnable {
  private boolean isRunning;
  private WebDriver driver;
  private NSApi nsapi; 
  private int clipId;  
  /* 10 secs */
  private long interval = 10000; 
  private long counter;
  
  public SnapShotThread(NSApi nsapi, WebDriver driver, long interval) {
    this.nsapi = nsapi;
    this.driver = driver;
    this.interval = interval;
    this.isRunning = true;
  }

  public void run() {
    while(isRunning) {
      try {
        File src = getScreenShot();
        saveFile(src);
        Thread.sleep(interval);
      }catch (InterruptedException e) {
        System.err.println("Interrupted while sleeping");
      }
    }
  }

  /**
   * Returns the snapshot as PNG file
   */
  private File getScreenShot() {
    return ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
  }

  /**
   * Returns the snapshot folder. 
   * $NS_WDIR/logs/TR[0-9]+/[0-9]+/rbu_logs/snap_shots/
   */

  private String getSnapShotFolder() {
    return HarBuilder.calculatePartitionPath(nsapi) + "rbu_logs/snap_shots/";
  }

  /**
   * Takes the source file and saves it to the appropriate
   * log folder in the testrun
   */
  private void saveFile(File src) {
    long length = src.length();
    File dest = new File(getSnapShotFolder() + getFormattedFileName(length));
    try {
      FileUtils.moveFile(src, dest);
    }catch(IOException e) {
      System.err.println("Failed to save file with name: " + dest.getName());
    }
  }

  /**
   * Returns a well formatted file name
   * @param length the length of the source file
   */
  private String getFormattedFileName(long length) { 
    int childIdx = nsapi.ns_get_nvmid();
    int userIdx = nsapi.ns_get_userid();
    int sessionInstance = nsapi.ns_get_sessinst();
    int pageInstance = nsapi.ns_get_pageInstance();
    int groupIdx = nsapi.ns_get_groupid();
    int sessionId = nsapi.ns_get_sessid();
    int pageId = nsapi.ns_get_pageId();
     
    String out = String.format(
      "video_clip_%d_%d_%d_%d_0_%d_%d_%d_0_%d_%d_%d.jpeg",
      childIdx,
      userIdx,
      sessionInstance,
      pageInstance,
      groupIdx,
      sessionId,
      pageId, 
      clipId,
      counter,
      length
    );
    clipId++;
	counter += interval;
    return out;
  }

  public void stopThread() {
    isRunning = false;
  }
}
