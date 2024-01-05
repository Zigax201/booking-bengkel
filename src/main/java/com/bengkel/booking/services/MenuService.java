package com.bengkel.booking.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bengkel.booking.models.BookingOrder;
import com.bengkel.booking.models.Customer;
import com.bengkel.booking.models.ItemService;
import com.bengkel.booking.repositories.CustomerRepository;
import com.bengkel.booking.repositories.ItemServiceRepository;

public class MenuService {
	private static List<Customer> listAllCustomers = CustomerRepository.getAllCustomer();
	private static List<ItemService> listAllItemService = ItemServiceRepository.getAllItemService();
	private static List<BookingOrder> bookingOrders = new ArrayList<>();
	private static Scanner input = new Scanner(System.in);
	private static BengkelService bengkelService = new BengkelService();
	private static Customer currentCustomer = new Customer();

	public static void run() {
		boolean isLooping = true;
		do {
			login();
			mainMenu();
		} while (isLooping);

	}

	public static void login() {
		int counter = 3;
		while (true) {
			System.out.println("1. Login");
			System.out.println("0. Exit");
			String menuLogin = Validation.validasiInput("Pilih Menu : ", "Input is not valid ", "[01]");

			if (menuLogin.equals("0")) {
				System.exit(0);
			} else if (!menuLogin.equals("1"))
				System.err.println("Invalid Input!");

			System.out.println("+=========================================+");
			String custID = Validation.validasiInput("Masukkan Customer ID : ", "Input is not valid", "[^\\u0000]*");
			String password = Validation.validasiInput("Masukkan Password : ", "Input is not valid", "[^\\u0000]*");
			System.out.println("+=========================================+");

			if (bengkelService.login(listAllCustomers, custID, password)){
				currentCustomer = BengkelService.findCustomerByID(custID, listAllCustomers);
				break;
			}

			counter--;

			if (counter == 0) {
				System.out.println("+=========================================+");
				System.out.println("3 kali gagal terdeteksi program di hentikan");
				System.out.println("+=========================================+");
				System.exit(0);
			} else {
				System.out.println("+====================+");
				System.out.println(counter + " kali lagi percobaan");
				System.out.println("+====================+");
			}
		}

	}

	public static void mainMenu() {
		String[] listMenu = { "Informasi Customer", "Booking Bengkel", "Top Up Bengkel Coin", "Informasi Booking",
				"Logout" };
		int menuChoice = 0;
		boolean isLooping = true;

		do {
			PrintService.printMenu(listMenu, "Booking Bengkel Menu");
			menuChoice = Validation.validasiNumberWithRange("Masukan Pilihan Menu:", "Input Harus Berupa Angka!",
					"^[0-9]+$", listMenu.length - 1, 0);
			System.out.println(menuChoice);

			switch (menuChoice) {
				case 1:
					bengkelService.getDetailCustomer(currentCustomer);
					break;
				case 2:
					bengkelService.createReservation(currentCustomer, bookingOrders);
					break;
				case 3:
					// panggil fitur Top Up Saldo Coin
					break;
				case 4:
					// panggil fitur Informasi Booking Order
					break;
				default:
					System.out.println("Logout");
					isLooping = false;
					break;
			}
		} while (isLooping);

	}

	// Silahkan tambahkan kodingan untuk keperluan Menu Aplikasi
}
