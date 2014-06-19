package betsim.model;
import java.io.File;
import java.util.Set;

public class Result {
	
	private Set<Path> resultSet;
	
	public Result(String filePath, Event event) throws IllegalInputException{
		this.resultSet = ResultParser.getResultSet(new File(filePath), event);
	}
	
	public boolean isWinningPath(Path path){
		return resultSet.contains(path);
	}
	
	public String toString(){
		return resultSet.toString();
	}
}
