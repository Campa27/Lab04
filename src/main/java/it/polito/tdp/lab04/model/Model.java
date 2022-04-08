package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;

	public Model() {
		this.corsoDao = new CorsoDAO();
		this.studenteDao = new StudenteDAO();
	}

	public List<Corso> getTuttiICorsi(){
		return this.corsoDao.getTuttiICorsi();
	}
	
	public List<Studente> getAllStudenti(){
		return this.studenteDao.getAllStudenti();
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso){
		return this.corsoDao.getStudentiIscrittiAlCorso(corso);
	}
	
	public List<Corso> getCorsiDelloStudente(Studente studente){
		return this.studenteDao.getCorsiDelloStudente(studente);
	}

}
