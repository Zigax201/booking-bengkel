package com.bengkel.booking.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.bengkel.booking.models.BookingOrder;
import com.bengkel.booking.models.Customer;
import com.bengkel.booking.models.ItemService;
import com.bengkel.booking.models.MemberCustomer;
import com.bengkel.booking.models.Vehicle;
import com.bengkel.booking.repositories.ItemServiceRepository;

public class BengkelService {

	// Silahkan tambahkan fitur-fitur utama aplikasi disini
	private static List<ItemService> serviceList = ItemServiceRepository.getAllItemService();
	private static PrintService printService = new PrintService();

	// Login
	public boolean login(List<Customer> listAllCustomers, String custID, String password) {
		if (listAllCustomers == null || custID == null || password == null)
			return false;

		if (findCustomerByID(custID, listAllCustomers) == null) {
			System.out.println("Customer Id Tidak Ditemukan atau Salah!");
			return false;
		}

		if (findPassCustomer(password, listAllCustomers) == null) {
			System.out.println("Password yang anda Masukan Salah!");
			return false;
		}

		return true;
	}

	public static Customer findCustomerByID(String custID, List<Customer> listAllCustomers) {
		return listAllCustomers.stream()
				.filter(customer -> customer.getCustomerId().equals(custID))
				.findFirst()
				.orElse(null);
	}

	public static Customer findPassCustomer(String pass, List<Customer> listAllCustomers) {
		return listAllCustomers.stream()
				.filter(customer -> customer.getPassword().equals(pass))
				.findFirst()
				.orElse(null);
	}

	// Info Customer
	public void getDetailCustomer(Customer customer) {
		MemberCustomer memberCustomer = null;
		String isMember = "Non Member";
		// int num = 1;

		System.out.println("+========================= Customer Profile =========================+");
		System.out.printf("%-15s : %-15s\n", "Customer ID", customer.getCustomerId());
		System.out.printf("%-15s : %-15s\n", "Nama", customer.getName());

		if (customer instanceof MemberCustomer) {
			isMember = "Member";
			memberCustomer = (MemberCustomer) customer;
		}

		System.out.printf("%-15s : %-15s\n", "Customer Status", isMember);
		System.out.printf("%-15s : %-15s\n", "Alamat", customer.getAddress());

		if (memberCustomer != null)
			System.out.printf("%-15s : %-15s\n", "Saldo Koin", memberCustomer.getSaldoCoin());

		System.out.println("List Kendaraan  :");
		PrintService.printVechicle(customer.getVehicles());

		// System.out.println("+=====================================================================+");
		// System.out.printf("| %-4s | %-11s | %-10s | %-15s | %-15s |\n",
		// "No.", "Vehicle ID", "Warna", "Tipe Kendaraan", "Tahun");
		// System.out.println("+=====================================================================+");
		// for (Vehicle vehicle : customer.getVehicles()) {
		// System.out.printf("| %-4s | %-11s | %-10s | %-15s | %-15s |\n",
		// num, vehicle.getVehiclesId(), vehicle.getColor(), vehicle.getVehicleType(),
		// vehicle.getYearRelease());
		// num++;
		// }
		// System.out.println("+=====================================================================+");
	}

	public void createReservation(Customer currentCustomer, List<BookingOrder> bookingOrders) {
		String paymentMethod = "";
		List<ItemService> selectedItemServices = new ArrayList<>();

		do {
			String vehicleID = Validation.validasiInput("Masukkan Vehicle ID", "Input Tidak Valid", "[^\\u0000]*");
			Vehicle isVehicle = currentCustomer.getVehicles().stream()
					.filter(vehicle -> vehicle.getVehiclesId().equals(vehicleID))
					.findFirst()
					.orElse(null);

			if (isVehicle == null) {
				System.out.println("Kendaraan Tidak Ditemukan");
				continue;
			}

			selectedItemServices = selectedServices(isVehicle);

			if (currentCustomer instanceof MemberCustomer)
				paymentMethod = Validation.validasiInput("Silahkan Pilih Metode Pembayaran (Saldo Coin atau Cash)",
						"Input Tidak Valid", "(?i)\\b(saldo coin|cash)\\b");
			else
				System.out.println("Metode Pembayaran menggunakan Cash");

			System.out.println(paymentMethod.equals("") ? "Cash" : paymentMethod);

			break;
		} while (true);

		String bookingID = "Book-" + UUID.randomUUID().toString().substring(0, 3);
		double bookingPrice = calculateReservationPrice(selectedItemServices);

		BookingOrder bookingOrder = new BookingOrder();
		bookingOrder.setBookingId(bookingID);
		bookingOrder.setCustomer(currentCustomer);
		bookingOrder.setServices(selectedItemServices);
		bookingOrder.setPaymentMethod(paymentMethod);
		bookingOrder.setTotalServicePrice(bookingPrice);
		bookingOrder.calculatePayment();

		bookingOrders.add(bookingOrder);

		System.out.println("Booking berhasil!!!");
		System.out.println("Total Harga Service : Rp. " + bookingOrder.getTotalServicePrice());
		System.out.println("Total Pembayaran : Rp. " + bookingOrder.getTotalPayment());

		if(currentCustomer instanceof MemberCustomer){
			MemberCustomer memberCustomer = (MemberCustomer) currentCustomer;
			memberCustomer.setSaldoCoin(memberCustomer.getSaldoCoin() - bookingPrice);
		}
	}

	private List<ItemService> selectedServices(Vehicle isVehicle) {
		List<ItemService> selectedItemServices = new ArrayList<>();

		do {
			printService.printService(serviceList, isVehicle);
			String serviceID = Validation.validasiInput("Masukkan Service ID", "Input Tidak Valid", "[^\\u0000]*");
			ItemService itemService = findItemServiceByID(serviceID);

			if (itemService == null) {
				System.out.println("Service tidak ditemukan.");
				continue;
			}

			if (!selectedItemServices.contains(itemService))
				selectedItemServices.add(itemService);
			else
				System.out.println("Service sudah di pilih");

			String selectAgain = Validation.validasiInput("Apakah anda ingin menambahkan Service Lainnya? (Y/T)",
					"Input Tidak Valid", "[YyTt]");

			if (selectAgain.equalsIgnoreCase("t"))
				break;

		} while (true);

		return selectedItemServices;
	}

	private ItemService findItemServiceByID(String service) {
		return serviceList.stream()
				.filter(itemService -> itemService.getServiceId().equals(service))
				.findFirst()
				.orElse(null);
	}

	private static double calculateReservationPrice(List<ItemService> selectedItemServices) {
		double finalPrice = selectedItemServices.stream()
				.mapToDouble(ItemService::getPrice)
				.sum();
		return finalPrice;
	}

	// Booking atau Reservation

	// Top Up Saldo Coin Untuk Member Customer

	// Logout

}
