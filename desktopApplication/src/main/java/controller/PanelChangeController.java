package controller;

import controller.entity.Person;
import controller.interfaces.ChangePersonInterface;
import controller.interfaces.ObserverSelectedRow;
import controller.repository.SharedDataRepository;
import lombok.Data;
import model.ConsumerAPI;
import view.PanelChange;

import java.util.ArrayList;
import java.util.List;

@Data
public class PanelChangeController implements ObserverSelectedRow{
	
	private PanelChange panelChange;
	
	private final ConsumerAPI consumerAPI = new ConsumerAPI();

	private final SharedDataRepository sharedDataRepository = SharedDataRepository.getInstance();

	private List<ChangePersonInterface> observersChangePerson = new ArrayList<>();

	public PanelChangeController(PanelChange panelChange) {
		this.panelChange = panelChange;
	}
	

	public void observersChangePersonAdd(ChangePersonInterface observer){
		observersChangePerson.add(observer);
	}

	private void informobserversChangePerson(Person person){
		observersChangePerson.stream().forEach(o -> o.changePersonInterface(person));
	}

	@Override
	public void selectedRow(Boolean status, int row) {
		if(row >= 0) {
			informobserversChangePerson(sharedDataRepository.getPersons().get(row));
		}else{
			informobserversChangePerson(null);
		}
	}
}
