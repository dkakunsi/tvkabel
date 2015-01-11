package com.unitedvision.tvkabel.domain.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.unitedvision.tvkabel.domain.Pegawai;
import com.unitedvision.tvkabel.domain.Pegawai.Status;
import com.unitedvision.tvkabel.domain.Perusahaan;
import com.unitedvision.tvkabel.exception.EntityNotExistException;

public interface PegawaiRepository extends JpaRepository<Pegawai, Integer> {
	Pegawai findByPerusahaanAndStatusAndKode(Perusahaan perusahaan, Status status, String kode) throws EntityNotExistException;
	Pegawai findByPerusahaanAndStatusAndNama(Perusahaan perusahaan, Status status, String nama) throws EntityNotExistException;
	Pegawai findByKredensi_UsernameAndStatus(String username, Status status) throws EntityNotExistException;

	List<Pegawai> findByPerusahaanAndStatus(Perusahaan perusahaan, Status status);
	List<Pegawai> findByPerusahaanAndStatus(Perusahaan perusahaan, Status status, Pageable page);
	List<Pegawai> findByPerusahaanAndStatusAndKodeContaining(Perusahaan perusahaan, Status status, String kode, Pageable page);
	List<Pegawai> findByPerusahaanAndStatusAndNamaContaining(Perusahaan perusahaan, Status status, String nama, Pageable page);
	
	long countByPerusahaan(Perusahaan perusahaan);
	long countByPerusahaanAndStatus(Perusahaan perusahaan, Status status);
	long countByPerusahaanAndStatusAndKodeContaining(Perusahaan perusahaan, Status status, String kode);
	long countByPerusahaanAndStatusAndNamaContaining(Perusahaan perusahaan, Status status, String nama);
}
