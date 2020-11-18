package com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.AcroFields.FieldPosition;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;

public class LcmPdfPositionText extends LcmPdfElement<String, String> {
	public final static int ALIGN_LEFT =Element.ALIGN_LEFT;
	public final static int ALIGN_CENTER = Element.ALIGN_CENTER;
	public final static int ALIGN_RIGHT = Element.ALIGN_RIGHT;
	   
	protected float positionx;
	protected float positiony;

	protected BaseFont textfont;
	protected float textsize;
	protected BaseColor textcolor;
	protected int           align;
	protected float rotation;
	public LcmPdfPositionText(String obj) {
		this(obj,0,0);
	}
	public LcmPdfPositionText(String obj,float x,float y){
		super(null, obj);
		this.positionx=x;
		this.positiony=y;
	}
	
	@Override
	public String getContent(FieldPosition fp, PdfContentByte pdfconbyte) {
		if(textfont==null){
			try {
				textfont=BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(textsize<=0){
			textsize=12;
		}
		 pdfconbyte.beginText();
		 pdfconbyte.setFontAndSize(textfont, textsize);
		 pdfconbyte.showTextAligned(align,obj,positionx,positiony,rotation);
		 if(textcolor!=null){
		 }
		 pdfconbyte.endText();
		return null;
	}
	public float getPositionx() {
		return positionx;
	}
	public void setPositionx(float positionx) {
		this.positionx = positionx;
	}
	public float getPositiony() {
		return positiony;
	}
	public void setPositiony(float positiony) {
		this.positiony = positiony;
	}
	public BaseFont getTextfont() {
		return textfont;
	}
	public void setTextfont(BaseFont textfont) {
		this.textfont = textfont;
	}
	public float getTextsize() {
		return textsize;
	}
	public void setTextsize(float textsize) {
		this.textsize = textsize;
	}
	public int getAlign() {
		return align;
	}
	public void setAlign(int align) {
		this.align = align;
	}
	public float getRotation() {
		return rotation;
	}
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
}
