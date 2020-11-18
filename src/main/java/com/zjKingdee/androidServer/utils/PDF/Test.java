package com.zjKingdee.androidServer.utils.PDF;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.util.IOUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.AcroFields.FieldPosition;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSmartCopy;
import com.itextpdf.text.pdf.PdfStamper;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.LcmPdfPrinter;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.LcmPdfUtil;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element.LcmPdfImage;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element.LcmPdfTemplate;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element.LcmPdfText;



public class Test {
	public static final String TEMP_PATH="C:\\Users\\Administrator\\Desktop\\test1.pdf";
	public static final String TEMP_PATH_2="C:\\Users\\Administrator\\Desktop\\通用文件模板-签署D.pdf";
	public static final String ECNTEMP_PATH="C:\\Users\\Administrator\\Desktop\\ECN_Template.pdf";
	public static final String NULLTEMP_PATH="C:\\Users\\Administrator\\Desktop\\Null_Template.pdf";
	public static final String PRINT_INFOR="C:\\Users\\Administrator\\Desktop\\test-D.pdf";
	public static float startPostionY=500;
	public static float startPostionX=50.5f;
	public static void main(String[] args) throws Exception{
		//test();
		//test2();
		//test3();
		test4();
		System.out.println("》》》》》》》》》》完成》》》》》》》》》》》");
	}
	public static void test4() throws Exception{
		//IBA信息
		LcmPdfTemplate template = new LcmPdfTemplate(TEMP_PATH);
//		File file = new File(TEMP_PATH);
//		System.out.println("filePath:"+file.getPath());
		template.add(new LcmPdfText("编码", "S"));
		template.add(new LcmPdfText("供应商", "1234567890"));
		System.out.println(System.getProperty("user.dir"));
		template.add(new LcmPdfImage("二维码", "MyQRCode.png"));
		LcmPdfPrinter printer = new LcmPdfPrinter(LcmPdfUtil.SimSuncss,10.0f);
		printer.addTempl(template);
		printer.print(PRINT_INFOR);
		
	}
	public static void test3() throws FileNotFoundException, Exception{
		List<CmPdfPrintBean> beans=new ArrayList<CmPdfPrintBean>();
		CmPdfPrintBean bean=new CmPdfPrintBean(new FileInputStream(ECNTEMP_PATH),674, 49.5f);
		beans.add(bean);
		//附件信息
		//bean= createAttchmentTable(bean,beans);
		//签审信息
		bean= createSignTable(bean,beans);
		bean.closeStream();
		//整合输出
		ByteArrayOutputStream outputStream=print(beans);
		IOUtils.copy(new ByteArrayInputStream(outputStream.toByteArray()),new FileOutputStream(PRINT_INFOR));
	}
	
	public static void test() throws Exception{
		//IBA信息
		LcmPdfTemplate template = new LcmPdfTemplate(TEMP_PATH);
		File file = new File(TEMP_PATH);
		System.out.println("filePath:"+file.getPath());
		template.add(new LcmPdfText("阶段", "S"));
		template.add(new LcmPdfText("文件编号", "1234567890"));
		template.add(new LcmPdfText("密级", "秘密★10年"));
		template.add(new LcmPdfText("版本", "A"));
		template.add(new LcmPdfText("共几页", "999"));
		template.add(new LcmPdfText("全称", "产品型号产品名称"));
		template.add(new LcmPdfText("文件名称", "文件名称"));
		template.add(new LcmPdfText("编制年月", "2017年10月"));
		LcmPdfTemplate template2 = new LcmPdfTemplate(TEMP_PATH_2);
		//template2.add(new LcmPdfText("编制", "王震1", LcmPdfUtil.SimSuncss, 10.0f, true));
		template2.add(new LcmPdfImage("编制", "D:\\printclient\\img\\杨报.jpg"));
		template2.add(new LcmPdfText("编制时间", "2017-10-16", LcmPdfUtil.SimSuncss, 10.0f, true));
		//template2.add(new LcmPdfText("校对", "王震校对", LcmPdfUtil.SimSuncss, 10.0f, true));
		template2.add(new LcmPdfImage("校对", "D:\\printclient\\img\\金辉.jpg"));
		template2.add(new LcmPdfText("校对时间", "2017-10-17", LcmPdfUtil.SimSuncss, 10.0f, true));
		//template2.add(new LcmPdfText("审核", "王震审核1", LcmPdfUtil.SimSuncss, 10.0f, true));
		template2.add(new LcmPdfImage("审核", "D:\\printclient\\img\\刘旭培.jpg"));
		template2.add(new LcmPdfText("审核时间", "2017-10-18", LcmPdfUtil.SimSuncss, 10.0f, true));
		template2.add(new LcmPdfImage("审定", "D:\\printclient\\img\\刘旭培.jpg"));
		template2.add(new LcmPdfText("审定时间", "2017-10-18", LcmPdfUtil.SimSuncss, 10.0f, true));
		template2.add(new LcmPdfImage("标审", "D:\\printclient\\img\\李世明.jpg"));
		template2.add(new LcmPdfText("标审时间", "2017-10-19", LcmPdfUtil.SimSuncss, 10.0f, true));
		template2.add(new LcmPdfImage("会签", "D:\\printclient\\img\\尉卫东.jpg"));
		template2.add(new LcmPdfText("会签时间", "2017-10-20", LcmPdfUtil.SimSuncss, 10.0f, true));
		template2.add(new LcmPdfImage("批准", "D:\\printclient\\img\\赵宏韬.jpg"));
		template2.add(new LcmPdfText("批准时间", "2017-10-21", LcmPdfUtil.SimSuncss, 10.0f, true));
		template2.add(new LcmPdfImage("顾客代表", "D:\\printclient\\img\\赵宏韬.jpg"));
		template2.add(new LcmPdfText("顾客代表时间", "2017-10-21", LcmPdfUtil.SimSuncss, 10.0f, true));
		LcmPdfPrinter printer = new LcmPdfPrinter(LcmPdfUtil.SimSuncss,10.0f);
		printer.addTempl(template);
		printer.addTempl(template2);
		printer.print(PRINT_INFOR);
		
	}
	public static void test2() throws Exception{
		List<CmPdfPrintBean> beans=new ArrayList<CmPdfPrintBean>();
		CmPdfPrintBean bean=new CmPdfPrintBean(new FileInputStream(ECNTEMP_PATH),500, 50.5f);
		beans.add(bean);
		//IBA信息
		appendFileds(bean.getStamper(), "number", "RD-0001", 8);
		appendFileds(bean.getStamper(), "名称", "名称-001", 8);
		appendFileds(bean.getStamper(), "category", "category", 8);
		appendFileds(bean.getStamper(), "effectiveCondition", "effectiveCondition", 8);
		appendFileds(bean.getStamper(), "clientConfirmation", "clientConfirmation", 8);
		//附件信息
		//bean= createAttchmentTable(bean,beans);
		//签审信息
		//bean= createSignTable(bean,beans);
		bean.closeStream();
		//整合输出
		ByteArrayOutputStream outputStream=print(beans);
		IOUtils.copy(new ByteArrayInputStream(outputStream.toByteArray()),new FileOutputStream(PRINT_INFOR));
	}
	/**
	 *将多页PDF合并到一个文件 
	 * */
	public static ByteArrayOutputStream print(List<CmPdfPrintBean> beans) throws Exception {
		ByteArrayOutputStream targetos = new ByteArrayOutputStream();
		Document doc = new Document();
		PdfCopy pdfCopy = new PdfSmartCopy(doc, targetos);
		doc.open();
		PdfImportedPage impPage = null;
		for (int i=0;i<beans.size();i++) {
			PdfReader reader=new PdfReader(beans.get(i).getInputStream());
			int page=reader.getNumberOfPages();
			for (int j=1;j<=page;j++) {
				impPage = pdfCopy.getImportedPage(reader,j);
				pdfCopy.addPage(impPage);
			}
		}
		doc.close();
		return targetos;
	}
	private static CmPdfPrintBean createSignTable(CmPdfPrintBean bean,List<CmPdfPrintBean> beans) throws Exception{
		BaseFont BFCHINESE     = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);//设置中文字体
		Font font      = new Font(BFCHINESE,8,Font.NORMAL);//表头字体样式 
		PdfPTable table = new PdfPTable(6);
		table.setTotalWidth(487);
		PdfPCell titleCell=new PdfPCell(new Paragraph("签审过程",font));
		titleCell.setColspan(6);
		setCellStyle(titleCell,1);
		table.addCell(titleCell);
		if(bean.getStartPostionY()-getTableHeight(table)<=20){
			table.writeSelectedRows(0,-1,bean.getStartPostionX(),bean.getStartPostionY(),bean.getContentByte());
			bean.getContentByte().closePathStroke();
			bean.closeStream();
			bean=new CmPdfPrintBean(new FileInputStream(NULLTEMP_PATH),bean.getPageHeight()-45f,50.5f);
			beans.add(bean);
			table=new PdfPTable(6);
			table.setTotalWidth(487);
		}
		PdfPCell cell1 = new PdfPCell(new Paragraph("状况",font));
		PdfPCell cell2 = new PdfPCell(new Paragraph("活动名称",font));
		PdfPCell cell3 = new PdfPCell(new Paragraph("执行人",font));
		PdfPCell cell4 = new PdfPCell(new Paragraph("角色",font));
		PdfPCell cell5 = new PdfPCell(new Paragraph("签审状态",font));
		PdfPCell cell6 = new PdfPCell(new Paragraph("完成时间",font));
		setCellStyle(cell1,1);
		setCellStyle(cell2,0);
		setCellStyle(cell3,0);
		setCellStyle(cell4,0);
		setCellStyle(cell5,0);
		setCellStyle(cell6,0);

		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		table.addCell(cell4);
		table.addCell(cell5);
		table.addCell(cell6);

		for (int i = 0; i<100; i++){
			if(bean.getStartPostionY()-getTableHeight(table)<=20){
				table.writeSelectedRows(0,-1,bean.getStartPostionX(),bean.getStartPostionY(),bean.getContentByte());
				bean.getContentByte().closePathStroke();
				bean.closeStream();
				bean=new CmPdfPrintBean(new FileInputStream(NULLTEMP_PATH),bean.getPageHeight()-45f,50.5f);
				beans.add(bean);
				table=new PdfPTable(6);
				table.setTotalWidth(487);
			}
			PdfPCell cell11 = new PdfPCell(new Paragraph("√",FontBase.fontRed));
			PdfPCell cell22 = new PdfPCell(new Paragraph("活动名称"+i,font));
			PdfPCell cell33 = new PdfPCell(new Paragraph("执行人"+i,font));
			PdfPCell cell44 = new PdfPCell(new Paragraph("角色"+i,font));
			PdfPCell cell55 = new PdfPCell(new Paragraph("[通过]",font));
			PdfPCell cell66 = new PdfPCell(new Paragraph("2017/08/28",font));
			setCellStyle(cell11,1);
			setCellStyle(cell22,0);
			setCellStyle(cell33,0);
			setCellStyle(cell44,0);
			setCellStyle(cell55,0);
			setCellStyle(cell66,0);
			table.addCell(cell11);
			table.addCell(cell22);
			table.addCell(cell33);
			table.addCell(cell44);
			table.addCell(cell55);
			table.addCell(cell66);
		}
		table.writeSelectedRows(0,-1,bean.getStartPostionX(),bean.getStartPostionY(),bean.getContentByte());
		bean.getContentByte().closePathStroke();
		return bean;
	}
	private static CmPdfPrintBean createAttchmentTable(CmPdfPrintBean bean, List<CmPdfPrintBean> beans) throws Exception{
		BaseFont BFCHINESE     = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);//设置中文字体
		Font font      = new Font(BFCHINESE,8,Font.NORMAL);//表头字体样式 
		PdfPTable table = new PdfPTable(6);
		table.setTotalWidth(487);
		//第一行
		PdfPCell titleCell=new PdfPCell(new Paragraph("相关附件",font));
		titleCell.setColspan(6);
		setCellStyle(titleCell,1);
		table.addCell(titleCell);
		//第二行
		PdfPCell cell1 = new PdfPCell(new Paragraph("活动名称",font));
		PdfPCell cell2 = new PdfPCell(new Paragraph("上传者",font));
		PdfPCell cell3 = new PdfPCell(new Paragraph("文件名称",font));
		cell3.setColspan(4);
		setCellStyle(cell1,1);
		setCellStyle(cell2,0);
		setCellStyle(cell3,0);
		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		//具体信息
		for (int i= 0;i<100;i++){
			if(bean.getStartPostionY()-getTableHeight(table)<=20){
				table.writeSelectedRows(0,-1,bean.getStartPostionX(),bean.getStartPostionY(),bean.getContentByte());
				bean.getContentByte().closePathStroke();
				bean.closeStream();
				bean=new CmPdfPrintBean(new FileInputStream(NULLTEMP_PATH),bean.getPageHeight()-45f,50.5f);
				beans.add(bean);
				table=new PdfPTable(6);
				table.setTotalWidth(487);
			}
			PdfPCell cell11 = new PdfPCell(new Paragraph("活动名称"+i,font));
			PdfPCell cell22 = new PdfPCell(new Paragraph("上传者"+i,font));
			PdfPCell cell33 = new PdfPCell(new Paragraph("文件名称"+i,font));
			cell33.setColspan(4);
			setCellStyle(cell11,1);
			setCellStyle(cell22,0);
			setCellStyle(cell33,0);
			table.addCell(cell11);
			table.addCell(cell22);
			table.addCell(cell33);
		}
		table.writeSelectedRows(0,-1,bean.getStartPostionX(),bean.getStartPostionY(),bean.getContentByte());
		float newStartPostionY=bean.getStartPostionY()-table.getTotalHeight();
		if(newStartPostionY<=20f){
			bean.getContentByte().closePathStroke();
			bean.closeStream();
			bean=new CmPdfPrintBean(new FileInputStream(NULLTEMP_PATH),bean.getPageHeight()-45f,50.5f);
			beans.add(bean);
		}else
			bean.setStartPostionY(newStartPostionY);
		return bean;
	}
	//	public static void test() throws Exception{
	//		PdfReader reader = new PdfReader(new FileInputStream(TEMP_PATH));
	//		Rectangle pageSize = reader.getPageSizeWithRotation(1);
	//		float pageHeight=pageSize.getHeight();//页面高度
	//		float pageMaxHeight=pageHeight-startPostionY;//表格最大高度
	//		System.out.println("pageHeight>>>>>>>>>>"+pageHeight);
	//		System.out.println("pageMaxHeight>>>>>>>>>"+pageMaxHeight);
	//		ByteOutputStream os=new ByteOutputStream();
	//		PdfStamper stamper =new PdfStamper(reader, os);
	//		appendFileds(stamper, "number", "RD-0001", 8);
	//		appendFileds(stamper, "name", "名称-001", 8);
	//		appendFileds(stamper, "category", "category", 8);
	//		appendFileds(stamper, "effectiveCondition", "effectiveCondition", 8);
	//		appendFileds(stamper, "clientConfirmation", "clientConfirmation", 8);
	//		PdfContentByte contentByte= stamper.getOverContent(1);
	//		PdfPTable attchmentTable= createAttchmentTable(contentByte,pageMaxHeight);
	//		float signInforTableY=startPostionY-getTableHeight(attchmentTable);
	//		attchmentTable.writeSelectedRows(0,-1,startPostionX,startPostionY,contentByte);
	//		PdfPTable signInforTable= createSignInforTable();
	//		signInforTable.writeSelectedRows(0,-1,startPostionX,signInforTableY,contentByte);
	//		contentByte.closePathStroke();
	//		stamper.close();
	//		os.close();
	//		IOUtils.copy(new ByteArrayInputStream(os.toByteArray()),new FileOutputStream(PRINT_INFOR));
	//	}
	/**
	 * 附件列表
	 * */
	private static PdfPTable createAttchmentTable(PdfContentByte contentByte, float pageMaxHeight) throws Exception{
		BaseFont BFCHINESE     = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);//设置中文字体
		Font font      = new Font(BFCHINESE,8,Font.NORMAL);//表头字体样式 
		PdfPTable table = new PdfPTable(6);
		table.setTotalWidth(487);
		PdfPCell titleCell=new PdfPCell(new Paragraph("相关附件",font));
		titleCell.setColspan(6);
		setCellStyle(titleCell,1);
		table.addCell(titleCell);

		//
		PdfPCell cell1 = new PdfPCell(new Paragraph("活动名称",font));
		PdfPCell cell2 = new PdfPCell(new Paragraph("上传者",font));
		PdfPCell cell3 = new PdfPCell(new Paragraph("文件名称",font));
		cell3.setColspan(4);
		setCellStyle(cell1,1);
		setCellStyle(cell2,0);
		setCellStyle(cell3,0);
		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		for (int i = 0;i<100; i++){
			if(pageMaxHeight-getTableHeight(table)<=25){
				table.writeSelectedRows(0,-1,startPostionX,startPostionY,contentByte);
			}
			PdfPCell cell11 = new PdfPCell(new Paragraph("活动名称"+i,font));
			PdfPCell cell22 = new PdfPCell(new Paragraph("上传者"+i,font));
			PdfPCell cell33 = new PdfPCell(new Paragraph("文件名称"+i,font));
			cell33.setColspan(4);
			setCellStyle(cell11,1);
			setCellStyle(cell22,0);
			setCellStyle(cell33,0);
			table.addCell(cell11);
			table.addCell(cell22);
			table.addCell(cell33);
		}
		return table;
	}
	private static void appendFileds(PdfStamper stamper,String key,String value,float textSize) throws Exception{
		AcroFields fields = stamper.getAcroFields();
		FieldPosition fp = null;
		if (key != null) {
			List<FieldPosition> fps = fields.getFieldPositions(key);
			if (fps != null && fps.size() > 0)
				fp = fps.get(0);
		}
		if (fp == null) {
			return;
		}
		textSize=ColumnText.fitText(FontBase.fontChinese,value,fp.position,textSize, 0)-0.1f;
		if (value == null){
			return;
		}
		fields.setFieldProperty(key, "textfont",FontBase.bfChinese, new int[]{0});
		fields.setFieldProperty(key, "textsize",textSize, new int[]{0});
		fields.setField(key, value);
	}
	public static  float getTableHeight(PdfPTable table) throws IOException, DocumentException{
		PdfReader reader = new PdfReader(ECNTEMP_PATH);
		OutputStream os =new ByteArrayOutputStream();
		PdfStamper stamper=new PdfStamper(reader, os);
		PdfContentByte contentByte=stamper.getOverContent(1);
		table.writeSelectedRows(0,-1,30,50,contentByte);
		stamper.close();
		reader.close();
		os.close();
		return table.getTotalHeight();
	}
	public static PdfPTable createSignInforTable() throws DocumentException, IOException{
		BaseFont BFCHINESE     = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);//设置中文字体
		Font font      = new Font(BFCHINESE,8,Font.NORMAL);//表头字体样式 
		PdfPTable table = new PdfPTable(6);
		table.setTotalWidth(487);
		PdfPCell titleCell=new PdfPCell(new Paragraph("签审过程",font));
		titleCell.setColspan(6);
		setCellStyle(titleCell,1);
		table.addCell(titleCell);

		PdfPCell cell1 = new PdfPCell(new Paragraph("状况",font));
		PdfPCell cell2 = new PdfPCell(new Paragraph("活动名称",font));
		PdfPCell cell3 = new PdfPCell(new Paragraph("执行人",font));
		PdfPCell cell4 = new PdfPCell(new Paragraph("角色",font));
		PdfPCell cell5 = new PdfPCell(new Paragraph("签审状态",font));
		PdfPCell cell6 = new PdfPCell(new Paragraph("完成时间",font));
		setCellStyle(cell1,1);
		setCellStyle(cell2,0);
		setCellStyle(cell3,0);
		setCellStyle(cell4,0);
		setCellStyle(cell5,0);
		setCellStyle(cell6,0);

		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		table.addCell(cell4);
		table.addCell(cell5);
		table.addCell(cell6);

		for (int i = 0; i<3; i++){
			PdfPCell cell11 = new PdfPCell(new Paragraph("√",font));
			PdfPCell cell22 = new PdfPCell(new Paragraph("活动名称"+i,font));
			PdfPCell cell33 = new PdfPCell(new Paragraph("执行人"+i,font));
			PdfPCell cell44 = new PdfPCell(new Paragraph("角色"+i,font));
			PdfPCell cell55 = new PdfPCell(new Paragraph("[通过]",font));
			PdfPCell cell66 = new PdfPCell(new Paragraph("2017/08/28",font));
			setCellStyle(cell11,1);
			setCellStyle(cell22,0);
			setCellStyle(cell33,0);
			setCellStyle(cell44,0);
			setCellStyle(cell55,0);
			setCellStyle(cell66,0);
			table.addCell(cell11);
			table.addCell(cell22);
			table.addCell(cell33);
			table.addCell(cell44);
			table.addCell(cell55);
			table.addCell(cell66);
		}
		return table;
	}
	public static void setCellStyle(PdfPCell cell, int i){
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorderWidth((float)0.7);
		cell.setBorderWidthLeft(i);
	}
}
