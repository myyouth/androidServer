package com.zjKingdee.androidServer.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.util.Assert;

/**
 * 解压缩
 * @author Yinqi				
 * @date 2020年9月25日
 */
public class CompressionUtil {
	private static final int BUFFER_SIZE = 4 * 1024;  
	  
    public static byte[] compress(byte[] data, Level level) throws IOException {  
  
        Assert.notNull(data);  
        Assert.notNull(level);  
  
        Deflater deflater = new Deflater();  
        // set compression level  
        deflater.setLevel(level.getLevel());  
        deflater.setInput(data);  
  
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);  
  
        deflater.finish();  
        byte[] buffer = new byte[BUFFER_SIZE];  
        while (!deflater.finished()) {  
            int count = deflater.deflate(buffer); // returns the generated  
                                                  // code... index  
            outputStream.write(buffer, 0, count);  
        }  
        byte[] output = outputStream.toByteArray();  
        outputStream.close();  
        return output;  
    }  
  
    /** 
     * decompress data 
     *  
     * @author lichengwu 
     * @created 2013-02-07 
     *  
     * @param data 
     * @return 
     * @throws IOException 
     * @throws DataFormatException 
     */  
    public static byte[] decompress(byte[] data) throws IOException, DataFormatException {  
  
        Assert.notNull(data);  
  
        Inflater inflater = new Inflater();  
        inflater.setInput(data);  
  
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);  
        byte[] buffer = new byte[BUFFER_SIZE];  
        while (!inflater.finished()) {  
            int count = inflater.inflate(buffer);  
            outputStream.write(buffer, 0, count);  
        }  
        byte[] output = outputStream.toByteArray();  
        outputStream.close();  
        return output;  
    }  
  
    /** 
     * Compression level 
     */  
    public static enum Level {  
  
        /** 
         * Compression level for no compression. 
         */  
        NO_COMPRESSION(0),  
  
        /** 
         * Compression level for fastest compression. 
         */  
        BEST_SPEED(1),  
  
        /** 
         * Compression level for best compression. 
         */  
        BEST_COMPRESSION(9),  
  
        /** 
         * Default compression level. 
         */  
        DEFAULT_COMPRESSION(-1);  
  
        private int level;  
  
        Level(  
  
        int level) {  
            this.level = level;  
        }  
        public int getLevel() {  
            return level;  
        }  
    }    
}
