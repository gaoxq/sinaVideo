package weibo4j.examples.timeline;

import weibo4j.Timeline;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;

//用个人id发送新浪微博
public class UpdateStatus {

	public static void main(String[] args) {
		String access_token = "2.00f6LUwB0iDhDB4ef8bd9e2eynKudC";
		String statuses = "df";
		Timeline tm = new Timeline();
		tm.client.setToken(access_token);
		try {
			Status status = tm.UpdateStatus(statuses);
			Log.logInfo(status.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}	}

}
