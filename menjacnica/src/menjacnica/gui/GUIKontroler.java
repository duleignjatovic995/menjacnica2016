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
		DodajKursGUI prozor = new DodajKursGUI();
		prozor.setLocationRelativeTo(mainForm.getContentPane());
		prozor.setVisible(true);
	}
	
	public static void prikaziObrisiKursGUI(int row) {
		ObrisiKursGUI prozor = new ObrisiKursGUI(row);
		prozor.setLocationRelativeTo(mainForm.getContentPane());
		prozor.setVisible(true);
	}
	
	public static void prikaziIzvrsiZamenuGUI(int row) {
		IzvrsiZamenuGUI prozor = new IzvrsiZamenuGUI(row);
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
		mainForm.prikaziSveValute();
		
	}


	
	
	/*
	 * Deo kontrolera za formu IzvrsiIzmenuGUI
	 */
	
	public static double izvrsiTransakciju(int valuta, boolean selected, String parseDouble) {
		Valuta v = vratiValutu(valuta);
		return menjacnica.izvrsiTransakciju(v, selected, Double.parseDouble(parseDouble));
	}

	/*
	 * Deo kontrolera za formu ObrisiKursGUI
	 */

	public static void obrisiValutu(int valuta) throws Exception {
		Valuta v = vratiValutu(valuta);
		menjacnica.obrisiValutu(v);		
	}
	
	/*
	 * Ostatak
	 */
	public static double vratiProdajni(int valuta){
		Valuta v = vratiValutu(valuta);
		return v.getProdajni();
	}
	
	public static double vratiKupovni(int valuta){
		Valuta v = vratiValutu(valuta);
		return v.getKupovni();
	}
	
	public static double vratiSrednji(int valuta){
		Valuta v = vratiValutu(valuta);
		return v.getSrednji();
	}
	
	public static String vratiSkraceni(int valuta){
		Valuta v = vratiValutu(valuta);
		return v.getSkraceniNaziv();
	}	
	
	public static String vratiNaziv(int valuta){
		Valuta v = vratiValutu(valuta);
		return v.getNaziv();
	}
	
	public static int vratiSifru(int valuta){
		Valuta v = vratiValutu(valuta);
		return v.getSifra();
	}
	
	public static Valuta vratiValutu(int index){
		MenjacnicaTableModel model = (MenjacnicaTableModel)mainForm.getTable().getModel();
		return model.vratiValutu(index);
	}

	
}
