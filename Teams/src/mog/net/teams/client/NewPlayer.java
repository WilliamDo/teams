package mog.net.teams.client;

import mog.net.teams.client.event.SavePlayerCompleteEvent;
import mog.net.teams.client.event.SavePlayerCompleteEventHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class NewPlayer extends Composite {

	private static NewPlayerUiBinder uiBinder = GWT
			.create(NewPlayerUiBinder.class);
	
	private static DataServiceAsync dataService = GWT.create(DataService.class);

	interface NewPlayerUiBinder extends UiBinder<Widget, NewPlayer> {
	}
	
	private final HandlerManager eventBus;

	public NewPlayer(final HandlerManager eventBus) {
		initWidget(uiBinder.createAndBindUi(this));
		this.eventBus = eventBus;
		
		form.addSubmitCompleteHandler(new SubmitCompleteHandler() {
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				Window.alert(event.getResults());
				form.reset();
				eventBus.fireEvent(new SavePlayerCompleteEvent());
			}
		});
		
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

	@UiHandler("submitButton")
	void onClick(ClickEvent e) {
		
		dataService.getBlobstoreServiceAction(new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String result) {
				form.setEncoding(FormPanel.ENCODING_MULTIPART);
				form.setMethod(FormPanel.METHOD_POST);
				form.setAction(result);
				form.submit();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
		
		
		
	}


}
