package com.cognizant.truyum;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.service.MenuItemService;
import com.cognizant.truyum.util.DateUtil;

import java.util.*;
@SpringBootApplication
@ComponentScan("com.cognizant.truyum")
public class App {

	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	// static references
	private static MenuItemService menuItemService;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(App.class, args);

		// assigning from context
		menuItemService = context.getBean(MenuItemService.class);

		Scanner sc = new Scanner(System.in);
		String choice;

		do {
			System.out.println("Menu");
			System.out.println("****************************************");
			System.out.println("1. Admin");
			System.out.println("2. Customer");
			System.out.println("3. Exit");
			System.out.println("****************************************");

			choice = sc.nextLine();
			System.out.println("****************************************");

			switch (choice) {
			case "1": {
				String adminChoice;
				do {
					System.out.println("Admin Menu");
					System.out.println("****************************************");
					System.out.println("1. Get Menu Item List");
					System.out.println("2. Modify Menu Item");
					System.out.println("3. Get Menu Item");
					System.out.println("4. Exit Menu");
					System.out.println("****************************************");

					adminChoice = sc.nextLine();
					System.out.println("****************************************");

					switch (adminChoice) {
					case "1": {
						System.out.println("Admin Menu Item List");
						System.out.println("****************************************");
						testGetMenuItemListAdmin();
						break;
					}
					case "2": {
						System.out.println("Item 2 is modified. Enter 3 to display the changes.");
						System.out.println("****************************************");
						testEditMenuItem();
						break;
					}
					case "3": {
						System.out.println("2nd Menu Item is displayed");
						System.out.println("****************************************");
						testGetMenuItem();
						break;
					}
					case "4": {
						break;
					}
					default: {
						System.out.println("Enter valid choice");
					}
					}
				} while (!adminChoice.equals("4"));
				break;
			}
			case "2": {
				System.out.println("specific menu iteam");
				System.out.println("****************************************");
				testGetMenuItemListCustomer();
				break;
			}
			case "3": {
				System.out.println("Exited !!!");
				break;
			}
			default: {
				System.out.println("Enter valid choice");
			}
			}
		} while (!choice.equals("3"));

		sc.close();
	}
	
	
	
	
	

	private static void testGetMenuItemListAdmin() {
		LOGGER.info("Start");

		List<MenuItem> menuItems = menuItemService.getMenuItemListAdmin();
		LOGGER.debug("Menu Item List Admin:"+menuItems);
		LOGGER.info("End");
	}

	private static void testGetMenuItemListCustomer() {
		LOGGER.info("testGetMenuItemListCustomer Start");

		List<MenuItem> menuItems = menuItemService.getMenuItemListCustomer();
		LOGGER.debug("Menu Item List Customer:"+menuItems);
		LOGGER.info("testGetMenuItemListCustomer End");
	}

	private static void testGetMenuItem() {
		LOGGER.info("testGetMenuItem Start");

		MenuItem menuItem = menuItemService.getMenuItem(3);
		LOGGER.debug("Menu Item:"+menuItem);
		LOGGER.info("testGetMenuItem End");
	}
	
	private static void testEditMenuItem() {
		
		MenuItem menuItem = new MenuItem(3, "Chocolate Brownie", 32f, "Yes", DateUtil.convertToDate("02/11/2020"), "Dessert",
				"Yes");
		menuItemService.editMenuItem(menuItem);
	}

}
