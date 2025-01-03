package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Until.hibernateUntil;
import model.Lop;
import model.MonHoc;
import model.SinhVien;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

public class QL_SinhVien extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private JTextField txtMaSV;
	private JTextField txtMaLop;
	private JTextField txtNienKhoa;
	private JTextField txtTenSV;
	private JTextField txtSDT;
	private JTextField txtNgaySinh;
	private JTextField txtNoiSinh;
	private JTextField txtGioiTinh;
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	public QL_SinhVien() {
		setTitle("Quản lý sinh viên");
		init();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public void init() {
		SessionFactory sessionFactory = hibernateUntil.getSessionFactory();
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Mã sinh viên");
		tableModel.addColumn("Tên sinh viên");
		tableModel.addColumn("Ngày sinh");
		tableModel.addColumn("Giới tính");
		tableModel.addColumn("Mã lớp");
		tableModel.addColumn("Khóa");
		tableModel.addColumn("Số điện thoại");
		tableModel.addColumn("Nơi sinh");
		 table = new JTable(tableModel);
		 table.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 		int rowIndex = table.getSelectedRow();
				
				Object maSV = table.getValueAt(rowIndex, 0);
				Object tenSV = table.getValueAt(rowIndex, 1);
				Object ngaySinh = table.getValueAt(rowIndex, 2);
				Object gioiTinh = table.getValueAt(rowIndex, 3);
				Object maLop = table.getValueAt(rowIndex, 4);
				Object nienKhoa = table.getValueAt(rowIndex, 5);
				Object sdt = table.getValueAt(rowIndex, 6);
				Object noiSinh = table.getValueAt(rowIndex, 7);
				
				txtMaSV.setText(maSV.toString());
				txtTenSV.setText(tenSV.toString());
				txtNgaySinh.setText(ngaySinh.toString());
				txtGioiTinh.setText(gioiTinh.toString());
				txtMaLop.setText(maLop.toString());
				txtNienKhoa.setText(nienKhoa.toString());
				txtSDT.setText(sdt.toString());
				txtNoiSinh.setText(noiSinh.toString());
				
		 	}
		 });
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 917, 563);
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
		
		JLabel lbTitle = new JLabel("Quản lý sinh viên");
		lbTitle.setForeground(Color.BLUE);
		lbTitle.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panel_Title.add(lbTitle, BorderLayout.CENTER);
		
		JPanel panel_Footer = new JPanel();
		contentPane.add(panel_Footer, BorderLayout.SOUTH);
		
		// BUTOTN THÊM
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String maSV = txtMaSV.getText();
				String tenSV = txtTenSV.getText();
				String ngaySinh = txtNgaySinh.getText();
				String gioiTinh = txtGioiTinh.getText();
				String maLop = txtMaLop.getText();
				String nienKhoa = txtNienKhoa.getText();
				String sdt = txtSDT.getText();
				String noiSinh = txtNoiSinh.getText();
				
				if(maLop.equals("")||maSV.equals("")||tenSV.equals("")||ngaySinh.equals("")||gioiTinh.equals("")||sdt.equals("")|| noiSinh.equals("") || nienKhoa.equals("")) {
					JOptionPane.showConfirmDialog(null,
	                        "Vui lòng nhập đầy đủ dữ liệu!", "Thông báo",
	                        JOptionPane.DEFAULT_OPTION);
				}
				else {
				if(sessionFactory!=null) {
					Session session = sessionFactory.openSession();
					Transaction tr = session.beginTransaction();
					try {
						
						SinhVien sv = new SinhVien();
						sv.setMaSV(maSV);
						sv.setTen(tenSV);
						sv.setNgaySinh(ngaySinh);
						sv.setGioiTinh(gioiTinh);
						sv.setMaLop(maLop);
						sv.setNienKhoa(nienKhoa);
						sv.setSDT(sdt);
						sv.setNoiSinh(noiSinh);
						
						session.save(sv);
						tr.commit();
						  // Thêm dòng mới vào DefaultTableModel
		                Object[] rowData = {maSV, tenSV, ngaySinh, gioiTinh, maLop, nienKhoa, sdt, noiSinh};
		                tableModel.addRow(rowData);
				        
						JOptionPane.showConfirmDialog(null,
		                        "Thêm dữ liệu thành công!", "Thông báo",
		                        JOptionPane.DEFAULT_OPTION);
						
					}
					catch(Exception ex) {
						tr.rollback();
						ex.printStackTrace();
					}
					finally{
						session.close();
					}
			}
				}
				
			}
		});
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		
		// BUTTON XÓA
		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Kiểm tra xem có dòng được chọn trong bảng hay không
		        int rowIndex = table.getSelectedRow();
		        if (rowIndex == -1) {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        // Lấy giá trị trong cột mã lớp của dòng được chọn
		        String maSV = tableModel.getValueAt(rowIndex, 0).toString();

		        // Xác nhận việc xóa dữ liệu từ người dùng
		        int confirmResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa dòng này?", "Xác nhận xóa",
		                JOptionPane.YES_NO_OPTION);
		        if (confirmResult == JOptionPane.YES_OPTION) {
		            if (sessionFactory != null) {
		                Session session = sessionFactory.openSession();
		                Transaction tr = session.beginTransaction();
		                try {
		                    SinhVien sv = session.get(SinhVien.class, maSV);
		                    if (sv != null) {
		                        session.delete(sv);
		                        tr.commit();

		                        // Xóa dòng khỏi DefaultTableModel
		                        tableModel.removeRow(rowIndex);

		                        JOptionPane.showConfirmDialog(null, "Xóa dữ liệu thành công!", "Thông báo",
		                                JOptionPane.DEFAULT_OPTION);
		                    } else {
		                        JOptionPane.showMessageDialog(null, "Sinh viên không tồn tại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
		                    }
		                } catch (Exception ex) {
		                    tr.rollback();
		                    ex.printStackTrace();
		                } finally {
		                    session.close();
		                }
		            }
		        }
				
			}
		});
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		// BUTTON SỬA
		JButton btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String maSV = txtMaSV.getText();
				String tenSV = txtTenSV.getText();
				String ngaySinh = txtNgaySinh.getText();
				String gioiTinh = txtGioiTinh.getText();
				String maLop = txtMaLop.getText();
				String nienKhoa = txtNienKhoa.getText();
				String sdt = txtSDT.getText();
				String noiSinh = txtNoiSinh.getText();

			        if (sessionFactory != null) {
			            Session session = sessionFactory.openSession();
			            Transaction tr = session.beginTransaction();
			            try {
			                SinhVien sv = session.get(SinhVien.class, maSV);
			                if (sv != null) {
			                	sv.setMaSV(maSV);
								sv.setTen(tenSV);
								sv.setNgaySinh(ngaySinh);
								sv.setGioiTinh(gioiTinh);
								sv.setMaLop(maLop);
								sv.setNienKhoa(nienKhoa);
								sv.setSDT(sdt);
								sv.setNoiSinh(noiSinh);

			                    session.saveOrUpdate(sv);
			                    tr.commit();
			                    // Thêm dòng mới vào DefaultTableModel
			                    int rowIndex = table.getSelectedRow();
			                    tableModel.setValueAt(maSV, rowIndex, 0);
			                    tableModel.setValueAt(tenSV, rowIndex, 1);
			                    tableModel.setValueAt(ngaySinh, rowIndex, 2);
			                    tableModel.setValueAt(gioiTinh, rowIndex, 3);
			                    tableModel.setValueAt(maLop, rowIndex, 4);
			                    tableModel.setValueAt(nienKhoa, rowIndex, 5);
			                    tableModel.setValueAt(sdt, rowIndex, 6);
			                    tableModel.setValueAt(noiSinh, rowIndex, 7);

			                    JOptionPane.showConfirmDialog(null, "Sửa dữ liệu thành công!", "Thông báo",
			                            JOptionPane.DEFAULT_OPTION);
			                } else {
			                    JOptionPane.showMessageDialog(null, "Lớp không tồn tại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
			                }
			            } catch (Exception ex) {
			                tr.rollback();
			                ex.printStackTrace();
			            } finally {
			                session.close();
			            }
			        }
				
			}
		});
		btnSua.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		//BUTTON TÌM KIẾM
		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String tuKhoa = txtMaSV.getText().trim();

		        // Xóa các dòng hiện có trong bảng
		        while (tableModel.getRowCount() > 0) {
		            tableModel.removeRow(0);
		        }
		        
		        if(tuKhoa.equals("")){
		        	JOptionPane.showConfirmDialog(null, "Nhập mã sinh viên để tìm kiếm", "Thông báo",
	                        JOptionPane.DEFAULT_OPTION);
		        }
		        else { 
		        	if (sessionFactory != null) {
		            Session session = sessionFactory.openSession();
		            try {
		                // Thực hiện truy vấn tìm kiếm dựa trên từ khóa
		                Query<SinhVien> query = session.createQuery("FROM SinhVien WHERE maSV LIKE :tuKhoa", SinhVien.class);
		                query.setParameter("tuKhoa", "%" + tuKhoa + "%");
		                List<SinhVien> ketQua = query.list();

		                // Hiển thị kết quả tìm kiếm trong bảng
		                for (SinhVien sv : ketQua) {
		                    Object[] rowData = {sv.getMaSV(), sv.getTen(), sv.getNgaySinh(), sv.getGioiTinh(), sv.getMaLop(), sv.getNienKhoa()
		                    		, sv.getSDT(), sv.getNoiSinh()};
		                    tableModel.addRow(rowData);
		                }

		                JOptionPane.showConfirmDialog(null, "Tìm thấy: "+tuKhoa, "Thông báo",
		                        JOptionPane.DEFAULT_OPTION);
		            } catch (Exception ex) {
		                ex.printStackTrace();
		            } finally {
		                session.close();
		            }
		        }
			}
				
			}
		});
		btnTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtMaSV.setText("");
				txtTenSV.setText("");
				txtNgaySinh.setText("");
				txtGioiTinh.setText("");
				txtMaLop.setText("");
				txtNgaySinh.setText("");
				txtSDT.setText("");
				txtNgaySinh.setText("");
				txtNienKhoa.setText("");
				txtNoiSinh.setText("");
				
				tableModel.setRowCount(0);
				
				if (sessionFactory != null) {
		            Session session = sessionFactory.openSession();
		            try {
		                // Thực hiện truy vấn để lấy dữ liệu từ csdl
		                Query<SinhVien> query = session.createQuery("FROM SinhVien", SinhVien.class);
		                List<SinhVien> kq = query.list();

		                // Hiển thị dữ liệu lên bảng JTable
		                for (SinhVien sv : kq) {
		                	Object[] rowData = {sv.getMaSV(), sv.getTen(), sv.getNgaySinh(), sv.getGioiTinh(), sv.getMaLop(), sv.getNienKhoa()
		                    		, sv.getSDT(), sv.getNoiSinh()};
		                    tableModel.addRow(rowData);
		                }
		            } catch (Exception ex) {
		                ex.printStackTrace();
		            } finally {
		                session.close();
		            }
		        }
				
			}
		});
		btnLamMoi.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		// BUTTON THOÁT
		JButton btnThoat = new JButton("Thoát");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(JOptionPane.showConfirmDialog(null,
                        "Bạn có muốn thoát?", "Xác nhận thoát",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					Main main = new Main();
				main.setVisible(true);
				dispose();
				}
				
			}
		});
		btnThoat.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		GroupLayout gl_panel_Footer = new GroupLayout(panel_Footer);
		gl_panel_Footer.setHorizontalGroup(
			gl_panel_Footer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Footer.createSequentialGroup()
					.addGap(85)
					.addComponent(btnThem, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(50)
					.addComponent(btnXoa, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addComponent(btnSua, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(btnTimKiem, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(btnLamMoi, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(btnThoat, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(115, Short.MAX_VALUE))
		);
		gl_panel_Footer.setVerticalGroup(
			gl_panel_Footer.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_Footer.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_Footer.createParallelGroup(Alignment.LEADING)
						.addComponent(btnXoa, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSua, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnTimKiem, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLamMoi, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnThoat, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnThem, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_Footer.setLayout(gl_panel_Footer);
		
		JPanel panel_Center = new JPanel();
		contentPane.add(panel_Center, BorderLayout.CENTER);
		panel_Center.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 893, 156);
		panel_Center.add(panel);
		
		JLabel lbMaSV = new JLabel("Mã sinh viên:");
		lbMaSV.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		
		txtMaSV = new JTextField();
		txtMaSV.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		txtMaSV.setColumns(10);
		
		JLabel lbMaLop = new JLabel("Mã lớp:");
		lbMaLop.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		
		txtMaLop = new JTextField();
		txtMaLop.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		txtMaLop.setColumns(10);
		
		txtNienKhoa = new JTextField();
		txtNienKhoa.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		txtNienKhoa.setColumns(10);
		
		JLabel lbNienKhoa = new JLabel("Khóa:");
		lbNienKhoa.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		
		JLabel lbTenSV = new JLabel("Tên sinh viên:");
		lbTenSV.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		
		txtTenSV = new JTextField();
		txtTenSV.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		txtTenSV.setColumns(10);
		
		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		txtSDT.setColumns(10);
		
		JLabel lbSDT = new JLabel("SDT:");
		lbSDT.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		
		JLabel lbNgaySinh = new JLabel("Ngày sinh:");
		lbNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		
		txtNgaySinh = new JTextField();
		txtNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		txtNgaySinh.setColumns(10);
		
		txtNoiSinh = new JTextField();
		txtNoiSinh.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		txtNoiSinh.setColumns(10);
		
		JLabel lbNoiSinh = new JLabel("Nơi sinh:");
		lbNoiSinh.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		
		JLabel lbGioiTinh = new JLabel("Giới tính:");
		lbGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		
		txtGioiTinh = new JTextField();
		txtGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		txtGioiTinh.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lbMaSV, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtMaSV, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addGap(32)
							.addComponent(lbTenSV, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtTenSV, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addGap(49)
							.addComponent(lbNgaySinh, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtNgaySinh, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addGap(46)
							.addComponent(lbGioiTinh, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(txtGioiTinh, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lbMaLop, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(txtMaLop, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addGap(32)
							.addComponent(lbNienKhoa, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(txtNienKhoa, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addGap(49)
							.addComponent(lbSDT, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(txtSDT, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addGap(46)
							.addComponent(lbNoiSinh, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(txtNoiSinh, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(41)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(3)
									.addComponent(txtMaSV, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(3)
									.addComponent(txtTenSV, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtNgaySinh, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(3)
									.addComponent(lbGioiTinh, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtGioiTinh, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(47)
							.addComponent(lbMaSV, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(47)
							.addComponent(lbTenSV, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(44)
							.addComponent(lbNgaySinh, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)))
					.addGap(21)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(6)
							.addComponent(lbMaLop, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(txtMaLop, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(6)
							.addComponent(lbNienKhoa, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(txtNienKhoa, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lbSDT, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtSDT, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lbNoiSinh, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtNoiSinh, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_Table = new JPanel();
		panel_Table.setBounds(0, 155, 893, 251);
		panel_Center.add(panel_Table);
		panel_Table.setLayout(new BorderLayout(0, 0));
		
		if(sessionFactory!=null) {
			Session session = sessionFactory.openSession();
			
			try {
				Transaction tr = session.beginTransaction();
				
				String hql = "FROM SinhVien";
				Query<SinhVien> query = session.createQuery(hql, SinhVien.class);
				tr.commit();
				List<SinhVien> svList = query.list();
				
				//tableModel = new DefaultTableModel();
				
				  tableModel.addColumn("Mã sinh viên");
				  tableModel.addColumn("Tên sinh viên");
				  tableModel.addColumn("Ngày sinh");
				  tableModel.addColumn("Giới tính");
				  tableModel.addColumn("Mã lớp");
				  tableModel.addColumn("Khóa");
				  tableModel.addColumn("Số điện thoại");
				  tableModel.addColumn("Nơi sinh");
				 
		        
		        for (SinhVien sv : svList) {
		            Object[] row = new Object[8];
		            row[0] = sv.getMaSV();
		            row[1] = sv.getTen();
		            row[2] = sv.getNgaySinh();
		            row[3] = sv.getGioiTinh();
		            row[4] = sv.getMaLop();
		            row[5] = sv.getNienKhoa();
		            row[6] = sv.getSDT();
		            row[7] = sv.getNoiSinh();
		            tableModel.addRow(row);
		        }
		        
		        
		        
		      //  setVisible(true);
				
			}
			finally{
				session.close();
			}
		}
		String[] s = {"Mã sinh viên", "Tên sinh viên", "Ngày sinh", "Giới tính", "Mã lớp", "Khóa", "Số điện thoại", "Nơi sinh"};
		tableModel.setColumnIdentifiers(s);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.getTableHeader().setVisible(true); // Hiển thị tiêu đề cột
		table.getTableHeader().setReorderingAllowed(false);
		panel_Table.add(new JScrollPane(table), BorderLayout.CENTER);
	}

}
