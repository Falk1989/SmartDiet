package enumerativi;

import java.util.GregorianCalendar;

/**
 * @author Cristian
 *
 */
public enum Giorni {

	/**
	 * definizione delle costanti enumerative
	 */
	GENNAIO("Gennaio", 0), FEBBRAIO("Febbraio", 1), MARZO("Marzo", 2), APRILE(
			"Aprile", 3), MAGGIO("Maggio", 4), GIUGNO("Giugno", 5), LUGLIO(
			"Luglio", 6), AGOSTO("Agosto", 7), SETTEMBRE("Settembre", 8), OTTOBRE(
			"Ottobre", 9), NOVEMBRE("Novembre", 10), DICEMBRE("Dicembre", 11);

	/**
	 * nome -> campo che conterrà il nome del mese in oggetto in minuscolo con
	 * la prima lettera maiuscola numero -> campo che conterrà il numero del
	 * mese corrispondente al nome (es. Gennaio => 1 )
	 */
	private String nome;
	private int numero;

	/**
	 * costruttore a due parametri
	 */
	Giorni(String nome, int numero) {
		this.nome = nome;
		this.numero = numero;
	}

	/**
	 * Questo metodo restituisce il nome del mese corrispondente, in fomato
	 * Stringa minuscola, alla costante enumerativa che rappresenta un mese
	 *
	 * @return il nome del mese. TIpo: Stringa minuscola con iniziale maiuscola
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Questo metodo restituisce il numero corrispondente alla costante
	 * enumerativa che rappresenta un mese
	 * 
	 * @return il numero del mese. Tipo: intero.
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Qeusto metodo converte un numero intero (1..12) nella corrispondente
	 * costante enumerativa
	 * 
	 * @param num
	 *            numero del mese in formato stringa
	 * @return la costante enumerativa relativa alla stinga "numero del mese"
	 */
	public static Giorni getMeseFromNumber(String num) {

		switch (num) {
		case "01":
			return GENNAIO;
		case "02":
			return FEBBRAIO;
		case "03":
			return MARZO;
		case "04":
			return APRILE;
		case "05":
			return MAGGIO;
		case "06":
			return GIUGNO;
		case "07":
			return LUGLIO;
		case "08":
			return AGOSTO;
		case "09":
			return SETTEMBRE;
		case "10":
			return OTTOBRE;
		case "11":
			return NOVEMBRE;
		case "12":
			return DICEMBRE;
		default:
			break;
		}

		return null; // messo perchè il metodo deve comunque restituire qualcosa
	}

	/**
	 * Questo metodo è stato definito statico perchè è inerente all'enumerativo
	 * ma non necessita che sia stata istanziato alcun oggetto di questo tipo in
	 * quanto non fa altro che prendere una data e trasformarla in un formato
	 * accettato dal DB sqlLight
	 * 
	 * @param g
	 *            data da calendario Gregoriano
	 * @return oggetto Data in formato "2015-05-19"
	 */
	public static java.sql.Date castDate(GregorianCalendar g) {

		java.util.Date utilDate = g.getTime();
		return new java.sql.Date(utilDate.getTime());

	}

}
