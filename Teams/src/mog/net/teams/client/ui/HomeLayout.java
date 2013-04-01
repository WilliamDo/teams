package mog.net.teams.client.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.digester.SetRootRule;

import mog.net.teams.client.DataService;
import mog.net.teams.client.DataServiceAsync;
import mog.net.teams.client.Player;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

public class HomeLayout extends Composite {

	private static HomeLayoutUiBinder uiBinder = GWT
			.create(HomeLayoutUiBinder.class);

	interface HomeLayoutUiBinder extends UiBinder<Widget, HomeLayout> {
	}
	
	@UiField ScrollPanel centerScrollPanel;
	
	@UiField SimplePanel southPanel;
	
	@UiField HTMLPanel leftNavBar;
	
	public HomeLayout(EventBus eventBus, Widget southWidget, Widget mainWidget) {
		initWidget(uiBinder.createAndBindUi(this));
		southPanel.setWidget(southWidget);
		centerScrollPanel.setWidget(mainWidget);
		
		CellList<Player> cellList = new CellList<Player>(new MyCell());
		MyAsyncDataProvider dataProvider = new MyAsyncDataProvider();
		dataProvider.addDataDisplay(cellList);
		leftNavBar.add(cellList);
		
		
	}
	
	private static final List<String> DAYS = Arrays.asList("Sunday", "Monday",
		      "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
	
	private class MyCell extends AbstractCell<Player> {

		@Override
		public void render(com.google.gwt.cell.client.Cell.Context context,
				Player player, SafeHtmlBuilder sb) {
			sb.appendHtmlConstant("<table><tr><td><img src='" + player.getImageServingUrl()  + "' /></td><td>");
			sb.appendEscaped(player.getFirstName());
			sb.appendHtmlConstant("</td></tr></table>");
			
		}
		
	}
	
	private class MyAsyncDataProvider extends AsyncDataProvider<Player> {

		@Override
		protected void onRangeChanged(HasData<Player> display) {
			DataServiceAsync dataService = GWT.create(DataService.class);
			dataService.getPlayers(new AsyncCallback<List<Player>>() {
				
				@Override
				public void onSuccess(List<Player> result) {
					updateRowData(0, result);
				}
				
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}
			});
			
		}
		
	}

}
