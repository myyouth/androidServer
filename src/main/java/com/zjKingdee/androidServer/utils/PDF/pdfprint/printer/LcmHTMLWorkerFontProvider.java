package com.zjKingdee.androidServer.utils.PDF.pdfprint.printer;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;

public class LcmHTMLWorkerFontProvider extends XMLWorkerFontProvider {
    private BaseFont textFont;
    private float    fontSize;
    public LcmHTMLWorkerFontProvider(BaseFont textFont, float fontSize) {
       this.textFont = textFont;
       this.fontSize = fontSize;
    }
    @Override
    public Font getFont(String fontname, String encoding, boolean embedded, float size, int style, BaseColor color) {
       try {
          if (textFont == null) {
             textFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
          }
          if (fontSize > 0) {
             size = fontSize;
          }
       } catch (Exception e) {
          e.printStackTrace();
       }
       Font font = new Font(textFont, size, style, color);
       return font;
    }

 }
