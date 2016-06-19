import java.util.ArrayList;
import java.util.List;

public abstract class Blast {
	protected List<String> userName = null;
	protected List<String> password = null;
	protected int total;
	protected List<String> hackedName = null;
	protected List<String> hackedPwd = null;
	protected AccackUI frame = null;
	
	public Blast(AccackUI a) {
		// TODO Auto-generated constructor stub
		frame = a;
		total = 0;
		hackedName = new ArrayList<>();
		hackedPwd = new ArrayList<>();
	}
	
	public void launch(List<String> uList,List<String> pList) {
		userName = uList;
		password = pList;
		
		if(password.size()!=0)
			hasPassword();
		else
			noPassword();
		
	}
	
	public void hasPassword() {
		int cur;
		String url = null;
		for(int i = 0;i<userName.size();i++) {
			String su = userName.get(i);
			for(int j = 0;j<password.size();j++) {
				String sp = password.get(j);
				
				cur = (int)(((double)(i*password.size()+j)/userName.size()*password.size())*100);
				frame.setProBar(cur);
				frame.setLab(su, sp);
				frame.validate();
				connect(su, sp);
			}
		}
		frame.setProBar(100);
		frame.setHacked(hackedName,hackedPwd);
	}
	
	public void noPassword() {
		int cur;
		String url = null;
		for(int i = 0;i<userName.size();i++) {
			String su = userName.get(i);
				cur = (int)(((double)(i*1.0/userName.size()))*100);
				frame.setProBar(cur);
				frame.setLab(su, su);
				frame.validate();
				connect(su, su);
			
		}
		frame.setProBar(100);
		frame.setHacked(hackedName,hackedPwd);
	}
	
	public abstract void connect(String name,String pwd);
}
