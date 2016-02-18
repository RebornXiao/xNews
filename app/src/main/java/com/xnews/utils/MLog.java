package com.xnews.utils;

import android.os.Environment;
import android.util.Log;

import com.xnews.config.Configure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 日志输出
 */
public class MLog {

	public static final boolean DEBUG = Configure.DEBUG;
	public static final boolean WRITE_FILE = false;

	public static final String TAG = "mylog";

	public static void d(String msg) {
		if (DEBUG) {
			Log.d(TAG, msg);
			addRecord(msg);
		}
	}

	public static void d(String tag, String msg) {
		if (DEBUG) {
			Log.d(tag, msg);
			addRecord(msg);
		}
	}

	public static void i(String tag, String msg) {
		if (DEBUG) {
			Log.i(tag, msg);
			addRecord(msg);
		}
	}

	public static void w(String tag, String msg) {
		if (DEBUG) {
			Log.w(tag, msg);
			addRecord(msg);
		}
	}

	public static void e(String tag, String msg) {
		if (DEBUG) {
			Log.e(tag, msg);
			addRecord(msg);
		}
	}

	public static void e(String msg) {
		if (DEBUG) {
			Log.e(TAG, msg);
			addRecord(msg);
		}
	}

	public static void v(String tag, String msg) {
		if (DEBUG) {
			Log.v(tag, msg);
			addRecord(msg);
		}
	}

	private static File file;

	private static void addRecord(String logInfo) {
		if (!WRITE_FILE || logInfo == null || "".equals(logInfo))
			return;

		file = getFile();
		if (file == null)
			return;

		String date = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		StringBuffer sb = new StringBuffer();
		sb.append(date);
		sb.append(" {");
		sb.append(logInfo);
		sb.append("}");
		logInfo = sb.toString();
		BufferedWriter bw = null;
		BufferedReader br = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file, true)));
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					file)));
			if (br.readLine() != null) {
				bw.newLine();
			}
			bw.append(logInfo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static File getFile() {
		String state = Environment.getExternalStorageState();
		if (!Environment.MEDIA_MOUNTED.equals(state)) {
			return null;
		}

		if (file == null) {
			String filePath = getFilePath();
			file = new File(filePath);
			if (!file.exists()) {
				try {
					File dir = file.getParentFile();
					if (!dir.exists())
						dir.mkdirs();
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return file;
	}

	private static String getFilePath() {
		StringBuffer sb = new StringBuffer();
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
		sb.append(File.separator);
		sb.append("HaierTVclient");
		sb.append(File.separator);
		sb.append("logs");
		sb.append(File.separator);
		sb.append("log_");
		sb.append(date);
		sb.append(".txt");
		return sb.toString();
	}
}
