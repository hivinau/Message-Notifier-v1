/**
 * Copyright 2014
 *
 *
 * Sous licence Apache, Version 2.0 (la "Licence");
 * Vous ne pouvez pas utiliser ce fichier sauf en conformité avec la licence.
 * Vous pouvez obtenir une copie de la licence à l'adresse :
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Sauf si requis par la loi en vigueur ou accord écrit, le logiciel
 * Distribué sous licence est distribué «TEL QUEL»,
 * SANS GARANTIES OU CONDITIONS D'AUCUNE SORTE, express ou implicite.
 * Voir la licence pour les autorisations spécifiques aux différentes langues et
 * Limitations sous la licence.
 *
 * Contribué par : Hivinau GRAFFE
 */
package com.hg.development.apps.messagenotifier_v1.Contact;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.hg.development.apps.messagenotifier_v1.Contact.Person.*;
import com.hg.development.apps.messagenotifier_v1.Contact.Person.Number;
import com.hg.development.apps.messagenotifier_v1.Utils.Utility;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class : Contact_provider
 * @author Hivinau GRAFFE
 * @version 1.0
 */
public class Contact_provider extends Contact_Implement
{
    private Map<String,LocalContact> contacts = null;

    private Context context = null;

    /**
     * Créé une nouvelle instance de la classe {@link com.hg.development.apps.messagenotifier_v1.Contact.Person.LocalContact}.
     */
    public Contact_provider(Context context)
    {
        try
        {
            if(contacts == null)
                contacts = new HashMap<>();

            this.context = context;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    @Override
    public void ListContacts()
    {
        try
        {
            Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;

            String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
            String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
            String ID = ContactsContract.Contacts._ID;

            ContentResolver contentResolver = context.getContentResolver();

            Cursor cursor = contentResolver.query(CONTENT_URI, null,null, null, null);

            if (cursor.getCount() > 0) {

                while (cursor.moveToNext()) {
                    try {
                        String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
                        String id = cursor.getString(cursor.getColumnIndex(ID));


                        int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));
                        if (hasPhoneNumber > 0) {

                            PhoneContact contact = new PhoneContact(id, name);

                            //Log.d("GET CONTACT", "("+ id + ") " + name);

                            Lancer_GetPhoneNumberContact(contact);
                        }
                    } catch (Exception ex) {

                    }
                }
            }
            cursor.close();
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    @Override
    public List<LocalContact> getContacts() {
        //Log.d("CONTACTS LIST LENGTH"," " + contacts.size());
        return Utility.removeContactsDuplicated(contacts);
    }

    @Override
    protected synchronized void GetPhoneNumberContact(PhoneContact contact) throws Exception
    {
        try
        {
            //Log.d("GET PHONES NUMBER", "Récupération des numéros de " + contact.getNAME());

            Map<String,Number> numbers = new HashMap<>();

            ContentResolver cr = context.getContentResolver();

            String name = contact.getNAME();
            //name = name.replace("\'","\\\'");

            Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
                    "DISPLAY_NAME = '" + name + "'", null, null);

            if (cursor.moveToFirst()) {
                String contactId = contact.getID();//cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);

                while (phones.moveToNext())
                {
                    //Log.d("GET PHONES NUMBER", "Un numéro a été trouvé de " + contact.getNAME());

                    String number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).trim();
                    //Log.d("PHONES NUMBER OF " + contact.getNAME(),number);

                    int type = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                    //Log.d("TYPE OF NUMBER OF " + number,""+ type);

                    com.hg.development.apps.messagenotifier_v1.Contact.Person.Number contact_number = null;
                    switch (type)
                    {
                        case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                            contact_number = new Number(number, Number.TYPE_HOME);
                            break;
                        case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                            contact_number = new Number(number, Number.TYPE_MOBILE);
                            break;
                        case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                            contact_number = new Number(number, Number.TYPE_WORK);
                            break;
                        default: throw new Exception("Type du numéro de téléphone non pris en charge");
                    }
                    //Log.d("PHONES NUMBER OF " + contact.getNAME(),contact_number.getNumber());
                    numbers.put(contact.getID(), contact_number);
                }
                phones.close();
            }
            cursor.close();

            String photo = null;
            try {
                photo = GetPhotoThumbnailContact(contact);
            }
            catch (Exception ex)
            {
                //pas de photos, on ne renvoie rien
            }

            contacts.put(contact.getNAME(), new LocalContact(contact, Utility.removeNumbersDuplicated(numbers), photo));
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Permet de récupérer l'image d'un contact (si existe).
     * @param contact
     */
    private String GetPhotoThumbnailContact(PhoneContact contact)
    {
        InputStream inputStream = null;
        String photo = null;
        try
        {
            inputStream = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(),
                    ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(contact.getID())));

            if (inputStream != null) {
                photo =  Utility.BitMapToString(BitmapFactory.decodeStream(inputStream));

                //Log.d("PHOTO OF " + contact.getNAME(), photo);
            }
        }
        catch(Exception ex)
        {
            throw ex;
        }
        finally {
            return photo;
        }
    }
}
