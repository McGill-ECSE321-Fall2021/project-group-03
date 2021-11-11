package ca.mcgill.ecse321.librarymanagement.dto;

import java.sql.Date;

import ca.mcgill.ecse321.librarymanagement.model.Client;
import ca.mcgill.ecse321.librarymanagement.model.Title;

public class TitleReservationDto {

	private Date returnDate;
	private boolean isCheckedOut;
	private TitleDto title;
	private ClientDto client;
	private int titleReservationId;

	public Date getReturnDate() {
		return returnDate;
	}

	public TitleReservationDto(Date returnDate, boolean isCheckedOut, TitleDto title, ClientDto client,
			int titleReservationId) {

		this.returnDate = returnDate;
		this.isCheckedOut = isCheckedOut;
		this.title = title;
		this.client = client;
		this.titleReservationId = titleReservationId;
	}

	public boolean isCheckedOut() {
		return isCheckedOut;
	}

	public TitleDto getTitle() {
		return title;
	}

	public ClientDto getClient() {
		return client;
	}

	public int getTitleReservationId() {
		return titleReservationId;
	}

}
