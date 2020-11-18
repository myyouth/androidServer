package com.zjKingdee.androidServer.utils.PDF.pdfprint.printer;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.html.simpleparser.StyleSheet;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.tool.xml.ElementList;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element.LcmPdfSingleHtml;


public class LcmPdfUtil {
   /**
    *黑体字
    */
   public static BaseFont SimHei    = null;
   /**
    *宋体字
    */
   public static BaseFont SimSuncss = null;
   /**
    *新宋体字
    */
   public static BaseFont NSimSun   = null;
   static {
      try {
         SimHei = BaseFont.createFont("com/zjKingdee/androidServer/utils/pdf/pdfprint/font/SIMHEI.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
         SimSuncss = BaseFont.createFont("com/zjKingdee/androidServer/utils/pdf/pdfprint/font/SIMSUN.TTC,0", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
         NSimSun = BaseFont.createFont("com/zjKingdee/androidServer/utils/pdf/pdfprint/font/SIMSUN.TTC,1", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public static List<String> splitStr(String value, BaseFont font, float fontSize, float maxWidth) {
      List<String> ret = new ArrayList<String>();
      if (value == null)
         return ret;

      String[] strs=value.split("\n");
      for(String strV:strs){
    	  int strVWidth = (int) font.getWidthPoint(strV, fontSize);
    	  if (strVWidth<=maxWidth){
    		  ret.add(strV);
    		  continue;
    	  }
    	  
	      char[] chars = strV.toCharArray();
	      
	      int iMaxWidth = (int) maxWidth; 
	      
	      int start = 0, count = 0;
	      while (start + count < chars.length) {
	         count++;
	         String str = String.valueOf(chars, start, count);
	         int tempWidth = (int) font.getWidthPoint(str, fontSize);
	         if ( iMaxWidth - tempWidth <= 6 ) {
	            ret.add(String.valueOf(chars, start, count - 1));
	            start += count - 1;
	            count = 1;
	         }
	      }
	      if (start < chars.length)
	         ret.add(String.valueOf(chars, start, chars.length - start));
	   }
      return ret;
   }

   public static List<String> checkAndsplitStr(String str, BaseFont font, float defaultSize, float minSize, float maxWidth) {
      List<String> ret = new ArrayList<String>();
      float tempWidth = font.getWidthPoint(str, defaultSize);
      if (maxWidth > tempWidth) {
         ret.add(str);
         return ret;
      }
      tempWidth = font.getWidthPoint(str, minSize);
      if (maxWidth > tempWidth) {
         ret.add(str);
         return ret;
      }

      return splitStr(str, font, defaultSize, maxWidth);
   }

   public static int max(int... size) {
      if (size == null || size.length == 0)
         return 0;

      int ret = size[0];
      for (int i = 1; i < size.length; i++)
         if (ret < size[i])
            ret = size[i];

      return ret;
   }

   public static List<LcmPdfSingleHtml> splitHtml(String htmlStr, BaseFont font, float fontSize, float lineWidth, float lineHeight) {
      List<LcmPdfSingleHtml> ret = new ArrayList<LcmPdfSingleHtml>();
      if (htmlStr == null) {
         return ret;
      }
      List<List<Element>> slist = new ArrayList<List<Element>>();
      StringReader sr = new StringReader(htmlStr);
      List<Element> list = parseByHTMLWorker(sr, font, fontSize);
      List<Element> sublist = new ArrayList<Element>();
      slist.add(sublist);
      float lw = 0f;
      for (Element ele : list) {
         List<Chunk> chunkList = ele.getChunks();
         for (Chunk chunk : chunkList) {
            float chunkWidth = 0f;
            if (chunk.getImage() != null) {
               Image img = LcmPdfUtil.zoomImg(lineWidth, lineHeight * (0.8f), chunk.getImage());
               chunk = new Chunk(img, 0, -5);
               chunkWidth = chunk.getWidthPoint();
            } else {
               String content = chunk.getContent().trim();
               if (!chunk.isEmpty() && content.length() == 0) { // 换行
                  sublist = new ArrayList<Element>();
                  lw = 0.0f;
                  slist.add(sublist);
                  continue;
               }
               chunkWidth = font.getWidthPoint(content, fontSize);
               if (lw + chunkWidth > lineWidth) {
                  String str = LcmPdfUtil.getStrByLength(content, font, fontSize, lineWidth - lw);
                  sublist.add(new Chunk(str, new Font(font, fontSize)));
                  lw = 0;

                  String hstr = content.substring(str.length());
                  List<String> strlist = splitStr(hstr, font, fontSize, lineWidth);
                  Iterator<String> it = strlist.iterator();
                  while (it.hasNext()) {
                     String str1 = it.next();
                     List<Element> strcl = new ArrayList<Element>();
                     strcl.add(new Chunk(str1, new Font(font, fontSize)));
                     slist.add(strcl);
                     if (!it.hasNext()) {
                        sublist = strcl;
                        lw += font.getWidthPoint(str1, fontSize);
                     }
                  }
               } else {
                  sublist.add(new Chunk(content, new Font(font, fontSize)));
                  lw += chunkWidth;
               }
               continue;
            }
            if ((lw + chunkWidth) > lineWidth) {
               sublist = new ArrayList<Element>();
               slist.add(sublist);
               lw = chunkWidth;
            } else {
               lw += chunkWidth;
            }
            sublist.add(chunk);
         }
      }

      for (List<Element> clist : slist) {
         if (clist == null) {
            continue;
         }
         ret.add(new LcmPdfSingleHtml(null, clist));
      }
      return ret;
   }

   private static List<Element> parseByHTMLWorker(StringReader sr, BaseFont font, float fontSize) {
      List<Element> ret = new ElementList();
      StyleSheet st = new StyleSheet();
      HashMap<String, Object> mm = new HashMap<String, Object>();
      mm.put("font_factory", new LcmHTMLWorkerFontProvider(font, fontSize));
      try {
         ret = HTMLWorker.parseToList(sr, st, mm);
      } catch (Exception e) {
         e.printStackTrace();
         ret = new ElementList();
      }
      return ret;
   }

   public static Image zoomImg(float maxWidth, float maxHeight, Image img) {
      float imgWidth = img.getWidth();
      float imgHeight = img.getHeight();
      float wRatio = maxWidth / imgWidth;
      float hRatio = maxHeight / imgHeight;
      float ratio = wRatio <= hRatio ? wRatio : hRatio;
      if (ratio < 1) {
         imgWidth = imgWidth * ratio;
         imgHeight = imgHeight * ratio;
         img.scaleAbsolute(imgWidth, imgHeight);
      }
      return img;
   }

   public static String getStrByLength(String content, BaseFont font, float fontSize, float width) {
      char[] chars = content.toCharArray();
      float tempWidth = 0f;
      StringBuilder str = new StringBuilder();
      for (int i = 0; i < chars.length; i++) {
         float tw = font.getWidthPoint(chars[i], fontSize);
         if (tempWidth + tw > width) {
            break;
         }
         tempWidth += tw;
         str.append(chars[i]);
      }
      return str.toString();
   }
   public static List<StringBuilder> splitPlainText(String content,BaseFont font,float fontSize, float maxWidth,float maxHeight){
	   if (content == null)
	         return new ArrayList<StringBuilder>();
	   List<StringBuilder> ret=new ArrayList<StringBuilder>();
	  
	   String[] strs=content.split("\n");
	   int line=0;
	   float tempHeight=0f;
	   StringBuilder sb=new StringBuilder();
	   ret.add(sb);
	   for(String str:strs){
		   List<String> strlist=splitStr(str, font, fontSize, maxWidth);
		   for(String s:strlist){
			   line++;
			   tempHeight=line*fontSize;
			   if(tempHeight>maxHeight){
				   line=0;
				   tempHeight=0f;
				   ret.add(sb=new StringBuilder());
			   }
			   sb.append("\n").append(s);
		   }
	   }
	   return ret;
   }
}
