package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.entity.Person;
import controller.enums.ButtonsNamesEnum;
import controller.interfaces.ButtonsControllerInterface;
import controller.interfaces.ObserverSelectedRow;
import controller.interfaces.ObserversButtonsInterface;

@SuppressWarnings("serial")
public class PanelButtons extends JPanel implements ActionListener, ButtonsControllerInterface {
	
	private JButton btnRefresh;
	
	private JButton btnCreate;
	
	private JButton btnDelete;
	
	private GroupLayout gl_jpButtons;
	
	private List<ObserversButtonsInterface> observersEventButtons = new ArrayList<>();
	
	
	public PanelButtons() {
		createPanelDesigner();

		refreshview(false, -1);
	}
	
	private void createPanelDesigner() {
		btnRefresh = new JButton(ButtonsNamesEnum.REFRESH.getDescription());
		btnCreate = new JButton(ButtonsNamesEnum.CREATE.getDescription());
		btnDelete = new JButton(ButtonsNamesEnum.DELETE.getDescription());
		
		btnRefresh.addActionListener(this);
		btnCreate.addActionListener(this);
		btnDelete.addActionListener(this);
		
		
		gl_jpButtons = new GroupLayout(this);
		gl_jpButtons.setHorizontalGroup(
			gl_jpButtons.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpButtons.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnRefresh)
					.addGap(248)
					.addComponent(btnCreate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(239)
					.addComponent(btnDelete)
					.addContainerGap())
		);
		gl_jpButtons.setVerticalGroup(
			gl_jpButtons.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpButtons.createSequentialGroup()
					.addContainerGap(18, Short.MAX_VALUE)
					.addGroup(gl_jpButtons.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRefresh)
						.addComponent(btnDelete)
						.addComponent(btnCreate))
					.addContainerGap())
		);
		
		setLayout(gl_jpButtons);
	}

	public void observersAdd(ObserversButtonsInterface observer) {
		observersEventButtons.add(observer);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource() instanceof JButton) {
			JButton button = (JButton) e.getSource();
			
			observersEventButtons.forEach(o -> o.buttonClicked(button.getText()));
		}
	}

	public void refreshData() {
		btnRefresh.doClick();
	}

	@Override
	public void refreshview(Boolean status, int row) {
		btnDelete.setEnabled(status);

		if(status) btnCreate.setText(ButtonsNamesEnum.EDIT.getDescription()); else btnCreate.setText(ButtonsNamesEnum.CREATE.getDescription());
	}
}
