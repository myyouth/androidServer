package com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.AcroFields.FieldPosition;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;

public class LcmPdfSingleHtml extends LcmPdfElement<List<Element>, ByteArrayOutputStream> {
   public LcmPdfSingleHtml(String key, List<Element> obj) {
      super(key, obj);
   }

   @Override
   public ByteArrayOutputStream getContent(FieldPosition fp, PdfContentByte pdfconbyte) {
      if (fp == null || pdfconbyte == null)
         return null;
      ColumnText ct = new ColumnText(pdfconbyte);
      Phrase phrase = new Phrase();
      if (obj != null)
         for (Element ele : obj) {
            phrase.add(ele);
         }
      ct.setSimpleColumn(phrase, fp.position.getLeft(), fp.position.getBottom(), fp.position.getRight(), fp.position.getTop(), 11, Element.ALIGN_LEFT);
      try {
         ct.go();
      } catch (DocumentException e) {
         e.printStackTrace();
      }
      return null;
   }

	@Override
	public String toString() {
		StringBuffer sb=new StringBuffer();
		for(Element el:obj){
			sb.append(el.toString());
		}
		return "LcmPdfSingleHtml [toString()=" + sb.toString() + "]";
	}
   
}
