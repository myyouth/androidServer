package com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.html.simpleparser.StyleSheet;
import com.itextpdf.text.pdf.AcroFields.FieldPosition;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.tool.xml.ElementList;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.LcmHTMLWorkerFontProvider;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.LcmPdfUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;


public class LcmPdfHtml extends LcmPdfElement<String, ByteArrayOutputStream> {
	
	private BaseFont font=LcmPdfUtil.SimSuncss;
	private float fontSize=12f;
	private List<Element> list=new ElementList();
	public LcmPdfHtml(String key,String obj){
		this(key, obj,null,0f);
	}
	public LcmPdfHtml(String key, String obj,BaseFont font,float fontSize){
		super(key, obj);
		if(font!=null)
		this.font=font;
		if(fontSize>0)
		this.fontSize=fontSize;
		initHtmlStream();
	}
	private void initHtmlStream(){
		StringReader sr=new StringReader(obj);
		this.list=parseByHTMLWorker(sr,this.font,this.fontSize);
	}
	private List<Element> parseByHTMLWorker(StringReader sr,BaseFont font,float fontSize){
		List<Element> ret=new ElementList();
		StyleSheet st = new StyleSheet();     
	    HashMap<String,Object> mm=new HashMap<String,Object>();
	    mm.put("font_factory",new LcmHTMLWorkerFontProvider(font,fontSize));
	    try {
			ret=HTMLWorker.parseToList(sr, st,mm);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return ret;
	}
	@Override
	public ByteArrayOutputStream getContent(FieldPosition fp,PdfContentByte pdfconbyte) {
		ColumnText ct = new ColumnText(pdfconbyte);
	  	Phrase phrase=new Phrase();
	  	if(list!=null)
	  	for (Element ele : list) {
	  		phrase.add(ele);
	  	}
	  	ct.setSimpleColumn(phrase, fp.position.getLeft(), fp.position.getBottom(),fp.position.getRight(), fp.position.getTop(),  15, Element.ALIGN_LEFT);
	  	try {
			ct.go();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	  	return null;
	}

	public float getFontSize() {
		return fontSize;
	}
}
