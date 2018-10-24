
package com.cavisson.scripts.;

import pacJnvmApi.NSApi;

public class runlogic {
    
    Home home = new Home();
    WaitTime wait = new WaitTime();
    
 /*#####################################
  * 
  * CREATE PAGE OBJECT FOR EACH FLOW 
  * LIKE ( Home home = new Home(); ) 
  * 
  *#####################################
  */
    
    public void execute(NSApi nsApi) {
        int initStatus = init_script.execute(nsApi);
             home.execute(nsApi);
             wait.execute(nsApi);
            
            /*###############################
             *  
             *  CALL execute(nsApi) METHOD
             *  WITH REFRENCE VALUE 
             *  LIKE ( home.execute(nsApi) )
             *  
             *###############################	
             */
             
	     nsApi.ns_end_session();
            }
      }

    
