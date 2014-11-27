package com.unitedvision.tvkabel.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitedvision.tvkabel.exception.ApplicationException;
import com.unitedvision.tvkabel.util.CodeUtil;
import com.unitedvision.tvkabel.web.rest.RestResult;

@Controller
public class PageController extends AbstractController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showHome() {
		return "redirect:/admin";
	}	
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String showHomeAdmin(@RequestParam(required = false) String message, Map<String, Object> model) {
		return "cover_contact";
	}
	
	@RequestMapping(value = "/admin/tunggakan/recount/{kode}", method = RequestMethod.GET)
	public @ResponseBody RestResult recountTunggakan(@PathVariable String kode, Map<String, Object> model) {
		String message;
		try {
			if (!kode.equals(CodeUtil.getKode())) {
				message = "Gagal! Anda tidak punya otoritas!";
			} else {
				pelangganService.recountTunggakan();
				message = "Berhasil!";
			}
		} catch (ApplicationException e) {
			message = String.format("Gagal! %s", e.getMessage());
		}

		return RestResult.create(message);
	}
	
	@RequestMapping(value = "/admin/tunggakan/recount/{kode}/{tanggal}", method = RequestMethod.GET)
	public @ResponseBody RestResult recountTunggakanWithTanggal(@PathVariable String kode, @PathVariable Integer tanggal, Map<String, Object> model) {
		String message;
		try {
			if (!kode.equals(CodeUtil.getKode())) {
				message = "Gagal! Anda tidak memiliki otoritas!";
			} else {
				pelangganService.recountTunggakan(tanggal);
				message = "Berhasil!";
			}
		} catch (ApplicationException e) {
			message = String.format("Gagal! %s", e.getMessage());
		}

		return RestResult.create(message);
	}
}
