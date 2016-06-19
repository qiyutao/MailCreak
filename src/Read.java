import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class Read {
	private File fAccount = null;
	private File fPassword = null;
	private List<String> userName = null;
	private List<String> password = null;
	private AccackUI frame = null;
	
	public Read(File fA,File fP,AccackUI a) {
		// TODO Auto-generated constructor stub
		fAccount = fA;
		fPassword = fP;
		frame = a;
		
		userName = new ArrayList<>();
		password = new ArrayList<>();
	}
	
	public void connect() {
		parse();
		if(frame.getChoose()==2) {
			JwcBlast blast = new JwcBlast(frame);
			blast.launch(userName, password);
		} else {
			Mail163Blast blast = new Mail163Blast(frame);
			blast.launch(userName, password);
		}
	}
	
	public void parse() {
		if(fAccount.getName().indexOf("xls")!=-1) {
			readXls(fAccount,userName);
			if(fPassword!=null)
				readXls(fPassword, password);
		} else {
			readText(fAccount,userName);
			if(fPassword!=null)
				readText(fPassword, password);
		}
	}
	
	public void readXls(File file,List<String> list) {
		Workbook workbook;
		String name = null;
		try {
			workbook = Workbook.getWorkbook(file);
		
			Sheet sheet = workbook.getSheet(0);
			int row = sheet.getRows();
			Cell cell = null;
			for (int i = 0; i < row; i++){
				cell = sheet.getCell(0, i);
				name = cell.getContents();
				list.add(name);
			}
			
			workbook.close();
		}catch (Exception  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readText(File file,List<String> list) {
		String str = null;
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader buf = new BufferedReader(fileReader);
			
			while((str = buf.readLine())!=null) {
				list.add(str);
			}
			buf.close();
			fileReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
