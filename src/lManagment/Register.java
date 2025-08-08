package lManagment;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
public class Register extends JFrame{
///////////////////////////////////////////////////////////////////////////////////////		


	private JPanel leftPanel;
	private JLabel lbRegister;
	private JLabel lbName;
	private JLabel lbId;
	private JLabel lbEmail;
	private JLabel lbPno;
	private JLabel lbDep;
	private JLabel lbForgot;
	private JLabel rightLabel;
	private JTextField tfName;

	private JTextField tfId;
	private JTextField tfEmail;
	private JTextField tfPno;
	private JComboBox<?> combDep;
	private JButton btnRegister;
	private JButton btnLogin;
	private JButton btnClear;	
	Register(){
		initComp();
    	setExtendedState(JFrame.MAXIMIZED_BOTH);

		
	}
	private void initComp() {
		
		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        int screenWidth = screenSize.width;
	        int screenHeight = screenSize.height;
	        
		leftPanel=new JPanel();
		leftPanel.setBounds(0,0,500,screenHeight);
		leftPanel.setBackground(Color.blue);
		leftPanel.setLayout(null);
		
		lbRegister=new JLabel("Register page");
		lbRegister.setForeground(Color.white);
		lbRegister.setFont(new Font(null,Font.BOLD,25));
		lbRegister.setBounds((int)(leftPanel.getWidth()/2.4),50,350,70);
		
		lbName=new JLabel("name");
		lbName.setForeground(Color.white);
		lbName.setFont(new Font(null,Font.PLAIN,16));
		lbName.setBounds((int)(leftPanel.getWidth()/7.6),leftPanel.getHeight()/4,200,70);
		
		tfName=new JTextField();
		tfName.setFont(new Font(null,Font.PLAIN,16));
		tfName.setBounds(leftPanel.getWidth()/3,leftPanel.getHeight()/4+22,240,28);
		
		
		lbId=new JLabel("id");
		lbId.setForeground(Color.white);
		lbId.setFont(new Font(null,Font.PLAIN,16));
		lbId.setBounds((int)(leftPanel.getWidth()/7.6),leftPanel.getHeight()/3,200,70);
		
		tfId=new JTextField();
		tfId.setFont(new Font(null,Font.PLAIN,16));
		tfId.setBounds(leftPanel.getWidth()/3,leftPanel.getHeight()/3+22,240,28);

		
		lbEmail=new JLabel("email");
		lbEmail.setForeground(Color.white);
		lbEmail.setFont(new Font(null,Font.PLAIN,16));
		lbEmail.setBounds((int)(leftPanel.getWidth()/7.6),leftPanel.getHeight()/3+50,200,70);
		
		tfEmail=new JTextField();
		tfEmail.setFont(new Font(null,Font.PLAIN,16));
		tfEmail.setBounds(leftPanel.getWidth()/3,leftPanel.getHeight()/3+72,240,28);
		
		lbPno=new JLabel("pno");
		lbPno.setForeground(Color.white);
		lbPno.setFont(new Font(null,Font.PLAIN,16));
		lbPno.setBounds((int)(leftPanel.getWidth()/7.6),leftPanel.getHeight()/3+100,200,70);
		
		tfPno=new JTextField();
		tfPno.setFont(new Font(null,Font.PLAIN,16));
		tfPno.setBounds(leftPanel.getWidth()/3,leftPanel.getHeight()/3+122,240,28);
	
		lbDep=new JLabel("Department");
		lbDep.setForeground(Color.white);
		lbDep.setFont(new Font(null,Font.PLAIN,16));
		lbDep.setBounds((int)(leftPanel.getWidth()/7.6),leftPanel.getHeight()/3+150,200,70);
		
		String[] deplist= {"Computer Science","Electrical Engineering "};
		combDep=new JComboBox(deplist);
		combDep.setFont(new Font(null,Font.PLAIN,16));
		combDep.setBounds(leftPanel.getWidth()/3,leftPanel.getHeight()/3+172,240,28);
	
		btnRegister=new JButton("Register");
		btnRegister.setBounds(leftPanel.getWidth()/7,(int)(leftPanel.getHeight()/1.45),85,28);
		
		btnLogin=new JButton("login");
		btnLogin.setBounds((int)(leftPanel.getWidth()/2.5),(int)(leftPanel.getHeight()/1.45),85,28);
		
		btnClear=new JButton("clear");
		btnClear.setBounds((int)(leftPanel.getWidth()/1.5),(int)(leftPanel.getHeight()/1.45),85,28);
		
		

		
		lbForgot=new JLabel("Forgot password?");
		lbForgot.setForeground(Color.yellow);

		lbForgot.setBounds(leftPanel.getWidth()-200,(int)(leftPanel.getHeight()-150),200,28);
         
		
		
		leftPanel.add(lbRegister);	
		leftPanel.add(lbName);
		leftPanel.add(tfName);		
		leftPanel.add(lbId);
		leftPanel.add(tfId);
		leftPanel.add(lbEmail);
		leftPanel.add(tfEmail);
		leftPanel.add(lbPno);
		leftPanel.add(tfPno);
		leftPanel.add(lbDep);
		leftPanel.add(combDep);
		leftPanel.add(btnRegister);
	    leftPanel.add(btnLogin);
	    leftPanel.add(btnClear);
	    
		leftPanel.add(lbForgot);
		
///////////////////////////////////////////////////////////////////////////////////////		
		///actions 
	
		btnRegister.addActionListener(a->{
			String name=tfName.getText();
			String id=tfId.getText();
			String email=tfEmail.getText();
			String pno=tfPno.getText();
			String dep=combDep.getSelectedItem().toString();
			if(!name.trim().equals("")&&!id.trim().equals("")&&!email.trim().equals("")&&!pno.trim().equals("")) {
				tryToRegister(name,id,email,pno,dep);
			}
			else 				
				new JOptionPane().showMessageDialog(this, "all fields are required","fill",JOptionPane.WARNING_MESSAGE);
		});

		
		btnLogin.addActionListener(a->{
			new Login().setVisible(true);
			dispose();
		});
		
		btnClear.addActionListener(a->{
			clear();
		});
		
		
		lbForgot.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
					  Connection con=Connect.getConnection();
					        String id=tfId.getText().trim();
					    if(!id.isEmpty()) {

					  try{
					      Statement stt=con.createStatement();
					      ResultSet result=stt.executeQuery("select * from students where stid='"+id+"'");
					      if(result.next()){
					           JOptionPane.showMessageDialog(null, "dear '"+result.getString("stname")+"', your library id is '"+result.getInt("library_id")+"'","show data",JOptionPane.INFORMATION_MESSAGE);
					           clear();
					      }
					      else {
					           JOptionPane.showMessageDialog(null, "there is no registered user with this id");
					      }
					  }
					  catch(SQLException ex){
					      ex.printStackTrace();
					  }
					  }
			else 
JOptionPane.showMessageDialog(null, "please enter your school id","show data",JOptionPane.ERROR_MESSAGE); 
			}
		});
	

		
		rightLabel=new JLabel();
		rightLabel.setOpaque(true);
		rightLabel.setBounds(leftPanel.getWidth(),-30,screenWidth-leftPanel.getWidth(),screenHeight);
		rightLabel.setIcon(new ImageIcon("./icons/library-3.png.png"));
		add(rightLabel);
		
		/////////////////////////////////////////////////////////////////


		add(leftPanel);
		setIconImage(new ImageIcon("./icons/library-3.png.png").getImage());
		setTitle("Library Managment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setSize(1000,660);
			
	}
	
	
	private void tryToRegister(String name,String id,String email,String pno,String dep) {
	    	
	    	Connection con=new Connect().getConnection();
	        
	        try(
	        Statement stt=con.createStatement();){
	        stt.execute("insert into students(stid,stname,stemail,stpno,department) values('"+id+"','"+name+"','"+email+"','"+pno+"','"+dep+"')");
	        ResultSet result=stt.executeQuery("select library_id,stname from students where stid='"+id+"'");
	        if(result.next()){
	            JOptionPane.showMessageDialog(this, "dear '"+result.getString("stname")+"',regisetered  successfully your library id is '"+result.getInt("library_id")+"'");
	            clear();
	        }
	        else
	        	JOptionPane.showMessageDialog(this, "something went wrong","error",JOptionPane.ERROR_MESSAGE);
	        }
 catch (SQLException e) {
				e.printStackTrace();
			}
	    }

	private void clear() {
		tfName.setText("");
		tfId.setText("");
		tfEmail.setText("");
		tfPno.setText("");
		combDep.setSelectedIndex(0);
	}
	
    public static void main(String args[]) {
      EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }
    
  
}

