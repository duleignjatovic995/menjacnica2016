package menjacnica.sistemskeoperacije;

import java.util.LinkedList;

import menjacnica.Valuta;

public class SOObrisiValutu {
	public static void izvrsi(Valuta valuta, LinkedList<Valuta> kursnaLista) throws Exception {
		if (!kursnaLista.contains(valuta))
			throw new Exception("Valuta ne postoji u kursnoj listi");

		kursnaLista.remove(valuta);
	}
}
