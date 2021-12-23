import dao.Dao;
import dto.DtoTravel;
import model.*;
import org.hibernate.SessionFactory;
import service.*;
import util.HibernateUtil;
import view.ConsoleColors;
import view.GetValidData;
import view.PrintOutput;

import java.util.Date;
import java.util.List;

import static view.PrintOutput.getTravelInfo;

public class Main {

    public static final String managerUsername = "admin";
    public static final String managerPassword = "1234";

    public static void main(String[] args) {
        Dao.creatDatabase();
        SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        loginMenu();

    }

    public static void loginMenu() {
        UserService userService = new UserService();
        while (true) {
            int choice = GetValidData.getValidChoice("1)login 2)signUp 3)exit\nenter your choice: ", 3);
            switch (choice) {
                case 1: {
                    login(userService);
                }
                break;
                case 2: {
                    signUp(userService);
                }
                break;
                case 3: {
                    System.exit(0);
                }
            }
        }

    }

    private static void signUp(UserService userService) {
        User user = userService.getUserInfo();
        userService.save(user);
    }

    private static void login(UserService userService) {

        System.out.println("************ login menu ************");
        String username = GetValidData.getValidString("user name: ");
        String password = GetValidData.getValidString("password: ");

        if (username.equals(managerUsername) && password.equals(managerPassword)) {
            managerMainMenu();
        } else {
            User user = userService.getUserByUsernameAndPassword(username, password);
            if (user != null) {
                userMainMenu(user, userService);
            } else {
                System.out.println("wrong username or password!");
                loginMenu();
            }
        }

    }

    public static void managerMainMenu() {
        try {
            BusService busService = new BusService();
            DriverService driverService = new DriverService();
            TravelService travelService = new TravelService();

            while (true) {
                System.out.println("************ manager main menu ************");
                int choice = GetValidData.getValidChoice("1)get report \n2)add travel " +
                        "\n3)add driver " +
                        "\n4)add bus " +
                        "\n5)loginMenu " +
                        "\n6)exit \n" +
                        "enter your choice: ", 6);
                switch (choice) {
                    case 1: {
                       PrintOutput.printMangerReport( travelService.getMangerReport());
                    }
                    break;
                    case 2: {
                        Travel travel = getTravelInfo(busService, driverService);
                        travelService.save(travel);
                    }
                    break;
                    case 3: {
                        Driver driver = driverService.getDriverInfo();
                        driverService.save(driver);
                    }
                    break;
                    case 4: {
                        Bus bus = busService.getBusInfo();
                        busService.save(bus);
                    }
                    break;
                    case 5: {
                        loginMenu();
                    }
                    break;
                    case 6: {
                        System.exit(0);
                    }
                }
            }
        } catch (Exception e) {
           e.printStackTrace();
            //System.out.println(ConsoleColors.RED + e.getMessage() + ConsoleColors.RESET);
            managerMainMenu();
        }

    }

    public static void userMainMenu(User user, UserService userService) {
        TravelService travelService = new TravelService();
        PassengerService passengerService = new PassengerService();
        try {
            while (true) {
                System.out.println("************ user main menu ************");
                int choice = GetValidData.getValidChoice("1)buy ticket 2)increase balance 3)loginMenu 4)exit" +
                        "\nenter your choice: ", 4);
                switch (choice) {
                    case 1: {
                        List<DtoTravel> travelList = GetValidData.getValidTravel(travelService);
                        PrintOutput.printTravelList(travelList);
                        if (!travelList.isEmpty()) {
                            addTicketsToTravel(user, travelService, passengerService, userService);
                        }
                    }
                    break;
                    case 2: {
                        double amount = GetValidData.getValidDouble("enter amount: ");
                        userService.increaseBalance(user.getId(), amount);
                    }
                    break;
                    case 3: {
                        loginMenu();
                    }
                    break;
                    case 4: {
                        System.exit(0);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + e.getMessage() + ConsoleColors.RESET);
            userMainMenu(user, userService);
        }

    }

    private static void addTicketsToTravel(User user, TravelService travelService, PassengerService passengerService, UserService userService) {

        int travelId = GetValidData.getValidInt("enter travel id: ");
        Travel travel = travelService.findById(travelId);
        if (travel != null) {
            int numberOfTickets = GetValidData.getValidInt("enter number of the tickets: ");
            double newUserBalance = user.getBalance() - (travel.getPrice() * numberOfTickets);
            if (newUserBalance > 0) {
                if (travel.getAvailableChairs() > numberOfTickets) {
                    int availableChairs = (travel.getAvailableChairs()) - numberOfTickets;
                    getPassengersInfo(passengerService, numberOfTickets, travel, travelService, availableChairs);
                    user.setBalance(newUserBalance);
                    userService.update(user);
                } else throw new RuntimeException("there is not enough chair!");

            } else throw new RuntimeException("your balance is not enough!");
        }
    }

    private static void getPassengersInfo(PassengerService passengerService,
                                          int numberOfTickets, Travel travel,
                                          TravelService travelService,
                                          int availableChairs) {

        for (int i = 0; i < numberOfTickets; i++) {
            System.out.println("info for passenger number " + (i + 1) + ": ");
            Passenger passenger = passengerService.getPassengerInfo();
            Ticket ticket = Ticket.TicketBuilder.aTicket().setPassenger(passenger)
                    .setTravel(travel).setParchasingDate(new Date()).build();
            passengerService.save(passenger);
            travelService.addTicket(travel.getId(), ticket, availableChairs);
        }
    }

}
