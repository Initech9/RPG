package dev.codenmore.tilegame.display;

import java.awt.*;
import java.awt.Canvas;
import java.awt.Dimension;
//import org.eclipse.swt.widgets.Button;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Display extends JFrame {

	private JFrame frame;
	private Canvas canvas;

	private String title;
	private int width, height;

	private static final long serialVersionUID = 1L;
	// JButton bChange; // reference to the button object
	public JButton button = new JButton("Grow");

	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		// this.button = new JButton("Click Me!"); // construct a JButton

		createDisplay();

		// super( title ); // invoke the JFrame constructor
		// setLayout( new LayoutManager() ); // set the layout manager

	}

	private void createDisplay() {
		frame = new JFrame(title);
		//frame.setLayout(new FlowLayout());
		
		

		
		frame.setSize(width, height);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);




		
        
        //frame.setSize(300,300);  
        //frame.setVisible(true);  
		//bChange.setBounds(10,10, 100, 40);
		//if(mouse)
		//bChange.
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
		//frame.add(bChange);


		frame.add(button);
		button.setFocusable(false);
		
		// button.addActionListener(new ActionListener() {
		// 	public void actionPerformed(ActionEvent e) {
		// 		if (button.isEnabled()) {
		// 			System.out.println("Add Button is pressed");
		// 		}
		// 		if (!button.isEnabled()) {
		// 			System.out.println("Add Button is not pressed");
		// 		}
		// 	}
		// });

		button.setBounds(10,10, 100, 40);
		button.setFocusable(false);










		frame.add(canvas);
		frame.pack();

		
		//bChange.setBounds(10,10, 100, 40);
		//frame.add( bChange );
		
		

	}

	public Canvas getCanvas(){
		return canvas;
	}
	
	public JFrame getFrame(){
		return frame;
	}



  // constructor for ButtonFrame
  
	
}
