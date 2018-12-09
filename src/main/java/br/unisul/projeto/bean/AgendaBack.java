package br.unisul.projeto.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.unisul.projeto.dao.AgendaDao;
import br.unisul.projeto.domain.Agenda;

@ManagedBean(name = "agendaBack")
@ViewScoped
public class AgendaBack {

	private ScheduleModel agendas;
	private ScheduleEvent eventAgenda = new DefaultScheduleEvent();
	private Agenda agenda;
	private boolean editing;
	
	@PostConstruct
	public void listar() {
		editing = false;
		agendas = new DefaultScheduleModel();
		AgendaDao agendaDao = new AgendaDao();
		List<Agenda> agendaList = agendaDao.listar();
		if (agendaList != null) {
			for (Agenda agenda : agendaList) {
				DefaultScheduleEvent event = new DefaultScheduleEvent();
				event.setTitle(agenda.getDsPendencia());
				event.setDescription(agenda.getCd().toString());
				event.setStartDate(agenda.getDtAgenda());
				event.setEndDate(agenda.getDtAgenda());
				event.setAllDay(true);
				agendas.addEvent(event);
			}
		}
	}
	
	public void novo(SelectEvent event) {
		editing = false;
		agenda = new Agenda();
		Date dataSelecionada = (Date)event.getObject();
		agenda.setDtAgenda(dataSelecionada);
		agenda.setDsHora(Calendar.getInstance().getTime());
	}
	
	public void salvar() {
		AgendaDao agendaDao = new AgendaDao();
		
		Calendar calendar = Calendar.getInstance(Locale.forLanguageTag("pt_BR"));
		calendar.setTime(agenda.getDtAgenda());
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int date = calendar.get(Calendar.DATE);
		
		calendar.setTime(agenda.getDsHora());
		int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		
		calendar.set(year, month, date, hourOfDay, minute);
		calendar.add(Calendar.DATE, 1);
		calendar.add(Calendar.HOUR_OF_DAY, -2);
		
		agenda.setDsHora(calendar.getTime());
		agendaDao.salvar(agenda);
		
		listar();
	}
	
	public void onEventSelect(SelectEvent selectEvent) {
		editing = true;
        eventAgenda = (ScheduleEvent) selectEvent.getObject();
        AgendaDao agendaDao = new AgendaDao();
        List<Agenda> agendaList = agendaDao.listar(Integer.decode(eventAgenda.getDescription()));
        if (agendaList != null) {
        	agenda = agendaList.get(0);
        }
    }
	
	public void remover() {
		AgendaDao agendaDao = new AgendaDao();
		agendaDao.excluir(agenda);
		listar();
	}
	
	public ScheduleModel getAgendas() {
		return agendas;
	}
	
	public void setAgendas(ScheduleModel agendas) {
		this.agendas = agendas;
	}
	
	public ScheduleEvent getEventAgenda() {
		return eventAgenda;
	}
	
	public void setEventAgenda(ScheduleEvent eventAgenda) {
		this.eventAgenda = eventAgenda;
	}
	
	public Agenda getAgenda() {
		return agenda;
	}
	
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
	
	public boolean isEditing() {
		return editing;
	}
	
	public void setEditing(boolean editing) {
		this.editing = editing;
	}
	
}
