import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;

class eSurveying extends JFrame implements Runnable , ActionListener
{
	static int height=500,width=500;
	Panel p1=new Panel();
	Panel p2=new Panel();
	Panel p3=new Panel();
	Panel p4=new Panel();
	Panel p5=new Panel();
	Panel p6=new Panel();
	Panel p7=new Panel();
	Panel p8=new Panel();
	Panel p9=new Panel();
	

	FileWriter fw;
	Thread t1,t2,t3;
	int noo=0,hrs,mins,index,time,winnervotes,tie;
	boolean timer=false,votingstarted=false;
	String date = "yyyy-MM-dd HH:mm:ss";
	String options[]=new String[100];
	String tieoptions[]=new String[100];
	String winnername=new String("");
	String purpose=new String("");
	String detail="";
	String helptext="How to use this application\n\n*Click on Start survey button.\n* Enter the topic you want to survey for.\n* Enter the duration of survey to be conducted in hours minutes format.\n* Enter all options you desire.\n* Enter the user name and password given to you by the programmer.\n* Now the survey starts.\n* The participants can now cast their respective votes of their choice.\n* Once the time duration of the survey completes, results are declared.\n* You can save the result into a text file if you desire by clicking the save button."; 
	char waitsymbols[]={'|','/','-','\\'};

	int votes[]=new int[100];

	String result=new String("The");
	int i=0;

	JLabel jl1=new JLabel("e-Survey");
	JLabel jl2=new JLabel("");
	JLabel jl3=new JLabel("Duration to conduct survey: ");
	JLabel jl4=new JLabel("Enter the topic detail: ");
	JLabel jl5=new JLabel("Enter viable options for surveying: ");
	JLabel jl6=new JLabel("User name: ");
	JLabel jl7=new JLabel("Password: ");
	JLabel jl8=new JLabel("Click on any one of the following options to record your opinion");
	JLabel jl9=new JLabel("Congratulations! Survey completed. Results are as per the following.");
	JLabel jl10=new JLabel("Enter name of the survey:");
	JLabel jl11=new JLabel("");


	JButton jb1=new JButton("Start Survey");
	JButton jb2=new JButton("Help");
	JButton jb3=new JButton("Next");
	JButton jb4=new JButton("Start");
	JButton jb5=new JButton("Start Survey");
	JButton jb6=new JButton("Start Survey");
	JButton jb7=new JButton("Save Result");
	JButton jb8=new JButton("Exit");
	JButton jb9=new JButton("Add option");

	JTextField jtf1=new JTextField("");
	JTextField jtf2=new JTextField("");
	JTextField jtf3=new JTextField("");
	JTextField jtf4=new JTextField("");
	JTextField jtf5=new JTextField("");
	JPasswordField jtf6=new JPasswordField("");
	JTextField jtf7=new JTextField("");

	JTextArea jta1=new JTextArea();
	JTextArea jta2=new JTextArea();
	JRadioButton optionlist[]=new JRadioButton[100];
	ButtonGroup group=new ButtonGroup();

	Dimension size;
	Insets insets;

	boolean loading=true,valid=true;
	eSurveying()
	{
		super("e-Surveying");
		add(p1);
		p1.setLayout(null);
		p2.setLayout(null);
		p3.setLayout(null);
		p4.setLayout(null);
		p5.setLayout(null);
		p6.setLayout(null);
		p7.setLayout(null);
		p8.setLayout(null);
		p9.setLayout(null);

		insets=p1.getInsets();
		t1=new Thread(this);
		t2=new Thread(this);
		t3=new Thread(this);
		t1.start();

		for(int i=0;i<100;i++)
			options[i]="";

		size=jl1.getPreferredSize();
		jl1.setBounds(insets.left+170,insets.top+10,size.width+200,size.height+30);
		jl1.setFont(new Font("Serif",Font.BOLD,30));
		jl1.setForeground(Color.magenta);
		p1.add(jl1);
		
		size=jl2.getPreferredSize();
		jl2.setBounds(170,insets.top+200,size.width+200,size.height+30);
		jl2.setFont(new Font("Serif",Font.BOLD,40));
		
		insets=p2.getInsets();
		size=jb1.getPreferredSize();
		jb1.setBounds(insets.left+150,insets.top+150,size.width,size.height);
		jb1.addActionListener(this);
		jb1.setActionCommand("start survey");
		
		size=jb2.getPreferredSize();
		jb2.setBounds(insets.left+173,insets.top+210,size.width+4,size.height);
		jb2.addActionListener(this);
		jb2.setActionCommand("help");
		
		insets=p3.getInsets();
		size=jl10.getPreferredSize();
		jl10.setBounds(insets.left+20,insets.top+110,size.width+100,size.height);

		size=jtf7.getPreferredSize();
		jtf7.setBounds(insets.left+200,insets.top+110,size.width+150,size.height);

		size=jl3.getPreferredSize();
		jl3.setBounds(insets.left+20,insets.top+165,size.width+100,size.height);

		size=jtf1.getPreferredSize();
		jtf1.setBounds(insets.left+200,insets.top+140,size.width+250,size.height);
		
		size=jl4.getPreferredSize();
		jl4.setBounds(insets.left+20,insets.top+140,size.width+150,size.height);
		
		size=jtf2.getPreferredSize();
		jtf2.setBounds(insets.left+200,insets.top+165,size.width+30,size.height);
		jtf2.setText("h");

		size=jtf3.getPreferredSize();
		jtf3.setBounds(insets.left+240,insets.top+165,size.width+30,size.height);
		jtf3.setText("mm");
		jtf3.addActionListener(this);
		jtf3.setActionCommand("next");


		size=jb3.getPreferredSize();
		jb3.setBounds(insets.left+150,insets.top+205,size.width+4,size.height);
		jb3.setActionCommand("next");
		jb3.addActionListener(this);

		insets=p4.getInsets();
		size=jl5.getPreferredSize();
		jl5.setBounds(insets.left+00,insets.top+100,size.width+150,size.height);

		size=jtf4.getPreferredSize();
		jtf4.setBounds(insets.left+210,insets.top+100,size.width+250,size.height);
		jtf4.setActionCommand("add option");
		jtf4.addActionListener(this);

		size=jb4.getPreferredSize();
		jb4.setBounds(insets.left+230,insets.top+160,size.width,size.height);
		jb4.setActionCommand("added option");
		jb4.addActionListener(this);

		size=jb9.getPreferredSize();
		jb9.setBounds(insets.left+110,insets.top+160,size.width,size.height);
		jb9.setActionCommand("add option");
		jb9.addActionListener(this);
		
		insets=p5.getInsets();
		size=jl6.getPreferredSize();
		jl6.setBounds(insets.left+130,insets.top+100,size.width+50,size.height);

		size=jtf5.getPreferredSize();
		jtf5.setBounds(insets.left+200,insets.top+100,size.width+100,size.height);

		size=jl7.getPreferredSize();
		jl7.setBounds(insets.left+130,insets.top+140,size.width+100,size.height);

		size=jtf6.getPreferredSize();
		jtf6.setBounds(insets.left+200,insets.top+140,size.width+100,size.height);
		jtf6.setActionCommand("start surveying");
		jtf6.addActionListener(this);
		jtf6.setEchoChar('*');

		size=jb5.getPreferredSize();
		jb5.setBounds(insets.left+160,insets.top+200,size.width	,size.height);
		jb5.setActionCommand("start surveying");
		jb5.addActionListener(this);

		insets=p6.getInsets();
		size=jl8.getPreferredSize();
		jl8.setBounds(5,insets.top+60,size.width+500,size.height);
		
		insets=p8.getInsets();
		size=jta2.getPreferredSize();
		jta2.setBounds(insets.left+10,insets.top+100,width-35,height-250);
		jta2.setText(helptext);
		jta2.setEditable(false);

		size=jb6.getPreferredSize();
		jb6.setBounds(insets.left+160,400,size.width,size.height);
		jb6.setActionCommand("start surveying from help");
		jb6.addActionListener(this);

		insets=p9.getInsets();
		size=jl9.getPreferredSize();
		jl9.setBounds(7,75,size.width,size.height);

		insets=p9.getInsets();
		size=jta1.getPreferredSize();
		jta1.setBounds(10,100,width-35,height-250);
		jta1.setText(result);
		jta1.setEditable(false);

		size=jb7.getPreferredSize();
		jb7.setBounds(insets.left+100,400,size.width,size.height);
		jb7.addActionListener(this);
		jb7.setActionCommand("save");

		size=jb8.getPreferredSize();
		jb8.setBounds(insets.left+230,400,size.width,size.height);
		jb8.addActionListener(this);
		jb8.setActionCommand("exit");

	}

	public void run()
	{
		if(loading)
		{
			try
			{
				for(int i=1;i<=4;i++)
				{
					for(int j=0;j<4;j++)
					{
						jl2.setText(" "+waitsymbols[j]+" "+waitsymbols[j]+" "+waitsymbols[j]+" "+waitsymbols[j]);
						p1.add(jl2);
						t1.sleep(200);
					}
				}
			}
			catch (Exception e){}
			loading=false;
			jl2.setVisible(false);
			remove(p1);
			add(p2);
			p2.setSize(height,width);
			p2.add(jl1);
			p2.add(jb1);
			p2.add(jb2);

		}
		if(timer)
		{
			try
			{
				Thread.sleep(time);
				timeout();
			}
			catch (Exception e){}
		}
	}
	void timeout()
	{
		/*for(int i=0;i<noo;i++)
			optionlist[i].setEnabled(false);*/
		JOptionPane.showMessageDialog(this,"No more voting!! Voting time exhausted.","Time up",JOptionPane.INFORMATION_MESSAGE);
		for(int i=0;i<noo;i++)
		{
			if(votes[i]>winnervotes)
			{
				winnervotes=votes[i];
				winnername=options[i];
			}
		}
		for(int i=0;i<noo;i++)
		{
			if(votes[i]==winnervotes)
			{
				tie++;
			}
		}
		if(tie>1)
		{
			int j=0;
			JOptionPane.showMessageDialog(this,"There is a tie between "+tie+" options","Tie",JOptionPane.INFORMATION_MESSAGE);
			for(int i=0;i<noo;i++)
			{
				if(votes[i]==winnervotes)
				{
					tieoptions[j++]=options[i];
				}
			}
			result+="re is a tie between "+tie+" options ";
			for(int i=0;i<tie;i++)
			{
				if(i<tie-1)
					result+=tieoptions[i]+", ";
				else
					result+=tieoptions[i]+" each";
			}
		}
		else
		{
			result="The most voted option is "+winnername;
		}
		if(winnervotes==1)
			result+=" with "+winnervotes+" vote.\n\nNumber of votes casted for each option are\n\n";
		else			
			result+=" with "+winnervotes+" votes.\n\nNumber of votes casted for each option are\n\n";
		for(int i=0;i<noo;i++)
		{
			result+=options[i]+"\t"+votes[i]+"\n";
		}
		remove(p6);
		remove(p7);
		add(p9);
		jta1.setText(result);
		p9.setSize(height,width);
		p9.add(jl1);
		p9.add(jl9);
		p9.add(jta1);
		p9.add(jb7);
		p9.add(jb8);

	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getActionCommand().equals("start survey"))
		{
			jb1.setVisible(false);
			jb2.setVisible(false);
			remove(p2);
			add(p3);
			p3.setSize(height,width);
			p3.add(jl1);
			p3.add(jl10);
			p3.add(jtf7);
			p3.add(jl3);
			p3.add(jl4);
			p3.add(jtf1);
			p3.add(jtf2);
			jtf2.setToolTipText("hours");
			p3.add(jtf3);
			jtf3.setToolTipText("minutes");
			p3.add(jb3);			
		}

		if(ae.getActionCommand().equals("next"))
		{
			if((!jtf7.getText().equals(""))&&(!jtf1.getText().equals(""))&&(!jtf2.getText().equals(""))&&(!jtf3.getText().equals("")))
			{
				purpose=jtf7.getText();
				detail=jtf1.getText();
				try
				{
					hrs = Integer.parseInt(jtf2.getText());
					mins = Integer.parseInt(jtf3.getText());
				}
				catch (Exception e)
				{
				}
				valid=true;
				if(hrs<0)
				{
					JOptionPane.showMessageDialog(this,"Hours value should be atleast 0","Error",JOptionPane.ERROR_MESSAGE);
					valid=false;
				}else
				if(mins<1)
				{
					JOptionPane.showMessageDialog(this,"Survey should be scheduled for atleast 1 minute","Error",JOptionPane.ERROR_MESSAGE);
					valid=false;
				}
				if(valid)
				{
				try
				{
						hrs = Integer.parseInt(jtf2.getText());
						mins = Integer.parseInt(jtf3.getText());
						mins=mins+(hrs*60);
						time=(mins*60)*1000;
						remove(p3);
						add(p4);
						p4.setSize(height,width);
						p4.add(jl1);
						p4.add(jl5);
						p4.add(jb4);
						p4.add(jtf4);
						p4.add(jb9);
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(this,"Please specify appropriate values","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			else
				JOptionPane.showMessageDialog(this,"Please specify appropriate values for each field","Error",JOptionPane.ERROR_MESSAGE);

		}
		if(ae.getActionCommand().equals("add option"))
		{
			if(jtf4.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Please specify an option","Alert",JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				boolean matched=false;
				for(int i=0;i<noo;i++)
				{
					if(options[i].equals(jtf4.getText()))
					{
						matched=true;
						JOptionPane.showMessageDialog(this,"Option already exists!!","Alert",JOptionPane.INFORMATION_MESSAGE);
					}
				}
				if(!matched)
				{
					options[index++]=jtf4.getText();
					jtf4.setText("");
					noo++;
				}
			}
		}
		if(ae.getActionCommand().equals("added option"))
		{
			if(noo<2)
				JOptionPane.showMessageDialog(this,"There should be atleast 2 options to survey","Error",JOptionPane.ERROR_MESSAGE);
			else
			{
				JOptionPane.showMessageDialog(this," "+noo+" options successfully added to survey","Alert",JOptionPane.INFORMATION_MESSAGE);
				remove(p4);
				add(p5);
				p5.setSize(height,width);
				p5.add(jl1);
				p5.add(jl6);
				p5.add(jtf5);
				p5.add(jl7);
				p5.add(jtf6);
				p5.add(jb5);
			}
		}
		if(ae.getActionCommand().equals("start surveying"))
		{
			if(jtf5.getText().equals("admin") && jtf6.getText().equals("admin"))
			{
				JOptionPane.showMessageDialog(this,"You are about to start survey for "+purpose,"Alert",JOptionPane.INFORMATION_MESSAGE);
				timer=true;
				t2.start();
				votingstarted=true;
				remove(p5);
				add(p6);
				p6.setSize(height,width);
				insets=p6.getInsets();
				size=p7.getPreferredSize();
				p7.setBounds(insets.left,insets.top+90,500,height-100);
				p6.add(p7);
				insets=p7.getInsets();
				for(i=0;i<noo;i++)
				{
					optionlist[i]=new JRadioButton(options[i]);
					group.add(optionlist[i]);
					optionlist[i].setToolTipText("Record my vote towards "+options[i]);
					p6.add(jl1);
					jl8.setText("Click on any one of the following options to cast your vote for "+purpose+".");
					jl11.setText(detail);
					jl11.setFont(new Font("Serif",Font.BOLD,15));

					size=jl11.getPreferredSize();
					jl11.setBounds(5,5,500,size.height);

					p6.add(jl8);
					size=optionlist[i].getPreferredSize();
					optionlist[i].setBounds(insets.left,(i*size.height)+35,width-15,size.height);
					p7.add(jl11);
					p7.add(optionlist[i]);

					optionlist[i].setActionCommand(options[i]);
					optionlist[i].setFont(new Font("Serif",Font.BOLD,15));

					optionlist[i].addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent ae)
						{
							for(int j=0;j<noo;j++)
							{
								if(ae.getActionCommand().equals(options[j]))
								{
									votes[j]+=1;
									JOptionPane.showMessageDialog(new eSurveying(),"Your vote is recorded succesfully!","Alert",JOptionPane.INFORMATION_MESSAGE);
								}
							}
							for(int j=0;j<noo;j++)
							{
								optionlist[j].setSelected(false);
							}

							/*for(int j=0;j<noo;j++)
								System.out.print("   "+votes[j]);
							System.out.print("\n\n");*/
						}
					});

				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Entered user name or password are incorrect","Alert",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(ae.getActionCommand().equals("help"))
		{
			remove(p2);
			add(p8);
			p8.setSize(height,width);
			p8.add(jl1);
			p8.add(jta2);
			p8.add(jb6);
		}
		if(ae.getActionCommand().equals("start surveying from help"))
		{
			remove(p8);
			add(p3);
			add(p3);
			p3.setSize(height,width);
			p3.add(jl1);
			p3.add(jl10);
			p3.add(jtf7);
			p3.add(jl3);
			p3.add(jl4);
			p3.add(jtf1);
			p3.add(jtf2);
			jtf2.setToolTipText("hours");
			p3.add(jtf3);
			jtf3.setToolTipText("minutes");
			p3.add(jb3);			
		}
		if(ae.getActionCommand().equals("save"))
		{
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(date);
			date=sdf.format(cal.getTime());
			date=date.replace(" ","_");
			date=date.replace("-","");
			date=date.replace(":","");

			try
			{
				result+="\n Results for "+purpose+" survey held on "+date;
				String filename="D:/survey_"+purpose+"_"+date+".txt";
				fw=new FileWriter(filename);
				char buffer[]=new char[result.length()];
				result.getChars(0,result.length(),buffer,0);
				fw.write(buffer);
				fw.close();
				JOptionPane.showMessageDialog(this,"Result recorded succesfully as "+"D:/survey_"+purpose+"_"+date+".txt","Alert",JOptionPane.INFORMATION_MESSAGE);
			}
			catch (Exception e)
			{
				System.out.println(e);
				JOptionPane.showMessageDialog(this,"Sorry! Could not save data to file.","Error",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if(ae.getActionCommand().equals("exit"))
		{
			System.exit(0);
		}

	}
	public static void main(String[] args) 
	{
		System.out.println("Welcome to e-Surveying");
		eSurveying esurveying=new eSurveying();
		esurveying.setSize(height,width);
		esurveying.setVisible(true);
		//esurveying.setResizable(false);
		esurveying.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}