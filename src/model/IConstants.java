package model;

public interface IConstants {
	public static String XEM_THONG_TIN = "Xem";
	public static String THEM_SACH = "Thêm";
	public static String TIM_KIEM = "Tìm Kiếm";
	public static String DAT_LAI = "Đặt Lại";
	public static String HUY_BO = "Hủy Bỏ";
	public static String GO_BACK = "Trở Về";
	public static String [] FIND_MODE = {
			"Tìm Kiếm Theo Tên",
			"Tìm Kiếm Theo Tác Giả",
			"Tìm Kiếm Theo Nhà Xuất Bản"
	};
	public static String [] SHOW_MODE = {
			"Xem Toàn Bộ",
			"Xem Sách Đã Cho Mượn",
			"Xem Sách Chưa Cho Mượn",
			"Xem Theo Tên",
			"Xem Theo Tác Giả",
			"Xem Nhà Xuất Bản",
			"Xem Theo Năm Xuất Bản"
	};
	public static String sID = "Mã Sách: ";
	public static String sNAME = "Tên Sách: ";
	public static String sAUTHOR = "Tác Giả: ";
	public static String sCOM = "Nhà Phát Hành: ";
	public static String sYEAR = "Năm Phát Hành: ";
	public static String sSTATUS = "Tình Trạng Sách: ";
	
	public static String s_ID = "Mã Sách";
	public static String s_NAME = "Tên Sách";
	public static String s_AUTHOR = "Tác Giả";
	public static String s_COM = "Nhà Phát Hành";
	public static String s_YEAR = "Năm Phát Hành";
	public static String s_STATUS = "Tình Trạng Sách";
	
	public static String [] INSERT_MODE = {
			"Thêm Đầu Danh Sách",
			"Thêm Sau Vị Trí K",
			"Thêm Cuối Danh Sách"
	};
	public static String HAS_BOOK = "Còn Sách";
	public static String HAS_NOT_BOOK = "Tạm Hết Sách";
	public static String DEL = "Xóa";
	public static String [] DEL_MODE = {
			"Mã Số",
			"1 Sách Sau Mã Số",
			"Tên Sách",
			"Tên Tác Giả",
			"Xóa Đầu Danh Sách",
			"Xóa Cuối Danh Sách",
	};
}
