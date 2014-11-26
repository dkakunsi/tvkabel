package com.unitedvision.tvkabel.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.unitedvision.tvkabel.core.domain.Kredensi.Role;
import com.unitedvision.tvkabel.core.domain.Pelanggan.Status;
import com.unitedvision.tvkabel.core.domain.Operator;
import com.unitedvision.tvkabel.core.domain.Pegawai;
import com.unitedvision.tvkabel.core.domain.Pelanggan;
import com.unitedvision.tvkabel.core.domain.Perusahaan;
import com.unitedvision.tvkabel.core.service.PegawaiService;
import com.unitedvision.tvkabel.core.service.PelangganService;
import com.unitedvision.tvkabel.exception.ApplicationException;
import com.unitedvision.tvkabel.exception.UnauthenticatedAccessException;
import com.unitedvision.tvkabel.util.MessageUtil;
import com.unitedvision.tvkabel.web.security.SpringAuthenticationBasedAuthorizationProvider;

public abstract class AbstractController {
	@Autowired
	protected SpringAuthenticationBasedAuthorizationProvider authorizationProvider;
	@Autowired
	protected PelangganService pelangganService;
	@Autowired
	protected PegawaiService pegawaiService;
	
	protected Perusahaan getPerusahaan() throws UnauthenticatedAccessException {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return  authorizationProvider.getPerusahaan(authentication);
	}
	
	protected Pegawai getPegawai() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return  authorizationProvider.getPegawai(authentication);
	}
	
	protected String getUserRoleStr() {
		final Operator operator = getPegawai();
		
		return authorizationProvider.getUserRoleStr(operator);
	}

	protected Role getUserRole() {
		final Operator operator = getPegawai();
		
		return authorizationProvider.getUserRole(operator);
	}
	
	protected String createMessage(String process, Integer state, String entityName) {
		if (process != null && state != null)
			return MessageUtil.getMessage(process, state, entityName);
		return null;
	}
	
	protected Status createStatus(String s) {
		if (s != null && !(s.equals("")) && !(s.equals("AKTIF")))
			return Status.valueOf(s.toUpperCase());
		return Status.AKTIF;
	}
	
	protected Pelanggan createPelanggan(String searchBy, String query) throws ApplicationException {
		Perusahaan perusahaan = getPerusahaan();
		
		if (searchBy.contains("nama"))
			return pelangganService.getOneByNama(perusahaan, query);
		return pelangganService.getOneByKode(perusahaan, query);
	}
	
	protected Pegawai createPegawai(String searchBy, String query) throws ApplicationException {
		Perusahaan perusahaan = getPerusahaan();
		
		if (searchBy.toLowerCase().contains("nama"))
			return pegawaiService.getOneByNama(perusahaan, query);
		return pegawaiService.getOneByKode(perusahaan, query);
	}
}