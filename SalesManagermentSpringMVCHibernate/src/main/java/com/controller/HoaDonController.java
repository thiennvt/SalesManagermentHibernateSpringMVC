package com.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.entity.HoaDon;
import com.export.ExportData;
import com.model.HoaDonModel;

@Controller
@RequestMapping(value = "hoadon")
public class HoaDonController {

	@Autowired
	HoaDonModel hdModel;

	public HoaDonController() {
		// TODO Auto-generated constructor stub
	}

	// hiển thị trang chào mừng
	@RequestMapping(value = "/initShowWelcome", method = RequestMethod.GET)
	public ModelAndView initShowWelcomme() {
		ModelAndView model = new ModelAndView("welcome");
		return model;
	}

	// hiển thị trang chủ của web
	@RequestMapping(value = "/initShowhomepage", method = RequestMethod.GET)
	public ModelAndView initShowHomepage() {
		ModelAndView model = new ModelAndView("homepage");
		return model;
	}
	
//	// hiển thị trang chủ của web
//		@RequestMapping(value = "/initGetAll", method = RequestMethod.GET)
//		public ModelAndView initGetAll() {
//			ModelAndView model = new ModelAndView("homepage");
//			ArrayList<HoaDon> listHoadon = hdModel.dsHoaDon();
//			model.addObject("listHoadon", listHoadon);
//			return model;
//		}


	// hiển thi danh sách hóa đơn lên giao
	@RequestMapping(value = "initGetAll", method = RequestMethod.GET)
	public ModelAndView getAll(HttpServletRequest request, HttpServletResponse response) {
//		ArrayList<HoaDon> listHoadon = hdModel.dsHoaDon();
		ArrayList<HoaDon>listHoadon = hdModel.listHoaDon();
		ModelAndView model = new ModelAndView("DanhSachHoaDon");
		HoaDon hd = new HoaDon();
		model.getModelMap().put("listHoadon", listHoadon);
		String typeReport = request.getParameter("type");

		if (typeReport != null && typeReport.equals("xls")) {
			// model = new ModelAndView(new ExportFileXSL(), "listHoadon", listHoadon);
			model = new ModelAndView(new ExportData("application/vnd.ms-excel"), "listHoadon", listHoadon);
			model.getModelMap().put("hoadon", hd);
			return model;
		}
		if (typeReport != null && typeReport.equals("xlsx")) {
			model = new ModelAndView(
					new ExportData("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"), "listHoadon",
					listHoadon);
			model.getModelMap().put("hoadon", hd);
			// return new ModelAndView(new HoaDonExcelXSLX(), "listHoadon",
			// listHoadon);
			return model;
		}
		model = new ModelAndView("DanhSachHoaDon", "listHoadon", listHoadon);
		model.getModelMap().put("hoadon", hd);
		return model;
	}

	// xử lí tìm kiếm thông tin hóa đơn theo ngày đuợc nhập vào
	@RequestMapping(value = "xuLiTimKiem", method = RequestMethod.GET)
	public ModelAndView search(@ModelAttribute("hoadon") HoaDon hoadon, HttpServletRequest request,
			HttpServletResponse response) {
		ArrayList<HoaDon> listHD = hdModel.search(hoadon);
		String typeReport = request.getParameter("type");
		if (typeReport != null && typeReport.equals("xls")) {
			return new ModelAndView(new ExportData("application/vnd.ms-excel"), "listHoadon", listHD);
		}
		if (typeReport != null && typeReport.equals("xlsx")) {
			return new ModelAndView(new ExportData("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
					"listHoadon", listHD);
		}
		return new ModelAndView("DanhSachHoaDon", "listHoadon", listHD);
	}
}
