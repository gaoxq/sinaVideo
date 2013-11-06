package weibo.crwal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

//解析维棠导出列表，利用hash根据长链接得到文件名
public class hashMap {
	public static void main(String[] args) throws IOException {
		HashMap<String , String> map = new HashMap<String , String>();
		FileReader fr=new FileReader("E:/SinaVideo/linkList.lst");
		BufferedReader br=new BufferedReader(fr);
		String s;
		while((s=br.readLine())!= null)
		{
			String[] words = s.split("\\|");
			map.put(words[0], words[1]);
			System.out.println(words[0]+" "+words[1]);
		}
		FileReader tr=new FileReader("E:/SinaVideo/link.lst");
		BufferedReader zr=new BufferedReader(tr);
		while((s=zr.readLine())!= null)
		{
			//System.out.println(map.get(s));
			WebPageSource.append("E:/SinaVideo/hash.txt", map.get(s));
		}
	}
}
