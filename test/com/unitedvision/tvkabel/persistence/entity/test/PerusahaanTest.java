package com.unitedvision.tvkabel.persistence.entity.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.unitedvision.tvkabel.exception.EmptyCodeException;
import com.unitedvision.tvkabel.exception.EmptyIdException;
import com.unitedvision.tvkabel.persistence.entity.Alamat;
import com.unitedvision.tvkabel.persistence.entity.Kecamatan;
import com.unitedvision.tvkabel.persistence.entity.Kelurahan;
import com.unitedvision.tvkabel.persistence.entity.Kontak;
import com.unitedvision.tvkabel.persistence.entity.Kota;
import com.unitedvision.tvkabel.persistence.entity.Perusahaan;
import com.unitedvision.tvkabel.persistence.entity.Perusahaan.Status;

public class PerusahaanTest {

	@Test
	public void createDefaultWorks() {
		Perusahaan perusahaan = new Perusahaan();

		assertEquals(true, perusahaan.isNew());
	}

	@Test
	public void setAlamatWorks() {
		Perusahaan perusahaan = new Perusahaan();
		Alamat alamat = new Alamat(1, "", 0, 0);
		perusahaan.setAlamat(alamat);
		
		assertEquals(alamat, perusahaan.getAlamat());
	}

	@Test
	public void generateKode() throws EmptyIdException, EmptyCodeException {
		Kota kota = new Kota(1, "Kota");
		Kecamatan kecamatan = new Kecamatan(1, kota, "Kecamatan");
		Kelurahan kelurahan = new Kelurahan(1, kecamatan, "Kelurahan");
		Alamat alamat = new Alamat(1, "", 0, 0);
		Kontak kontak = new Kontak("1", "2", "3");
		Perusahaan perusahaan = new Perusahaan(1, "1", "1", "PT. 1", kelurahan, alamat, kontak, 1000L, Status.AKTIF);
		
		assertEquals(1, perusahaan.getId());
		assertEquals("1", perusahaan.getKode());
		assertEquals("1", perusahaan.getNama());
		
		perusahaan.generateKode(2);
		
		assertEquals("COM3", perusahaan.getKode());
	}
}
