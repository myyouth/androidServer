package com.zjKingdee.androidServer.utils.PDF;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;

public class FontBase {

	public static final float fontSize = 12f;
	public static Font fontChinese = null; //主要是title的信息的字体
	public static Font fontChineseBold = null;
	public static Font fontRed= null;
	public static BaseFont bfChinese = null;
	static {
		try {
			bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
			fontChinese = new Font(bfChinese, fontSize, Font.NORMAL);
			fontChineseBold = new Font(bfChinese, fontSize, Font.BOLD);
			fontRed = new Font(bfChinese, fontSize, Font.NORMAL);
			fontRed.setColor(BaseColor.RED);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
