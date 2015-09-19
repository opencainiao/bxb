package test;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class TestUpload {

	public static void main(String[] args) throws ClientProtocolException, IOException {

		String url = "http://localhost:8080/bxb/attachment/upload";
		HttpClient httpclient = new DefaultHttpClient();

		try {

			HttpPost httppost = new HttpPost(url);

			FileBody bin = new FileBody(new File("F:\\11.png"));

			StringBody comment = new StringBody("11.png");

			MultipartEntity reqEntity = new MultipartEntity();

			reqEntity.addPart("file1", bin);// file1为请求后台的File upload;属性
			reqEntity.addPart("filename1", comment);// filename1为请求后台的普通参数;属性
			httppost.setEntity(reqEntity);

			HttpResponse response = httpclient.execute(httppost);

			int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode == HttpStatus.SC_OK) {

				System.out.println("服务器正常响应.....");

				HttpEntity resEntity = response.getEntity();

				System.out.println(EntityUtils.toString(resEntity));// httpclient自带的工具类读取返回数据

				System.out.println(resEntity.getContent());

				EntityUtils.consume(resEntity);
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				httpclient.getConnectionManager().shutdown();
			} catch (Exception ignore) {

			}
		}
	}
}
