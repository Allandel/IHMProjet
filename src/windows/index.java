package windows;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

public class index extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4845003394650059301L;
	private JPanel paneLbl;
	private JLabel lblYourWorkspaceIs;
	protected JPanel contentPane;
	protected JScrollPane container;
	private Color RGB;
	private Color GreyLevel;
	private index info = this;
	protected ArrayList<PanelCouleur> couleurTotal = new ArrayList<>();
	/**
	 * Create the frame.
	 */
	public index() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(index.class.getResource("/color-circle.png")));
		setBackground(Color.BLACK);
		setTitle("Grey Level Comparator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("     File     ");
		menuBar.add(mnFile);

		JMenuItem mntmFile = new JMenuItem("Add new color");
		mntmFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				Colorchooser newcouleur = new Colorchooser();
				newcouleur.setParent(info);
				newcouleur.setLocationRelativeTo(contentPane);

			}
		});
		mnFile.add(mntmFile);

		JMenuItem mntmNewMenuItem = new JMenuItem("Compare two colors");
		mnFile.add(mntmNewMenuItem);
		JSeparator separator = new JSeparator();
		mntmNewMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(couleurTotal.isEmpty()){
					JOptionPane.showMessageDialog(contentPane, "The Color Area is Empty!");
				}else if(couleurTotal.size() == 1 ){
					JOptionPane.showMessageDialog(contentPane, "You Must add at least 2 colors to do that!");
				}else{
					ListePanel liste = new ListePanel(couleurTotal);
					liste.setLocationRelativeTo(null);
				}	
			}
		});
		mnFile.add(separator);

		JMenuItem mntmExportTotxt = new JMenuItem("Export to .txt");
		mntmExportTotxt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if(!couleurTotal.isEmpty()){
					try{
						int cpt = 0;
						JFileChooser jfc = new JFileChooser();
						jfc.setDialogTitle("Select the place to export");
						jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						jfc.showOpenDialog(contentPane);
						String path = jfc.getSelectedFile().toString();
						path += "\\exportColor.txt";
						System.out.println(path);
						File fi = new File(path);
						FileWriter flux;
						try {
							flux = new FileWriter(fi);
							BufferedWriter fichier = new BufferedWriter(flux);
							for(PanelCouleur ar : couleurTotal){
								//TODO
								flux.write(ar.toString());
								cpt++;
								flux.write("\r\n");

							}
							flux.close();
							fichier.close();
						} catch (IOException e1){
							System.out.println("Is it too late now to say sooooooooooooooooory ??");
							e1.printStackTrace();
						}


					}catch(NullPointerException e1){

					}
				}else{
					JOptionPane.showMessageDialog(contentPane, "You must at least add 1 color to export something");
				}

			}

		});
		mnFile.add(mntmExportTotxt);

		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);

		JMenu mnEdit = new JMenu("     Edit     ");
		menuBar.add(mnEdit);

		JMenuItem mntmCloseAll = new JMenuItem("Remove All", new ImageIcon(Toolkit.getDefaultToolkit().getImage(index.class.getResource("/poubelle.png"))));
		mntmCloseAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.removeAll();
				isEmpty();
				couleurTotal.removeAll(couleurTotal);
				validate();
				repaint();
			}
		});
		mnEdit.add(mntmCloseAll);


		contentPane = new JPanel();
		container = new JScrollPane();
		container.setViewportView(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		setContentPane(container);
		FirstTime();
		isEmpty();
	}

	private void FirstTime() {
		paneLbl = new JPanel();
		lblYourWorkspaceIs = new JLabel("Your workspace is empty. File > Add new color, to start !");
	}

	public void isEmpty() {
		@SuppressWarnings("unused")
		int compNum;
		if((compNum = contentPane.getComponentCount()) == 0 ){
			lblYourWorkspaceIs.setFont(new Font("Calibri", Font.PLAIN, 24));
			lblYourWorkspaceIs.setForeground(Color.LIGHT_GRAY);
			lblYourWorkspaceIs.setOpaque(true);
			lblYourWorkspaceIs.setBackground(Color.WHITE);
			paneLbl.add(lblYourWorkspaceIs);
			paneLbl.setBackground(Color.WHITE);
			contentPane.add(paneLbl);
			GridBagLayout gbl_contentPane = new GridBagLayout();
			gbl_contentPane.columnWidths = new int[]{0};
			gbl_contentPane.rowHeights = new int[]{0};
			gbl_contentPane.columnWeights = new double[]{Double.MIN_VALUE};
			gbl_contentPane.rowWeights = new double[]{Double.MIN_VALUE};
			contentPane.setLayout(gbl_contentPane);
			PanelCouleur.resetcpt();
		}else{ 
			//		System.out.println("Hola Chica");
			Container parent = lblYourWorkspaceIs.getParent();
			try{
				parent.remove(lblYourWorkspaceIs);
				super.remove(paneLbl);
				parent.getParent().remove(parent);
				parent.validate();
				parent.repaint();
			}catch(NullPointerException ex){

			}
			WrapLayout test2 = new WrapLayout();
			contentPane.setLayout(test2);

		}
	}

	public void setRGB(Color color){
		this.RGB = color;
	}
	//
	//public String toString(){
	//	return RGB.toString() + " "+ GreyLevel.toString();
	//}

	public void addCanvas(Color rgb, Color Grey){

		PanelCouleur pane = new PanelCouleur(rgb, Grey);
		pane.setTextClr("RGB: "+RGB.getRed()+", "+RGB.getGreen()+", "+RGB.getBlue());
		pane.setTextGry("Gris:"+Grey.getRed());
		pane.setParent(info);
		couleurTotal.add(pane);
		contentPane.add(pane);
		contentPane.validate();
	}

	public void setGreyLevel(){
		int r, g, b, res;
		r = RGB.getRed();
		g = RGB.getGreen();
		b = RGB.getBlue();
		res =(int) (0.3*r+0.59*g+0.11*b);
		this.GreyLevel = new Color(res, res ,res);
	}

	public Color getRGB(){
		return this.RGB;
	}

	public Color getGrey(){
		return this.GreyLevel;
	}
}
