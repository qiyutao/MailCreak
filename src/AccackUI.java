import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.StandardBorderPainter;
import org.jvnet.substance.button.ClassicButtonShaper;
import org.jvnet.substance.painter.StandardGradientPainter;
import org.jvnet.substance.theme.SubstanceTerracottaTheme;
import org.jvnet.substance.watermark.SubstanceBubblesWatermark;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class AccackUI extends JFrame implements ActionListener,Runnable{

	private JPanel contentPane;
	private JProgressBar proBar = null;
	private JLabel showCur =null;
	private JMenuItem openItem = null;
	private JMenuItem saveItem = null;
	private JTextArea textArea = null;
	private JLabel stopLab = null;
	private JButton btn = null;
	private List<String> hackedName = null;
	private List<String> hackedPwd = null;
	private JButton ok_opendlg = null;
	private JDialog openDlg = null;
	private JTextField nameTF1 = null;
	private JTextField nameTF2 = null;
	private JButton btn_name = null;
	private JButton btn_pwd = null;
	private File userName = null;
	private File password = null;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton radioJWC = null;
	private JRadioButton radio163 = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
						UIManager.setLookAndFeel(new SubstanceLookAndFeel());
						JFrame.setDefaultLookAndFeelDecorated(true);
						JDialog.setDefaultLookAndFeelDecorated(true);
						SubstanceLookAndFeel.setCurrentTheme(new SubstanceTerracottaTheme());
						SubstanceLookAndFeel.setCurrentButtonShaper(new ClassicButtonShaper());
						SubstanceLookAndFeel.setCurrentWatermark(new SubstanceBubblesWatermark());
						SubstanceLookAndFeel.setCurrentBorderPainter(new StandardBorderPainter());
						SubstanceLookAndFeel.setCurrentGradientPainter(new StandardGradientPainter());

					} catch (Exception e) {
						System.err.println("Something went wrong!");
					}
					AccackUI frame = new AccackUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AccackUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 762, 459);
		setTitle("网站爆破 V1.0");
		
		
		setComp();
		addEvent();
	}
	
	public void setComp() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("文件");
		menuBar.add(mnNewMenu);
		
		openItem = new JMenuItem("打开文件");
		mnNewMenu.add(openItem);
		
		saveItem = new JMenuItem("保存结果");
		mnNewMenu.add(saveItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		proBar = new JProgressBar();
		proBar.setBounds(10, 10, 628, 30);
		contentPane.add(proBar);
		
		showCur = new JLabel();
		showCur.setBounds(10, 50, 453, 15);
		contentPane.add(showCur);
		showCur.setVisible(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 75, 628, 305);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		stopLab = new JLabel();
		stopLab.setBounds(648, 25, 16, 15);
		contentPane.add(stopLab);
		
		btn = new JButton("爆破");
		btn.setBounds(648, 358, 93, 23);
		contentPane.add(btn);
		
		radioJWC = new JRadioButton("教务处");
		buttonGroup.add(radioJWC);
		radioJWC.setBounds(644, 128, 121, 23);
		contentPane.add(radioJWC);
		
		radio163 = new JRadioButton("163");
		buttonGroup.add(radio163);
		radio163.setBounds(644, 153, 121, 23);
		contentPane.add(radio163);
	}
	
	public void setTextArea(String content) {
		StringBuilder stringBuilder = new StringBuilder(textArea.getText());
		stringBuilder.append(content);
		stringBuilder.append("\n");
		textArea.setText(content);
	}
	
	public void setHacked(List<String> hackedName,List<String> hackedPwd) {
		this.hackedName = hackedName;
		this.hackedPwd = hackedPwd;
	}
	
	public void setProBar(int cur) {
		proBar.setValue(cur);
	}
	
	public void setLab(String name,String pwd) {
		if(!showCur.isVisible()) {
			showCur.setVisible(true);
		}
		showCur.setText("当前用户名："+name+"\t当前密码："+pwd);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btn) {
			new Thread(this).start();
		} else if(e.getSource()==openItem) {
			open();
		} else if(e.getSource()==saveItem) {
			save();
		} else if(e.getSource()==ok_opendlg){
			openDlg.dispose();
		} else if(e.getSource()==btn_name) {
			JFileChooser fChooser = new JFileChooser();
			fChooser.showOpenDialog(openDlg);
			File file = fChooser.getSelectedFile();
			userName = file;
			nameTF1.setText(file.getAbsolutePath());
			
		} else if(e.getSource()==btn_pwd) {
			JFileChooser fChooser = new JFileChooser();
			fChooser.showOpenDialog(openDlg);
			File file = fChooser.getSelectedFile();
			password = file;
			nameTF2.setText(file.getAbsolutePath());
		}
	}
	
	public void addEvent() {
		btn.addActionListener(this);
		openItem.addActionListener(this);
		saveItem.addActionListener(this);
		
		
	}
	
	public void open() {
		openDlg = new JDialog(this, "打开文件", false);
		openDlg.setBounds(getX()+200, getY()+150, 350, 200);
		openDlg.setVisible(true);
		
		JPanel com1 = new JPanel(new FlowLayout());
		nameTF1 = new JTextField(20);
		btn_name = new JButton("选择账号文件");
		com1.add(nameTF1);
		com1.add(btn_name);
		
		JPanel com2 = new JPanel(new FlowLayout());
		nameTF2 = new JTextField(20);
		btn_pwd = new JButton("选择密码文件");
		ok_opendlg = new JButton("确定");
		com2.add(nameTF2);
		com2.add(btn_pwd);
		com2.add(ok_opendlg);
		
		openDlg.getContentPane().add(com1,BorderLayout.NORTH);
		openDlg.getContentPane().add(com2,BorderLayout.CENTER);
		
		openDlg.validate();
		
		btn_name.addActionListener(this);
		btn_pwd.addActionListener(this);
		ok_opendlg.addActionListener(this);
	}
	
	public void save() {
		openDlg = new JDialog(this, "保存文件", false);
		openDlg.setBounds(getX()+300, getY()+150, 400, 200);
		openDlg.setVisible(true);
		
		JPanel com1 = new JPanel(new FlowLayout());
		nameTF1 = new JTextField(20);
		btn_name = new JButton("选择保存文件");
		com1.add(nameTF1);
		com1.add(btn_name);
		
		JPanel com2 = new JPanel(new FlowLayout());
		ok_opendlg = new JButton("确定");
		com2.add(ok_opendlg);
		
		openDlg.getContentPane().add(com1,BorderLayout.NORTH);
		openDlg.getContentPane().add(com2,BorderLayout.CENTER);
		

		btn_name.addActionListener(this);
		ok_opendlg.addActionListener(this);
	}
	
	public int getChoose() {
		if(radio163.isSelected()) {
			return 1;
		} else {
			return 2;
		}
	}
	
	public String getTextArea() {
		return textArea.getText();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Read read = new Read(userName, password, this);
		read.connect();
		
	}
}
