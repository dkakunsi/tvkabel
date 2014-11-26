package com.unitedvision.tvkabel.core.service;

import java.util.Date;
import java.util.List;

import com.unitedvision.tvkabel.core.domain.Operator;
import com.unitedvision.tvkabel.core.domain.Perusahaan;
import com.unitedvision.tvkabel.exception.ApplicationException;
import com.unitedvision.tvkabel.exception.EntityNotExistException;

public interface PerusahaanService extends Service<Perusahaan> {
	Perusahaan getByKode(String kode) throws EntityNotExistException;
	Operator regist(Perusahaan perusahaan) throws ApplicationException;
	
	void setMapLocation(Perusahaan perusahaan, float latitude, float longitude) throws ApplicationException;

	//This will be use for admin's page
	List<? extends Perusahaan> getAll();
	
	long countTagihanBulanBerjalan(Perusahaan perusahaan, Date tanggalAwal, Date tanggalAkhir);
	long countTagihanBulanBerjalan(Perusahaan perusahaan);
	long countEstimasiPemasukanBulanan(Perusahaan perusahaan);
	long countEstimasiTagihanBulanan(Perusahaan perusahaan);
	long countTotalAkumulasiTunggakan(Perusahaan perusahaan);
	long countPemasukanBulanBerjalan(Perusahaan perusahaan);
}