package com.example.quiz12.constants;

public enum QuesType {

	SINGLE("Single"), //
	MULTI("Multi"), //
	TEXT("Text");

	private String type;

	private QuesType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	public static boolean checkType(String type) {
		for(QuesType item : QuesType.values()) {
			if(type.equalsIgnoreCase(item.getType())) {
				return true;
			}
		}
		return false;
	}

}
