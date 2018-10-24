package com.cavisson.scripts.;

import pacJnvmApi.NSApi;

public class WaitTime implements NsFlow{
    public int execute(NSApi nsApi){
        nsApi.ns_start_transaction("WaitTime");
        try{
            
            Thread.sleep(5000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }                                                                                                             
        nsApi.ns_end_transaction("WaitTime", 0);
        return 0;
    }
}
