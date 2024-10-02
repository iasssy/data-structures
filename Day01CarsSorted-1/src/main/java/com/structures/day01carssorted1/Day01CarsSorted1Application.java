package com.structures.day01carssorted1;

import com.structures.day01carssorted1.entity.Car;
import com.structures.day01carssorted1.entity.SortByEngineSize;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

@SpringBootApplication
public class Day01CarsSorted1Application {

    static ArrayList<Car> parking = new ArrayList<>();

    static String fileName = "cars.txt";

    public static void main(String[] args) {
        SpringApplication.run(Day01CarsSorted1Application.class, args);



        // Instantiate 5 or more Cars and add them to the list.
		/*
		ArrayList<Car> parking = new ArrayList<>();
		parking.add(new Car("Toyota",0.8, 2015));
		parking.add(new Car("Honda",1.2, 2007));
		parking.add(new Car("Mercedes",1, 2016));
		parking.add(new Car("Buick",2, 1980));
		parking.add(new Car("Tesla",0, 2024));
		parking.add(new Car("Toyota",0.8, 2024));
		 */

        readDataFromFile(fileName);


        // 1. Print out cars, one per line (implement toString in Car that displays all fields)
        System.out.println("Initial array list from file:");
        for (Car car : parking) {
            System.out.println(car);
        }

        // 2. Sort cars by makeModel using Comparable<Car> interface display the list
        Collections.sort(parking);
        System.out.println("Cars sorted by makeModel:");
        for (Car car : parking) {
            System.out.println(car);
        }


        //3. Sort cars by engineSizeL using Comparator<Car> and display the list
		/* Comparator sortByEngineSize = new SortByEngineSize();
		Collections.sort(parking, sortByEngineSize);
		 */
        parking.sort(Comparator.comparingDouble(Car::getEngineSizeL));

        System.out.println("Cars sorted by engineSizeL:");
        for (Car car : parking) {
            System.out.println(car);
        }

        //4. Sort cars by prodYear using lambda and display the list
        Collections.sort(parking, (o1, o2) -> o1.getProdYear() - o2.getProdYear());
        System.out.println("Cars sorted by by prodYear (lambda):");
        for (Car car : parking) {
            System.out.println(car);
        }

        //5. Sort cars by prodYear then makeModel using lambda or Comparator::CONSTANTS
        // and if prodYear is the same then by makeModel (you need to create a few entries with the same prodYear to test this)
		/*
		parking.sort((car1, car2) -> {
			int result = Integer.compare(car1.getProdYear(), car2.getProdYear());
			if (result == 0) {
				result = car1.getMakeModel().compareTo(car2.getMakeModel());
			}
			return result;
		});
		*/
        parking.sort(Comparator.comparingInt(Car::getProdYear).thenComparing(Car::getMakeModel));

        System.out.println("Cars sorted by  by prodYear then makeModel (lambda):");
        for (Car car : parking) {
            System.out.println(car);
        }

    }

    public static void readDataFromFile(String file) {
        try (Scanner reader = new Scanner(new File(file))) {
            System.out.println("Reading data from " + file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                System.out.println("Line: " +line);
                String[] parts = line.split(";");
                if (parts.length < 3) {
                    System.out.println("Invalid line format: " + line);
                    continue;
                }
                String makeModel = parts[0].trim();
                double engineSizeL = Double.parseDouble(parts[1]);
                int prodYear = Integer.parseInt(parts[2]);

                parking.add(new Car(makeModel, engineSizeL, prodYear));
            }
            System.out.println();
            System.out.println("Cars read and unsorted: " + parking);
            System.out.println();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + file, e);
        } catch (Exception e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
    }
}