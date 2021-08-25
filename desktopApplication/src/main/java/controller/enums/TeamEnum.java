package controller.enums;

public enum TeamEnum {
	
	NULL(""),
	MOBILE("Mobile"),
	FRONTEND("Frontend"),
	BACKEND("Backend");
	
	private String description;

	TeamEnum(String descricao) {
        this.description = descricao;
    }

    public String getDescription() {
        return description;
    }
}
