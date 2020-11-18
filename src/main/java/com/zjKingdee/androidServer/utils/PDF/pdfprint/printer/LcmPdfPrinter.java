/**
 * <br>Created on 2013-8-16
 * @author Y.Huang - 黄勇
 */
package com.zjKingdee.androidServer.utils.PDF.pdfprint.printer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.AcroFields.FieldPosition;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSmartCopy;
import com.itextpdf.text.pdf.PdfStamper;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element.LcmPdfElement;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element.LcmPdfHtml;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element.LcmPdfImage;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element.LcmPdfPhrase;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element.LcmPdfPlainText;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element.LcmPdfPositionText;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element.LcmPdfSingleHtml;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element.LcmPdfTemplate;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element.LcmPdfText;


/**
 * 组件名称：PDF打印输出组件<br>
 * 创建时间：2013-8-16 版本:1.0<br>
 * 更新时间：2013-8-23 版本:1.5<br>
 * 更新时间：2013-11-10 版本:2.0<br>
 * 
 * @author Y.Huang - 黄勇 <h1>功能支持：</h1>
 *         <ul>
 *         <li>在PDF模板指定位置插入文本（包含多行文本显示）</li>
 *         <li>在PDF模板指定位置插入图片，图宽或高超出范围则图片自动按比例缩小到合适大小</li>
 *         <li>在PDF模板指定位置插入html代码，html代码将解析为显示文本及图片---此功能主要为实现 图文混排，暂时不支持复杂样式。</li>
 *         </ul>
 *         * <h1>本类一依赖包列表:</h1>
 *         <ul>
 *         <li>itext-asian-5.4.3.jar</li>
 *         <li>itextpdf-5.4.3.jar</li>
 *         <li>xmlworker-5.4.3.jar</li>
 *         </ul>
 */
public class LcmPdfPrinter {
	private final static String  PAGECOUNTKEY = "page";
	private final static String  PAGEKEY      = "page_of";

	private BaseFont             font;
	public float                 fontSize;

	private List<LcmPdfTemplate> templateList = new ArrayList<LcmPdfTemplate>(); ;
	private int                  pagecount;

	public LcmPdfPrinter() {
		this(null, 0);
	}

	public LcmPdfPrinter(BaseFont font, float fontSize) {
		if (font == null) {
			try {
				this.font = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			this.font = font;
		}
		if (fontSize == 0.0f) {
			this.fontSize = 12f;
		} else {
			this.fontSize = fontSize;
		}
	}

	public int getPageCount() {
		return this.pagecount;
	}

	public void addTempl(LcmPdfTemplate templ) {
		templateList.add(templ);
	}

	public void removeTempl(LcmPdfTemplate templ) {
		templateList.remove(templ);
	}

	public void print(OutputStream os) throws Exception {
		ByteArrayOutputStream bos = print();
		bos.writeTo(os);
		bos.close();
		os.close();
	}

	/**
	 * <h1>将输出流写入指定的路径的文件中</h1> 返回 File 生成的新的PDF文件
	 */
	public File print(String targetFileName) throws Exception {
		File ret = new File(targetFileName);
		FileOutputStream fos = new FileOutputStream(ret);
		ByteArrayOutputStream bos = print();
		bos.writeTo(fos);
		bos.close();
		fos.close();
		return ret;
	}

	/**
	 * <h1>合并PDF页面</h1>
	 * <p>
	 * 为了不生成多余的文件，直接将合并后的文件写到一个输出流返回
	 * </p>
	 * 
	 * 返回 ByteArrayOutputStream 是一个已合并后的输出流
	 */
	public ByteArrayOutputStream print() throws Exception {
		ByteArrayOutputStream[] pdfStream = write();
//		this.pagecount = pdfStream.length;
		ByteArrayOutputStream targetos = new ByteArrayOutputStream();
		Document doc = new Document();
		PdfCopy pdfCopy = new PdfSmartCopy(doc, targetos);
		doc.open();
		PdfImportedPage impPage = null;
		//
		for (int i=0;i<templateList.size();i++) {
			PdfReader reader=templateList.get(i).getContent(null, null);
			int page=reader.getNumberOfPages();
			this.pagecount+=page;
			for (int j=1;j<=page ;j++) {
				impPage = pdfCopy.getImportedPage(new PdfReader(pdfStream[i].toByteArray()),j);
				pdfCopy.addPage(impPage);
			}
			
		}
//		for (int i = 0; i < pdfStream.length; i++) {
//			impPage = pdfCopy.getImportedPage(new PdfReader(pdfStream[i].toByteArray()), 1);
//			pdfCopy.addPage(impPage);
//		}
		doc.close();
		return targetos;
	}

	private ByteArrayOutputStream[] write() throws Exception {
		int templateCount = 0;//总页数
		for (LcmPdfTemplate templete : templateList) {
			PdfReader  reader=templete.getContent(null, null);
			templateCount+=reader.getNumberOfPages();
		}
		ByteArrayOutputStream[] tempStream = new ByteArrayOutputStream[templateList.size()];

		if (templateCount == 0) {
			return tempStream;
		}

		Iterator<LcmPdfTemplate> it = templateList.iterator();
		int index = 0;
		while (it.hasNext()) {
			LcmPdfTemplate template = it.next();

			tempStream[index] = new ByteArrayOutputStream();

			PdfReader reader = template.getContent(null, null);
			PdfStamper stamp = new PdfStamper(reader, tempStream[index]);
			AcroFields fields = stamp.getAcroFields();

			fields.setFieldProperty(PAGEKEY, LcmPdfText.FP_TEXT_FONT, font, new int[]{0});
			fields.setFieldProperty(PAGEKEY, LcmPdfText.FP_TEXT_SIZE, fontSize, new int[]{0});
			fields.setField(PAGEKEY, String.valueOf(index +1));

			fields.setFieldProperty(PAGECOUNTKEY, LcmPdfText.FP_TEXT_FONT, font, new int[]{0});
			fields.setFieldProperty(PAGECOUNTKEY, LcmPdfText.FP_TEXT_SIZE, fontSize, new int[]{0});
			fields.setField(PAGECOUNTKEY, String.valueOf(templateCount));

			index+=reader.getNumberOfPages();
			List<LcmPdfElement> list = template.getValueList();
			if (list != null)
				for (LcmPdfElement ele : list) {
					if (ele instanceof LcmPdfText) {
						printString(stamp, (LcmPdfText) ele);
					} else if (ele instanceof LcmPdfImage) {
						printImage(stamp, (LcmPdfImage) ele);
					} else if (ele instanceof LcmPdfHtml) {
						printHtml(stamp, (LcmPdfHtml) ele);
					} else if (ele instanceof LcmPdfSingleHtml) {
						printHtml2(stamp, (LcmPdfSingleHtml) ele);
					} else if (ele instanceof LcmPdfPlainText) {
						printPlainText(stamp, (LcmPdfPlainText) ele);
					}else if(ele instanceof LcmPdfPositionText){
						printPositionText(stamp,(LcmPdfPositionText)ele);
					}else if(ele instanceof LcmPdfPhrase){
						printPhrase(stamp,(LcmPdfPhrase)ele);
					}
				}

			stamp.setFormFlattening(true);
			stamp.close();
			reader.close();
		}

		return tempStream;
	}
	private void printPositionText(PdfStamper stamp,LcmPdfPositionText text){
		FieldPosition fp = null;
		text.getContent(fp, stamp.getOverContent(1));
	}

	private void printPlainText(PdfStamper stamp, LcmPdfPlainText pdfText) {
		AcroFields fields = stamp.getAcroFields();
		String key = pdfText.getKey();
		FieldPosition fp = null;
		if (key != null) {
			List<FieldPosition> fps = fields.getFieldPositions(key);
			if (fps != null && fps.size() > 0)
				fp = fps.get(0);
		}
		if (fp == null) {
			return;
		}
		String content = pdfText.getContent(fp, stamp.getOverContent(fp.page));
	}

	private void printString(PdfStamper stamp, LcmPdfText pdfstr) throws Exception {
		AcroFields fields = stamp.getAcroFields();
		String key = pdfstr.getKey();
		FieldPosition fp = null;
		if (key != null) {
			List<FieldPosition> fps = fields.getFieldPositions(key);
			if (fps != null && fps.size() > 0){
				fp = fps.get(0);
			}
		}
		if (fp == null) {
			return;
		}
		String content = pdfstr.getContent(fp, null);
		if (content == null) {
			return;
		}
		Map<String, Object> prop = pdfstr.getProperties();
		if (!prop.containsKey(LcmPdfText.FP_TEXT_FONT)) {
			prop.put(LcmPdfText.FP_TEXT_FONT, font);
		}
		if (!prop.containsKey(LcmPdfText.FP_TEXT_SIZE)) {
			prop.put(LcmPdfText.FP_TEXT_SIZE, fontSize);
		}
		for (Map.Entry<String, Object> entry : prop.entrySet()) {
			if(LcmPdfText.FP_TEXT_FONT.equals(entry.getKey())){
				fields.setFieldProperty(key, entry.getKey(), entry.getValue(), new int[]{0});
			}
		}
		fields.setField(key, content);
	}

	private void printImage(PdfStamper stamp, LcmPdfImage pdfimg) throws Exception {
		PdfContentByte pdfconbyte = stamp.getOverContent(1);
		AcroFields fields = stamp.getAcroFields();
		String key = pdfimg.getKey();
		FieldPosition fp = null;
		if (key != null) {
			List<FieldPosition> fps = fields.getFieldPositions(key);
			if (fps != null && fps.size() > 0){
				for(int i=0;i<fps.size();i++){
					fp = fps.get(i);
					pdfconbyte = stamp.getOverContent(fp.page);
					Image image = pdfimg.getContent(fp, pdfconbyte);
					if (image != null)
						pdfconbyte.addImage(image);
				}
				
			}
		}
		if (fp == null) {
			return;
		}
		fields.setFieldProperty(key, "textfont",font, new int[]{0});
		fields.setField(key, "");
	}

	private void printHtml(PdfStamper stamp, LcmPdfHtml pdfhtml) throws Exception {
		PdfContentByte pdfconbyte = stamp.getOverContent(1);
		AcroFields fields = stamp.getAcroFields();
		String key = pdfhtml.getKey();
		FieldPosition fp = null;
		if (key != null) {
			List<FieldPosition> fps = fields.getFieldPositions(key);
			if (fps != null && fps.size() > 0)
				fp = fps.get(0);
		}
		ByteArrayOutputStream bos = pdfhtml.getContent(fp, pdfconbyte);
		if (bos == null) {
			return;
		}
		PdfImportedPage pdfImportedPage = stamp.getImportedPage(new PdfReader(bos.toByteArray()), 1);
		float x = fp.position.getLeft();
		float y = fp.position.getTop() - pdfImportedPage.getHeight();
		// *，宽度缩放，旋转，右倒,,x,y
		pdfconbyte.addTemplate(pdfImportedPage, 1f, 0f, 0f, 1f, x, y);
	}

	private void printHtml2(PdfStamper stamp, LcmPdfSingleHtml pdfhtml) throws Exception {
		PdfContentByte pdfconbyte = stamp.getOverContent(1);
		AcroFields fields = stamp.getAcroFields();
		String key = pdfhtml.getKey();
		FieldPosition fp = null;
		if (key != null) {
			List<FieldPosition> fps = fields.getFieldPositions(key);
			if (fps != null && fps.size() > 0)
				fp = fps.get(0);
		}
		pdfhtml.getContent(fp, pdfconbyte);
	}

	private void printPhrase(PdfStamper stamp, LcmPdfPhrase phrase) throws Exception {
		PdfContentByte pdfconbyte = stamp.getOverContent(1);
		AcroFields fields = stamp.getAcroFields();
		String key = phrase.getKey();
		FieldPosition fp = null;
		if (key != null) {
			List<FieldPosition> fps = fields.getFieldPositions(key);
			if (fps != null && fps.size() > 0)
				fp = fps.get(0);
		}
		phrase.getContent(fp, pdfconbyte);
	}

	public void setFont(BaseFont font) {
		if (font != null)
			this.font = font;
	}

	public void setFontSize(float fontSize) {
		if (fontSize > 0)
			this.fontSize = fontSize;
	}
}
