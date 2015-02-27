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
package com.hg.development.apps.messagenotifier_v1.Socket.Events;

import com.hg.development.apps.messagenotifier_v1.Socket.Communicate_Profile.Profile_Channel;
import com.hg.development.apps.messagenotifier_v1.Socket.Events.Enumeration.StateConnectionSocket;

import java.util.EventObject;

/**
 * class : ConnectionEvent
 * @author Hivinau GRAFFE
 * @version 1.0
 */
public class ConnectionEvent extends EventObject {
    private StateConnectionSocket STATE = StateConnectionSocket.DISCONNECTED;
    private Profile_Channel CHANNEL = null;

    /**
     *
     * @return Etat de la connection au serveur.
     */
    public StateConnectionSocket getSTATE() {
        return STATE;
    }

    /**
     *
     * @return paramètres de connection au serveur (IP, PORT, ...).
     */
    public Profile_Channel getCHANNEL() {
        return CHANNEL;
    }

    /**
     * Créé une nouvelle instance de la classe {@link com.hg.development.apps.messagenotifier_v1.Socket.Events.ConnectionEvent}.
     * @param source Instance qui implémente cette classe.

     * @param STATE Etat de la connection au serveur.
     * @param CHANNEL paramètres de connection au serveur (IP, PORT, ...).
     */
    public ConnectionEvent(Object source, StateConnectionSocket STATE, Profile_Channel CHANNEL)
    {
        super(source);

        try
        {
            this.STATE = STATE;
            this.CHANNEL = CHANNEL;
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
}
