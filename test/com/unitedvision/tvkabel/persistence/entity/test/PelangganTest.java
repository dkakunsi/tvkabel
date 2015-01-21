package com.unitedvision.tvkabel.persistence.entity.test;

import static org.junit.Assert.*;

import java.time.Month;

import org.junit.Test;

import com.unitedvision.tvkabel.exception.EmptyCodeException;
import com.unitedvision.tvkabel.exception.EmptyIdException;
import com.unitedvision.tvkabel.exception.StatusChangeException;
import com.unitedvision.tvkabel.persistence.entity.Alamat;
import com.unitedvision.tvkabel.persistence.entity.Kecamatan;
import com.unitedvision.tvkabel.persistence.entity.Kelurahan;
import com.unitedvision.tvkabel.persistence.entity.Kontak;
import com.unitedvision.tvkabel.persistence.entity.Kota;
import com.unitedvision.tvkabel.persistence.entity.Pelanggan;
import com.unitedvision.tvkabel.persistence.entity.Pembayaran;
import com.unitedvision.tvkabel.persistence.entity.Pelanggan.Detail;
import com.unitedvision.tvkabel.persistence.entity.Pelanggan.Status;
import com.unitedvision.tvkabel.persistence.entity.Pembayaran.Tagihan;
import com.unitedvision.tvkabel.util.DateUtil;

public class PelangganTest {

	@Test
	public void createWithId() throws EmptyIdException {
		Pelanggan pelanggan = new Pelanggan();
		pelanggan.setId(0);
		
		assertEquals(0, pelanggan.getId());
		assertEquals(true, pelanggan.isNew());
	}

	@Test
	public void removeWorks() throws EmptyIdException, EmptyCodeException, StatusChangeException {
		Kota kota = new Kota(1, "Kota");
		Kecamatan kecamatan = new Kecamatan(1, kota, "Kecamatan");
		Kelurahan kelurahan = new Kelurahan(1, kecamatan, "Kelurahan");
		Alamat alamat = new Alamat(1, "", 0, 0);
		Kontak kontak = new Kontak("1", "2", "3");
		Detail detail = new Detail(DateUtil.getDate("08/20/2012"), 1, 40000L, 0);
		Pelanggan pelanggan = new Pelanggan(1, null, "1", "Pelanggan", "Drummer", kelurahan, alamat, kontak, detail, Status.AKTIF);
		
		assertEquals(Status.AKTIF, pelanggan.getStatus());
		assertEquals(1, pelanggan.getId());
		assertEquals("1", pelanggan.getKode());
		
		pelanggan.remove();

		assertEquals(Status.REMOVED, pelanggan.getStatus());
		assertEquals(1, pelanggan.getId());
		assertEquals("REM1", pelanggan.getKode());
	}

	@Test(expected = StatusChangeException.class)
	public void removeNewPelanggan() throws StatusChangeException {
		Pelanggan pelanggan = new Pelanggan();
		
		pelanggan.remove();
	}
	
	@Test
	public void countTunggakanDefault() throws EmptyIdException, EmptyCodeException {
		Kota kota = new Kota(1, "Kota");
		Kecamatan kecamatan = new Kecamatan(1, kota, "Kecamatan");
		Kelurahan kelurahan = new Kelurahan(1, kecamatan, "Kelurahan");
		Alamat alamat = new Alamat(1, "", 0, 0);
		Kontak kontak = new Kontak("1", "2", "3");
		Detail detail = new Detail(DateUtil.getDate("01/01/2014"), 1, 40000L, 0);
		Pelanggan pelanggan = new Pelanggan(1, null, "1", "Pelanggan", "Drummer", kelurahan, alamat, kontak, detail, Status.AKTIF);
		
		assertEquals(Status.AKTIF, pelanggan.getStatus());
		assertEquals(1, pelanggan.getId());
		assertEquals("1", pelanggan.getKode());
		
		pelanggan.countTunggakan();
		
		assertEquals(12, pelanggan.getTunggakan());
	}
	
	@Test
	public void countTunggakanWithPembayaran() throws EmptyIdException, EmptyCodeException {
		Kota kota = new Kota(1, "Kota");
		Kecamatan kecamatan = new Kecamatan(1, kota, "Kecamatan");
		Kelurahan kelurahan = new Kelurahan(1, kecamatan, "Kelurahan");
		Alamat alamat = new Alamat(1, "", 0, 0);
		Kontak kontak = new Kontak("1", "2", "3");
		Detail detail = new Detail(DateUtil.getDate("01/01/2014"), 1, 40000L, 0);
		Pelanggan pelanggan = new Pelanggan(1, null, "1", "Pelanggan", "Drummer", kelurahan, alamat, kontak, detail, Status.AKTIF);
		
		assertEquals(Status.AKTIF, pelanggan.getStatus());
		assertEquals(1, pelanggan.getId());
		assertEquals("1", pelanggan.getKode());
		
		Pembayaran pembayaranTerakhir = new Pembayaran();
		pembayaranTerakhir.setTagihan(new Tagihan(2014, Month.AUGUST));
		
		pelanggan.countTunggakan(pembayaranTerakhir);
		
		assertEquals(5, pelanggan.getTunggakan());
	}
	
	@Test
	public void countTunggakanWithTagihan() throws EmptyIdException, EmptyCodeException {
		Kota kota = new Kota(1, "Kota");
		Kecamatan kecamatan = new Kecamatan(1, kota, "Kecamatan");
		Kelurahan kelurahan = new Kelurahan(1, kecamatan, "Kelurahan");
		Alamat alamat = new Alamat(1, "", 0, 0);
		Kontak kontak = new Kontak("1", "2", "3");
		Detail detail = new Detail(DateUtil.getDate("01/01/2014"), 1, 40000L, 0);
		Pelanggan pelanggan = new Pelanggan(1, null, "1", "Pelanggan", "Drummer", kelurahan, alamat, kontak, detail, Status.AKTIF);
		
		assertEquals(Status.AKTIF, pelanggan.getStatus());
		assertEquals(1, pelanggan.getId());
		assertEquals("1", pelanggan.getKode());
		
		Tagihan tagihan = new Tagihan(2014, Month.AUGUST);
		
		pelanggan.countTunggakan(tagihan);
		
		assertEquals(5, pelanggan.getTunggakan());
	}
}
