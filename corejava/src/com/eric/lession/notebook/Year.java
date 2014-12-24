package com.eric.lession.notebook;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Year extends JPanel implements ActionListener,MouseListener{
	private int year;
	private JTextField showYear;
	private JButton nextYear,agoYear;
	private CalendarPad calendar;
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==agoYear){
			year=year-1;
			showYear.setText("   "+year);
			calendar.setYear(year);
			calendar.setCalendarPanel(year,calendar.getMonth());
		}
		if(e.getSource()==nextYear){
			year=year+1;
			showYear.setText(" "+year);
			calendar.setYear(year);
			calendar.setCalendarPanel(year,calendar.getMonth());
		}
		if(e.getSource()==showYear){
			year=Integer.parseInt(showYear.getText());
			calendar.setYear(year);
			calendar.setCalendarPanel(year,calendar.getMonth());
			
		}
	}
	public Year(CalendarPad calendar){
		super();
		setLayout(new FlowLayout());
		showYear=new JTextField();
		showYear.setForeground(Color.BLUE);
		showYear.setFont(new Font("TimesRomn",Font.BOLD,14));
		showYear.setText(calendar.getYear()+" ");
		this.calendar=calendar;
		year=calendar.getYear();
		nextYear=new JButton("下年");
		agoYear=new JButton("上年");
		nextYear.addActionListener(this);
		nextYear.addMouseListener(this);
		agoYear.addMouseListener(this);
		agoYear.addActionListener(this);
		add(showYear);
		add(nextYear);
		add(agoYear);
		showYear.setEditable(false);
		validate();
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public void mouseClicked(MouseEvent e) {
		
	}
	public void mouseEntered(MouseEvent e) {
		
	}
	public void mouseExited(MouseEvent e) {
		
	}
	public void mousePressed(MouseEvent e) {
		if(e.getSource()==agoYear){
			if(year==2000){
				agoYear.setEnabled(false);
			}
			if(!nextYear.isEnabled()){
				nextYear.setEnabled(true);
			}
			
		}
		if(e.getSource()==nextYear){
			if(year==2100){
				nextYear.setEnabled(false);
			}
			if(!agoYear.isEnabled()){
				agoYear.setEnabled(true);
			}
			
		}
		
	}
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
