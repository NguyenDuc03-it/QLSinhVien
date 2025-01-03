package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Until.hibernateUntil;
import model.DangNhap;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField pwF_Password;

	
	public Login() {
		init();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public void init() {
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 318);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Header = new JPanel();
		contentPane.add(panel_Header, BorderLayout.NORTH);
		panel_Header.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lbTitle = new JLabel("Quản lý sinh viên");
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setForeground(new Color(0, 0, 255));
		lbTitle.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lbTitle.setBackground(new Color(240, 240, 240));
		panel_Header.add(lbTitle);
		
		KeyListener enterKeyListener = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	                    checkLogin();;
				 }
				
			}
		};
		JPanel panel_Center = new JPanel();
		contentPane.add(panel_Center);
		
		JLabel lbUser = new JLabel("Username:");
		lbUser.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lbUser.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		
		JLabel lbPassword = new JLabel("Password:");
		lbPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lbPassword.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		txtUser = new JTextField();
		txtUser.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtUser.setColumns(10);
		txtUser.addKeyListener(enterKeyListener);
		
		pwF_Password = new JPasswordField();
		pwF_Password.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		pwF_Password.addKeyListener(enterKeyListener);
		
		
		JCheckBox ckbEnable = new JCheckBox("Hiện mật khẩu");
		ckbEnable.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		
		ckbEnable.addItemListener((ItemListener) new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (ckbEnable.isSelected()) {
                	pwF_Password.setEchoChar((char) 0); // Hiển thị mật khẩu
                } else {
                	pwF_Password.setEchoChar('\u2022'); // Ẩn mật khẩu
                }
            }
        });
		GroupLayout gl_panel_Center = new GroupLayout(panel_Center);
		gl_panel_Center.setHorizontalGroup(
			gl_panel_Center.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Center.createSequentialGroup()
					.addGap(86)
					.addComponent(lbUser, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(txtUser, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_Center.createSequentialGroup()
					.addGap(86)
					.addComponent(lbPassword, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(pwF_Password, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_Center.createSequentialGroup()
					.addGap(96)
					.addComponent(ckbEnable, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_Center.setVerticalGroup(
			gl_panel_Center.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Center.createSequentialGroup()
					.addGap(58)
					.addGroup(gl_panel_Center.createParallelGroup(Alignment.LEADING)
						.addComponent(lbUser, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtUser, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(gl_panel_Center.createParallelGroup(Alignment.LEADING)
						.addComponent(lbPassword, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_Center.createSequentialGroup()
							.addGap(1)
							.addComponent(pwF_Password, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
					.addGap(11)
					.addComponent(ckbEnable, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
		);
		panel_Center.setLayout(gl_panel_Center);
		
		JPanel panel_Footer = new JPanel();
		contentPane.add(panel_Footer, BorderLayout.SOUTH);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkLogin();
			}
		});
		
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int confirmed = JOptionPane.showConfirmDialog(null,
	                        "Bạn có muốn thoát chương trình?", "Xác nhận thoát",
	                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

	                if (confirmed == JOptionPane.YES_OPTION) {
	                    System.exit(0); // Kết thúc chương trình
	                }
	            }
			
		});
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GroupLayout gl_panel_Footer = new GroupLayout(panel_Footer);
		gl_panel_Footer.setHorizontalGroup(
			gl_panel_Footer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Footer.createSequentialGroup()
					.addGap(100)
					.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
					.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(103))
		);
		gl_panel_Footer.setVerticalGroup(
			gl_panel_Footer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Footer.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_Footer.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnExit, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel_Footer.setLayout(gl_panel_Footer);
		
	}
	
	private void checkLogin() {
		if(txtUser.getText().isEmpty()) {
			JOptionPane.showConfirmDialog(null, "Vui lòng nhập tài khoản!", "Thông báo", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			txtUser.requestFocus();
		}
		else if(new String(pwF_Password.getPassword()).isEmpty()) {
			JOptionPane.showConfirmDialog(null, "Vui lòng nhập mật khẩu!", "Thông báo", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			pwF_Password.requestFocus();
		}
		else {
			SessionFactory sessionFactory = hibernateUntil.getSessionFactory();
			if(sessionFactory!=null) {
				Session session = sessionFactory.openSession();
				
				try {
					Transaction tr = session.beginTransaction();
					// dung hibernate la chung ta dang tuong tac voi doi tuong (obj) vi vay bang o day chung ta can xem la DangNhap thay vi 
					// bang Login o duoi csdl
					String query = "FROM DangNhap WHERE username = :username AND password = :password";
					// để thông báo cho trình biên dịch biết rằng đã kiểm tra kiểu dữ liệu và đảm bảo nó an toàn
					// sử dụng @SuppressWarnings("unchecked") để tắt cảnh báo này
					@SuppressWarnings("unchecked")
					Query<DangNhap> loginQuery = (Query<DangNhap>)session.createQuery(query);
					loginQuery.setParameter("username", txtUser.getText());
					loginQuery.setParameter("password", new String(pwF_Password.getPassword()));
					tr.commit(); // day cac du lieu o bo dem xuong csdl
					List<DangNhap> logins = loginQuery.list();
					if (logins.size() > 0) {
					    // Đăng nhập thành công
						
						Main mainForm = new Main();
						mainForm.setAdminName(txtUser.getText());
						mainForm.setLoggedInAdmin(txtUser.getText()); // Truyền adminName vào form Main
						mainForm.setVisible(true);
						dispose();
					} else {
						JOptionPane.showConfirmDialog(null, "Thông tin tài khoản hoặc mật khẩu không chính xác", "Thông báo", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
					}
				}
				finally{
					session.close();
				}
			}
			
			
		}
		
	}

}
