package mog.net.teams.client.ui;

import mog.net.teams.client.DataService;
import mog.net.teams.client.DataServiceAsync;
import mog.net.teams.client.Player;
import mog.net.teams.client.event.LoadPlayerEvent;
import mog.net.teams.client.event.LoadPlayerEventHandler;
import mog.net.teams.client.event.SavePlayerCompleteEvent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
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
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class NewPlayer extends Composite {

	private static NewPlayerUiBinder uiBinder = GWT
			.create(NewPlayerUiBinder.class);
	
	private static DataServiceAsync dataService = GWT.create(DataService.class);

	interface NewPlayerUiBinder extends UiBinder<Widget, NewPlayer> {
	}

	@UiField
	Image playerImage;
	
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
	
	private final EventBus eventBus;
	
	private String imageKey;
	
	private Player player;

	public NewPlayer(final EventBus eventBus) {
		initWidget(uiBinder.createAndBindUi(this));
		this.eventBus = eventBus;
		
		form.addSubmitCompleteHandler(new SubmitCompleteHandler() {
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				imageKey = event.getResults();
				Window.alert(imageKey);
				submitButton.setEnabled(true);
			}
		});
		
		eventBus.addHandler(LoadPlayerEvent.TYPE, new LoadPlayerEventHandler() {
			@Override
			public void onLoadPlayer(LoadPlayerEvent event) {
				loadPlayer(event.getId());
				
			}
			
		});
		
		imageUpload.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				submitButton.setEnabled(false);
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
		});
		
	}



	@UiHandler("submitButton")
	void onClick(ClickEvent e) {
		
		if (player == null) {
			player = new Player(firstNameTextBox.getText(), surnameTextBox.getText(), emailTextBox.getText(), imageKey);
		} else {
			player.setFirstName(firstNameTextBox.getText());
			player.setLastName(surnameTextBox.getText());
			player.setEmailAddress(emailTextBox.getText());
		}
		
		dataService.savePlayer(player, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Player saved!");
				eventBus.fireEvent(new SavePlayerCompleteEvent());
				
			}
		});
		
	}
	
	@UiHandler("newPlayerButton")
	void onNewPlayer(ClickEvent e) {
		form.reset();
		this.player = null;
	}
	
	@UiHandler("deletePlayerButton")
	void onDeletePlayer(ClickEvent e) {
		if (player != null) {
			dataService.deletePlayer(player.getId(), new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(Void result) {
					// Reusing the save event - consider renaming 
					eventBus.fireEvent(new SavePlayerCompleteEvent());
					
				}


				
			});
		}
	}
	
	private void loadPlayer(long id) {

		dataService.getPlayer(id, new AsyncCallback<Player>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Player result) {
				form.reset();
				firstNameTextBox.setText(result.getFirstName());
				surnameTextBox.setText(result.getLastName());
				emailTextBox.setText(result.getEmailAddress());
				playerImage.setUrl(result.getImageServingUrl());
				player = result;
			}
			
		});
		
	}


}
