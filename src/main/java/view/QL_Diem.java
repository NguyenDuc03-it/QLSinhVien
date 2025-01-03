package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Until.hibernateUntil;
import model.Diem;
import model.Khoa;
import model.Lop;
import model.SinhVien;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QL_Diem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private JTextField txtMaMonHoc;
	private JTextField txtMSV;
	private JTextField txtNamHoc;
	private JTextField txtDiemCuoiKy;
	private JTextField txtDiemGiuaKy;
	private JTextField txtDiemChuyenCan;
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	public QL_Diem() {
		setTitle("Quản lý điểm");
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
		tableModel.addColumn("Mã môn học");
		tableModel.addColumn("Mã sinh viên");
		tableModel.addColumn("Năm học");
		tableModel.addColumn("Điểm chuyên cần");
		tableModel.addColumn("Điểm giữa kỳ");
		tableModel.addColumn("Điểm cuối kỳ");
		
		 table = new JTable(tableModel);
		 table.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 		int rowIndex = table.getSelectedRow();
				
				Object maMonHoc = table.getValueAt(rowIndex, 0);
				Object maSinhVien = table.getValueAt(rowIndex, 1);
				Object namHoc = table.getValueAt(rowIndex, 2);
				Object diemChuyenCan = table.getValueAt(rowIndex, 3);
				Object diemGiuaKy = table.getValueAt(rowIndex, 4);
				Object diemCuoiKy = table.getValueAt(rowIndex, 5);
				
				
				txtMaMonHoc.setText(maMonHoc.toString());
				txtMSV.setText(maSinhVien.toString());
				txtNamHoc.setText(namHoc.toString());
				txtDiemChuyenCan.setText(diemChuyenCan.toString());
				txtDiemGiuaKy.setText(diemGiuaKy.toString());
				txtDiemCuoiKy.setText(diemCuoiKy.toString());
				
				
		 	}
		 });
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1004, 574);
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
		
		JLabel lbTitle = new JLabel("Quản lý điểm");
		lbTitle.setForeground(Color.BLUE);
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		panel_Title.add(lbTitle);
		
		JPanel panel_Footer = new JPanel();
		contentPane.add(panel_Footer, BorderLayout.SOUTH);
		
		//BUTTON THÊM
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String maMonHoc = txtMaMonHoc.getText();
				String maSV = txtMSV.getText();
				String namHoc = txtNamHoc.getText();
				int diemChuyenCan = Integer.parseInt(txtDiemChuyenCan.getText());
				float diemGiuaKy = Float.parseFloat(txtDiemGiuaKy.getText());
				float diemCuoiKy = Float.parseFloat(txtDiemCuoiKy.getText());
				
				if(maMonHoc.equals("") || maSV.equals("") || namHoc.equals("") || txtDiemChuyenCan.getText().isEmpty() 
						|| txtDiemGiuaKy.getText().isEmpty() || txtDiemCuoiKy.getText().isEmpty()) {
					JOptionPane.showConfirmDialog(null,
	                        "Vui lòng nhập đầy đủ dữ liệu!", "Thông báo",
	                        JOptionPane.DEFAULT_OPTION);
				}
				else {
				if(sessionFactory!=null) {
					Session session = sessionFactory.openSession();
					Transaction tr = session.beginTransaction();
					try {
						
						Diem diem = new Diem();
						diem.setMaMonHoc(maMonHoc);
						diem.setMaSV(maSV);
						diem.setNamHoc(namHoc);
						diem.setDiemChuyenCan(diemChuyenCan);
						diem.setDiemGiuaKy(diemGiuaKy);
						diem.setDiemCuoiKy(diemCuoiKy);
						
						
						session.save(diem);
						tr.commit();
						  // Thêm dòng mới vào DefaultTableModel
		                Object[] rowData = {maMonHoc, maSV, namHoc, diemChuyenCan, diemGiuaKy, diemCuoiKy};
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
		
		//BUTTON XÓA
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
		        String maMonHoc = tableModel.getValueAt(rowIndex, 0).toString();

		        // Xác nhận việc xóa dữ liệu từ người dùng
		        int confirmResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa dòng này?", "Xác nhận xóa",
		                JOptionPane.YES_NO_OPTION);
		        if (confirmResult == JOptionPane.YES_OPTION) {
		            if (sessionFactory != null) {
		                Session session = sessionFactory.openSession();
		                Transaction tr = session.beginTransaction();
		                try {
		                    Diem diem = session.get(Diem.class, maMonHoc);
		                    if (diem != null) {
		                        session.delete(diem);
		                        tr.commit();

		                        // Xóa dòng khỏi DefaultTableModel
		                        tableModel.removeRow(rowIndex);

		                        JOptionPane.showConfirmDialog(null, "Xóa dữ liệu thành công!", "Thông báo",
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
				
			}
		});
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		//BUTTON SỬA
		JButton btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String maMonHoc = txtMaMonHoc.getText();
				String maSV = txtMSV.getText();
				String namHoc = txtNamHoc.getText();
				int diemChuyenCan = Integer.parseInt(txtDiemChuyenCan.getText());
				float diemGiuaKy = Float.parseFloat(txtDiemGiuaKy.getText());
				float diemCuoiKy = Float.parseFloat(txtDiemCuoiKy.getText());

		        if (sessionFactory != null) {
		            Session session = sessionFactory.openSession();
		            Transaction tr = session.beginTransaction();
		            
		            try {
		                Diem diem = session.get(Diem.class, maMonHoc);
		                if (diem != null) {
		                	diem.setMaMonHoc(maMonHoc);
		                	diem.setMaSV(maSV);
		                	diem.setNamHoc(namHoc);
		                	diem.setDiemChuyenCan(diemChuyenCan);
		                	diem.setDiemGiuaKy(diemGiuaKy);
		                	diem.setDiemCuoiKy(diemCuoiKy);
		                	

		                    session.saveOrUpdate(diem);
		                    tr.commit();
		                    // Thêm dòng mới vào DefaultTableModel
		                    int rowIndex = table.getSelectedRow();
		                    tableModel.setValueAt(maMonHoc, rowIndex, 0);
		                    tableModel.setValueAt(maSV, rowIndex, 1);
		                    tableModel.setValueAt(namHoc, rowIndex, 2);
		                    tableModel.setValueAt(diemChuyenCan, rowIndex, 3);
		                    tableModel.setValueAt(diemGiuaKy, rowIndex, 4);
		                    tableModel.setValueAt(diemCuoiKy, rowIndex, 5);

		                    JOptionPane.showConfirmDialog(null, "Sửa dữ liệu thành công!", "Thông báo",
		                            JOptionPane.DEFAULT_OPTION);
		                } else {
		                    JOptionPane.showMessageDialog(null, "Mã môn học không tồn tại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
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
		
		// BUTTON TÌM KIẾM THEO MÃ SINH VIÊN
		JButton btnTimKiemMSV = new JButton("Tìm kiếm theo msv");
		btnTimKiemMSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String tuKhoa = txtMSV.getText().trim();

		        // Xóa các dòng hiện có trong bảng
		        
		        
		        if(tuKhoa.equals("")){
		        	JOptionPane.showConfirmDialog(null, "Nhập mã sinh viên để tìm kiếm", "Thông báo",
	                        JOptionPane.DEFAULT_OPTION);
		        }
		        else { 
		        	while (tableModel.getRowCount() > 0) {
		            tableModel.removeRow(0);
		        }
		        	if (sessionFactory != null) {
		            Session session = sessionFactory.openSession();
		            try {
		                // Thực hiện truy vấn tìm kiếm dựa trên từ khóa
		                Query<Diem> query = session.createQuery("FROM Diem WHERE maSV LIKE :tuKhoa", Diem.class);
		                query.setParameter("tuKhoa", "%" + tuKhoa + "%");
		                List<Diem> ketQua = query.list();

		                // Hiển thị kết quả tìm kiếm trong bảng
		                for (Diem diem : ketQua) {
		                    Object[] rowData = {diem.getMaMonHoc(), diem.getMaSV(), diem.getNamHoc(), diem.getDiemChuyenCan(), diem.getDiemGiuaKy(), diem.getDiemCuoiKy()};
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
		btnTimKiemMSV.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		// BUTTON TÌM KIẾM THEO MÃ MÔN
		JButton btnTimKiemTheo = new JButton("Tìm kiếm theo môn");
		btnTimKiemTheo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String tuKhoa = txtMaMonHoc.getText().trim();

		        // Xóa các dòng hiện có trong bảng
		        
		        
		        if(tuKhoa.equals("")){
		        	JOptionPane.showConfirmDialog(null, "Nhập mã môn học để tìm kiếm", "Thông báo",
	                        JOptionPane.DEFAULT_OPTION);
		        }
		        else { 
		        	while (tableModel.getRowCount() > 0) {
		            tableModel.removeRow(0);
		        }
		        	if (sessionFactory != null) {
		            Session session = sessionFactory.openSession();
		            try {
		                // Thực hiện truy vấn tìm kiếm dựa trên từ khóa
		                Query<Diem> query = session.createQuery("FROM Diem WHERE maMonHoc LIKE :tuKhoa", Diem.class);
		                query.setParameter("tuKhoa", "%" + tuKhoa + "%");
		                List<Diem> ketQua = query.list();

		                // Hiển thị kết quả tìm kiếm trong bảng
		                for (Diem diem : ketQua) {
		                    Object[] rowData = {diem.getMaMonHoc(), diem.getMaSV(), diem.getNamHoc(), diem.getDiemChuyenCan(), diem.getDiemGiuaKy(), diem.getDiemCuoiKy()};
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
		btnTimKiemTheo.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		
		//BUTTON LÀM MỚI
		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtMaMonHoc.setText("");
				txtMSV.setText("");
				txtNamHoc.setText("");
				txtDiemChuyenCan.setText("");
				txtDiemGiuaKy.setText("");
				txtDiemCuoiKy.setText("");
				
				tableModel.setRowCount(0);
				
				if (sessionFactory != null) {
		            Session session = sessionFactory.openSession();
		            try {
		                // Thực hiện truy vấn để lấy dữ liệu từ csdl
		                Query<Diem> query = session.createQuery("FROM Diem", Diem.class);
		                List<Diem> kq = query.list();

		                // Hiển thị dữ liệu lên bảng JTable
		                for (Diem diem : kq) {
		                	Object[] rowData = {diem.getMaMonHoc(), diem.getMaSV(), diem.getNamHoc(), diem.getDiemChuyenCan(), diem.getDiemGiuaKy(), diem.getDiemCuoiKy()};
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
				
				Main main = new Main();
				main.setVisible(true);
				dispose();
			}
		});
		btnThoat.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		GroupLayout gl_panel_Footer = new GroupLayout(panel_Footer);
		gl_panel_Footer.setHorizontalGroup(
			gl_panel_Footer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Footer.createSequentialGroup()
					.addGap(10)
					.addComponent(btnThem, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(btnXoa, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(btnSua, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(btnTimKiemTheo, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(btnTimKiemMSV, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(btnLamMoi, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(btnThoat, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_Footer.setVerticalGroup(
			gl_panel_Footer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Footer.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_Footer.createParallelGroup(Alignment.LEADING)
						.addComponent(btnThem, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnXoa, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSua, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnTimKiemTheo, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnTimKiemMSV, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLamMoi, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnThoat, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
		);
		panel_Footer.setLayout(gl_panel_Footer);
		
		JPanel panel_Center = new JPanel();
		contentPane.add(panel_Center, BorderLayout.CENTER);
		
		
		JPanel panel_info = new JPanel();
		
		JLabel lbmaMonHoc = new JLabel("Mã môn học:");
		lbmaMonHoc.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		
		txtMaMonHoc = new JTextField();
		txtMaMonHoc.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		txtMaMonHoc.setColumns(10);
		
		txtMSV = new JTextField();
		txtMSV.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		txtMSV.setColumns(10);
		
		JLabel lbMaSV = new JLabel("Mã sinh viên:");
		lbMaSV.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		
		txtNamHoc = new JTextField();
		txtNamHoc.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		txtNamHoc.setColumns(10);
		
		JLabel lbNamHoc = new JLabel("Năm học:");
		lbNamHoc.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		
		JLabel lbDiemChuyenCan = new JLabel("Điểm chuyên cần:");
		lbDiemChuyenCan.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		
		JLabel lbDiemGiuaKy = new JLabel("Điểm giữa kỳ:");
		lbDiemGiuaKy.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		
		JLabel lbDiemCuoiKy = new JLabel("Điểm cuối kỳ:");
		lbDiemCuoiKy.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		
		txtDiemCuoiKy = new JTextField();
		txtDiemCuoiKy.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		txtDiemCuoiKy.setColumns(10);
		
		txtDiemGiuaKy = new JTextField();
		txtDiemGiuaKy.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		txtDiemGiuaKy.setColumns(10);
		
		txtDiemChuyenCan = new JTextField();
		txtDiemChuyenCan.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		txtDiemChuyenCan.setColumns(10);
		
		JPanel panel_Table = new JPanel();
		panel_Table.setLayout(new BorderLayout(0, 0));
		
		if(sessionFactory!=null) {
			Session session = sessionFactory.openSession();
			
			try {
				Transaction tr = session.beginTransaction();
				
				String hql = "FROM Diem";
				Query<Diem> query = session.createQuery(hql, Diem.class);
				tr.commit();
				List<Diem> diemList = query.list();
				
				//tableModel = new DefaultTableModel();
				
				  tableModel.addColumn("Mã môn học");
				  tableModel.addColumn("Mã sinh viên");
				  tableModel.addColumn("Năm học");
				  tableModel.addColumn("Điểm chuyên cần");
				  tableModel.addColumn("Điểm giữa kỳ");
				  tableModel.addColumn("Điểm cuối kỳ");
				  
		        
		        for (Diem diem : diemList) {
		            Object[] row = new Object[8];
		            row[0] = diem.getMaMonHoc();
		            row[1] = diem.getMaSV();
		            row[2] = diem.getNamHoc();
		            row[3] = diem.getDiemChuyenCan();
		            row[4] = diem.getDiemChuyenCan();
		            row[5] = diem.getDiemCuoiKy();
		            
		            tableModel.addRow(row);
		        }
		        
		        
		        
		      //  setVisible(true);
				
			}
			finally{
				session.close();
			}
		}
		String[] s = {"Mã môn học", "Mã sinh viên", "Năm học", "Điểm chuyên cần", "Điểm giữa kỳ", "Điểm cuối kỳ"};
		tableModel.setColumnIdentifiers(s);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.getTableHeader().setVisible(true); // Hiển thị tiêu đề cột
		table.getTableHeader().setReorderingAllowed(false);
		panel_Table.add(new JScrollPane(table), BorderLayout.CENTER);
		GroupLayout gl_panel_Center = new GroupLayout(panel_Center);
		gl_panel_Center.setHorizontalGroup(
			gl_panel_Center.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_info, GroupLayout.PREFERRED_SIZE, 980, GroupLayout.PREFERRED_SIZE)
				.addComponent(panel_Table, GroupLayout.PREFERRED_SIZE, 980, GroupLayout.PREFERRED_SIZE)
		);
		gl_panel_Center.setVerticalGroup(
			gl_panel_Center.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Center.createSequentialGroup()
					.addComponent(panel_info, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(panel_Table, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
		);
		GroupLayout gl_panel_info = new GroupLayout(panel_info);
		gl_panel_info.setHorizontalGroup(
			gl_panel_info.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_info.createSequentialGroup()
					.addGap(101)
					.addGroup(gl_panel_info.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_info.createSequentialGroup()
							.addComponent(lbmaMonHoc, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(txtMaMonHoc, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addGap(331)
							.addComponent(lbDiemChuyenCan, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(txtDiemChuyenCan, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_info.createSequentialGroup()
							.addComponent(lbMaSV, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(txtMSV, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addGap(331)
							.addComponent(lbDiemGiuaKy, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addGap(29)
							.addComponent(txtDiemGiuaKy, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_info.createSequentialGroup()
							.addComponent(lbNamHoc, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(txtNamHoc, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addGap(331)
							.addComponent(lbDiemCuoiKy, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addGap(29)
							.addComponent(txtDiemCuoiKy, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))))
		);
		gl_panel_info.setVerticalGroup(
			gl_panel_info.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_info.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_info.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_info.createSequentialGroup()
							.addGap(3)
							.addComponent(lbmaMonHoc, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtMaMonHoc, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_info.createSequentialGroup()
							.addGap(1)
							.addComponent(lbDiemChuyenCan, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtDiemChuyenCan, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(23)
					.addGroup(gl_panel_info.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_info.createSequentialGroup()
							.addGap(3)
							.addComponent(lbMaSV, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtMSV, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_info.createSequentialGroup()
							.addGap(6)
							.addComponent(lbDiemGiuaKy, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtDiemGiuaKy, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(23)
					.addGroup(gl_panel_info.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_info.createSequentialGroup()
							.addGap(3)
							.addComponent(lbNamHoc, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtNamHoc, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbDiemCuoiKy, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtDiemCuoiKy, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
		);
		panel_info.setLayout(gl_panel_info);
		panel_Center.setLayout(gl_panel_Center);
	}

}
