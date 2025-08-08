package lManagment;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
public class Books extends JFrame{
///////////////////////////////////////////////////////////////////////////////////////		

	// lb represents Label & tf represents textfield & pf represents passwordfield
	private JPanel leftPanel;
	private  JPanel pnTable;
	private JLabel lbBooks;
	private JLabel lbName;
	private JLabel lbAuthor;
	private JLabel lbQuant;
	private JLabel lbPrice;
	
	private JLabel lbBookDetails;
	
	private JLabel lbBack;
	
	private JTextField tfName;
	private JTextField tfAuthor;
	private JTextField tfQuant;
	private JTextField tfPrice;
	
	DefaultTableModel dtm;
	JTable table;
	JScrollPane sp;
	
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;	
	Books(){
		initComp();
    	setExtendedState(JFrame.MAXIMIZED_BOTH);

		
	}
	private void initComp() {
		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        int screenHeight = screenSize.height;
	        
		leftPanel=new JPanel();
		leftPanel.setBounds(0,0,500,screenHeight);
		leftPanel.setBackground(new Color(255,153,0));
		leftPanel.setLayout(null);
		
		lbBack=new JLabel("<<<");
		lbBack.setFont(new Font(null,Font.BOLD,25));
		lbBack.setBounds(20,5,70,50);
		
		lbBooks=new JLabel("Manange Books ");
		lbBooks.setForeground(Color.white);
		lbBooks.setFont(new Font(null,Font.BOLD,25));
		lbBooks.setBounds((int)(leftPanel.getWidth()/2.6),70,350,70);
		
		lbName=new JLabel("name");
		lbName.setForeground(Color.white);
		lbName.setFont(new Font(null,Font.PLAIN,16));
		lbName.setBounds((int)(leftPanel.getWidth()/7.6),leftPanel.getHeight()/4,200,70);
		
		tfName=new JTextField();
		tfName.setFont(new Font(null,Font.PLAIN,16));
		tfName.setBounds(leftPanel.getWidth()/3,leftPanel.getHeight()/4+22,240,28);
		
		
		lbAuthor=new JLabel("Author");
		lbAuthor.setForeground(Color.white);
		lbAuthor.setFont(new Font(null,Font.PLAIN,16));
		lbAuthor.setBounds((int)(leftPanel.getWidth()/7.6),leftPanel.getHeight()/3,200,70);
		
		tfAuthor=new JTextField();
		tfAuthor.setFont(new Font(null,Font.PLAIN,16));
		tfAuthor.setBounds(leftPanel.getWidth()/3,leftPanel.getHeight()/3+22,240,28);

		
		lbQuant=new JLabel("Quantity");
		lbQuant.setForeground(Color.white);
		lbQuant.setFont(new Font(null,Font.PLAIN,16));
		lbQuant.setBounds((int)(leftPanel.getWidth()/7.6),leftPanel.getHeight()/3+50,200,70);
		
		tfQuant=new JTextField();
		tfQuant.setFont(new Font(null,Font.PLAIN,16));
		tfQuant.setBounds(leftPanel.getWidth()/3,leftPanel.getHeight()/3+72,240,28);
		
		lbPrice=new JLabel("Price");
		lbPrice.setForeground(Color.white);
		lbPrice.setFont(new Font(null,Font.PLAIN,16));
		lbPrice.setBounds((int)(leftPanel.getWidth()/7.6),leftPanel.getHeight()/3+100,200,70);
		
		tfPrice=new JTextField();
		tfPrice.setFont(new Font(null,Font.PLAIN,16));
		tfPrice.setBounds(leftPanel.getWidth()/3,leftPanel.getHeight()/3+122,240,28);
	

		
	
		btnAdd=new JButton("ADD");
		btnAdd.setBounds(leftPanel.getWidth()/7,(int)(leftPanel.getHeight()/1.45),85,28);
		
		btnUpdate=new JButton("UPDATE");
		btnUpdate.setBounds((int)(leftPanel.getWidth()/2.5),(int)(leftPanel.getHeight()/1.45),85,28);
		
		btnDelete=new JButton("DELETE");
		btnDelete.setBounds((int)(leftPanel.getWidth()/1.5),(int)(leftPanel.getHeight()/1.45),85,28);
		
		

         
		
		
		leftPanel.add(lbBack);	
		leftPanel.add(lbBooks);	
		leftPanel.add(lbName);
		leftPanel.add(tfName);		
		leftPanel.add(lbAuthor);
		leftPanel.add(tfAuthor);
		leftPanel.add(lbQuant);
		leftPanel.add(tfQuant);
		leftPanel.add(lbPrice);
		leftPanel.add(tfPrice);
		leftPanel.add(btnAdd);
	    leftPanel.add(btnUpdate);
	    leftPanel.add(btnDelete);
	    		
///////////////////////////////////////////////////////////////////////////////////////		
		///actions 
	
		btnAdd.addActionListener(a->{
			String name=tfName.getText();
			String author=tfAuthor.getText();
			String quant=tfQuant.getText();
			String price=tfPrice.getText();
			if(!name.trim().equals("")&&!author.trim().equals("")&&!quant.trim().equals("")&&!price.trim().equals("")) {
				tryToAdd(name,author,quant,price);
			}
			else 				
				new JOptionPane().showMessageDialog(this, "all fields are required","fill",JOptionPane.WARNING_MESSAGE);
		});

		btnUpdate.addActionListener(a->{updateData();});
		
		lbBack.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new Home().setVisible(true);
				dispose();
			}
		});			
		
		btnDelete.addActionListener(a->{
			deleteData();
			clear();
		});
		/////////////////////////////////////////////////////////////////

 	tableDisplay();
	    
		
//////////////////////////////////////////
		add(leftPanel);
		setIconImage(new ImageIcon("./icons/library-3.png.png").getImage());
		setTitle("Library Managment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setSize(1000,660);
			
	}
	
	
	private void tryToAdd(String name,String author,String quant,String price) {
	    	
	    	Connection con=new Connect().getConnection();
	    	
	        try(
	        Statement stt=con.createStatement();){
	        	boolean isAdded= stt.execute("insert into books(bookname,bookauthor,bookquantity,bookavaliable,bookprice) values('"+name+"','"+author+"','"+quant+"','"+quant+"','"+price+"')");
               if(!isAdded) {
	            clear();
	   JOptionPane.showMessageDialog(this, "Book Successfully Registered:  {"+name+"]");
               }
	        else
	        	JOptionPane.showMessageDialog(this, "something went error please try again","error",JOptionPane.ERROR_MESSAGE);
	        }
 catch (SQLException e) {
				e.printStackTrace();
			}
	    }

	public void updateData() {
	    int id = Integer.parseInt(JOptionPane.showInputDialog("Enter bookid"));
	    String bookname = tfName.getText().trim();
	    String author = tfAuthor.getText().trim();
	    int bookquant = Integer.parseInt(tfQuant.getText().trim());
	    int bookprice = Integer.parseInt(tfPrice.getText().trim());
	    Connection con = Connect.getConnection();
	    try {
	        Statement stt = con.createStatement();
	        stt.execute("UPDATE books SET bookname='" + bookname + "', bookauthor='" + author + "', bookquantity=" + bookquant + ",bookprice="+bookprice+", bookavaliable='"+bookquant+"' WHERE bookid=" + id);      clear();
	      tableDisplay();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    }

	private void tableDisplay() {
		
    	pnTable=new JPanel();
    	
   	 dtm=new DefaultTableModel();
   	 dtm.setRowCount(0);
   	 lbBookDetails=new JLabel("Book Details");
   	 lbBookDetails.setBounds(250,5,300,20);
   	 lbBookDetails.setFont(new Font(null,Font.BOLD,20));
   	try {
			Connection con=Connect.getConnection();
			Statement stt=con.createStatement();
			ResultSet result=stt.executeQuery("select * from books order by 2 desc");    
			ResultSetMetaData rsmd=result.getMetaData();
			int clmns=rsmd.getColumnCount();
			for(int i=1;i<=clmns;i++) {
				dtm.addColumn(rsmd.getColumnName(i));
			}
			while(result.next()) {
				String[] data=new String[clmns];
				for(int i=1;i<=clmns;i++) {
					data[i-1]=result.getString(i);
				}
				dtm.addRow(data);
			}
			table=new JTable();
			table.setModel(dtm);
			sp=new JScrollPane(table);
			sp.setBounds(30,30,550,450);
			pnTable.setLayout(null);
			pnTable.add(lbBookDetails);
			pnTable.add(sp);
			pnTable.setBounds(leftPanel.getWidth()+100,50,600,500);
			//pnTable.setBackground(Color.gray);
			add(pnTable);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  table.addMouseListener(new MouseAdapter() {
    public void mouseClicked(MouseEvent e) {
        int selected = table.getSelectedRow();
        if (selected >= 0) { // Check if a row is selected
            tfName.setText(table.getValueAt(selected, 1).toString());
            tfAuthor.setText(table.getValueAt(selected, 2).toString());
            tfQuant.setText(table.getValueAt(selected, 3).toString());
            tfPrice.setText(table.getValueAt(selected, 5).toString()); 
            table.setSelectionBackground(Color.blue);
            table.setSelectionForeground(Color.white);
        }
    }
});

	}
	
	public void deleteData(){
	    
	    String bookname=tfName.getText().trim();
	    String author=tfAuthor.getText().trim();
	     Integer bookquant=Integer.parseInt(tfQuant.getText().trim());
	        Connection con=Connect.getConnection();
	    try{
	        if(!bookname.isBlank()&& !bookquant.toString().isBlank() && !author.isBlank()){
	    Statement stt=con.createStatement();
	    ResultSet result=stt.executeQuery("select * from books where bookname='"+bookname+"' and bookauthor='"+author+"' ");
	    if(result.next()){
	        
	 stt.execute("delete  from books  where bookname='"+bookname+"' and bookauthor='"+author+"'" );
	        result.close();

	         stt.close();    }
	       

	     tableDisplay();
	    
	        }
	        con.close();
	       
	    }
	    catch(SQLException sqle){
	        sqle.printStackTrace();
	    }
	}
	  
	private void clear() {
		tfName.setText("");
		tfAuthor.setText("");
		tfQuant.setText("");
		tfPrice.setText("");
	}
	
    public static void main(String args[]) {
      EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Books().setVisible(true);
            }
        });
    }
    
  
}

