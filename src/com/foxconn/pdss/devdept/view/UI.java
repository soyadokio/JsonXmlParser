package com.foxconn.pdss.devdept.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import com.foxconn.pdss.devdept.util.JsonValidator;
import com.foxconn.pdss.devdept.util.XmlValidator;

public class UI extends JFrame {
	private JTextArea txtIO;//JSON的輸入框
	private JTextArea txtIO2;//xml的輸入框
	private JButton btnVlidateJson, btnEmptyJson, btnVlidateXml, btnEmptyXml;
	
	JPanel panelControl;
	
	public static void main(String[] args) {
		new UI();
	}
	
	public UI() {
		ImageIcon iconJson= new ImageIcon(UI.class.getResource("/images/json.png"));
		ImageIcon iconXml= new ImageIcon(UI.class.getResource("/images/xml.png"));

		JTabbedPane tabCate= new JTabbedPane(JTabbedPane.TOP);
		tabCate.setUI(new SpacedTabbedPaneUI());

		JPanel panelTabJson= getJsonGUI();
		JPanel panelTabXml= getXmlGUI();
		
		addBtnListener();
		
		tabCate.addTab("JSON", iconJson, panelTabJson, "JSON Validation");
		tabCate.addTab("XML", iconXml, panelTabXml, "XML Validation");
		tabCate.setSelectedIndex(0);
		
		setContentPane(tabCate);
		setTitle("JSON&XML Parser v20170116");
		setSize(1080, 720);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private JPanel getJsonGUI() {
		JPanel panelTitle= new JPanel(new GridLayout(1, 1));
		panelTitle.setBounds(34, 20, 1000, 60);//由於在Tab內，總width只有約1068px
		JTextField txtTitle= new JTextField("JSON Parser");
		txtTitle.setEditable(false);
		txtTitle.setBackground(Color.WHITE);
		txtTitle.setFont(new Font("Monospaced", Font.BOLD, 48));
		txtTitle.setBorder(null);
		panelTitle.add(txtTitle);
		
		JPanel panelIO= new JPanel(new GridLayout(1, 1));
		panelIO.setBounds(34, 90, 1000, 380);//由於在Tab內，總width只有約1068px
		txtIO= new JTextArea();
		txtIO.setLineWrap(true);//設置自動換行
		txtIO.setWrapStyleWord(true);//設置斷行不斷字
		txtIO.setBackground(new Color(215, 230, 195));
		txtIO.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtIO.setTabSize(4);
		txtIO.setCaretPosition(txtIO.getText().length());//定位焦點使光標定位到最新位置
		JScrollPane scroll= new JScrollPane(txtIO);
		scroll.setPreferredSize(new Dimension(980, 300));
		panelIO.add(scroll);
		
		JPanel panelControl= new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelControl.setBackground(Color.WHITE);
		panelControl.setBorder(null);
		panelControl.setBounds(34, 485, 1000, 60);//由於在Tab內，總width只有約1068px
		btnVlidateJson= new JButton("Validate");
		btnVlidateJson.setForeground(Color.WHITE);
		btnVlidateJson.setFont(new Font("Monospaced", Font.BOLD, 18));
		btnVlidateJson.setPreferredSize(new Dimension(180, 48));
		btnVlidateJson.setBackground(new Color(51 ,122 ,183));
		panelControl.add(btnVlidateJson);
		btnEmptyJson= new JButton("Empty");
		btnEmptyJson.setForeground(Color.WHITE);
		btnEmptyJson.setFont(new Font("Monospaced", Font.BOLD, 18));
		btnEmptyJson.setPreferredSize(new Dimension(90, 48));
		btnEmptyJson.setBackground(new Color(217 ,83 ,79));
		panelControl.add(btnEmptyJson);

		JPanel panelTips= new JPanel(new GridLayout(4, 1));
		panelTips.setBackground(Color.WHITE);
		panelTips.setBounds(34, 555, 1000, 90);//由於在Tab內，總width只有約1068px
		JLabel labTips= new JLabel("Tips 如下原因會造成JSON校驗失敗，而且會讓你不知道為什麼失敗");
		labTips.setFont(new Font("Default", Font.PLAIN, 12));
		panelTips.add(labTips);
		labTips= new JLabel("1.JSON字符串里的非數字型鍵值沒有雙引號");
		labTips.setFont(new Font("Default", Font.PLAIN, 12));
		panelTips.add(labTips);
		labTips= new JLabel("2.JSON中存在\\t這樣的製表符，看起來和空格一樣，但是就是因為它的存在校驗不通過，去掉就能過了");
		labTips.setFont(new Font("Default", Font.PLAIN, 12));
		panelTips.add(labTips);
		labTips= new JLabel("3.編輯器有BOM頭（MS自帶的NOTEPAD.EXE就有這個多餘而且煩人的功能）");
		labTips.setFont(new Font("Default", Font.PLAIN, 12));
		panelTips.add(labTips);
		
		JPanel panelTab= new JPanel(new GridLayout(4, 1));
		panelTab.setLayout(null);
		panelTab.setBackground(Color.WHITE);
		panelTab.add(panelTitle);
		panelTab.add(panelIO);
		panelTab.add(panelControl);
		panelTab.add(panelTips);
		
		return panelTab;
	}
	
	private JPanel getXmlGUI() {
		JPanel panelTitle= new JPanel(new GridLayout(1, 1));
		panelTitle.setBounds(34, 20, 1000, 60);//由於在Tab內，總width只有約1068px
		JTextField txtTitle= new JTextField("XML Parser");
		txtTitle.setEditable(false);
		txtTitle.setBackground(Color.WHITE);
		txtTitle.setFont(new Font("Monospaced", Font.BOLD, 48));
		txtTitle.setBorder(null);
		panelTitle.add(txtTitle);
		
		JPanel panelIO= new JPanel(new GridLayout(1, 1));
		panelIO.setBounds(34, 90, 1000, 380);//由於在Tab內，總width只有約1068px
		txtIO2= new JTextArea();
		txtIO2.setLineWrap(true);//設置自動換行
		txtIO2.setWrapStyleWord(true);//設置斷行不斷字
		txtIO2.setBackground(new Color(215, 230, 195));
		txtIO2.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtIO2.setTabSize(4);
		txtIO2.setCaretPosition(txtIO2.getText().length());//定位焦點使光標定位到最新位置
		JScrollPane scroll= new JScrollPane(txtIO2);
		scroll.setPreferredSize(new Dimension(980, 300));
		panelIO.add(scroll);
		
		JPanel panelControl= new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelControl.setBackground(Color.WHITE);
		panelControl.setBorder(null);
		panelControl.setBounds(34, 485, 1000, 60);//由於在Tab內，總width只有約1068px
		btnVlidateXml= new JButton("Validate");
		btnVlidateXml.setForeground(Color.WHITE);
		btnVlidateXml.setFont(new Font("Monospaced", Font.BOLD, 18));
		btnVlidateXml.setPreferredSize(new Dimension(180, 48));
		btnVlidateXml.setBackground(new Color(51 ,122 ,183));
		panelControl.add(btnVlidateXml);
		btnEmptyXml= new JButton("Empty");
		btnEmptyXml.setForeground(Color.WHITE);
		btnEmptyXml.setFont(new Font("Monospaced", Font.BOLD, 18));
		btnEmptyXml.setPreferredSize(new Dimension(90, 48));
		btnEmptyXml.setBackground(new Color(217 ,83 ,79));
		panelControl.add(btnEmptyXml);

		JPanel panelTips= new JPanel(new GridLayout(4, 1));
		panelTips.setBackground(Color.WHITE);
		panelTips.setBounds(34, 555, 1000, 90);//由於在Tab內，總width只有約1068px
		JLabel labTips= new JLabel("Tips");
		labTips.setFont(new Font("Default", Font.PLAIN, 12));
		panelTips.add(labTips);
		labTips= new JLabel("1.暫時留空");
		labTips.setFont(new Font("Default", Font.PLAIN, 12));
		panelTips.add(labTips);
		labTips= new JLabel("2.暫時留空");
		labTips.setFont(new Font("Default", Font.PLAIN, 12));
		panelTips.add(labTips);
		labTips= new JLabel("3.暫時留空");
		labTips.setFont(new Font("Default", Font.PLAIN, 12));
		panelTips.add(labTips);
		
		JPanel panelTab= new JPanel(new GridLayout(4, 1));
		panelTab.setLayout(null);
		panelTab.setBackground(Color.WHITE);
		panelTab.add(panelTitle);
		panelTab.add(panelIO);
		panelTab.add(panelControl);
		panelTab.add(panelTips);
		
		return panelTab;
	}
	
	private void addBtnListener() {
		btnVlidateJson.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				String input= txtIO.getText().replaceAll(" ", "");
				if(!"".equals(input)) {
					input= input.replaceAll("\b", "").replaceAll("\f", "").replaceAll("\n", "").replaceAll("\r", "").replaceAll("\t", "");
					if(JsonValidator.isJson(input)) {
						txtIO.setText(JsonValidator.format(input));
						txtIO.setBorder(new LineBorder(Color.GREEN, 1));
					} else {
						txtIO.setBorder(new LineBorder(Color.RED, 1));
					}
				}
			}
		});
		btnEmptyJson.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				txtIO.setText(null);
				txtIO.setBorder(null);
			}
		});
		btnVlidateXml.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				String input= txtIO2.getText();
				if(!"".equals(input)) {
					if(XmlValidator.isXml(input)) {
						txtIO2.setText(XmlValidator.format(input));
						txtIO2.setBorder(new LineBorder(Color.GREEN, 1));
					} else {
						txtIO2.setBorder(new LineBorder(Color.RED, 1));
					}
				}
			}
		});
		btnEmptyXml.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				txtIO2.setText(null);
				txtIO2.setBorder(null);
			}
		});
	}
	
}

class SpacedTabbedPaneUI extends BasicTabbedPaneUI {
	@Override
	protected LayoutManager createLayoutManager() {
		return new BasicTabbedPaneUI.TabbedPaneLayout() {
			@Override
			protected void calculateTabRects(int tabPlacement, int tabCount) {
				final int spacer= 10;
				final int indent= 4;
				super.calculateTabRects(tabPlacement, tabCount);
				for(int i= 0; i< rects.length; i++) {
					rects[i].x+= i* spacer+ indent;
				}
			}
		};
	}
}
