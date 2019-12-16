package com.test.elastic.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.test.elastic.domain.Car;


public interface CarService {

	List<String> BRANDS = new ArrayList<String>(Arrays.asList(new String[] 
			{"Toyota", "Honda", "Ford", "Mitsubishi", "Chevrolet"}));
	List<String> COLORS = new ArrayList<String>(Arrays.asList(new String[] 
			{"Red", "Black", "White", "Blue", "Silver"}));
	List<String> TYPES = new ArrayList<String>(Arrays.asList(new String[] 
			{"Sedan", "SUV", "MPV", "Truck", "Coupe"}));
	List<String> ADDITIONAL_FEATURES = new ArrayList<String>(Arrays.asList(new String[] 
			{"GPS", "Alarm", "Sunroof", "Media player", "Leather seats"}));
	List<String> FUEL_TYPES = new ArrayList<String>(Arrays.asList(new String[] 
			{"Petrol", "Electric", "Hybrid"}));
	List<String> TIRE_MANUFACTURERS = new ArrayList<String>(Arrays.asList(new String[] 
			{"Goodyear", "Bridgestone", "Dunlop"}));


	Car generateCar();

}
