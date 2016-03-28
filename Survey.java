import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

public class Survey extends JFrame implements Serializable ,ActionListener{
	JLabel[] myLabel= new JLabel[5];
	String[] labels={"Record #","Zip Code","Social Media","Age Group","Avg Time"};
	ArrayList<CSample> surveyArray;//
	private DefaultListModel surveys;//
	JList surveyList;//
	JScrollPane scrollPane;//
	JButton bnAdd,bnDel,bnMod,bnDelAll,bnSave,bnLoad;
	int recNum=0;
	String[] args;
	public Survey main;
	public static Survey mainWnd=null;
	public Survey(String[] arg,Survey next){} 
	public Survey(String[] arg) {
		super("Survey on Social Media");
		args=arg;
		Container c = getContentPane();
		c.setLayout(null);
		
		for (int i=0; i<=myLabel.length-1;i++){
			myLabel[i] = new JLabel(labels[i]);
			myLabel[i].setSize(200,50);
			myLabel[i].setLocation(75+i*150,10);
			myLabel[i].setForeground(Color.blue);
			c.add(myLabel[i]);
		}
			
		
		
			surveyArray = new ArrayList<CSample>();
			surveys = new DefaultListModel();
			surveyList = new JList(surveys);
			if(args.length>0){
		      	try{
		      		try{
		      			ObjectInputStream input = new ObjectInputStream(new FileInputStream(args[0]));
		      			Survey next = (Survey)(input.readObject());
		      			for (int i=0;i<=surveyArray.size();i++){
		      				try{
		      					CSample temp = next.surveyArray.get(i);
		      					surveyArray.add(temp);
		      					surveys.addElement(next.surveyArray.get(i).getAnswer());
		      					surveyList.setSelectedIndex(surveys.size()-1);
		      					surveyList.ensureIndexIsVisible(surveys.size()-1);
		      				}
		      				catch(IndexOutOfBoundsException q){//q.printStackTrace();
		      					
		      				}
		      			}
		    			input.close();
		      			
		    		}
		        	catch(ClassNotFoundException t){System.out.println("Class");t.printStackTrace();}
		        }
		        catch(IOException i){System.out.println("IO");i.printStackTrace();}
			}
		surveyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		surveyList.setFont(new Font("Monospaced", Font.PLAIN, 12));
		scrollPane=new JScrollPane(surveyList);
		scrollPane.setSize(655,200);
		scrollPane.setLocation(75,50);
		c.add(scrollPane);
		//buttons
		bnAdd=new JButton("Add");
		bnAdd.setSize(100,30);
		bnAdd.setLocation(25,260);
		bnAdd.addActionListener(this);
		c.add(bnAdd);
		
		bnMod=new JButton("Modify");
		bnMod.setSize(100,30);
		bnMod.setLocation(150,260);
		bnMod.addActionListener(this);
		c.add(bnMod);
		
		bnDel=new JButton("Delete");
		bnDel.setSize(100,30);
		bnDel.setLocation(275,260);
		bnDel.addActionListener(this);
		c.add(bnDel);
		
		
		bnDelAll=new JButton("Delete All");
		bnDelAll.setSize(100,30);
		bnDelAll.setLocation(400,260);
		bnDelAll.addActionListener(this);
		c.add(bnDelAll);
		
		bnSave=new JButton("Save");
		bnSave.setSize(100,30);
		bnSave.setLocation(525,260);
		bnSave.addActionListener(this);
		c.add(bnSave);
		
		bnLoad=new JButton("Load");
		bnLoad.setSize(100,30);
		bnLoad.setLocation(650,260);
		bnLoad.addActionListener(this);
		c.add(bnLoad);
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setSize(800,350);
		setLocation(100,100);
		setVisible(true);
	}
	public final void actionPerformed(ActionEvent e){		
	if (e.getSource()==bnAdd){
		recNum++;
		CSample dialogWnd=new CSample(this,"Add a Survey","",recNum,0,0,0);
		if(!dialogWnd.isCancelled()){
			surveyArray.add(dialogWnd);
			//System.out.print(dialogWnd.getAnswer());
			surveys.addElement(dialogWnd.getAnswer());
			surveyList.setSelectedIndex(surveys.size()-1);
			surveyList.ensureIndexIsVisible(surveys.size()-1);
		}
		if(dialogWnd.isCancelled()){recNum--;}
	}
	else if(e.getSource()==bnMod){
		int index=surveyList.getSelectedIndex();
		if (index>=0){
			CSample dialogWnd=new CSample(this,"Add a Survey",surveyArray.get(index).getzip(),(int)surveyArray.get(index).R,(int)surveyArray.get(index).socs,(int)surveyArray.get(index).age,(int)surveyArray.get(index).hrs);
			if(!dialogWnd.isCancelled()){
				surveyArray.set(index,dialogWnd);
				surveys.set(index, dialogWnd.getAnswer());
			}
		}
	}
	else if(e.getSource()==bnDel){
		int index=surveyList.getSelectedIndex();
		if(index>=0){
			surveyArray.remove(index);
			surveys.remove(index);
			if(index==surveys.size()){
				index--;
			}
			surveyList.setSelectedIndex(index);
			surveyList.ensureIndexIsVisible(index);
		}
	}
	else if(e.getSource()==bnDelAll){
		for(int i=0;i<=surveys.size()-1;i++){
			int index=surveyList.getSelectedIndex();
			if(index>=0){
				surveyArray.clear();
				surveys.clear();
				if(index==surveys.size()){
					index--;
				}
			}
		}
	}
	else if(e.getSource()==bnSave){
		JFileChooser fc=new JFileChooser();
		int returnVa = fc.showSaveDialog(mainWnd);
	        if (returnVa == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();		
	            try{
	        	//	try{
	        			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
	        			output.writeObject(this);
	        			output.close();
	        			//}
	        		//catch(ClassNotFoundException t){
	        		//}
	            }
	        	catch(IOException i){i.printStackTrace();
	        }
	    }
	}    
	else if(e.getSource()==bnLoad){
		JFileChooser f=new JFileChooser();
		int returnVal = f.showOpenDialog(this);
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
	        File file = f.getSelectedFile();
	      	try{
	      		try{
	      			ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
	      			Survey next = (Survey)(input.readObject());
	      			for (int i=0;i<=surveyArray.size();i++){
	      				try{
	      					CSample temp = next.surveyArray.get(i);
	      					surveyArray.add(temp);
	      					surveys.addElement(next.surveyArray.get(i).getAnswer());
	      					surveyList.setSelectedIndex(surveys.size()-1);
	      					surveyList.ensureIndexIsVisible(surveys.size()-1);
	      				}
	      				catch(IndexOutOfBoundsException q){//q.printStackTrace();
	      					
	      				}
	      			}
	    			input.close();
	      			
	    		}
	        	catch(ClassNotFoundException t){System.out.println("Class");t.printStackTrace();}
	        }
	        catch(IOException i){System.out.println("IO");i.printStackTrace();}
		}
	}        
}
	public static void main(String[] args){Survey mainWnd=new Survey(args);}}
