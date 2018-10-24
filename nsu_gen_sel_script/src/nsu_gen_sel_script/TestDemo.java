package nsu_gen_sel_script;

import java.io.File;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TestDemo {

	public static void main(String[] args) {
		try {
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(new FileReader(new File(
					"capabilityData.json")));
			JSONObject arr = (JSONObject) obj.get("capabilities");
			String deviceName = (String) arr.get("deviceName");
			String platformName = (String) arr.get("platformName");
			String platformVersion = (String) arr.get("platformVersion");
			String udid = (String) arr.get("udid");
			String browserName = (String) arr.get("browserName");

			System.out.println(deviceName + ", " + platformName + ", "
					+ platformVersion + ", " + udid + ", " + browserName);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
