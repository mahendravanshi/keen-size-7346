package com.masai;



import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.masai.repository.BusRepository;

public class Main implements Serializable{
	
	int x = 20;
	

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		
		BusRepository bus = new BusRepository();
		
		
		bus.add(new Bus());
		
		
		 

	}

}
