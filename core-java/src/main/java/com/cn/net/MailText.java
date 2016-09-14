/*
 * MailText.java
 *
 * Created on __DATE__, __TIME__
 */

package com.cn.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * 
 * @author __USER__
 */
public class MailText extends javax.swing.JFrame {

	/** Creates new form MailText */
	public MailText() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		to = new javax.swing.JLabel();
		from = new javax.swing.JLabel();
		server = new javax.swing.JLabel();
		toText = new javax.swing.JTextField();
		fromText = new javax.swing.JTextField();
		serverText = new javax.swing.JTextField();
		comm = new javax.swing.JScrollPane();
		commArea = new javax.swing.JTextArea();
		send = new javax.swing.JButton();
		text = new javax.swing.JScrollPane();
		textArea = new javax.swing.JTextArea();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("MAILSERVER");
		setBackground(new java.awt.Color(0, 153, 255));
		setLocation(300, 300);

		to.setText("to");

		from.setText("from");

		server.setText("server");

		toText.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField1ActionPerformed(evt);
			}
		});

		commArea.setColumns(20);
		commArea.setRows(5);
		comm.setViewportView(commArea);

		send.setText("send");
		send.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				sendActionPerformed(evt);
			}
		});

		textArea.setColumns(20);
		textArea.setRows(5);
		text.setViewportView(textArea);

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(layout.createSequentialGroup()
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.TRAILING)
														.add(
																org.jdesktop.layout.GroupLayout.LEADING,
																layout
																		.createSequentialGroup()
																		.add(
																				106,
																				106,
																				106)
																		.add(
																				layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.LEADING)
																						.add(
																								layout
																										.createParallelGroup(
																												org.jdesktop.layout.GroupLayout.TRAILING,
																												false)
																										.add(
																												server,
																												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE)
																										.add(
																												to,
																												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE))
																						.add(
																								from))
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.TRAILING,
																								false)
																						.add(
																								fromText)
																						.add(
																								serverText)
																						.add(
																								org.jdesktop.layout.GroupLayout.LEADING,
																								toText,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																								116,
																								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
																		.add(
																				18,
																				18,
																				18)
																		.add(
																				send))
														.add(
																org.jdesktop.layout.GroupLayout.LEADING,
																layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.add(
																				layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.LEADING)
																						.add(
																								org.jdesktop.layout.GroupLayout.TRAILING,
																								text,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								355,
																								Short.MAX_VALUE)
																						.add(
																								comm,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								355,
																								Short.MAX_VALUE))))
										.add(35, 35, 35)));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								layout
										.createSequentialGroup()
										.add(38, 38, 38)
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(to)
														.add(
																toText,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(
																fromText,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
														.add(from))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(server)
														.add(
																serverText,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
														.add(send))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.UNRELATED)
										.add(
												comm,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.add(18, 18, 18)
										.add(
												text,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(29, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	// GEN-END:initComponents

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
	}

	private void sendActionPerformed(java.awt.event.ActionEvent evt) {
		new Thread(new Runnable() {
			public void run() {
				textArea.setText("");
				sendMail();
			}
		}).start();
	}

	public void sendMail() {
		try {
			Socket s = new Socket(serverText.getText(), 25);
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			sc = new Scanner(is);
			pw = new PrintWriter(os);
			String hostName = InetAddress.getLocalHost().getHostName();
			received();
			send("HELLO" + hostName);
			received();
			send("MAIL FROM :<" + fromText.getText() + ">");
			received();
			send("RCPT TO :<" + toText.getText() + ">");
			received();
			send("DATA");
			received();
			send(textArea.getText());
			send(".");
			received();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void send(String s) {
		commArea.append(s + "\n");
		pw.print(s.replace("\n", "\r\n"));
		pw.print("\r\n");
		pw.flush();
	}
	public void received() {
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			commArea.append(s);
			commArea.append("\n");
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MailText().setVisible(true);
			}
		});
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton send;
	private javax.swing.JLabel to;
	private javax.swing.JLabel from;
	private javax.swing.JLabel server;
	private javax.swing.JScrollPane text;
	private javax.swing.JScrollPane comm;
	private javax.swing.JTextArea textArea;
	private javax.swing.JTextArea commArea;
	private javax.swing.JTextField toText;
	private javax.swing.JTextField fromText;
	private javax.swing.JTextField serverText;
	private Scanner sc;
	private PrintWriter pw;
	// End of variables declaration//GEN-END:variables

}