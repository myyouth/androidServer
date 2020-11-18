package com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element;

import java.io.ByteArrayOutputStream;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.AcroFields.FieldPosition;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;

public class LcmPdfPhrase extends LcmPdfElement<Phrase, ByteArrayOutputStream> {
   public LcmPdfPhrase(String key, Phrase obj) {
      super(key, obj);
   }

   @Override
   public ByteArrayOutputStream getContent(FieldPosition fp, PdfContentByte pdfconbyte) {
      if (fp == null || pdfconbyte == null)
         return null;
      ColumnText ct = new ColumnText(pdfconbyte);
      ct.setSimpleColumn(obj, fp.position.getLeft(), fp.position.getBottom(), fp.position.getRight(), fp.position.getTop(), 11, Element.ALIGN_LEFT);
      try {
         ct.go();
      } catch (DocumentException e) {
         e.printStackTrace();
      }
      return null;
   }
}
