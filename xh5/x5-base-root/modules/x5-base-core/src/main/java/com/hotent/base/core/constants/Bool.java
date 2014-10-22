package com.hotent.base.core.constants;

public enum Bool {
	TRUE('Y',"是"),FALSE('N',"否");
	private char value;
	private String label;
	Bool(char _value,String _label){
		value=_value;
		label=_label;
	}
	public char value() {
		return value;
	}
	public String label() {
		return label;
	}
	
}
