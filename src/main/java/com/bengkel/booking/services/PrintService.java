package com.bengkel.booking.services;

import java.util.List;

import com.bengkel.booking.models.Car;
import com.bengkel.booking.models.ItemService;
import com.bengkel.booking.models.Vehicle;

public class PrintService {

	public static void printMenu(String[] listMenu, String title) {
		String line = "+---------------------------------+";
		int number = 1;
		String formatTable = " %-2s. %-25s %n";

		System.out.printf("%-25s %n", title);
		System.out.println(line);

		for (String data : listMenu) {
			if (number < listMenu.length) {
				System.out.printf(formatTable, number, data);
			} else {
				System.out.printf(formatTable, 0, data);
			}
			number++;
		}
		System.out.println(line);
		System.out.println();
	}

	public static void printVechicle(List<Vehicle> listVehicle) {
		String formatTable = "| %-2s | %-15s | %-10s | %-15s | %-15s | %-5s | %-15s |%n";
		String line = "+----+-----------------+------------+-----------------+-----------------+-------+-----------------+%n";
		System.out.format(line);
		System.out.format(formatTable, "No", "Vechicle Id", "Warna", "Brand", "Transmisi", "Tahun", "Tipe Kendaraan");
		System.out.format(line);
		int number = 1;
		String vehicleType = "";
		for (Vehicle vehicle : listVehicle) {
			if (vehicle instanceof Car) {
				vehicleType = "Mobil";
			} else {
				vehicleType = "Motor";
			}
			System.out.format(formatTable, number, vehicle.getVehiclesId(), vehicle.getColor(), vehicle.getBrand(),
					vehicle.getTransmisionType(), vehicle.getYearRelease(), vehicleType);
			number++;
		}
		System.out.printf(line);
	}

	public void printService(List<ItemService> listItemServices, Vehicle vehicle) {
		int num = 1;

		List<ItemService> listItemServicesByVehicle = listItemServices.stream()
				.filter(service -> service.getVehicleType().equals(vehicle.getVehicleType()))
				.toList();

		System.out.println("+=====================================================================+");
		System.out.printf("| %-4s | %-11s | %-15s | %-15s | %-15s |\n",
				"No.", "Service ID", "Nama Service", "Tipe Kendaraan", "Harga");
		System.out.println("+=====================================================================+");
		for (ItemService itemService : listItemServicesByVehicle) {
			System.out.printf("| %-4s | %-11s | %-15s | %-15s | %-15s |\n",
					num, itemService.getServiceId(), itemService.getServiceName(),
					itemService.getVehicleType(), itemService.getPrice());
			num++;
		}
		System.out.println("+=====================================================================+");
	}

	// Silahkan Tambahkan function print sesuai dengan kebutuhan.

}
