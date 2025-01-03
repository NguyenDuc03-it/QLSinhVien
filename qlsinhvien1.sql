USE [QuanLySinhVien]
GO
/****** Object:  Table [dbo].[Diem]    Script Date: 03/01/2025 8:26:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Diem](
	[maMonHoc] [varchar](20) NOT NULL,
	[maSV] [varchar](20) NOT NULL,
	[namHoc] [varchar](20) NULL,
	[diemChuyenCan] [int] NULL,
	[diemGiuaKy] [float] NULL,
	[diemCuoiKy] [float] NULL,
 CONSTRAINT [PK_Diem] PRIMARY KEY CLUSTERED 
(
	[maMonHoc] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Khoa]    Script Date: 03/01/2025 8:26:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Khoa](
	[maKhoa] [varchar](20) NOT NULL,
	[tenKhoa] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[maKhoa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Login]    Script Date: 03/01/2025 8:26:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Login](
	[username] [varchar](50) NOT NULL,
	[password] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Lop]    Script Date: 03/01/2025 8:26:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Lop](
	[maLop] [varchar](20) NOT NULL,
	[tenLop] [nvarchar](50) NULL,
	[heDaoTao] [nvarchar](50) NULL,
	[namNhapHoc] [varchar](10) NULL,
	[maKhoa] [varchar](20) NULL,
	[siSo] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[maLop] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MonHoc]    Script Date: 03/01/2025 8:26:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MonHoc](
	[maMonHoc] [varchar](20) NOT NULL,
	[tenMonHoc] [nvarchar](50) NULL,
	[soTinChi] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[maMonHoc] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SinhVien]    Script Date: 03/01/2025 8:26:13 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SinhVien](
	[maSV] [varchar](20) NOT NULL,
	[Ten] [nvarchar](50) NULL,
	[ngaySinh] [varchar](20) NULL,
	[gioiTinh] [nvarchar](10) NULL,
	[maLop] [varchar](20) NULL,
	[nienKhoa] [varchar](20) NULL,
	[SDT] [varchar](15) NULL,
	[noiSinh] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[maSV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Diem]  WITH CHECK ADD FOREIGN KEY([maMonHoc])
REFERENCES [dbo].[MonHoc] ([maMonHoc])
GO
ALTER TABLE [dbo].[Diem]  WITH CHECK ADD FOREIGN KEY([maSV])
REFERENCES [dbo].[SinhVien] ([maSV])
GO
ALTER TABLE [dbo].[Lop]  WITH CHECK ADD FOREIGN KEY([maKhoa])
REFERENCES [dbo].[Khoa] ([maKhoa])
GO
ALTER TABLE [dbo].[SinhVien]  WITH CHECK ADD FOREIGN KEY([maLop])
REFERENCES [dbo].[Lop] ([maLop])
GO
