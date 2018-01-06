package Browser;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
 
 
/**
 * This object represents the top log object according the HAR specification.
 *
 * @see <a href="http://www.softwareishard.com/blog/har-12-spec/#log">specification</a>
 */
@JsonPropertyOrder({
        "version",
        "creator",
        "browser",
        "pages",
        "entries",
        "comment"
})
public class HarLog {
 
    private String version = "1.2";
 
    private HarBrowser browser;
   
    private String comment;
 
    @JsonCreator
    public HarLog(@JsonProperty("version") String version, 
                  @JsonProperty("browser") HarBrowser browser,
                  @JsonProperty("comment") String comment) {
        this.version = version;
       
        this.browser = browser;
      
        this.comment = comment;
    }
 
    public String getVersion() {
        return version;
    }
 
   
    public HarBrowser getBrowser() {
        return browser;
    }
 
   
   
 
    public String getComment() {
        return comment;
    }
 
    @Override
    public String toString() {
        return "HarLog [  browser = " + browser + ", comment = " + comment +  ", version = " + version + "]";
    }
}