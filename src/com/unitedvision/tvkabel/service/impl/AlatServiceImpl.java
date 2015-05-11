package com.unitedvision.tvkabel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.unitedvision.tvkabel.entity.Alamat;
import com.unitedvision.tvkabel.entity.Alat;
import com.unitedvision.tvkabel.entity.Alat.Status;
import com.unitedvision.tvkabel.entity.Kelurahan;
import com.unitedvision.tvkabel.entity.Perusahaan;
import com.unitedvision.tvkabel.exception.ApplicationException;
import com.unitedvision.tvkabel.repository.AlatRepository;
import com.unitedvision.tvkabel.repository.KelurahanRepository;
import com.unitedvision.tvkabel.service.AlatService;

public class AlatServiceImpl implements AlatService {
	
	@Autowired
	private AlatRepository alatRepository;
	@Autowired
	private KelurahanRepository kelurahanRepository;

	@Override
	public Alat get(Integer id) throws ApplicationException {
		return alatRepository.findOne(id);
	}

	@Override
	public Alat add(Alat alat) throws ApplicationException {
		alat.setStatus(Status.AKTIF);
		
		return save(alat);
	}

	@Override
	public Alat save(Alat alat) throws ApplicationException {
		return alatRepository.save(alat);
	}

	@Override
	public Alat remove(Integer id) throws ApplicationException {
		Alat alat = get(id);
		
		return remove(alat);
	}

	@Override
	public Alat remove(Alat alat) throws ApplicationException {
		alat.setStatus(Status.AKTIF);
		
		return save(alat);
	}

	@Override
	public Alat subtitute(Integer idLama, Integer idBaru) throws ApplicationException {
		Alat alatLama = get(idLama);
		Alat alatBaru = get(idBaru);

		Alamat alamatLama = alatLama.getAlamat();
		Alamat alamatBaru = alatBaru.getAlamat();
		
		alatLama.setAlamat(alamatBaru);
		alatBaru.setAlamat(alamatLama);
		
		save(alatLama);
		save(alatBaru);
		
		return alatBaru;
	}

	@Override
	public List<Alat> getBySource(Integer idSource) throws ApplicationException {
		Alat alat = get(idSource);
		
		return getBySource(alat);
	}

	@Override
	public List<Alat> getBySource(Alat alat) throws ApplicationException {
		return alatRepository.findBySource(alat);
	}

	@Override
	public List<Alat> getByAlamat(Perusahaan perusahaan, Integer idKelurahan, Integer lingkungan) throws ApplicationException {
		Kelurahan kelurahan = kelurahanRepository.findOne(idKelurahan);
		
		return getByAlamat(perusahaan, kelurahan, lingkungan);
	}

	@Override
	public List<Alat> getByAlamat(Perusahaan perusahaan, Kelurahan kelurahan, Integer lingkungan) throws ApplicationException {
		return alatRepository.findByPerusahaanAndAlamat_KelurahanAndAlamat_Lingkungan(perusahaan, kelurahan, lingkungan);
	}

}
