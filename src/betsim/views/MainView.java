package betsim.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Font;
import java.util.List;

import javax.swing.JComboBox;

import org.jfree.chart.ChartPanel;

import betsim.model.Event;
import betsim.model.Item;
import betsim.model.ModelAccessor;
import betsim.model.Simulation;
import betsim.model.SimulationDelegate;

import javax.swing.UIManager;


public class MainView extends JFrame implements SimulationDelegate{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JButton startStopButton;
	private JLabel eventNameLabel;
	private JComboBox itemsComboBox;
	private GraphDataManager graphManager;
	private ChartPanel chartPanel;
	private JMultilineLabel bettingOptionsLabel;
	private JMultilineLabel eventsArea;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainView() {
		setTitle("Bet Simulator");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
        this.chartPanel = new ChartPanel(GraphDataManager.newEmptyChart());
		this.chartPanel.setBounds(10, 151, 538, 336);
		contentPane.add(this.chartPanel);
		
		final Simulation sim = new Simulation();
		sim.setDelegate(this);
		
		final GraphDataManager graphManager = new GraphDataManager(sim.getModelAccessor());
		this.graphManager = graphManager;
		
		JButton btnNewButton = new JButton("Empezar Simulaci—n");
		this.startStopButton = btnNewButton;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(sim.isRunning())
					sim.end();
				else sim.start();
			}
		});
		btnNewButton.setBounds(276, 523, 214, 23);
		contentPane.add(btnNewButton);
		
		JLabel EventNameLabel = new JLabel("EventName");
		EventNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		EventNameLabel.setBounds(25, 32, 375, 30);
		eventNameLabel = EventNameLabel;
		contentPane.add(EventNameLabel);
		
		JComboBox ItemsComboBox = new JComboBox();
		ItemsComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JComboBox cB = (JComboBox)arg0.getSource();
				graphManager.didChangeSelectedItem((Item)cB.getSelectedItem());
				updateUserInterface(sim.getModelAccessor());
			}
		});
		ItemsComboBox.setBounds(25, 98, 243, 30);
		itemsComboBox = ItemsComboBox;
		contentPane.add(ItemsComboBox);
		
		JMultilineLabel eventsArea = new JMultilineLabel();
		eventsArea.setBackground(UIManager.getColor("Button.background"));
		eventsArea.setBounds(560, 151, 214, 336);
		contentPane.add(eventsArea);
		this.eventsArea = eventsArea;
		
		JMultilineLabel currentPay = new JMultilineLabel();
		currentPay.setBackground(UIManager.getColor("Button.background"));
		currentPay.setBounds(495, 31, 299, 97);
		contentPane.add(currentPay);
		this.bettingOptionsLabel = currentPay;
		
		JButton btnPagar = new JButton("Pagar");
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sim.pay(sim.getModelAccessor().getEventList().get(0));
			}
		});
		btnPagar.setFocusPainted(false);
		btnPagar.setBounds(632, 520, 117, 29);
		contentPane.add(btnPagar);
		
		updateUserInterface(sim.getModelAccessor());

	}

	@Override
	public void updateUserInterface(ModelAccessor accessor) {
		if(accessor.isSimulationRunning()) 
			startStopButton.setText("Parar Simulaci—n");
		else startStopButton.setText("Empezar Simulaci—n");
		startStopButton.setFocusPainted(false);
	
		Event event = accessor.getEventList().get(0);
		
		eventNameLabel.setText(accessor.getEventTitleForEvent(event));
		
		if(itemsComboBox.getItemCount()==0){
			List<Item> itemList = accessor.getItemListForEvent(event);
			for(Item i : itemList){
				itemsComboBox.addItem(i);
			}
			itemsComboBox.setFocusable(false);
		}

		graphManager.updateChartData();
		chartPanel.setChart(graphManager.getChart());
		
		Item selectedItem = (Item)itemsComboBox.getSelectedItem();
		this.bettingOptionsLabel.setText(accessor.getCurrentPricesForItem(selectedItem));
		
		List<String> events = accessor.getActionsPerformedForEvent(event);
		
		String stringEvents = "";
		
		for(String s : events){
			stringEvents += s + "\n";
		}
		
		this.eventsArea.setText(stringEvents);
	}
}
