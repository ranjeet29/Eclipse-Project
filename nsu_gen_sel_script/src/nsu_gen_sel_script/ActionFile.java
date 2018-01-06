package nsu_gen_sel_script;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActionFile {

	Logger log = Logger.getLogger(ActionFile.class.getName());
	
	public String url;

	public ActionFile(String sname) {
		
		log.setLevel(Level.INFO);
	}
	StringBuffer buffertext = new StringBuffer();
	public void packagename(String sname) throws IOException {
		String content = "package com.cavisson.scripts." + sname + "";
		//System.out.println("content :" + content);
		writeflow("package com.cavisson.scripts.", content);

	}

	public void writallaction()throws Exception{
		
		//System.out.println("buffertext : "+buffertext.toString());
		writeflow("//codeblock", buffertext.toString());
		String newFileName = "" + Main.path + "/Flow.java";
		log.info("Flow file:"+newFileName);
		log.info("Flow file Cretated successfully");
	}
	public void flowend() throws IOException {
		String endline = "HarBuilder.endhar();\n" + 
				"        HarBuilder.stopProxy();\n" + 
				"        snapshot.stopThread();\n" + 
				"        command.driverQuit();\n" + 
				"        return 0;\n" + 
				"";

		writeflow("//endblock", endline);

	}
	
	public void Harbuilder(){
		
	}
	
	public void browseAction(String url , String page , boolean har , boolean trans){
		this.url = url;
		String urlbody;
		if(trans){
		 urlbody = "\n       nsApi.ns_start_transaction(\""+page+"\");\n" + 
				"        nsApi.ns_web_url(\""+page+"\",\"URL=http://127.0.0.1/tours/index.html\",\"Method=GET\");\n" + 
				"     \n" + 
				"       try{\n" + 
				"           HarBuilder.proxy.newHar(\""+url+"\");\n" + 
				"           command.openUrl(\""+url+"\");\n" + 
				"       }catch(Exception e){e.printStackTrace();}\n" + 
				"       try{\n" + 
				"           String message = HarBuilder.getHarAndRBUStats(nsApi, \""+page+"\", \""+url+"\", \"default\");\n" + 
				"           System.out.println(message);\n" + 
				"       } catch(Exception e){e.printStackTrace();}\n" + 
				"       nsApi.ns_end_transaction(\""+page+"\", 0);\n";
		}else{
		
			urlbody = "\n" + 
					"        nsApi.ns_web_url(\""+page+"\",\"URL=http://127.0.0.1/tours/index.html\",\"Method=GET\");\n" + 
					"     \n" + 
					"       try{\n" + 
					"           HarBuilder.proxy.newHar(\""+url+"\");\n" + 
					"           command.openUrl(\""+url+"\");\n" + 
					"       }catch(Exception e){e.printStackTrace();}\n" + 
					"       try{\n" + 
					"           String message = HarBuilder.getHarAndRBUStats(nsApi, \""+page+"\", \""+url+"\", \"default\");\n" + 
					"           System.out.println(message);\n" + 
					"       } catch(Exception e){e.printStackTrace();}\n"  
					;
		}
		buffertext.append(urlbody);
	}

	
    public void clickAction(String xpath , String page , boolean har , boolean trans){
    	String clickaction;
    	if(trans && har ){
    	clickaction = "\n       nsApi.ns_start_transaction(\""+page+"\");\n" + 
				"       nsApi.ns_web_url(\""+page+"\", \"URL=http://127.0.0.1/tours/index.html\", \"Method=GET\");\n" + 
				"       try{\n" + 
				"       \n" + 
				"           HarBuilder.proxy.newHar(\""+url+page+"\");\n" + 
				"           command.findElementByXpath(\""+xpath+"\").click();\n" + 
				"           String message = HarBuilder.getHarAndRBUStats(nsApi, \""+page+"\", \""+url+"\", \"default\");\n" + 
				"           System.out.println(message);\n" + 
				"       }catch(Exception e){\n" + 
				"           e.printStackTrace();\n" + 
				"       }        \n" + 
				"       nsApi.ns_end_transaction(\""+page+"\", 0);\n";
    	}else if ( trans == true  && har == false){
    		clickaction = "\n       nsApi.ns_start_transaction(\""+page+"\");\n" + 
    				"       nsApi.ns_web_url(\""+page+"\", \"URL=http://127.0.0.1/tours/index.html\", \"Method=GET\");\n" + 
    				"       try{\n" + 
    				"           command.findElementByXpath(\""+xpath+"\").click();\n" + 
    				"       }catch(Exception e){\n" + 
    				"           e.printStackTrace();\n" + 
    				"       }        \n" + 
    				"       nsApi.ns_end_transaction(\""+page+"\", 0);\n";
		}else if (trans == false && har == true){
			clickaction = "\n " + 
					"       nsApi.ns_web_url(\""+page+"\", \"URL=http://127.0.0.1/tours/index.html\", \"Method=GET\");\n" + 
					"       try{\n" + 
					"       \n" + 
					"           HarBuilder.proxy.newHar(\""+url+page+"\");\n" + 
					"           command.findElementByXpath(\""+xpath+"\").click();\n" + 
					"           String message = HarBuilder.getHarAndRBUStats(nsApi, \""+page+"\", \""+url+"\", \"default\");\n" + 
					"           System.out.println(message);\n" + 
					"       }catch(Exception e){\n" + 
					"           e.printStackTrace();\n" + 
					"       }  \n"  
                    ;
				
		}else{
			clickaction = "\n " + 
					"       nsApi.ns_web_url(\""+page+"\", \"URL=http://127.0.0.1/tours/index.html\", \"Method=GET\");\n" + 
					"       try{\n" + 
					"           command.findElementByXpath(\""+xpath+"\").click();\n" + 
					"       }catch(Exception e){\n" + 
					"           e.printStackTrace();\n" + 
					"       }        \n" ;
    	}
    	
    	
    	buffertext.append(clickaction);
		
	}
    
    public void inputAction(String xpath ,String  page ,String data ,boolean har ,boolean trans){
    	String inputaction;
    	if (trans && har ){
    	inputaction = "\n       nsApi.ns_start_transaction(\""+page+"\");\n" + 
				"       nsApi.ns_web_url(\""+page+"\", \"URL=http://127.0.0.1/tours/index.html\", \"Method=GET\");\n" + 
				"       try{\n" + 
				"       \n" + 
				"           HarBuilder.proxy.newHar(\""+url+page+"\");\n" + 
				"           command.findElementByXpath(\""+xpath+"\").sendKeys(\""+data+"\");\n" + 
				"           String message = HarBuilder.getHarAndRBUStats(nsApi, \""+page+"\", \""+url+"\", \"default\");\n" + 
				"           System.out.println(message);\n" + 
				"       }catch(Exception e){\n" + 
				"           e.printStackTrace();\n" + 
				"       }        \n" + 
				"       nsApi.ns_end_transaction(\""+page+"\", 0);\n";
    	}else if ( trans == true  && har == false){
    		inputaction = "\n       nsApi.ns_start_transaction(\""+page+"\");\n" + 
    				"       nsApi.ns_web_url(\""+page+"\", \"URL=http://127.0.0.1/tours/index.html\", \"Method=GET\");\n" + 
    				"       try{\n" + 
    				"           command.findElementByXpath(\""+xpath+"\").sendKeys(\""+data+"\");\n" + 
    				"       }catch(Exception e){\n" + 
    				"           e.printStackTrace();\n" + 
    				"       }        \n" + 
    				"       nsApi.ns_end_transaction(\""+page+"\", 0);\n";
			
		}else if (trans == false && har == true){
			inputaction = "\n " + 
					"       nsApi.ns_web_url(\""+page+"\", \"URL=http://127.0.0.1/tours/index.html\", \"Method=GET\");\n" + 
					"       try{\n" + 
					"       \n" + 
					"           HarBuilder.proxy.newHar(\""+url+page+"\");\n" + 
					"           command.findElementByXpath(\""+xpath+"\").sendKeys(\""+data+"\");\n" + 
					"           String message = HarBuilder.getHarAndRBUStats(nsApi, \""+page+"\", \""+url+"\", \"default\");\n" + 
					"           System.out.println(message);\n" + 
					"       }catch(Exception e){\n" + 
					"           e.printStackTrace();\n" + 
					"       }        \n"  
					;
				
		}else{
	 	inputaction = "\n"+ 
    				"       nsApi.ns_web_url(\""+page+"\", \"URL=http://127.0.0.1/tours/index.html\", \"Method=GET\");\n" + 
    				"       try{\n" + 
    				"           command.findElementByXpath(\""+xpath+"\").sendKeys(\""+data+"\");\n" + 
    				"       }catch(Exception e){\n" + 
    				"           e.printStackTrace();\n" + 
    				"       }        \n"  
    				;
    	}
    	buffertext.append(inputaction);
	}
    
    public void scrollAction(String axis ,String page ,String  data ,boolean har ,boolean  trans){
		
	}

	public void createOrigFlowFromTemplate() {
		StringBuffer buffString = new StringBuffer();
		String newFileName = "" + Main.path + "/Flow.java";
		InputStream stream = Flow.class.getResourceAsStream("/Flow.javaa");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(stream));
			String line;
			while ((line = br.readLine()) != null) {
				buffString.append(line + "\n");
			}
			br.close();
			writeDataToFile(newFileName, buffString);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void writeDataToFile(String fileName, StringBuffer buffString){
		try{
			FileOutputStream fos  = new FileOutputStream(new File(fileName));
			fos.write(buffString.toString().getBytes());
			fos.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public void writeflow(String data, String content) throws IOException {
		String newFileName = "" + Main.path + "/Flow.java";
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(newFileName)));
			String line;
			while ((line = br.readLine()) != null) {
				if (data != null) {
					line = line.replace(data, content);
					buffer.append(line + "\n");
				}
			}
			br.close();
			writeDataToFile(newFileName, buffer);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
