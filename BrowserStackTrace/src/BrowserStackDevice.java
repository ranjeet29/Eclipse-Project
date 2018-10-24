import org.apache.commons.codec.binary.Base64;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BrowserStackDevice {

    public static void main(String a[]) throws IOException{

       String url = "https://api.browserstack.com/automate/builds/ie/sessions/14e69729e534a089e1527920b121222244a00638/networklogs";
        
        String name = "saishubham1";
        String password = "NsURxkBQRy59HuZHeeZP";
        String authString = name + ":" + password;
        //String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
        //String authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());
        String authStringEnc = new String(Base64.encodeBase64(authString.getBytes()));
        System.out.println("Base64 encoded auth string: " + authStringEnc);
        Client restClient = Client.create();
        WebResource webResource = restClient.resource(url);
        ClientResponse resp = webResource.accept("application/json")
                                         .header("Authorization", "Basic " + authStringEnc)
                                         .get(ClientResponse.class);
        if(resp.getStatus() != 200){
            System.err.println("Unable to connect to the server");
        }
        String output = resp.getEntity(String.class);

        System.out.println("Output from Server .... \n");
        System.out.println(output);
        
        FileOutputStream fos = new FileOutputStream(new File("/home/netstorm/Desktop/IEHar.har"));
        fos.write(output.getBytes());
        
        //BufferedWriter br = new BufferedWriter(new FileWriter(new File("/home/netstorm/Desktop/IEHar.har")));
        //br.write(output);
        
    }
}
