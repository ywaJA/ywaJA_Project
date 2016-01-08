package com.common.utils;

/**
 * sunrise, Inc. All rights reserved. Copyright (C): 2015
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import android.util.Log;

/**
 * @Description: class 数据压缩和解压工具类 Revision History: DATE AUTHOR VERSION
 *               DESCRIPTION
 * 
 * @author 黄钢
 * @createDate 2015-1-28
 * @since iccs V01.00.000
 */

public class DataCompress
{
	private static final int BUFFER_LENGTH = 400;

	// 压缩字节最小长度，小于这个长度的字节数组不适合压缩，压缩完会更大
	private static final int BYTE_MIN_LENGTH = 50;

	// 字节数组是否压缩标志位
	private static final byte FLAG_UTF_STRING_UNCOMPRESSED_BYTEARRAY = 0;// 不需要压缩的标志
	private static final byte FLAG_UTF_STRING_COMPRESSED_BYTEARRAY = 1;// 需要压缩的标志

	/**
	 * 数据压缩
	 * 
	 * @param is
	 * @param os
	 * @throws Exception
	 */
	private static void compress(InputStream is, OutputStream os) throws Exception
	{

		GZIPOutputStream gos = new GZIPOutputStream(os);

		int count;
		byte data[] = new byte[BUFFER_LENGTH];
		while ((count = is.read(data, 0, BUFFER_LENGTH)) != -1)
		{
			gos.write(data, 0, count);
		}

		gos.finish();

		gos.flush();
		gos.close();
	}

	/**
	 * 数据解压缩
	 * 
	 * @param is
	 * @param os
	 * @throws Exception
	 */
	private static void decompress(InputStream is, OutputStream os) throws Exception
	{

		GZIPInputStream gis = new GZIPInputStream(is);

		int count;
		byte data[] = new byte[BUFFER_LENGTH];
		while ((count = gis.read(data, 0, BUFFER_LENGTH)) != -1)
		{
			os.write(data, 0, count);
		}

		gis.close();
	}

	/**
	 * 数据压缩
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] byteCompress(byte[] data) throws Exception
	{
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		// 压缩
		compress(bais, baos);

		byte[] output = baos.toByteArray();

		baos.flush();
		baos.close();

		bais.close();

		return output;
	}

	/**
	 * 数据解压缩
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] byteDecompress(byte[] data) throws Exception
	{
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		// 解压缩

		decompress(bais, baos);

		data = baos.toByteArray();

		baos.flush();
		baos.close();

		bais.close();

		return data;
	}

	/**
	 * 数据压缩
	 * 
	 * @param jsonStr
	 * @return
	 * @throws Exception
	 */
	public static Object[] CompressData(String jsonStr) throws Exception
	{
		Object[] reObject = null;
		if (jsonStr != null && !"".equals(jsonStr))
		{
			reObject = new Object[2];
			byte[] resultByte = jsonStr.getBytes();
			// 如果要返回的结果字节数组小于50位，不将压缩
			if (resultByte.length < BYTE_MIN_LENGTH)
			{
				byte flagByte = FLAG_UTF_STRING_UNCOMPRESSED_BYTEARRAY;
				reObject[0] = flagByte;
				reObject[1] = resultByte;
			} else
			{
				byte flagByte = FLAG_UTF_STRING_COMPRESSED_BYTEARRAY;
				reObject[0] = flagByte;
				reObject[1] = byteCompress(resultByte);
			}

		}
		return reObject;
	}

	public static byte[] DecompressData(byte[] receivedByte)
	{
		byte[] resultByte = null;
		try
		{
			byte flag = -1;
			byte[] compressedByte = null;
			if (receivedByte != null && receivedByte.length > 0)
			{
				flag = receivedByte[0];
				compressedByte = new byte[receivedByte.length - 1];
				for (int i = 0; i < compressedByte.length; i++)
				{
					compressedByte[i] = receivedByte[i + 1];
				}
			}

			// 判断接收到的字节数组是否是压缩过的
			if (flag == FLAG_UTF_STRING_UNCOMPRESSED_BYTEARRAY)
			{
				resultByte = compressedByte;

			} else if (flag == FLAG_UTF_STRING_COMPRESSED_BYTEARRAY)
			{
				resultByte = byteDecompress(compressedByte);
			}
		} catch (Exception e)
		{
			Log.e("数据解压出错：", e.getMessage() + "  ");
		}
		return resultByte;
	}

}
