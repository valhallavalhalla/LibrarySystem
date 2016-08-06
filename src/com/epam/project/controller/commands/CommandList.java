package com.epam.project.controller.commands;

public enum CommandList {
	
	LOGIN(new LoginCommand()),
	REGISTRATION(new RegistrationCommand()),
	PROFILE(new ProfileCommand()),
	LOGOUT(new LogoutCommand()),
	CATALOG(new BookCatalogCommand()),
	ORDER_BOOK(new OrderBookCommand()),
	RETURN_BOOK(new ReturnBookCommand()),
	ADMIN(new AdminCommand()),
	CONFIRM_ORDER(new ConfirmOrderCommand()),
	REFUSE_ORDER(new RefuseOrderCommand());
	
	private CommandList(Command command){
		this.command =command;
	}
	
	private Command command;
	
	public Command getCommand() {
		return command;
	}
	

}
