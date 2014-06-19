package betsim.views;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import betsim.model.Item;
import betsim.model.ModelAccessor;
import betsim.model.Option;


public class GraphDataManager {
	
	public static final Integer MAX_POINTS = 20;
	private ModelAccessor modelAccessor;
	private Item currentSelectedItem;
	private JFreeChart chart;
	
	public GraphDataManager(ModelAccessor mA){
		this.setModelAccessor(mA);
	}

	public ModelAccessor getModelAccessor() {
		return modelAccessor;
	}
	
	public void didChangeSelectedItem(Item i){
		this.setCurrentSelectedItem(i);
	}
	
	public void updateChartData(){
		
		Map<Option, List<Double>> dataList = modelAccessor.getItemBetPriceHistoryForItem(getCurrentSelectedItem());
		Set<Option> optionSet = dataList.keySet();
		
		XYSeriesCollection collection = new XYSeriesCollection();
		
		for(Option o : optionSet){
			XYSeries s = new XYSeries(o.toString());
			List<Double> doubleList = dataList.get(o);
			Integer index = (doubleList.size()>MAX_POINTS)?doubleList.size()-MAX_POINTS:0;
			int j = 0;
			for(int i=index;i<doubleList.size();i++, j++){
				s.add(j, doubleList.get(i));
			}
			collection.addSeries(s);
		}
		
        NumberAxis domain = new NumberAxis("Time");
        domain.setTickLabelsVisible(false);
        NumberAxis range = new NumberAxis("Current Pay ($)");
        XYSplineRenderer r = new XYSplineRenderer(3);
        XYPlot xyplot = new XYPlot(collection, domain, range, r);
        this.chart = new JFreeChart(xyplot);
	}
	
	public JFreeChart getChart(){
		return this.chart;
	}

	public void setModelAccessor(ModelAccessor modelAccessor) {
		this.modelAccessor = modelAccessor;
	}

	public Item getCurrentSelectedItem() {
		return currentSelectedItem;
	}

	public void setCurrentSelectedItem(Item currentSelectedItem) {
		this.currentSelectedItem = currentSelectedItem;
	}
	
	public static JFreeChart newEmptyChart(){
		
		XYSeriesCollection col = new XYSeriesCollection();
        NumberAxis domain = new NumberAxis("P");
        NumberAxis range = new NumberAxis("Time");
        XYSplineRenderer r = new XYSplineRenderer(3);
        XYPlot xyplot = new XYPlot(col, domain, range, r);
        
        return new JFreeChart(xyplot);
	}

	
}
