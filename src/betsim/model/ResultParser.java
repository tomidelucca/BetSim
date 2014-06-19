package betsim.model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ResultParser {

	private ResultParser(){
		
	}
	
	public static Set<Path> getResultSet(File file, Event event) throws IllegalInputException{
		
		BufferedReader buffer = null;
		String currentLine = "";
		String split = ";";
		Set<Path> inputSet = new HashSet<Path>();
		
		try {
			buffer = new BufferedReader(new FileReader(file));
			Integer itemIndex = 0;
			
			while ((currentLine = buffer.readLine()) != null) {
				String[] input = currentLine.split(split);
				
				Integer length = input.length;
				
				for(int optionIndex=0;optionIndex<length;optionIndex++){
					if(input[optionIndex].equals("true"))
					{
						Item item = itemForEventAndIndex(event, itemIndex);
						Option option = optionForItemAndIndex(item, optionIndex);
						inputSet.add(new Path(item,option));
						break;
					}
				}
				itemIndex++;
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
		
		return inputSet;
	}
	
	private static Option optionForItemAndIndex(Item item, Integer index){
		return item.getOptions().get(index);
	}
	
	private static Item itemForEventAndIndex(Event event, Integer index){
		return event.getBettableItems().get(index);
	}
	
}
