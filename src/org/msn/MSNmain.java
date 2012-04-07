package org.msn;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JFrame;

import org.msn.visual.MSNpanel;

/**
	 * @author Pawel Kleczkowski
	 * 
	 */
public class MSNmain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2915935641094874962L;
		private MSNpanel mainPanel;
		//private ConnectedPanel connectedPanel;
		//private DiagnosticHostMenu menu;

		MSNmain() {
			super("Manhattan Street Network Simulator");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(900, 600);
			setVisible(true);
			setLayout(new BorderLayout());
			this.mainPanel = new MSNpanel(this);
			add(mainPanel, BorderLayout.WEST);
			//connectedPanel = new ConnectedPanel(this,new DiagnosticHostClient());
			//add(connectedPanel, BorderLayout.WEST);
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
		};

		/**
		 * @param args
		 */
		public static void main(String[] args) {

			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					MSNmain main = new MSNmain();
					main.pack();
				}
			});
		}
	
}
