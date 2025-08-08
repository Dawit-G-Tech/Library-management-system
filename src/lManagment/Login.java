package lManagment;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
public class Login extends JFrame{
///////////////////////////////////////////////////////////////////////////////////////		

	// lb represents Label & tf represents textfield & pf represents passwordfield
	private JPanel leftPanel;
	private JLabel lbLoginpage;
	private JLabel lbUserName;
	private JLabel lbPassword;
	private JLabel lbNewAcc;
	private JLabel rightLabel;

	private JTextField tfUserName;
	private JPasswordField pfPassword;
	private JButton btnLogin;
	
	
	Login(){
		initComp();
    	setExtendedState(JFrame.MAXIMIZED_BOTH);

		
	}
	private void initComp() {
		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        int screenWidth = screenSize.width;
	        int screenHeight = screenSize.height;
		leftPanel=new JPanel();
		leftPanel.setBounds(0,0,500,screenHeight);
		leftPanel.setBackground(Color.orange);
		leftPanel.setLayout(null);
		
		lbLoginpage=new JLabel("Login Page");
		lbLoginpage.setIcon(new ImageIcon(" "));
		lbLoginpage.setFont(new Font(null,Font.BOLD,25));
		lbLoginpage.setBounds((int)(leftPanel.getWidth()/3.2),50,350,70);
		
		lbUserName=new JLabel("user name");
		lbUserName.setFont(new Font(null,Font.PLAIN,16));
		lbUserName.setBounds((int)(leftPanel.getWidth()/7.6),leftPanel.getHeight()/4,200,70);
		
		tfUserName=new JTextField();
		tfUserName.setFont(new Font(null,Font.PLAIN,16));
		tfUserName.setBounds(leftPanel.getWidth()/3,leftPanel.getHeight()/4+22,240,28);
		
		
		lbPassword=new JLabel("password");
		lbPassword.setFont(new Font(null,Font.PLAIN,16));
		lbPassword.setBounds((int)(leftPanel.getWidth()/7.6),leftPanel.getHeight()/3,200,70);
		
		pfPassword=new JPasswordField();
		pfPassword.setFont(new Font(null,Font.PLAIN,16));
		pfPassword.setBounds(leftPanel.getWidth()/3,leftPanel.getHeight()/3+22,240,28);
		
		btnLogin=new JButton("login");
		btnLogin.setBounds(leftPanel.getWidth()/3,(int)(leftPanel.getHeight()/2.2),240,28);
		
		lbNewAcc=new JLabel("Create new account");

		lbNewAcc.setBounds(leftPanel.getWidth()-200,(int)(leftPanel.getHeight()-150),200,28);

		
		leftPanel.add(lbLoginpage);	
		leftPanel.add(lbUserName);
		leftPanel.add(tfUserName);		
		leftPanel.add(lbPassword);
		leftPanel.add(pfPassword);
		leftPanel.add(btnLogin);
		leftPanel.add(lbNewAcc);
		
		
	///////////////////////////////////////////////////////	
		
		rightLabel=new JLabel();
		rightLabel.setOpaque(true);
		rightLabel.setBounds(leftPanel.getWidth(),0,screenWidth-leftPanel.getWidth(),screenHeight);
		rightLabel.setIcon(new ImageIcon("./icons/signup-library-icon.png"));
		add(rightLabel);
		
	
		
		
		
		
		
///////////////////////////////////////////////////////////////////////////////////////		
		///actions 
		
		btnLogin.addActionListener(a->{
			String userName=tfUserName.getText();
			String password=pfPassword.getText();
			if(!userName.trim().equals("")&&!password.trim().equals("")) {
				tryToLogin(userName,password);
			}
			else 				
				new JOptionPane().showMessageDialog(this, "please fill the field ","required",JOptionPane.WARNING_MESSAGE);
		});


		lbNewAcc.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new Register().setVisible(true);
				dispose();
			}
		});	
		
		
	////////////////////////////////////////////////////////////////////////////
		
		
		
		add(leftPanel);
		
		setIconImage(new ImageIcon("./icons/library-3.png.png").getImage());
		setTitle("Library Managment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setSize(1000,660);
		
		
	}
	
	
	private void tryToLogin(String userName,String password) {
	    	
	    	Connection con=new Connect().getConnection();
	    	try {
				Statement stt=con.createStatement();
				ResultSet result=stt.executeQuery("select * from admin where username='"+userName+"' and password='"+password+"' ");
				boolean isAdmin=result.next();
				if(isAdmin) {
					new Home().setVisible(true);
					dispose();
				}
				else
		new JOptionPane().showMessageDialog(this, "Check your user name and password","not found",JOptionPane.WARNING_MESSAGE);	
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }

	
	
    public static void main(String args[]) {
      EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    
  
}

