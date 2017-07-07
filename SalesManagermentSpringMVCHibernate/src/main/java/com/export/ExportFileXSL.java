package com.export;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.entity.HoaDon;

public class ExportFileXSL extends AbstractXlsView {

	// set style cell
	public static CellStyle cellStyle(Workbook workbook) {
		Font font = workbook.createFont();
		font.setBold(true);
		font.setFontName(HSSFFont.FONT_ARIAL);
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

	private static CellStyle backGroundCellHeader(CellStyle style) {
		style.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		return style;
	}

	//set background cho cell
	private static CellStyle backGroundCellValue(CellStyle style) {
		style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		return style;
	}

	// tao row header
	public static void createHeader(Row header, Workbook workbook) {
		String[] listHeader = { "Mã HD", "Ngày lập", "Mã SP", "Tên SP", "Giá tiền", "Khuyến mại", "VAT", "Tổng tiền",
				"Điểm tích", "Mã Người mua", "xếp hạng", "Mã nhân viên" };
		for (int i = 0; i < listHeader.length; i++) {
			header.createCell(i).setCellValue(listHeader[i]);
			header.getCell(i).setCellStyle(backGroundCellHeader(cellStyle(workbook)));
		}
	}

	public static void createCellValue(Workbook workbook, Sheet sheet, ArrayList<HoaDon> listHD) {
		int rowNum = 1;
		for (int i = 0; i < listHD.size(); i++) {
			Row row = sheet.createRow(rowNum);
			int cellNum = 0;

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

			row.createCell(cellNum).setCellValue(listHD.get(i).getSanpham().getGia());
			row.getCell(cellNum).setCellStyle(backGroundCellValue(cellStyle(workbook)));
			cellNum++;

			row.createCell(cellNum).setCellValue(listHD.get(i).getSanpham().getKhuyenmai());
			row.getCell(cellNum).setCellStyle(backGroundCellValue(cellStyle(workbook)));
			cellNum++;

			row.createCell(cellNum).setCellValue(listHD.get(i).getVat());
			row.getCell(cellNum).setCellStyle(backGroundCellValue(cellStyle(workbook)));
			cellNum++;

			row.createCell(cellNum).setCellValue(listHD.get(i).getTongtien());
			row.getCell(cellNum).setCellStyle(backGroundCellValue(cellStyle(workbook)));
			cellNum++;

			row.createCell(cellNum).setCellValue(listHD.get(i).getKhachhang().getMakh());
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

			rowNum++;
		}

		for (int i = 0; i < listHD.size(); i++) {
			sheet.autoSizeColumn(i);
		}
	}

	@Override
	public void buildExcelDocument(Map<String, Object> hoadon, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"list_hoadon.xls\"");
		@SuppressWarnings("unchecked")
		ArrayList<HoaDon> listHD = (ArrayList<HoaDon>) hoadon.get("listHoadon");
		HSSFSheet sheet = (HSSFSheet) workbook.createSheet("HoaDon List");

		HSSFRow header = (HSSFRow) sheet.createRow(0);
		// tao header
		createHeader(header, workbook);
		// set value cell
		createCellValue(workbook, sheet, listHD);
	}

}
