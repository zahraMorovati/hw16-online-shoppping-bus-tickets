package view;
import dao.Dao;
import dao.DaoUser;
import dto.DtoTravel;
import model.Passenger;
import model.enumation.BusType;
import model.enumation.Gender;
import service.TravelService;
import service.UserService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import static view.ConsoleColors.*;
public class GetValidData {


    public static Scanner input = new Scanner(System.in);

    public static String getValidName(String message) {
        System.out.print(BLUE_BRIGHT + message + RESET);
        String name = input.next();
        if (name.matches("[a-zA-Z]*")) {
            return name;
        } else {
            System.out.println(RED + "invalid input!" + RESET);
            return getValidName(message);
        }
    }

    public static String getValidUsername(String message) {
        System.out.print(BLUE_BRIGHT + message + RESET);
        String username = input.next();
        if (username.matches("^[a-z0-9_-]{3,15}$") && UserService.isValidUsername(username) && !username.equals("admin")) {
            return username;
        } else {
            System.out.println(RED + "invalid username!" + RESET);
            return getValidName(message);
        }
    }

    public static String getValidString(String message) {
        System.out.print(BLUE_BRIGHT + message + RESET);
        String str = input.next();
        return str;
    }

    public static String getValidFullName(String message) {
        System.out.print(BLUE_BRIGHT + message + RESET);
        String str = input.nextLine();
        return str;
    }

    public static Date getValidDate(String message) {
        System.out.print(BLUE_BRIGHT + message + RESET);
        try {
            Date date = new Date(input.nextInt(), input.nextInt(), input.nextInt());
            return date;
        } catch (Exception e) {
            System.out.println(RED + "invalid Date!" + RESET);
            return getValidDate(message);
        }
    }


    public static String getValidPhoneNumber(String message) {
        System.out.print(BLUE_BRIGHT + message + RESET);
        String phoneNumber = input.next();
        String str = phoneNumber.substring(0);
        if (isNumeric(str)) {
            return phoneNumber;
        } else {
            System.out.println(RED + "invalid phone number!" + RESET);
            return getValidPhoneNumber(message);
        }
    }

    public static int getValidInt(String message) {
        System.out.print(BLUE_BRIGHT + message + RESET);
        String str = input.next();
        if (isNumeric(str)) {
            return Integer.parseInt(str);
        } else {
            System.out.println(RED + "invalid input!" + RESET);
            return getValidInt(message);
        }
    }

    public static double getValidDouble(String message) {
        System.out.print(BLUE_BRIGHT + message + RESET);
        String str = input.next();
        if (isNumeric(str)) {
            return Double.parseDouble(str);
        } else {
            System.out.println(RED + "invalid input!" + RESET);
            return getValidDouble(message);
        }
    }

    public static int getValidChoice(String message, int maxChoice) {
        int number = getValidInt(message);
        for (int i = 1; i < maxChoice + 1; i++) {
            if (i == number) {
                return number;
            }
        }
        System.out.println(RED + "invalid choice!" + RESET);
        return getValidChoice(message, maxChoice);
    }

    public static boolean isNumeric(String str) {

        if (str == null || str.length() == 0) {
            return false;
        }

        try {
            Double.parseDouble(str);
            return true;

        } catch (NumberFormatException e) {
            return false;
        }

    }

    public static Gender getValidGender() {
        int choice = getValidChoice("1)male 2)female : ", 2);
        if (choice == 1)
            return Gender.MALE;
        else return Gender.FEMALE;
    }

    public static BusType getValidBusType() {
        int choice = getValidChoice("1)ELECTRIC 2)COACH 3)TROLLEY 4)TRANSIT : ", 4);
        switch (choice) {
            case 1:
                return BusType.ELECTRIC;
            case 2:
                return BusType.COACH;
            case 3:
                return BusType.TROLLEY;
            case 4:
                return BusType.TRANSIT;
        }
        return BusType.ETC;
    }

    public static Passenger getPassenger() {
        String name = getValidName("name: ");
        String family = getValidName("family name: ");
        int nationalCode = getValidInt("national code: ");
        Date birthDate = getValidDate("birthDate: ");
        Gender gender = getValidGender();
        return setPassenger(name, family, nationalCode, birthDate, gender);
    }

    private static Passenger setPassenger(String name, String family, int nationalCode, Date birthDate, Gender gender) {
        return Passenger.PassengerBuilder.aPassenger()
                .setName(name)
                .setFamily(family)
                .setNationalCode(nationalCode)
                .setBirthDate(birthDate)
                .setGender(gender).build();
    }

    public static boolean getValidYesOrNo(String message){
        System.out.print(BLUE_BRIGHT + message + RESET);
        String str= input.next();
        if(str.equalsIgnoreCase("yse")){
            return true;
        }else if(str.equalsIgnoreCase("no")){
            return false;
        }else {
            System.out.println(RED + "invalid input!" + RESET);
            return getValidYesOrNo(message);
        }
    }

    public static List<DtoTravel> getValidTravel(TravelService travelService){

        String origin = GetValidData.getValidName("origin: ");
        String destination = GetValidData.getValidName("destination: ");
        Date date=null;
        String companyName=null;
        BusType busType=null ;
        double minPrice=0;
        double maxPrice=0;
        Date startDate=null;
        Date endDate=null;

        boolean filterDate=GetValidData.getValidYesOrNo("do you want to filter by date ? (yes or no) ");
        if(filterDate){
            date=GetValidData.getValidDate("enter date: ");
        }

        filterDate=GetValidData.getValidYesOrNo("do you want to filter by company name ? (yes or no) ");
        if(filterDate){
            companyName=GetValidData.getValidName("enter company name: ");
        }

        filterDate=GetValidData.getValidYesOrNo("do you want to filter by bus type ? (yes or no) ");
        if(filterDate){
            busType=GetValidData.getValidBusType();
        }

        filterDate=GetValidData.getValidYesOrNo("do you want to filter by price ? (yes or no) ");
        if(filterDate){
            minPrice=GetValidData.getValidDouble("enter min price: ");
            maxPrice=GetValidData.getValidDouble("enter max price: ");
        }

        filterDate=GetValidData.getValidYesOrNo("do you want to filter between dates ? (yes or no) ");
        if(filterDate){
            startDate=GetValidData.getValidDate("enter start date: ");
            endDate=GetValidData.getValidDate("enter start time: ");
        }

        return travelService.getTravels(origin,destination,date,companyName,busType,minPrice,maxPrice,startDate,endDate);

    }







}
