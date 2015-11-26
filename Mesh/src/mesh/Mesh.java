/*
 * 
 */
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
    TextField t;
    public SetSpaceWindow(MeshCanvas obj){
        super("Space between lines");
        this.setSize(250, 70);
        this.setLayout(new BorderLayout());
        
        t = new TextField();
        this.add(t,BorderLayout.NORTH);
        
        
        t.addActionListener((ActionEvent e) -> {
            int i = 0;
            try{ i = Integer.valueOf(t.getText());
            }   catch(java.lang.NumberFormatException ex){
                t.setText(null);
            }
            if(i>0)obj.setSpace(i);
        });
        
        
    }
}