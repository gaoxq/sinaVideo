//测试正则表达式
package weibo.crwal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegxExam {
	public static void main(String[] args) {
		String ora="<dl class=\"feed_list\" mid=\"3624171432884749\"";
		String regex="\\<dl.+?\\smid=\\\"(\\d+).*?\"";//这就是正则表达式了
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(ora);
		while(m.find())
		{
			System.out.println(m.group(1));
		}
	}
}
