package betsim.model;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Simulation {

	private SimulationDelegate delegate;
	private final ModelAccessor modelAccessor;
	private final RangedLinkedList<String> eventsHistory;
	private TimerTask task;
	private boolean isRunning;
	
	public Simulation(){
		this.modelAccessor = new ModelContainer(this);
		this.eventsHistory = new RangedLinkedList<String>(25L);
		createOneHundredUsers();	
		Event e1;
		try {
			e1 = new Event(new InputableBettableEvent("files/event.txt"));
			getBettingCentral().registerEvent(e1);
		} catch (IllegalInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setRunning(false);
	}
	
	private void createOneHundredUsers(){
		Integer i;
		UserManager manager = UserManager.getInstance();
		for(i=0;i<100;i++){
			User u = manager.createNewUser(i.toString());
			u.addCash(1000.0);
			getBettingCentral().registerUser(u);
		}
	}
	
	public void start(){
		setRunning(true);
		startBetting();
		delegate.updateUserInterface(this.modelAccessor);
	}
	
	public void end(){
		setRunning(false);
		endBetting();
		delegate.updateUserInterface(this.modelAccessor);
	}
	
	public void pay(Event e){
		getBettingCentral().pay("files/result.txt", e);
	}
	
	public void startBetting(){
		Timer t = new Timer();
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				Random r = new Random();
				User randomUser = getBettingCentral().getUserList().get(r.nextInt(50));
				Event event = getBettingCentral().getEventList().get(0);
				Item item = event.getBettableItems().get(r.nextInt(event.getBettableItems().size()));
				Option option = item.getOptions().get(r.nextInt(item.getOptions().size()));
				double amountBetted = (double)r.nextInt(100);
				try {
					getBettingCentral().placeBet(randomUser, event, amountBetted, new Path(item, option));
				} catch (InsufficientFundsException e) {
					getEventsHistory().push("El usuario " + randomUser + " intento apostar al item \"" + item + "\" pero sus fondos fueron insuficientes.");
				} finally {
					getEventsHistory().push("El usuario " + randomUser + " aposto $" + amountBetted + " en el item \"" +  item + "\".");
				}
				getDelegate().updateUserInterface(modelAccessor);
			}
		};
		
		this.task = task;
		t.scheduleAtFixedRate(task, new Date(), 100);
	}
	
	public void endBetting(){
		task.cancel();
	}
	
	public void setDelegate(SimulationDelegate delegate){
		this.delegate = delegate;
	}
	
	public SimulationDelegate getDelegate(){
		return this.delegate;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public BettingCentral getBettingCentral() {
		return modelAccessor.getBettingCentral();
	}
	
	public ModelAccessor getModelAccessor(){
		return this.modelAccessor;
	}

	public LinkedList<String> getEventsHistory() {
		return eventsHistory;
	}
}
