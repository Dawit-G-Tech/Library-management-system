package lManagment;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
public class Users extends JFrame{
///////////////////////////////////////////////////////////////////////////////////////		

	// lb represents Label & tf represents textfield & pf represents passwordfield
	private JPanel leftPanel;
	private JLabel lbUsers;
	
	private JLabel lbUserId;
	private JLabel lbBookId;
	private JLabel lbBack;
	
	private JLabel lbUserDetails;

	
	private JTextField tfUserId;
	private JTextField tfBookId;
	
	DefaultTableModel dtm;
	JTable table;
	JScrollPane sp;
	JPanel pn;
	
	private JButton btnAdd;
	private JButton btnDelete;	
	Users(){
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
		
		lbUsers=new JLabel("Manange Users ");
		lbUsers.setForeground(Color.white);
		lbUsers.setFont(new Font(null,Font.BOLD,25));
		lbUsers.setBounds((int)(leftPanel.getWidth()/2.6),70,350,70);
		
		lbUserId=new JLabel("User Id");
		lbUserId.setForeground(Color.white);
		lbUserId.setFont(new Font(null,Font.PLAIN,16));
		lbUserId.setBounds((int)(leftPanel.getWidth()/7.6),leftPanel.getHeight()/4,200,70);
		
		tfUserId=new JTextField();
		tfUserId.setFont(new Font(null,Font.PLAIN,16));
		tfUserId.setBounds(leftPanel.getWidth()/3,leftPanel.getHeight()/4+22,240,28);
		
		
		lbBookId=new JLabel("Book Id");
		lbBookId.setForeground(Color.white);
		lbBookId.setFont(new Font(null,Font.PLAIN,16));
		lbBookId.setBounds((int)(leftPanel.getWidth()/7.6),leftPanel.getHeight()/3,200,70);
		
		tfBookId=new JTextField();
		tfBookId.setFont(new Font(null,Font.PLAIN,16));
		tfBookId.setBounds(leftPanel.getWidth()/3,leftPanel.getHeight()/3+22,240,28);

		
		
	
		btnAdd=new JButton("ADD");
		btnAdd.setBounds(leftPanel.getWidth()/3,(int)(leftPanel.getHeight()/1.9),85,28);
		

		
		btnDelete=new JButton("return");
		btnDelete.setBounds((int)(leftPanel.getWidth()/1.6),(int)(leftPanel.getHeight()/1.9),85,28);
		
		

         
		
		leftPanel.add(lbBack);
		leftPanel.add(lbUsers);	
		leftPanel.add(lbUserId);
		leftPanel.add(tfUserId);		
		leftPanel.add(lbBookId);
		leftPanel.add(tfBookId);
		leftPanel.add(btnAdd);
	    leftPanel.add(btnDelete);
	    		
///////////////////////////////////////////////////////////////////////////////////////		
		///actions 
	
		btnAdd.addActionListener(a->{
			String userId=tfUserId.getText();
			String bookId=tfBookId.getText();
			if(!userId.trim().equals("")&&!bookId.trim().equals("")) {
			//	tryToBorrow(Integer.parseInt(userId),Integer.parseInt(bookId));
				add();
			}
			else 				
				new JOptionPane().showMessageDialog(this, "all fields are required","fill",JOptionPane.WARNING_MESSAGE);
		});

		btnDelete.addActionListener(a->{
			deleteData();
		});
		
				
		lbBack.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new Home().setVisible(true);
				dispose();
			}
		});
		
		tfBookId.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				Connection con=Connect.getConnection();
				String bookid=tfBookId.getText();
				if(!bookid.isEmpty()) {

				int bookId=Integer.parseInt(bookid);
				try {
					Statement stt=con.createStatement();
					ResultSet result=stt.executeQuery("select * from books where bookid="+bookId+"");
					boolean isExist=result.next();
					if(isExist) {
						int ava=result.getInt("bookavaliable");
						if(ava<1) {
							JOptionPane.showMessageDialog(null, "there is no avaliable book quantity");
						}
					}
					else
	JOptionPane.showMessageDialog(null, "there is no registered book with this id");				
							
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
			}
		});
		
		
		
tfUserId.addFocusListener(new FocusAdapter() {
    public void focusLost(FocusEvent e) {
        Connection con = Connect.getConnection();
        String userId = tfUserId.getText();
        if (!userId.isEmpty()) {
            int libraryId = Integer.parseInt(userId);
            try (Statement stt = con.createStatement();
                 ResultSet result = stt.executeQuery("select * from users where library_id=" + libraryId)) {
                if (result.next()) {
                    int borrowedBookId = result.getInt("book_id");
                    if (borrowedBookId != 0) {
                        JOptionPane.showMessageDialog(null, "book with ID "+ borrowedBookId+" is currently checked out to you. " );
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);

                    } else {
                    }
                } else {
                    ResultSet rs = stt.executeQuery("select * from students where library_id=" + libraryId);
                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(null, "The user doesn't have an account");
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(false);
                    }
                    else {
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(true);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
});

		/////////////////////////////////////////////////////////////////
	 
		tableDisplay();
	    
////////////////////////////////////////

		add(leftPanel);
		setIconImage(new ImageIcon("./icons/library-3.png.png").getImage());
		setTitle("Library Managment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setSize(1000,660);
			
	}
	
	
	private void tryToBorrow(int userId,int bookId) {
	    	
//	    	Connection con=new Connect().getConnection();
//	    	
//	        try(
//	        Statement stt=con.createStatement();){
//	        	boolean isAdded= stt.execute("insert into users(library_id,book_id) value('"+userId+"','"+bookId+"')");
//               if(!isAdded)
//	            clear();
//	        
//	        else
//	        	JOptionPane.showMessageDialog(this, "something went error please try again","error",JOptionPane.ERROR_MESSAGE);
//	        }
// catch (SQLException e) {
//				e.printStackTrace();
//			}
	    }

	
	
	
	public void add() {
	    String userText = tfUserId.getText();
	    String bookText = tfBookId.getText().trim();

	    try {
	        int userId = Integer.parseInt(userText);
	        int bookId = Integer.parseInt(bookText);

	        Connection connection = Connect.getConnection();

	        Statement statement = connection.createStatement();

	        String insertUserQuery = "INSERT INTO users (library_id, book_id) VALUES (" + userId + ", " + bookId + ")";
	        statement.executeUpdate(insertUserQuery);

	        String decrementQuery = "update books SET bookavaliable = bookavaliable - 1 WHERE bookid = " + bookId;
	        statement.executeUpdate(decrementQuery);

	        statement.close();
	        connection.close();
	        
	    } catch (NumberFormatException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    tfUserId.setText("");
	    tfBookId.setText("");
	    tableDisplay();
	  
	}

	   public void deleteData(){
		    Connection con=Connect.getConnection();
		    try{
		    Statement stt=con.createStatement();
		     int bookid=Integer.parseInt(tfBookId.getText().trim());
		     int libraryid=Integer.parseInt(tfUserId.getText().trim());
		    stt.execute("update books set bookavaliable=bookavaliable+1 where bookid="+bookid);
		    stt.execute("delete from users where library_id="+libraryid);
		    tableDisplay();
		    }
		    catch(Exception e){
		        e.printStackTrace();
		    }
		   tfUserId.setText("");
		   tfBookId.setText("");
		 
		}
		 
	public void tableDisplay() {
	   	 pn=new JPanel();
    	 dtm=new DefaultTableModel();
    	 lbUserDetails=new JLabel("User Details");
    	 lbUserDetails.setBounds(300,20,200,30);
    	 lbUserDetails.setFont(new Font(null,Font.BOLD,25));
    	try {
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","");
			Statement stt=con.createStatement();
			ResultSet result=stt.executeQuery("select * from users ");
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
			sp.setBounds(100,50,550,400);
			
			pn.setBounds(leftPanel.getWidth(),0,700,500);
			pn.setLayout(null);
			pn.add(sp);
			pn.add(lbUserDetails);
			//pn.setBackground(Color.black);
			add(pn);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	table.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent e) {
    			int selected=table.getSelectedRow();
    			tfUserId.setText(table.getValueAt(selected, 1).toString());
    			tfBookId.setText(table.getValueAt(selected, 2).toString());
    			  table.setSelectionBackground(Color.blue);
    	          table.setSelectionForeground(Color.white);    			
    		}
    	});
    	
	}
    public static void main(String args[]) {
      EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Users().setVisible(true);
            }
        });
    }
    
  
}

