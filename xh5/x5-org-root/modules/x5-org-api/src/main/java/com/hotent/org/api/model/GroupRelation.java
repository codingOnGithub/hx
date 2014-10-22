package com.hotent.org.api.model;

import java.util.ArrayList;
import java.util.List;

import com.hotent.org.api.model.Group.Status;

public interface GroupRelation {
	public static enum Status {
		actived("actived"), 
		locked("locked"),
		archived("archived");
		
		private final String value;

		private Status(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
		
		@Override
		public String toString() {
			return getValue();
		}
		
		public static List<Status> getValidStatuses(){
			return new ArrayList<Status>(){
				{
					add(Status.actived);
				}
				{
					add(Status.locked);
				}
			};
		}
	} 
}
