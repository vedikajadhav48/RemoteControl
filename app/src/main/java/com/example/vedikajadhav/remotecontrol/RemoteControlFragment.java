package com.example.vedikajadhav.remotecontrol;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RemoteControlFragment extends Fragment {
    private TextView mSelectedTextView;
    private TextView mWorkingTextView;
    private Button mZeroButton;
    private Button mOneButton;
    private Button mEnterButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_remote_control, container, false);

        mSelectedTextView = (TextView)v.findViewById(R.id.fragment_remote_control_selectedTextView);
        mWorkingTextView = (TextView)v.findViewById(R.id.fragment_remote_control_workingTextView);
        /*mZeroButton = (Button)v.findViewById(R.id.fragment_remote_control_zeroButton);
        mOneButton = (Button)v.findViewById(R.id.fragment_remote_control_oneButton);*/
        /*mEnterButton = (Button)v.findViewById(R.id.fragment_remote_control_enterButton);*/

        View.OnClickListener numberButtonListener = new View.OnClickListener(){
            public void onClick(View v){
                TextView mTextView = (TextView)v;
                String workingText = mWorkingTextView.getText().toString();
                String buttonText = mTextView.getText().toString();

                if(workingText.equals("0")){
                    mWorkingTextView.setText(buttonText);
                }else{
                    mWorkingTextView.setText(workingText + buttonText);
                }
            }
        };

       /* mZeroButton.setOnClickListener(numberButtonListener);
        mOneButton.setOnClickListener(numberButtonListener);*/

        TableLayout tableLayout = (TableLayout)v.findViewById(R.id.fragment_remote_control_tableLayout);
        int number = 1;
        for(int i=2; i<tableLayout.getChildCount() - 1; i++){
            TableRow tableRow = (TableRow)tableLayout.getChildAt(i);
            for(int j = 0 ; j < tableRow.getChildCount(); j++){
                Button button = (Button)tableRow.getChildAt(j);
                button.setText("" + number);
                button.setOnClickListener(numberButtonListener);
                number++;
            }
        }

        TableRow bottomRow = (TableRow)tableLayout.getChildAt(tableLayout.getChildCount() - 1);
        Button deleteButton = (Button)bottomRow.getChildAt(0);
        deleteButton.setText("Delete");
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWorkingTextView.setText("0");
            }
        });

        Button zeroButton = (Button)bottomRow.getChildAt(1);
        zeroButton.setText("0");
        zeroButton.setOnClickListener(numberButtonListener);

        mEnterButton = (Button)bottomRow.getChildAt(2);
        mEnterButton.setText("Enter");
        mEnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence workingText = mWorkingTextView.getText();
                if(workingText.length() > 0){
                    mSelectedTextView.setText(workingText);
                }
                mWorkingTextView.setText("0");
            }
        });

        return v;
    }
}
