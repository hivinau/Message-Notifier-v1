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

import com.hg.development.apps.messagenotifier_v1.Utils.Utility;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.jar.Attributes;

/**
 * Class : PhoneContact
 * @author Hivinau GRAFFE
 * @version 1.0
 */
public class PhoneContact{

    /**
     * identifiant du contact.
     */
    protected String ID = null;

    /**
     * nom du contact.
     */
    protected String NAME = null;

    /**
     *
     * @return nom du contact.
     */
    public String getNAME() {
        return NAME;
    }

    /**
     *
     * @return identifiant du contact.
     */
    public String getID() {
        return ID;
    }

    /**
     * Créé une nouvelle instance de la classe {@link com.hg.development.apps.messagenotifier_v1.Contact.Person.PhoneContact}.
     * @param ID identifiant du contact.
     * @param NAME nom du contact.
     */
    public PhoneContact(String ID, String NAME) throws UnsupportedEncodingException {
        try
        {
            this.ID = ID;
            this.NAME = NAME;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    /**
     * Créé une nouvelle instance de la classe {@link com.hg.development.apps.messagenotifier_v1.Contact.Person.PhoneContact}.
     * @param contact contact
     */
    public PhoneContact(PhoneContact contact)
    {
        try
        {
            this.ID = contact.getID();
            this.NAME = contact.getNAME();
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
}
