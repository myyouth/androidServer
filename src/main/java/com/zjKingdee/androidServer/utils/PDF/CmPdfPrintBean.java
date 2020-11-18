package com.zjKingdee.androidServer.utils.PDF;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class CmPdfPrintBean{
	private float startPostionY;//起始打印位置Y
	private float startPostionX;//起始打印位置X
	private PdfReader reader=null;
	private ByteArrayOutputStream os=null;
	private PdfStamper stamper=null;
	private PdfContentByte contentByte=null;
	private Rectangle pageRectangle=null; 
	public CmPdfPrintBean(InputStream inputStream,float startPostionY,float startPostionX) throws Exception{
		reader=new PdfReader(inputStream);
		os=new ByteArrayOutputStream();
		stamper =new PdfStamper(reader,os);
		contentByte=stamper.getOverContent(1);
		pageRectangle=reader.getPageSizeWithRotation(1);
		this.startPostionY=startPostionY;
		this.startPostionX=startPostionX;
	}
	public float getStartPostionY() { 
		return startPostionY;
	}
	public void setStartPostionY(float startPostionY) {
		this.startPostionY = startPostionY;
	}
	public float getStartPostionX() {
		return startPostionX;
	}
	public void setStartPostionX(float startPostionX) {
		this.startPostionX = startPostionX;
	}
	public float getPageHeight(){
		return pageRectangle.getHeight();
	}
	public PdfReader getReader(){
		return reader;
	}
	public void setReader(PdfReader reader){
		this.reader = reader;
	}
	public ByteArrayOutputStream getOs(){
		return os;
	}
	public void setOs(ByteArrayOutputStream os){
		this.os = os;
	}
	public PdfStamper getStamper(){
		return stamper;
	}
	public void setStamper(PdfStamper stamper){
		this.stamper = stamper;
	}
	public PdfContentByte getContentByte(){
		return contentByte;
	}
	public void setContentByte(PdfContentByte contentByte){
		this.contentByte = contentByte;
	}
	public Rectangle getPageRectangle(){
		return pageRectangle;
	}
	public void setPageRectangle(Rectangle pageRectangle){
		this.pageRectangle = pageRectangle;
	}
	/**
	 * 获得流
	 * */
	public InputStream getInputStream(){
		return new ByteArrayInputStream(os.toByteArray());
	}
	/**
	 *关闭流 
	 * */
	public void closeStream() throws Exception{
		if(stamper!=null){
			stamper.close();
		}
		if(os!=null){
			os.close();
		}
	}
}
