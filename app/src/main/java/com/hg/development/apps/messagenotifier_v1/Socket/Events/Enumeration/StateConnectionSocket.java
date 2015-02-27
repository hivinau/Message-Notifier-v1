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
package com.hg.development.apps.messagenotifier_v1.Socket.Events.Enumeration;

/**
 * enum : StateConnectionSocket
 * @author Hivinau GRAFFE
 * @version 1.0
 */
public enum StateConnectionSocket {
    /**
     * Connection de l'application.
     */
    CONNECTING,

    /**
     * Application connectée.
     */
    CONNECTED,

    /**
     * Déconnection de l'application.
     */
    DISCONNECTING,

    /**
     * Application déconnectée.
     */
    DISCONNECTED;
}
