package controller;

import controller.entity.Person;
import controller.enums.ButtonsNamesEnum;
import controller.interfaces.*;
import controller.repository.SharedDataRepository;
import lombok.Data;
import model.ConsumerAPI;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
public class PanelButtonsController implements ObserversButtonsInterface, ObserverSelectedRow, TransferenceDataPanelChangeToPanelButtonsInterface {

	private final ConsumerAPI consumerAPI = new ConsumerAPI();
	private final SharedDataRepository sharedDataRepository = SharedDataRepository.getInstance();
	
	private List<RefreshTablePersonInterface> observersRefreshTablePerson = new ArrayList<>();
	private List<ButtonsControllerInterface> observersButtonsController = new ArrayList<>();
	private List<RequestDataPanelChangeToButtonInterface> observersRequestDataPanelChangeToButton = new ArrayList<>();

	private int idSelectedRow = -1;

	private Person personUpdate = null;
	
	public PanelButtonsController() {
	}

	@Override
	public void buttonClicked(String button) {

		ButtonsNamesEnum optionsButton = ButtonsNamesEnum.fromString(button);
		
		switch(optionsButton) {
		case REFRESH:
			refresh();
			break;

		case EDIT:
		case CREATE:
			change();
			break;
		case DELETE:
			delete();
			break;
		}
	}
	
	public int getIdSelectedRow() {
		return idSelectedRow;
	}

	public void setIdSelectedRow(int idSelectedRow) {
		this.idSelectedRow = idSelectedRow;
	}

	public void observersRefreshTablePersonAdd(RefreshTablePersonInterface observe) {
		this.observersRefreshTablePerson.add(observe);
	}
	
	public void observersButtonsControllerAdd(ButtonsControllerInterface observe) {
		this.observersButtonsController.add(observe);
	}

	public void observersRequestDataPanelChangeToButtonAdd(RequestDataPanelChangeToButtonInterface observer){
		observersRequestDataPanelChangeToButton.add(observer);
	}
	
	private void informObserversRefreshTablePerson(List<Person> persons) {

		sharedDataRepository.setPersons(persons);
		
		observersRefreshTablePerson.stream()
			.forEach(o -> o.refreshTablePerson(persons));
	}
	
	private void informObserversButtonsController(Boolean status, int row) {
		this.observersButtonsController.stream().forEach(o -> o.refreshview(status, row));
	}

	private void informObserversRequestDataPanelChangeToButton(){
		observersRequestDataPanelChangeToButton.stream().forEach(o -> o.requestDataPanelChangeToButtonInterface());
	}

	private String getMessage(String message, String find){
		return message.substring(message.indexOf("Message: ")+find.length(), message.length());
	}

	private void delete() {

		Long id = sharedDataRepository.getPersons().get(getIdSelectedRow()).getId();

		if(id >= 0) {
			if(JOptionPane.showConfirmDialog(null, "Do you want to delete the employee?", "Delete Employee", JOptionPane.WARNING_MESSAGE) == 0){
				String response = consumerAPI.delete(SharedDataRepository.urlPerson, id);
				if(response.contains("Status: 200")){;
					JOptionPane.showMessageDialog(null, getMessage(response, "Message: "),"Sucess", JOptionPane.INFORMATION_MESSAGE);
				}
				informObserversRefreshTablePerson(get());
			}else{
				System.out.println("1");
			}
		}
	}

	private <T> Boolean isEquals(T oldObject, T newObject){
		if(oldObject.equals(newObject)) return true; else return false;
	}

	private Person editPerson(Person person){

		if(!isEquals(person.getName(), personUpdate.getName())) person.setName(personUpdate.getName());
		if(!isEquals(person.getEmail(), personUpdate.getEmail())) person.setEmail(personUpdate.getEmail());
		if(!isEquals(person.getCpf(), personUpdate.getCpf())) person.setCpf(personUpdate.getCpf());
		if(!isEquals(person.getBirthDate(), personUpdate.getBirthDate())) person.setBirthDate(personUpdate.getBirthDate());
		if(!isEquals(person.getTeam(), personUpdate.getTeam())) person.setTeam(personUpdate.getTeam());
		if(!isEquals(person.getGender().getDescription(), personUpdate.getGender().getDescription())) person.setGender(personUpdate.getGender());
		return person;
	}

	private Boolean validatioinPerson(){

		if(personUpdate.getName().isBlank() || personUpdate.getName() == null) return false;
		if(personUpdate.getEmail().isBlank() || personUpdate.getEmail() == null) return false;
		if(personUpdate.getCpf().isBlank() || personUpdate.getCpf() == null) return false;
		if(personUpdate.getBirthDate().isBlank() || personUpdate.getBirthDate() == null) return false;
		if(personUpdate.getTeam().isBlank() || personUpdate.getTeam() == null) return false;
		if(personUpdate.getGender().getDescription().isBlank() || personUpdate.getGender().getDescription() == null) return false;

		return true;
	}

	private void showMessageSucess(String message){
		JOptionPane.showMessageDialog(null, getMessage(message, "Message: "),"Sucess", JOptionPane.INFORMATION_MESSAGE);
	}

	private void showMessageFail(String message){
		JOptionPane.showMessageDialog(null, getMessage(message, "Message: "),"Fail", JOptionPane.INFORMATION_MESSAGE);
	}

	private void showMessageFailGeneral(String message){
		JOptionPane.showMessageDialog(null, message,"Fail", JOptionPane.INFORMATION_MESSAGE);
	}

	private void change() {

		informObserversRequestDataPanelChangeToButton();

		if (validatioinPerson()) {
			if (idSelectedRow == -1) {

				personUpdate.getGender().setId(sharedDataRepository.getIdGenderByName(personUpdate.getGender().getDescription()));
				personUpdate.setStartDate(DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDateTime.now()).toString());

				String response = consumerAPI.create(SharedDataRepository.urlPerson, personUpdate);

				if (response.contains("Status: 201")) {
					showMessageSucess(response);
				} else {
					showMessageFailGeneral("Error creating employee");
				}
			}else {
				Person person = sharedDataRepository.getPersons().get(getIdSelectedRow());
				Long id = person.getId();

				person.setId(id);
				person = editPerson(person);
				person.getGender().setId(sharedDataRepository.getIdGenderByName(person.getGender().getDescription()));
				String response = consumerAPI.update(SharedDataRepository.urlPerson, id, person);

				if (response.contains("Status: 200")) {
					showMessageSucess(response);
				} else {
					showMessageFailGeneral("Error updating employee");
				}
			}
			informObserversRefreshTablePerson(get());
		} else {
			showMessageFailGeneral("Please fill in all fields");
		}
	}

	private void refresh() {
		informObserversRefreshTablePerson(get());
	}
	
	private List<Person> get() {
		sharedDataRepository.setPersons(consumerAPI.get(SharedDataRepository.urlPerson, Person.class));
		return sharedDataRepository.getPersons();
	}

	@Override
	public void selectedRow(Boolean status, int row) {
		if(status) setIdSelectedRow(row);else setIdSelectedRow(-1);

		informObserversButtonsController(status, row);
	}

	@Override
	public void transferenceDataPanelChangeToPanelButtonsInterface(Person person) {
		setPersonUpdate(person);
	}
}