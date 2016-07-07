package com.example.beautydress.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import com.example.beautydress.common.CommonData;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 
 * @类名称: ExternalStorageUtil
 * @类描述: 外部存储工具类
 * @创建人：徐文波
 * @创建时间：2015年12月17日 下午6:43:16
 * @备注：
 * @version V1.0
 */
public class ExternalStorageUtil {

	/**
	 * 保存图片的缓存路径
	 */
	// public static final String CACHE_DIR = Environment
	// .getExternalStorageDirectory() + "/qianfeng/images";

	public static final String CACHE_DIR = Environment
			.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
			.toString();

	/**
	 * 图片格式
	 */
	public static final int FORMAT_JPG = 1;
	public static final int FORMAT_PNG = 2;

	/**
	 * @方法名称:isMounted
	 * @描述: 判断扩展卡是否已挂载
	 * @创建人：徐文波
	 * @创建时间：2015年12月17日 下午8:49:36
	 * @params:
	 * @备注：
	 * @返回类型：boolean
	 */
	public static boolean isMounted() {
		// 获取当前扩展卡的状态
		String state = Environment.getExternalStorageState();
		return state.equals(Environment.MEDIA_MOUNTED);
	}

	/**
	 * @方法名称:saveImg
	 * @描述: 保存图片到扩展卡的缓存目录中
	 * @创建人：徐文波
	 * @创建时间：2015年12月17日 下午8:52:14
	 * @param url
	 *            :图片的URL地址
	 * @param bytes
	 *            :要保存的图片数据
	 * @throws IOException
	 * @备注：
	 * @返回类型：void
	 */
	public static void saveImg(String url, byte[] bytes) throws IOException {
		// 1. 判断当前的扩展是否可用
		if (!isMounted())
			return;

		// 2. 判断当前的缓存目录是否已存在
		File dir = new File(CACHE_DIR);
		if (!dir.exists())
			dir.mkdirs(); // 级联创建目录

		// 3. 将图片的字节数写入到指定的文件中
		FileOutputStream fos = new FileOutputStream(new File(dir,
				getFileName(url)));
		fos.write(bytes);
		fos.close();
	}

	/**
	 * 
	 * @方法名称:saveImg
	 * @描述: 保存图片到扩展卡的缓存目录中
	 * @创建人：徐文波
	 * @创建时间：2015年12月17日 下午8:53:01
	 * @param url
	 *            :图片的URL
	 * @param bitmap
	 *            :要保存的图片
	 * @param format
	 *            :图片格式
	 * @throws IOException
	 * @备注：
	 * @返回类型：void
	 */
	public static void saveImg(String url, Bitmap bitmap, int format)
			throws IOException {
		// 1. 判断当前的扩展是否可用
		if (!isMounted())
			return;

		// 2. 判断当前的缓存目录是否已存在
		File dir = new File(CACHE_DIR);
		if (!dir.exists())
			dir.mkdirs(); // 级联创建目录

		// 3. 保存图片
		FileOutputStream fos = new FileOutputStream(new File(dir,
				getFileName(url)));

		// 针对指定的输出流,生成一个压缩版的图片.
		// 将Bitmap写入到指定的文件输出流中.按照某种格式对图片进行压缩处理.
		// 对于有些png格式的图片,进行压缩,设置quality未必会起作用.
		bitmap.compress(format == FORMAT_JPG ? CompressFormat.JPEG
				: CompressFormat.PNG, 100, fos);

	}

	/**
	 * 
	 * @方法名称:readImg
	 * @描述: 读取在存储卡中存储的缓存图片
	 * @创建人：徐文波
	 * @创建时间：2015年12月17日 下午8:55:07
	 * @param url
	 * @return
	 * @备注：
	 * @返回类型：Bitmap
	 */
	public static Bitmap readImg(String url) {
		if (!isMounted())
			return null;

		File imgFile = new File(CACHE_DIR, getFileName(url));
		if (imgFile.exists()) {

			// 将指定路径的图片文件转成Bitmap对象
			return BitmapFactory.decodeFile(imgFile.getAbsolutePath());
		}

		return null;
	}

	/**
	 * 
	 * @方法名称:getFileName
	 * @描述: 根据图片的网络地址获取文件名
	 * @创建人：徐文波
	 * @创建时间：2015年12月17日 下午8:55:44
	 * @param url
	 * @return
	 * @备注：
	 * @返回类型：String
	 */
	public static String getFileName(String url) {
		return url.substring(url.lastIndexOf("/") + 1);
	}

	/**
	 * 
	 * @方法名称:clearCaches
	 * @描述: 清除扩展卡下缓存目录中所有的资源
	 * @创建人：徐文波
	 * @创建时间：2015年12月17日 下午8:56:06
	 * @备注：
	 * @返回类型：void
	 */
	public static void clearCaches() {
		if (!isMounted())
			return;

		// 获取缓存目录的文件对象
		File cacheDir = new File(CACHE_DIR);
		if (cacheDir.exists()) {
			File[] files = cacheDir.listFiles(); // 列出所有的文件
			for (File f : files) {
				f.delete(); // 删除文件
			}
		}
	}

	/**
	 * 
	 * @方法名称:isSizeAvailable
	 * @描述: 判断当前扩展卡是否有足够的可用空间
	 * @创建人：徐文波
	 * @创建时间：2015年12月17日 下午8:56:42
	 * @return
	 * @备注：
	 * @返回类型：boolean
	 */
	@SuppressLint("NewApi")
	public static boolean isSizeAvailable() {
		if (!isMounted()) {
			return false;
		}

		// 根目录
		File rootFile = Environment.getExternalStorageDirectory();

		long freeSize = 0;

		if (Build.VERSION.SDK_INT >= 18) {
			// StatFs 主要用来描述设备的存储状态 描述指定路径下的总空间 可用空间以及状态等
			StatFs sf = new StatFs(rootFile.getAbsolutePath());
			// 通过获取可用数据块和每个数据块的字节大小来计算可用空间大小
			// freeSize = sf.getBlockCount() * sf.getBlockSize();
			freeSize = sf.getBlockCountLong() * sf.getBlockSizeLong();
		} else {
			// getTotalSpace() 获取file指定空间的大小
			freeSize = rootFile.getTotalSpace();
		}

		// 可用空间大于10M，则表示可用
		if (freeSize > 10 * 1024 * 1024) {
			return true;
		}

		return false;
	}

	/**
	 * 
	 * @方法名称:getTotalSpace
	 * @描述: 获取总空间大小
	 * @创建人：徐文波
	 * @创建时间：2015年12月17日 下午8:57:28
	 * @param file
	 * @return
	 * @备注： 总空间的大小 byte 字节 1byte=8位 bit 比特位 kb 千字节 1kb=1024b MB 兆字节 1MB=1024KB
	 *      GB 吉字节 1GB=1024MB
	 * @返回类型：long
	 */
	@SuppressLint("NewApi")
	public static long getTotalSpace(File file) {
		long size = 0;
		if (isMounted()) {
			if (Build.VERSION.SDK_INT >= 18) {
				StatFs sf = new StatFs(file.getAbsolutePath());
				size = sf.getBlockCountLong() * sf.getBlockSizeLong();// sd卡，任何存储设备（分块进行存储的）
			} else {
				size = file.getTotalSpace();
			}
		} else {
			size = 0;
		}

		return size / 1024 / 1024;
	}

	/**
	 * 
	 * @方法名称:getFreeSpaceSize
	 * @描述: 根据file对象指定当前存储空间可用空间大小
	 * @创建人：徐文波
	 * @创建时间：2015年12月17日 下午8:58:16
	 * @param file
	 * @return
	 * @备注：
	 * @返回类型：long
	 */
	@SuppressLint("NewApi")
	public static long getFreeSpaceSize(File file) {
		long size = 0;
		if (isMounted()) {
			if (Build.VERSION.SDK_INT >= 18) {
				StatFs sf = new StatFs(file.getAbsolutePath());
				// size = sf.getAvailableBlocks() * sf.getBlockSize();
				size = sf.getAvailableBlocksLong() * sf.getBlockSizeLong();
			} else {
				size = file.getFreeSpace();
			}
		} else {
			size = 0;
		}

		return size / 1024 / 1024;
	}

	/**
	 * @方法名称:getExternalStorageRootPath
	 * @创建时间：2015年12月17日 下午8:43:04
	 * @备注： 获取SD卡根路径
	 * @返回类型：String
	 */
	public static String getExternalStorageRootPath() {
		if (isMounted()) {
			return Environment.getExternalStorageDirectory().getAbsolutePath();
		}
		return null;
	}

	/**
	 * 
	 * @方法名称:saveDataIntoSdcard
	 * @描述: 保存数据到SD卡中
	 * @创建人：徐文波
	 * @创建时间：2015年12月17日 下午8:59:20
	 * @param dir
	 *            :要保存的具体路径
	 * @param fileName
	 *            :要保存的文件名称
	 * @param data
	 *            :要保存的数据
	 * @return
	 * @备注：
	 * @返回类型：boolean
	 */
	public static boolean saveDataIntoSdcard(String dir, String fileName,
			byte[] data) {

		FileOutputStream fos = null;
		try {
			if (isMounted()) {// 判断设备是否可用
				String path = getExternalStorageRootPath() + File.separator
						+ dir;
				File parentFile = new File(path);
				if (!parentFile.exists()) {
					parentFile.mkdirs();
				}
				File file = new File(parentFile, fileName);
				fos = new FileOutputStream(file);
				fos.write(data);
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @方法名称:saveDataIntoSdcard
	 * @描述: 保存数据到SD卡中
	 * @创建人：徐文波
	 * @创建时间：2015年12月17日 下午8:59:20
	 * @param dir
	 *            :要保存的具体路径
	 * @param fileName
	 *            :要保存的文件名称
	 * @param data
	 *            :要保存的数据
	 * @return
	 * @备注：
	 * @返回类型：boolean
	 */
	public static boolean saveDataIntoPhone(String dir, String fileName,
			byte[] data) {

		FileOutputStream fos = null;
		try {
			if (isMounted()) {// 判断设备是否可用

				File file = new File(dir, fileName);
				fos = new FileOutputStream(file);
				fos.write(data);
				return true;

			} else {

				File file = new File(dir, fileName);
				fos = new FileOutputStream(file);
				fos.write(data);
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @方法名称:readDataFromSdcard
	 * @描述: 根据文件名称读取sdcard根目录的文件
	 * @创建人：徐文波
	 * @创建时间：2015年12月17日 下午9:02:14
	 * @param dir
	 *            :文件夹父目录
	 * @param fileName
	 *            :要读取的文件名
	 * @return
	 * @备注：
	 * @返回类型：byte[]
	 */
	public static byte[] readDataFromSdcard(String dir, String fileName) {
		ByteArrayOutputStream baos = null;
		FileInputStream fis = null;
		try {
			if (isMounted()) {
				String path = getExternalStorageRootPath() + File.separator
						+ dir;
				File file = new File(path, fileName);
				if (file != null && file.exists()) {
					baos = new ByteArrayOutputStream();
					fis = new FileInputStream(file);
					int len = 0;
					byte[] buffer = new byte[1024];
					while ((len = fis.read(buffer)) != -1) {
						baos.write(buffer, 0, len);
					}

					return baos.toByteArray();
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param dir
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static byte[] readDataFromPhone(String dir, String fileName) {
		ByteArrayOutputStream baos = null;
		FileInputStream fis = null;

		if (isMounted()) {
			File file = new File(dir, fileName);
			if (file != null && file.exists()) {
				baos = new ByteArrayOutputStream();
				try {
					fis = new FileInputStream(file);
					int len = 0;
					byte[] buffer = new byte[1024];
					while ((len = fis.read(buffer)) != -1) {
						baos.write(buffer, 0, len);
					}

					return baos.toByteArray();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} else {

			File file = new File(dir, fileName);
			if (file != null && file.exists()) {
				try {
					baos = new ByteArrayOutputStream();
					fis = new FileInputStream(fileName);
					int len = 0;
					byte[] buffer = new byte[1024];
					while ((len = fis.read(buffer)) != -1) {
						baos.write(buffer, 0, len);
					}

					return baos.toByteArray();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @方法名称:saveDataIntoSdcardPublic
	 * @描述: 向外部存储的公共路径写入内容
	 * @创建人：徐文波
	 * @创建时间：2015年12月17日 下午9:08:20
	 * @param type
	 *            :文件夹类型
	 * @param fileName
	 * @param content
	 *            :要存储的文件内容
	 * @return
	 * @备注：
	 * @返回类型：boolean
	 * 
	 */
	public static boolean saveDataIntoSdcardPublic(String type,
			String fileName, String content) {

		try {
			if (isMounted()) {// 判断设备是否可用
				if (hasThePublicDir(type)) {// 当前类型文件是否存在
					// getExternalStoragePublicDirectory(String type)
					// 注意:type不能为null
					// 表示根据type类型获取外部存储公共路径的file对象
					File parentFile = Environment
							.getExternalStoragePublicDirectory(type);
					File file = new File(parentFile, fileName);
					FileOutputStream fos = new FileOutputStream(file);
					fos.write(content.getBytes());
					fos.close();
					return true;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 
	 * @方法名称:readDataFromPublic
	 * @描述: 读取外部公共存储路径下的文件
	 * @创建人：徐文波
	 * @创建时间：2015年12月17日 下午9:09:32
	 * @param type
	 * @param fileName
	 * @return
	 * @备注：
	 * @返回类型：byte[]
	 */
	public static byte[] readDataFromPublic(String type, String fileName) {
		ByteArrayOutputStream baos = null;
		FileInputStream fis = null;
		try {
			if (isMounted()) {
				if (hasThePublicDir(type)) {
					File parentFile = Environment
							.getExternalStoragePublicDirectory(type);
					File file = new File(parentFile, fileName);
					baos = new ByteArrayOutputStream();
					fis = new FileInputStream(file);
					int len = 0;
					byte[] buffer = new byte[1024];
					while ((len = fis.read(buffer)) != -1) {
						baos.write(buffer, 0, len);
					}

					return baos.toByteArray();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @方法名称:saveDataIntoPrivate
	 * @描述: 保存数据到SD的私有路径
	 * @创建人：徐文波
	 * @创建时间：2015年12月17日 下午9:13:19
	 * @param type
	 * @param fileName
	 * @param content
	 * @param context
	 * @return
	 * @备注： 如果getExternalFilesDir(String type)中type指定了具体的类型(Environment中的常量)
	 *      那么路径是 mnt/sdcard/Android/data/应用程序包名/files/指定文件的类型/文件 如果type未指定则
	 *      mnt/sdcard/Android/data/应用程序包名/files/文件
	 * @返回类型：boolean
	 */
	public static boolean saveDataIntoPrivate(String type, String fileName,
			String content, Context context) {
		try {
			if (isMounted()) {// 判断设备是否可用
				// getExternalFilesDir() 根据指定的类型获取外部存储的路径 注意:type可以为空
				File parentFile = context.getExternalFilesDir(type);
				File file = new File(parentFile, fileName);
				FileOutputStream outputStream = new FileOutputStream(file);
				outputStream.write(content.getBytes());
				outputStream.close();
				return true;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @方法名称:readDataFromPrivate
	 * @描述: 读取外部存储的私有路径下的文件
	 * @创建人：徐文波
	 * @创建时间：2015年12月17日 下午9:14:08
	 * @param type
	 * @param fileName
	 * @param context
	 * @return
	 * @备注：
	 * @返回类型：byte[]
	 */
	public static byte[] readDataFromPrivate(String type, String fileName,
			Context context) {

		ByteArrayOutputStream baos = null;
		FileInputStream fis = null;

		try {
			if (isMounted()) {
				File parentFile = context.getExternalFilesDir(type);
				File file = new File(parentFile, fileName);
				baos = new ByteArrayOutputStream();
				fis = new FileInputStream(file);
				int len = 0;
				byte[] buffer = new byte[1024];
				while ((len = fis.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
				}
				return baos.toByteArray();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @方法名称:deleteFile
	 * @描述: 根据指定的file删除文件
	 * @创建人：徐文波
	 * @创建时间：2015年12月17日 下午9:14:25
	 * @param file
	 * @return
	 * @备注：
	 * @返回类型：boolean
	 */
	public static boolean deleteFile(File file) {
		if (file != null && file.exists()) {
			return file.delete();
		}
		return false;
	}

	/**
	 * 
	 * @方法名称:hasExternalStoragePublicDir
	 * @描述: 根据传递的文件夹类型的字符串判断当前外部存储的公共路径下是否存在该文件夹
	 * @创建人：徐文波
	 * @创建时间：2015年12月17日 下午9:07:26
	 * @param type
	 * @return
	 * @备注：
	 * @返回类型：boolean
	 */
	public static boolean hasThePublicDir(String type) {
		File file = Environment.getExternalStoragePublicDirectory(type);
		// 判断当前生成的file对象是否存在
		if (file != null && file.exists()) {
			return true;
		}
		return false;
	}

	/**
	 * 获得手机上（内部存储，外部sd卡）的文件
	 * 
	 * @para
	 * @return
	 */
	public static File getPhoneFileName(Context context, String fileName) {
		// 约定目录：
		// 内部：cache;外部：sd卡的公共目录下:根目录
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {

			File file = new File(Environment.getExternalStorageDirectory(),
					fileName);
			return file;

		} else {
			File file = new File(context.getCacheDir(), fileName);
			return file;
		}
	}

	/**
	 * 获得手机上的目录
	 * 
	 * @return
	 */
	public static String getPhoneFileDir(Context context) {
		// 约定目录：
		// 内部：cache;外部：sd卡的公共目录下:根目录
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {

			return CommonData.DOCUMENTS_DIR;

		} else {
			return context.getCacheDir().toString();
		}
	}

	/**
	 * 判断标签名文件是否是存在
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isExistTabFile(Context context) {
		File tagNameFile = new File(
				ExternalStorageUtil.getPhoneFileDir(context), "tagNames.txt");

		System.out.println("tagNameFile = " + tagNameFile + "，存在否？"
				+ tagNameFile.exists());
		return tagNameFile.exists() && tagNameFile.length() > 0;
	}
}
