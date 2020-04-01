package TiroParabolico;

import java.awt.Canvas; 
import java.awt.Dimension;
import javax.swing.JFrame; 

public final class Display {
    private JFrame jframe; 
    private Canvas canvas;  
    
    private final String title;   
    private final int width;      
    private final int height;   
    
    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;        
        createDisplay();
    }

    public void createDisplay() {
        jframe = new JFrame(title);
        jframe.setSize(width, height);
        
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setFocusable(false);
        
        jframe.add(canvas);
        jframe.pack();
        
    }
    
    public JFrame getJframe() {
        return jframe;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
