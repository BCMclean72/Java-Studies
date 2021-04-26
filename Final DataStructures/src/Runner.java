import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import datastructures.Map.MapReverseIterator;
import datastructures.SortedMap;
import datastructures.SortedMap.SortedMapIterator;
import datastructures.SortedMap.SortedMapReverseIterator;

/**
 * 
 * @author Brad McLean 0103571 This GUI tests SortedMap.java 
 * against java,s built-in tree map.
 *
 */

public class Runner extends JFrame {

	private SortedMap sm;
	private TreeMap tm;
	private TreeMap reverseMap;
	private SortedMapIterator sitr;
	private Iterator jitr, rJitr;
	private SortedMapReverseIterator rSitr;

	private JPanel mainLayout;
	private JPanel entryPane;
	private JLabel entryLabel;
	private JTextField entryText;
	private JPanel TextPane;
	private JButton solveButton;
	private JPanel areaPane;
	private JTextArea solutionText;

	public Runner() {

		sm = new SortedMap<Integer, Integer>();
		tm = new TreeMap();
		Collection tree = tm.entrySet();
		jitr = tree.iterator();
		sitr = sm.new SortedMapIterator();
		entryPane = new JPanel();

		solveButton = new JButton("Go");
		solveButton.addActionListener(new solveListener());

		entryLabel = new JLabel("Enter the size of the map.");
		entryText = new JTextField(10);
		entryPane.add(entryLabel);
		entryPane.add(entryText);
		entryPane.add(solveButton);
		solutionText = new JTextArea(50, 30);
		areaPane = new JPanel();
		areaPane.add(solutionText);
		TextPane = new JPanel();
		TextPane.setBorder(new TitledBorder(null, "Results", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		TextPane.add(areaPane);

		mainLayout = new JPanel(new BorderLayout());
		mainLayout.add(entryPane, BorderLayout.PAGE_START);
		mainLayout.add(TextPane);

		add(mainLayout);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 1000);
		setTitle("Sorter Map Runner");
		setResizable(true);
	}

	public class solveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			sm = new SortedMap<Integer, Integer>();
			tm = new TreeMap();
			int size = 0;
			try {
				size = Integer.parseInt(entryText.getText());
			} catch (NumberFormatException n) {
				solutionText.setText("ENTER AN INTEGER");
			}
			ArrayList<Double> list = new ArrayList();

			solutionText.setText("RANDOM ENTRY_____\n");
			for (int idx = 0; idx < size; ++idx)
				list.add(Math.random());
			double key;

			// run my map
			long time1 = System.currentTimeMillis();
			for (int idx = 0; idx < size; ++idx) {
				key = list.get(idx);
				sm.insert(key, 0);
			}
			time1 = System.currentTimeMillis() - time1;

			// run java's map
			long time2 = System.currentTimeMillis();
			for (int idx = 0; idx < size; ++idx) {
				key = list.get(idx);
				tm.put(key, 0);
			}
			time2 = System.currentTimeMillis() - time2;
			solutionText.append("My time for Random Entry: " + time1 + " ms.\n");
			solutionText.append("Java's time for Random Entry: " + time2 + " ms.\n");
			solutionText.append("Java is faster by " + Math.abs(time1 - time2) + " ms.\n\n");

			time1 = System.currentTimeMillis();
			sitr = sm.begin();
			while (sitr.hasNext())
				sitr.next();
			time1 = System.currentTimeMillis() - time1;

			time2 = System.currentTimeMillis();
			while (jitr.hasNext())
				jitr.next();
			time2 = System.currentTimeMillis() - time2;

			solutionText.append("My time for forward iteration is: " + time1 + " ms.\n");
			solutionText.append("Java's time for forward iteration is: " + time2 + " ms. \n");
			solutionText.append("Java is faster by " + Math.abs(time1 - time2) + " ms.\n\n");

			time1 = System.currentTimeMillis();
			rSitr = sm.rbegin();
			while (rSitr.hasNext())
				rSitr.next();
			time1 = System.currentTimeMillis() - time1;

			rJitr = tm.descendingMap().entrySet().iterator();

			time2 = System.currentTimeMillis();
			while (rJitr.hasNext())
				rJitr.next();
			time2 = System.currentTimeMillis() - time2;

			solutionText.append("My time for reverse iteration is: " + time1 + " ms.\n");
			solutionText.append("Java's time for reverse iteration is: " + time2 + " ms. \n");
			solutionText.append("Java is faster by " + Math.abs(time1 - time2) + " ms.\n\n");

			solutionText.append("FORWARD ENTRY_____\n");
			sm.clear();
			tm.clear();

			time1 = System.currentTimeMillis();
			for (int idx = 0; idx < size; ++idx)
				sm.insert((idx + 1), 0);
			time1 = System.currentTimeMillis() - time1;

			// run java's map
			time2 = System.currentTimeMillis();
			for (int idx = 0; idx < size; ++idx)
				tm.put((idx + 1), 0);
			time2 = System.currentTimeMillis() - time2;
			solutionText.append("My time for Forward: " + time1 + " ms.\n");
			solutionText.append("Java's time for Forward: " + time2 + " ms.\n");
			solutionText.append("Java is faster by " + Math.abs(time1 - time2) + " ms.\n\n");

			time1 = System.currentTimeMillis();
			sitr = sm.begin();
			while (sitr.hasNext())
				sitr.next();
			time1 = System.currentTimeMillis() - time1;

			time2 = System.currentTimeMillis();
			while (jitr.hasNext())
				jitr.next();
			time2 = System.currentTimeMillis() - time2;

			solutionText.append("My time for forward iteration is: " + time1 + " ms.\n");
			solutionText.append("Java's time for forward iteration is: " + time2 + " ms. \n");
			solutionText.append("Java is faster by " + Math.abs(time1 - time2) + " ms.\n\n");

			time1 = System.currentTimeMillis();
			rSitr = sm.rbegin();
			while (rSitr.hasNext())
				rSitr.next();
			time1 = System.currentTimeMillis() - time1;

			rJitr = tm.descendingMap().entrySet().iterator();

			time2 = System.currentTimeMillis();
			while (rJitr.hasNext())
				rJitr.next();
			time2 = System.currentTimeMillis() - time2;

			solutionText.append("My time for reverse iteration is: " + time1 + " ms.\n");
			solutionText.append("Java's time for reverse iteration is: " + time2 + " ms. \n");
			solutionText.append("Java is faster by " + Math.abs(time1 - time2) + " ms.\n\n");

			solutionText.append("REVERSE ENTRY_____\n");
			sm.clear();
			tm.clear();

			time1 = System.currentTimeMillis();
			for (int idx = size; idx > 0; --idx)
				sm.insert((idx), 0);
			time1 = System.currentTimeMillis() - time1;

			// run java's map
			time2 = System.currentTimeMillis();
			for (int idx = size; idx > 0; --idx)
				tm.put((idx), 0);
			time2 = System.currentTimeMillis() - time2;

			solutionText.append("My time for Reverse Entry: " + time1 + " ms.\n");
			solutionText.append("Java's time for Reverse Entry: " + time2 + " ms.\n");
			solutionText.append("Java is faster by " + Math.abs(time1 - time2) + " ms.\n\n");

			time1 = System.currentTimeMillis();
			sitr = sm.begin();
			while (sitr.hasNext())
				sitr.next();
			time1 = System.currentTimeMillis() - time1;

			time2 = System.currentTimeMillis();
			while (jitr.hasNext())
				jitr.next();
			time2 = System.currentTimeMillis() - time2;

			solutionText.append("My time for forward iteration is: " + time1 + " ms.\n");
			solutionText.append("Java's time for forward iteration is: " + time2 + " ms. \n");
			solutionText.append("Java is faster by " + Math.abs(time1 - time2) + " ms.\n\n");

			time1 = System.currentTimeMillis();
			rSitr = sm.rbegin();
			while (rSitr.hasNext())
				rSitr.next();
			time1 = System.currentTimeMillis() - time1;

			rJitr = tm.descendingMap().entrySet().iterator();

			time2 = System.currentTimeMillis();
			while (rJitr.hasNext())
				rJitr.next();
			time2 = System.currentTimeMillis() - time2;

			solutionText.append("My time for reverse iteration is: " + time1 + " ms.\n");
			solutionText.append("Java's time for reverse iteration is: " + time2 + " ms. \n");
			solutionText.append("Java is faster by " + Math.abs(time1 - time2) + " ms.\n\n");
		}

	}

	public static void main(String[] args) {
		new Runner();
	}

}
