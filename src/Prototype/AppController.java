package Prototype;
/**
 * @author <Tran Tuan Minh - s3804812>
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class AppController {
    InsuranceCard insuranceCard = new InsuranceCard();
    InsuranceClaim insuranceClaim = new InsuranceClaim();
    Holder holder = new Holder();
    Dependent dependent = new Dependent();

    public void run() throws IOException, InterruptedException{
        mainMenu();
    }

    // Pre Processing. This reads all the data from files //
    public void preProcessing() throws FileNotFoundException, InterruptedException {
        System.out.println("Loading data from file...");
        Holder holder = new Holder();
        Thread.sleep(500);
        holder.readData();
        System.out.println("...");
        Thread.sleep(500);
        dependent.readData();
        System.out.println("...");
        Thread.sleep(500);
        insuranceClaim.readData();
        insuranceClaim.getLastIDGenerated();
        System.out.println("...");
        Thread.sleep(500);
        insuranceCard.readData();
        System.out.println("Data loaded successfully!");
    }


    // Post Processing. This writes all the data to files. Some methods already write data to file as a precaution //
    public void postProcessing() throws IOException, InterruptedException {
        System.out.println("Saving data to file...");
        Thread.sleep(500);
        try {
            insuranceCard.writeData();
            insuranceClaim.writeData();
            insuranceClaim.writeLastIDGenerated();
            holder.writeData();
            dependent.writeData();
        } catch (Exception e) {
            System.out.println("Error saving data to file!");
        }
        System.out.println("Data saved successfully!");
    }


    // Main Menu //
    public void mainMenu() throws IOException, InterruptedException {
        preProcessing();
        Scanner mainMenuScanner = new Scanner(System.in);
        Boolean mainMenuLoop = true;
        System.out.println("Welcome to the Insurance Claim System!");
        while (mainMenuLoop) {
            System.out.println("Please select an option:\n" +
                    "1. Display All Holder\n" +
                    "2. Display All Dependents\n" +
                    "3. Display All Insurance Cards\n" +
                    "4. Display All Insurance Claim\n" +
                    "5. Display A Specific Insurance Claim\n" +
                    "6. New Insurance Claim\n" +
                    "7. Update Insurance Claim Details\n" +
                    "8. Delete Insurance Claim\n" +
                    "9. Exit");
            System.out.print("Please enter your choice: ");
            String mainMenuChoice = mainMenuScanner.nextLine();
            switch (mainMenuChoice) {
                case "1":
                    holder.printHolders();
                    break;
                case "2":
                    dependent.printAllDependents();
                    break;
                case "3":
                    insuranceCard.getAll();
                    break;
                case "4":
                    insuranceClaim.getAll();
                    break;
                case "5":
                    insuranceClaim.getOne();
                    break;
                case "6":
                    insuranceClaim.add();
                    insuranceClaim.writeLastIDGenerated();
                    break;
                case "7":
                    insuranceClaim.update();
                    break;
                case "8":
                    insuranceClaim.delete();
                    break;
                case "9":
                    postProcessing();
                    mainMenuLoop = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }
    }
}
