package weibo.crwal;

import weibo4j.Comments;
import weibo4j.model.Status;
import weibo4j.Timeline;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import weibo4j.model.CommentWapper;
import weibo4j.model.Comment;
import weibo4j.model.Paging;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONArray;
import weibo4j.org.json.JSONObject;
public class GetTimelines {
	
	public static void main(String[] args) {
		int pageNum = 1; 
		for(int j = 1; j <= pageNum; j++) {
			String url = "http://s.weibo.com/weibo/%25E7%259F%25AD%25E8%25A7%2586%25E9%25A2%2591&hasvideo=1&page="+j;
			WebPageSource find = new WebPageSource();
			myclass a = new myclass();
			find.getMid(WebPageSource.getHTML(url, "utf-8"), a.midList);
			String access_token = "2.00f6LUwB0iDhDB4ef8bd9e2eynKudC";
			String id = null;
			Timeline tm = new Timeline();
			tm.client.setToken(access_token);
			try {
				WebPageSource.append("E:/SinaVideo/status.txt", String.valueOf(a.midList.size()));
				//System.out.println(a.midList.size());	
				for(int i = 0; i < a.midList.size(); i++)
				{
					id = a.midList.get(i);
					Status status = tm.showStatus(id);
					//Log.logInfo(status.toString());
					myclass x = new myclass(status, access_token);
					x.toString(find);
					//System.out.println(x);		
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class myclass {
	private Date createdAt;
	private String id;
	private String mid;
	private String text;
	private String original_pic;
	private Status retweetedStatus = null;
	private int repostsCount;
	private int commentsCount;
	private String url;// 视频的url
	private List<Comment> comment;// 评论列表
	private ArrayList<String> scomment = new ArrayList<String>();// 评论列表的内容
	private String s;
	private int index = 1;
	private String videoShortLink;
	private String videoLongLink;
	ArrayList<String> midList = new ArrayList<String>();
	public myclass(Status x, String access_token) {
		try {
			createdAt = x.getCreatedAt();
			id = x.getId();
			retweetedStatus = x.getRetweetedStatus();
			// 若此微博是转发的,则应转向被转发的位置
			if (retweetedStatus != null) {
				
				mid = retweetedStatus.getMid();
				text = retweetedStatus.getText();
				original_pic = retweetedStatus.getOriginalPic();
				repostsCount = retweetedStatus.getRepostsCount();
				commentsCount = retweetedStatus.getCommentsCount();
			}
			
			// 原创微博
			else {
				mid = x.getMid();
				text = x.getText();
				original_pic = x.getOriginalPic();
				repostsCount = x.getRepostsCount();
				commentsCount = x.getCommentsCount();
			}
			
			Pattern p = Pattern.compile("http://[\\w\\.\\-/:]+",Pattern.CASE_INSENSITIVE );
			Matcher m = p.matcher(text);
			if(m.find())
			{
				videoShortLink = m.group();
				weibo4j.ShortUrl su = new weibo4j.ShortUrl();
				su.client.setToken(access_token);
				try {
					JSONObject jo = su.shortToLongUrl(videoShortLink);
					JSONArray jsonOb = jo.getJSONArray("urls");
					JSONObject info=jsonOb.getJSONObject(0);
					videoLongLink = info.getString("url_long");
				} catch (WeiboException e) {
					e.printStackTrace();
				}
			}
			
			// 获得评论内容
			/*
			Comments cm = new Comments();
			cm.client.setToken(access_token);
			CommentWapper cw = cm.getCommentById(mid);
			comment = cw.getComments();
			for (Comment c : comment) {
				scomment.add(c.getText()); 
			}
			*/
			Comments cm = new Comments();
			cm.client.setToken(access_token);
			Paging paging = new Paging();
			CommentWapper cw = cm.getCommentById(mid, paging, 0);
			comment = cw.getComments();
			for (Comment c : comment) {
				scomment.add(c.getText()); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public myclass() {
		// TODO Auto-generated constructor stub
	}

	public String toString(WebPageSource find) {	
		String result = "createdAt:" + createdAt + ",id=" + id + ",mid=" + mid
				+ ",text=" + text + ",original_pic=" + original_pic
				+ ",repostsCount=" + repostsCount + ",commentsCount="
				+ commentsCount + ",VideoShortLink=" + videoShortLink + ",VideoLongLink=" + videoLongLink;
		WebPageSource.append("E:/SinaVideo/status.txt", result);
		WebPageSource.append("E:/SinaVideo/status.txt", "comment") ;
		for (String s : scomment) {
			//System.out.println(s);
			WebPageSource.append("E:/SinaVideo/status.txt", s);			
		}
		WebPageSource.append("E:/SinaVideo/status.txt", "\n");
		return result;
	}
}
