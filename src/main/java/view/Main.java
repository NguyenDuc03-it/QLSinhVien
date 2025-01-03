package view;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import model.DangNhap;

import java.awt.BorderLayout;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import view.Login;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JPanel panel_BodyApp;
	private JLabel lbUsername;
	private JLabel lbTime;

	private static String adminName = "";

    

    public static String getAdminName() {
        return adminName;
    }

    public static void setAdminName(String name) {
        adminName = name;
    }
	/**
	 * Launch the application.
	 */
	public Main() {
		init();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	/**
	 * Create the frame.
	 */
	public void init() {
		System.out.println("Ten: "+adminName);
		String imagePath = "Image.jpg";
		setTitle("Main");
		setFont(new Font("Times New Roman", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 924, 610);
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
		
		JPanel panel_Title = new JPanel();
		contentPane.add(panel_Title, BorderLayout.NORTH);
		panel_Title.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Quản lý sinh viên");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_Title.add(lblNewLabel);
		
		panel_BodyApp = new JPanel();
		contentPane.add(panel_BodyApp);
		
		JPanel panel_Image = new JPanel();
		
		URL imageUrl = getClass().getClassLoader().getResource(imagePath);
		ImageIcon imageIcon = new ImageIcon(imageUrl);
		Image image = imageIcon.getImage();

		// Kích thước hiển thị mong muốn của JLabel
		int targetWidth = 186;
		int targetHeight = 125;

		// Tính toán tỷ lệ thu nhỏ của hình ảnh
		double scaleFactor = Math.min((double) targetWidth / image.getWidth(null),
		                               (double) targetHeight / image.getHeight(null));

		// Tạo một hình ảnh thu nhỏ với tỷ lệ đã tính toán
		int scaledWidth = (int) (image.getWidth(null) * scaleFactor);
		int scaledHeight = (int) (image.getHeight(null) * scaleFactor);
		Image scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

		// Tạo ImageIcon từ hình ảnh thu nhỏ
		ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
		panel_Image.setLayout(new BorderLayout(0, 0));
		
		JLabel lb_Image = new JLabel("New label");
		panel_Image.add(lb_Image);
		lb_Image.setHorizontalAlignment(SwingConstants.CENTER);
		lb_Image.setIcon(scaledImageIcon);
		
		JLabel lbAdmin = new JLabel("Admin:");
		lbAdmin.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		lbUsername = new JLabel("AdminName");
		lbUsername.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		String adminName = Main.getAdminName();
		if (adminName.equals("nguyentrungduc")) {
		    lbUsername.setText("Nguyễn Trung Đức"); // Hiển thị adminName trong JLabel
		} else if (adminName.equals("nguyenvuhongchinh")) {
		    lbUsername.setText("Nguyễn Vũ Hồng Chính"); // Hiển thị adminName trong JLabel
		}
		
		
		JLabel lbToday = new JLabel("Today:");
		lbToday.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		lbTime = new JLabel("New label");
		lbTime.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	       String currentDate = dateFormat.format(new Date());
	       lbTime.setText(currentDate);
	       
	       
		GroupLayout gl_panel_BodyApp = new GroupLayout(panel_BodyApp);
		gl_panel_BodyApp.setHorizontalGroup(
			gl_panel_BodyApp.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_BodyApp.createSequentialGroup()
					.addGap(256)
					.addGroup(gl_panel_BodyApp.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_Image, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_BodyApp.createSequentialGroup()
							.addGroup(gl_panel_BodyApp.createParallelGroup(Alignment.LEADING)
								.addComponent(lbAdmin, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbToday, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
							.addGap(36)
							.addGroup(gl_panel_BodyApp.createParallelGroup(Alignment.LEADING)
								.addComponent(lbTime, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lbUsername, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE))))
					.addGap(137))
		);
		gl_panel_BodyApp.setVerticalGroup(
			gl_panel_BodyApp.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_BodyApp.createSequentialGroup()
					.addGap(79)
					.addComponent(panel_Image, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addGap(42)
					.addGroup(gl_panel_BodyApp.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbAdmin)
						.addComponent(lbUsername, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(gl_panel_BodyApp.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbToday)
						.addComponent(lbTime, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(173, Short.MAX_VALUE))
		);
		panel_BodyApp.setLayout(gl_panel_BodyApp);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		
		JButton btnQuanLySV = new JButton("Quản lý sinh viên");
		btnQuanLySV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				QL_SinhVien QLSV = new QL_SinhVien();
				QLSV.setVisible(true);
				dispose();
			}
		});
		btnQuanLySV.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("MENU");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_1, BorderLayout.CENTER);
		
		JButton btnQuanLyLop = new JButton("Quản lý lớp");
		btnQuanLyLop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QL_lop_JFrame QLLop = new QL_lop_JFrame();
				QLLop.setVisible(true);
				dispose();
			}
		});
		btnQuanLyLop.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		JButton btnQuanLyKhoa = new JButton("Quản lý khoa");
		btnQuanLyKhoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QL_Khoa QLKhoa = new QL_Khoa();
				QLKhoa.setVisible(true);
				dispose();
			}
		});
		btnQuanLyKhoa.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		JButton btnQuanLyMonHoc = new JButton("Quản lý môn học");
		btnQuanLyMonHoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				QL_MonHoc QLMonHoc = new QL_MonHoc();
				QLMonHoc.setVisible(true);
				dispose();
				
			}
		});
		btnQuanLyMonHoc.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		
		
		JButton btnQuanLyDiem = new JButton("Quản lý điểm");
		btnQuanLyDiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				QL_Diem QLDiem = new QL_Diem();
				QLDiem.setVisible(true);
				dispose();
			}
		});
		btnQuanLyDiem.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		JButton btnDangXuat = new JButton("Đăng xuất");
		btnDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null,
                        "Bạn có muốn thoát?", "Xác nhận thoát",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					Login login_form = new Login();
				login_form.setVisible(true);
				dispose();
				}
			}
		});
		btnDangXuat.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(25)
					.addComponent(btnQuanLySV, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(25)
					.addComponent(btnQuanLyLop, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(25)
					.addComponent(btnQuanLyKhoa, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(25)
					.addComponent(btnQuanLyMonHoc, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(25)
					.addComponent(btnQuanLyDiem, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(25)
					.addComponent(btnDangXuat, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addGap(24)
					.addComponent(btnQuanLySV, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(23)
					.addComponent(btnQuanLyLop, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(btnQuanLyKhoa, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(24)
					.addComponent(btnQuanLyMonHoc, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(btnQuanLyDiem, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addComponent(btnDangXuat, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);
		
	}
	public void setLoggedInAdmin(String adminname) {
		System.out.println("Ten Main: "+ adminname);
		if(adminname.equals("nguyentrungduc")) {
			lbUsername.setText("Nguyễn Trung Đức"); // Hiển thị adminName trong JLabel
		}else if(adminname.equals("nguyenvuhongchinh")) {
			 lbUsername.setText("Nguyễn Vũ Hồng Chính"); // Hiển thị adminName trong JLabel
		}
		 
	
	}
	
}
