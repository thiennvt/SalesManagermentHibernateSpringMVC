package com.export;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.entity.HoaDon;

public class ExportFileXSLX extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "attachment; filename=list_hoadon.xlsx");
		@SuppressWarnings("unchecked")
		ArrayList<HoaDon> listHD = (ArrayList<HoaDon>) model.get("listHoadon");
		XSSFSheet sheet = (XSSFSheet) workbook.createSheet("HoaDon List");
		// format header
		XSSFRow header = sheet.createRow(0);
		ExportFileXSL.createHeader(header, workbook);
		ExportFileXSL.createCellValue(workbook, sheet, listHD);
	}

}
