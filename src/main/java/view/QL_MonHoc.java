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
import model.Khoa;
import model.MonHoc;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.ScrollPane;
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

public class QL_MonHoc extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private JTextField txtMaMonHoc;
	private JTextField txtTenMonHoc;
	private JTextField txtTinChi;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public QL_MonHoc() {
		setTitle("Quản lý môn học");
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
			tableModel.addColumn("Tên môn học");
			tableModel.addColumn("Số tín chỉ");
			 table = new JTable(tableModel);
			 table.addMouseListener(new MouseAdapter() {
			 	@Override
			 	public void mouseClicked(MouseEvent e) {
			 		int rowIndex = table.getSelectedRow();
					
					Object maMonHoc = table.getValueAt(rowIndex, 0);
					Object tenMonHoc = table.getValueAt(rowIndex, 1);
					Object tinChi = table.getValueAt(rowIndex, 2);
					
					txtMaMonHoc.setText(maMonHoc.toString());
					txtTenMonHoc.setText(tenMonHoc.toString());
					txtTinChi.setText(tinChi.toString());
			 	}
			 });
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 793, 494);
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
		
		JLabel lbTitle = new JLabel("Quản lý môn học");
		lbTitle.setForeground(Color.BLUE);
		lbTitle.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panel_Title.add(lbTitle);
		
		JPanel panel_Footer = new JPanel();
		contentPane.add(panel_Footer, BorderLayout.SOUTH);
		
		
		// BUTTON THÊM
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String maMonHoc = txtMaMonHoc.getText();
				String tenMonHoc = txtTenMonHoc.getText();
				int tinChi =  Integer.parseInt(txtTinChi.getText());
				
				
				if(maMonHoc.equals("") || tenMonHoc.equals("") || txtTinChi.getText().isEmpty()) {
					JOptionPane.showConfirmDialog(null,
	                        "Vui lòng nhập đầy đủ dữ liệu!", "Thông báo",
	                        JOptionPane.DEFAULT_OPTION);
				}
				else {
				if(sessionFactory!=null) {
					Session session = sessionFactory.openSession();
					Transaction tr = session.beginTransaction();
					try {
						
						MonHoc monHoc = new MonHoc();
						monHoc.setMaMonHoc(maMonHoc);
						monHoc.setTenMonHoc(tenMonHoc);
						monHoc.setSoTinChi(tinChi);
						
						
						session.save(monHoc);
						tr.commit();
						  // Thêm dòng mới vào DefaultTableModel
		                Object[] rowData = {maMonHoc, tenMonHoc, tinChi};
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
		        String maMonHoc = tableModel.getValueAt(rowIndex, 0).toString();

		        // Xác nhận việc xóa dữ liệu từ người dùng
		        int confirmResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa dòng này?", "Xác nhận xóa",
		                JOptionPane.YES_NO_OPTION);
		        if (confirmResult == JOptionPane.YES_OPTION) {
		            if (sessionFactory != null) {
		                Session session = sessionFactory.openSession();
		                Transaction tr = session.beginTransaction();
		                try {
		                    MonHoc monHoc = session.get(MonHoc.class, maMonHoc);
		                    if (monHoc != null) {
		                        session.delete(monHoc);
		                        tr.commit();

		                        // Xóa dòng khỏi DefaultTableModel
		                        tableModel.removeRow(rowIndex);

		                        JOptionPane.showConfirmDialog(null, "Xóa dữ liệu thành công!", "Thông báo",
		                                JOptionPane.DEFAULT_OPTION);
		                    } else {
		                        JOptionPane.showMessageDialog(null, "Môn học không tồn tại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
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
		        String tenMonHoc = txtTenMonHoc.getText();
		        int tinChi =  Integer.parseInt(txtTinChi.getText());
		        
		        

		        if (sessionFactory != null) {
		            Session session = sessionFactory.openSession();
		            Transaction tr = session.beginTransaction();
		            try {
		                MonHoc monHoc = session.get(MonHoc.class, maMonHoc);
		                if (monHoc != null) {
		                	monHoc.setMaMonHoc(maMonHoc);
		                	monHoc.setTenMonHoc(tenMonHoc);
		                	monHoc.setSoTinChi(tinChi);

		                    session.saveOrUpdate(monHoc);
		                    tr.commit();
		                    // Thêm dòng mới vào DefaultTableModel
		                    int rowIndex = table.getSelectedRow();
		                    tableModel.setValueAt(maMonHoc, rowIndex, 0);
		                    tableModel.setValueAt(tenMonHoc, rowIndex, 1);
		                    tableModel.setValueAt(tinChi, rowIndex, 2);
		                    
		                    JOptionPane.showConfirmDialog(null, "Sửa dữ liệu thành công!", "Thông báo",
		                            JOptionPane.DEFAULT_OPTION);
		                } else {
		                    JOptionPane.showMessageDialog(null, "Khoa không tồn tại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
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
		
		// BUTTON TÌM KIẾM
		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String tuKhoa = txtMaMonHoc.getText().trim();

		        // Xóa các dòng hiện có trong bảng
		        while (tableModel.getRowCount() > 0) {
		            tableModel.removeRow(0);
		        }
		        
		        if(tuKhoa.equals("")){
		        	JOptionPane.showConfirmDialog(null, "Nhập mã khoa để tìm kiếm", "Thông báo",
	                        JOptionPane.DEFAULT_OPTION);
		        	
		        }
		        else { 
		        	if (sessionFactory != null) {
		            Session session = sessionFactory.openSession();
		            try {
		                // Thực hiện truy vấn tìm kiếm dựa trên từ khóa
		                Query<MonHoc> query = session.createQuery("FROM MonHoc WHERE maMonHoc LIKE :tuKhoa", MonHoc.class);
		                query.setParameter("tuKhoa", "%" + tuKhoa + "%");
		                List<MonHoc> ketQua = query.list();

		                // Hiển thị kết quả tìm kiếm trong bảng
		                for (MonHoc monHoc : ketQua) {
		                    Object[] rowData = {monHoc.getMaMonHoc(), monHoc.getTenMonHoc(), monHoc.getSoTinChi()};
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
		
		//BUTTON LÀM MỚI
		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtMaMonHoc.setText("");
				txtTenMonHoc.setText("");
				txtTinChi.setText("");
				
				tableModel.setRowCount(0);
				
				if (sessionFactory != null) {
		            Session session = sessionFactory.openSession();
		            try {
		                // Thực hiện truy vấn để lấy dữ liệu từ csdl
		                Query<MonHoc> query = session.createQuery("FROM MonHoc", MonHoc.class);
		                List<MonHoc> kq = query.list();

		                // Hiển thị dữ liệu lên bảng JTable
		                for (MonHoc monHoc : kq) {
		                    Object[] rowData = {monHoc.getMaMonHoc(), monHoc.getTenMonHoc(), monHoc.getSoTinChi()};
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
					.addGap(55)
					.addComponent(btnThem, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(btnXoa, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(40)
					.addComponent(btnSua, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(btnTimKiem, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(btnLamMoi, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(btnThoat, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_Footer.setVerticalGroup(
			gl_panel_Footer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Footer.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_Footer.createParallelGroup(Alignment.LEADING)
						.addComponent(btnThem, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnXoa, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSua, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnTimKiem, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLamMoi, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnThoat, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
		);
		panel_Footer.setLayout(gl_panel_Footer);
		
		JPanel panel_Center = new JPanel();
		contentPane.add(panel_Center, BorderLayout.CENTER);
		
		JPanel panel_info = new JPanel();
		
		JLabel lbMaMonHoc = new JLabel("Mã môn học:");
		lbMaMonHoc.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		
		txtMaMonHoc = new JTextField();
		txtMaMonHoc.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		txtMaMonHoc.setColumns(10);
		
		JLabel lbTenMonHoc = new JLabel("Tên môn học:");
		lbTenMonHoc.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		
		txtTenMonHoc = new JTextField();
		txtTenMonHoc.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		txtTenMonHoc.setColumns(10);
		
		JLabel lbTinChi = new JLabel("Tín chỉ:");
		lbTinChi.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		
		txtTinChi = new JTextField();
		txtTinChi.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		txtTinChi.setColumns(10);
		GroupLayout gl_panel_info = new GroupLayout(panel_info);
		gl_panel_info.setHorizontalGroup(
			gl_panel_info.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_info.createSequentialGroup()
					.addGap(56)
					.addComponent(lbMaMonHoc, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(txtMaMonHoc, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addGap(48)
					.addComponent(lbTenMonHoc, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(txtTenMonHoc, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addGap(51)
					.addComponent(lbTinChi, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(25)
					.addComponent(txtTinChi, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_info.setVerticalGroup(
			gl_panel_info.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_info.createSequentialGroup()
					.addGap(44)
					.addComponent(lbMaMonHoc, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_info.createSequentialGroup()
					.addGap(41)
					.addComponent(txtMaMonHoc, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_info.createSequentialGroup()
					.addGap(44)
					.addComponent(lbTenMonHoc, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_info.createSequentialGroup()
					.addGap(41)
					.addComponent(txtTenMonHoc, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_info.createSequentialGroup()
					.addGap(44)
					.addComponent(lbTinChi, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_info.createSequentialGroup()
					.addGap(41)
					.addComponent(txtTinChi, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
		);
		panel_info.setLayout(gl_panel_info);
		
		JPanel panel_Table = new JPanel();
		panel_Table.setLayout(new BorderLayout(0, 0));
		
		if(sessionFactory!=null) {
			Session session = sessionFactory.openSession();
			
			try {
				Transaction tr = session.beginTransaction();
				
				String hql = "FROM MonHoc";
				Query<MonHoc> query = session.createQuery(hql, MonHoc.class);
				tr.commit();
				List<MonHoc> monList = query.list();
				
				//tableModel = new DefaultTableModel();
				
				  tableModel.addColumn("Mã khoa");
				  tableModel.addColumn("Tên khoa");
				  
				 
		        
		        for (MonHoc monHoc : monList) {
		            Object[] row = new Object[3];
		            row[0] = monHoc.getMaMonHoc();
		            row[1] = monHoc.getTenMonHoc();
		            row[2] = monHoc.getSoTinChi();
		            tableModel.addRow(row);
		        }
		        
		        
		        
		      //  setVisible(true);
				
			}
			finally{
				session.close();
			}
		}
		String[] s = {"Mã môn học", "Tên môn học", "Số tín chỉ"};
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
				.addComponent(panel_Table, GroupLayout.PREFERRED_SIZE, 769, GroupLayout.PREFERRED_SIZE)
				.addComponent(panel_info, GroupLayout.PREFERRED_SIZE, 769, GroupLayout.PREFERRED_SIZE)
		);
		gl_panel_Center.setVerticalGroup(
			gl_panel_Center.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Center.createSequentialGroup()
					.addGap(96)
					.addComponent(panel_Table, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE))
				.addComponent(panel_info, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
		);
		panel_Center.setLayout(gl_panel_Center);
	}
}
