package test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;

public class TestUpload {

	/**
	 * 这个例子展示了如何执行请求包含一个多部分编码的实体 模拟表单提交
	 * 
	 * @throws IOException
	 */
	public static void update1() throws IOException {

		String url = "http://localhost:8080/bxb/attachment/upload";
		CloseableHttpClient httpClient = HttpClients.createDefault();

		try {
			// 要上传的文件的路径
			String filePath = "F:\\张敏保障说明.docx";
			// 把一个普通参数和文件上传给下面这个地址 是一个servlet
			HttpPost httpPost = new HttpPost(url);
			// 把文件转换成流对象FileBody
			File file = new File(filePath);
			FileBody bin = new FileBody(file);
			StringBody userId = new StringBody("用户ID", ContentType.create(
					"text/plain", Consts.UTF_8));
			// 以浏览器兼容模式运行，防止文件名乱码。
			HttpEntity reqEntity = MultipartEntityBuilder.create()
					.setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
					.addPart("multipartFile", bin).addPart("userId", userId)
					.setCharset(CharsetUtils.get("UTF-8")).build();

			httpPost.setEntity(reqEntity);

			System.out.println("发起请求的页面地址 " + httpPost.getRequestLine());
			// 发起请求 并返回请求的响应
			CloseableHttpResponse response = httpClient.execute(httpPost);
			try {
				System.out.println("----------------------------------------");
				// 打印响应状态
				System.out.println(response.getStatusLine());
				// 获取响应对象
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					// 打印响应长度
					System.out.println("Response content length: "
							+ resEntity.getContentLength());
					// 打印响应内容
					System.out.println(EntityUtils.toString(resEntity,
							Charset.forName("UTF-8")));
				}
				// 销毁
				EntityUtils.consume(resEntity);
			} finally {
				response.close();
			}
		} finally {
			httpClient.close();
		}
	}

	/**
	 * 这个例子展示了如何执行请求包含一个多部分编码的实体 模拟表单提交
	 * 
	 * @throws IOException
	 */
	public static void update2() throws IOException {

		String url = "http://localhost:8080/bxb/attachment/upload_pic_cj";
		CloseableHttpClient httpClient = HttpClients.createDefault();

		try {
			// 要上传的文件的路径
			String filePath = "F:\\li.png";
			// 把一个普通参数和文件上传给下面这个地址 是一个servlet
			HttpPost httpPost = new HttpPost(url);
			// 把文件转换成流对象FileBody
			File file = new File(filePath);
			FileBody bin = new FileBody(file);

			StringBody x1 = new StringBody("30.5", ContentType.create(
					"text/plain", Consts.UTF_8));
			StringBody y1 = new StringBody("22", ContentType.create(
					"text/plain", Consts.UTF_8));
			StringBody x2 = new StringBody("176.5", ContentType.create(
					"text/plain", Consts.UTF_8));
			StringBody y2 = new StringBody("168", ContentType.create(
					"text/plain", Consts.UTF_8));

			StringBody w = new StringBody("146", ContentType.create(
					"text/plain", Consts.UTF_8));
			StringBody h = new StringBody("146", ContentType.create(
					"text/plain", Consts.UTF_8));

			// 以浏览器兼容模式运行，防止文件名乱码。
			HttpEntity reqEntity = MultipartEntityBuilder.create()
					.setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
					.addPart("multipartFile", bin).addPart("x1", x1)
					.addPart("y1", y1).addPart("x2", x2).addPart("y2", y2)
					.addPart("w", w).addPart("h", h)
					.setCharset(CharsetUtils.get("UTF-8")).build();

			httpPost.setEntity(reqEntity);

			System.out.println("发起请求的页面地址 " + httpPost.getRequestLine());
			// 发起请求 并返回请求的响应
			CloseableHttpResponse response = httpClient.execute(httpPost);
			try {
				System.out.println("----------------------------------------");
				// 打印响应状态
				System.out.println(response.getStatusLine());
				// 获取响应对象
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					// 打印响应长度
					System.out.println("Response content length: "
							+ resEntity.getContentLength());
					// 打印响应内容
					System.out.println(EntityUtils.toString(resEntity,
							Charset.forName("UTF-8")));
				}
				// 销毁
				EntityUtils.consume(resEntity);
			} finally {
				response.close();
			}
		} finally {
			httpClient.close();
		}
	}

	public static void main(String[] args) throws IOException {
		update2();
	}
}
