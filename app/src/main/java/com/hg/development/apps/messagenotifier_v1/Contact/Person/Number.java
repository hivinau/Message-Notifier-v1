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

import com.hg.development.apps.messagenotifier_v1.Contact.Person.Enumeration.TYPE;

/**
 * Class : NumberType
 * @author Hivinau GRAFFE
 * @version 1.0
 */
public class Number implements Parcelable {

    /**
     * numéro mobile
     */
    public static final int TYPE_MOBILE = 1;

    /**
     * numéro domicile fixe
     */
    public static final int TYPE_HOME = 2;

    /**
     * numéro de travail
     */
    public static final int TYPE_WORK = 3;

    private TYPE type = TYPE.mobile;
    private String number = null;

    /**
     *
     * @return type du numéro de téléphone.
     */
    public TYPE getType() {
        return type;
    }

    /**
     *
     * @return numéro de téléphone.
     */
    public String getNumber() {
        return number;
    }

    /**
     * Créé une nouvelle instance de la classe {@link com.hg.development.apps.messagenotifier_v1.Contact.Person.Number}.
     * @param number numéro de téléphone
     * @param type type du numéro de téléphone
     * @throws Exception type non pris en charge
     */
    public Number(String number,final int type) throws Exception {
        try
        {
            switch (type)
            {
                case TYPE_MOBILE : this.type = TYPE.mobile; break;
                case TYPE_HOME : this.type = TYPE.home; break;
                case TYPE_WORK : this.type = TYPE.work; break;
                default :
                    throw new  Exception("type non pris en charge");
            }

            this.number = number;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Créé une nouvelle instance de la classe {@link com.hg.development.apps.messagenotifier_v1.Contact.Person.Number}.
     * @param number parcel du numéro de téléphone.
     * @throws Exception désérialisation du type a échoué.
     */
    public Number(Parcel number) throws Exception {
        try
        {
            String type = number.readString();

            if(type == null)
                throw new Exception("Impossible de désérialiser le type du numéro de téléphone");

            if(type == "mobile")
                this.type = TYPE.mobile;
            else if(type == "home")
                this.type = TYPE.home;
            else
                this.type = TYPE.work;

            this.number = number.readString();
        }
        catch(Exception ex)
        {
            throw ex;
        }
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
            dest.writeString(type.toString());
            dest.writeString(number);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Permet de comparer cette instance actuelle avec un autre objet.
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        try {

            if (obj != null && obj instanceof Number) {
                Number other = (Number) obj;

                if(number == other.getNumber() && type == other.getType())
                    return true;
            }

            throw new Exception();
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public static final Parcelable.Creator<Number> CREATOR = new Creator<Number>() {
        @Override
        public Number createFromParcel(Parcel source) {
            try
            {
                return new Number(source);
            }
            catch (Exception ex)
            {
                //impossible de désérializer
                return null;
            }
        }

        @Override
        public Number[] newArray(int size) {
            return new Number[size];
        }
    };
}
