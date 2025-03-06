/*
Assignment 2

Programmer Name: Eric Chang
Assignment Start: March 5th 2025, 8:25 PM
Assignment Completion: March 6th 2025, 1:04 PM
Total Hours for Assignment: 3 hours 30 minutes
Comments:

The easiest part of this was the functionality of the program. It was very easy making a program that take user input and creates a for loop based on how many trips they made and then 
calculating the total mpg of all the trips.The part that took the longest was making all the input validation and exception handling work properly. I had to use multiple regex if statements
 to make sure that the user could still use the 'stop' function throughout the runtime and also get an error message if they entered numbers that were negative, alphabets or zero
 to prevent future crashes. 

 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MPGapp{
    public static void main(String[]args){

        Scanner input = new Scanner(System.in);
        double totalMiles = 0;
        double totalGas = 0;
        int numTrips = 0;
        //time formatter
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MM-dd-yyyy , (HH:mm:ss a)");
        String Date_and_time = now.format(formatter);
    
        System.out.println("\n\nWELCOME to Eric's MPG Calculator");
        System.out.println("INSTRUCTIONS: Enter the Miles and Gallons for each trip");
        System.out.println("(Enter the word 'STOP' at any time to EXIT)\n");
        
        //Catch test to make sure incorrect input doesn't crash the program
        while(true){
            System.out.print("Please enter how many Trips were Driven: ");
            String tripInput = input.next();

            //Stop method that is avalible to the user at any time
            if(tripInput.equalsIgnoreCase("Stop")){
                System.out.println("Program Stopped.");
                input.close();
                return;
            }
            if (!tripInput.matches("-?\\d+")) {  
                System.out.println("ERROR: Only numeric values (whole numbers) are allowed or 'STOP' to Exit!");
                continue;
            }
            
            try{
                numTrips = Integer.parseInt(tripInput);
                if(numTrips <= 0){
                    System.out.println("ERROR. Please enter a REAL number.\n");
                    continue;
                }
                break;
            }catch(InputMismatchException e){
                System.out.println("ERROR.");
                input.next();
            }
        }
                
        //iterate through the amount of trips inputted 
        for(int count =1; count <=  numTrips; count++){
            System.out.printf("\n- For Trip %d-\n",count);

            //Catch function to prevent crash Miles input
            boolean validateMil = false;
            double miles = 0;
            while(!validateMil){
                System.out.print("\nEnter miles driven: ");
                String mileInput = input.next();
                //Stop method that is avalible to the user at any time
                if(mileInput.equalsIgnoreCase("Stop")){
                    System.out.println("Program has been Stopped.");
                    input.close();
                    return;
                }
                if (!mileInput.matches("[1-9]\\-?\\d*\\.?\\d*")) {  // Matches numbers (including decimals and negative)
                    System.out.println("ERROR: Only (+)numeric values are allowed or 'STOP' to Exit!");
                    continue;
                }
                //if the user didn't hit stop try function collects input if it is valid
                try{
                    miles = Double.parseDouble(mileInput);
                    validateMil = true;
                }catch(InputMismatchException e){
                    System.out.println("ERROR.");
                    input.next();
                }
                
            }

            //Catch function to prevent crash with Gallons input
            boolean validateGal = false;
            double gallons = 0;
            while(!validateGal){
                System.out.print("Enter how many gallons were used:  ");
                String galInput = input.next();
                //Stop method that is avalible to the user at any time
                if(galInput.equalsIgnoreCase("Stop")){
                    System.out.println("Program has been Stopped.");
                    input.close();
                    return;
                }
                if (!galInput.matches("[1-9]\\-?\\d*\\.?\\d*")) {  
                    System.out.println("ERROR: Only (+)numeric values are allowed or 'STOP' to Exit!");
                    continue;
                }
                //Collects input if it is valid
                try{
                    gallons = Double.parseDouble(galInput);
                    validateGal =true;
                }catch(InputMismatchException e){
                    System.out.println("ERROR.");
                    input.next();
                }
            }

            if (miles > 0 && gallons > 0){
                double mpg = (double)miles / gallons;
                System.out.printf("\nThe MPG for the trip is %.2f%n",mpg);
                totalGas += gallons;
                totalMiles += miles;

                if(count > 1){
                double combinedMpg = (double)totalMiles /totalGas;
                System.out.printf("Combined Miles per Gal for the %d trips: %.2f%n%n", count, combinedMpg);  
                } 
            } 
            else{
                System.out.println("Please enter only (+)POS. REAL Values!");
                count--;
            }
        }
        if (totalGas > 0){
            double totalTripMpg = (double) totalMiles / totalGas;

            System.out.printf("\n-TOTAL TRIP RESULTS-\n");
            System.out.printf("\nTRIPS: %d%n",numTrips);
            System.out.printf("MILES DRIVEN: %.2f%n",totalMiles);
            System.out.printf("GALLONS USED: %.2f%n", totalGas);
            System.out.printf("TOTAL Miles per Gallon: %.2f%n", totalTripMpg);
            
        
            System.out.print("\n\nThank you for using the Gas Mileage application.");
            System.out.print("Time of Calculation is: " + Date_and_time);
            System.exit(0);

        } 
        input.close();

    }  
}