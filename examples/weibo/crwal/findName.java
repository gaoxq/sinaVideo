package weibo.crwal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//根据网页源代码，获取视频链接的名字
public class findName {
	/*
	public static void main(String[] args) {
		String url = "http://www.netnoease.com/201310/the-pocket-reflector.html";
		WebPageSource find = new WebPageSource();
		System.out.print(find.getLinkName(WebPageSource.getHTML(url, "utf-8"), url));
	}
	*/
	
	public static void main(String[] args) throws IOException, InterruptedException {
		FileReader fr=new FileReader("E:/SinaVideo/link.lst");
		BufferedReader br=new BufferedReader(fr);
		String s;
		while((s=br.readLine())!= null)
		{
			WebPageSource find = new WebPageSource();
			System.out.println(find.getLinkName(WebPageSource.getHTML(s, "utf-8"), s));
			
		}
	}
	
}
