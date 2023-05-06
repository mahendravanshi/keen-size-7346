package com.masai.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

import com.masai.Bus;

public class BusRepository {

	static String busRepoFileName = "busRepository.ser";
	
    static {
    	File f = new File(busRepoFileName);
    	
    	if(!f.exists()) {
    		
    		 try {
    			 
    			 ObjectOutputStream  x=new ObjectOutputStream(new FileOutputStream(busRepoFileName));
    		 
    			 x.writeObject(new HashSet<Bus>());
    			 
    			 x.flush();
    			 x.close();
    		 
    		 }
    		 catch(IOException e) {
    			 
    			 System.err.println("file cannot be created");
    		 }
    	}
    	
    }
	
	public void add(Bus... b) throws ClassNotFoundException, IOException {
		
		
		
		ObjectInputStream obj = new ObjectInputStream(new FileInputStream(busRepoFileName));
		
		Set<Bus> bus = (Set<Bus>) obj.readObject();
		
	
		  for(int i=0;i<b.length;i++) {
			  
			  bus.add(b[i]);
		  }
		  
		  ObjectOutputStream  x = new ObjectOutputStream(new FileOutputStream(busRepoFileName));
		  
//		  x.writeObject(bus);
		  
		  x.flush();
		  x.close();
		  
		  obj.close();
		  
		  
	}
	
	
}
