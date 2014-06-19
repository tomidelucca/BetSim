package betsim.model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventParser {

	private EventParser(){
		
	}
	
	public static List<Item> getItemsList(File file) throws IllegalInputException {
		
		BufferedReader buffer = null;
		List<Item> itemsList = new ArrayList<Item>();
		String currentLine = "";
		String split = ";";
		String optionSplit = ":";
		
		try {
			
			buffer = new BufferedReader(new FileReader(file));
			
			if((currentLine = buffer.readLine()) != null ); // Captures eventTitle
			
			while ((currentLine = buffer.readLine()) != null) {
				 
				String[] input = currentLine.split(split);			
				List<String> itemsAndOptions = new ArrayList<String>(Arrays.asList(input));
			
				String itemTitle = itemsAndOptions.get(0);
				itemsAndOptions.remove(itemTitle);				// Captures itemTitle 
			
				List<Option> optionsList = new ArrayList<Option>();
			
				for(String s : itemsAndOptions){					// Creates options
					String[] optionsAndValues = s.split(optionSplit);
					Option o = new Option(optionsAndValues[0], Double.parseDouble(optionsAndValues[1]));	
					optionsList.add(o);
				}
			
				Item i = new Item(itemTitle, optionsList);
			
				itemsList.add(i);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (IOException e) {
			System.out.println("Can't read input.");
		} catch (Exception e) {
			throw new IllegalInputException();
		} finally {
			try {
				buffer.close();
			} catch (IOException e) {
				System.out.println("Error closing buffer.");
			}
		}
		
		return itemsList;
	}

	public static String getEventTitle(File file) throws IllegalInputException{

		BufferedReader buffer = null;
		String eventTitle = "";
		
		try {
			buffer = new BufferedReader(new FileReader(file));
			
			if((eventTitle = buffer.readLine()) != null );				// Captures eventTitle
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (IOException e) {
			System.out.println("Can't read input.");
		} catch (Exception e) {
			throw new IllegalInputException();
		} finally {
			try {
				buffer.close();
			} catch (IOException e) {
				System.out.println("Error closing buffer.");
			}
		}
	
		return eventTitle;
	}
	
}
