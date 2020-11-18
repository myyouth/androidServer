package com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element;

import java.util.HashMap;
import java.util.Map;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.AcroFields.FieldPosition;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.LcmPdfUtil;


public class LcmPdfText extends LcmPdfElement<String, String> {
   public final static String FP_BG_COLOR   = "bgcolor";
   public final static String FP_TEXT_COLOR = "textcolor";
   public final static String FP_TEXT_FONT  = "textfont";
   public final static String FP_TEXT_SIZE  = "textsize";

   protected BaseFont         textfont;
   protected float            textsize;
   protected BaseColor        textcolor;
   protected BaseColor        bgcolor;
   protected boolean          autoSize;

   public LcmPdfText(String key, String obj) {
      this(key, obj, 0);
   }

   public LcmPdfText(String key, String obj, boolean autosize) {
      this(key, obj,null,0,autosize);
   }

   public LcmPdfText(String key, String obj, float size) {
      this(key, obj, null, size);
   }

   public LcmPdfText(String key, String obj, float size,boolean autosize) {
	      this(key, obj, null, size,autosize);
   }
   public LcmPdfText(String key, String obj, BaseFont font, float size) {
      this(key, obj, font, size,false);
   }

   public LcmPdfText(String key, String obj, BaseFont font, float size, boolean autosize) {
      this(key, obj, font, size, null, null);
      this.autoSize = autosize;
   }

   public LcmPdfText(String key, String obj, BaseFont font, float size, BaseColor textcolor, BaseColor bgcolor) {
      super(key, obj);
      this.textsize = size;
      this.textfont = font;
      this.textcolor = textcolor;
      this.bgcolor = bgcolor;
   }

   @Override
   public String getContent(FieldPosition fp, PdfContentByte pdfconbyte) {
	   if(textfont==null){
		   textfont=LcmPdfUtil.SimSuncss;
	   }
	   if(textsize<=0){
		   textsize=12f;
	   }
	   if (isAutoSize()) 
	          textsize = (ColumnText.fitText(new Font(textfont), obj, fp.position, this.textsize, 0))-0.1f;
	   return obj;
   }

   public Map<String, Object> getProperties() {
      Map<String, Object> pro = new HashMap<String, Object>();
      
      if (bgcolor != null) {
         pro.put(FP_BG_COLOR, bgcolor);
      }
      if (textcolor != null) {
         pro.put(FP_TEXT_COLOR, textcolor);
      }
      if (textsize > 0) {
         pro.put(FP_TEXT_SIZE, textsize);
      }
      if (textfont != null) {
         pro.put(FP_TEXT_FONT, textfont);
      }
      return pro;
   }

   public boolean isAutoSize() {
      return autoSize;
   }

   public void setAutoSize(boolean autoSize) {
      this.autoSize = autoSize;
   }

}
