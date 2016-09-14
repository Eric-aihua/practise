package com.eric.lession.notebook;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalendarPad extends JFrame implements ActionListener,MouseListener{

	private int year,month,day;
	private Hashtable hashTable;
	private File file;
	private JTextField showDay[];
	private JLabel[] title;
	private int week;
	private Calendar calendar;
	private NotePad notePad;
	private Month forMonth;
	private Year forYear;
	private String[] weekdays;
	private JPanel leftPanel,rightPanel;
	
	public CalendarPad(int year, int month, int day) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		title=new JLabel[8];
		showDay=new JTextField[42];
		leftPanel=new JPanel();
		weekdays=new String[]{"日       ","一       ","二       ","三         ","四       ","五       ","  六       "};
		JPanel leftCenter=new JPanel();
		JPanel leftNorth=new JPanel();
		hashTable=new Hashtable();
		file=new File("noteBook.tx");
		leftCenter.setLayout(new GridLayout(6,6));
		rightPanel=new JPanel();
		forYear=new Year(this);
		forYear.setYear(year);
		forMonth=new Month(this);
		forMonth.setMonth(month);
		for (int i = 0; i < 7; i++) {
			title[i]=new JLabel(weekdays[i]);
			leftNorth.add(title[i]);
		}
		title[0].setBackground(Color.red);
		title[6].setBackground(Color.red);
		for (int i = 0; i < 42; i++) {
			showDay[i]=new JTextField();
			showDay[i].addMouseListener(this);
			showDay[i].setEditable(false);
			leftCenter.add(showDay[i]);
		}
		leftCenter.validate();
		calendar=Calendar.getInstance();
		JPanel setMY=new JPanel();
		setMY.setLayout(new BorderLayout());
		setMY.add(forYear,BorderLayout.WEST);
		setMY.add(forMonth);
		setMY.validate();
		leftNorth.add(setMY);
		leftPanel.setLayout(new BorderLayout());
		leftPanel.add(setMY,BorderLayout.SOUTH);
		leftNorth.validate();
		leftPanel.add(leftNorth,BorderLayout.NORTH);
		leftPanel.add(leftCenter,BorderLayout.CENTER);
		
		if(!file.exists()){
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(file);
				ObjectOutputStream oos=new ObjectOutputStream(fos);
				oos.writeObject(hashTable);
				oos.close();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		notePad=new NotePad(this);
		rightPanel.add(notePad);
		add(leftPanel);
		add(rightPanel,BorderLayout.EAST);
		setCalendarPanel(year, month);
		validate();
		setSize(600,300);
		setLocation(300, 300);
		setTitle("日历记事本");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
	}
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public void setCalendarPanel(int year, int month) {
		calendar.set(year, month-1, 1);
		week=calendar.get(Calendar.DAY_OF_WEEK)-1;
		if(month==2){
			if(year%4==0&&((year%100)!=0)||(year%400==0)){
				arrangeNum(week, 29);
			}
			else{
				arrangeNum(week, 28);
			}
		}
		if(month==4||month==6||month==9||month==11){
			arrangeNum(week, 30);
		}
		else{
			arrangeNum(week, 31);
		}
		
	}
	public void arrangeNum(int week,int dayOfMonth){
		int n=1;
		Calendar c=Calendar.getInstance();
		for (int i = week; i < week+dayOfMonth; i++) {
			showDay[i].setText(" "+n);
			if(i==day&&month==c.get(Calendar.MONTH)+1&&year==c.get(Calendar.YEAR)){
				showDay[i].setForeground(Color.RED);
				showDay[i].setFont(new Font("TimesRoman",Font.BOLD,20));
			}
			else if((i%7)==6){
				showDay[i].setForeground(Color.GRAY);
				showDay[i].setFont(new Font("TimesRoman",Font.BOLD,20));
			}
			else if(i%7==0){
				showDay[i].setForeground(Color.GRAY);
				showDay[i].setFont(new Font("TimesRoman",Font.BOLD,20));
			}
			else{
				showDay[i].setForeground(Color.black);
				showDay[i].setFont(new Font("TimesRoman",Font.BOLD,12));
			}
			n++;
		}
		for (int i = 0; i < week; i++) {
			showDay[i].setText("  ");
		}
		for (int i = week+dayOfMonth; i < 42; i++) {
			showDay[i].setText("  ");
		}
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		JTextField source=(JTextField)e.getSource();
		day=Integer.parseInt(source.getText().trim());
		notePad.setDay(day);
		notePad.setLabel(year, month, day);
		notePad.setTestArea(null);
		//System.out.println(day);
		notePad.getLogContent(year, month, day);
	}

	public void mouseReleased(MouseEvent e) {
		
	}
	public static void main(String[] args) {
		Calendar calendar=Calendar.getInstance();
		int Year=calendar.get(Calendar.YEAR);
		int month=calendar.get(Calendar.MONTH)+1;
		int day=calendar.get(Calendar.DAY_OF_MONTH);
		new CalendarPad(Year,month,day);
	}

	public Hashtable getHashTable() {
		return hashTable;
	}

	public void setHashTable(Hashtable hashTable) {
		this.hashTable = hashTable;
	}

	
	
}
