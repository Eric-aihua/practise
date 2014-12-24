package com.eric.lession.csTest;

import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JList;

public class Client extends javax.swing.JFrame implements Runnable {

	public Client() {
		initComponents();
	}

	private void initComponents() {

		tipUrl = new javax.swing.JLabel();
		serverUrl = new javax.swing.JTextField();
		serverUrl.setText("127.0.0.1");
		conSer = new javax.swing.JButton();
		choiceFile = new ChoiceFile();
		starTest = new javax.swing.JButton();
		conResult = new javax.swing.JLabel();
		loadQues = new javax.swing.JButton();
		rightPanel = new javax.swing.JPanel();
		result = new javax.swing.JScrollPane();
		resultArea = new javax.swing.JTextArea();
		ques = new javax.swing.JScrollPane();
		quesArea = new javax.swing.JTextArea();
		aAn = new javax.swing.JCheckBox();
		bAn = new javax.swing.JCheckBox();
		cAn = new javax.swing.JCheckBox();
		dAn = new javax.swing.JCheckBox();
		commitAn = new javax.swing.JButton();
		commitAn.setEnabled(false);
		nextQue = new javax.swing.JButton();
		nextQue.setEnabled(false);
		querySco = new javax.swing.JButton();
		querySco.setEnabled(false);
		useTime = new javax.swing.JTextField();
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		tipUrl
				.setText("\u8f93\u5165\u8003\u8bd5\u670d\u52a1\u5668\u7684\u5730\u5740\uff1a");

		conSer.setText("\u8fde\u63a5\u670d\u52a1\u5668");
		conSer.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});
		starTest.setText("\u5f00\u59cb\u8003\u8bd5");
		starTest.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});
		
		commitAn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});
		querySco.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton5ActionPerformed(evt);
			}
		});
		conResult.setText("connect label");
		loadQues.setText("\u8f7d\u5165\u8bd5\u9898");

		nextQue.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		rightPanel.setBackground(new java.awt.Color(102, 153, 255));

		resultArea.setColumns(20);
		resultArea.setRows(5);
		result.setViewportView(resultArea);

		quesArea.setColumns(20);
		quesArea.setRows(5);
		ques.setViewportView(quesArea);

		aAn.setText("A");

		bAn.setText("B");

		cAn.setText("C");

		dAn.setText("D");

		commitAn.setText("提交答案");

		nextQue.setText("下一题");

		querySco.setText("查询分数");

		useTime.setText("你所用的时间为:");
		loadQues.setVisible(false);

		org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(
				rightPanel);
		rightPanel.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.add(aAn)
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(bAn)
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(cAn)
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(dAn).addContainerGap(75,
												Short.MAX_VALUE))
						.add(
								jPanel1Layout
										.createSequentialGroup()
										.add(51, 51, 51)
										.add(
												jPanel1Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING,
																false)
														.add(
																org.jdesktop.layout.GroupLayout.TRAILING,
																useTime)
														.add(
																org.jdesktop.layout.GroupLayout.TRAILING,
																jPanel1Layout
																		.createSequentialGroup()
																		.add(
																				commitAn)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				nextQue)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				querySco)))
										.addContainerGap(131, Short.MAX_VALUE))
						.add(
								jPanel1Layout
										.createSequentialGroup()
										.add(40, 40, 40)
										.add(
												jPanel1Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(
																org.jdesktop.layout.GroupLayout.TRAILING,
																result,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																335,
																Short.MAX_VALUE)
														.add(
																org.jdesktop.layout.GroupLayout.TRAILING,
																ques,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																335,
																Short.MAX_VALUE))
										.add(38, 38, 38)));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.add(
												result,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												71,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.add(18, 18, 18)
										.add(
												ques,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												80,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.add(43, 43, 43)
										.add(
												jPanel1Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(aAn).add(cAn).add(
																dAn).add(bAn))
										.add(18, 18, 18)
										.add(
												jPanel1Layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(commitAn).add(
																nextQue).add(
																querySco))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												useTime,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(20, Short.MAX_VALUE)));

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								layout
										.createSequentialGroup()
										.add(39, 39, 39)
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(
																layout
																		.createSequentialGroup()
																		.add(
																				conSer)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				starTest)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED))
														.add(
																layout
																		.createSequentialGroup()
																		.add(
																				serverUrl,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				172,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED))
														.add(
																layout
																		.createSequentialGroup()
																		.add(
																				tipUrl,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				172,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED))
														.add(
																layout
																		.createSequentialGroup()
																		.add(
																				layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.TRAILING)
																						.add(
																								loadQues)
																						.add(
																								choiceFile)
																						.add(
																								conResult))
																		.add(
																				86,
																				86,
																				86)))
										.add(18, 18, 18)
										.add(
												rightPanel,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.add(10, 10, 10)));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								layout
										.createSequentialGroup()
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(
																layout
																		.createSequentialGroup()
																		.add(
																				44,
																				44,
																				44)
																		.add(
																				tipUrl)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				serverUrl,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.BASELINE)
																						.add(
																								conSer)
																						.add(
																								starTest))
																		.add(
																				18,
																				18,
																				18)
																		.add(
																				conResult)
																		.add(
																				70,
																				70,
																				70)
																		.add(
																				choiceFile)
																		.add(
																				50,
																				50,
																				50)
																		.add(
																				loadQues))
														/*  */

														.add(
																layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.add(
																				rightPanel,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		setLocation(300, 300);
		setTitle("c/s考试系统");
		setResizable(false);
		thread=new Thread(this);
		pack();
	}// </editor-fold>

	// GEN-END:initComponents

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
	/* 开始考试 */
	    commitAn.setEnabled(true);
	    nextQue.setEnabled(true);
	    querySco.setEnabled(true);
	    System.out.println("jButton2ActionPerformed.startexam");
		thread.start();
		if(choiceFile.getSuccess()){
			quesArea.setText("你选择了试题："+choiceFile.getSelectedFileName());
		}
		String s;
		try {
			s = dis.readUTF();
			if(s.startsWith("考")){
				useTime.setText(useTime.getText()+""+s.substring(s.indexOf(":")+1));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
		

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		/*connect server*/
		String url = serverUrl.getText().trim();

		try {
			socket = new Socket(url, 0003);
		} catch (UnknownHostException e) {
			conResult.setText("抱歉，请从新输入考试系统的地址！");
			e.printStackTrace();
		} catch (IOException e) {
			conResult.setText("抱歉，请从新输入考试系统的地址！");
			e.printStackTrace();
		}
		if (socket != null) {
			try {
				is = socket.getInputStream();
				os = socket.getOutputStream();
				dis=new DataInputStream(is);
				dos=new DataOutputStream(os);
				
				choiceFile.setSocketConnection(socket,dis,dos);
			} catch (IOException e) {
				e.printStackTrace();
			}
			sc = new Scanner(is);
			pw = new PrintWriter(os, true);
			conResult.setText("祝贺你，考试系统登陆成功!");
		} else {
			conResult.setText("抱歉，请从新输入考试系统的地址！");
		}
		/* 获得连接 */
	}
/*提交答案*/
	private void jButton4ActionPerformed(ActionEvent evt) {
		String selfAnswer = "";
		if(aAn.isSelected()){
			selfAnswer="a";
		}
		if(bAn.isSelected()){
			selfAnswer="b";
		}
		if(cAn.isSelected()){
			selfAnswer="c";
		}
		if(dAn.isSelected()){
			selfAnswer="d";
		}
		try {
			dos.writeUTF("提交的答案:"+selfAnswer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*查询得分*/
	private void jButton5ActionPerformed(ActionEvent evt) {
		try {
			resultArea.setText("");
			String s;
			dos.writeUTF("查看得分");
			s=dis.readUTF();
			System.out.println("client.jButton5ActionPerformed 448lines s:"+s);
			String score="";
			if(s.startsWith("我考试得分")){
				score=s.substring(s.indexOf(":")+1);
				System.out.println("client.jButton5ActionPerformed 450lines 考试得分:"+score);
			}
			resultArea.append("你的考试得分为:"+score);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		/* 下一题 */
		aAn.setSelected(false);
		bAn.setSelected(false);
		cAn.setSelected(false);
		dAn.setSelected(false);
		
		try {
			if(count!=0){
				dos.writeUTF("读取下一题");
			}
			String s;
			try {
				s=dis.readUTF();
				System.out.println("client.nextT:"+s);
				if(s.startsWith("试")){
					quesArea.setText(s.substring(s.indexOf(":")+1));
				}
				if(s.endsWith("end")){
					nextQue.setEnabled(false);
				}
				
				count++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String args[]) {
		new Client().setVisible(true);
	}

	private javax.swing.JButton conSer;
	private javax.swing.JButton starTest;
	private javax.swing.JButton loadQues;
	private javax.swing.JButton commitAn;
	private javax.swing.JButton nextQue;
	private javax.swing.JButton querySco;
	private javax.swing.JCheckBox aAn;
	private javax.swing.JCheckBox bAn;
	private javax.swing.JCheckBox cAn;
	private javax.swing.JCheckBox dAn;
	private javax.swing.JLabel tipUrl;
	private javax.swing.JLabel conResult;
	private javax.swing.JPanel rightPanel;
	private javax.swing.JScrollPane result;
	private javax.swing.JScrollPane ques;
	private javax.swing.JTextArea resultArea;
	private javax.swing.JTextArea quesArea;
	private javax.swing.JTextField serverUrl;
	private javax.swing.JTextField useTime;
	private ChoiceFile choiceFile;
	private Thread thread;
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;
	private Scanner sc;
	private PrintWriter pw;
	private Socket socket;
	private JList queLsist;
	private int count=0;
	
	public void run() {
//		String s;
//		try {
//			s=dis.readUTF();
//			System.out.println("client.run:"+s);
//			if(s.startsWith("试")){
//				quesArea.setText(s.substring(s.indexOf(":")+1));
//			}
//			Thread.sleep(5);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}