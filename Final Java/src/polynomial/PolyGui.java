package polynomial;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
/**
 * 
 * @author Brad McLean
 * Creates an application to perform operations on polynomials.
 * This application will prompt the user to get a file with lists of doubles.
 * The app then creates polynomials in "x" from any valid list in the file.
 * The user may then add, subtract, or multiply any two polynomials.
 * They can also solve any polynomial for a value of x.
 * Users can also create their own polynomials.
 *
 */
public class PolyGui extends JFrame {
	private ArrayList<Polynomial> polys;
	Term[] termList;
	private Polynomial current;
	private JPanel mainLayout, infoPane, outputPane, displayPane,
	   				calcPane, buttonPane, choicePane, result, result2, createPane,
					newPoly, addTerm, solvePane, storePane, showPolyPane;
	private JMenuBar menuBar;
	private JMenu file; 
	private JMenuItem exit, open;
	private JTextArea output, resultText, resultText2, showPoly;
	private JLabel opLabel, resultLabel, resultLabel2, createLabel, coeff, exp, 
				solveLabel, storeLabel, pickLabel;
	private JTextField poly1, poly2, coeffBox, expBox, xValue, solveBox;
	private JButton add, subtract, multiply, solveButton, newButton, addTermButton, saveButton;
	
	/**
	 * Constructor initializes all the elements of the GUI.
	 */
	public PolyGui() {
		current = null;
		showPolyPane = new JPanel(new FlowLayout());
		saveButton = new JButton("Save");
		saveButton.setEnabled(false);
		saveButton.addActionListener(new StoreListener());
		storeLabel = new JLabel ("Store your polynomial: ");
		storePane = new JPanel(new FlowLayout());
		showPoly = new JTextArea(8,20);
		
		pickLabel = new JLabel("Pick a polynomial");
		solveBox = new JTextField(5);
		xValue = new JTextField(5);
		solvePane = new JPanel(new FlowLayout());
		solveLabel = new JLabel("Enter a value for x");
		solveButton = new JButton("Solve");
		solveButton.setEnabled(false);
		solveButton.addActionListener(new SolveListener());
		
		newButton = new JButton("New");
		addTermButton = new JButton("Add Term");
		addTermButton.setEnabled(false);

		newPoly = new JPanel(new FlowLayout());
		addTerm = new JPanel(new FlowLayout());
		coeffBox = new JTextField(5);
		expBox = new JTextField(5);
		displayPane = new JPanel(new GridLayout(1,3));
		createPane = new JPanel(new GridLayout(4,1));
		createPane.setBorder(new TitledBorder(null, "Create a Polynomial", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		createLabel = new JLabel("Create your own polynomial.");
		coeff = new JLabel("Coefficient: ");
		exp = new JLabel("Exponent: ");
		
		storePane.add(storeLabel);
		storePane.add(saveButton);
		
		solvePane.add(pickLabel);
		solvePane.add(solveBox);
		solvePane.add(solveLabel);
		solvePane.add(xValue);
		solvePane.add(solveButton);
		
		newPoly.add(createLabel);
		newPoly.add(newButton);
		
		
		addTerm.add(coeff);
		addTerm.add(coeffBox);
		addTerm.add(exp);
		addTerm.add(expBox);
		addTerm.add(addTermButton);
		
		showPolyPane.add(showPoly);
		createPane.add(newPoly);
		createPane.add(addTerm);
		createPane.add(storePane);
		createPane.add(showPolyPane);
		
		add = new JButton("Add");
		add.setEnabled(false);
		subtract = new JButton("Subtract");
		subtract.setEnabled(false);
		multiply = new JButton("Multiply");
		multiply.setEnabled(false);
		poly1 = new JTextField(5);
		poly2 = new JTextField(5);
		opLabel = new JLabel("Chose 2 polynomials to operate on."); 
		choicePane = new JPanel(new FlowLayout());
		choicePane.add(opLabel);
		choicePane.add(poly1);
		choicePane.add(poly2);
		calcPane = new JPanel(new GridLayout(5,1));
		calcPane.setBorder(new TitledBorder(null, "Operations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		buttonPane= new JPanel(new FlowLayout());
		
		add.addActionListener(new OpListener());
		subtract.addActionListener(new OpListener());
		multiply.addActionListener(new OpListener());
		newButton.addActionListener(new NewListener());
		addTermButton.addActionListener(new newTermListener());
		
		buttonPane.add(add);
		buttonPane.add(subtract);
		buttonPane.add(multiply);
		
		resultLabel = new JLabel("Result!!");
		resultText = new JTextArea(1,10);
		result = new JPanel(new FlowLayout());
		result.add(resultLabel);
		result.add(resultText);
		
		resultLabel2 = new JLabel("Result!!");
		resultText2 = new JTextArea(1,10);
		result2 = new JPanel(new FlowLayout());
		result2.add(resultLabel2);
		result2.add(resultText2);

		calcPane.add(choicePane);
		calcPane.add(buttonPane);
		calcPane.add(result);
		calcPane.add(solvePane);
		calcPane.add(result2);

		outputPane = new JPanel(new FlowLayout());
		output = new JTextArea(20,40);
		outputPane.add(output);
		polys = new ArrayList<Polynomial>();
		infoPane = new JPanel(new GridLayout(1,2));
		infoPane.setBorder(new TitledBorder(null, "Polynomials", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		
		menuBar = new JMenuBar();
		file  = new JMenu("File");
		open  = new JMenuItem("Open");
		open.addActionListener(new fileListener());
		exit  = new JMenuItem("Exit");
		exit.addActionListener(new exitListener());
		file.add(open);
		file.add(exit);
		menuBar.add(file);
		
		infoPane.add(outputPane);
		output.setText("Welcome to the Polynomial Calculator.\n"+
						"Use File/Open to choose a file with lists of coefficients.\n"+
						"Numbers must be separated by a comma or spaces, but not both.");
		mainLayout = new JPanel(new BorderLayout());
		mainLayout.add(menuBar,BorderLayout.PAGE_START);
		mainLayout.add(displayPane,BorderLayout.CENTER);
		displayPane.add(infoPane);
		displayPane.add(calcPane);
		displayPane.add(createPane);
		add(mainLayout);
	}

	
	/**
	 * Listener for the three operations, add, subtract, multiply.
	 *
	 */
	public class OpListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int index1 = 0;
			int index2 = 0;
			boolean errorCaught = false;
			try {
				index1 = Integer.parseInt(poly1.getText());
				index2 = Integer.parseInt(poly2.getText());
			} catch(NumberFormatException n) {
				JOptionPane.showMessageDialog(null,"Enter one of the numbers from the polynomial list.","File Error",JOptionPane.ERROR_MESSAGE);
				errorCaught = true;
			}
			
			if (!errorCaught) {
				int numP = polys.size();
				if (index1 <= 0 || index2 <= 0 || index1 > numP || index2 > numP ) {
					JOptionPane.showMessageDialog(null,"Enter one of the numbers from the polynomial list.","File Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
					
				Polynomial p1 = polys.get(index1 - 1);
				Polynomial p2 = polys.get(index2 - 1);
				Polynomial p3 = new Polynomial();
				if (e.getSource()==add)
					p3 = p1.add(p2);
				else if (e.getSource()==subtract)
					p3 = p1.subtract(p2);
				else
					p3 = p1.multiply(p2);
				resultText.setText(p3.toString());
			}
		}		
	}
	
	
	/**
	 * Listener for the exit menu item.
	 * 
	 */
	public class exitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}		
	}
	
	/**
	 * 
	 * Listener for the file menu option. Listener will create
	 * polynomials and add them to an arrayList of polynomials
	 * Polynomials are created from lists of numbers found in 
	 * the file. Numbers can be separated by spaces or commas 
	 * but not both.
	 * 
	 *
	 */
	public class fileListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			polys = new ArrayList<Polynomial>();
			JFileChooser inChooser = new JFileChooser();
			inChooser.showOpenDialog(null);
			Scanner fileIn = null;
			Boolean errorCaught = false;
			
			try {
				fileIn = new Scanner(inChooser.getSelectedFile());
			} catch (FileNotFoundException f) {
				JOptionPane.showMessageDialog(null,"This file does not exist","File Error",JOptionPane.ERROR_MESSAGE);
				errorCaught = true;
			}catch (NullPointerException n) {
				JOptionPane.showMessageDialog(null,"Please select a file.","File Error",JOptionPane.ERROR_MESSAGE);
				errorCaught = true;
			}
			
			if(!errorCaught) {
				add.setEnabled(true);
				subtract.setEnabled(true);
				multiply.setEnabled(true);
				solveButton.setEnabled(true);
				createPolyArray(fileIn);
				printPolys();
			}			
		}		
	}
	
	
	//prints out the array of polynomials to the output box
	private void printPolys() {
		output.setText("Here are your Polynimials\n\n");
		if (!polys.isEmpty())
			for (int i = 0; i < polys.size(); ++i) 
				output.append((i+1) + ".     " + polys.get(i) + "\n\n");
	}
	
	
	/**
	 *Listener for the Solve button to solve a chosen polynomial
	 *for the given value for x.
	 */
	public class SolveListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int index = 0;
			double x = 0;
			double answer = 0;
			boolean errorCaught = false;
			try {
				index = Integer.parseInt(solveBox.getText());
			} catch(NumberFormatException n) {
				JOptionPane.showMessageDialog(null,"Enter one of the numbers from the polynomial list.","File Error",JOptionPane.ERROR_MESSAGE);
				errorCaught = true;
			}
			try {
				x  = Double.parseDouble(xValue.getText());			
			} catch(NumberFormatException n) {
				JOptionPane.showMessageDialog(null,"The value for x must be a real number.","File Error",JOptionPane.ERROR_MESSAGE);
				errorCaught = true;
			}
			
			if (!errorCaught) {
				answer = polys.get(index - 1).solveFor(x);
				resultText2.setText("" + answer);
			}			
		}		
	}
	
	/**
	 * Listener for the New button
	 * Creates a new empty polynomial.
	 * @author Brad M
	 *
	 */
	public class NewListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			addTermButton.setEnabled(true);
			current = new Polynomial();
			showPoly.setText("New polynomial created.");
		}		
	}
	
	
	/**
	 * Listener for the new term button. 
	 * Creates a new term or changes a current term based on the coefficient and exponent provided.
	 *
	 */
	public class newTermListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			double co = 0;
			int ex = 0;
			boolean errorCaught = false;
			try {
				co = Double.parseDouble(coeffBox.getText());
			} catch(NumberFormatException n) {
				JOptionPane.showMessageDialog(null,"Enter a real value for the coefficient.","File Error",JOptionPane.ERROR_MESSAGE);
				errorCaught = true;
			}
			try {
				ex = Integer.parseInt(expBox.getText());		
			} catch(NumberFormatException n) {
				JOptionPane.showMessageDialog(null,"Enter a positive integer for the exponent.","File Error",JOptionPane.ERROR_MESSAGE);
				errorCaught = true;
			}
			if (ex < 0) {
				JOptionPane.showMessageDialog(null,"Enter a positive integer for the exponent.","File Error",JOptionPane.ERROR_MESSAGE);
				errorCaught = true;
			}
			
			if (!errorCaught) {
				saveButton.setEnabled(true);
				current.addTerm(new Term("x",ex,co));
				showPoly.setText(current.toString());
			}
		}		
	}
	
	/**
	 * Listener for the Save button. 
	 * Adds the newly created polynomial into the arrayList of previous polynomials
	 */
	public class StoreListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(current!=null && !current.isEmpty()) {
				Polynomial newP = new Polynomial(current);
				polys.add(newP);
				current = null;
				printPolys();
			}
		}		
	}
	
	
	//Method used with the fileIn from the FileListener.
	//This method grabs an appropriate array of doubles
	//and adds them as terms into a polynomial.
	//if a string is not a valid double. The polynimial
	//is not created.
	private void createPolyArray(Scanner fileIn) {
		
		Polynomial p = new Polynomial();
		while (fileIn.hasNextLine()) {
			String line = fileIn.nextLine();
			String[] coeffs = line.split("\\s+|,"); 
			double number;
			p = new Polynomial();
			Boolean foundDouble = true;
			for (int i = 0; i < coeffs.length; ++i) {
				number = 0.0;
				try {
					number = Double.parseDouble(coeffs[i]);
				} catch (NumberFormatException n) {
					foundDouble = false;
				}
				if (foundDouble) {
					p.addTerm(new Term("x",i,number));									
				}
			
			}
			if(foundDouble)
				polys.add(p);
		}
	}
	
	
	public static void main(String[] args) {
		PolyGui pg = new PolyGui();
		pg.setVisible(true);
		pg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
		pg.setTitle("Polynomial Interface");
		pg.setResizable(true);
		pg.setSize(1500,500);

	}

}
