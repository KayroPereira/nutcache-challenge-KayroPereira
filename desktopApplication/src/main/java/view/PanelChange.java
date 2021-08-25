package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.toedter.calendar.JDateChooser;

import controller.entity.Gender;
import controller.entity.Person;
import controller.enums.TeamEnum;
import controller.interfaces.ChangePersonInterface;
import controller.interfaces.RequestDataPanelChangeToButtonInterface;
import controller.interfaces.TransferenceDataPanelChangeToPanelButtonsInterface;
import controller.repository.SharedDataRepository;
import lombok.Setter;
import lombok.Getter;

@SuppressWarnings("serial")
@Getter
@Setter
public class PanelChange extends JPanel implements ChangePersonInterface, RequestDataPanelChangeToButtonInterface {

	private JTextField tfName;
	private JTextField tfEmail;
	private JTextField tfCPF;
	private JLabel jlBirthDate;
	private JLabel jbGender;
	private JLabel jlTeam;
	private JDateChooser dtcBirthDate;
	
	private JComboBox<String> cbxGender;
	private JComboBox<String> cbxTeam;
	
	private List<TransferenceDataPanelChangeToPanelButtonsInterface> observersTransferenceDataPerson = new ArrayList<>();
	
	public PanelChange() {
		
		createPanelDesigner();
		changeValues();
	}
	
	private void changeValues() {
		for(TeamEnum t : TeamEnum.values()) {
			cbxTeam.addItem(t.getDescription());
		}

		List<Gender> genders = SharedDataRepository.getInstance().getGenders();
		cbxGender.addItem("");
		for(Gender g : genders) {
			cbxGender.addItem(g.getDescription());
		}

		defaultStatus();
	}
	
	public void observersTransferenceDataPersonAdd(TransferenceDataPanelChangeToPanelButtonsInterface observer) {
		observersTransferenceDataPerson.add(observer);
	}
	
	private void informObserversTransferenceDataPerson(Person person) {
		observersTransferenceDataPerson.stream()
			.forEach(o -> o.transferenceDataPanelChangeToPanelButtonsInterface(person));
	}
	
	private void defaultStatus() {
		dtcBirthDate.setDate(new Date());
		
		cbxTeam.setSelectedIndex(0);
		cbxGender.setSelectedIndex(0);
		
		tfName.setText("");
		tfEmail.setText("");
		tfCPF.setText("");
	}

	private void createPanelDesigner() {
		
		JLabel jlName = new JLabel("Name");
		
		JLabel jlEmail = new JLabel("Email");
		
		JLabel jlCpf = new JLabel("CPF");
		
		tfName = new JTextField();
		tfName.setToolTipText("Name");
		tfName.setColumns(10);
		
		tfEmail = new JTextField();
		tfEmail.setToolTipText("Name");
		tfEmail.setColumns(10);
		
		tfCPF = new JTextField();
		tfCPF.setToolTipText("Name");
		tfCPF.setColumns(10);
		
		jlBirthDate = new JLabel("Birth Date");
		
		jbGender = new JLabel("Gender");
		
		jlTeam = new JLabel("Team");
		
		cbxGender = new JComboBox();
		
		cbxTeam = new JComboBox();
		
		dtcBirthDate = new JDateChooser();
		dtcBirthDate.setDateFormatString("dd/MM/yyyy");
		
		GroupLayout gl_jpChange = new GroupLayout(this);
		gl_jpChange.setHorizontalGroup(
			gl_jpChange.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpChange.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpChange.createParallelGroup(Alignment.LEADING)
						.addComponent(jlName)
						.addComponent(jlEmail)
						.addComponent(jlCpf))
					.addGap(25)
					.addGroup(gl_jpChange.createParallelGroup(Alignment.LEADING)
						.addComponent(tfEmail, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
						.addComponent(tfName, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
						.addComponent(tfCPF, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
					.addGap(52)
					.addGroup(gl_jpChange.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_jpChange.createSequentialGroup()
							.addGroup(gl_jpChange.createParallelGroup(Alignment.LEADING)
								.addComponent(jbGender)
								.addComponent(jlTeam))
							.addGap(23)
							.addGroup(gl_jpChange.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(cbxTeam, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(cbxGender, 0, 142, Short.MAX_VALUE)))
						.addGroup(gl_jpChange.createSequentialGroup()
							.addComponent(jlBirthDate)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(dtcBirthDate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(150))
		);
		gl_jpChange.setVerticalGroup(
			gl_jpChange.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpChange.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpChange.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpChange.createParallelGroup(Alignment.BASELINE)
							.addComponent(jlName)
							.addComponent(tfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(jlBirthDate))
						.addComponent(dtcBirthDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_jpChange.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpChange.createSequentialGroup()
							.addGroup(gl_jpChange.createParallelGroup(Alignment.BASELINE)
								.addComponent(jlEmail)
								.addComponent(tfEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(jlCpf))
						.addGroup(gl_jpChange.createSequentialGroup()
							.addGroup(gl_jpChange.createParallelGroup(Alignment.BASELINE)
								.addComponent(jbGender)
								.addComponent(cbxGender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_jpChange.createParallelGroup(Alignment.BASELINE)
								.addComponent(jlTeam)
								.addComponent(cbxTeam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfCPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		setLayout(gl_jpChange);
	}

	@Override
	public void changePersonInterface(Person person) {
		defaultStatus();

		if(person != null) {
			tfName.setText(person.getName());
			tfCPF.setText(person.getCpf());
			tfEmail.setText(person.getEmail());

			try {
				dtcBirthDate.setDate(new SimpleDateFormat("dd-MM-yyyy").parse(person.getBirthDate()));
			} catch (ParseException e) {
				dtcBirthDate.setDate(new Date());
			}

			cbxGender.setSelectedItem(person.getGender().getDescription());
			cbxTeam.setSelectedItem(person.getTeam());
		}
	}

	@Override
	public void requestDataPanelChangeToButtonInterface() {
		Person person = Person.builder()
			.id(null)
			.name(tfName.getText())
			.birthDate(new SimpleDateFormat("dd-MM-YYYY").format(dtcBirthDate.getDate()==null?new Date():dtcBirthDate.getDate()))
			.email(tfEmail.getText())
			.cpf(tfCPF.getText())
			.startDate(DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDateTime.now()).toString())
			.team(cbxTeam.getSelectedItem().toString())
			.gender(Gender.builder().id(null).description(cbxGender.getSelectedItem().toString()).build())
			.build();

		informObserversTransferenceDataPerson(person);
	}
}