import java.io.IOException;



public class CallThread {
     
	      
	public static void main(String[] args) throws InterruptedException, IOException  {
		   
		   
		  
		  DemoTest dt = null;
		  long startTime = 0 ;
		  Process arg = Runtime.getRuntime().exec("ps -ef");
		  for( Object b : arg.toString().getBytes() ){
			 System.out.println(b); 
		  }
		  System.out.println(arg);
		  try{
		  dt = new DemoTest();
		  startTime = System.nanoTime();
		  new Thread(dt).start();
		  Thread.sleep(7000);
		  }catch(Exception e ){e.printStackTrace();}
		  finally{
			try {
				if (dt != null)
				dt.stopThread();
				System.out.println("frege");
			} catch (Exception e2) {
				// TODO: handle exception
			}
		  }
		  
		  long totalTime = (System.nanoTime() - startTime)/ 10000000;
		  System.out.println("Total time: " + totalTime);

		  
		  System.out.println("******************************");
		  
		  try{
			  dt = new DemoTest();
			  startTime = System.nanoTime();
			  new Thread(dt).start();
			  Thread.sleep(7000);
			  }catch(Exception e ){e.printStackTrace();}
			  finally{
				try {
					dt.stopThread();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			  }
		  
		  
		
		  
	}
}
