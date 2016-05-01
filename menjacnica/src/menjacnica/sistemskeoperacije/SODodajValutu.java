package menjacnica.sistemskeoperacije;

import java.util.LinkedList;

import menjacnica.Valuta;

public class SODodajValutu {

	public static void izvrsi(Valuta valuta, LinkedList<Valuta> kursnaLista) throws Exception {
		if (valuta == null)
			throw new Exception("Valuta ne sme biti null");

		if (kursnaLista.contains(valuta))
			throw new Exception("Valuta je vec uneta u kursnu listu");

		kursnaLista.add(valuta);
	}
}
