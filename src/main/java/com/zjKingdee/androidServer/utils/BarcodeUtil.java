package com.zjKingdee.androidServer.utils;


import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * 条码/二维码管理工具
 * @author Yinqi				
 * @date 2020年9月1日
 */
public class BarcodeUtil {
	private static final String QR_CODE_IMAGE_PATH = System.getProperty("user.dir")+"\\MyQRCode.png";
	
	public static File generateQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        //配置条码参数
        Map<EncodeHintType,Object> hints = new HashMap<>();
        //设置条码两边空白边距为0，默认为10，如果宽度不是条码自动生成宽度的倍数则MARGIN无效
        hints.put(EncodeHintType.MARGIN, 0);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height,hints);
		Path path = FileSystems.getDefault().getPath(QR_CODE_IMAGE_PATH);
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
		File file=new File(System.getProperty("user.dir")+"\\MyQRCode.png");
		return file;
	}
	
	public static void main(String[] args) {
        try {
            generateQRCodeImage("This is my first QR Code", 5, 5);
        } catch (WriterException e) {
            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
        }
		
	}
}
