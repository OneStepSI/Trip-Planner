package onestep.id.joints;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CreateTrip extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Toolbar toolbar;
    private Button btn_berangkat, btn_sampai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);
        btn_berangkat = (Button)findViewById(R.id.buttonBerangkat);
        btn_berangkat.setText("Keberangkatan\n DD/MM/YY");
        btn_sampai = (Button)findViewById(R.id.buttonSampai);
        btn_sampai.setText("Kedatangan\n DD/MM/YY");
    }
    public void datePicker(View view) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getFragmentManager(),"date");
    }

    private void setTanggalKeberangkatan(final Calendar calendar) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        (btn_berangkat).setText("Keberangkatan\n"+dateFormat.format(calendar.getTime()));
    }
    private void setTanggalSampai(final Calendar calendar) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        (btn_sampai).setText("Kedatangan\n"+dateFormat.format(calendar.getTime()));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setTanggalKeberangkatan(cal);
        setTanggalSampai(cal);
    }

    public static class DatePickerFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(),
                    (DatePickerDialog.OnDateSetListener)
                            getActivity(),year,month,day);
        }
    }
}
