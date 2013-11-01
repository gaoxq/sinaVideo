package weibo4j.video;
import weibo4j.Timeline;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Paging;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;

//获取当前登录用户及其所关注用户的最新微博消息。
//过滤类型ID，0：全部、1：原创、2：图片、3：视频、4：音乐，默认为0。
public class GetFriendsVideo {
	
		public static void main(String[] args) {
			String access_token = "2.00f6LUwB0iDhDB4ef8bd9e2eynKudC";
			Timeline tm = new Timeline();
			Integer baseAPP = 0;
			Paging paging = null;
			tm.client.setToken(access_token);
			try {
				StatusWapper status = tm.getFriendsTimeline(baseAPP, 3, paging);
				for(Status s : status.getStatuses()){
					Log.logInfo(s.toString());
				}
				System.out.println(status.getNextCursor());
				System.out.println(status.getPreviousCursor());
				System.out.println(status.getTotalNumber());
				System.out.println(status.getHasvisible());
			} catch (WeiboException e) {
				e.printStackTrace();
			}

		}

	}

