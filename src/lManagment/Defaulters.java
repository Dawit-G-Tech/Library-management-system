package lManagment;
import javax.swing.*;
import java.time.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
public class Defaulters extends JFrame{
///////////////////////////////////////////////////////////////////////////////////////		

	// lb represents Label & tf represents textfield & pf represents passwordfield
	private JPanel leftPanel;
	
	private JLabel lbUserId;
	private JLabel lbIssueDate;
	private JLabel lbBack;
	private JLabel	lbDefaulters;
	private JLabel lbSearchById;
	private JLabel	lbSearchByDate;
	
	private JComboBox<String> sorttxt;
	private JTable deftable;

	
	private JTextField tfUserId;
	private JTextField tfIssueDate;
	final Font tfont =new Font(null,Font.PLAIN,26);

	DefaultTableModel dtm;
	JTable table;
	JTable bydatetable;
	JTable byidtable;
	JScrollPane sp;
	JPanel pn;
	
	private JButton btnSearchById;
	private JButton btnSearchByDate;

	Defaulters(){
		initComp();
    	setExtendedState(JFrame.MAXIMIZED_BOTH);

	}
	private void initComp() {
		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        int screenHeight = screenSize.height;
	        
		leftPanel=new JPanel();
		leftPanel.setBounds(0,0,500,screenHeight);
		leftPanel.setBackground(new Color(255,54,0));
		leftPanel.setLayout(null);
		lbBack=new JLabel("<<<");
		lbBack.setFont(new Font(null,Font.BOLD,25));
		lbBack.setBounds(20,5,70,50);
		
		lbSearchByDate=new JLabel("search by date");
		lbSearchById=new JLabel("search by id");

		lbSearchById.setFont(tfont);
		lbSearchById.setBounds(200,30,400,40);
		lbSearchById.setForeground(Color.white);
		
		lbSearchByDate.setFont(tfont);
		lbSearchByDate.setBounds(200,300,400,40);
		lbSearchByDate.setForeground(Color.white);
		
		leftPanel.add(lbSearchById);
		leftPanel.add(lbSearchByDate);
		
		
		lbUserId=new JLabel("User Id");
		lbUserId.setForeground(Color.white);
		lbUserId.setFont(new Font(null,Font.PLAIN,16));
		lbUserId.setBounds((int)(leftPanel.getWidth()/7.6),leftPanel.getHeight()/10,200,70);
		
		tfUserId=new JTextField();
		tfUserId.setFont(new Font(null,Font.PLAIN,16));
		tfUserId.setBounds(leftPanel.getWidth()/3,leftPanel.getHeight()/10+22,200,28);
		
		
		lbIssueDate=new JLabel("yyyy-mm-dd");
		lbIssueDate.setForeground(Color.white);
		lbIssueDate.setFont(new Font(null,Font.PLAIN,16));
		lbIssueDate.setBounds((int)(leftPanel.getWidth()/7.6),leftPanel.getHeight()/2,200,70);
		
		tfIssueDate=new JTextField();
		tfIssueDate.setFont(new Font(null,Font.PLAIN,16));
		tfIssueDate.setBounds(leftPanel.getWidth()/3,leftPanel.getHeight()/2+22,200,28);

		
		
	
		btnSearchById=new JButton("search");
		btnSearchById.setBounds((int)(leftPanel.getWidth()/1.3),leftPanel.getHeight()/8,85,28);	
		
		btnSearchByDate=new JButton("search");
		btnSearchByDate.setBounds((int)(leftPanel.getWidth()/1.3),(int)(leftPanel.getHeight()/1.89),85,28);
		
	
		
		

         
		
		leftPanel.add(lbBack);
		leftPanel.add(lbUserId);
		leftPanel.add(tfUserId);		
		leftPanel.add(lbIssueDate);
		leftPanel.add(tfIssueDate);
		leftPanel.add(btnSearchById);
		leftPanel.add(btnSearchByDate);

		setIconImage(new ImageIcon("./icons/library-3.png.png").getImage());
		setTitle("Library Managment");   		
///////////////////////////////////////////////////////////////////////////////////////		
		///actions 
	
		btnSearchById.addActionListener(a->{
			searchById();
		});
		btnSearchByDate.addActionListener(a->{
	        if(tfIssueDate.getText().trim()!="")
	        	searchByDate();

		});
				
		lbBack.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new Home().setVisible(true);
				dispose();
			}
		});
		
	

		/////////////////////////////////////////////////////////////////
	 
		tableDisplay();

////////////////////////////////////////

		add(leftPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setSize(1000,660);
			
	}
	
	

	private void tableDisplay() {
    pn = new JPanel();
    sp = new JScrollPane();
    deftable = new JTable();
    sorttxt = new JComboBox<>();

    try (Connection con = Connect.getConnection()) {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Library ID");
        dtm.addColumn("Book ID");
        dtm.addColumn("Issue Date");
        dtm.addColumn("Return Date");

        int sort = sorttxt.getSelectedIndex();
        try (Statement stt = con.createStatement();
             ResultSet result = stt.executeQuery("select * from users order by return_date " + (sort == 0 ? "asc" : "desc"))) {
            while (result.next()) {
                LocalDate now = LocalDate.now();
                LocalDate date = result.getDate("return_date").toLocalDate();
                if (date.isBefore(now)) {
                    Object[] def = {result.getInt("library_id"), result.getInt("book_id"), result.getString("issue_date"), result.getString("return_date")};
                    dtm.addRow(def);
                }
            }
        }

        deftable.setModel(dtm);
        sp = new JScrollPane(deftable);
        lbDefaulters=new JLabel("Defaulters list");
        lbDefaulters.setBounds(230,30,300,30);
        lbDefaulters.setFont(new Font(null,Font.BOLD,20));
        sp.setBounds(50,60,500,500);
        pn.add(sp);
        pn.add(lbDefaulters);
        pn.setBounds(leftPanel.getWidth(), 0, 600, 600);
        pn.setLayout(null);
    //    pn.setBackground(Color.BLACK);
        add(pn);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
       
	public void searchByDate() {
    try {
        Connection con = Connect.getConnection();
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Library ID");
        dtm.addColumn("Book ID");
        dtm.addColumn("Issue Date");
        dtm.addColumn("Return Date");

        Statement stt = con.createStatement();
        ResultSet result = stt.executeQuery("select * from users where return_date='" + tfIssueDate.getText() + "'");
        while (result.next()) {
            Object[] def = {result.getInt("library_id"), result.getInt("book_id"), result.getString("issue_date"), result.getString("return_date")};
            dtm.addRow(def);
        }

        bydatetable = new JTable(dtm); 
        JScrollPane spp = new JScrollPane(bydatetable);
        spp.setBounds(60, 400, 400, 170);
        leftPanel.add(spp);

    } catch (Exception e) {
        e.printStackTrace();
    }
}
	
	public void searchById() {
    try {
        Connection con = Connect.getConnection();
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Library ID");
        dtm.addColumn("Book ID");
        dtm.addColumn("Issue Date");
        dtm.addColumn("Return Date");

        Statement stt = con.createStatement();
        ResultSet result = stt.executeQuery("select * from users where library_id='" + Integer.parseInt(tfUserId.getText()) + "'");
        if (result.next()) {
            Object[] def = {result.getInt("library_id"), result.getInt("book_id"), result.getString("issue_date"), result.getString("return_date")};
            dtm.addRow(def);
        } else {
            JOptionPane.showMessageDialog(this, "There is no issue with this ID");
        }

        byidtable = new JTable(dtm); 
        JScrollPane spp = new JScrollPane(byidtable);
        spp.setBounds(60, 130, 400, 170);
        leftPanel.add(spp);

    } catch (Exception e) {
        e.printStackTrace();
    }
}

   public static void main(String args[]) {
      EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Defaulters().setVisible(true);
            }
        });
    }
    
  
}

