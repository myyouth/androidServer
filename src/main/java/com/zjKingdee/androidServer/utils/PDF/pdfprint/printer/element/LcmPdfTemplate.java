package com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.AcroFields.FieldPosition;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.LcmPdfHandle;


public class LcmPdfTemplate extends LcmPdfElement<String, PdfReader> {
	private String key;
	private PdfReader reader=null;
	private List<LcmPdfElement> valuesList=new ArrayList<LcmPdfElement>();
	private LcmPdfHandle handle;
	public LcmPdfTemplate(String obj){
		this(obj, null);
	}
	public LcmPdfTemplate(InputStream is){
		super(null, null);
		if(is!=null){
			try {
				reader=new PdfReader(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public LcmPdfTemplate(String key,String obj,LcmPdfHandle handle){
		this(obj,handle);
		this.key=key;
	}
	public LcmPdfTemplate(String obj,LcmPdfHandle handle) {
		super(null, obj);
		if(handle!=null)
		this.handle=handle;
		
		InputStream is=null;
		if(this.handle!=null){
			is=handle.getTemplateFile(obj);
		}else{
			is=LcmPdfTemplate.class.getResourceAsStream(obj);
			System.out.println("is:"+is);
			if(is==null)
				try {
					is= new FileInputStream(obj);
					System.out.println("is:"+is);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
		}
		if(is!=null){
			try {
				reader=new PdfReader(is);
				System.out.println("reader:"+reader);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void add(LcmPdfElement ele){
		if(ele!=null)
			valuesList.add(ele);
	}
	public List<LcmPdfElement> getValueList(){
		return this.valuesList;
	}

	public Rectangle getRectangle(String key){
		if(reader==null){
			return null;
		}
		AcroFields fields=reader.getAcroFields();
		List<FieldPosition> fps = fields.getFieldPositions(key);
		if(fps==null||fps.size()==0){
			return null;
		}
		FieldPosition fp=fps.get(0);
		return fp.position;
	}
	@Override
	public PdfReader getContent(FieldPosition fp,PdfContentByte pdfconbyte) {
		return reader;
	}
	public LcmPdfTemplate copyTempl(String key){
		LcmPdfTemplate ret=copyTempl();
		ret.setKey(key);
		return ret;
	}
	public LcmPdfTemplate copyTempl(){
		LcmPdfTemplate ret=new LcmPdfTemplate(obj);
		ret.handle=this.handle;
		return ret;
	}
	public String getKey(){
		return key;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LcmPdfTemplate other = (LcmPdfTemplate) obj;
		if (this.obj == null) {
			if (other.obj != null)
				return false;
		} else if (!this.obj.equals(other.obj))
			return false;
		return true;
	}
}
