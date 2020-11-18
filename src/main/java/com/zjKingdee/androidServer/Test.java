package com.zjKingdee.androidServer;

import java.io.File;

import com.zjKingdee.androidServer.utils.BarcodeUtil;
import com.zjKingdee.androidServer.utils.PrintUtil;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.LcmPdfPrinter;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.LcmPdfUtil;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element.LcmPdfImage;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element.LcmPdfTemplate;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element.LcmPdfText;

public class Test {
	public static void main(String[] args) throws Exception {
		LcmPdfTemplate template = new LcmPdfTemplate(System.getProperty("user.dir")+"\\src\\main\\java\\com\\zjKingdee\\androidServer\\utils\\PDF\\barcode.pdf");
//		File file = new File(TEMP_PATH);
//		System.out.println("filePath:"+file.getPath());
		template.add(new LcmPdfText("产品编码", "1.01.001"));
		template.add(new LcmPdfText("产品名称", "镇江"));
		template.add(new LcmPdfText("自编码", "镇江"));
		template.add(new LcmPdfText("供应商", "镇江金蝶"));
		template.add(new LcmPdfText("数量", "1"));
		File barcodeFile=BarcodeUtil.generateQRCodeImage("ZJFC00000014", 800, 800);
		template.add(new LcmPdfImage("物料条码", "MyQRCode.png"));
		barcodeFile=BarcodeUtil.generateQRCodeImage("JHKSN", 500, 500);
		template.add(new LcmPdfImage("源单编码", "MyQRCode.png"));
		LcmPdfPrinter printer = new LcmPdfPrinter(LcmPdfUtil.SimSuncss,5.0f);
		printer.addTempl(template);
		File pdfFile=printer.print(System.getProperty("user.dir")+"\\barcode.pdf");
		PrintUtil.JPGPrint(pdfFile,"192.168.191.221\\HP LaserJet 1020");
		if(pdfFile.exists()) {
			pdfFile.delete();
		}
		if(barcodeFile.exists()) {
			barcodeFile.delete();
		}
	}
}
