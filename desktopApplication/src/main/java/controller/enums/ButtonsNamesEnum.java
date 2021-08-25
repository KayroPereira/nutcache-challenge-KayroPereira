package controller.enums;

public enum ButtonsNamesEnum {
	
	REFRESH("Refresh"),
	CREATE("Create"),
	EDIT("Edit"),
	DELETE("Delete");

	private String description;
	
	public String getDescription() {
		return description;
	}

	ButtonsNamesEnum(String description) {
		this.description = description;
	}
	
	public static ButtonsNamesEnum fromString(String value) {
        for (ButtonsNamesEnum label : ButtonsNamesEnum.values()) {
            if (label.getDescription().equals(value)) {
                return label;
            }
        }
        return null;
    }
}
