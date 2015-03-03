package com.cpa.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.cpa.geometry.Vertex;
import com.cpa.tools.TestFilesManager;

public class AlgorithmViewer extends JFrame implements KeyListener {
	private AlgorithmCanvas canvas;

	public AlgorithmViewer() {
		initUI();
	}

	private void initUI(){

		createCanvas();

		setTitle("ALGOS");
		setSize(800,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		addKeyListener(this);

	}

	private void createCanvas(){
		canvas = new AlgorithmCanvas();
		add(canvas);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_N){
			canvas.nextTest();
		}
		if(e.getKeyCode() == KeyEvent.VK_H){
			canvas.generateConvexHull();
		}
		if(e.getKeyCode() == KeyEvent.VK_R){
			canvas.generateRectangle();
		}
		if(e.getKeyCode() == KeyEvent.VK_Q){
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
