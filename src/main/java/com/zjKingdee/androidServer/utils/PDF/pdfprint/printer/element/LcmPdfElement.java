package com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element;

import com.itextpdf.text.pdf.AcroFields.FieldPosition;
import com.itextpdf.text.pdf.PdfContentByte;

public abstract class LcmPdfElement<T,K> {
	protected String key;
	protected T obj;
	public LcmPdfElement(String key,T obj) {
		this.key = key;
		this.obj=obj;
	}
	public String getKey(){
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	public abstract K getContent(FieldPosition fp,PdfContentByte pdfconbyte); 
	
}
