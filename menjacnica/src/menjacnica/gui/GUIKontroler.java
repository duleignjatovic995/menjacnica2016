package menjacnica.gui;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import menjacnica.Menjacnica;
import menjacnica.Valuta;
import menjacnica.gui.models.MenjacnicaTableModel;
import menjacnica.interfejs.MenjacnicaInterface;

public class GUIKontroler {

	private static MenjacnicaGUI mainForm;
	private static MenjacnicaInterface menjacnica;
	
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
	
	public static void sacuvajUFajl() throws FileNotFoundException, IOException {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(mainForm.getContentPane());

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			menjacnica.sacuvajUFajl(file.getAbsolutePath());
		}
	}

	public static void ucitajIzFajla() throws FileNotFoundException, ClassNotFoundException, IOException {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(mainForm.getContentPane());

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			menjacnica.ucitajIzFajla(file.getAbsolutePath());
			mainForm.prikaziSveValute();
		}
	}

	public static List<Valuta> vratiKursnuListu() {
		return menjacnica.vratiKursnuListu();
	}

	public static void prikaziDodajKursGUI() {
		DodajKursGUI prozor = new DodajKursGUI(mainForm);
		prozor.setLocationRelativeTo(mainForm.getContentPane());
		prozor.setVisible(true);
	}
	
	public static void prikaziObrisiKursGUI(MenjacnicaTableModel model, int row) {
		ObrisiKursGUI prozor = new ObrisiKursGUI(model.vratiValutu(row));
		prozor.setLocationRelativeTo(mainForm.getContentPane());
		prozor.setVisible(true);
	}
	
	public static void prikaziIzvrsiZamenuGUI(MenjacnicaTableModel model, int row) {
		IzvrsiZamenuGUI prozor = new IzvrsiZamenuGUI(model.vratiValutu(row));
		prozor.setLocationRelativeTo(mainForm.getContentPane());
		prozor.setVisible(true);
	}
	
	public static void prikaziSveValute() {
		mainForm.prikaziSveValute();		
	}
	
	/*
	 * Deo kontrolera za formu DodajKursGUI
	 */
	public static void dodajValutu(String naziv, String skraceniNaziv, String sifra, String prodajni, String kupovni,
			String srednji) throws Exception {
		Valuta valuta = new Valuta();
		// Punjenje podataka o valuti
		valuta.setNaziv(naziv);
		valuta.setSkraceniNaziv(skraceniNaziv);
		valuta.setSifra(Integer.parseInt(sifra));
		valuta.setProdajni(Double.parseDouble(prodajni));
		valuta.setKupovni(Double.parseDouble(kupovni));
		valuta.setSrednji(Double.parseDouble(srednji));
		menjacnica.dodajValutu(valuta);
	}


	
	
	/*
	 * Deo kontrolera za formu IzvrsiIzmenuGUI
	 */
	
	public static double izvrsiTransakciju(Valuta valuta, boolean selected, String parseDouble) {		
		return menjacnica.izvrsiTransakciju(valuta, selected, Double.parseDouble(parseDouble));
	}

	public static double vratiProdajni(Valuta valuta){
		return valuta.getProdajni();
	}
	
	public static double vratiKupovni(Valuta valuta){
		return valuta.getKupovni();
	}
	
	public static double vratiSrednji(Valuta valuta){
		return valuta.getSrednji();
	}
	
	public static String vratiSkraceni(Valuta valuta){
		return valuta.getSkraceniNaziv();
	}	
	
	public static String vratiNaziv(Valuta valuta){
		return valuta.getNaziv();
	}
	
	public static int vratiSifru(Valuta valuta){
		return valuta.getSifra();
	}
	
	/*
	 * Deo kontrolera za formu ObrisiKursGUI
	 */

	public static void obrisiValutu(Valuta valuta) throws Exception {
		menjacnica.obrisiValutu(valuta);
		
	}

	
}
