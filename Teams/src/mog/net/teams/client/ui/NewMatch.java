package mog.net.teams.client.ui;

import mog.net.teams.client.DataService;
import mog.net.teams.client.DataServiceAsync;
import mog.net.teams.client.TeamsGinjector;
import mog.net.teams.client.event.SaveMatchCompleteEvent;
import mog.net.teams.shared.MatchWrapper;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class NewMatch extends Composite {

	private static NewMatchUiBinder uiBinder = GWT
			.create(NewMatchUiBinder.class);
	private final TeamsGinjector injector = GWT.create(TeamsGinjector.class);

	interface NewMatchUiBinder extends UiBinder<Widget, NewMatch> {
	}
	
	DataServiceAsync dataService = GWT.create(DataService.class);
	
	@UiField HTMLPanel dateControl;
	@UiField TextBox teamName;
	
	private DateBox dateBox; 
	
	private EventBus eventBus;

	public NewMatch(EventBus eventBus) {
		initWidget(uiBinder.createAndBindUi(this));
		
		this.eventBus = eventBus;
		DateTimeFormat dateFormat = DateTimeFormat.getFormat(PredefinedFormat.DATE_MEDIUM);
	    this.dateBox = new DateBox();
	    dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
		dateControl.add(this.dateBox);
	}
	
	@UiHandler("submitButton")
	public void onSubmit(ClickEvent e) {
		MatchWrapper match = new MatchWrapper(teamName.getText(), null, null, null);
		match.setDate(dateBox.getValue());
		dataService.saveMatch(match, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				Window.alert("Saved!");
				eventBus.fireEvent(new SaveMatchCompleteEvent());
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				
			}
		});
		
	}

}
