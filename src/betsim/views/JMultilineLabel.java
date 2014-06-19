package betsim.views;

import javax.swing.JTextArea;
import javax.swing.UIManager;

public class JMultilineLabel extends JTextArea{
    private static final long serialVersionUID = 1L;
    
    public JMultilineLabel(){
        super();
        setEditable(false);  
        setCursor(null);  
        setOpaque(false);  
        setFocusable(false);  
        setFont(UIManager.getFont("Label.font"));      
        setWrapStyleWord(true);  
        setLineWrap(true);
    }
    
    public JMultilineLabel(String text){
        super(text);
        setEditable(false);  
        setCursor(null);  
        setOpaque(false);  
        setFocusable(false);  
        setFont(UIManager.getFont("Label.font"));      
        setWrapStyleWord(true);  
        setLineWrap(true);
    }
} 