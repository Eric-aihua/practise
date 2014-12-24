package com.eric.io;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;

public class OpenZipFileApp {
	public static void main(String[] args) {
		new ZipFrame();
	}

}
class ZipFrame extends JFrame{
	public ZipFrame(){
		setTitle("zip application");
		setSize(500,700);
		add(new ZipJPanel());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
class ZipJPanel extends JPanel{
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private JComboBox combox;
	private JTextArea textArea;
	private String fileName;
	public ZipJPanel(){
		setLayout(new BorderLayout());
		setSize(500,700);
		menuBar=new JMenuBar();
		menu=new JMenu("file");
		menuItem=new JMenuItem("open");
		menuItem.addActionListener(new OpenAction());
		menu.add(menuItem);
		combox=new JComboBox();
		combox.setSize(500, 300);
		menuItem=new JMenuItem("exit");
		textArea=new JTextArea();
		textArea.setSize(500, 300);
		menu.add(menuItem);
		menu.add(menuItem);
		menuBar.add(menu);
		combox.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				loadZipFile((String)combox.getSelectedItem());
			}
			
		});
		add(menuBar,BorderLayout.NORTH);
		add(combox,BorderLayout.SOUTH);
		add(textArea,BorderLayout.CENTER);
	}
	class OpenAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser=new JFileChooser();
			fileChooser.setCurrentDirectory(new File("src\\com\\eric\\io"));
			ExtensionFilterFile eff=new ExtensionFilterFile();
			eff.addExtensions(".zip");
			eff.addExtensions(".rar");
			eff.addExtensions(".jar");
			fileChooser.setFileFilter(eff);
			int r=fileChooser.showOpenDialog(ZipJPanel.this);
			if(r==JFileChooser.APPROVE_OPTION){
				fileName=fileChooser.getSelectedFile().getPath();
				scanZipFile();
			}
		}
	}
		public void scanZipFile(){
			combox.removeAllItems();
			System.out.println(fileName+"@@@");
			try {
				ZipInputStream zis=new ZipInputStream(new FileInputStream(fileName));
				ZipEntry ze=zis.getNextEntry();
				while((ze=zis.getNextEntry())!=null){
					combox.addItem(ze.getName());
					zis.closeEntry();
				}
				zis.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		public void loadZipFile(String name){
			try {
				ZipInputStream zis=new ZipInputStream(new FileInputStream(fileName));
				ZipEntry ze;
				textArea.setText("");
				while((ze=zis.getNextEntry())!=null){
					if(ze.getName().equals(name)){
						BufferedReader br=new BufferedReader(new InputStreamReader(zis));
						StringBuilder sb=new StringBuilder();
						while(br.readLine()!=null){
							sb.append(br.readLine());
							sb.append("\n");
						}
						textArea.setText(sb.toString());
					}
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	class ExtensionFilterFile extends FileFilter{
		
		private String description="";
		private ArrayList<String> extensions=new ArrayList<String>();
		
		public void addExtensions(String filename){
			if(!filename.startsWith(".")){
				filename="."+filename;
			}
			extensions.add(filename.toLowerCase());
		}
		public ArrayList<String> getExtensions() {
			return extensions;
		}

		public void setExtensions(ArrayList<String> extensions) {
			this.extensions = extensions;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		@Override
		public boolean accept(File f) {
			if(f.isDirectory()){
				return true;
			}
			String fileName=f.getName().toLowerCase();
			for (String en : extensions) {
				if(fileName.endsWith(en)){
					return true;
				}
			}
			return false;
		}
		
		@Override
		public String getDescription() {
			return description;
		}
		
	}
}
