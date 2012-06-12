package org.msn.visual;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.msn.MSNmain;

public class NetworkGraphPanel extends Canvas implements Runnable {
	private int height;
	private int width;
	private int netSize;
	private int bufferSize;
	private Image router;
	
	private MSNmain parentFrame;
	private Image doubleBuffer;
    private Graphics g2=null;
	
public NetworkGraphPanel(MSNmain parent) {
	setPreferredSize(new Dimension(850, 680));
	//setBorder(BorderFactory.createTitledBorder(""));
	setVisible(true);
	height = getHeight();
	width = getWidth();
	netSize = 0;
	bufferSize = 1;
	parentFrame = parent;
	//removeAll();
	//repaint();
	//revalidate();
    try {
    	router=ImageIO.read(new File("res/router.png"));}
    catch(IOException e) {
    	e.printStackTrace();
      	}
	}

	public void run() {
	    while (true) {
	    	repaint();
	             try {
	                Thread.sleep(100);
	            } catch (InterruptedException ie) {
	            	ie.printStackTrace();
	            }
	           
	        }
	    }
	@Override
	public void update(Graphics g) {
	    paint(g);

	}
	
	public void setNetSize(int n) {
		this.netSize = n;
	}
	
	public void setBufferSize(int n) {
		this.bufferSize = n;
	}
	
	public synchronized void paint(Graphics g) {
		if (netSize < 128) {
			int k=2;
			if (netSize < 5) {
				k = 2 * ( 6 - netSize);
			}
			if (netSize == 5) {
				k = 3;
			}
			width = this.getWidth();
			height = this.getHeight();
			doubleBuffer = createImage(width, height);
			g2 = doubleBuffer.getGraphics();
			g2.setColor(Color.black);
			for (int i = 0; i < netSize; i++) {
				for (int j = 0; j < netSize; j++) {
					g2.drawImage(router, 50 + (i * 120 * k / 2), 50 + (j * 90 * k / 2), 40, 27, this);
					g2.setFont(new Font("Tahoma",Font.BOLD,11));
					g2.setColor(Color.white);
					g2.drawString("("+j+","+i+")", 58 + (i * 120 * k / 2), 72 + (j * 90 * k / 2));
					if ((i % 2) == 1) {
						    //isEven = true
						g2.setColor(Color.black);
						g2.drawRect(65 + (i * 120 * k /2), 30 + (j * 90 * k / 2), 11, 6);
						g2.setColor(Color.red);
						g2.drawString("D1", 65 + (i * 120 * k / 2), 48 + (j * 90 * k / 2));
						if (!parentFrame.mainPanel.network.routers[j][i].d1.isEmpty()) {
							g2.setColor(Color.yellow);
							g2.fillRect(66 + (i * 120 * k / 2), 31 + (j * 90 * k / 2), 10, 5);
						}
						
						g2.setColor(Color.blue);
						g2.drawString("B1", 65 + (i * 120 * k / 2), 87 + (j * 90 * k / 2));
						g2.setColor(Color.black);
						for (int l=1;l<=bufferSize;l++) {
							g2.setColor(Color.black);
							g2.drawRect(65 + (i * 120 * k / 2), 83 + l*6 + (j * 90 * k / 2), 11, 6);
							//if (bufferCell[i,j].)
							if (parentFrame.mainPanel.network.routers[j][i].b1.isPacketInBufferCell(l)) {
								g2.setColor(Color.orange);
								g2.fillRect(66 + (i * 120 * k / 2), 84 + l*6 + (j * 90 * k / 2), 10, 5);
							}
						}
					}
					else {
						g2.setColor(Color.black);
						g2.drawRect(65 + (i * 120 * k / 2), 90 + (j * 90 * k / 2), 11, 6);
						g2.setColor(Color.red);
						g2.drawString("D1", 65 + (i * 120 * k / 2), 88 + (j * 90 * k / 2));
						if (!parentFrame.mainPanel.network.routers[j][i].d1.isEmpty()) {
							g2.setColor(Color.yellow);
							g2.fillRect(66 + (i * 120 * k / 2), 91 + (j * 90 * k / 2), 10, 5);
						}
						
						g2.setColor(Color.blue);
						g2.drawString("B1", 65 + (i * 120 * k / 2), 49 + (j * 90 * k / 2));
						for (int l=1;l<=bufferSize;l++) {
							g2.setColor(Color.black);
							g2.drawRect(65 + (i * 120 * k /2), 38 - l*6 + (j * 90 * k / 2), 11, 6);
							if (parentFrame.mainPanel.network.routers[j][i].b1.isPacketInBufferCell(l)) {
								g2.setColor(Color.orange);
								g2.fillRect(66 + (i * 120 * k /2), 39 - l*6 + (j * 90 * k / 2), 10, 5);
							}
						}
					}
					if ((j % 2) == 0) {
						g2.setColor(Color.black);
						g2.drawRect(26 + (i * 120 * k / 2), 57 + (j * 90 * k / 2), 6, 11);
						g2.setColor(Color.red);
						g2.drawString("D2", 35 + (i * 120 * k / 2), 67 + (j * 90 * k / 2));
						if (!parentFrame.mainPanel.network.routers[j][i].d2.isEmpty()) {
							g2.setColor(Color.yellow);
							g2.fillRect(27 + (i * 120 * k / 2), 58 + (j * 90 * k / 2), 5, 10);
						}
						
						g2.setColor(Color.blue);
						g2.drawString("B2", 91 + (i * 120 * k / 2), 67 + (j * 90 * k / 2));
						for (int l=1;l<=bufferSize;l++) {
							g2.setColor(Color.black);
							g2.drawRect(100 + l*6 + (i * 120 * k /2), 57 + (j * 90 * k / 2), 6, 11);
							if (parentFrame.mainPanel.network.routers[j][i].b2.isPacketInBufferCell(l)) {
								g2.setColor(Color.orange);
								g2.fillRect(101 + l*6 + (i * 120 * k /2), 58 + (j * 90 * k / 2), 5, 10);
							}
						}
					}
					else {
						g2.setColor(Color.black);
						g2.drawRect(107 + (i * 120 * k / 2), 57 + (j * 90 * k / 2), 6, 11);
						g2.setColor(Color.red);
						g2.drawString("D2", 91 + (i * 120 * k / 2), 67 + (j * 90 * k / 2));
						if (!parentFrame.mainPanel.network.routers[j][i].d2.isEmpty()) {
							g2.setColor(Color.yellow);
							g2.fillRect(108 + (i * 120 * k / 2), 58 + (j * 90 * k / 2), 5, 10);
						}
						g2.setColor(Color.blue);
						g2.drawString("B2", 37 + (i * 120 * k / 2), 67 + (j * 90 * k / 2));
						for (int l=1;l<=bufferSize;l++) {
							g2.setColor(Color.black);
							g2.drawRect(34 - l*6 + (i * 120 * k /2), 57 + (j * 90 * k / 2), 6, 11);
							if (parentFrame.mainPanel.network.routers[j][i].b2.isPacketInBufferCell(l)) {
								g2.setColor(Color.orange);
								g2.fillRect(35 - l*6 + (i * 120 * k /2), 58 + (j * 90 * k / 2), 5, 10);
							}
						}
					}
					if (!parentFrame.mainPanel.network.routers[j][i].fcIn.isEmpty()) {
						g2.setColor(Color.green);
						g2.setFont(new Font("Tahoma",Font.BOLD,15));
						g2.drawString("FCin", 95 + (i * 120 * k / 2), 90 + (j * 90 * k / 2));
					}
					else {
						g2.setColor(Color.black);
						g2.setFont(new Font("Tahoma",Font.BOLD,11));
						g2.drawString("FCin", 95 + (i * 120 * k / 2), 90 + (j * 90 * k / 2));
					}
					if (!parentFrame.mainPanel.network.routers[j][i].fcOut.isEmpty()) {
						g2.setColor(Color.green);
						g2.setFont(new Font("Tahoma",Font.BOLD,15));
						g2.drawString("FCout", 20 + (i * 120 * k / 2), 90 + (j * 90 * k / 2));
					}
					else {
						g2.setColor(Color.black);
						g2.setFont(new Font("Tahoma",Font.BOLD,11));
						g2.drawString("FCout", 20 + (i * 120 * k / 2), 90 + (j * 90 * k / 2));
					}
				}
			}
		}
		else {
			width = this.getWidth();
			height = this.getHeight();
			doubleBuffer = createImage(width, height);
			g2 = doubleBuffer.getGraphics();
		}
		g.drawImage(doubleBuffer, 0, 0, this);
	}

	
	public void drawRouterBufferB1() {
		
	}
	
	public void drawRouterBufferB2() {
		
	}
}