package HeadlessBrowser;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarLog;
import net.lightbody.bmp.proxy.CaptureType;

public class ChromeBrowser {

	public static void main(String[] args) throws Exception {
		BrowserMobProxy proxy = new BrowserMobProxyServer();
		proxy.start(7991);

		Proxy seleniumproxy = ClientUtil.createSeleniumProxy(proxy);
		proxy.setHarCaptureTypes(CaptureType.getAllContentCaptureTypes());
		proxy.enableHarCaptureTypes(CaptureType.RESPONSE_HEADERS,
				CaptureType.REQUEST_HEADERS);

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(CapabilityType.PROXY, seleniumproxy);
		caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		caps.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);

		proxy.newHar("google.com");
		System.setProperty("webdriver.chrome.driver",
				"/home/netstorm/Desktop/chromedriver");
		WebDriver driver = new ChromeDriver(caps);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		System.out.println("Browser Opened");
		driver.get("https://www.google.com");
		Thread.sleep(7000);
		System.out.println(driver.getTitle());
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		File dest = new File("/home/netstorm/Desktop/google.png");
		if (dest.exists()) {
			dest.delete();
		}
		FileUtils.moveFile(src, dest);
		Har har = proxy.getHar();

		getInfofromHar(har);

		FileOutputStream fos = new FileOutputStream(
				"/home/netstorm/Desktop/google.har");
		har.writeTo(fos);

		if (proxy != null) {
			proxy.stop();
			driver.quit();
		}

	}

	private static void getInfofromHar(Har har) {
		HarLog log = har.getLog();
		List<HarEntry> entries = log.getEntries();

		for (HarEntry enrty : entries) {

			System.out.println("Url name : " + enrty.getRequest().getUrl()
					+ "  Urlsize : " + enrty.getRequest().getHeadersSize());
			System.out.println("Request method : "
					+ enrty.getRequest().getMethod() + " Url status : "
					+ enrty.getResponse().getStatus());
			System.out.println("Time took : " + enrty.getTime() + " ms");

		}

	}
	
	

}
