package com.eric.lession.notebook;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class NotePad extends JPanel implements ActionListener{

	private JTextArea text;
	private JButton addLog,deleteLog;
	private Hashtable hashTable;
	private File file;
	private int year,month,day;
	private CalendarPad calendar;
	private JLabel label;
	public NotePad(CalendarPad calendar){
		super();
		this.calendar=calendar;
		year=calendar.getYear();
		month=calendar.getMonth();
		day=calendar.getDay();
		hashTable=calendar.getHashTable();
		text=new JTextArea(10,15);
		label=new JLabel(year+" 年 "+month+" 月 "+day+" 日 ");
		label.setForeground(Color.red);
		addLog=new JButton("增加日志");
		deleteLog=new JButton("删除日志");
		addLog.addActionListener(this);
		deleteLog.addActionListener(this);
		file=new File("noteBook.tx");
		setLayout(new BorderLayout());
		JPanel panelSouth=new JPanel();
		panelSouth.add(label);
		add(panelSouth,BorderLayout.NORTH);
		add(new JScrollPane(text),BorderLayout.CENTER);
		JPanel panelNorth=new JPanel();
		panelNorth.add(addLog);
		panelNorth.add(deleteLog);
		add(panelNorth,BorderLayout.SOUTH);
		setSize(50,200);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==addLog){
			addLog(year,month,day);
		}
		else if(e.getSource()==deleteLog){
			deleteLog(year,month,day);
		}
	}
	public  void deleteLog(int year, int month, int day) {
		String key=""+year+""+month+""+day;
		String logContent=text.getText();
		String message=" "+year+" 年"+month+"月"+day+"日，确定要删除日志吗？";
		int ok=JOptionPane.showConfirmDialog(this, message, "delete", JOptionPane.YES_NO_OPTION);
		if(hashTable.containsKey(key)){
			
		if(ok==JOptionPane.OK_OPTION){
			FileOutputStream fos;
			try {
				FileInputStream fis=new FileInputStream(file);
				ObjectInputStream ois=new ObjectInputStream(fis);
				hashTable=(Hashtable)ois.readObject();
				hashTable.remove(key);
				fos = new FileOutputStream(file);
				ObjectOutputStream oos=new ObjectOutputStream(fos);
				oos.writeObject(hashTable);
				ois.close();
				fis.close();
				oos.close();
				fos.close();
				text.setText("");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		}
		else{
			String message2=" "+year+" 年"+month+"月"+day+"日，该天没有记录？";
			JOptionPane.showMessageDialog(this, message2,"tip",JOptionPane.CANCEL_OPTION);
		}
	}
	public void addLog(int year, int month, int day) {
		String key=""+year+""+month+""+day;
		System.out.println("add log key" +":"+key);
		String logContent=text.getText();
		String message=" "+year+" 年"+month+"月"+day+"日，真的添加日志吗？";
		int ok=JOptionPane.showConfirmDialog(this, message, "add", JOptionPane.YES_NO_OPTION);
		if(ok==JOptionPane.OK_OPTION){
			try {
				FileInputStream fis=new FileInputStream(file);
				ObjectInputStream ois=new ObjectInputStream(fis);
				hashTable=(Hashtable)ois.readObject();
				hashTable.put(key,logContent);
				ois.close();
				fis.close();
				FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos=new ObjectOutputStream(fos);
				oos.writeObject(hashTable);
				oos.close();
				fos.close();
				text.setText("");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	public void setLabel(int year, int month, int day){
		label.setText(" "+year+" 年 "+month+" 月 "+day+" 日 ");
	}
	public void setTestArea(String s){
		text.setText(s);
	}
	public void getLogContent(int year,int month,int day){
		String key=""+year+""+month+""+day;
		System.out.println("click key："+key);
		try {
			FileInputStream fis=new FileInputStream(file);
			ObjectInputStream ois=new ObjectInputStream(fis);
			hashTable=(Hashtable)ois.readObject();
			if(hashTable.get(key)!=null){
				String message=" "+year+" 年"+month+"月"+day+"日，今天有日志，想看吗？";
				int ok=JOptionPane.showConfirmDialog(this, message, "query", JOptionPane.YES_NO_OPTION);
					if(ok==JOptionPane.YES_OPTION){
						text.setText((String)hashTable.get(key));
					}
					else if(ok==JOptionPane.NO_OPTION){
						text.setText(" ");
					}
				}
			ois.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	

}
