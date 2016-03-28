import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.*;

public class CSample extends JDialog implements Serializable ,ActionListener{
	JLabel[] myLabel=new JLabel[4];
	JLabel recnum1,recnum2;
	String[] labels={"Zip Code","Which Social Media Do You Use?","What is Your Age?","How Much Time Do You Spend on Social Media on Average Daily?"};
	public JTextField myTextField;
	private JButton subButton;
	private JButton cancelButton;
	private String rec;
	public int R;
	public String zip;
	ArrayList<JCheckBox> socials;
	String[] cb={"Facebook","Twitter","Linked-In","Pintrest","other"};
	private boolean cancelled;
	private JRadioButton _19,_20,_35,_50;
	private JRadioButton half, one, two, more;
	private ButtonGroup radioGroup;
	private ButtonGroup time;
	public int socs;
	public int age;
	public int hrs;
	
	public String getAnswer(){
		String a;
		if(R<10){a="0000000"+rec;}
		else{a="000000"+rec;}
		
		a=a+"               "+(String)zip+"                 ";
		
		if((socs&1)!=0)a+='F';
		else{a+='-';}
		if((socs&2)!=0)a+='T';
		else{a+='-';}
		if((socs&4)!=0)a+='L';
		else{a+='-';}
		if((socs&8)!=0)a+='P';
		else{a+='-';}
		if((socs&16)!=0)a+='O';
		else{a+='-';}
		a+="                ";
		switch(age){
		case 0:
			a+= " >19 ";
			break;
		case 1:  
			a+= "20-35";
			break;
		case 2: 
			a+= "36-49";
			break;
		case 3: 
			a+= " <50 ";
			break;}
		a+="               ";
		switch(hrs){
		case 0:
			a+= " L ";
			break;
		case 1:  
			a+= " M ";
			break;
		case 2: 
			a+= " H ";
			break;
		case 3: 
			a+= " X ";
			break;}
	
		return a;
	}
	public boolean isCancelled(){return cancelled;}
	public String getzip(){return zip;}
	public int getsocs(){return socs;}
	public int getAge(){return age;}
	public int getHrs(){return hrs;}
	
	
	public CSample(Survey owner, String title, String initStr,int r,int _initVal,int _initVa,int _initV){
		super(owner,title,true);
		Container c = getContentPane();
		c.setLayout(null);
		R=r;
		zip=initStr;
		socs=_initVal;
		age=_initVa;
		hrs=_initV;
		recnum1 = new JLabel("Record #");
		recnum1.setSize(200,50);
		recnum1.setLocation(20,5);
		recnum1.setForeground(Color.blue);
		c.add(recnum1);
		
		rec=String.valueOf(R);
		recnum2=new JLabel(String.format("%08d",R));
		recnum2.setSize(100,50);
		recnum2.setLocation(150,5);
		recnum2.setForeground(Color.red);
		c.add(recnum2);
		
		for (int i=0; i<=myLabel.length-1;i++){
			myLabel[i] = new JLabel(labels[i]);
			myLabel[i].setSize(200,50);
			myLabel[i].setLocation(20,20+i*100);
			myLabel[i].setForeground(Color.blue);
			c.add(myLabel[i]);
		}
		myTextField=new JTextField(initStr);
		myTextField.setSize(100,20);
		myTextField.setLocation(100,40);
		c.add(myTextField);
		
		subButton=new JButton("Submit");
		subButton.addActionListener(this);
		subButton.setSize(75,25);
		subButton.setLocation(150,475);
		c.add(subButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		cancelButton.setSize(75,25);
		cancelButton.setLocation( 350,475);
		c.add(cancelButton);
		
		socials = new ArrayList<JCheckBox>();
		
		for (int i=0;i<=cb.length-1;i++){
			socials.add(new JCheckBox(cb[i], (socs&(int)Math.pow(2,i))!=0));
			socials.get(i).setSize(100,25);
			socials.get(i).setLocation(50+100*i,170);
			c.add(socials.get(i));
			
		}
		_19=new JRadioButton("19 or Less",age==0);
		_19.setSize(100,25);
		_19.setLocation(50,270);
		_20=new JRadioButton("20-35",age==1);
		_20.setSize(100,25);
		_20.setLocation(150,270);
		_35=new JRadioButton("36-49",age==2);
		_35.setSize(100,25);
		_35.setLocation(250,270);
		_50=new JRadioButton("50 or More",age==3);
		_50.setSize(100,25);
		_50.setLocation(350,270);
		c.add(_19);
		c.add(_20);
		c.add(_35);
		c.add(_50);
		
		half=new JRadioButton("Less than half hour(L)",hrs==0);
		half.setSize(150,25);
		half.setLocation(20,370);
		one=new JRadioButton("half to one hour(M)",hrs==1);
		one.setSize(130,25);
		one.setLocation(170,370);
		two=new JRadioButton("one to two hours(H)",hrs==3);
		two.setSize(130,25);
		two.setLocation(300,370);
		more=new JRadioButton("two or more hours(X)",hrs==4);
		more.setSize(150,25);
		more.setLocation(450,370);
		c.add(half);
		c.add(one);
		c.add(two);
		c.add(more);
		
		radioGroup=new ButtonGroup();
		radioGroup.add(_19);
		radioGroup.add(_20);
		radioGroup.add(_35);
		radioGroup.add(_50);
		
		time=new ButtonGroup();
		time.add(half);
		time.add(one);
		time.add(two);
		time.add(more);
		
		setSize(600,600);
		setLocationRelativeTo(owner);
		setVisible(true);
	}
	
	
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==subButton){
			//compile answer line here...
			//rec=R
			//zip
			zip = myTextField.getText();
			//social
			if(socials.get(0).isSelected())socs |=1;
			if(socials.get(1).isSelected())socs |=2;
			if(socials.get(2).isSelected())socs |=3;
			if(socials.get(3).isSelected())socs |=4;
			if(socials.get(4).isSelected())socs |=5;
			//age
			if(_19.isSelected())age=0;
			if(_20.isSelected())age=1;
			if(_35.isSelected())age=2;
			if(_50.isSelected())age=3;
			//time
			if(half.isSelected())hrs=0;
			if(one.isSelected())hrs=1;
			if(two.isSelected())hrs=2;
			if(more.isSelected())hrs=3;
			
			cancelled=false;
			setVisible(false);
		}
		else if(e.getSource()==cancelButton){
			cancelled=true;
			setVisible(false);
		}
		
	}

}
