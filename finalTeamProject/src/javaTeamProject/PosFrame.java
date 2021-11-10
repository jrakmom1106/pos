package javaTeamProject;

import javax.swing.JFrame;

public class PosFrame extends JFrame {
	   public PosFrame() {
	      setTitle("pos");
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	      setContentPane(new Pos());

	      setSize(1050,640);
	      setVisible(true);
	   }
	}
