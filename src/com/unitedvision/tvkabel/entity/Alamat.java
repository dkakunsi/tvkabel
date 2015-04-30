package com.unitedvision.tvkabel.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 * Address value object.
 * 
 * @author Deddy Christoper Kakunsi
 *
 */
@Embeddable
public final class Alamat {
	/**
	 * lingkungan.
	 */
	private int lingkungan;

	/**
	 * Detail of address.
	 */
	private String detailAlamat;
	
	private Location lokasi;
	
	/**
	 * Latitude position on map
	 */
	private float latitude;

	/**
	 * Longitude position on map
	 */
	private float longitude;

	/**
	 * Create instance.
	 */
	public Alamat() {
		super();
	}

	/**
	 * Return lingkungan.
	 * @return lingkungan
	 */
	@Column(name = "lingkungan")
	public int getLingkungan() {
		return lingkungan;
	}

	/**
	 * Set lingkungan.
	 * @param lingkungan
	 */
	public void setLingkungan(int lingkungan) {
		this.lingkungan = lingkungan;
	}

	/**
	 * Return detailAlamat.
	 * @return
	 */
	@Column(name = "detail_alamat")
	public String getDetailAlamat() {
		return detailAlamat;
	}

	/**
	 * Set detailAlamat.
	 * @param detailAlamat
	 */
	public void setDetailAlamat(String detailAlamat) {
		this.detailAlamat = detailAlamat;
	}

	@Embedded
	public Location getLokasi() {
		return lokasi;
	}

	public void setLokasi(Location lokasi) {
		this.lokasi = lokasi;
	}

	@Override
	public String toString() {
		return "Alamat [lingkungan=" + lingkungan + ", detailAlamat="
				+ detailAlamat + ", latitude=" + latitude + ", longitude="
				+ longitude + "]";
	}

}
