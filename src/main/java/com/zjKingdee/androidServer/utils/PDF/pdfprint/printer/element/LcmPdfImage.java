package com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element;

import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.AcroFields.FieldPosition;
import com.itextpdf.text.pdf.PdfContentByte;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.LcmPdfHandle;



public class LcmPdfImage extends LcmPdfElement<String,Image> {
	 private float  width;
	 private float  height;
	 private float  positionx=-1;
	 private float  positiony=-1;
	 private float  rotation;
	 private int    align=1;//0=靠左 1=居中   //2=靠右（右实现）
	 private int    model=SCALE_IMG;
	 private LcmPdfHandle handle;
	 private Image img;
	 private boolean definexy=false;
	public LcmPdfImage(String key, String obj) {
		this(key, obj,null,1);
	}
	public LcmPdfImage(String key, String obj,int align) {
		this(key, obj,null,align);
	}
	public LcmPdfImage(String key,String obj,LcmPdfHandle handle){
		this(key, obj,handle,1);
	}
	public LcmPdfImage(String key,String obj,LcmPdfHandle handle,int align){
		super(key, obj);
		this.align=align;
		if(handle!=null)
			this.handle=handle;
		try{
			if(this.handle!=null){
				img=handle.getImage(obj);
			}else{
				img=Image.getInstance(obj);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	@Override
	public Image getContent(FieldPosition fp, PdfContentByte pdfconbyte) {
		if(img==null){
			return null;
		}
		calculateSize(fp);
		calculatePosition(fp);
		img.scaleAbsolute(width, height);
		img.setInitialRotation(rotation);
		img.setAbsolutePosition(positionx, positiony);
		return img;
	}
	private void calculateSize(FieldPosition fp){
		if (fp == null) {
			if (width == 0.0f) {
				width = img.getWidth();
			}
			if (height == 0.0f) {
				height = img.getHeight();
			}
		}else{
			float[] scale = scale(img, fp.position.getWidth(),fp.position.getHeight());
			width = scale[0];
			height = scale[1];
		}
	}
	private void calculatePosition(FieldPosition fp){
		if (SCALE_IMG == model) {
			if(!this.definexy){
				float wc = (fp.position.getWidth() - width) / 2;
				float hc = (fp.position.getHeight() - height) / 2;
				calculateAlign(fp,wc,hc);
			}
		} else if (SHEAR_IMG == model) {

		}
	}

	private void calculateAlign(FieldPosition fp,float wc,float hc) {
		switch (align) {
		case 0:
			this.positionx = fp.position.getLeft();
			this.positiony = fp.position.getTop() - height;
			break;
		case 1:
			this.positionx = fp.position.getLeft() + wc;
			this.positiony = fp.position.getTop() - height - hc;
			break;
		case 2:
			break;
		}
	}
	private float[] scale(Image img,float width,float height){
		float wRatio = width / img.getWidth();
		float hRatio = height / img.getHeight();
		float ratio = wRatio <= hRatio ? wRatio : hRatio;
		if (ratio < 1) {
			width = img.getWidth() * ratio;
			height = img.getHeight() * ratio;
		}else{
			width = img.getWidth();
			height = img.getHeight();
		}
		return new float[]{width,height};
	}
	
	public void setXY(float x,float y){
		this.definexy=true;
		this.positionx=x;
		this.positiony=y;
	}
	public void setSize(float w,float h){
		this.width=w;
		this.height=h;
	}
	
	public final static int lNORMAL_IMG=0;
	public final static int SCALE_IMG=1;
	public final static int SHEAR_IMG=2;
}
