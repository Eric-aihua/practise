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

public class Month extends JPanel implements ActionListener,MouseListener{
	private int month;
	private JTextField showMonth;
	private JButton nextMonth,agoMonth;
	private CalendarPad calendar;
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==agoMonth){
			month=month-1;
			showMonth.setText(" "+month);
			calendar.setMonth(month);
			calendar.setCalendarPanel(calendar.getYear(),month);
		}
		if(e.getSource()==nextMonth){
			month=month+1;
			showMonth.setText(" "+month);
			calendar.setMonth(month);
			calendar.setCalendarPanel(calendar.getYear(),month);
		}
		if(e.getSource()==showMonth){
			month=Integer.parseInt(showMonth.getText());
			calendar.setMonth(month);
			calendar.setCalendarPanel(calendar.getYear(),month);
			
		}
	}
	public Month(CalendarPad calendar){
		super();
		setLayout(new FlowLayout());
		showMonth=new JTextField();
		showMonth.setForeground(Color.BLUE);
		showMonth.setFont(new Font("TimesRomn",Font.BOLD,14));
		showMonth.setText(calendar.getMonth()+"   ");
		this.calendar=calendar;
		month=calendar.getMonth();
		nextMonth=new JButton("下月");
		agoMonth=new JButton("上月");
		nextMonth.addActionListener(this);
		agoMonth.addMouseListener(this);
		nextMonth.addMouseListener(this);
		agoMonth.addActionListener(this);
		add(showMonth);
		add(nextMonth);
		add(agoMonth);
		showMonth.setEditable(false);
		validate();
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
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
		if(e.getSource()==agoMonth){
			if(month==1){
				agoMonth.setEnabled(false);
			}
			if(!nextMonth.isEnabled()){
				nextMonth.setEnabled(true);
			}
			
		}
		if(e.getSource()==nextMonth){
			if(month==12){
				nextMonth.setEnabled(false);
			}
			if(!agoMonth.isEnabled()){
				agoMonth.setEnabled(true);
			}
			
		}
		
	}
	public void mouseReleased(MouseEvent e) {
		
	}

}
