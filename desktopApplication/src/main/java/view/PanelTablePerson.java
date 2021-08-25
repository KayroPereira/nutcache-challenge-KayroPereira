package view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.entity.Person;
import controller.interfaces.ObserverSelectedRow;
import controller.interfaces.RefreshTablePersonInterface;

@SuppressWarnings("serial")
public class PanelTablePerson extends JPanel implements MouseListener, RefreshTablePersonInterface {

	private JTable jtPerson;
	
	private DefaultTableModel model;
	
	private List<ObserverSelectedRow> observerSelectedRow = new ArrayList<>(); 
	
	public PanelTablePerson() {
		
		createPanelDesigner();
		
		modelTableCreate();
		
		jtPerson.addMouseListener(this);
	}

	private void createPanelDesigner() {
		JScrollPane spPerson = new JScrollPane();
		GroupLayout gl_jpTablePerson = new GroupLayout(this);
		gl_jpTablePerson.setHorizontalGroup(
			gl_jpTablePerson.createParallelGroup(Alignment.LEADING)
				.addComponent(spPerson, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
		);
		gl_jpTablePerson.setVerticalGroup(
			gl_jpTablePerson.createParallelGroup(Alignment.LEADING)
				.addComponent(spPerson, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
		);
		
		jtPerson = new JTable();
		
		spPerson.setViewportView(jtPerson);
		setLayout(gl_jpTablePerson);
	}

	private void modelTableCreate() {
		
		model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Select", "Name", "Email", "Star Date", "Team"
				}
			){
				Class[] columnTypes = new Class[] {
					Boolean.class, String.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					true, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		
		jtPerson.setModel(model);
		
		jtPerson.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		jtPerson.getColumnModel().getColumn(0).setPreferredWidth(50);
		jtPerson.getColumnModel().getColumn(0).setMaxWidth(50);
		jtPerson.getColumnModel().getColumn(0).setResizable(false);
		
		jtPerson.getColumnModel().getColumn(1).setPreferredWidth(100);
		
		jtPerson.getColumnModel().getColumn(2).setPreferredWidth(100);
		
		jtPerson.getColumnModel().getColumn(3).setMinWidth(80);
		jtPerson.getColumnModel().getColumn(3).setPreferredWidth(80);
		jtPerson.getColumnModel().getColumn(3).setMaxWidth(80);
		jtPerson.getColumnModel().getColumn(3).setResizable(false);
		
		jtPerson.getColumnModel().getColumn(4).setMinWidth(80);
		jtPerson.getColumnModel().getColumn(4).setPreferredWidth(80);
		jtPerson.getColumnModel().getColumn(4).setMaxWidth(80);
		jtPerson.getColumnModel().getColumn(4).setResizable(false);
	}

	private void informObservers(Boolean status, int row) {
		observerSelectedRow.stream()
			.forEach(o -> o.selectedRow(status, row));
	}
	
	public void observersSelectedRowAdd(ObserverSelectedRow observer) {
		observerSelectedRow.add(observer);
	}
	
	@Override
	public void refreshTablePerson(List<Person> person) {
		model.setNumRows(0);

		if(person != null) {
			person.stream()
					.forEach(p -> model.addRow(new Object[]{false, p.getName(), p.getEmail(), p.getStartDate(), p.getTeam()}));
		}
		
		informObservers(false, -1);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		int column = jtPerson.columnAtPoint(e.getPoint());
		int row = jtPerson.rowAtPoint(e.getPoint());
		int rowSelected = jtPerson.getSelectedRow();
		
		if(column == 0 && row != -1 && row == rowSelected) {			
			for(int i = 0; i < model.getRowCount(); i++) {
				if(i != row && Boolean.parseBoolean(model.getValueAt(i, 0).toString())) {
					model.setValueAt(false, i, 0);
				}
			}
			informObservers(Boolean.parseBoolean(model.getValueAt(row, 0).toString()), row);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
