package com.export;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.view.AbstractView;

import com.entity.HoaDon;

public class ExportData extends AbstractView {

	private static Workbook workbook;
	private boolean flag;

	public ExportData(String contentType) {
		if (contentType.equals("application/vnd.ms-excel")) {
			workbook = new HSSFWorkbook();
			flag = false;
		}
		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			workbook = new XSSFWorkbook();
			flag = true;
		}
		setContentType(contentType);
	}

	// set style cell
	public static CellStyle cellStyle(Workbook workbook) {
		Font font = workbook.createFont();
		// font.setBold(true);
		// font.setFontName(HSSFFont.FONT_ARIAL);
		font.setFontName("Times New Roman");
		font.setFontHeightInPoints((short) 13);

		// set style cho head
		CellStyle style = workbook.createCellStyle();

		// căn giữa
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFont(font);

		// kẻ bảng
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		return style;
	}

	public static CellStyle backGroundCellHeader(CellStyle style) {
		style.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		return style;
	}

	// set background cho cell
	public static CellStyle backGroundCellValue(CellStyle style) {
		style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		return style;
	}

	// tao row header
	public static void createHeader(Row header, Workbook workbook, Sheet sheet) {
		String[] listHeader = { "Mã HD", "Ngày lập", "Mã SP", "Tên SP", "Số lượng", "Giá tiền", "Khuyến mại", "VAT",
				"Tổng tiền", "Mã khách hàng", "Tên khách hàng", "Điểm tích", "xếp hạng", "Mã nhân viên" };
		for (int i = 0; i < listHeader.length; i++) {
			header.createCell(i).setCellValue(listHeader[i]);
			header.setHeight((short) 500);
			header.getCell(i).setCellStyle(backGroundCellHeader(cellStyle(workbook)));
		}
		for (int i = 0; i < cellNum; i++) {
			sheet.autoSizeColumn(i);
		}
	}
	// tao du lieu cho cac cell

	static int cellNum;
	static Row row = null;

	public static void createCellValue(Workbook workbook, Sheet sheet, ArrayList<HoaDon> listHD) {
		int rownum = 1;
		for (int i = 0; i < listHD.size(); i++) {
			row = sheet.createRow(rownum);
			row.setHeight((short) 360);
			cellNum = 0;

			row.createCell(cellNum).setCellValue(listHD.get(i).getMahd());
			row.getCell(cellNum).setCellStyle(backGroundCellValue(cellStyle(workbook)));
			cellNum++;

			row.createCell(cellNum).setCellValue(listHD.get(i).getNgayLap());
			row.getCell(cellNum).setCellStyle(backGroundCellValue(cellStyle(workbook)));
			cellNum++;

			row.createCell(cellNum).setCellValue(listHD.get(i).getSanpham().getMasp());
			row.getCell(cellNum).setCellStyle(backGroundCellValue(cellStyle(workbook)));
			cellNum++;

			row.createCell(cellNum).setCellValue(listHD.get(i).getSanpham().getTensp());
			row.getCell(cellNum).setCellStyle(backGroundCellValue(cellStyle(workbook)));
			cellNum++;

			row.createCell(cellNum, CellType.NUMERIC).setCellValue(Integer.parseInt(listHD.get(i).getSoluong()));
			row.getCell(cellNum).setCellStyle(backGroundCellValue(cellStyle(workbook)));
			cellNum++;

			row.createCell(cellNum, CellType.NUMERIC)
					.setCellValue(Integer.parseInt(listHD.get(i).getSanpham().getGia()));
			row.getCell(cellNum).setCellStyle(backGroundCellValue(cellStyle(workbook)));
			cellNum++;

			row.createCell(cellNum, CellType.NUMERIC)
					.setCellValue(Integer.parseInt(listHD.get(i).getSanpham().getKhuyenmai()));
			row.getCell(cellNum).setCellStyle(backGroundCellValue(cellStyle(workbook)));
			cellNum++;

			row.createCell(cellNum, CellType.NUMERIC).setCellValue(Integer.parseInt(listHD.get(i).getVat()));
			row.getCell(cellNum).setCellStyle(backGroundCellValue(cellStyle(workbook)));
			cellNum++;

			String fomula = "E" + (rownum + 1) + "*F" + (rownum + 1) + "-(F" + (rownum + 1) + "*0.1*G" + (rownum + 1)+ ")" + "+H" + (rownum + 1);
			row.createCell(cellNum, CellType.FORMULA).setCellFormula(fomula);
			row.getCell(cellNum).setCellStyle(backGroundCellValue(cellStyle(workbook)));
			cellNum++;

			row.createCell(cellNum).setCellValue(listHD.get(i).getKhachhang().getMakh());
			row.getCell(cellNum).setCellStyle(backGroundCellValue(cellStyle(workbook)));
			cellNum++;

			row.createCell(cellNum).setCellValue(listHD.get(i).getKhachhang().getFullName());
			row.getCell(cellNum).setCellStyle(backGroundCellValue(cellStyle(workbook)));
			cellNum++;

			row.createCell(cellNum).setCellValue(listHD.get(i).getKhachhang().getDiemtich());
			row.getCell(cellNum).setCellStyle(backGroundCellValue(cellStyle(workbook)));
			cellNum++;

			row.createCell(cellNum).setCellValue(listHD.get(i).getKhachhang().getXephang());
			row.getCell(cellNum).setCellStyle(backGroundCellValue(cellStyle(workbook)));
			cellNum++;

			row.createCell(cellNum).setCellValue(listHD.get(i).getNhanvien().getManv());
			row.getCell(cellNum).setCellStyle(backGroundCellValue(cellStyle(workbook)));

			cellNum++;
			rownum++;
		}

		for (int i = 0; i < cellNum; i++) {
			 sheet.autoSizeColumn(i, true);

		}

		// Row row = workbook.getSheetAt(0).getRow(0);
		// for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
		// // workbook.getSheetAt(0).setColumnWidth(colNum, 8*256+200);
		// PixelUtil.setColumnWidth((HSSFSheet) workbook.getSheetAt(0), colNum,
		// sheet.getColumnWidth(colNum) / 256 * 2);
		// int width = ((int) (256 * 1.14388)) * 256;
		// workbook.getSheetAt(0).setColumnWidth(colNum, width);
		// }

	}

	// lấy độ dài của từng cell
	private static String getExcelCellString(Cell cell) {
		if (cell == null) {
			return null;
		}
		int type = cell.getCellType();
		switch (type) {
		case Cell.CELL_TYPE_STRING:
			// return cell.getRichStringCellValue().getString();
			return cell.getStringCellValue();
		case Cell.CELL_TYPE_NUMERIC:
			return cell.getNumericCellValue() + " ";

		case Cell.CELL_TYPE_FORMULA:
			switch (cell.getCachedFormulaResultType()) {
			case Cell.CELL_TYPE_STRING:
				// return cell.getRichStringCellValue().getString();
				return cell.getStringCellValue();
			case Cell.CELL_TYPE_NUMERIC:
				return cell.getNumericCellValue() + " ";
			default:
				break;
			}
		default:
			break;
		}
		return null;
	}

	public static int cong(int n) {
		if (n > 0 && n <= 5) {
			return 5;
		} else if (n > 5 && n <= 10) {
			return 10;
		} else if (n > 10 && n <= 15) {
			return 15;
		} else if (n > 15 && n <= 20) {
			return 20;
		} else
			return 30;
	}

	// build file xls
	public static void buildExcelXLS(Map<String, Object> hoadon, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"list_hoadon.xls\"");
		@SuppressWarnings("unchecked")
		ArrayList<HoaDon> listHD = (ArrayList<HoaDon>) hoadon.get("listHoadon");
		HSSFSheet sheet = (HSSFSheet) workbook.createSheet("Danh Sách hóa đơn");

		// format header
		HSSFRow header = (HSSFRow) sheet.createRow(0);
		createHeader(header, workbook, sheet);
		;
		createCellValue(workbook, sheet, listHD);

	}

	// build file slsx
	public void buildExcelXLSX(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "attachment; filename=list_hoadon.xlsx");
		@SuppressWarnings("unchecked")
		ArrayList<HoaDon> listHD = (ArrayList<HoaDon>) model.get("listHoadon");
		XSSFSheet sheet = (XSSFSheet) workbook.createSheet("Danh sách hóa đơn");
		// format header
		XSSFRow header = sheet.createRow(0);
		createHeader(header, workbook, sheet);
		createCellValue(workbook, sheet, listHD);
	}

	// ghi file
	public void renderWorkbook(Workbook workbook, HttpServletResponse response) throws IOException {
		ServletOutputStream out = response.getOutputStream();
		workbook.write(out);

		// Closeable only implemented as of POI 3.10
		if (workbook instanceof Closeable) {
			((Closeable) workbook).close();
		}
	}

	@Override
	protected final void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// Delegate to application-provided document code.
		if (flag) {
			buildExcelXLSX(model, workbook, request, response);
		} else {
			buildExcelXLS(model, workbook, request, response);
		}
		// Set the content type.
		response.setContentType(getContentType());

		// Flush byte array to servlet output stream.
		renderWorkbook(workbook, response);
	}

}
