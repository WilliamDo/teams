package mog.net.teams.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class NewPlayer extends Composite implements HasText {

	private static NewPlayerUiBinder uiBinder = GWT
			.create(NewPlayerUiBinder.class);

	interface NewPlayerUiBinder extends UiBinder<Widget, NewPlayer> {
	}

	public NewPlayer() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Button submitButton;

	@UiField
	FormPanel form;

	@UiField
	TextBox firstNameTextBox;

	@UiField
	TextBox surnameTextBox;

	@UiField
	TextBox emailTextBox;

	@UiField
	FileUpload imageUpload;

	public NewPlayer(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		firstNameTextBox.setText(firstName);
	}

	@UiHandler("submitButton")
	void onClick(ClickEvent e) {
		form.reset();
		Window.alert("Submit not implemented yet!");
	}

	public void setText(String text) {
		submitButton.setText(text);
	}

	public String getText() {
		return submitButton.getText();
	}

}
