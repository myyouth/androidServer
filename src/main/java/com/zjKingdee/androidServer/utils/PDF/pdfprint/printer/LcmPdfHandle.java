package com.zjKingdee.androidServer.utils.PDF.pdfprint.printer;

import java.io.InputStream;

import com.itextpdf.text.Image;


public interface LcmPdfHandle {
	InputStream getTemplateFile(String filestr);
	Image getImage(String imgStr);
}
