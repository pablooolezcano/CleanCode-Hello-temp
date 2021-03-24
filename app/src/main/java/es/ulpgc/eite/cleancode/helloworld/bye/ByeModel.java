package es.ulpgc.eite.cleancode.helloworld.bye;

/**
 * Created by Luis on March, 2021
 */
public class ByeModel implements ByeContract.Model {

  public static String TAG = ByeModel.class.getSimpleName();

  private String data;

  public ByeModel(String data) {
    this.data = data;
  }

  @Override
  public String getStoredData() {
    // Log.e(TAG, "getStoredData()");
    return data;
  }

  @Override
  public void onRestartScreen(String data) {
    // Log.e(TAG, "onRestartScreen()");
  }

  @Override
  public void onDataFromNextScreen(String data) {
    // Log.e(TAG, "onDataFromNextScreen()");
    
  }

  @Override
  public void onDataFromPreviousScreen(String data) {
    // Log.e(TAG, "onDataFromPreviousScreen()");

  }
}