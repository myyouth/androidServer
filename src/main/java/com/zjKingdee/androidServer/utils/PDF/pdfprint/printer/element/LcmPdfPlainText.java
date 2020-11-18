package com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.AcroFields.FieldPosition;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfStamper;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.LcmPdfUtil;


public class LcmPdfPlainText extends LcmPdfElement<String, String> {
   public final static int ALIGN_LEFT   = 0;
   public final static int ALIGN_CENTER = 1;
   public final static int ALIGN_RIGHT  = 2;

   private BaseFont        textfont;
   private float           textsize;
   private int             align;
   private boolean         autoSize;

   public LcmPdfPlainText(String key, String value, int align) {
      this(key, value, null, 0.0f, align, false);
   }

   public LcmPdfPlainText(String key, String value, boolean autoSize) {
      this(key, value, null, 0.0f, ALIGN_LEFT, autoSize);
   }
   
   public LcmPdfPlainText(String key, String value, int align, boolean autoSize) {
      this(key, value, null, 0.0f, align, autoSize);
   }

   public LcmPdfPlainText(String key, String value, BaseFont font, float size, boolean autoSize) {
      this(key, value, font, size, ALIGN_LEFT, false);
   }

   public LcmPdfPlainText(String key, String value, BaseFont font, float size, int align) {
      this(key, value, font, size, align, false);
   }

   public LcmPdfPlainText(String key, String value, BaseFont font, float size, int align, boolean autoSize) {
      super(key, value);
      this.textsize = size;
      this.textfont = font;

      this.align = align;
      this.autoSize = autoSize;
   }

   public int getAlign() {
      return align;
   }

   public void setAlign(int align) {
      this.align = align;
   }

   public boolean isAutoSize() {
      return autoSize;
   }

   public void setAutoSize(boolean autoSize) {
      this.autoSize = autoSize;
   }

   public void print(PdfStamper stamper, FieldPosition fp, BaseFont defaultFont, float defaultFontSize) {

   }

   public String getContent(FieldPosition fp, PdfContentByte pdfconbyte) {
      if (key == null || key.length() == 0 || obj == null || obj.length() == 0 || fp == null || pdfconbyte == null)
         return null;

      if (this.textfont == null)
         this.textfont = LcmPdfUtil.SimSuncss;
      if (this.textsize <= 0.0f)
         this.textsize = 12f;
      
      Font font = new Font(this.textfont);
      if (isAutoSize()) 
         this.textsize = ColumnText.fitText(font, obj, fp.position, this.textsize, 0);
      
      ColumnText ct = new ColumnText(pdfconbyte);
      ct.setSimpleColumn(fp.position.getLeft(), fp.position.getTop(), fp.position.getRight(), fp.position.getBottom(), fp.position.getHeight() * 0.8f, align);
      ct.addText(new Phrase(obj, font));
      try {
         ct.go();
      } catch (DocumentException e) {
      }
      
      return obj;
   }
}
