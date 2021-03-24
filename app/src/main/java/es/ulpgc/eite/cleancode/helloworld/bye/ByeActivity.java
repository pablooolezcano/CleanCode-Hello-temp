package es.ulpgc.eite.cleancode.helloworld.bye;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.cleancode.helloworld.R;
import es.ulpgc.eite.cleancode.helloworld.hello.HelloActivity;

/**
 * Created by Luis on March, 2021
 */
public class ByeActivity
    extends AppCompatActivity implements ByeContract.View {

  public static String TAG = ByeActivity.class.getSimpleName();

  private ByeContract.Presenter presenter;
  Button sayByeButton, goHelloButton;
  TextView ByeMessage;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bye);
    getSupportActionBar().setTitle(R.string.bye_screen_title);

    sayByeButton = findViewById(R.id.sayByeButton);
    goHelloButton = findViewById(R.id.goHelloButton);
    ByeMessage = findViewById(R.id.byeMessage);

    goHelloButton.setOnClickListener(v -> presenter.goHelloButtonClicked());

    sayByeButton.setOnClickListener(v -> presenter.sayByeButtonClicked());
    /*
    if(savedInstanceState == null) {
      AppMediator.resetInstance();
    }
    */

    // do the setup
    ByeScreen.configure(this);

    if (savedInstanceState == null) {
      presenter.onStart();


    } else {
      presenter.onRestart();
    }
  }

  @Override
  protected void onResume() {
    super.onResume();

    // load the data
    presenter.onResume();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();

    presenter.onBackPressed();
  }

  @Override
  protected void onPause() {
    super.onPause();

    presenter.onPause();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();

    presenter.onDestroy();
  }

  @Override
  public void onDataUpdated(ByeViewModel viewModel) {
    //Log.e(TAG, "onDataUpdated()");

    // deal with the data

    ByeMessage.setText(viewModel.byemessage);
  }

@Override
public void displayByeData(ByeViewModel viewModel){
    ByeMessage.setText(viewModel.byemessage);

}

  @Override
  public void navigateToHelloScreen() {
    Intent intent = new Intent();
    setResult(RESULT_OK, intent);
    finish();
  }

  @Override
  public void injectPresenter(ByeContract.Presenter presenter) {
    this.presenter = presenter;
  }
}
