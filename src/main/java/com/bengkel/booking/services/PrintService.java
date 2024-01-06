package com.bengkel.booking.services;

import java.util.List;

import com.bengkel.booking.models.BookingOrder;
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

		String line = "+------+-------------+-----------------+-----------------+---------------+%n";
		System.out.printf(line);
		System.out.printf("| %-4s | %-11s | %-15s | %-15s | %-13s |\n",
				"No.", "Service ID", "Nama Service", "Tipe Kendaraan", "Harga");
		System.out.printf(line);
		for (ItemService itemService : listItemServicesByVehicle) {
			System.out.printf("| %-4s | %-11s | %-15s | %-15s | %-13s |\n",
					num, itemService.getServiceId(), itemService.getServiceName(),
					itemService.getVehicleType(), itemService.getPrice());
			num++;
		}
		System.out.printf(line);
	}

	public static void printNotMember() {
		System.out.println("+------------------------------------+");
		System.out.println("Maaf fitur ini hanya untuk member saja");
		System.out.println("+------------------------------------+");
	}

	public static void printTitleFeature(String string) {
		System.out.println("+========================= " + string + " =========================+");
	}

	public static void printBookingOrder(List<BookingOrder> bookingOrders) {
		String formatTable = "| %-2s | %-15s | %-15s | %-15s | %-15s | %-20s | %-15s | %-15s |%n";
		String line = "+----+-----------------+-----------------+-----------------+-----------------+----------------------+-----------------+-----------------+%n";
		System.out.format(line);
		System.out.format(formatTable, "No", "Booking Id", "Nama Customer", "List Service", "Payment Method",
				"Total Service Price", "Total Payment", "Booking Date");
		System.out.format(line);
		int number = 1;
		for (BookingOrder bookingOrder : bookingOrders) {
			int counter = 0;
			for (ItemService itemService : bookingOrder.getServices()) {
				if (counter == 0)
					System.out.format(formatTable, number, bookingOrder.getBookingId(),
							bookingOrder.getCustomer().getName(),
							itemService.getServiceName(), bookingOrder.getPaymentMethod(), bookingOrder.getTotalServicePrice(),
							bookingOrder.getTotalPayment(), "");
				else
					System.out.format(formatTable, "", "", "", itemService.getServiceName(), "", "", "", "");
				counter++;
			}
			number++;
		}
		System.out.printf(line);
	}

	// Silahkan Tambahkan function print sesuai dengan kebutuhan.

}
