package com.unitedvision.tvkabel.core.document.pdf.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;
import com.unitedvision.tvkabel.core.document.pdf.HariPdfView;
import com.unitedvision.tvkabel.core.service.PegawaiService;
import com.unitedvision.tvkabel.core.service.RekapService;
import com.unitedvision.tvkabel.exception.EmptyIdException;
import com.unitedvision.tvkabel.exception.EntityNotExistException;
import com.unitedvision.tvkabel.persistence.SpringDataJpaConfig;
import com.unitedvision.tvkabel.persistence.entity.Pegawai;
import com.unitedvision.tvkabel.persistence.entity.Pelanggan;
import com.unitedvision.tvkabel.util.DateUtil;

public class HariTest extends HariPdfView {
	private static HariTest kartu = new HariTest();
	private static ApplicationContext appContext = new AnnotationConfigApplicationContext(SpringDataJpaConfig.class);
	private static RekapService rekapService = appContext.getBean(RekapService.class);
	private static PegawaiService pegawaiService = appContext.getBean(PegawaiService.class);

	public static void main(String[] args) throws EmptyIdException {
        Document document = kartu.newDocument();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("E:\\test.pdf"));

            document.open();
            Pegawai pegawai = pegawaiService.getOne(14);
            String tanggalAwal = "12-1-2014";
            String tanggalAkhir = "1-31-2015";
            Date hariAwal = DateUtil.getDate(tanggalAwal);
            Date hariAkhir = DateUtil.getDate(tanggalAkhir);
			List<Pelanggan> list = (List<Pelanggan>)rekapService.rekapHarian(pegawai, hariAwal, hariAkhir);

            Map<String, Object> model = new HashMap<>();
            model.put("rekap", list);
            model.put("pegawai", pegawai.getNama());
            model.put("tanggalAwal", tanggalAwal);
            model.put("tanggalAkhir", tanggalAkhir);
            
            kartu.create(model, document);
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
			e.printStackTrace(); 
		} catch (EntityNotExistException e) {
			e.printStackTrace();
		}
        
        System.out.println("DONE...");
	}
	
	@Override
	protected Document newDocument() {
		return super.newDocument();
	}
}
