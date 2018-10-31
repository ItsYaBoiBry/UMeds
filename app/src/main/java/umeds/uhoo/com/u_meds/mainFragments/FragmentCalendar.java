package umeds.uhoo.com.u_meds.mainFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import umeds.uhoo.com.u_meds.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCalendar extends Fragment {
    ScrollView scrollView;
    LinearLayout appointments, appointmentList;
    CalendarView calendarView;
    TextView appointmentsTitle;


    public FragmentCalendar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_calendar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Declare(view);
        Listeners();
        DummyData();

    }

    public static String getDate(long milliSeconds, String dateFormat) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public void Declare(View view) {
        appointmentsTitle = view.findViewById(R.id.appointmentstitle);
        calendarView = view.findViewById(R.id.calendarView);
        appointments = view.findViewById(R.id.appointments);
        appointmentList = view.findViewById(R.id.appointsmentlist);
        scrollView = view.findViewById(R.id.scrollview);
        scrollView.setVisibility(View.GONE);
    }

    public void Listeners() {
        appointmentsTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scrollView.getVisibility() == View.VISIBLE) {
                    scrollView.setVisibility(View.GONE);
                } else {
                    scrollView.setVisibility(View.VISIBLE);
                }
            }
        });

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar calendar = Calendar.getInstance();
                calendar = eventDay.getCalendar();
                String date = getDate(calendar.getTimeInMillis(), "yyyy-MM-dd");
                Log.e("Day", date);
                appointmentsTitle.setText("Appointments\n" + date);
            }
        });
    }

    public void DummyData() {
        ArrayList<EventDay> events = new ArrayList<>();

        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        calendar1.set(2018, 9, 4);
        calendar2.set(2018, 9, 30);

        events.add(new EventDay(calendar1, R.drawable.ic_main_homecare));
        events.add(new EventDay(calendar2, R.drawable.ic_main_specialist));

        calendarView.setEvents(events);
        for (int i = 0; i <= 4; i++) {
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
            View contents = inflater.inflate(R.layout.appointments_list_item, null);
            appointments.addView(contents);
        }
    }

}
