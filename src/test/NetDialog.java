package test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NetDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField address;
	private Properties props;
	private JTextField port;

	/**
	 * Create the dialog.
	 */
	public NetDialog() {
		initFrame();
		initData();		
	}

	private void initFrame(){
		setResizable(false);
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		JLabel addressLabel = new JLabel("\u670D\u52A1\u5668\u5730\u5740");
		GridBagConstraints gbc_addressLabel = new GridBagConstraints();
		gbc_addressLabel.insets = new Insets(10, 5, 5, 5);
		gbc_addressLabel.anchor = GridBagConstraints.EAST;
		gbc_addressLabel.gridx = 0;
		gbc_addressLabel.gridy = 0;
		contentPanel.add(addressLabel, gbc_addressLabel);
	
	
		address = new JTextField();
		GridBagConstraints gbc_address = new GridBagConstraints();
		gbc_address.insets = new Insets(10, 0, 5, 20);
		gbc_address.fill = GridBagConstraints.HORIZONTAL;
		gbc_address.gridx = 1;
		gbc_address.gridy = 0;
		contentPanel.add(address, gbc_address);
		address.setColumns(30);
		
		JLabel port_label = new JLabel("\u7AEF\u53E3\u53F7");
		GridBagConstraints gbc_port_label = new GridBagConstraints();
		gbc_port_label.anchor = GridBagConstraints.EAST;
		gbc_port_label.insets = new Insets(10, 5, 5, 5);
		gbc_port_label.gridx = 0;
		gbc_port_label.gridy = 1;
		contentPanel.add(port_label, gbc_port_label);
		
		port = new JTextField();
		GridBagConstraints gbc_port = new GridBagConstraints();
		gbc_port.insets = new Insets(10, 0, 5, 20);
		gbc_port.fill = GridBagConstraints.HORIZONTAL;
		gbc_port.gridx = 1;
		gbc_port.gridy = 1;
		contentPanel.add(port, gbc_port);
		port.setColumns(30);
	
	
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("\u4FEE\u6539");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				props = new Properties();
				try {
					props.load(new FileInputStream("mysql.ini"));
					props.setProperty("url" , address.getText().trim());
					props.setProperty("port" , port.getText().trim());
					props.store(new FileOutputStream("mysql.ini"), "modify the data"); 
					JOptionPane.showMessageDialog(null, "修改成功", "成功", JOptionPane.INFORMATION_MESSAGE);
				} catch (FileNotFoundException e1) {
					System.out.println("找不到 mysql.ini 文件");
					JOptionPane.showMessageDialog(null, "找不到 mysql.ini 文件", "修改失败", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (IOException e1) {
					System.out.println("文件加载错误");
					JOptionPane.showMessageDialog(null, "打不开 mysql.ini 文件", "修改失败", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	
	
		JButton cancelButton = new JButton("\u53D6\u6D88");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
		setVisible(true);
	}
	
	
	private void initData(){
		props = new Properties();
		try {
			props.load(new FileInputStream("mysql.ini"));
			String url = props.getProperty("url");
			String pot = props.getProperty("port");
			address.setText(url);
			port.setText(pot);

		} catch (FileNotFoundException e) {
			System.out.println("找不到 mysql.ini 文件");
			JOptionPane.showMessageDialog(null, "找不到 mysql.ini 文件", "修改失败", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("文件加载错误");
			JOptionPane.showMessageDialog(null, "打不开 mysql.ini 文件", "修改失败", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
}
