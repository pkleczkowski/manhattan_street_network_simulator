package org.msn.visual;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import org.msn.MSNmain;
import org.msn.Network;
import org.msn.Parameters;
import org.msn.Results;
import org.msn.Routing;

public class MSNpanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7052427088447626849L;
	private MSNmain mainFrame;
	private JSpinner param_spinner_1;
	private JSpinner param_spinner_2;
	private JSpinner param_spinner_3;
	private JSpinner param_spinner_4;
	private JComboBox<String> param_comboBox_1;
	private JButton playButton;
	private JButton stopButton;
	private JButton pauseButton;
	private JLabel lbl_results_11;
	private JLabel lbl_results_21;
	private JLabel lbl_results_31;
	private JLabel lbl_results_32;
	private JLabel lbl_results_41;
	
	private Results results;
	private Parameters parameters;
	public Network network;
	private Thread networkThread;
	private Routing routing;
	protected static final String OUTPUT_FILE = "res/output.csv";
	
	private Vector<Integer> delayArray;
	/**
	 * Create the panel.
	 */
	public MSNpanel(MSNmain mainFrame) {
		this();
		this.mainFrame = mainFrame;
		this.results = new Results(this);
		this.parameters = new Parameters();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	public MSNpanel() {
		setPreferredSize(new Dimension(500, 650));
		setVisible(true);
		removeAll();
		repaint();
		revalidate();
		
		File yourFile = new File(OUTPUT_FILE);
		yourFile.delete();
		FileWriter fw;
		try {
			fw = new FileWriter(OUTPUT_FILE, true);
			fw.append("Network Size [N];New Packets Avr Delay [Pavr];Bufor Size [L];Time to Live [TTL];Routing Type;Delay;Delay Var;Received Packets;Lost Packets;Hops Avr");
			fw.append(System.getProperty("line.separator"));
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		gbc_separator_1.gridwidth = 3;
		gbc_separator_1.fill = GridBagConstraints.BOTH;
		gbc_separator_1.insets = new Insets(0, 0, 5, 40);
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
		
		param_spinner_1 = new JSpinner();
		param_spinner_1.setModel(new SpinnerNumberModel(new Integer(2), new Integer(2), null, new Integer(1)));
		GridBagConstraints gbc_param_spinner_1 = new GridBagConstraints();
		gbc_param_spinner_1.gridwidth = 2;
		gbc_param_spinner_1.fill = GridBagConstraints.VERTICAL;
		gbc_param_spinner_1.anchor = GridBagConstraints.WEST;
		gbc_param_spinner_1.insets = new Insets(15, 20, 5, 5);
		gbc_param_spinner_1.gridx = 2;
		gbc_param_spinner_1.gridy = 3;
		param_spinner_1.setPreferredSize(new Dimension(40, 23));
		add(param_spinner_1, gbc_param_spinner_1);
		
		JLabel lbl_parameters_2 = new JLabel("New packets delay in node (Pavr):");
		GridBagConstraints gbc_lbl_parameters_2 = new GridBagConstraints();
		gbc_lbl_parameters_2.anchor = GridBagConstraints.WEST;
		gbc_lbl_parameters_2.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_parameters_2.gridx = 1;
		gbc_lbl_parameters_2.gridy = 4;
		add(lbl_parameters_2, gbc_lbl_parameters_2);
		
		param_spinner_2 = new JSpinner();
		param_spinner_2.setModel(new SpinnerNumberModel(new Integer(7), new Integer(1), null, new Integer(1)));
		GridBagConstraints gbc_param_spinner_2 = new GridBagConstraints();
		gbc_param_spinner_2.gridwidth = 2;
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
		
		param_spinner_3 = new JSpinner();
		param_spinner_3.setModel(new SpinnerNumberModel(new Integer(4), new Integer(1), null, new Integer(1)));
		GridBagConstraints gbc_param_spinner_3 = new GridBagConstraints();
		gbc_param_spinner_3.gridwidth = 2;
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
		
		param_spinner_4 = new JSpinner();
		param_spinner_4.setModel(new SpinnerNumberModel(new Integer(14), new Integer(1), null, new Integer(1)));
		GridBagConstraints gbc_param_spinner_4 = new GridBagConstraints();
		gbc_param_spinner_4.gridwidth = 2;
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
		
		param_comboBox_1 = new JComboBox();
		GridBagConstraints gbc_param_comboBox_1 = new GridBagConstraints();
		gbc_param_comboBox_1.gridwidth = 2;
		gbc_param_comboBox_1.anchor = GridBagConstraints.WEST;
		gbc_param_comboBox_1.insets = new Insets(0, 20, 5, 5);
		gbc_param_comboBox_1.fill = GridBagConstraints.VERTICAL;
		gbc_param_comboBox_1.gridx = 2;
		gbc_param_comboBox_1.gridy = 7;
		param_comboBox_1.setPreferredSize(new Dimension(120, 22));
		param_comboBox_1.setSize(120,22);
		add(param_comboBox_1, gbc_param_comboBox_1);
		
		for (int i=0; i < Routing.getRoutings().length; i++)
		{
			param_comboBox_1.addItem(Routing.getRoutings()[i]);
		}
		param_comboBox_1.setSelectedIndex(0);
		
		
		
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
		//icon1 = new ImageIcon(newimg1);
		//JLabel lbl_play = new JLabel(icon1);
		playButton = new JButton(new ImageIcon(newimg1));
		playButton.setContentAreaFilled(false);
		playButton.setBorderPainted(false);
		GridBagConstraints gbc_lbl_play = new GridBagConstraints();
		gbc_lbl_play.insets = new Insets(5, 0, 5, 0);
		gbc_lbl_play.gridx = 1;
		gbc_lbl_play.gridy = 1;
		panel.add(playButton, gbc_lbl_play);
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				play();
			}
		});
		
		ImageIcon icon2 = new ImageIcon("res/pause.png","Play");
		Image img2 = icon2.getImage();  
		Image newimg2 = img2.getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH ) ;  
		pauseButton  = new JButton(new ImageIcon(newimg2));
		pauseButton.setContentAreaFilled(false);
		pauseButton.setBorderPainted(false);
		GridBagConstraints gbc_lbl_pause = new GridBagConstraints();
		gbc_lbl_pause.insets = new Insets(5, 0, 5, 0);
		gbc_lbl_pause.gridx = 2;
		gbc_lbl_pause.gridy = 1;
		panel.add(pauseButton, gbc_lbl_pause);
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pause();
			}
		});

		ImageIcon icon3 = new ImageIcon("res/stop.png","Stop");
		Image img3 = icon3.getImage();  
		Image newimg3 = img3.getScaledInstance(30,30,java.awt.Image.SCALE_SMOOTH ) ;  
		stopButton  = new JButton(new ImageIcon(newimg3));
		stopButton.setContentAreaFilled(false);
		stopButton.setBorderPainted(false);
		GridBagConstraints gbc_lbl_stop = new GridBagConstraints();
		gbc_lbl_stop.insets = new Insets(5, 0, 5, 0);
		gbc_lbl_stop.gridx = 3;
		gbc_lbl_stop.gridy = 1;
		panel.add(stopButton, gbc_lbl_stop);
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stop();
			}
		});
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(Color.BLACK);
		separator_2.setForeground(Color.BLACK);
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.gridwidth = 3;
		gbc_separator_2.fill = GridBagConstraints.BOTH;
		gbc_separator_2.insets = new Insets(0, 0, 5, 40);
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
		gbc_separator_3.gridwidth = 3;
		gbc_separator_3.insets = new Insets(0, 0, 5, 40);
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
		
		lbl_results_11 = new JLabel("0");
		GridBagConstraints gbc_lbl_results_11 = new GridBagConstraints();
		gbc_lbl_results_11.gridwidth = 2;
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
		
		lbl_results_21 = new JLabel("0");
		GridBagConstraints gbc_lbl_results_21 = new GridBagConstraints();
		gbc_lbl_results_21.gridwidth = 2;
		gbc_lbl_results_21.anchor = GridBagConstraints.WEST;
		gbc_lbl_results_21.insets = new Insets(0, 20, 5, 5);
		gbc_lbl_results_21.gridx = 2;
		gbc_lbl_results_21.gridy = 17;
		add(lbl_results_21, gbc_lbl_results_21);
		
		JLabel lblReceivedPackets = new JLabel("Received packets:");
		GridBagConstraints gbc_lblReceivedPackets = new GridBagConstraints();
		gbc_lblReceivedPackets.anchor = GridBagConstraints.WEST;
		gbc_lblReceivedPackets.insets = new Insets(0, 0, 5, 5);
		gbc_lblReceivedPackets.gridx = 1;
		gbc_lblReceivedPackets.gridy = 18;
		add(lblReceivedPackets, gbc_lblReceivedPackets);
		
		lbl_results_32 = new JLabel("0");
		GridBagConstraints gbc_lbl_results_32 = new GridBagConstraints();
		gbc_lbl_results_32.gridwidth = 2;
		gbc_lbl_results_32.anchor = GridBagConstraints.WEST;
		gbc_lbl_results_32.insets = new Insets(0, 20, 5, 5);
		gbc_lbl_results_32.gridx = 2;
		gbc_lbl_results_32.gridy = 18;
		add(lbl_results_32, gbc_lbl_results_32);
		
		JLabel lbl_results_3 = new JLabel("Lost packets:");
		GridBagConstraints gbc_lbl_results_3 = new GridBagConstraints();
		gbc_lbl_results_3.anchor = GridBagConstraints.WEST;
		gbc_lbl_results_3.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_results_3.gridx = 1;
		gbc_lbl_results_3.gridy = 19;
		add(lbl_results_3, gbc_lbl_results_3);
		
		lbl_results_31 = new JLabel("0");
		GridBagConstraints gbc_lbl_results_31 = new GridBagConstraints();
		gbc_lbl_results_31.gridwidth = 2;
		gbc_lbl_results_31.anchor = GridBagConstraints.WEST;
		gbc_lbl_results_31.insets = new Insets(0, 20, 5, 5);
		gbc_lbl_results_31.gridx = 2;
		gbc_lbl_results_31.gridy = 19;
		add(lbl_results_31, gbc_lbl_results_31);
		
		JLabel lbl_results_4 = new JLabel("Hops (avr):");
		GridBagConstraints gbc_lbl_results_4 = new GridBagConstraints();
		gbc_lbl_results_4.anchor = GridBagConstraints.WEST;
		gbc_lbl_results_4.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_results_4.gridx = 1;
		gbc_lbl_results_4.gridy = 20;
		add(lbl_results_4, gbc_lbl_results_4);
		
		lbl_results_41 = new JLabel("0");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.gridwidth = 2;
		gbc_label_2.anchor = GridBagConstraints.WEST;
		gbc_label_2.insets = new Insets(0, 20, 0, 5);
		gbc_label_2.gridx = 2;
		gbc_label_2.gridy = 20;
		add(lbl_results_41, gbc_label_2);
		
		delayArray= new Vector<Integer>();

	}
	
	public void play() 
	{
		this.param_spinner_1.setEnabled(false);
		this.param_spinner_2.setEnabled(false);
		this.param_spinner_3.setEnabled(false);
		this.param_spinner_4.setEnabled(false);
		this.param_comboBox_1.setEnabled(false);
		this.parameters = new Parameters(this.getParam_spinner_1(),
				this.getParam_spinner_2(),
				this.getParam_spinner_3(),
				this.getParam_spinner_4(),
				this.getParam_comboBox_1().getSelectedItem().toString());
		if (networkThread==null) {
			this.lbl_results_11.setText("0");
			this.lbl_results_21.setText("0");
			this.lbl_results_31.setText("0");
			this.lbl_results_32.setText("0");
			this.lbl_results_41.setText("0");
			delayArray= new Vector<Integer>();
			network = new Network(parameters,this);
			network.setWorking(true);
			networkThread = new Thread(network);
			networkThread.start();
			
			//part responsible for drawing
			this.mainFrame.graphPanel.setNetSize(parameters.getNetSize());
			this.mainFrame.graphPanel.setBufferSize(parameters.getBufferOut());
		}
		else {
			network.setWorking(true);
			network.updateParameters(parameters); //TODO
		}
		
	}
	
	public void pause() 
	{
		this.param_spinner_2.setEnabled(true);
		this.param_spinner_3.setEnabled(true);
		this.param_spinner_4.setEnabled(true);
		this.param_comboBox_1.setEnabled(true);
		network.setWorking(false);
	}
	
	public void stop()
	{
		//save outputs and parameters in file
		FileWriter fw;
		try {
			fw = new FileWriter(OUTPUT_FILE, true);
					fw.append(this.parameters.getNetSize() + ";");
					fw.append(this.parameters.getNewPkt() + ";");
					fw.append(this.parameters.getBufferOut() + ";");
					fw.append(this.parameters.getTtl() + ";");
					fw.append(this.parameters.getRoutingType() + ";");
					fw.append(this.lbl_results_11.getText() + ";");
					fw.append(this.lbl_results_21.getText() + ";");
					fw.append(this.lbl_results_32.getText() + ";");
					String[] lost = this.lbl_results_31.getText().split(" ");
					int lostP = Integer.parseInt(lost[0]);
					fw.append(""+lostP + ";");
					fw.append(this.lbl_results_41.getText() + ";");
					fw.append(System.getProperty("line.separator"));
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.param_spinner_1.setEnabled(true);
		this.param_spinner_2.setEnabled(true);
		this.param_spinner_3.setEnabled(true);
		this.param_spinner_4.setEnabled(true);
		this.param_comboBox_1.setEnabled(true);
		
		if (networkThread!=null) {
			networkThread.stop();
			networkThread = null;
		}
		
	}
	
	
	
	
	
	
	
	
	
	public JComboBox getParam_comboBox_1() {
		return param_comboBox_1;
	}

	public void setParam_comboBox_1(JComboBox param_comboBox_1) {
		this.param_comboBox_1 = param_comboBox_1;
	}

	public int getParam_spinner_1() {
		return ((Integer) param_spinner_1.getValue()).intValue();
	}

	public int getParam_spinner_2() {
		return ((Integer) param_spinner_2.getValue()).intValue();
	}

	public int getParam_spinner_3() {
		return ((Integer) param_spinner_3.getValue()).intValue();
	}

	public int getParam_spinner_4() {
		return ((Integer) param_spinner_4.getValue()).intValue();
	}

	public void setLbl_results_11(String text) {
		this.lbl_results_11.setText(text);
	}

	public void setLbl_results_21(String text) {
		this.lbl_results_21.setText(text);
	}
	
	public void setLbl_results_31(String text) {
		this.lbl_results_31.setText(text);
	}
	
	public void setLbl_results_41(String text) {
		this.lbl_results_41.setText(text);
	}

	public void updateReceived(int receivedPacketsCount, int hopsCount, int delay) {
		double hopsCountAvr = ((receivedPacketsCount-1)*(Double.parseDouble(this.lbl_results_41.getText())) 
				+ (double) hopsCount) / receivedPacketsCount;
		int r = (int)(hopsCountAvr * 100);
		double f = r / 100.0;
		this.lbl_results_41.setText("" + f);
		
		this.lbl_results_32.setText(""+receivedPacketsCount);
		
		double delayAvr = ((receivedPacketsCount-1)*(Double.parseDouble(this.lbl_results_11.getText())) 
				+ (double) delay) / receivedPacketsCount;
		int q = (int)(delayAvr * 100);
		double g = q / 100.0;
		this.lbl_results_11.setText("" + g);
		

		delayArray.add(Integer.valueOf(delay));
		double variance = calculateVariance(delayAvr);
		this.lbl_results_21.setText(""+variance);
		
		String[] lost = this.lbl_results_31.getText().split(" ");
		double lostP = Double.parseDouble(lost[0]);
		double percent = (lostP*100/(receivedPacketsCount+lostP));
		int tmpLost = (int)(percent * 100);
		percent = tmpLost / 100.0;
		//double tmp = percent/100.0;
		this.lbl_results_31.setText("" +(int)lostP + " (" + percent + "%)");
	}

	private double calculateVariance(double delayAvr) {
		
		double numerator = 0;
		int denominator= delayArray.size();
		Iterator<Integer> iter = delayArray.iterator();
		while(iter.hasNext())
			numerator+=Math.pow((iter.next()-delayAvr), 2);
		double tmp = (numerator/denominator);
		int q = (int)(tmp * 100);
		double g = q / 100.0;
		return g;
	}

	public void updateLost(int i) {
		String[] lost = this.lbl_results_31.getText().split(" ");
		double lostP = Double.parseDouble(lost[0]) + 1.00;
		double percent = (lostP*100/(Integer.parseInt(this.lbl_results_32.getText())+lostP));
		int tmpLost = (int)(percent * 100);
		percent = tmpLost / 100.0;
		//double tmp = percent/100.0;
		this.lbl_results_31.setText("" +(int)lostP + " (" + percent + "%)");
	}

}
