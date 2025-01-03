package view;



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
import model.Khoa;
import model.Lop;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QL_Khoa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMaKhoa;
	private JTextField txtTenKhoa;
	private JTable table;
	private DefaultTableModel tableModel;

	/**
	 * Launch the application.
	 */
	public QL_Khoa() {
		setTitle("Quản lý khoa");
		init();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public void init() {
		 tableModel = new DefaultTableModel();
			tableModel.addColumn("Mã khoa");
			tableModel.addColumn("Tên khoa");
			 table = new JTable(tableModel);
			 table.addMouseListener(new MouseAdapter() {
			 	@Override
			 	public void mouseClicked(MouseEvent e) {
			 		int rowIndex = table.getSelectedRow();
					
					Object maKhoa = table.getValueAt(rowIndex, 0);
					Object tenKhoa = table.getValueAt(rowIndex, 1);
					
					txtMaKhoa.setText(maKhoa.toString());
					txtTenKhoa.setText(tenKhoa.toString());
			 		
			 	}
			 });
			 
		SessionFactory sessionFactory = hibernateUntil.getSessionFactory();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 752, 552);
		
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
		
		JLabel lbTitle = new JLabel("Quản lý khoa");
		lbTitle.setForeground(Color.BLUE);
		lbTitle.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panel_Title.add(lbTitle);
		
		JPanel panel_button = new JPanel();
		contentPane.add(panel_button, BorderLayout.SOUTH);
		
		//BUTTON THÊM
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maKhoa = txtMaKhoa.getText();
				String tenKhoa = txtTenKhoa.getText();
				
				
				if(maKhoa.equals("") || tenKhoa.equals("")) {
					JOptionPane.showConfirmDialog(null,
	                        "Vui lòng nhập đầy đủ dữ liệu!", "Thông báo",
	                        JOptionPane.DEFAULT_OPTION);
				}
				else {
				if(sessionFactory!=null) {
					Session session = sessionFactory.openSession();
					Transaction tr = session.beginTransaction();
					try {
						
						Khoa khoa = new Khoa();
						khoa.setMaKhoa(maKhoa);
						khoa.setTenKhoa(tenKhoa);
						
						
						session.save(khoa);
						tr.commit();
						  // Thêm dòng mới vào DefaultTableModel
		                Object[] rowData = {maKhoa, tenKhoa};
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
		
		// BUTTON XOA
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
		        String maKhoa = tableModel.getValueAt(rowIndex, 0).toString();

		        // Xác nhận việc xóa dữ liệu từ người dùng
		        int confirmResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa dòng này?", "Xác nhận xóa",
		                JOptionPane.YES_NO_OPTION);
		        if (confirmResult == JOptionPane.YES_OPTION) {
		            if (sessionFactory != null) {
		                Session session = sessionFactory.openSession();
		                Transaction tr = session.beginTransaction();
		                try {
		                    Khoa khoa = session.get(Khoa.class, maKhoa);
		                    if (khoa != null) {
		                        session.delete(khoa);
		                        tr.commit();

		                        // Xóa dòng khỏi DefaultTableModel
		                        tableModel.removeRow(rowIndex);

		                        JOptionPane.showConfirmDialog(null, "Xóa dữ liệu thành công!", "Thông báo",
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
		        
			}
		});
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		//BUTTON SỬA
		JButton btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String maKhoa = txtMaKhoa.getText();
		        String tenKhoa = txtTenKhoa.getText();
		        
		        

		        if (sessionFactory != null) {
		            Session session = sessionFactory.openSession();
		            Transaction tr = session.beginTransaction();
		            try {
		                Khoa khoa = session.get(Khoa.class, maKhoa);
		                if (khoa != null) {
		                	khoa.setMaKhoa(maKhoa);
		                	khoa.setTenKhoa(tenKhoa);
		                    

		                    session.saveOrUpdate(khoa);
		                    tr.commit();
		                    // Thêm dòng mới vào DefaultTableModel
		                    int rowIndex = table.getSelectedRow();
		                    tableModel.setValueAt(maKhoa, rowIndex, 0);
		                    tableModel.setValueAt(tenKhoa, rowIndex, 1);
		                   
		                    
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
		
		//BUTTON TÌM KIẾM
		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String tuKhoa = txtMaKhoa.getText().trim();

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
		                Query<Khoa> query = session.createQuery("FROM Khoa WHERE maKhoa LIKE :tuKhoa", Khoa.class);
		                query.setParameter("tuKhoa", "%" + tuKhoa + "%");
		                List<Khoa> ketQua = query.list();

		                // Hiển thị kết quả tìm kiếm trong bảng
		                for (Khoa khoa : ketQua) {
		                    Object[] rowData = {khoa.getMaKhoa(), khoa.getTenKhoa()};
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
		
		//BUTTON LÀM MỚI
		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtMaKhoa.setText("");
				txtTenKhoa.setText("");
				
				tableModel.setRowCount(0);
				
				if (sessionFactory != null) {
		            Session session = sessionFactory.openSession();
		            try {
		                // Thực hiện truy vấn để lấy dữ liệu từ csdl
		                Query<Khoa> query = session.createQuery("FROM Khoa", Khoa.class);
		                List<Khoa> kq = query.list();

		                // Hiển thị dữ liệu lên bảng JTable
		                for (Khoa khoa : kq) {
		                    Object[] rowData = {khoa.getMaKhoa(), khoa.getTenKhoa()};
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
		
		
		GroupLayout gl_panel_button = new GroupLayout(panel_button);
		gl_panel_button.setHorizontalGroup(
			gl_panel_button.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_button.createSequentialGroup()
					.addContainerGap(37, Short.MAX_VALUE)
					.addComponent(btnThem, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(btnXoa, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addComponent(btnSua, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(btnTimKiem, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(btnLamMoi, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addComponent(btnThoat, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(33))
		);
		gl_panel_button.setVerticalGroup(
			gl_panel_button.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_button.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_button.createParallelGroup(Alignment.LEADING)
						.addComponent(btnThem, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnXoa, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSua, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnTimKiem, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_button.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnLamMoi, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnThoat, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))))
		);
		panel_button.setLayout(gl_panel_button);
		
		JPanel panel_Center = new JPanel();
		contentPane.add(panel_Center, BorderLayout.CENTER);
		panel_Center.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_info = new JPanel();
		panel_Center.add(panel_info);
		panel_info.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_info_left = new JPanel();
		panel_info.add(panel_info_left);
		panel_info_left.setLayout(null);
		
		JLabel lbmaKhoa = new JLabel("Mã khoa:");
		lbmaKhoa.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lbmaKhoa.setBounds(87, 91, 45, 13);
		panel_info_left.add(lbmaKhoa);
		
		txtMaKhoa = new JTextField();
		txtMaKhoa.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		txtMaKhoa.setBounds(152, 88, 124, 19);
		panel_info_left.add(txtMaKhoa);
		txtMaKhoa.setColumns(10);
		
		JPanel panel_infp_right = new JPanel();
		panel_info.add(panel_infp_right);
		panel_infp_right.setLayout(null);
		
		JLabel lbTenKhoa = new JLabel("Tên khoa:");
		lbTenKhoa.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lbTenKhoa.setBounds(87, 91, 45, 13);
		panel_infp_right.add(lbTenKhoa);
		
		txtTenKhoa = new JTextField();
		txtTenKhoa.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		txtTenKhoa.setBounds(152, 88, 124, 19);
		panel_infp_right.add(txtTenKhoa);
		txtTenKhoa.setColumns(10);
		
		JPanel panel_Table = new JPanel();
		panel_Center.add(panel_Table);
		panel_Table.setLayout(new BorderLayout(0, 0));
		
		if(sessionFactory!=null) {
			Session session = sessionFactory.openSession();
			
			try {
				Transaction tr = session.beginTransaction();
				
				String hql = "FROM Khoa";
				Query<Khoa> query = session.createQuery(hql, Khoa.class);
				tr.commit();
				List<Khoa> khoaList = query.list();
				
				//tableModel = new DefaultTableModel();
				
				  tableModel.addColumn("Mã khoa");
				  tableModel.addColumn("Tên khoa");
				  
				 
		        
		        for (Khoa khoa : khoaList) {
		            Object[] row = new Object[2];
		            row[0] = khoa.getMaKhoa();
		            row[1] = khoa.getTenKhoa();
		            
		            tableModel.addRow(row);
		        }
		        
		        
		        
		      //  setVisible(true);
				
			}
			finally{
				session.close();
			}
		}
		String[] s = {"Mã khoa", "Tên khoa"};
		tableModel.setColumnIdentifiers(s);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.getTableHeader().setVisible(true); // Hiển thị tiêu đề cột
		table.getTableHeader().setReorderingAllowed(false);
		panel_Table.add(new JScrollPane(table), BorderLayout.CENTER);
	}
}
