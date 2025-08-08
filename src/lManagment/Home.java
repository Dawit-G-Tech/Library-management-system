package lManagment;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.*;
public class Home extends JFrame{
	private JPanel leftPanel;	
	private JPanel topPanel;
	private JPanel pnHome;
	private JPanel pnBooks;
	private JPanel pnUsers;
	private JPanel pnDefaulters;
	private JPanel pnLogout;
	private JPanel pnN;
	private JPanel pnNBooks;
	private JPanel pnNUsers;
	private JPanel pnNDefaulters;
	private JPanel pnT;

  
	private JLabel name;
	private JLabel lbHome;
	private JLabel lbBooks;
	private JLabel lbUsers;
	private JLabel lbDefaulters;
	private JLabel tBooks;
	private JLabel tUsers;
	private JLabel tDefaulters;
	private JLabel lbLogout;
	private JLabel lbNBooks;
	private JLabel lbNUsers;
	private JLabel lbNDefaulters;
	private JLabel pnD;

	private Font font=new Font("consolas",Font.PLAIN,40);
	
	  private JTable tbBooks;
	  
	final Color blue=Color.blue;
	final Color black=Color.black;
	final Color white=Color.white;
	final Color gray=new Color(90,90,90);
	final Color lGray=Color.LIGHT_GRAY;
	final Font txtBold=new Font(null,Font.CENTER_BASELINE,24);
	final Font txtPlain=new Font(null,Font.PLAIN,24);
	final Font tfont =new Font(null,Font.PLAIN,26);
	
	Home(){
		
		
		
		initComp();
    	setExtendedState(JFrame.MAXIMIZED_BOTH);
		noOF("books","bookid",lbNBooks);
		noOF("users","library_id",lbNUsers);
		noOF("users",lbNDefaulters,LocalDate.now());
        tableDisplay("books",tbBooks);

		
	}
  private void initComp() {
		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        int screenWidth = screenSize.width;
	        int screenHeight = screenSize.height;
	        
		topPanel=new JPanel();
		topPanel.setBounds(0,0,screenWidth,75);
		topPanel.setBackground(blue);
		topPanel.setLayout(null);
	////////////////////////////////////////
		/// left components 
		leftPanel=new JPanel();
		leftPanel.setBounds(0,0+topPanel.getHeight(),270,screenHeight);
		leftPanel.setBackground(gray);
		leftPanel.setLayout(null);
		
		
		name=new JLabel("AAU library");
		name.setBounds(50,0,200,70);
		name.setFont(new Font("Segoe Print",Font.BOLD,24));
		name.setForeground(white);
		
		pnHome=new JPanel();
		pnHome.setBackground(blue);
		pnHome.setLayout(null);
		pnHome.setBounds(0,15,leftPanel.getWidth(),75);
		
		lbHome=new JLabel("Home");
		lbHome.setFont(txtBold);
		lbHome.setBounds(50,0,leftPanel.getWidth(),75);
		lbHome.setForeground(white);
		lbHome.setIcon(new ImageIcon("./adminIcons/home_24px.png"));
		pnHome.add(lbHome);
		
		pnBooks=new JPanel();
		pnBooks.setBackground(gray);
		pnBooks.setLayout(null);
		pnBooks.setBounds(0,25+pnHome.getHeight(),leftPanel.getWidth(),75);

		lbBooks=new JLabel("manage books");
		lbBooks.setFont(txtPlain);
		lbBooks.setBounds(35,0,leftPanel.getWidth(),75);
		lbBooks.setForeground(white);
		lbBooks.setIcon(new ImageIcon("./adminIcons/icons8_Book_26px.png"));
		pnBooks.add(lbBooks);
		
		pnUsers=new JPanel();
		pnUsers.setBackground(gray);
		pnUsers.setLayout(null);
		pnUsers.setBounds(0,15+pnBooks.getHeight()+pnBooks.getY(),leftPanel.getWidth(),75);
		
		lbUsers=new JLabel("manage users");
		lbUsers.setFont(txtPlain);
		lbUsers.setBounds(35,0,leftPanel.getWidth(),75);
		lbUsers.setForeground(white);
		lbUsers.setIcon(new ImageIcon("./adminIcons/icons8_People_50px.png"));
		pnUsers.add(lbUsers);
		
		pnDefaulters=new JPanel();
		pnDefaulters.setBackground(gray);
		pnDefaulters.setLayout(null);
		pnDefaulters.setBounds(0,15+pnUsers.getHeight()+pnUsers.getY(),leftPanel.getWidth(),75);
		
		lbDefaulters=new JLabel("manage defaulters");
		lbDefaulters.setFont(txtPlain);
		lbDefaulters.setBounds(35,0,leftPanel.getWidth(),75);
		lbDefaulters.setForeground(white);
		lbDefaulters.setIcon(new ImageIcon("./adminIcons/icons8_Sell_26px.png"));
		pnDefaulters.add(lbDefaulters);
		
		pnLogout = new JPanel() {
		    
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        int width = getWidth();
		        int height = getHeight();
		        int arc = 25;
		        g.setColor(Color.RED);
		        g.fillRoundRect(0, 0, width, height, arc, arc);
		    }
		};

		pnLogout.setLayout(null);
		pnLogout.setBackground(gray);
		pnLogout.setBounds(0, 15 + pnDefaulters.getHeight() + pnDefaulters.getY()+70, leftPanel.getWidth() / 2, 50);

		pnLogout.addMouseListener(new MouseAdapter() {
		    
		    public void mouseEntered(MouseEvent e) {
		        pnLogout.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		    }

		    
		    public void mouseExited(MouseEvent e) {
		        pnLogout.setBorder(null);
		    }
		    public void mouseClicked(MouseEvent e) {
		    	new Login().setVisible(true);
		    	dispose();
		    }
		});

		lbLogout = new JLabel("logout");
		lbLogout.setFont(new Font(null, Font.PLAIN, 20));
		lbLogout.setBounds(16, 0, leftPanel.getWidth(), 50);
		lbLogout.setForeground(Color.WHITE);
		lbLogout.setIcon(new ImageIcon("./adminIcons/icons8_Exit_26px_1.png"));
		pnLogout.add(lbLogout);


		
	/////////////////////////////////////////////////////////////  
		topPanel.add(name);
		leftPanel.add(pnHome);
		leftPanel.add(pnBooks);
		leftPanel.add(pnUsers);
		leftPanel.add(pnDefaulters);
		leftPanel.add(pnLogout);
		
////////////////////////////////////////////////////////////////////
		//right components
		Border rBorder= BorderFactory.createMatteBorder(10, 0, 0, 0, Color.red);
		Border bBorder= BorderFactory.createMatteBorder(10, 0, 0, 0, Color.blue);
		
		pnD=new JLabel("");


		pnNBooks=new JPanel();
		pnNBooks.setBackground(white);
		pnNBooks.setBounds(70,115,222,222);
		pnNBooks.setLayout(new BorderLayout());		
		pnNBooks.setBorder(bBorder);
		
		lbNBooks=new JLabel("0");
		lbNBooks.setFont(font);
		lbNBooks.setLayout(new BorderLayout());
		lbNBooks.setHorizontalAlignment(JLabel.CENTER);
		lbNBooks.setIcon(new ImageIcon("./adminIcons/icons8_Book_Shelf_50px.png"));
		pnNBooks.add(lbNBooks);
		
		
		pnNUsers=new JPanel();
		pnNUsers.setBackground(white);
		pnNUsers.setBounds(pnNBooks.getWidth()+pnNBooks.getX()*2,pnNBooks.getY(),pnNBooks.getWidth(),pnNBooks.getHeight());
		pnNUsers.setLayout(new BorderLayout());
		pnNUsers.setBorder(rBorder);
		
		lbNUsers=new JLabel("0");
		lbNUsers.setFont(font);
		lbNUsers.setLayout(new BorderLayout());
		lbNUsers.setHorizontalAlignment(JLabel.CENTER);
		lbNUsers.setIcon(new ImageIcon("./adminIcons/icons8_Conference_26px.png"));
		pnNUsers.add(lbNUsers);
		
		pnNDefaulters=new JPanel();
		pnNDefaulters.setBackground(white);
		pnNDefaulters.setBounds(pnNUsers.getWidth()+pnNUsers.getX()+pnNBooks.getX(),pnNBooks.getY(),pnNBooks.getWidth(),pnNBooks.getHeight());
		pnNDefaulters.setLayout(new BorderLayout());
		pnNDefaulters.setBorder(bBorder);
		
		lbNDefaulters=new JLabel("0");
		lbNDefaulters.setFont(font);
		lbNDefaulters.setLayout(new BorderLayout());
		lbNDefaulters.setHorizontalAlignment(JLabel.CENTER);
		lbNDefaulters.setIcon(new ImageIcon("./adminIcons/icons8_People_50px.png"));
		pnNDefaulters.add(lbNDefaulters);
         
		
		
		
		
		tBooks=new JLabel("no of books");
		tBooks.setBounds(pnNBooks.getX()+40,pnNBooks.getY()-35,pnNBooks.getWidth(),40);
		tBooks.setFont(tfont);
		tUsers=new JLabel("no of users");
		tUsers.setFont(tfont);
		tUsers.setBounds(pnNUsers.getX()+40,pnNUsers.getY()-35,pnNUsers.getWidth(),40);
		tDefaulters=new JLabel("no of defaulters");
		tDefaulters.setFont(tfont);
		tDefaulters.setBounds(pnNDefaulters.getX()+10,pnNDefaulters.getY()-35,pnNDefaulters.getWidth(),40);
		
		
		
		
		
		
		
		
		
		pnN=new JPanel();
		pnN.setLayout(null);
		pnN.setBounds(leftPanel.getWidth(),topPanel.getHeight(),1050,300);
		pnN.add(tBooks);
		pnN.add(tUsers);
		pnN.add(tDefaulters);
		pnN.add(pnNBooks);
		pnN.add(pnNUsers);
		pnN.add(pnNDefaulters);
	//	pnN.setBackground(lGray);
		
		tbBooks=new JTable();
		pnT=new JPanel();
		pnT.setLayout(null);
		pnT.setBounds(leftPanel.getWidth(),topPanel.getHeight()+pnN.getHeight(),1050,430);
		pnT.add(new JScrollPane(tbBooks));
		//pnT.setBackground(black);
		
		
		
/////////////////////////////
		add(leftPanel);
		add(topPanel);
		add(pnN);
		add(pnT);
		setIconImage(new ImageIcon("./icons/library-3.png.png").getImage());
		setTitle("Library Managment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setSize(1000,660);
	////////////////////////////////////////////////////////////////////////
		//Actions
		lbBooks.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) 
			{		pnBooks.setBackground(black);
			}
			public void mouseExited(MouseEvent e) 
			{		pnBooks.setBackground(gray);
			}
			public void mouseClicked(MouseEvent e) {
				new Books().setVisible(true);
				dispose();
			}
		});
		
		lbUsers.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) 
			{		pnUsers.setBackground(black);
			}
			public void mouseExited(MouseEvent e) 
			{		pnUsers.setBackground(gray);
			}
			public void mouseClicked(MouseEvent e) {
				new Users().setVisible(true);
				dispose();
			}
		});
		
		lbDefaulters.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) 
			{		pnDefaulters.setBackground(black);
			}
			public void mouseExited(MouseEvent e) 
			{		pnDefaulters.setBackground(gray);
			}
			
			public void mouseClicked(MouseEvent e) {
				new Defaulters().setVisible(true);
				dispose();
			}
		});
		
	
  }

  private void noOF(String tableName,String attribute,JLabel label) {
	  Connection con=new Connect().getConnection();
	  try {
		Statement stt=con.createStatement();
		ResultSet result=stt.executeQuery("select count(distinct "+attribute+") as quant from "+tableName+" ");
		boolean isExist=result.next();
		if(isExist) {
		label.setText(result.getObject("quant").toString());
		}
		else
			label.setText(String.valueOf(0));

	} catch (SQLException e) {
		e.printStackTrace();
	}
	  
  }
  private void noOF(String tableName,JLabel label,LocalDate now) {
	  Connection con=new Connect().getConnection();
	  try {
		Statement stt=con.createStatement();
		ResultSet result=stt.executeQuery("select *  from "+tableName+" ");
		LocalDate date;
		 Integer quant=0;
		while(result.next()
) {
		     date=result.getDate("return_date").toLocalDate();
		   
		        if(date.isBefore(now)){
		        	quant+=1;
		        	
		         
		        }
		label.setText(quant.toString());
		}
		

	} catch (SQLException e) {
		e.printStackTrace();
	}
	  
  }
  
  
  
  public void tableDisplay(String tname, JTable dt){
	    Connection con=Connect.getConnection();
	    DefaultTableModel tm;
	    try{
	           tm=(DefaultTableModel)dt.getModel();

	           tm.setRowCount(0);

	            Statement stt=con.createStatement();
	    ResultSet result=stt.executeQuery("select * from "+tname);
	    while(result.next()){
	        
	        String bookname=result.getString("bookname");
	          String author=result.getString("bookauthor");
	        int bookid=result.getInt("bookid");
	        int quant=result.getInt("bookquantity");
	        int ava=result.getInt("bookavaliable");
	  
	        Object[] data={bookid,bookname,author,quant,ava};
	            tm=(DefaultTableModel)dt.getModel();
	            tm.addRow(data);

	    }
	    result.close();
	    stt.close();
	    con.close();
	    }
	    catch(SQLException sqle){
	        sqle.printStackTrace();
	    }
	    
	}
	  
	
    public static void main(String args[]) {
      EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }
}
