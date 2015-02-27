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
package com.hg.development.apps.messagenotifier_v1.Contact.Person;

import android.os.Parcel;
import android.os.Parcelable;

import com.hg.development.apps.messagenotifier_v1.Contact.Person.Number;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class : LocalContact
 * @author Hivinau GRAFFE
 * @version 1.0
 */
public class LocalContact extends PhoneContact implements Parcelable {

    private List<Number> Numbers = null;

    private String Photo = null;

    /**
     * Créé une nouvelle instance de la classe {@link com.hg.development.apps.messagenotifier_v1.Contact.Person.LocalContact}.
     * @param ID
     * @param NAME
     * @param Numbers
     */
    public LocalContact(String ID, String NAME, List<Number> Numbers, String Photo) throws UnsupportedEncodingException {
        super(ID, NAME);

        try
        {
            this.Numbers = Numbers;
            this.Photo = Photo;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Créé une nouvelle instance de la classe {@link com.hg.development.apps.messagenotifier_v1.Contact.Person.LocalContact}.
     * @param contact
     * @param Numbers
     */
    public LocalContact(PhoneContact contact, List<Number> Numbers, String Photo) {
        super(contact);

        try
        {
            this.Numbers = Numbers;
            this.Photo = Photo;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Créé une nouvelle instance de la classe {@link com.hg.development.apps.messagenotifier_v1.Contact.Person.LocalContact}.
     * @param contact
     */
    public LocalContact(LocalContact contact) throws UnsupportedEncodingException {
        super(contact.getID(),contact.getNAME());

        try
        {
            this.Numbers = contact.getNumbers();
            this.Photo = contact.getPhoto();
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Créé une nouvelle instance de la classe {@link com.hg.development.apps.messagenotifier_v1.Contact.Person.LocalContact}.
     * @param contact
     */
    public LocalContact(Parcel contact) throws UnsupportedEncodingException {
        super(contact.readString(),contact.readString());

        try
        {
            this.Numbers = contact.readArrayList(Number.class.getClassLoader());
            this.Photo = contact.readString();
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     *
     * @return les octets de la photo du contact sous forme de chaîne de caractères.
     * Si aucune photo n'existe, retourne null.
     */
    public String getPhoto() {
        return Photo;
    }

    /**
     *
     * @return liste des numéros associées au contact.
     */
    public List<Number> getNumbers() {
        return Numbers;
    }

    /**
     *
     * @return identifiant du contact.
     */
    public String getID(){
        return this.getID();
    }

    /**
     *
     * @return nom du contact.
     */
    public String getName(){
        return this.getNAME();
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        try
        {
            dest.writeString(ID);
            dest.writeString(NAME);
            dest.writeTypedList(Numbers);
            dest.writeString(Photo);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    public static final Parcelable.Creator<LocalContact> CREATOR = new Creator<LocalContact>() {
        @Override
        public LocalContact createFromParcel(Parcel source) {
            try
            {
                return new LocalContact(source);
            }
            catch (UnsupportedEncodingException e)
            {
                return null;
            }
        }

        @Override
        public LocalContact[] newArray(int size) {
            return new LocalContact[size];
        }
    };
}
