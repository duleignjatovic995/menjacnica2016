package menjacnica.sistemskeoperacije;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

import menjacnica.Valuta;

public class SOUcitajIzFajla {
	public static void izvrsi(String putanja, LinkedList<Valuta> kursnaLista) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(putanja)));

		kursnaLista = (LinkedList<Valuta>) (in.readObject());

		in.close();
	}
}
