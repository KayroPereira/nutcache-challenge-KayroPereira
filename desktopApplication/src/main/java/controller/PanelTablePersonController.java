package controller;

import controller.repository.SharedDataRepository;
import view.PanelTablePerson;

public class PanelTablePersonController {

    private final SharedDataRepository sharedDataRepository = SharedDataRepository.getInstance();

    private final PanelTablePerson panelTablePerson;

    public PanelTablePersonController(PanelTablePerson panelTablePerson){
        this.panelTablePerson = panelTablePerson;
    }
}
