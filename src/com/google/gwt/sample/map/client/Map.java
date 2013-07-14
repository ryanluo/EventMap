package com.google.gwt.sample.map.client;

import java.text.DecimalFormat;
import java.util.ArrayList;
import com.google.gwt.user.client.ui.SourcesTabEvents;
import com.google.gwt.user.client.ui.TabListener;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.i18n.client.DateTimeFormat;
import java.util.Date;
import org.vaadin.gwtgraphics.client.DrawingArea;
import org.vaadin.gwtgraphics.client.Group;
import org.vaadin.gwtgraphics.client.Image;
import org.vaadin.gwtgraphics.client.Line;
import org.vaadin.gwtgraphics.client.shape.Circle;
import com.gargoylesoftware.htmlunit.javascript.host.Window;
import com.google.gwt.sample.map.shared.FieldVerifier;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.touch.client.Point;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Map implements EntryPoint {
	
	private double xPoint;
	private double yPoint;
	private boolean Social; //Event
	private double xScale=0,yScale=0;
	private int xClicked = 0;
    private int yClicked = 0;
    private int xDragged = 0;
    private int yDragged = 0;
    private int xDistance = 0;
    private int yDistance = 0;
    private int zoomScale = 0;
    private int alternator =0;
    private final double ZOOM = 1.2; //scale that we zoom by
    private double distance = 0;
    private double totalDistance = 0;	
    private String imageURL = "http://25.media.tumblr.com/344bd36d4fcfffc2d90cb7473dfe7dec/tumblr_mgjn57W73t1rkyzquo1_1280.jpg";
	private Image image = new Image(0,0,1000,1000,imageURL);
	private Image zImage = image; //for zoom
	private DrawingArea canvas = new DrawingArea(1000,1000);
	private DrawingArea canvas2 = new DrawingArea(1600,1600); //not sure
	private boolean mouseDown = false;
	private FlexTable gFTDistance = new FlexTable();
    private TabPanel distanceCalculator = new TabPanel();
    private Button clearButton = new Button("Clear");
	private HorizontalPanel horizPanel = new HorizontalPanel();	
	private HorizontalPanel horizPanelMap = new HorizontalPanel();
	private HorizontalPanel hPanelLoc = new HorizontalPanel();
	private HorizontalPanel hPanelName = new HorizontalPanel();
	private HorizontalPanel hPanelTime = new HorizontalPanel();
	private HorizontalPanel hPanelDesc = new HorizontalPanel();
	private HorizontalPanel hPanelContact = new HorizontalPanel();
	private HorizontalPanel hPanelButton = new HorizontalPanel();
	private HorizontalPanel hPanelType = new HorizontalPanel();
	private HorizontalPanel h2 = new HorizontalPanel();
	private VerticalPanel vertPanel = new VerticalPanel();
	VerticalPanel vert = new VerticalPanel();
	TextBox eventNameBox = new TextBox();
	TextBox locationBox = new TextBox();
	TextBox contactBox = new TextBox();
	TextArea theDesc = new TextArea();
	Label location = new Label("Location");
	Label time = new Label("Time");
	Label mornAfter = new Label("AM/PM?");
	Label type = new Label("Event Type");
	ListBox dayTime = new ListBox();
	ListBox timeBox = new ListBox();
	ListBox typeBox = new ListBox();
	Button addButton = new Button();
	Button cancelButton = new Button();
	private Label eventCalendar = new Label();
	private HorizontalPanel eventPanel = new HorizontalPanel();
	private boolean tab2Clicked = false;
	private VerticalPanel canvasEventPanel = new VerticalPanel();
	private Button PressMe = new Button();
	private Button Reset = new Button();
	HorizontalPanel hPanelCalendar = new HorizontalPanel();
	DatePicker datePicker = new DatePicker();
    final Label text = new Label();
    private ListBox lb = new ListBox();
    private ListBox lb2 = new ListBox();
    private Button calculateButton = new Button();
    private Label unitLabel = new Label();
    private double calories,miles,meters,walking,running,swimming,hvz;
    private HorizontalPanel h3 = new HorizontalPanel();
    private HorizontalPanel h4 = new HorizontalPanel();
    private Label filler = new Label();
    private int numberOfLines=0;
    HandlerRegistration eventOrDraw;
    
    
	Event[] allEvents = new Event[100];
	int eventCount = 0;
	class Event {

		Image academImage, socialImage, campusMap;
		public String location, eventName, description, timeEvent, contact;
		Boolean isSocial;
		DrawingArea canvas;
		Double x, y;

		public Event(String loca, String eName, String describe, String time,
				String c, Boolean is, DrawingArea acanvas, double x, double y) {
			this.x = x;
			this.y = y;

			canvas = acanvas;
			
			if (!is) {
				academImage = new Image(
					(int) x - 15,
					(int) y - 15,
					30,
					30,
					"http://icons.iconarchive.com/icons/oxygen-icons.org/oxygen/128/Categories-applications-education-university-icon.png");
					canvas.add(academImage);
			}
			else {
				socialImage = new Image((int) x - 15,
						(int) y - 15,
						30,
						30,
				"http://i48.tinypic.com/2zzue4y.png");
				canvas.add(socialImage);
			}
			
				

			/*
			 * socialImage = new Image(300, 300, 30, 30,
			 * "http://i48.tinypic.com/2zzue4y.png");
			 *
			 *
			 * canvas.add(socialImage);
			 */
			
			location = loca;
			eventName = eName;
			description = describe;
			timeEvent = time;
			contact = c;
		}
		public boolean isClicked(double xClick, double yClick){
				return (xClick >= x-5 && xClick<= x + 35 && yClick >= y-5 && yClick <= y+35);	
		}
	}
	boolean eventClicked = false;
	PopupPanel pop = new PopupPanel();
	RadioButton aChoice = new RadioButton("Academic");
	Boolean popExist = false;
	private Group lineList = new Group();
	private Group iconList = new Group();

	public void onModuleLoad() {
		PressMe.setText("Press Me!");
		PressMe.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				alternator++;
				if(alternator%2==1){
				eventOrDraw.removeHandler();
				eventOrDraw = canvas.addMouseDownHandler(new MouseDownHandler() {
					@Override
					public void onMouseDown(MouseDownEvent event) {
						mouseDown=false;
						  datePicker.addValueChangeHandler(new ValueChangeHandler() {
						      public void onValueChange(ValueChangeEvent event) {
						        Date date = (Date)event.getValue();
						        String dateString = DateTimeFormat.getMediumDateFormat().format(date);
						        text.setText(dateString);
						      }
						    });
						  datePicker.setValue(new Date(), true);
						eventClicked = false;
						for(int j = 0; j <=eventCount; j++){
							if(allEvents[j] != null && allEvents[j].isClicked(event.getX(), event.getY())){
								System.out.println(allEvents[j].description);
								eventClicked = true;
								locationBox.setText(allEvents[j].location);
								eventNameBox.setText(allEvents[j].eventName);
								theDesc.setText(allEvents[j].description);
								contactBox.setText(allEvents[j].contact);
								addButton.setVisible(false);
								
								//make text boxes not editable
								locationBox.setReadOnly(true);
								eventNameBox.setReadOnly(true);
								theDesc.setReadOnly(true);
								contactBox.setReadOnly(true);
								
								//make listboxes not editable
								dayTime.setEnabled(false);
								timeBox.setEnabled(false);
								typeBox.setEnabled(false);
								
								pop.show();
								j = eventCount + 1;
								
							}
						}
						if(!eventClicked){
						pop.setTitle("Add Event Info Here");
						pop.setHeight("50");
						pop.setWidth("500");
						pop.center();
						pop.setPopupPosition(event.getX()+250, event.getY()-200);
						xPoint = event.getX();
						yPoint = event.getY();

						
						locationBox.setText("Where is the Event?");
						eventNameBox.setText("What is the event name?");
						theDesc.setText("Please give a short description of your event:");
						contactBox.setText("Please give contact info");
						addButton.setVisible(true);
						locationBox.setReadOnly(false);
						eventNameBox.setReadOnly(false);
						theDesc.setReadOnly(false);
						contactBox.setReadOnly(false);
						
						//make list boxes editable
						dayTime.setEnabled(true);
						timeBox.setEnabled(true);
						typeBox.setEnabled(true);
						if (!popExist) {
							// button for model
							 addButton = new Button("Create Event",
									new ClickListener() {

										@Override
										public void onClick(Widget sender) {
											pop.hide();
											
											if ((typeBox.getValue(typeBox.getSelectedIndex()).equals("Social"))) {
												 Social = true;
											}
											else {
												Social = false;
											}
											allEvents[eventCount] = new Event(
													locationBox.getText(), eventNameBox
															.getText(), theDesc
															.getText(),
													timeBox.getValue(timeBox
															.getSelectedIndex()),
													contactBox.getText(), Social, canvas,
													xPoint, yPoint);
											eventCount++;

										}	
									});

							 cancelButton = new Button("Cancel",
									new ClickListener() {

										@Override
										public void onClick(Widget sender) {
											pop.hide();
										}
									});

							 eventNameBox.setText("What is the event name?");
								eventNameBox.setWidth("200px");
								locationBox.setText("Where is the event?");
								locationBox.setWidth("200px");
								theDesc.setText("Please give a short description of your event:");
								contactBox.setText("Please give contact info");
								contactBox.setWidth("200px");
								theDesc.setVisibleLines(5);
								theDesc.setCharacterWidth(30);
								hPanelName.add(eventNameBox);
								hPanelLoc.add(locationBox);
								for (int i = 1; i < 13; i++) {
									timeBox.addItem("" + i);
								}
								dayTime.addItem("AM");
								dayTime.addItem("PM");
								typeBox.addItem("Social");
								typeBox.addItem("Academic");
								hPanelTime.add(time);
								hPanelTime.add(timeBox);
								hPanelTime.add(mornAfter);
								hPanelTime.add(dayTime);
								hPanelCalendar.add(text);
								hPanelCalendar.add(datePicker);
								hPanelDesc.add(theDesc);
								hPanelTime.setSpacing(5);
								hPanelContact.add(contactBox);
								hPanelButton.add(addButton);
								hPanelButton.add(cancelButton);
								hPanelType.add(type);
								hPanelType.add(typeBox);
								hPanelType.setSpacing(5);
								hPanelCalendar.setSpacing(5);

								vert.add(hPanelName);
								vert.add(hPanelLoc);
								vert.add(hPanelTime);
								vert.add(hPanelCalendar);
								vert.add(hPanelDesc);
								vert.add(hPanelType);
								vert.add(hPanelContact);
								vert.add(hPanelButton);
								
								pop.add(vert);

								popExist = true;
						}

						
						
						
					}
					}
				});
			}
			else{
				eventOrDraw.removeHandler();
				eventOrDraw = canvas.addMouseDownHandler(new MouseDownHandler(){
					@Override
					public void onMouseDown(MouseDownEvent e) {
						mouseDown = true;
						xClicked = e.getX();
						yClicked = e.getY();
						distance = 0;
					}
				});
			}			
			}
			});		
		filler.setText(" dasfadsf                                  ");
		lb2.addItem("--Select Transportation--");
		lb2.addItem("Walking");
		lb2.addItem("Running");
		lb2.addItem("Swimming");
		lb2.addItem("during HvZ");
		running = .095;
		swimming = .074;
		hvz = 10000*Math.random();
		
		lb.addItem("--Select Units--");
	    lb.addItem("calories");
	    lb.addItem("cans of soda");
	    lb.addItem("cows");
	    calculateButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent e){
			    if(lb2.getSelectedIndex() == 1){
			    	
			    }
			    else if(lb2.getSelectedIndex() == 2){
			    	calories = calories * 2.5;
			    }
			    if(lb2.getSelectedIndex() == 3){
			    	calories = calories * 2;
			    }
			    if(lb2.getSelectedIndex() == 4){
			    	calories = calories * hvz;
			    }
			    if(lb.getSelectedIndex() == 1){
			    	unitLabel.setText("You burned " + (int) calories+ " calories.");
			    }
			    else if(lb.getSelectedIndex() == 2){
			    	calories = calories/146;
			    	unitLabel.setText("You burned " + (int) calories+ " cans of soda. Stop drinking soda.");
			    }
			    else if(lb.getSelectedIndex() == 3){
			    	calories = calories/600000.0;
			    	unitLabel.setText("You burned " + (int) (calories) + " cows. Not literally. I hope.");
			    }
			    
			    
			}
		});
	    calculateButton.setText("Calculate");
		canvas.add(image);
				
		
		eventCalendar.setText("Event Calendar");
		eventPanel.add(eventCalendar);
		eventPanel.add(PressMe);
		clearButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				zoomScale=0;
				canvas.clear();
				zImage=image;
				canvas.add(image);
				totalDistance=0;
				gFTDistance.setText(0,0,"Distance: 0 meters");
				gFTDistance.setText(1, 0, "Total Distance: 0 meters");
				unitLabel.setText("");
				lineList.clear();	
			}
		});

		gFTDistance.setText(0, 0, "Distance: ");
		gFTDistance.setText(1,0, "Total Distance: ");
		vertPanel.add(gFTDistance);
		vertPanel.add(unitLabel);
		h2.add(lb);
		h2.add(lb2);		
		vertPanel.add(h2);
		h3.add(calculateButton);
		h3.add(clearButton);	
		h3.setSize("100px","100px");
		h4.add(filler);
		vertPanel.add(h4);
		vertPanel.add(h3);
		horizPanel.add(vertPanel);
		distanceCalculator.add(horizPanel,"CalorieCalculator");
		distanceCalculator.add(eventPanel,"EventCalendar");
		distanceCalculator.setHeight("200px");
		distanceCalculator.setWidth("200px");
		horizPanelMap.add(distanceCalculator);
		
		if(!tab2Clicked){
			eventOrDraw=canvas.addMouseDownHandler(new MouseDownHandler(){
				@Override
				public void onMouseDown(MouseDownEvent e) {
					mouseDown = true;
					xClicked = e.getX();
					yClicked = e.getY();
					distance = 0;
				}
			});
			
			canvas.addMouseUpHandler(new MouseUpHandler(){
				@Override
				public void onMouseUp(MouseUpEvent e) {
					System.out.println(numberOfLines);
					mouseDown = false;	
					totalDistance += distance;
					meters = totalDistance * (109.7/60.0)/(Math.pow(ZOOM, zoomScale));
				    miles = meters / 1609.34;
				    calories = ((.036 * 60.0 * 120.0 * meters) / 4500.0);
					gFTDistance.setText(1,0,"Total Distance: " + (int) meters + " meters");
				}
			});
	
			canvas.addMouseWheelHandler(new MouseWheelHandler(){
				@Override
				public void onMouseWheel(MouseWheelEvent event) {
					if(event.isNorth()){
						int xCoordinate = event.getX();
						int yCoordinate = event.getY();	//coordinates of zoom focus		
						int xCoordinate2 = zImage.getX(); //returns current image shift
						int frameXSize = xCoordinate2;
						xCoordinate2 = Math.abs(xCoordinate2)+xCoordinate;//returns coordinates in terms of image, assuming image is centered at 0,0
						int yCoordinate2 = zImage.getY();
						int frameYSize = yCoordinate2;
						yCoordinate2 = Math.abs(yCoordinate2)+yCoordinate;
						double zWidth = ZOOM*zImage.getWidth();	
						double zHeight = ZOOM*zImage.getHeight();//zooms into the image
						double xDisplacement=xCoordinate2*ZOOM;//finds the point in the new zoomed image
						double yDisplacement=yCoordinate2*ZOOM;
						double xShift = xCoordinate-xDisplacement;//finds the necessary shift to maintain focus
						double yShift = yCoordinate-yDisplacement;
						zImage = new Image((int)xShift,(int)yShift,(int)zWidth,(int)zHeight,imageURL);//creates larger image with appropriate shift
						canvas.clear();
						canvas.add(zImage);		
						zoomScale++;//keep track of how many 'zooms' made
						Group newLineList= new Group();//keeps track of lines drawn
						for(int i=0;i<lineList.getVectorObjectCount();i++){
							Line lineTemp = (Line) lineList.getVectorObject(i);
							int x1 = lineTemp.getX1();
							double x1t = (x1+Math.abs(frameXSize))*ZOOM;
							int y1 = lineTemp.getY1();
							double y1t = (y1+Math.abs(frameYSize))*ZOOM;
							int x2 = lineTemp.getX2();
							double x2t = (x2+Math.abs(frameXSize))*ZOOM;
							int y2 = lineTemp.getY2();
							double y2t = (y2+Math.abs(frameYSize))*ZOOM;
							Line lineDraw = new Line((int)(x1t+xShift),(int)(y1t+yShift),(int)(x2t+xShift),(int)(y2t+yShift));
							lineDraw.setStrokeColor("rgb(38,196,242)");
							lineDraw.setStrokeWidth(3*(int)(Math.pow(ZOOM,zoomScale)));
							lineDraw.setStrokeOpacity(.8);
							canvas.add(lineDraw);
							Line lineStore = new Line((int)(x1t+xShift),(int)(y1t+yShift),(int)(x2t+xShift),(int)(y2t+yShift));
							newLineList.add(lineStore);
							numberOfLines++;
							}
						lineList.clear();
						lineList=newLineList;
						}
					else if(event.isSouth()){
						int xCoordinate = event.getX();
						int yCoordinate = event.getY();			
						int xCoordinate2 = zImage.getX();
						int frameXSize = xCoordinate2;
						xCoordinate2 = Math.abs(xCoordinate2)+xCoordinate;
						int yCoordinate2 = zImage.getY();
						int frameYSize = yCoordinate2;
						yCoordinate2 = Math.abs(yCoordinate2)+yCoordinate;
						double zWidth = 1/ZOOM*zImage.getWidth();	
						double zHeight = 1/ZOOM*zImage.getHeight();
						double xDisplacement=xCoordinate2/ZOOM;
						double yDisplacement=yCoordinate2/ZOOM;
						double xShift = xCoordinate-xDisplacement;
						double yShift = yCoordinate-yDisplacement;
						System.out.println("Zoom in scale = " + xScale + " " + yScale);
						zImage = new Image((int)xShift,(int)yShift,(int)zWidth,(int)zHeight,imageURL);
						canvas.clear();
						canvas.add(zImage);		
						zoomScale--;
						Group newLineList= new Group();
						for(int i=0;i<lineList.getVectorObjectCount();i++){
							Line lineTemp = (Line) lineList.getVectorObject(i);
							int x1 = lineTemp.getX1();
							double x1t = (x1+Math.abs(frameXSize))/ZOOM;
							int y1 = lineTemp.getY1();
							double y1t = (y1+Math.abs(frameYSize))/ZOOM;
							int x2 = lineTemp.getX2();
							double x2t = (x2+Math.abs(frameXSize))/ZOOM;
							int y2 = lineTemp.getY2();
							double y2t = (y2+Math.abs(frameYSize))/ZOOM;
							Line lineDraw = new Line((int)(x1t+xShift),(int)(y1t+yShift),(int)(x2t+xShift),(int)(y2t+yShift));
							lineDraw.setStrokeColor("rgb(38,196,242)");
							lineDraw.setStrokeWidth(3*(int)(Math.pow(ZOOM,zoomScale)));
							lineDraw.setStrokeOpacity(.8);
							canvas.add(lineDraw);
							Line lineStore = new Line((int)(x1t+xShift),(int)(y1t+yShift),(int)(x2t+xShift),(int)(y2t+yShift));
							newLineList.add(lineStore);
							}
						lineList.clear();
						lineList=newLineList;
					}
				}
				
			});
			canvas.addMouseMoveHandler(new MouseMoveHandler(){
				@Override
				public void onMouseMove(MouseMoveEvent e) {
					
					if(mouseDown){
						xDragged = e.getX();
						yDragged = e.getY();
						Line line = new Line(xClicked, yClicked, xDragged, yDragged);
						line.setStrokeColor("rgb(38,196,242)");
						line.setStrokeWidth(3*(int)(Math.pow(ZOOM,zoomScale)));
						line.setStrokeOpacity(.8);
						canvas.add(line);
						numberOfLines++;
						Line line2 = new Line(xClicked,yClicked,xDragged,yDragged);
						lineList.add(line2);
						xDistance = (xDragged - xClicked);
						yDistance = (yDragged - yClicked);
						distance += Math.pow(Math.pow(xDistance, 2) + Math.pow(yDistance, 2), 0.5);
						xClicked = xDragged;
						yClicked = yDragged;
						meters = distance * (109.7/60.0)/Math.pow(ZOOM, zoomScale);
					    miles = meters / 1609.34/Math.pow(ZOOM, zoomScale);
					    					    
						gFTDistance.setText(0,0,"Distance: " + (int) meters + " meters");
					}
				}
			});
		}
		
		distanceCalculator.selectTab(0);
		horizPanelMap.add(canvas);
		RootPanel.get().add(horizPanelMap);
	}
}
