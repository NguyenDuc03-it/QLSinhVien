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
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QL_lop_JFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtMaLop;
	private JTextField txtTenLop;
	private JTextField txtHeDaoTao;
	private JTextField txtNamNhapHoc;
	private JTextField txtMaKhoa;
	private JTextField txtSiSo;
	private DefaultTableModel tableModel;
	/**
	 * Launch the application.
	 */
	public QL_lop_JFrame() {
		setTitle("Quản lý lớp");
		init();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public void init() {
		SessionFactory sessionFactory = hibernateUntil.getSessionFactory();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 876, 576);
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
		
		JPanel panel_TiTle = new JPanel();
		contentPane.add(panel_TiTle, BorderLayout.NORTH);
		panel_TiTle.setLayout(new BorderLayout(0, 0));
		
		JLabel lbTitle = new JLabel("Quản lý lớp");
		lbTitle.setForeground(Color.BLUE);
		lbTitle.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panel_TiTle.add(lbTitle, BorderLayout.CENTER);
		
		JPanel panel_Footer = new JPanel();
		contentPane.add(panel_Footer, BorderLayout.SOUTH);
		
		 tableModel = new DefaultTableModel();
				tableModel.addColumn("Mã Lớp");
				tableModel.addColumn("Tên Lớp");
				tableModel.addColumn("Hệ Đào Tạo");
				tableModel.addColumn("Năm Nhập Học");
				tableModel.addColumn("Mã Khoa");
				tableModel.addColumn("Sĩ Số");
				 table = new JTable(tableModel);
				
		// BUTTON THEM
		JButton btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Tạo DefaultTableModel với các cột tương ứng
				
				// Gán DefaultTableModel cho JTable
				
				String maLop = txtMaLop.getText();
				String tenLop = txtTenLop.getText();
				String heDaoTao = txtHeDaoTao.getText();
				String namNhapHoc = txtNamNhapHoc.getText();
				String maKhoa = txtMaKhoa.getText();
			//	int siSo =  Integer.parseInt(txtSiSo.getText());
				String SiSo = txtSiSo.getText();
				if(maLop.equals("")||tenLop.equals("")||heDaoTao.equals("")||namNhapHoc.equals("")||maKhoa.equals("")||SiSo.equals("")) {
					JOptionPane.showConfirmDialog(null,
	                        "Vui lòng nhập đầy đủ dữ liệu!", "Thông báo",
	                        JOptionPane.DEFAULT_OPTION);
				}
				else {
				int	siSo =  Integer.parseInt(txtSiSo.getText());
				if(sessionFactory!=null) {
					Session session = sessionFactory.openSession();
					Transaction tr = session.beginTransaction();
					try {
						
						Lop lop = new Lop();
						lop.setMaLop(maLop);
						lop.setTenLop(tenLop);
						lop.setHeDaoTao(heDaoTao);
						lop.setNamNhapHoc(namNhapHoc);
						lop.setMaKhoa(maKhoa);
						lop.setSiSo(siSo);
						
						session.save(lop);
						tr.commit();
						  // Thêm dòng mới vào DefaultTableModel
		                Object[] rowData = {maLop, tenLop, heDaoTao, namNhapHoc, maKhoa, siSo};
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
			}});
		
		// BUTTON SỬA
		JButton btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String maLop = txtMaLop.getText();
		        String tenLop = txtTenLop.getText();
		        String heDaoTao = txtHeDaoTao.getText();
		        String namNhapHoc = txtNamNhapHoc.getText();
		        String maKhoa = txtMaKhoa.getText();
		        int siSo = Integer.parseInt(txtSiSo.getText());

		        if (sessionFactory != null) {
		            Session session = sessionFactory.openSession();
		            Transaction tr = session.beginTransaction();
		            try {
		                Lop lop = session.get(Lop.class, maLop);
		                if (lop != null) {
		                    lop.setTenLop(tenLop);
		                    lop.setHeDaoTao(heDaoTao);
		                    lop.setNamNhapHoc(namNhapHoc);
		                    lop.setMaKhoa(maKhoa);
		                    lop.setSiSo(siSo);

		                    session.saveOrUpdate(lop);
		                    tr.commit();
		                    // Thêm dòng mới vào DefaultTableModel
		                    int rowIndex = table.getSelectedRow();
		                    tableModel.setValueAt(maLop, rowIndex, 0);
		                    tableModel.setValueAt(tenLop, rowIndex, 1);
		                    tableModel.setValueAt(heDaoTao, rowIndex, 2);
		                    tableModel.setValueAt(namNhapHoc, rowIndex, 3);
		                    tableModel.setValueAt(maKhoa, rowIndex, 4);
		                    tableModel.setValueAt(siSo, rowIndex, 5);

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
		        String maLop = tableModel.getValueAt(rowIndex, 0).toString();

		        // Xác nhận việc xóa dữ liệu từ người dùng
		        int confirmResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa dòng này?", "Xác nhận xóa",
		                JOptionPane.YES_NO_OPTION);
		        if (confirmResult == JOptionPane.YES_OPTION) {
		            if (sessionFactory != null) {
		                Session session = sessionFactory.openSession();
		                Transaction tr = session.beginTransaction();
		                try {
		                    Lop lop = session.get(Lop.class, maLop);
		                    if (lop != null) {
		                        session.delete(lop);
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
		
		//BUTTON TÌM KIẾM
		JButton btnTmKim = new JButton("Tìm kiếm");
		btnTmKim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tuKhoa = txtMaLop.getText().trim();

		        // Xóa các dòng hiện có trong bảng
		        
		        
		        if(tuKhoa.equals("")){
		        	JOptionPane.showConfirmDialog(null, "Nhập mã lớp để tìm kiếm", "Thông báo",
	                        JOptionPane.DEFAULT_OPTION);
		        }
		        else { 
		        	if (sessionFactory != null) {
		            Session session = sessionFactory.openSession();
		            while (tableModel.getRowCount() > 0) {
			            tableModel.removeRow(0);
			        }
		            try {
		                // Thực hiện truy vấn tìm kiếm dựa trên từ khóa
		                Query<Lop> query = session.createQuery("FROM Lop WHERE maLop LIKE :tuKhoa", Lop.class);
		                query.setParameter("tuKhoa", "%" + tuKhoa + "%");
		                List<Lop> ketQua = query.list();

		                // Hiển thị kết quả tìm kiếm trong bảng
		                for (Lop lop : ketQua) {
		                    Object[] rowData = {lop.getMaLop(), lop.getTenLop(), lop.getHeDaoTao(), lop.getNamNhapHoc(), lop.getMaKhoa(), lop.getSiSo()};
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
		btnTmKim.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
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
				txtMaLop.setText("");
				txtTenLop.setText("");
				txtHeDaoTao.setText("");
				txtNamNhapHoc.setText("");
				txtMaKhoa.setText("");
				txtSiSo.setText("");
				
				tableModel.setRowCount(0);
				
				if (sessionFactory != null) {
		            Session session = sessionFactory.openSession();
		            try {
		                // Thực hiện truy vấn để lấy dữ liệu từ csdl
		                Query<Lop> query = session.createQuery("FROM Lop", Lop.class);
		                List<Lop> kq = query.list();

		                // Hiển thị dữ liệu lên bảng JTable
		                for (Lop lop : kq) {
		                    Object[] rowData = {lop.getMaLop(), lop.getTenLop(), lop.getHeDaoTao(), lop.getNamNhapHoc(), lop.getMaKhoa(), lop.getSiSo()};
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
		GroupLayout gl_panel_Footer = new GroupLayout(panel_Footer);
		gl_panel_Footer.setHorizontalGroup(
			gl_panel_Footer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Footer.createSequentialGroup()
					.addGap(43)
					.addComponent(btnThem, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(65)
					.addComponent(btnSua, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(69)
					.addComponent(btnXoa, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(59)
					.addComponent(btnTmKim, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addComponent(btnLamMoi)
					.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
					.addComponent(btnThoat, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(24))
		);
		gl_panel_Footer.setVerticalGroup(
			gl_panel_Footer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Footer.createSequentialGroup()
					.addGroup(gl_panel_Footer.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Footer.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnThem, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnSua, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnXoa, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_Footer.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnThoat, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnTmKim, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnLamMoi, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_Footer.setLayout(gl_panel_Footer);
		
		JPanel panel_Center = new JPanel();
		contentPane.add(panel_Center, BorderLayout.CENTER);
		panel_Center.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_info = new JPanel();
		panel_Center.add(panel_info);
		panel_info.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_West_info = new JPanel();
		panel_info.add(panel_West_info);
		panel_West_info.setLayout(null);
		
		JLabel lbMaLop = new JLabel("Mã lớp:");
		lbMaLop.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lbMaLop.setBounds(75, 43, 45, 13);
		panel_West_info.add(lbMaLop);
		
		JLabel lbTenLop = new JLabel("Tên lớp:");
		lbTenLop.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lbTenLop.setBounds(75, 86, 45, 13);
		panel_West_info.add(lbTenLop);
		
		JLabel lbHDT = new JLabel("Hệ đào tạo:");
		lbHDT.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lbHDT.setBounds(75, 131, 49, 13);
		panel_West_info.add(lbHDT);
		
		txtMaLop = new JTextField();
		txtMaLop.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		txtMaLop.setBounds(162, 40, 123, 19);
		panel_West_info.add(txtMaLop);
		txtMaLop.setColumns(10);
		
		txtTenLop = new JTextField();
		txtTenLop.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		txtTenLop.setColumns(10);
		txtTenLop.setBounds(162, 83, 123, 19);
		panel_West_info.add(txtTenLop);
		
		txtHeDaoTao = new JTextField();
		txtHeDaoTao.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		txtHeDaoTao.setColumns(10);
		txtHeDaoTao.setBounds(162, 128, 123, 19);
		panel_West_info.add(txtHeDaoTao);
		
		JPanel panel_East_info = new JPanel();
		panel_info.add(panel_East_info);
		panel_East_info.setLayout(null);
		
		JLabel lbNamNhapHoc = new JLabel("Năm nhập học:");
		lbNamNhapHoc.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lbNamNhapHoc.setBounds(76, 41, 69, 13);
		panel_East_info.add(lbNamNhapHoc);
		
		JLabel lbMaKhoa = new JLabel("Mã khoa:");
		lbMaKhoa.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lbMaKhoa.setBounds(76, 82, 45, 13);
		panel_East_info.add(lbMaKhoa);
		
		JLabel lbSiSo = new JLabel("Sĩ số:");
		lbSiSo.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lbSiSo.setBounds(76, 120, 45, 13);
		panel_East_info.add(lbSiSo);
		
		txtNamNhapHoc = new JTextField();
		txtNamNhapHoc.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		txtNamNhapHoc.setColumns(10);
		txtNamNhapHoc.setBounds(186, 38, 123, 19);
		panel_East_info.add(txtNamNhapHoc);
		
		txtMaKhoa = new JTextField();
		txtMaKhoa.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		txtMaKhoa.setColumns(10);
		txtMaKhoa.setBounds(186, 79, 123, 19);
		panel_East_info.add(txtMaKhoa);
		
		txtSiSo = new JTextField();
		txtSiSo.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		txtSiSo.setColumns(10);
		txtSiSo.setBounds(186, 117, 123, 19);
		panel_East_info.add(txtSiSo);
		
		JPanel panel_table = new JPanel();
		panel_Center.add(panel_table);
		panel_table.setLayout(new BorderLayout(0, 0));
		
		if(sessionFactory!=null) {
			Session session = sessionFactory.openSession();
			
			try {
				Transaction tr = session.beginTransaction();
				
				String hql = "FROM Lop";
				Query<Lop> query = session.createQuery(hql, Lop.class);
				tr.commit();
				List<Lop> lopList = query.list();
				
				//tableModel = new DefaultTableModel();
		        tableModel.addColumn("Mã lớp");
		        tableModel.addColumn("Tên lớp");
		        tableModel.addColumn("Hệ đào tạo");
		        tableModel.addColumn("Năm nhập học");
		        tableModel.addColumn("Mã khoa");
		        tableModel.addColumn("Sĩ số");
		        
		        for (Lop lop : lopList) {
		            Object[] row = new Object[6];
		            row[0] = lop.getMaLop();
		            row[1] = lop.getTenLop();
		            row[2] = lop.getHeDaoTao();
		            row[3] = lop.getNamNhapHoc();
		            row[4] = lop.getMaKhoa();
		            row[5] = lop.getSiSo();
		            tableModel.addRow(row);
		        }
		        
		      //  setVisible(true);
				
			}
			finally{
				session.close();
			}
		}
		
		//table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowIndex = table.getSelectedRow();
				
				Object maLop = table.getValueAt(rowIndex, 0);
				Object tenLop = table.getValueAt(rowIndex, 1);
				Object heDaoTao = table.getValueAt(rowIndex, 2);
				Object namNhapHoc = table.getValueAt(rowIndex, 3);
				Object maKhoa = table.getValueAt(rowIndex, 4);
				Object siSo = table.getValueAt(rowIndex, 5);
				
				txtMaLop.setText(maLop.toString());
				txtTenLop.setText(tenLop.toString());
				txtHeDaoTao.setText(heDaoTao.toString());
				txtNamNhapHoc.setText(namNhapHoc.toString());
				txtMaKhoa.setText(maKhoa.toString());
				txtSiSo.setText(siSo.toString());
				
			}
		});
		String[] s = {"Mã lớp","Tên lớp","Hệ đào tạo","Năm nhập học","Mã khoa","Sĩ số"};
		tableModel.setColumnIdentifiers(s);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.getTableHeader().setVisible(true); // Hiển thị tiêu đề cột
		table.getTableHeader().setReorderingAllowed(false);
		panel_table.add(new JScrollPane(table), BorderLayout.CENTER);
		
	}}

