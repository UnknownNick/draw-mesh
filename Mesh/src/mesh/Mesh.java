
package mesh;

import java.awt.*;
import java.awt.event.*;


public class Mesh extends Frame{
MeshCanvas m;
    public Mesh(){
        super("Mesh");
        this.setLayout(new GridLayout());
        this.setSize(1000, 1000);
        
        this.add((m = new MeshCanvas(this)));
        m.setSize(this.getSize());
        
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent ev){
                System.exit(0);
            }
        });
        
        new SetSpaceWindow(m).setVisible(true);
    }
    
    public static void main(String[] args) {
        new Mesh().setVisible(true);
    }
    
}

class MeshCanvas extends Canvas{
    int space = 20;
    Mesh owner;
    
    public MeshCanvas(Mesh owner){
        this.owner = owner;
    }
    
    public void setSpace(int value){
        this.space = value;
        repaint();
    }
    
    @Override
    public void paint(Graphics g){
        this.setSize(owner.getSize());
        int x = owner.getWidth();
        int y = owner.getHeight();
        for(int i = 1; i<=2000 ;i++){
            g.drawLine(0, space*i, x, space*i);
        }
        for(int u = 1 ; u <= 2000 ; u++){
            g.drawLine(space*u,0,space*u,y);
        }
    }
}

class SetSpaceWindow extends Frame{
    TextField t,cBlue,cGreen,cRed;
    Panel p;
    
    public SetSpaceWindow(MeshCanvas obj){
        super("Space between lines");
        this.setSize(300, 90);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        
        t = new TextField();
        this.add(t,BorderLayout.NORTH);
        
        p = new Panel();
        p.setLayout(new GridLayout(1,3));
        this.add(p);
        
        cBlue = new TextField("0");
        cGreen = new TextField("0");
        cRed = new TextField("0");
        cBlue.setBackground(Color.blue);
        cGreen.setBackground(Color.green);
        cRed.setBackground(Color.red);
        p.add(cRed);
        p.add(cGreen);
        p.add(cBlue);
        
        t.addActionListener((ActionEvent e) -> {
            int i = 0;
            try{ i = Integer.valueOf(t.getText());
            }   catch(java.lang.NumberFormatException ex){
                t.setText(null);
            }
            if(i>0)obj.setSpace(i);
        });
        
        cBlue.addActionListener((ActionEvent e) -> {
            int i = 0;
            Color c = obj.getBackground();
            try{
                i = Integer.valueOf(cBlue.getText());
            } catch(java.lang.NumberFormatException ex){ cBlue.setText(null); }
            if (i>=0 && i<256) {
                Color x1 = new Color(c.getRed(),c.getGreen(),i);
                obj.setBackground(x1);
                System.out.println("Color changed to: " + x1.toString());
            } else {
                cBlue.setText(null);
            }
        });
        
        cGreen.addActionListener((ActionEvent e) -> {
            int i = 0;
            Color c = obj.getBackground();
            try{
                i = Integer.valueOf(cGreen.getText());
            } catch(java.lang.NumberFormatException ex){ cGreen.setText(null); }
            if (i>=0 && i<256) {
                Color x1 = new Color(c.getRed(),i,c.getBlue());
                obj.setBackground(x1);
                System.out.println("Color changed to: " + x1.toString());
            } else {
                cGreen.setText(null);
            }
        });
        
        cRed.addActionListener((ActionEvent e) -> {
            int i = 0;
            Color c = obj.getBackground();
            try{
                i = Integer.valueOf(cRed.getText());
            } catch(java.lang.NumberFormatException ex){ cRed.setText(null); }
            if (i>=0 && i<256) {
                Color x1 = new Color(i,c.getGreen(),c.getBlue());
                obj.setBackground(x1);
                System.out.println("Color changed to: " + x1.toString());
            } else {
                cRed.setText(null);
            }
        });
    }
}