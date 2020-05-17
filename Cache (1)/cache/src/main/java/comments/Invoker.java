package comments;

import comments.Command;

public class Invoker {

	private Command command;

	public void setCommand(Command command) {

		this.command = command;

	}

	public void executeAction() {
		command.execute();
	}
	
	

}
