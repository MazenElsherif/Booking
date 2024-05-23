package Utilties;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;



//generic actions/methods
public  class CommonActions  {
	 public static String getCheckInDate() {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");

	        LocalDate today = LocalDate.now();
	        // Get the last day of the current month
	        LocalDate lastDayOfMonth = today.withDayOfMonth(today.lengthOfMonth());
	        int remainingDays = lastDayOfMonth.getDayOfMonth() - today.getDayOfMonth();
	        
	        // Check if remaining days are less than 3
	        if (remainingDays < 3) {
	            // Get the first day of the next month
	        	today = today.plusMonths(1).withDayOfMonth(1);
	        } 
	        System.out.println(today.format(formatter));
	            return today.format(formatter);
	        
	    }
		public static String getCheckOutDate(String dateStr) {
			 DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
	            
	            // Parse the input date string into a LocalDate object
	            LocalDate date = LocalDate.parse(dateStr, inputFormatter);
		        
		        // Add 3 days to the date
		        LocalDate newDate = date.plusDays(3);
		        
		        // Format the new date as "yyyy-MM-dd"
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
		        System.out.println(newDate.format(formatter));
		        return newDate.format(formatter);
	        
	    }
		public static String changeFormat(String inputData) {
			DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);
	        
	        // Define the output formatter
	        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("EEE, MMM dd", Locale.ENGLISH);
	        
	   
	            // Parse the input date string to a LocalDate
	            LocalDate date = LocalDate.parse(inputData, inputFormatter);
	            
	            // Format the LocalDate to the desired output format
	            return date.format(outputFormatter);
				
	            
	            // Print the result
	         
		}
		 public static void editExcelFile(String firstString, String secondString) {
		        try {
		           String filePath= System.getProperty("user.dir")+"\\Data.xlsx";
		            FileInputStream file = new FileInputStream(new File(filePath));
		            
		            // Create Workbook instance holding reference to .xlsx file
		            XSSFWorkbook workbook = new XSSFWorkbook(file);
		            
		            // Get the desired sheet from the workbook
		            XSSFSheet sheet = workbook.getSheetAt(0);
		            
		            // Set the first string in the second row, second cell (B2)
		            Row row = sheet.getRow(1); // Row index starts from 0, so 1 is the second row
		            if (row == null) {
		                row = sheet.createRow(1);
		            }
		            Cell cellB2 = row.getCell(1); // Cell index starts from 0, so 1 is the second cell (B)
		            if (cellB2 == null) {
		                cellB2 = row.createCell(1);
		            }
		            cellB2.setCellValue(firstString);
		            
		            // Set the second string in the second row, third cell (C2)
		            Cell cellC2 = row.getCell(2); // Cell index 2 is the third cell (C)
		            if (cellC2 == null) {
		                cellC2 = row.createCell(2);
		            }
		            cellC2.setCellValue(secondString);
		            
		            // Close the input stream
		            file.close();
		            
		            // Write the changes back to the Excel file
		            FileOutputStream outFile = new FileOutputStream(new File(filePath));
		            workbook.write(outFile);
		            outFile.close();
		            
		            // Close the workbook
		            workbook.close();
		            
		            System.out.println("Excel file updated successfully!");
		            
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		    
}
