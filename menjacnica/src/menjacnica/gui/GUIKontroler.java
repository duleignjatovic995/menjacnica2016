package menjacnica.gui;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import menjacnica.Menjacnica;
import menjacnica.Valuta;
import menjacnica.gui.models.MenjacnicaTableModel;
import menjacnica.interfejs.MenjacnicaInterface;

public class GUIKontroler {

	private static MenjacnicaGUI mainForm;
	private static MenjacnicaInterface menjacnica;
	private static ObrisiKursGUI obrisiKurs;
	private static DodajKursGUI dodajKurs;
	private static IzvrsiZamenuGUI izvrsiIzmenu;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menjacnica = new Menjacnica();
					mainForm = new MenjacnicaGUI();
					mainForm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void ugasiAplikaciju() {
		int opcija = JOptionPane.showConfirmDialog(mainForm.getContentPane(),
				"Da li ZAISTA zelite da izadjete iz apliacije", "Izlazak",
				JOptionPane.YES_NO_OPTION);

		if (opcija == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	public static void prikaziAboutProzor(){
		JOptionPane.showMessageDialog(mainForm.getContentPane(),
				"Autor: Bojan Tomic, Verzija 1.0", "O programu Menjacnica",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	/*
	 * Deo kontrolera za glavnu formu.
	 */
	
	public static void sacuvajUFajl(){
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(mainForm.getContentPane());

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				menjacnica.sacuvajUFajl(file.getAbsolutePath());
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(mainForm.getContentPane(), "Fajl nije pronadjen", "Greska",
						JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(mainForm.getContentPane(), "Greska pri upisivanju u fajl", "Greska",
						JOptionPane.ERROR_MESSAGE);
			} catch (Exception e1){
				JOptionPane.showMessageDialog(mainForm.getContentPane(), "Doslo je do nepoznate greske pri upisifanju u fajl",
						"Greska", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public static void ucitajIzFajla(){
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(mainForm.getContentPane());

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				menjacnica.ucitajIzFajla(file.getAbsolutePath());
				mainForm.prikaziSveValute();
			} catch (ClassNotFoundException | IOException e) {
				JOptionPane.showMessageDialog(mainForm.getContentPane(), "Greska pri ucitavanju iz fajla",
						"Greska", JOptionPane.ERROR_MESSAGE);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(mainForm.getContentPane(), "Doslo je do nepoznate greske pri ucitavanju",
						"Greska", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}

	public static List<Valuta> vratiKursnuListu() {
		return menjacnica.vratiKursnuListu();
	}

	public static void prikaziDodajKursGUI() {
		dodajKurs = new DodajKursGUI();
		dodajKurs.setLocationRelativeTo(mainForm.getContentPane());
		dodajKurs.setVisible(true);
	}
	
	public static void prikaziObrisiKursGUI(MenjacnicaTableModel model, int row) {
		obrisiKurs = new ObrisiKursGUI(model.vratiValutu(row));
		obrisiKurs.setLocationRelativeTo(mainForm.getContentPane());
		obrisiKurs.setVisible(true);
	}
	
	public static void prikaziIzvrsiZamenuGUI(MenjacnicaTableModel model, int row) {
		izvrsiIzmenu = new IzvrsiZamenuGUI(model.vratiValutu(row));
		izvrsiIzmenu.setLocationRelativeTo(mainForm.getContentPane());
		izvrsiIzmenu.setVisible(true);
	}
	
	
	/*
	 * Deo kontrolera za formu DodajKursGUI
	 */
	public static void dodajValutu(String naziv, String skraceniNaziv, String sifra, String prodajni, String kupovni,
			String srednji) {
		Valuta valuta = new Valuta();
		// Punjenje podataka o valuti
		valuta.setNaziv(naziv);
		valuta.setSkraceniNaziv(skraceniNaziv);
		valuta.setSifra(Integer.parseInt(sifra));
		valuta.setProdajni(Double.parseDouble(prodajni));
		valuta.setKupovni(Double.parseDouble(kupovni));
		valuta.setSrednji(Double.parseDouble(srednji));
		try {
			menjacnica.dodajValutu(valuta);
			mainForm.prikaziSveValute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(dodajKurs.getContentPane(), e.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	
	public static void prikaziGresku(JPanel parent, String errorMessage) {
		JOptionPane.showMessageDialog(parent, errorMessage, "Greska", JOptionPane.ERROR_MESSAGE);
	}

	
	
	/*
	 * Deo kontrolera za formu IzvrsiIzmenuGUI
	 */
	
	public static String izvrsiTransakciju(Valuta valuta, boolean selected, String parseDouble) {
		double transakcija = menjacnica.izvrsiTransakciju(valuta, selected, Double.parseDouble(parseDouble));
		return String.valueOf(transakcija);
	}

	/*
	 * Deo kontrolera za formu ObrisiKursGUI
	 */

	public static void obrisiValutu(Valuta valuta) {
		try {
			menjacnica.obrisiValutu(valuta);
			mainForm.prikaziSveValute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(obrisiKurs.getContentPane(), e.getMessage(),
					"Greska", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/*
	 * Ostatak
	 */
	public static String vratiProdajni(Valuta valuta){
		return String.valueOf(valuta.getProdajni());
	}
	
	public static String vratiKupovni(Valuta valuta){
		return String.valueOf(valuta.getKupovni());
	}
	
	public static String vratiSrednji(Valuta valuta){
		return String.valueOf(valuta.getSrednji());
	}
	
	public static String vratiSkraceni(Valuta valuta){
		return valuta.getSkraceniNaziv();
	}	
	
	public static String vratiNaziv(Valuta valuta){
		return valuta.getNaziv();
	}
	
	public static String vratiSifru(Valuta valuta){
		return String.valueOf(valuta.getSifra());
	}
	
//	public static Valuta vratiValutu(int index){
//		MenjacnicaTableModel model = new MenjacnicaTableModel();
//		return model.vratiValutu(index);
//	}

	
}
