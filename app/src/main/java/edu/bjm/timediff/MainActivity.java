package edu.bjm.timediff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private EditText etStartTime;
    private EditText etEndTime;
    private TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etStartTime = findViewById(R.id.etStartTime);
        etEndTime = findViewById(R.id.etEndTime);
        tvTime = findViewById(R.id.tvTime);

        initToggleTime();
    }

    private  void initToggleTime(){
        Button btnCalc = findViewById(R.id.btnCalc);
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTimeDifference();
            }
        });
    }

    private void calculateTimeDifference() {
        String startTimeStr = etStartTime.getText().toString();
        String endTimeStr = etEndTime.getText().toString();

        try {
            // Parsing start time
            String[] startTimeComponents = startTimeStr.split(":");
            int startHours = Integer.parseInt(startTimeComponents[0]);
            int startMinutes = startTimeComponents.length > 1 ? Integer.parseInt(startTimeComponents[1]) : 0;
            int startSeconds = startTimeComponents.length > 2 ? Integer.parseInt(startTimeComponents[2]) : 0;
            int startTimeInSeconds = startHours * 3600 + startMinutes * 60 + startSeconds;

            // Parsing end time
            String[] endTimeComponents = endTimeStr.split(":");
            int endHours = Integer.parseInt(endTimeComponents[0]);
            int endMinutes = endTimeComponents.length > 1 ? Integer.parseInt(endTimeComponents[1]) : 0;
            int endSeconds = endTimeComponents.length > 2 ? Integer.parseInt(endTimeComponents[2]) : 0;
            int endTimeInSeconds = endHours * 3600 + endMinutes * 60 + endSeconds;

            // Calculating time difference
            int timeDifferenceSeconds = Math.abs(endTimeInSeconds - startTimeInSeconds);

            // Convert time difference back to HH:MM:SS format
            int hours = timeDifferenceSeconds / 3600;
            int minutes = (timeDifferenceSeconds % 3600) / 60;
            int seconds = timeDifferenceSeconds % 60;

            // Display the result
            String formattedTimeDifference = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            String result =  timeDifferenceSeconds + " = " + formattedTimeDifference;
            tvTime.setText(result);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            // Handle invalid input
            showToast("Invalid input");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}