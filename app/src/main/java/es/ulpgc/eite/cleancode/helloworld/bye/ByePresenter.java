package es.ulpgc.eite.cleancode.helloworld.bye;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.helloworld.app.AppMediator;
import es.ulpgc.eite.cleancode.helloworld.app.ByeToHelloState;
import es.ulpgc.eite.cleancode.helloworld.app.HelloToByeState;

/**
 * Created by Luis on March, 2021
 */
public class ByePresenter implements ByeContract.Presenter {

  public static String TAG = ByePresenter.class.getSimpleName();

  private WeakReference<ByeContract.View> view;
  private ByeState state;
  private ByeContract.Model model;
  private AppMediator mediator;

  public ByePresenter(AppMediator mediator) {
    this.mediator = mediator;
    state = mediator.getByeState();
  }

  @Override
  public void onStart() {
    // Log.e(TAG, "onStart()");

    // initialize the state if is necessary
    if (state == null) {
      state = new ByeState();
    }

    // call the model and update the state
    state.byemessage = model.getStoredData();

    // use passed state if is necessary
    HelloToByeState savedState = getStateFromPreviousScreen();
    if (savedState != null) {

      // update the model if is necessary
      model.onDataFromPreviousScreen(savedState.dataHelloToBye);

      // update the state if is necessary
      state.byemessage = savedState.toString();
    }
  }

  @Override
  public void onRestart() {
    // Log.e(TAG, "onRestart()");

    // update the model if is necessary
    model.onRestartScreen(state.byemessage);
  }

  @Override
  public void onResume() {
    // Log.e(TAG, "onResume()");

    // use passed state if is necessary
    ByeToHelloState savedState = getStateFromNextScreen();
    if (savedState != null) {

      // update the model if is necessary
      model.onDataFromNextScreen(savedState.dataByeToHello);

      // update the state if is necessary
      state.byemessage = savedState.dataByeToHello;
    }

    // call the model and update the state
    //state.data = model.getStoredData();

    // update the view
    view.get().onDataUpdated(state);

  }

  @Override
  public void onBackPressed() {
    // Log.e(TAG, "onBackPressed()");
  }

  @Override
  public void onPause() {
    // Log.e(TAG, "onPause()");
  }

  @Override
  public void onDestroy() {
    // Log.e(TAG, "onDestroy()");
  }

  private ByeToHelloState getStateFromNextScreen() {

    return mediator.getByeToHelloState();
  }
  @Override
  public void goHelloButtonClicked() {
    //Log.e(TAG, "goByeButtonClicked()");

    ByeToHelloState newState = new ByeToHelloState(state.byemessage);
    passDataToHelloScreen(newState);
    navigateToHelloScreen();
  }

  @Override
  public void sayByeButtonClicked(){
    state.byemessage = "?";

    view.get().displayByeData(state);
    startByeMessageAsyncTask();
  }
  private void startByeMessageAsyncTask(){
    state.byemessage = model.getStoredData();
    view.get().displayByeData(state);
  }

  private void passDataToHelloScreen(ByeToHelloState state){
    mediator.setByeToHelloState(state);

  }

  private void passStateToNextScreen(ByeToHelloState state) {
    mediator.setByeToHelloState(state);
  }

  private void passStateToPreviousScreen(HelloToByeState state) {
    mediator.setHelloToByeState(state);
  }
  private void navigateToHelloScreen() {
    //TODO: no implemented
    view.get().navigateToHelloScreen();
  }
  private HelloToByeState getStateFromPreviousScreen() {
    return mediator.getHelloToByeState();
  }

  @Override
  public void injectView(WeakReference<ByeContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(ByeContract.Model model) {
    this.model = model;
  }

}
