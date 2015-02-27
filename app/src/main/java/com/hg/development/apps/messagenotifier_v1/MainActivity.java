package com.hg.development.apps.messagenotifier_v1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

import com.hg.development.apps.messagenotifier_v1.Contact.Contact_provider;
import com.hg.development.apps.messagenotifier_v1.Contact.Person.*;

import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            WebView view = (WebView) findViewById(R.id.text);


            Contact_provider provider = new Contact_provider(getApplicationContext());

            provider.ListContacts();

            Thread.sleep(3000);

            List<LocalContact> contacts = provider.getContacts();

            if (contacts == null)
                throw new Exception("Contacts nuls");

            StringBuilder sb = new StringBuilder();

            sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
            sb.append("<html>");
            sb.append("<head>");
            sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
            sb.append("<title>Report Project</title>");
            sb.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            sb.append("</head>");
            sb.append("<body bgcolor=\"#f6f8f1\">");

            for (LocalContact contact : contacts) {
                sb.append(contact.getName() + " : " +  "</br>");

                List<com.hg.development.apps.messagenotifier_v1.Contact.Person.Number> numbers = contact.getNumbers();
                for(com.hg.development.apps.messagenotifier_v1.Contact.Person.Number number : numbers)
                {
                    sb.append(number.getType().toString() + " : " + number.getNumber()+  "</br>");
                }

                sb.append("</br>");
            }

            sb.append("</body>");
            sb.append("</html>");

            view.loadData(sb.toString(), "text/html; charset=utf-8", "utf-8");
        }
        catch (Exception ex)
        {
            Toast toast = Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT);

           toast.show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
