package weibo4j.examples.timeline;

import weibo4j.Timeline;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONArray;

public class GetRepostDaily {
	public static void main(String[] args) {
		String access_token = "2.00f6LUwB0iDhDB4ef8bd9e2eynKudC";
		Timeline tm = new Timeline();
		tm.client.setToken(access_token);
		try {
			JSONArray status = tm.getRepostDaily();
			Log.logInfo(status.toString());

		} catch (WeiboException e) {
			e.printStackTrace();
		}

	}

}
