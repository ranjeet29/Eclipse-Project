
public class DemoTest implements Runnable {
	
   
	public boolean isRunnable ;
	
	public DemoTest( ) {
		// TODO Auto-generated constructor stub
		
		this.isRunnable = true ; 
	}  
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while (isRunnable) {
			
			System.out.println("Thread :" + Thread.currentThread());
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			System.out.println(" ");
		}
		
		
	}
   
	
	void stopThread() {
		// TODO Auto-generated method stub
		
			
			isRunnable = false ; 
			

	}
}
