package client.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.service.domain.User;



public class RestHttpClientApp {
	
	// main Method
	public static void main(String[] args) throws Exception{
		
		////////////////////////////////////////////////////////////////////////////////////////////
		// �ּ��� �ϳ��� ó���ذ��� �ǽ�
		////////////////////////////////////////////////////////////////////////////////////////////
		
		//System.out.println("\n====================================\n");
//		// 1.1 Http Get ��� Request : JsonSimple lib ���
		//RestHttpClientApp.getUserTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Get ��� Request : CodeHaus lib ���
//		RestHttpClientApp.getUserTest_Codehaus();
		
		System.out.println("\n====================================\n");
//		// 2.1 Http Post ��� Request : JsonSimple lib ���
//		RestHttpClientApp.LoginTest_JsonSimple();
//		RestHttpClientApp.addProductTest_JsonSimple();
		//RestHttpClientApp.addUserTest_JsonSimple();
//		RestHttpClientApp.updateUserTest_JsonSimple();
//		RestHttpClientApp.updateProductTest_JsonSimple();
		RestHttpClientApp.listProductTest_JsonSimple();
//		RestHttpClientApp.listUserTest_JsonSimple();
//		RestHttpClientApp.checkDuplicationTest_JsonSimple();
//		RestHttpClientApp.getProductTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Post ��� Request : CodeHaus lib ���
//		RestHttpClientApp.LoginTest_Codehaus();		
	
	}
	
	
//================================================================//
	//1.1 Http Protocol GET Request : JsonSimple 3rd party lib ���
	public static void getUserTest_JsonSimple() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/user/json/getUser/admin";
				
		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> �����б�(JSON Value Ȯ��)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	}
	
	
	//1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib ���
	public static void getUserTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/user/json/getUser/admin";

		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> �ٸ� ������� serverData ó�� 
		//System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 User user = objectMapper.readValue(jsonobj.toString(), User.class);
		 System.out.println(user);
	}
//================================================================//	
	
//================================================================//
	//2.1 Http Protocol POST Request : FromData ���� / JsonSimple 3rd party lib ���
	public static void LoginTest_JsonSimple() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/user/json/login";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		//[ ��� 1 : String ���]
//			String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//			HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
		
		//[ ��� 2 : JSONObject ���]
		JSONObject json = new JSONObject();
		json.put("userId", "admin");
		json.put("password", "1234");
		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> �����б�(JSON Value Ȯ��)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	
	}
	
	
	//2.2 Http Protocol POST ��� Request : FromData���� 
	//==> JsonSimple + codehaus 3rd party lib ���
	public static void LoginTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/user/json/login";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
//		//[ ��� 1 : String ���]
//		String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//		HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
	
//		//[ ��� 2 : JSONObject ���]
//		JSONObject json = new JSONObject();
//		json.put("userId", "admin");
//		json.put("password", "1234");
//		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
		
		//[ ��� 3 : codehaus ���]
		User user01 =  new User();
		user01.setUserId("admin");
		user01.setPassword("1234");
		ObjectMapper objectMapper01 = new ObjectMapper();
		//Object ==> JSON Value �� ��ȯ
		String jsonValue = objectMapper01.writeValueAsString(user01);
		
		System.out.println(jsonValue);
		
		HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
		
		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> �ٸ� ������� serverData ó�� 
		//System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 User user = objectMapper.readValue(jsonobj.toString(), User.class);
		 System.out.println(user);
	}	
	
public static void addProductTest_JsonSimple() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/product/json/addProduct";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		//[ ��� 1 : String ���]
//			String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//			HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
		
		//[ ��� 2 : JSONObject ���]
		JSONObject json = new JSONObject();
		json.put("prodName", "admin");
		json.put("prodNo", 10000);
		json.put("manuDate", "20200202");
		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> �����б�(JSON Value Ȯ��)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	
	}
	
public static void addUserTest_JsonSimple() throws Exception{
	
	// HttpClient : Http Protocol �� client �߻�ȭ 
	HttpClient httpClient = new DefaultHttpClient();
	
	String url = "http://127.0.0.1:8080/user/json/addUser";
	HttpPost httpPost = new HttpPost(url);
	httpPost.setHeader("Accept", "application/json");
	httpPost.setHeader("Content-Type", "application/json");
	
	//[ ��� 1 : String ���]
//		String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//		HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
	
	//[ ��� 2 : JSONObject ���]
	JSONObject json = new JSONObject();
	json.put("userId", "adam2n");
	json.put("userName", "10000");
	json.put("password", "10000");
	json.put("ssn", "123457897777");
	json.put("phone", "010-1111-1111");
	json.put("addr", "1111aaa");
	json.put("email", "1111@ssasd.com");
	HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

	httpPost.setEntity(httpEntity01);
	HttpResponse httpResponse = httpClient.execute(httpPost);
	
	//==> Response Ȯ��
	System.out.println(httpResponse);
	System.out.println();

	//==> Response �� entity(DATA) Ȯ��
	HttpEntity httpEntity = httpResponse.getEntity();
	
	System.out.println("======");
	//==> InputStream ����
	InputStream is = httpEntity.getContent();
	BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
	
	System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
	
	String serverData = br.readLine();
	System.out.println("======");
	System.out.println(serverData);
	System.out.println("======");
	
	//==> �����б�(JSON Value Ȯ��)
	JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
	System.out.println(jsonobj);

	}

public static void updateUserTest_JsonSimple() throws Exception{
	
	// HttpClient : Http Protocol �� client �߻�ȭ 
	HttpClient httpClient = new DefaultHttpClient();
	
	String url = "http://127.0.0.1:8080/user/json/updateUser";
	HttpPost httpPost = new HttpPost(url);
	httpPost.setHeader("Accept", "application/json");
	httpPost.setHeader("Content-Type", "application/json");
//	httpPost.setHeader("Set-Cookie", "JSESSIONID=C89EDC0E55C3326F68B0DF39A774C4CF");
	
	//[ ��� 1 : String ���]
//		String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//		HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
	
	//[ ��� 2 : JSONObject ���]
	JSONObject json = new JSONObject();
	json.put("userId", "admn");
	json.put("userName", "1001");
	json.put("phone", "010-1112-1111");
	json.put("addr", "11113aa");
	json.put("email", "1111@ss1sd.com");

	HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

	httpPost.setEntity(httpEntity01);
	HttpResponse httpResponse = httpClient.execute(httpPost);
	
	//==> Response Ȯ��
	System.out.println(httpResponse);
	System.out.println();

	//==> Response �� entity(DATA) Ȯ��
	HttpEntity httpEntity = httpResponse.getEntity();
	
	//==> InputStream ����
	InputStream is = httpEntity.getContent();
	BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
	
	System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
	String serverData = br.readLine();
	System.out.println(serverData);
	
	//==> �����б�(JSON Value Ȯ��)
	JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
	System.out.println(jsonobj);

}

public static void updateProductTest_JsonSimple() throws Exception{
	
	// HttpClient : Http Protocol �� client �߻�ȭ 
	HttpClient httpClient = new DefaultHttpClient();
	
	String url = "http://127.0.0.1:8080/product/json/updateProduct";
	HttpPost httpPost = new HttpPost(url);
	httpPost.setHeader("Accept", "application/json");
	httpPost.setHeader("Content-Type", "application/json");
	
	//[ ��� 1 : String ���]
//		String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//		HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
	
	//[ ��� 2 : JSONObject ���]
	JSONObject json = new JSONObject();
	json.put("prodName", "admn");
	json.put("prodNo", 10000);
	

	HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

	httpPost.setEntity(httpEntity01);
	HttpResponse httpResponse = httpClient.execute(httpPost);
	
	//==> Response Ȯ��
	System.out.println(httpResponse);
	System.out.println();

	//==> Response �� entity(DATA) Ȯ��
	HttpEntity httpEntity = httpResponse.getEntity();
	
	//==> InputStream ����
	InputStream is = httpEntity.getContent();
	BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
	
	System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
	String serverData = br.readLine();
	System.out.println(serverData);
	
	//==> �����б�(JSON Value Ȯ��)
	JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
	System.out.println(jsonobj);

}

public static void listProductTest_JsonSimple() throws Exception{
	
	// HttpClient : Http Protocol �� client �߻�ȭ 
	HttpClient httpClient = new DefaultHttpClient();
	
	String url = "http://127.0.0.1:8080/product/json/listProduct";
	HttpPost httpPost = new HttpPost(url);
	httpPost.setHeader("Accept", "application/json");
	httpPost.setHeader("Content-Type", "application/json");
	
	//[ ��� 1 : String ���]
//		String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//		HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
	
	//[ ��� 2 : JSONObject ���]
	JSONObject json = new JSONObject();
	

	HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

	httpPost.setEntity(httpEntity01);
	HttpResponse httpResponse = httpClient.execute(httpPost);
	
	//==> Response Ȯ��
	System.out.println(httpResponse);
	System.out.println();

	//==> Response �� entity(DATA) Ȯ��
	HttpEntity httpEntity = httpResponse.getEntity();
	
	//==> InputStream ����
	InputStream is = httpEntity.getContent();
	BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
	
	System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
	String serverData = br.readLine();
	System.out.println(serverData);
	
	//==> �����б�(JSON Value Ȯ��)
	JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
	System.out.println(jsonobj);

}

public static void listUserTest_JsonSimple() throws Exception{
	
	// HttpClient : Http Protocol �� client �߻�ȭ 
	HttpClient httpClient = new DefaultHttpClient();
	
	String url = "http://127.0.0.1:8080/user/json/listUser";
	HttpPost httpPost = new HttpPost(url);
	httpPost.setHeader("Accept", "application/json");
	httpPost.setHeader("Content-Type", "application/json");
	
	//[ ��� 1 : String ���]
//		String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//		HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
	
	//[ ��� 2 : JSONObject ���]
	JSONObject json = new JSONObject();
	

	HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

	httpPost.setEntity(httpEntity01);
	HttpResponse httpResponse = httpClient.execute(httpPost);
	
	//==> Response Ȯ��
	System.out.println(httpResponse);
	System.out.println();

	//==> Response �� entity(DATA) Ȯ��
	HttpEntity httpEntity = httpResponse.getEntity();
	
	//==> InputStream ����
	InputStream is = httpEntity.getContent();
	BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
	
	System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
	String serverData = br.readLine();
	System.out.println(serverData);
	
	//==> �����б�(JSON Value Ȯ��)
	JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
	System.out.println(jsonobj);

}
public static void checkDuplicationTest_JsonSimple() throws Exception{
	
	// HttpClient : Http Protocol �� client �߻�ȭ 
	HttpClient httpClient = new DefaultHttpClient();
	
	String url = "http://127.0.0.1:8080/user/json/checkDuplication";
	HttpPost httpPost = new HttpPost(url);
	httpPost.setHeader("Accept", "application/json");
	httpPost.setHeader("Content-Type", "application/json");
	
	//[ ��� 1 : String ���]
//		String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//		HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
	
	//[ ��� 2 : JSONObject ���]
	JSONObject json = new JSONObject();
	json.put("userId", "admn");

	HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

	httpPost.setEntity(httpEntity01);
	HttpResponse httpResponse = httpClient.execute(httpPost);
	
	//==> Response Ȯ��
	System.out.println(httpResponse);
	System.out.println();

	//==> Response �� entity(DATA) Ȯ��
	HttpEntity httpEntity = httpResponse.getEntity();
	
	//==> InputStream ����
	InputStream is = httpEntity.getContent();
	BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
	
	System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
	String serverData = br.readLine();
	System.out.println(serverData);
	
	//==> �����б�(JSON Value Ȯ��)
	JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
	System.out.println(jsonobj);

	}
	
public static void getProductTest_JsonSimple() throws Exception{
	
	// HttpClient : Http Protocol �� client �߻�ȭ 
	HttpClient httpClient = new DefaultHttpClient();
	
	String url= 	"http://127.0.0.1:8080/product/json/getProduct/10020";
			
	// HttpGet : Http Protocol �� GET ��� Request
	HttpGet httpGet = new HttpGet(url);
	httpGet.setHeader("Accept", "application/json");
	httpGet.setHeader("Content-Type", "application/json");
	
	// HttpResponse : Http Protocol ���� Message �߻�ȭ
	HttpResponse httpResponse = httpClient.execute(httpGet);
	
	//==> Response Ȯ��
	System.out.println(httpResponse);
	System.out.println();

	//==> Response �� entity(DATA) Ȯ��
	HttpEntity httpEntity = httpResponse.getEntity();
	
	//==> InputStream ����
	InputStream is = httpEntity.getContent();
	BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
	
	System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
	String serverData = br.readLine();
	System.out.println(serverData);
	
	//==> �����б�(JSON Value Ȯ��)
	JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
	System.out.println(jsonobj);
	}
}