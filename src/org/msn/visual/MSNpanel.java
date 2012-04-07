package org.msn.visual;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.SwingConstants;

import org.msn.MSNmain;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import java.awt.Panel;

public class MSNpanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7052427088447626849L;
	private MSNmain mainFrame;
	/**
	 * Create the panel.
	 */
	public MSNpanel(MSNmain mainFrame) {
		this();
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	public MSNpanel() {
		setPreferredSize(new Dimension(800, 650));
		setVisible(true);
		removeAll();
		repaint();
		revalidate();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel empty_lbl_1 = new JLabel("              ");
		GridBagConstraints gbc_empty_lbl_1 = new GridBagConstraints();
		gbc_empty_lbl_1.insets = new Insets(0, 0, 5, 5);
		gbc_empty_lbl_1.gridx = 0;
		gbc_empty_lbl_1.gridy = 0;
		add(empty_lbl_1, gbc_empty_lbl_1);
		
		JLabel lbl_title_1 = new JLabel("    Parameters:");
		lbl_title_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbl_title_1.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lbl_title_1 = new GridBagConstraints();
		gbc_lbl_title_1.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_title_1.anchor = GridBagConstraints.WEST;
		gbc_lbl_title_1.gridx = 1;
		gbc_lbl_title_1.gridy = 1;
		add(lbl_title_1, gbc_lbl_title_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLACK);
		separator_1.setForeground(Color.BLACK);
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.gridwidth = 2;
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.insets = new Insets(0, 0, 5, 440);
		gbc_separator_1.gridx = 1;
		gbc_separator_1.gridy = 2;
		add(separator_1, gbc_separator_1);
		
		JLabel lbl_paramaeters_1 = new JLabel("Network size (n x n):");
		lbl_paramaeters_1.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lbl_paramaeters_1 = new GridBagConstraints();
		gbc_lbl_paramaeters_1.anchor = GridBagConstraints.WEST;
		gbc_lbl_paramaeters_1.insets = new Insets(15, 0, 5, 5);
		gbc_lbl_paramaeters_1.gridx = 1;
		gbc_lbl_paramaeters_1.gridy = 3;
		add(lbl_paramaeters_1, gbc_lbl_paramaeters_1);
		
		JSpinner param_spinner_1 = new JSpinner();
		param_spinner_1.setModel(new SpinnerNumberModel(new Integer(2), new Integer(2), null, new Integer(1)));
		GridBagConstraints gbc_param_spinner_1 = new GridBagConstraints();
		gbc_param_spinner_1.fill = GridBagConstraints.VERTICAL;
		gbc_param_spinner_1.anchor = GridBagConstraints.WEST;
		gbc_param_spinner_1.insets = new Insets(15, 20, 5, 5);
		gbc_param_spinner_1.gridx = 2;
		gbc_param_spinner_1.gridy = 3;
		param_spinner_1.setPreferredSize(new Dimension(40, 23));
		add(param_spinner_1, gbc_param_spinner_1);
		
		JLabel lbl_parameters_2 = new JLabel("New packets in node (Pavr):");
		GridBagConstraints gbc_lbl_parameters_2 = new GridBagConstraints();
		gbc_lbl_parameters_2.anchor = GridBagConstraints.WEST;
		gbc_lbl_parameters_2.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_parameters_2.gridx = 1;
		gbc_lbl_parameters_2.gridy = 4;
		add(lbl_parameters_2, gbc_lbl_parameters_2);
		
		JSpinner param_spinner_2 = new JSpinner();
		param_spinner_2.setModel(new SpinnerNumberModel(new Integer(1), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_param_spinner_2 = new GridBagConstraints();
		gbc_param_spinner_2.anchor = GridBagConstraints.WEST;
		gbc_param_spinner_2.fill = GridBagConstraints.VERTICAL;
		gbc_param_spinner_2.insets = new Insets(0, 20, 5, 5);
		gbc_param_spinner_2.gridx = 2;
		gbc_param_spinner_2.gridy = 4;
		param_spinner_2.setPreferredSize(new Dimension(40, 23));
		add(param_spinner_2, gbc_param_spinner_2);
		
		JLabel lbl_parameters_3 = new JLabel("Node output buffer size (N):");
		GridBagConstraints gbc_lbl_parameters_3 = new GridBagConstraints();
		gbc_lbl_parameters_3.anchor = GridBagConstraints.WEST;
		gbc_lbl_parameters_3.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_parameters_3.gridx = 1;
		gbc_lbl_parameters_3.gridy = 5;
		add(lbl_parameters_3, gbc_lbl_parameters_3);
		
		JSpinner param_spinner_3 = new JSpinner();
		param_spinner_3.setModel(new SpinnerNumberModel(new Integer(7), new Integer(1), null, new Integer(1)));
		GridBagConstraints gbc_param_spinner_3 = new GridBagConstraints();
		gbc_param_spinner_3.fill = GridBagConstraints.VERTICAL;
		gbc_param_spinner_3.anchor = GridBagConstraints.WEST;
		gbc_param_spinner_3.insets = new Insets(0, 20, 5, 5);
		gbc_param_spinner_3.gridx = 2;
		gbc_param_spinner_3.gridy = 5;
		param_spinner_3.setPreferredSize(new Dimension(40, 23));
		add(param_spinner_3, gbc_param_spinner_3);
		
		JLabel lbl_parameters_4 = new JLabel("Packet time to live (TTL):");
		GridBagConstraints gbc_lbl_parameters_4 = new GridBagConstraints();
		gbc_lbl_parameters_4.anchor = GridBagConstraints.WEST;
		gbc_lbl_parameters_4.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_parameters_4.gridx = 1;
		gbc_lbl_parameters_4.gridy = 6;
		add(lbl_parameters_4, gbc_lbl_parameters_4);
		
		JSpinner param_spinner_4 = new JSpinner();
		param_spinner_4.setModel(new SpinnerNumberModel(new Integer(7), new Integer(1), null, new Integer(1)));
		GridBagConstraints gbc_param_spinner_4 = new GridBagConstraints();
		gbc_param_spinner_4.fill = GridBagConstraints.VERTICAL;
		gbc_param_spinner_4.anchor = GridBagConstraints.WEST;
		gbc_param_spinner_4.insets = new Insets(0, 20, 5, 5);
		gbc_param_spinner_4.gridx = 2;
		gbc_param_spinner_4.gridy = 6;
		param_spinner_4.setPreferredSize(new Dimension(40, 23));
		add(param_spinner_4, gbc_param_spinner_4);
		
		
		JLabel lbl_parameters_5 = new JLabel("Routing type:");
		GridBagConstraints gbc_lbl_parameters_5 = new GridBagConstraints();
		gbc_lbl_parameters_5.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_parameters_5.anchor = GridBagConstraints.WEST;
		gbc_lbl_parameters_5.gridx = 1;
		gbc_lbl_parameters_5.gridy = 7;
		add(lbl_parameters_5, gbc_lbl_parameters_5);
		
		JComboBox param_comboBox_1 = new JComboBox();
		GridBagConstraints gbc_param_comboBox_1 = new GridBagConstraints();
		gbc_param_comboBox_1.anchor = GridBagConstraints.WEST;
		gbc_param_comboBox_1.insets = new Insets(0, 20, 5, 5);
		gbc_param_comboBox_1.fill = GridBagConstraints.VERTICAL;
		gbc_param_comboBox_1.gridx = 2;
		gbc_param_comboBox_1.gridy = 7;
		param_comboBox_1.setSize(100, 20);
		param_comboBox_1.setPreferredSize(new Dimension(100, 22));
		add(param_comboBox_1, gbc_param_comboBox_1);
		
		Panel panel = new Panel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(15, 0, 5, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 9;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0};
		gbl_panel.rowHeights = new int[]{0};
		gbl_panel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		ImageIcon icon1 = new ImageIcon("res/play.png","Play");
		Image img1 = icon1.getImage();  
		Image newimg1 = img1.getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH ) ;  
		icon1 = new ImageIcon(newimg1);
		JLabel lbl_play = new JLabel(icon1);
		GridBagConstraints gbc_lbl_play = new GridBagConstraints();
		gbc_lbl_play.insets = new Insets(5, 0, 5, 5);
		gbc_lbl_play.gridx = 1;
		gbc_lbl_play.gridy = 1;
		panel.add(lbl_play, gbc_lbl_play);
		
		ImageIcon icon2 = new ImageIcon("res/pause.png","Play");
		Image img2 = icon2.getImage();  
		Image newimg2 = img2.getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH ) ;  
		icon2 = new ImageIcon(newimg2);
		JLabel lbl_pause = new JLabel(icon2);
		GridBagConstraints gbc_lbl_pause = new GridBagConstraints();
		gbc_lbl_pause.insets = new Insets(5, 0, 5, 5);
		gbc_lbl_pause.gridx = 2;
		gbc_lbl_pause.gridy = 1;
		panel.add(lbl_pause, gbc_lbl_pause);
		
		ImageIcon icon3 = new ImageIcon("res/stop.png","Play");
		Image img3 = icon3.getImage();  
		Image newimg3 = img3.getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH ) ;  
		icon3 = new ImageIcon(newimg3);
		JLabel lbl_stop = new JLabel(icon3);
		GridBagConstraints gbc_lbl_stop = new GridBagConstraints();
		gbc_lbl_stop.insets = new Insets(5, 0, 5, 5);
		gbc_lbl_stop.gridx = 3;
		gbc_lbl_stop.gridy = 1;
		panel.add(lbl_stop, gbc_lbl_stop);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(Color.BLACK);
		separator_2.setForeground(Color.BLACK);
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.gridwidth = 2;
		gbc_separator_2.fill = GridBagConstraints.BOTH;
		gbc_separator_2.insets = new Insets(0, 0, 5, 440);
		gbc_separator_2.gridx = 1;
		gbc_separator_2.gridy = 13;
		add(separator_2, gbc_separator_2);
		
		JLabel lbl_title_2 = new JLabel("    Results:");
		lbl_title_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_lbl_title_2 = new GridBagConstraints();
		gbc_lbl_title_2.anchor = GridBagConstraints.WEST;
		gbc_lbl_title_2.insets = new Insets(20, 0, 5, 5);
		gbc_lbl_title_2.gridx = 1;
		gbc_lbl_title_2.gridy = 14;
		add(lbl_title_2, gbc_lbl_title_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(Color.BLACK);
		separator_3.setForeground(Color.BLACK);
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.fill = GridBagConstraints.BOTH;
		gbc_separator_3.gridwidth = 2;
		gbc_separator_3.insets = new Insets(0, 0, 5, 440);
		gbc_separator_3.gridx = 1;
		gbc_separator_3.gridy = 15;
		add(separator_3, gbc_separator_3);
		
		JLabel lbl_results_1 = new JLabel("Delay (avr):");
		GridBagConstraints gbc_lbl_results_1 = new GridBagConstraints();
		gbc_lbl_results_1.anchor = GridBagConstraints.WEST;
		gbc_lbl_results_1.insets = new Insets(15, 0, 5, 5);
		gbc_lbl_results_1.gridx = 1;
		gbc_lbl_results_1.gridy = 16;
		add(lbl_results_1, gbc_lbl_results_1);
		
		JLabel lbl_results_11 = new JLabel("21");
		GridBagConstraints gbc_lbl_results_11 = new GridBagConstraints();
		gbc_lbl_results_11.anchor = GridBagConstraints.WEST;
		gbc_lbl_results_11.insets = new Insets(15, 20, 5, 5);
		gbc_lbl_results_11.gridx = 2;
		gbc_lbl_results_11.gridy = 16;
		add(lbl_results_11, gbc_lbl_results_11);
		
		JLabel lbl_results_2 = new JLabel("Delay variance:");
		GridBagConstraints gbc_lbl_results_2 = new GridBagConstraints();
		gbc_lbl_results_2.anchor = GridBagConstraints.WEST;
		gbc_lbl_results_2.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_results_2.gridx = 1;
		gbc_lbl_results_2.gridy = 17;
		add(lbl_results_2, gbc_lbl_results_2);
		
		JLabel lbl_results_21 = new JLabel("6");
		GridBagConstraints gbc_lbl_results_21 = new GridBagConstraints();
		gbc_lbl_results_21.anchor = GridBagConstraints.WEST;
		gbc_lbl_results_21.insets = new Insets(0, 20, 5, 5);
		gbc_lbl_results_21.gridx = 2;
		gbc_lbl_results_21.gridy = 17;
		add(lbl_results_21, gbc_lbl_results_21);
		
		JLabel lbl_results_3 = new JLabel("Lost packets:");
		GridBagConstraints gbc_lbl_results_3 = new GridBagConstraints();
		gbc_lbl_results_3.anchor = GridBagConstraints.WEST;
		gbc_lbl_results_3.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_results_3.gridx = 1;
		gbc_lbl_results_3.gridy = 18;
		add(lbl_results_3, gbc_lbl_results_3);
		
		JLabel lbl_results_31 = new JLabel("126");
		GridBagConstraints gbc_lbl_results_31 = new GridBagConstraints();
		gbc_lbl_results_31.anchor = GridBagConstraints.WEST;
		gbc_lbl_results_31.insets = new Insets(0, 20, 5, 5);
		gbc_lbl_results_31.gridx = 2;
		gbc_lbl_results_31.gridy = 18;
		add(lbl_results_31, gbc_lbl_results_31);
		
		JLabel lbl_results_4 = new JLabel("Hops (avr):");
		GridBagConstraints gbc_lbl_results_4 = new GridBagConstraints();
		gbc_lbl_results_4.anchor = GridBagConstraints.WEST;
		gbc_lbl_results_4.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_results_4.gridx = 1;
		gbc_lbl_results_4.gridy = 19;
		add(lbl_results_4, gbc_lbl_results_4);
		
		JLabel label_2 = new JLabel("4");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.WEST;
		gbc_label_2.insets = new Insets(0, 20, 0, 5);
		gbc_label_2.gridx = 2;
		gbc_label_2.gridy = 19;
		add(label_2, gbc_label_2);
		

	}

}
