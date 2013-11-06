package weibo.crwal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebPageSource {
	
	public ArrayList<String> getMid(String Page, ArrayList<String> midList) {
		// 使用原网页里声明的gb2312反而会出现乱码
		// <dl class=\"feed_list\" mid=\"3624171432884749\"
		Pattern p = Pattern.compile("\\<dl.+?\\smid=\\\"(\\d+).*?\"", Pattern.CASE_INSENSITIVE);		
		Matcher m = p.matcher(Page);
		while (m.find()) {
			midList.add(m.group(1));
			System.out.println("mid"+m.group(1));
		}
		System.out.println(Page);
		return midList;		
	}
	public String getLinkName(String Page, String url) {
		//<title>请勿放弃治疗：2013 Vine短视频集锦—在线播放—优酷网，视频高清在线观看</title>
		Pattern p = Pattern.compile("<title>(.*?)(—|_|-)", Pattern.CASE_INSENSITIVE);		
		Matcher m = p.matcher(Page);
		 if(m.find()) {
			 return "E:/SinaVideo/SinaVideoDownload/"+m.group(1)+".flv";
		}
		 else 
			 return url;
	}
	public static boolean append(String fileName, String fileContent) {
		boolean result = false;
		File f = new File(fileName);
		try {
			if (f.exists()) {
				FileInputStream fsIn = new FileInputStream(f);
				byte[] bIn = new byte[fsIn.available()];
				fsIn.read(bIn);
				String oldFileContent = new String(bIn);
				// System.out.println("旧内容:" + oldFileContent);
				fsIn.close();
				if (!oldFileContent.equalsIgnoreCase("")) {
					fileContent = oldFileContent + "\r\n" + fileContent;
					// System.out.println("新内容:" + fileContent);
				}
			}
			FileOutputStream fs = new FileOutputStream(f);
			byte[] b = fileContent.getBytes();
			fs.write(b);
			fs.flush();
			fs.close();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public static String getHTML(String pageURL, String encoding) {
		StringBuilder pageHTML = new StringBuilder();
		try {
			URL url = new URL(pageURL);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestProperty("User-Agent", "MSIE 7.0");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), encoding));
			String line = null;
			while ((line = br.readLine()) != null) {
				pageHTML.append(line);
				pageHTML.append("\r\n");
			//	append("E:/SinaVideo/pageSource.txt", line);
			}
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageHTML.toString();
	}
}

