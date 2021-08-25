package view;

import controller.PanelButtonsController;
import controller.PanelChangeController;
import controller.PanelTablePersonController;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;

@SuppressWarnings("serial")
public class InterfaceApi extends JFrame {

	private JPanel contentPane;
	private PanelButtons jpButtons;
	private PanelTablePerson jpTablePerson;
	private PanelChange jpChange;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceApi frame = new InterfaceApi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public InterfaceApi() {
		createWindowApp();
		
		PanelButtonsController buttonsController = new PanelButtonsController();
		PanelChangeController changeController = new PanelChangeController(jpChange);
		PanelTablePersonController tablePersonController = new PanelTablePersonController(jpTablePerson);

		buttonsController.observersButtonsControllerAdd(jpButtons);
		buttonsController.observersRefreshTablePersonAdd(jpTablePerson);
		buttonsController.observersRequestDataPanelChangeToButtonAdd(jpChange);

		changeController.observersChangePersonAdd(jpChange);

		jpButtons.observersAdd(buttonsController);

		jpTablePerson.observersSelectedRowAdd(buttonsController);
		jpTablePerson.observersSelectedRowAdd(changeController);
		
		jpChange.observersTransferenceDataPersonAdd(buttonsController);
		
		jpButtons.refreshData();
	}

	private void createWindowApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 736, 556);
		setSize(800, 600);
		setResizable(false);
		setLocationRelativeTo(null);

		try {
			UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
			
			UIManager.setLookAndFeel(looks[3].getClassName());
		} catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();	}
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		jpTablePerson = new PanelTablePerson();
		
		jpButtons = new PanelButtons();
		
		jpChange = new PanelChange();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(jpButtons, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
				.addComponent(jpTablePerson, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
				.addComponent(jpChange, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(jpTablePerson, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(jpChange, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
					.addComponent(jpButtons, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
		);
				
		contentPane.setLayout(gl_contentPane);
	}
}
