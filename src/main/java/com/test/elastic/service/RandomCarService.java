package com.test.elastic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.test.elastic.domain.Car;
import com.test.elastic.domain.Engine;
import com.test.elastic.domain.Tire;
import com.test.elastic.util.RandomDateUtil;


@Service
public class RandomCarService implements CarService {

	private Random random = new Random();

	@Override
	public Car generateCar() {
		String randomBrand = BRANDS.get(random.nextInt(BRANDS.size()));
		String randomColor = COLORS.get(random.nextInt(COLORS.size()));
		String randomType = TYPES.get(random.nextInt(TYPES.size()));

		Car car = new Car(randomBrand, randomColor, randomType);

		car.setAvailable(random.nextBoolean());
		car.setPrice(5000 + random.nextInt(7001));
		car.setFirstReleaseDate(RandomDateUtil.generateRandomDate());

		int randomCount = random.nextInt(ADDITIONAL_FEATURES.size());
		List<String> additionalFeatures = new ArrayList<String>();
		for (int i = 1; i <= randomCount; i++) {
			additionalFeatures.add(ADDITIONAL_FEATURES.get(i - 1));
		}
		car.setAdditionalFeatures(additionalFeatures);

		String randomFuelType = FUEL_TYPES.get(random.nextInt(FUEL_TYPES.size()));
		int randomHorsepower = 100 + random.nextInt(121);
		Engine randomEngine = new Engine(randomFuelType, randomHorsepower);
		car.setEngine(randomEngine);

		List<Tire> randomCompatibleTires = new ArrayList<Tire>();
		for (int i = 0; i < 3; i++) {
			String tireManufacturer = TIRE_MANUFACTURERS.get(random.nextInt(TIRE_MANUFACTURERS.size()));
			int tireSize = 15 + random.nextInt(3);
			int tirePrice = 200 + random.nextInt(201);

			Tire randomTire = new Tire(tireManufacturer, tireSize, tirePrice);
			randomCompatibleTires.add(randomTire);
		}

		car.setCompatibleTires(randomCompatibleTires);

		if (random.nextBoolean()) {
			car.setSecretFeature("Can fly");
		}

		return car;
	}

}
