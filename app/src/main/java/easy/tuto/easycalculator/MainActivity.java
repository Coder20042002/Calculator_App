package easy.tuto.easycalculator;

import static android.content.ContentValues.TAG;

import static easy.tuto.easycalculator.BuildConfig.DEBUG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
     String history="";
    TextView resultTv,solutionTv;
    MaterialButton buttonC,buttonBrackOpen,buttonBrackClose;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot,button_historyTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       resultTv = findViewById(R.id.result_tv);
       solutionTv = findViewById(R.id.solution_tv);



       assignId(buttonC,R.id.button_c);
       assignId(buttonBrackOpen,R.id.button_open_bracket);
       assignId(buttonBrackClose,R.id.button_close_bracket);
       assignId(buttonDivide,R.id.button_divide);
       assignId(buttonMultiply,R.id.button_multiply);
       assignId(buttonPlus,R.id.button_plus);
       assignId(buttonMinus,R.id.button_minus);
       assignId(buttonEquals,R.id.button_equals);
       assignId(button0,R.id.button_0);
       assignId(button1,R.id.button_1);
       assignId(button2,R.id.button_2);
       assignId(button3,R.id.button_3);
       assignId(button4,R.id.button_4);
       assignId(button5,R.id.button_5);
       assignId(button6,R.id.button_6);
       assignId(button7,R.id.button_7);
       assignId(button8,R.id.button_8);
       assignId(button9,R.id.button_9);
       assignId(buttonAC,R.id.button_ac);
       assignId(buttonDot,R.id.button_dot);






    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }
//L??u l???ch s??? b???ng h??m onPause
//    @Override
//    protected void onPause() {
//        super.onPause();
//        SharedPreferences mypref=getSharedPreferences("mysave",MODE_PRIVATE);
//        SharedPreferences.Editor myedit=mypref.edit();
//        myedit.putString("ls",history);
//        myedit.commit();
//
//    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        button_historyTV=findViewById(R.id.button_history);
        Intent intent =new Intent(this,MainActivity2.class);


        if(buttonText.equals("=")) {
                history = buttonText.equals("=") ? resultTv.getText().toString().trim() : "=" + resultTv.getText().toString().trim();
                history = (dataToCalculate + buttonText + history).toString();
                intent.putExtra("keyResult",history);


        }

        button_historyTV.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        }

);

            if (buttonText.equals("AC")) {
                solutionTv.setText("");
                resultTv.setText("0");
                return;
            }
            if (buttonText.equals("=")) {
                solutionTv.setText(resultTv.getText());
                return;
            }
            if (buttonText.equals("C")) {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            } else {
                dataToCalculate = dataToCalculate + buttonText;
            }
            solutionTv.setText(dataToCalculate);

            String finalResult = getResult(dataToCalculate);

            if (!finalResult.equals("Err")) {
                resultTv.setText(finalResult);
            }



    }
    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        if (DEBUG) {
            Log.d(TAG, "onSaveInstanceState(Bundle)");
            Log.d(TAG, ">>>> Bundle not null only");
        }
        CharSequence resultData = resultTv.getText();
        outState.putCharSequence("MyResult",resultData);



    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (DEBUG) {
            Log.d(TAG, "onSaveInstanceState(Bundle)");
            Log.d(TAG, ">>>> Bundle not null only");
        }
        CharSequence storeData=savedInstanceState.getCharSequence( "MyResult");
        resultTv.setText(storeData);
    }
}